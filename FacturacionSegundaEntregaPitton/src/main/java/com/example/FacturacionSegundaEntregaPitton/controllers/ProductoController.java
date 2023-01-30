package com.example.FacturacionSegundaEntregaPitton.controllers;


import com.example.FacturacionSegundaEntregaPitton.entitys.Producto;
import com.example.FacturacionSegundaEntregaPitton.services.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/producto")
public class ProductoController {
    @Autowired
    private ProductoService productoService;

    //CREATE -------------------------
    //http://localhost:8080/producto
    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> crearProducto(@RequestBody Producto productoRecibido) {

        return ResponseEntity.ok(productoService.guardarProducto(productoRecibido));
    }

    //READ -------------------------
    //http://localhost:8080/producto/1
    @GetMapping(value = "{id}")
    public ResponseEntity<?> obtenerProductoPorID(@PathVariable(name = "id") final Long id) {

        return ResponseEntity.ok(productoService.leerProducto(id));
    }

    //http://localhost:8080/producto/1
    @PutMapping(value = "{id}")
    public ResponseEntity<?> obtenerProductoPorID(@PathVariable(name = "id") final Long id,
                                                  @RequestBody Producto productoRecibido) {

        Optional<Producto> posibleProducto = productoService.leerProducto(id);
        if (posibleProducto.isPresent()) {
            Producto productoBuscado = posibleProducto.get();
            productoBuscado.setCodigo(productoRecibido.getCodigo());
            productoBuscado.setDescripcion(productoRecibido.getDescripcion());
            productoBuscado.setCantidad(productoRecibido.getCantidad());
            productoBuscado.setPrecio(productoRecibido.getPrecio());
            return ResponseEntity.ok(productoService.guardarProducto(productoBuscado));
        } else {
            return ResponseEntity.ok("Producto Inexistente");
        }
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<?> eliminarProductoPorID(@PathVariable(name = "id") final Long id) {

        return ResponseEntity.ok(productoService.borrarProducto(id));
    }
}
