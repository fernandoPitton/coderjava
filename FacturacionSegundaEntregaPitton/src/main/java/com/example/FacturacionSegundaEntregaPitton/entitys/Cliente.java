package com.example.FacturacionSegundaEntregaPitton.entitys;


import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "CLIENTE")
public class Cliente {
    public Cliente() {
    }

    public Cliente(String nombre, String apellido, int dni) {

        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
    }

    public Cliente(int clienteId, String nombre, String apellido, int dni) {
        this.clienteId = clienteId;
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;

    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ClienteId")
    private int clienteId;

    @Column(name = "Nombre")
    private String nombre;
    @Column(name = "Apellido")
    private String apellido;
    @Column(name = "DNI")
    private int dni;


    @OneToMany(mappedBy = "clienteRelacion", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Comprobante> comprobante;

    public int getClienteId() {
        return clienteId;
    }

    public void setClienteId(int clienteId) {
        this.clienteId = clienteId;
    }

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cliente cliente = (Cliente) o;
        return clienteId == cliente.clienteId && dni == cliente.dni && Objects.equals(nombre, cliente.nombre) && Objects.equals(apellido, cliente.apellido);
    }

    @Override
    public int hashCode() {
        return Objects.hash(clienteId, dni, nombre, apellido);
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "clienteId=" + clienteId +
                ", dni=" + dni +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                '}';
    }
}