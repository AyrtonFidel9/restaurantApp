package com.restaurante.app.dto;
import com.restaurante.app.entity.TipoMesas;


public class MesaDTO {
    private int idMesa;
    private int idRestaurante;
    private int capacidad;
    private com.restaurante.app.entity.TipoMesas tipo;
    private boolean estado;

    private String nombre;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getIdMesa() {
        return idMesa;
    }

    public void setIdMesa(int idMesa) {
        this.idMesa = idMesa;
    }

    public int getIdRestaurante() {
        return idRestaurante;
    }

    public void setIdRestaurante(int idRestaurante) {
        this.idRestaurante = idRestaurante;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public TipoMesas getTipo() {
        return tipo;
    }

    public void setTipo(TipoMesas tipo) {
        this.tipo = tipo;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }
}
