package com.example.FacturacionSegundaEntregaPitton.repositories;

import com.example.FacturacionSegundaEntregaPitton.entitys.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
}
