package com.api_rest.service;

import com.api_rest.model.Trabalhador;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.Query;
import org.springframework.context.annotation.Lazy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@Service
public class FirebaseSyncService {

    @Autowired
    private Firestore firestore;

    private ServiceTrabalhador serviceTrabalhador;

    @Autowired
    public void setServiceTrabalhador(@Lazy ServiceTrabalhador serviceTrabalhador) {
        this.serviceTrabalhador = serviceTrabalhador;
    }

    @Async
    public CompletableFuture<String> syncTrabalhadorToFirebase(Trabalhador trabalhador) {
        try {
            Map<String, Object> firebaseTrabalhador = new HashMap<>();
            firebaseTrabalhador.put("id", trabalhador.getId());
            firebaseTrabalhador.put("id_planta", trabalhador.getId_planta());
            firebaseTrabalhador.put("tipo_perfil", trabalhador.getTipo_perfil());
            firebaseTrabalhador.put("login", trabalhador.getLogin());
            firebaseTrabalhador.put("senha", trabalhador.getSenha());
            firebaseTrabalhador.put("setor", trabalhador.getSetor());
            firebaseTrabalhador.put("sqlId", trabalhador.getId());
            firebaseTrabalhador.put("lastSync", new Date());

            ApiFuture<WriteResult> result = firestore.collection("trabalhadores")
                    .document(String.valueOf(trabalhador.getId()))
                    .set(firebaseTrabalhador);

            result.get();
            return CompletableFuture.completedFuture("Trabalhador " + trabalhador.getLogin() + " sincronizado com Firebase");

        } catch (Exception e) {
            e.printStackTrace();
            return CompletableFuture.completedFuture("Erro ao sincronizar trabalhador: " + e.getMessage());
        }
    }

    public boolean trabalhadorExistsInFirebase(String login) {
        try {
            Query query = firestore.collection("trabalhadores")
                    .whereEqualTo("login", login)
                    .limit(1);

            ApiFuture<QuerySnapshot> querySnapshot = query.get();
            return !querySnapshot.get().isEmpty();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean trabalhadorExistsInFirebase(Long id) {
        try {
            ApiFuture<QuerySnapshot> querySnapshot = firestore.collection("trabalhadores")
                    .whereEqualTo("id", id)
                    .limit(1)
                    .get();

            return !querySnapshot.get().isEmpty();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Async
    public void fullSync() {
        try {
            List<Trabalhador> allTrabalhadores = serviceTrabalhador.listarTrabalhadores();

            for (Trabalhador trabalhador : allTrabalhadores) {
                syncTrabalhadorToFirebase(trabalhador);
            }

            System.out.println("Sincronização completa concluída: " + allTrabalhadores.size() + " trabalhadores");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Async
    public void syncNewTrabalhadores() {
        try {
            List<Trabalhador> allTrabalhadores = serviceTrabalhador.listarTrabalhadores();
            int syncedCount = 0;

            for (Trabalhador trabalhador : allTrabalhadores) {
                if (!trabalhadorExistsInFirebase(trabalhador.getId())) {
                    syncTrabalhadorToFirebase(trabalhador);
                    syncedCount++;
                    System.out.println("Novo trabalhador sincronizado: " + trabalhador.getLogin());
                }
            }

            System.out.println("Sincronização de novos trabalhadores: " + syncedCount + " adicionados");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Async
    public CompletableFuture<String> deleteTrabalhadorFromFirebase(Long id) {
        try {
            ApiFuture<WriteResult> result = firestore.collection("trabalhadores")
                    .document(String.valueOf(id))
                    .delete();

            result.get();
            return CompletableFuture.completedFuture("Trabalhador " + id + " removido do Firebase");
        } catch (Exception e) {
            e.printStackTrace();
            return CompletableFuture.completedFuture("Erro ao remover trabalhador do Firebase: " + e.getMessage());
        }
    }

    @Scheduled(fixedRate = 30000)
    public void scheduledSync() {
        System.out.println("Executando sincronização agendada...");
        syncNewTrabalhadores();
    }
}