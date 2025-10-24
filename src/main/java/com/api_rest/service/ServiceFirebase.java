package com.api_rest.service;

import com.api_rest.model.Login;
import com.google.firebase.auth.*;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class ServiceFirebase {

    private final ServiceLogin serviceLogin;

    public ServiceFirebase(ServiceLogin serviceLogin){
        this.serviceLogin = serviceLogin;
    }

    public UserRecord.CreateRequest criarUsuario(String email, String senha) {
        UserRecord.CreateRequest request = new UserRecord.CreateRequest()
                .setEmail(email)
                .setPassword(senha);
        return request;
    }
    public List<String> listarIds() throws FirebaseAuthException {
        ListUsersPage page = FirebaseAuth.getInstance().listUsers(null);
        List<String> users = new ArrayList<>();
        for (ExportedUserRecord user : page.iterateAll()) {
            users.add(user.getUid());
        }
        return users;
    }

    public UserRecord enviarDadosFirebase() throws FirebaseAuthException {
        List<Login> login = serviceLogin.listarUsuarios();
        ListUsersPage page = FirebaseAuth.getInstance().listUsers(null);
        List<ExportedUserRecord> pages = (List<ExportedUserRecord>) page.getValues();

        if (!pages.isEmpty()) {
            for (int i = 0; i <= pages.size(); i++) {
                if (login.get(i).getEmail().equals(pages.get(i).getEmail())) {
                    login.remove(i);
                }
            }
        }

        UserRecord resposta = null;
        for (int i = 0; i <= login.size(); i++) {
            UserRecord.CreateRequest user = criarUsuario(login.get(i).getEmail(), login.get(i).getSenha());
            resposta = FirebaseAuth.getInstance().createUser(user);
        }

        return resposta;
    }

}
