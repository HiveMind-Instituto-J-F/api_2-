package com.api_rest.service;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

public class ServiceFirebase {
    @Service
    public class MyFirebaseService {

        private final DatabaseReference databaseRef;

        @Autowired
        public MyFirebaseService(FirebaseDatabase firebaseDatabase) {
            this.databaseRef = firebaseDatabase.getReference("seus-dados"); // Nome do nó principal
        }

        public void saveData(String key, Object data) {
            databaseRef.child(key).setValueAsync(data);
        }

        // Métodos para ler, atualizar e excluir dados...
    }
}
