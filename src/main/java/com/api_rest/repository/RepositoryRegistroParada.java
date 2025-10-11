package com.api_rest.repository;

import com.api_rest.model.RegistroParadas;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositoryRegistroParada extends JpaRepository<RegistroParadas, Long> {
}
