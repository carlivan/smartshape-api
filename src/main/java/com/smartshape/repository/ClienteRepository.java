package com.smartshape.repository;

import com.smartshape.dto.ClienteDTO;
import com.smartshape.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    long countByPersonalId(Long personalId);

    @Query("select new com.smartshape.dto.ClienteDTO(c.id, c.nome) " +
            "from Cliente c where c.personal.id = :personalId")
    List<ClienteDTO> findByPersonalId(@Param("personalId") Long personalId);
}
