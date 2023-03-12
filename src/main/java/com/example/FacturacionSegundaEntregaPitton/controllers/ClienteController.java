package com.example.FacturacionSegundaEntregaPitton.controllers;



import com.example.FacturacionSegundaEntregaPitton.entitys.Cliente;
import com.example.FacturacionSegundaEntregaPitton.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    //CREATE -------------------------
    //http://localhost:8080/cliente
    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> crearCliente(@RequestBody Cliente clienteRecibido) {

        return ResponseEntity.ok(clienteService.guardarCliente(clienteRecibido));
    }

    //READ -------------------------
    //http://localhost:8080/cliente/1
    @GetMapping(value = "{id}")
    public ResponseEntity<?> obtenerClientePorID(@PathVariable(name = "id") final Long id) {

        return ResponseEntity.ok(clienteService.leerCliente(id));
    }

    //http://localhost:8080/cliente/1
    @PutMapping(value = "{id}")
    public ResponseEntity<?> obtenerClientePorID(@PathVariable(name = "id") final Long id,
                                                 @RequestBody Cliente clienteRecibido) {

        Optional<Cliente> posibleCliente = clienteService.leerCliente(id);
        if (posibleCliente.isPresent()) {
            Cliente clienteBuscado = posibleCliente.get();
            clienteBuscado.setNombre(clienteRecibido.getNombre());
            clienteBuscado.setApellido(clienteRecibido.getApellido());
            clienteBuscado.setDni(clienteRecibido.getDni());
            return ResponseEntity.ok(clienteService.guardarCliente(clienteBuscado));

        }else{return ResponseEntity.ok("Cliente Inexistente");}
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<?> eliminarClientePorID(@PathVariable(name = "id") final Long id) {

        return ResponseEntity.ok(clienteService.borrarCliente(id));
    }
}
