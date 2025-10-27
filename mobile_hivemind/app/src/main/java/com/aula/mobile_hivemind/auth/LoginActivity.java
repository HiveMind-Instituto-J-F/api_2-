package com.aula.mobile_hivemind.auth;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.aula.mobile_hivemind.MainActivity;
import com.aula.mobile_hivemind.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;

public class LoginActivity extends AppCompatActivity {

    private FirebaseAuth auth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Esconder action bar
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        Button btnEntrar = findViewById(R.id.btnEntrar);
        btnEntrar.setOnClickListener(v -> {
            String txtEmail = ((EditText) findViewById(R.id.editTextEMAILCONT)).getText().toString();
            String txtSenha = ((EditText) findViewById(R.id.editTextPassword)).getText().toString();

            if (txtEmail.isEmpty() || txtSenha.isEmpty()) {
                Toast.makeText(LoginActivity.this, "Preencha todos os campos", Toast.LENGTH_SHORT).show();
                return;
            }

            // Fazer login
            auth.signInWithEmailAndPassword(txtEmail, txtSenha)
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            // Login bem-sucedido - determinar userType baseado no email
                            String userType = determinarUserTypePorEmail(txtEmail);

                            // Ir para MainActivity passando o tipo de usuário
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            intent.putExtra("USER_TYPE", userType);
                            startActivity(intent);
                            finish();
                        } else {
                            // Tratar erros de login
                            String mensagemErro = "Erro ao fazer login";

                            try {
                                throw task.getException();
                            } catch (FirebaseAuthInvalidUserException e) {
                                mensagemErro = "Usuário não cadastrado";
                            } catch (FirebaseAuthInvalidCredentialsException e) {
                                mensagemErro = "E-mail ou senha inválidos";
                            } catch (Exception e) {
                                mensagemErro = "Erro: " + e.getMessage();
                            }

                            Toast.makeText(LoginActivity.this, mensagemErro, Toast.LENGTH_LONG).show();
                        }
                    });
        });
    }

    private String determinarUserTypePorEmail(String email) {
        // Lógica simples baseada no email
        if (email.equals("userRegular@email.com")) {
            return "regular";
        } else if (email.equals("userMOP@email.com")) {
            return "MOP";
        } else if (email.equals("userRH@email.com")) {
            return "RH";
        } else {
            return "regular"; // padrão
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        // Se já estiver logado, ir direto para MainActivity
        if (auth.getCurrentUser() != null) {
            String userType = determinarUserTypePorEmail(auth.getCurrentUser().getEmail());
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            intent.putExtra("USER_TYPE", userType);
            startActivity(intent);
            finish();
        }
    }
}