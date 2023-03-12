package com.example.FacturacionSegundaEntregaPitton.entitys;

import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "PRODUCTO")
public class Producto {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ProductoId")
    private Long productoId;
    @Column(name = "Codigo")
    private int codigo;
    @Column(name = "Descripcion")
    private String descripcion;
    @Column(name = "Cantidad")
    private int cantidad;
    @Column(name = "Precio")
    private float precio;

    @OneToMany(mappedBy = "productoRelacion", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Linea> linea;

    public Producto() {
    }

    public Producto(Long productoId, int cantidad) {
        this.productoId = productoId;
        this.cantidad = cantidad;
    }

    public Producto(int codigo, String descripcion, int cantidad, float precio) {
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.cantidad = cantidad;
        this.precio = precio;
    }

    public Producto(Long productoId, int codigo, String descripcion, int cantidad, float precio) {
        this.productoId = productoId;
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.cantidad = cantidad;
        this.precio = precio;

    }
    public Long getProductoId() {
        return productoId;
    }

    public void setProductoId(Long productoId) {
        this.productoId = productoId;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Producto producto = (Producto) o;
        return productoId == producto.productoId && codigo == producto.codigo && cantidad == producto.cantidad && Float.compare(producto.precio, precio) == 0 && Objects.equals(descripcion, producto.descripcion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productoId, codigo, descripcion, cantidad, precio);
    }

    @Override
    public String toString() {
        return "Producto{" +
                "productoId=" + productoId +
                ", codigo=" + codigo +
                ", descripcion='" + descripcion + '\'' +
                ", cantidad=" + cantidad +
                ", precio=" + precio +
                '}';
    }
}
