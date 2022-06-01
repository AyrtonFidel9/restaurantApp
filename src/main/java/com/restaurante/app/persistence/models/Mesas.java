package com.restaurante.app.persistence.models;

import javax.persistence.*;
import java.util.Set;

enum TipoMesas{
    individual,
    pareja,
    familiar
}

@Entity
public class Mesas {
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

    @ManyToMany(mappedBy = "mesas")
    private Set<Reserva> reservas;

    @OneToOne(mappedBy = "mesas",cascade = CascadeType.ALL)
    private Pedido pedido;

    @OneToMany(mappedBy = "mesas")
    private Set<ReservaMesa> reservaMesas;
}
