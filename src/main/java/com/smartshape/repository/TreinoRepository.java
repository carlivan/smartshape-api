package com.smartshape.repository;

import com.smartshape.model.Treino;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TreinoRepository extends JpaRepository<Treino, Long> {

    List<Treino> findByClienteId(Long clienteId);
}
