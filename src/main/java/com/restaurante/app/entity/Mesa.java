package com.restaurante.app.entity;

import javax.persistence.*;
import java.util.Set;

enum TipoMesas{
    individual,
    pareja,
    familiar
}

@Entity
@Table(name = "Mesas")
public class Mesa {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="idRestaurante", referencedColumnName = "id")
    private Restaurante restaurante;

    private int capacidad;

    private boolean estado;

    @Enumerated(EnumType.STRING)
    private TipoMesas tipo;

    @OneToOne(mappedBy = "mesas",cascade = CascadeType.ALL)
    private Pedido pedido;

    @OneToMany(mappedBy = "mesas")
    private Set<ReservaMesa> reservaMesas;

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

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public TipoMesas getTipo() {
        return tipo;
    }

    public void setTipo(TipoMesas tipo) {
        this.tipo = tipo;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public Set<ReservaMesa> getReservaMesas() {
        return reservaMesas;
    }

    public void setReservaMesas(Set<ReservaMesa> reservaMesas) {
        this.reservaMesas = reservaMesas;
    }
}
