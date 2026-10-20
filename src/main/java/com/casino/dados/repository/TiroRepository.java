package com.casino.dados.repository;

import com.casino.dados.model.Tiro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TiroRepository extends JpaRepository<Tiro, Long> {
}
