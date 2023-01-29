package com.example.FacturacionSegundaEntregaPitton.services;

import com.example.FacturacionSegundaEntregaPitton.entitys.*;
import com.example.FacturacionSegundaEntregaPitton.repositories.ClienteRepository;
import com.example.FacturacionSegundaEntregaPitton.repositories.ComprobanteRepository;
import com.example.FacturacionSegundaEntregaPitton.repositories.ProductoRepository;
import com.example.FacturacionSegundaEntregaPitton.request.ComprobanteRequest;
import com.example.FacturacionSegundaEntregaPitton.utils.Fecha;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ComprobanteService {
    @Autowired
    private ComprobanteRepository comprobanteRepository;
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private ProductoRepository productoRepository;

    public Comprobante crearComprobante(ComprobanteRequest comprobanteACrear) {

        Comprobante comprobanteAGuardar = new Comprobante();
        comprobanteAGuardar = comprobanteRepository.save(comprobanteAGuardar);

        float totalPrecioComprobante = 0;

        int cantidad = 0;
        List<Linea> lineas = new ArrayList<>();
        //Bucle para leer los productos recibidos que se agregaran al comprobante.

        for (int i = 0; i < comprobanteACrear.getProductosComprobante().size(); i++) {
            Producto productoRequest = comprobanteACrear.getProductosComprobante().get(i);

            cantidad = cantidad + productoRequest.getCantidad();
            float precioProducto = productoRepository.getReferenceById((long) productoRequest.getProductoId()).getPrecio();
            totalPrecioComprobante = totalPrecioComprobante + ( precioProducto* productoRequest.getCantidad());

            //restamos del stock los productos que se agregan a la factura
            Producto productoAModificar = productoRepository.getReferenceById(productoRequest.getProductoId());
            productoAModificar.setCantidad(productoAModificar.getCantidad() - productoRequest.getCantidad());

            Linea lineaProducto = new Linea("descripcion",
                                            productoRequest.getCantidad(),
                                            precioProducto,
                                            productoRepository.findById(productoRequest.getProductoId()).get(),
                                            comprobanteAGuardar.getComprobanteId()
                                            );
            lineas.add(lineaProducto);
        }

        String fecha = Fecha.getApi();

        //busca cliente en la base de datos al que se le creara el comprobante
        Cliente clienteId = clienteRepository.getReferenceById((long) comprobanteACrear.getClienteId());

//        Comprobante comprobanteAGuardar = new Comprobante(idGenerado,fecha, cantidad, totalPrecioComprobante, clienteId,lineas);
        //comprobanteAGuardar.setLinea();
        comprobanteAGuardar.setFecha(fecha);
        comprobanteAGuardar.setCantidad(cantidad);
        comprobanteAGuardar.setTotal(totalPrecioComprobante);
        comprobanteAGuardar.setClienteRelacion(clienteId);
        comprobanteAGuardar.setLineas(lineas);

        return comprobanteRepository.save(comprobanteAGuardar);
    }

    public Comprobante guardarComprobante(Comprobante comprobanteAGuardar) {
        return comprobanteRepository.save(comprobanteAGuardar);
    }

    public Optional leerComprobante(Long idComprobante) {

        return comprobanteRepository.findById(idComprobante);


    }

    public String borrarComprobante(Long idcomprobante) {
        comprobanteRepository.deleteById(idcomprobante);
        return "Eliminado";
    }

}
