package com.api_rest.firebase;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

@Configuration
public class Firebase {

    @PostConstruct
    public void firebaseDatabase() {
        System.out.println("=== INICIANDO CONEXÃO FIREBASE ===");

        try {
            // Lê a variável de ambiente configurada no Render
            String serviceAccountPath = System.getenv("GOOGLE_APPLICATION_CREDENTIALS");

            InputStream serviceAccount;

            if (serviceAccountPath != null && !serviceAccountPath.isEmpty()) {
                serviceAccount = new FileInputStream(serviceAccountPath);
            } else {
                throw new RuntimeException("Nenhuma credencial do Firebase configurada!");
            }

            GoogleCredentials credentials = GoogleCredentials.fromStream(serviceAccount);

            FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(credentials)
                    .build();

            if (FirebaseApp.getApps().isEmpty()) {
                FirebaseApp.initializeApp(options);
                System.out.println("✅ CONEXÃO COM FIREBASE ESTABELECIDA COM SUCESSO!");
            }

        } catch (Exception e) {
            System.out.println("❌ ERRO NA CONEXÃO COM FIREBASE!");
            System.out.println("🔴 Motivo: " + e.getMessage());
            throw new RuntimeException("Falha na configuração do Firebase", e);
        }
    }
}
