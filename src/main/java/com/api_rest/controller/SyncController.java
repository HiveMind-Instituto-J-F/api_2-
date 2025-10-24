package com.api_rest.controller;

import com.api_rest.service.FirebaseSyncService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/sync")
public class SyncController {

    @Autowired
    private FirebaseSyncService firebaseSyncService;

    @PostMapping("/full")
    public ResponseEntity<String> triggerFullSync() {
        try {
            firebaseSyncService.fullSync();
            return ResponseEntity.ok("Sincronização completa de trabalhadores iniciada");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erro na sincronização: " + e.getMessage());
        }
    }

    @PostMapping("/new")
    public ResponseEntity<String> triggerSyncNew() {
        try {
            firebaseSyncService.syncNewTrabalhadores();
            return ResponseEntity.ok("Sincronização de novos trabalhadores iniciada");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erro na sincronização: " + e.getMessage());
        }
    }
}