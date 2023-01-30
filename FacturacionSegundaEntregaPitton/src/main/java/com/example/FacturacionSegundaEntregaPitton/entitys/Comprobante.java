package com.example.FacturacionSegundaEntregaPitton.entitys;

import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "COMPROBANTE")
public class Comprobante {

    public Comprobante() {
    }

    public Comprobante(Long comprobanteId, String fecha, int cantidad, float total, Cliente clienteRelacion) {
        this.comprobanteId = comprobanteId;
        this.fecha = fecha;
        this.cantidad = cantidad;
        this.total = total;
        this.clienteRelacion = clienteRelacion;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ComprobanteId")
    private Long comprobanteId;
    @Column(name = "Fecha")
    private String fecha;

    @Column(name = "Cantidad")
    private int cantidad;
    @Column(name = "Total")
    private float total;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ClienteId")
    private Cliente clienteRelacion;
    @OneToMany(mappedBy = "comprobanteRelacion", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Linea> linea;

    public Long getComprobanteId() {
        return comprobanteId;
    }

    public void setComprobanteId(Long comprobanteId) {
        comprobanteId = comprobanteId;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public Cliente getClienteRelacion() {
        return clienteRelacion;
    }

    public void setClienteRelacion(Cliente clienteRelacion) {
        this.clienteRelacion = clienteRelacion;
    }

    public List<Linea> getLinea() {
        return linea;
    }

    public void setLineas(List<Linea> linea) {
        this.linea = linea;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comprobante that = (Comprobante) o;
        return comprobanteId == that.comprobanteId && cantidad == that.cantidad && Float.compare(that.total, total) == 0 && Objects.equals(fecha, that.fecha) && Objects.equals(clienteRelacion, that.clienteRelacion) && Objects.equals(linea, that.linea);
    }

    @Override
    public int hashCode() {
        return Objects.hash(comprobanteId, fecha, cantidad, total, clienteRelacion, linea);
    }

    @Override
    public String toString() {
        return "Comprobante{" +
                "comprobanteId=" + comprobanteId +
                ", fecha='" + fecha + '\'' +
                ", cantidad=" + cantidad +
                ", total=" + total +
                ", clienteRelacion=" + clienteRelacion +
                ", linea=" + linea +
                '}';
    }
}