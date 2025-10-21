package com.api_rest.firebase;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.io.FileInputStream;
import java.io.InputStream;

@Configuration
public class Firebase {

    @PostConstruct // ✅ Executa após a inicialização do bean
    public void firebaseDatabase() {
        System.out.println("=== INICIANDO CONEXÃO FIREBASE ===");

        try {
            InputStream serviceAccount = getClass().getClassLoader().getResourceAsStream("hivemind-fb-mdb-dataset-firebase-adminsdk-fbsvc-f072f90534.json");
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