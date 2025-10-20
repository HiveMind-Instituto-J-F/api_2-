package com.api_rest.repository;

import com.api_rest.model.Maquina;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositoryMaquina extends JpaRepository<Maquina, Long> {
}
