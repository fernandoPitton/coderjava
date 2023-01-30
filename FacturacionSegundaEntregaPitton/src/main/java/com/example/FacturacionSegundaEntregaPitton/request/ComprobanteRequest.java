package com.example.FacturacionSegundaEntregaPitton.request;

import com.example.FacturacionSegundaEntregaPitton.entitys.Producto;

import java.util.List;
import java.util.Objects;

public class ComprobanteRequest {

    private int clienteId;

    private List<Producto> productosComprobante;

    public ComprobanteRequest() {
    }

    public ComprobanteRequest(int clienteId, List<Producto> productosComprobante) {
        this.clienteId = clienteId;
        this.productosComprobante = productosComprobante;
    }

    public int getClienteId() {
        return clienteId;
    }

    public void setClienteId(int clienteId) {
        this.clienteId = clienteId;
    }

    public List<Producto> getProductosComprobante() {
        return productosComprobante;
    }

    public void setProductosComprobante(List<Producto> productosComprobante) {
        this.productosComprobante = productosComprobante;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ComprobanteRequest that = (ComprobanteRequest) o;
        return clienteId == that.clienteId && Objects.equals(productosComprobante, that.productosComprobante);
    }

    @Override
    public int hashCode() {
        return Objects.hash(clienteId, productosComprobante);
    }

    @Override
    public String toString() {
        return "ComprobanteRequest{" +
                "clienteId=" + clienteId +
                ", productosComprobante=" + productosComprobante +
                '}';
    }
}
