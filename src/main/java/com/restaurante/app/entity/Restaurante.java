package com.restaurante.app.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Set;
import javax.persistence.*;

@Entity
@Table(name = "Restaurantes")
public class Restaurante {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    @Column(name="nombre", length=100, nullable=false)
    private String nombre;

    @Column(name="cantMesas", length=100, nullable=false)
    private int cantMesas;
    private String propietario;

    @OneToMany(mappedBy = "restaurante", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Mesa> mesas;

    @OneToMany(mappedBy = "restaurante", cascade = CascadeType.ALL)
    private Set<Usuario> usuarios;

    @OneToMany(mappedBy = "restaurante", cascade = CascadeType.ALL)
    private Set<Reserva> reservas;

    @OneToMany(mappedBy = "restaurante", cascade = CascadeType.ALL)
    private Set<Menu> menus;

    @OneToMany(mappedBy = "restaurante", cascade = CascadeType.ALL)
    private Set<Venta> ventas;

    @OneToMany(mappedBy = "restaurante", cascade = CascadeType.ALL)
    private Set<Pedido> pedidos;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCantMesas() {
        return cantMesas;
    }

    public void setCantMesas(int cantMesas) {
        this.cantMesas = cantMesas;
    }

    public String getPropietario() {
        return propietario;
    }

    public void setPropietario(String propietario) {
        this.propietario = propietario;
    }

    public Set<Mesa> getMesas() {
        return mesas;
    }

    public void setMesas(Set<Mesa> mesas) {
        this.mesas = mesas;
    }

    public Set<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(Set<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public Set<Reserva> getReservas() {
        return reservas;
    }

    public void setReservas(Set<Reserva> reservas) {
        this.reservas = reservas;
    }

    public Set<Menu> getMenus() {
        return menus;
    }

    public void setMenus(Set<Menu> menus) {
        this.menus = menus;
    }

    public Set<Venta> getVentas() {
        return ventas;
    }

    public void setVentas(Set<Venta> ventas) {
        this.ventas = ventas;
    }

    public Set<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(Set<Pedido> pedidos) {
        this.pedidos = pedidos;
    }
}
