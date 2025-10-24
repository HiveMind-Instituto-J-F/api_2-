package com.api_rest.firebase;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.io.FileInputStream;
import java.io.InputStream;

@Configuration
public class Firebase {
    Dotenv env = Dotenv.load();

    @PostConstruct
    public void firebaseDatabase() {
        System.out.println("=== INICIANDO CONEXÃO FIREBASE ===");
        String filePath = env.get("GOOGLE_APPLICATION_CREDENTIALS");
        try {
            InputStream serviceAccount = new FileInputStream(filePath);
            GoogleCredentials credentials = GoogleCredentials.fromStream(serviceAccount);

            FirebaseOptions options = new FirebaseOptions.Builder()
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