package com.example.FacturacionSegundaEntregaPitton.services;

import com.example.FacturacionSegundaEntregaPitton.entitys.Producto;
import com.example.FacturacionSegundaEntregaPitton.repositories.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    public Producto guardarProducto(Producto productoAGuardar) {
        return productoRepository.save(productoAGuardar);
    }

    public Optional<Producto> leerProducto(Long idProducto) {

        return productoRepository.findById(idProducto);


    }

    public String borrarProducto(Long idProducto) {
        productoRepository.deleteById(idProducto);
        return "Eliminado";
    }

}
