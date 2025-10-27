package com.aula.mobile_hivemind.ui.home;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.aula.mobile_hivemind.MainActivity;
import com.aula.mobile_hivemind.R;
import com.aula.mobile_hivemind.api.RetrofitClient;
import com.aula.mobile_hivemind.dto.RegistroParadaResponseDTO;
import com.aula.mobile_hivemind.recyclerViewParadas.Parada;
import com.aula.mobile_hivemind.recyclerViewParadas.ParadaAdapter;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {
    ImageButton filtrarParadas;
    private ChipGroup chipGroupSetores;
    private Chip chipTodos;

    private RecyclerView recyclerViewParadas;
    private ParadaAdapter paradaAdapter;

    private List<Parada> paradasList;
    private List<Parada> allParadasList;
    private com.aula.mobile_hivemind.api.ApiService apiService;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        filtrarParadas = view.findViewById(R.id.filterButton);
        chipGroupSetores = view.findViewById(R.id.chipGroupSetores);
        recyclerViewParadas = view.findViewById(R.id.recyclerViewParadas);

        // Inicializar API Service
        apiService = RetrofitClient.getApiService();

        // Inicializar listas
        paradasList = new ArrayList<>();
        allParadasList = new ArrayList<>();

        // Configurar RecyclerView
        recyclerViewParadas.setLayoutManager(new LinearLayoutManager(getContext()));
        paradaAdapter = new ParadaAdapter(paradasList);

        paradaAdapter.setOnItemClickListener(parada -> {
            abrirModalParada(parada);
        });

        recyclerViewParadas.setAdapter(paradaAdapter);

        carregarParadas();

        filtrarParadas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (chipGroupSetores.getVisibility() == View.GONE) {
                    chipGroupSetores.setVisibility(View.VISIBLE);
                } else {
                    chipGroupSetores.setVisibility(View.GONE);
                }
            }
        });

        chipGroupSetores.setVisibility(View.GONE);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (getActivity() instanceof MainActivity) {
            ((MainActivity) getActivity()).setFabVisibility(true);
            ((MainActivity) getActivity()).setBottomNavigationVisibility(true);
        }
    }

    private void abrirModalParada(Parada parada) {
        View modalView = LayoutInflater.from(requireContext()).inflate(R.layout.modal_parada, null);

        TextView txtIdMaquina = modalView.findViewById(R.id.txtIdMaquina);
        TextView txtCodigoColaborador = modalView.findViewById(R.id.txtCodigoColaborador);
        TextView txtNomeMaquina = modalView.findViewById(R.id.txtNomeMaquina);
        TextView txtSetor = modalView.findViewById(R.id.txtSetor);
        TextView txtDataParada = modalView.findViewById(R.id.txtData);
        TextView txtDescricaoParada = modalView.findViewById(R.id.txtDescricao);

        txtIdMaquina.setText(String.valueOf(parada.getIdMaquina()));
        txtCodigoColaborador.setText(String.valueOf(parada.getCodigoColaborador()));
        txtNomeMaquina.setText(parada.getNomeMaquina());
        txtSetor.setText(parada.getSetor());
        txtDataParada.setText(parada.getDataParada());
        txtDescricaoParada.setText(parada.getDescricaoParada());

        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(requireContext());
        bottomSheetDialog.setContentView(modalView);
        bottomSheetDialog.show();

        ImageButton btnFechar = modalView.findViewById(R.id.btnFechar);
        if (btnFechar != null) {
            btnFechar.setOnClickListener(v -> bottomSheetDialog.dismiss());
        }
    }

    private void carregarParadas() {
        Call<List<RegistroParadaResponseDTO>> call = apiService.getAllRegistros();
        call.enqueue(new Callback<List<RegistroParadaResponseDTO>>() {
            @Override
            public void onResponse(Call<List<RegistroParadaResponseDTO>> call, Response<List<RegistroParadaResponseDTO>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    paradasList.clear();
                    allParadasList.clear();

                    // Converter RegistroParadaResponseDTO para Parada
                    for (RegistroParadaResponseDTO registro : response.body()) {
                        Parada parada = converterParaParada(registro);
                        paradasList.add(parada);
                        allParadasList.add(parada);
                    }

                    paradaAdapter.notifyDataSetChanged();

                    // Extrair setores únicos e adicionar chips
                    Set<String> setores = new HashSet<>();
                    for (Parada parada : paradasList) {
                        if (parada.getSetor() != null && !parada.getSetor().isEmpty()) {
                            setores.add(parada.getSetor()); // Usa o setor real da API
                        }
                    }

                    addChipsToChipGroup(new ArrayList<>(setores));

//                    Toast.makeText(getContext(), "Paradas carregadas: " + paradasList.size(), Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(getContext(), "Erro ao carregar paradas: " + response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<RegistroParadaResponseDTO>> call, Throwable t) {
                Toast.makeText(getContext(), "Falha na conexão: " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private Parada converterParaParada(RegistroParadaResponseDTO registro) {
        return new Parada(
                registro.getId_maquina(),  // idMaquina
                registro.getNomeMaquina(), // nomeMaquina
                registro.getId_usuario(),  // códigoColaborador
                registro.getSetor(),       // setor
                registro.getDescricao(),    // descricaoParada
                registro.getDate()        // dataParada
        );
    }

    private void addChipsToChipGroup(List<String> sectors) {
        // Limpa chips antigos
        chipGroupSetores.removeAllViews();

        // Chip "Todos"
        chipTodos = new Chip(requireContext());
        chipTodos.setText("Todos");
        chipTodos.setId(View.generateViewId());
        chipTodos.setCheckable(true);
        chipTodos.setClickable(true);
        chipTodos.setChecked(true);
        chipGroupSetores.addView(chipTodos);

        // Chips para cada setor
        for (String sectorName : sectors) {
            Chip chip = new Chip(requireContext());
            chip.setText(sectorName);
            chip.setId(View.generateViewId());
            chip.setCheckable(true);
            chip.setClickable(true);
            chipGroupSetores.addView(chip);
        }

        // Listener para atualização de seleção
        chipGroupSetores.setOnCheckedStateChangeListener((group, checkedIds) -> {
            if (checkedIds.isEmpty()) {
                chipTodos.setChecked(true);
                paradaAdapter.setParadas(new ArrayList<>(allParadasList));
            } else {
                List<Parada> filteredList = new ArrayList<>();
                boolean todosSelecionado = false;

                // Verifica cada chip selecionado
                for (int id : checkedIds) {
                    Chip chip = group.findViewById(id);
                    if (chip != null) {
                        String name = chip.getText().toString();
                        if (name.equals("Todos")) {
                            todosSelecionado = true;
                            break;
                        } else {
                            filteredList.addAll(filterParadasBySector(name));
                        }
                    }
                }

                if (todosSelecionado) {
                    // Se "Todos" estiver selecionado, mostra todas as paradas
                    filteredList = new ArrayList<>(allParadasList);

                    // Desmarca outros chips
                    for (int i = 0; i < group.getChildCount(); i++) {
                        Chip currentChip = (Chip) group.getChildAt(i);
                        if (!currentChip.getText().toString().equals("Todos")) {
                            currentChip.setChecked(false);
                        }
                    }
                }

                // Atualiza RecyclerView
                paradaAdapter.setParadas(filteredList);
            }
        });
    }

    private List<Parada> filterParadasBySector(String sectorName) {
        List<Parada> filteredList = new ArrayList<>();
        for (Parada parada : allParadasList) {
            if (sectorName.equals(parada.getSetor())) {
                filteredList.add(parada);
            }
        }
        return filteredList;
    }
}