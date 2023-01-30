package com.example.FacturacionSegundaEntregaPitton.entitys;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "LINEA")
public class Linea {
    public Linea() {
    }


    public Linea(String descripcion, int cantidad, float precio, Producto productoRelacion, Comprobante comprobanteRelacion) {
        this.descripcion = descripcion;
        this.cantidad = cantidad;
        this.precio = precio;
        this.comprobanteRelacion = comprobanteRelacion;
        this.productoRelacion = productoRelacion;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "LineaId")
    private int lineaId;
    @Column(name = "Descripcion")
    private String descripcion;

    @Column(name = "Cantidad")
    private int cantidad;
    @Column(name = "Precio")
    private float precio;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ComprobanteId")
    private Comprobante comprobanteRelacion;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ProductoId")
    private Producto productoRelacion;

    public int getLineaId() {
        return lineaId;
    }

    public void setLineaId(int lineaId) {
        this.lineaId = lineaId;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public Comprobante getComprobanteRelacion() {
        return comprobanteRelacion;
    }

    public void setComprobanteRelacion(Comprobante comprobanteRelacion) {
        this.comprobanteRelacion = comprobanteRelacion;
    }

    public Producto getProductoRelacion() {
        return productoRelacion;
    }

    public void setProductoRelacion(Producto productoRelacion) {
        this.productoRelacion = productoRelacion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Linea linea = (Linea) o;
        return lineaId == linea.lineaId && cantidad == linea.cantidad && Float.compare(linea.precio, precio) == 0 && Objects.equals(descripcion, linea.descripcion) && Objects.equals(comprobanteRelacion, linea.comprobanteRelacion) && Objects.equals(productoRelacion, linea.productoRelacion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lineaId, descripcion, cantidad, precio, comprobanteRelacion, productoRelacion);
    }

    @Override
    public String toString() {
        return "Linea{" +
                "lineaId=" + lineaId +
                ", descripcion='" + descripcion + '\'' +
                ", cantidad=" + cantidad +
                ", precio=" + precio +
                ", comprobanteRelacion=" + comprobanteRelacion +
                ", productoRelacion=" + productoRelacion +
                '}';
    }
}