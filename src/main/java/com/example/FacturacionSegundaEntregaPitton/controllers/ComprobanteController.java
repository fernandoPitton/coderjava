package com.example.FacturacionSegundaEntregaPitton.controllers;

import com.example.FacturacionSegundaEntregaPitton.entitys.Cliente;
import com.example.FacturacionSegundaEntregaPitton.entitys.Comprobante;
import com.example.FacturacionSegundaEntregaPitton.entitys.Producto;
import com.example.FacturacionSegundaEntregaPitton.request.ComprobanteRequest;
import com.example.FacturacionSegundaEntregaPitton.services.ClienteService;
import com.example.FacturacionSegundaEntregaPitton.services.ComprobanteService;
import com.example.FacturacionSegundaEntregaPitton.services.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/comprobante")
public class ComprobanteController {

    @Autowired
    private ComprobanteService comprobanteService;
    @Autowired
    private ClienteService clienteService;
    @Autowired
    private ProductoService productoService;

    //CREATE -------------------------
    //http://localhost:8080/comprobante
    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> crearComprobante(@RequestBody ComprobanteRequest comprobanteRecibido) {

            long clienteId = (long) comprobanteRecibido.getClienteId();
            Optional<Cliente> posibleCliente = clienteService.leerCliente(clienteId);
            if (!posibleCliente.isPresent()){
                return ResponseEntity.ok("El cliente que ingreso no existe");}

            for (int i = 0; i < comprobanteRecibido.getProductosComprobante().size(); i++) {
             Optional<Producto> posibleProducto = productoService.leerProducto((long) comprobanteRecibido.getProductosComprobante().get(i).getProductoId());
            if (!posibleProducto.isPresent()){
                return ResponseEntity.ok("El productoId: "+comprobanteRecibido.getProductosComprobante().get(i).getProductoId()+ " no existe");
            }
            if( posibleProducto.get().getCantidad()< comprobanteRecibido.getProductosComprobante().get(i).getCantidad()){
                return ResponseEntity.ok("El productoId: "+comprobanteRecibido.getProductosComprobante().get(i).getProductoId()+ " no tiene stock suficiente");
            }
            }
        return ResponseEntity.ok(comprobanteService.crearComprobante(comprobanteRecibido));
    }

    //READ -------------------------
    //http://localhost:8080/comprobante/1
    @GetMapping(value = "{id}")
    public ResponseEntity<?> obtenerComprobantePorID(@PathVariable(name = "id") final Long id) {
        Optional<Comprobante> posibleComprobante = comprobanteService.leerComprobante(id);
        if (posibleComprobante.isPresent()) {
            return ResponseEntity.ok(posibleComprobante);
        }else{return ResponseEntity.ok("Comprobante inexistente");}
    }

    //http://localhost:8080/comprobante/1
    @PutMapping(value = "{id}")
    public ResponseEntity<?> obtenerComprobantePorID(@PathVariable(name = "id") final Long id,
                                                 @RequestBody Comprobante comprobanteRecibido) {
        Optional<Comprobante> posibleComprobante = comprobanteService.leerComprobante(id);
        if (posibleComprobante.isPresent()) {
        Comprobante comprobanteBuscado = posibleComprobante.get();
        comprobanteBuscado.setFecha(comprobanteRecibido.getFecha());
        comprobanteBuscado.setCantidad(comprobanteRecibido.getCantidad());
        comprobanteBuscado.setTotal(comprobanteRecibido.getTotal());
        return ResponseEntity.ok(comprobanteService.guardarComprobante(comprobanteBuscado));
        }else{return ResponseEntity.ok("Comprobante Inexistente");}

    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<?> eliminarComprobantePorID(@PathVariable(name = "id") final Long id) {

        return ResponseEntity.ok(comprobanteService.borrarComprobante(id));
    }

}
