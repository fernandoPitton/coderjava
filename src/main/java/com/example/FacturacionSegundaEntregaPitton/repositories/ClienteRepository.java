package com.example.FacturacionSegundaEntregaPitton.repositories;

import com.example.FacturacionSegundaEntregaPitton.entitys.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
