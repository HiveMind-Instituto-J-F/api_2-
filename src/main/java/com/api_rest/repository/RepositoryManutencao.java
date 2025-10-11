package com.api_rest.repository;

import com.api_rest.model.Manutencao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositoryManutencao extends JpaRepository<Manutencao, Long> {

}
