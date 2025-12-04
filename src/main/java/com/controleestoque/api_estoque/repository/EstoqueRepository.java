package com.controleestoque.api_estoque.repository;

import com.controleestoque.api_estoque.model.Estoque;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstoqueRepository extends JpaRepository<Estoque, Long> {
}
