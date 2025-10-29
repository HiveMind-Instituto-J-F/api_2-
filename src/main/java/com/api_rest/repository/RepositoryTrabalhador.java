package com.api_rest.repository;

import com.api_rest.model.Trabalhador;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RepositoryTrabalhador extends JpaRepository<Trabalhador, Long> {
    Optional<Trabalhador> findByDesLogin(String desLogin);
    //boolean existsBydes_login(String des_login);
}
