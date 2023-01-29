package com.example.FacturacionSegundaEntregaPitton.services;

import com.example.FacturacionSegundaEntregaPitton.entitys.Cliente;
import com.example.FacturacionSegundaEntregaPitton.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public Cliente guardarCliente(Cliente clienteAGuardar){
        return clienteRepository.save(clienteAGuardar);
    }
    public Optional<Cliente> leerCliente(Long idCliente){

        return  clienteRepository.findById(idCliente);


}

    public String borrarCliente(Long idCliente) {
        clienteRepository.deleteById(idCliente);
        return "Eliminado";
    }


}
