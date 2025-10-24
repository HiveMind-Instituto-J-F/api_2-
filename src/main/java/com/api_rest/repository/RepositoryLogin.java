package com.api_rest.repository;

import com.api_rest.model.Login;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositoryLogin extends JpaRepository<Login, Long> {
}
