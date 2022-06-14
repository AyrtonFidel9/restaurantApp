package com.restaurante.app.entity;


import javax.persistence.*;
import java.util.Set;

public enum EstadoPedido{
    pendiente,
    anulado,
    entregado
}

@Entity
@Table(name = "Pedidos")
public class Pedido {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="idRestaurante", referencedColumnName = "id")
    private Restaurante restaurante;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="idMesa", referencedColumnName = "id")
    private Mesa mesas;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="idUsuario", referencedColumnName = "id")
    private Usuario usuario;

    @OneToOne(mappedBy = "pedido", cascade = CascadeType.ALL)
    private Venta ventas;

    @OneToMany(mappedBy = "pedido")
    private Set<DetallePedido> DetallePedidos;

    @Enumerated(EnumType.STRING)
    private EstadoPedido estadoPedido;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Restaurante getRestaurante() {
        return restaurante;
    }

    public void setRestaurante(Restaurante restaurante) {
        this.restaurante = restaurante;
    }

    public Mesa getMesas() {
        return mesas;
    }

    public void setMesas(Mesa mesas) {
        this.mesas = mesas;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Venta getVentas() {
        return ventas;
    }

    public void setVentas(Venta ventas) {
        this.ventas = ventas;
    }

    public Set<DetallePedido> getDetallePedidos() {
        return DetallePedidos;
    }

    public void setDetallePedidos(Set<DetallePedido> DetallePedidos) {
        this.DetallePedidos = DetallePedidos;
    }

    public EstadoPedido getEstadoPedido() {
        return estadoPedido;
    }

    public void setEstadoPedido(EstadoPedido estadoPedido) {
        this.estadoPedido = estadoPedido;
    }
}
