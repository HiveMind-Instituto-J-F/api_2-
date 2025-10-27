package com.aula.mobile_hivemind.ui.home.paradas;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import com.aula.mobile_hivemind.MainActivity;
import com.aula.mobile_hivemind.R;
import com.aula.mobile_hivemind.api.RetrofitClient;
import com.aula.mobile_hivemind.dto.RegistroParadaRequestDTO;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.datepicker.CalendarConstraints;
import com.google.android.material.datepicker.MaterialDatePicker;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddParadaFragment extends Fragment {

    private ImageButton btnBack;
    private MaterialButton btnAdicionarParada;
    private EditText idMaquina;
    private EditText nomeMaquina;
    private EditText codigoColaborador;
    private EditText setor;
    private EditText descricaoParada;
    private EditText editTextDATAPARADA;
    private com.aula.mobile_hivemind.api.ApiService apiService;

    public AddParadaFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_add_parada, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Inicializar API Service
        apiService = RetrofitClient.getApiService();

        hideFab();

        idMaquina = view.findViewById(R.id.editIdMaquina);
        nomeMaquina = view.findViewById(R.id.editNomeMaquina);
        codigoColaborador = view.findViewById(R.id.editCodigoColaborador);
        setor = view.findViewById(R.id.editSetor);
        descricaoParada = view.findViewById(R.id.editDescricaoParada);
        editTextDATAPARADA = view.findViewById(R.id.editTextDATAPARADA);

        editTextDATAPARADA.setFocusable(false);
        editTextDATAPARADA.setOnTouchListener((v, event) -> {
            if (event.getAction() == MotionEvent.ACTION_UP) {
                mostrarDatePicker();
                return true;
            }
            return false;
        });

        btnBack = view.findViewById(R.id.btnBack);
        btnBack.setOnClickListener(v -> {
            navigateBack();
        });

        btnAdicionarParada = view.findViewById(R.id.btnAdicionarParada);
        btnAdicionarParada.setOnClickListener(v -> {
            salvarParada();
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        hideFab();
    }

    private void hideFab() {
        if (getActivity() instanceof MainActivity) {
            ((MainActivity) getActivity()).setFabVisibility(false);
            ((MainActivity) getActivity()).setBottomNavigationVisibility(false);
        }
    }

    private void showFab() {
        if (getActivity() instanceof MainActivity) {
            ((MainActivity) getActivity()).setFabVisibility(true);
            ((MainActivity) getActivity()).setBottomNavigationVisibility(true);
        }
    }

    private void navigateBack() {
        showFab();

        try {
            NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_activity_main);
            navController.popBackStack();
        } catch (Exception e) {
            if (getActivity() != null) {
                getActivity().onBackPressed();
            }
        }
    }

    private void mostrarDatePicker() {
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        long today = calendar.getTimeInMillis();

        // Constraints para permitir datas até hoje
        CalendarConstraints.Builder constraintsBuilder = new CalendarConstraints.Builder();
        constraintsBuilder.setEnd(today);

        MaterialDatePicker<Long> datePicker = MaterialDatePicker.Builder.datePicker()
                .setTitleText("Selecione a data")
                .setCalendarConstraints(constraintsBuilder.build())
                .build();

        datePicker.show(getParentFragmentManager(), "DATE_PICKER");

        datePicker.addOnPositiveButtonClickListener(selection -> {
            if (selection > today) {
                Toast.makeText(requireContext(), "Não é possível adicionar paradas futuras", Toast.LENGTH_SHORT).show();
                return;
            }

            SimpleDateFormat sdf = new SimpleDateFormat("dd, MMM yyyy", Locale.getDefault());
            sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
            String selectedDate = sdf.format(new Date(selection));
            editTextDATAPARADA.setText(selectedDate);
        });
    }

    private void salvarParada() {
        try {
            if (idMaquina == null || nomeMaquina == null || codigoColaborador == null || setor == null || descricaoParada == null || editTextDATAPARADA == null) {
                Toast.makeText(requireContext(), "Preencha todos os campos", Toast.LENGTH_SHORT).show();
                return;
            }

            String idMaquinaStr = idMaquina.getText().toString().trim();
            String nomeMaquinaText = nomeMaquina.getText().toString().trim();
            String codigoColaboradorStr = codigoColaborador.getText().toString().trim();
            String setorText = setor.getText().toString().trim();
            String descricaoParadaText = descricaoParada.getText().toString().trim();
            String dataParadaText = editTextDATAPARADA.getText().toString().trim();

            if (idMaquinaStr.isEmpty() || codigoColaboradorStr.isEmpty()) {
                Toast.makeText(requireContext(), "ID Máquina e Código Colaborador são obrigatórios", Toast.LENGTH_SHORT).show();
                return;
            }

            Integer idMaquinaText = Integer.parseInt(idMaquinaStr);
            Integer codigoColaboradorText = Integer.parseInt(codigoColaboradorStr);

            if (nomeMaquinaText.isEmpty() || setorText.isEmpty() || descricaoParadaText.isEmpty() || dataParadaText.isEmpty()) {
                Toast.makeText(requireContext(), "Preencha todos os campos", Toast.LENGTH_SHORT).show();
                return;
            }

            // Criar DTO para a API MongoDB
            RegistroParadaRequestDTO requestDTO = new RegistroParadaRequestDTO(
                    idMaquinaText,         // id_maquina
                    nomeMaquinaText,       // nomeMaquina
                    codigoColaboradorText, // id_usuario (usando código colaborador como ID)
                    setorText,             // setor
                    descricaoParadaText,   // descricao
                    dataParadaText         // date
            );

            salvarNoMongoDB(requestDTO);
        } catch (NumberFormatException e) {
            Toast.makeText(requireContext(), "ID Máquina e Código Colaborador devem ser números", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(requireContext(), "Erro ao salvar parada", Toast.LENGTH_SHORT).show();
        }
    }

    private void salvarNoMongoDB(RegistroParadaRequestDTO requestDTO) {
        btnAdicionarParada.setEnabled(false);
        btnAdicionarParada.setText("Salvando...");

        Call<ResponseBody> call = apiService.criarRegistro(requestDTO);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                btnAdicionarParada.setEnabled(true);
                btnAdicionarParada.setText("Adicionar Parada");


                Log.d("API_RESPONSE", "Status Code: " + response.code());

                if (response.isSuccessful()) {
                    String rawResponse = null;
                    try {
                        rawResponse = response.body() != null ? response.body().string() : "Resposta vazia";
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    Log.d("API_RESPONSE", "Raw Response: " + rawResponse);

//                    Toast.makeText(requireContext(), rawResponse, Toast.LENGTH_SHORT).show();

                    limparCampos();

                    try {
                        NavHostFragment.findNavController(AddParadaFragment.this)
                                .navigate(R.id.confirmationFragment);
                    } catch (Exception e) {
                        navigateBack();
                    }
                } else {
                    Toast.makeText(requireContext(), "Erro ao salvar parada: " + response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                btnAdicionarParada.setEnabled(true);
                btnAdicionarParada.setText("Adicionar Parada");

                Log.e("API_ERROR", "Falha na conexão: ", t);
                Toast.makeText(requireContext(), "Falha na conexão: " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void limparCampos() {
        idMaquina.setText("");
        nomeMaquina.setText("");
        codigoColaborador.setText("");
        setor.setText("");
        descricaoParada.setText("");
        editTextDATAPARADA.setText("");
    }
}