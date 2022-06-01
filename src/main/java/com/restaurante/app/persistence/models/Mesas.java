package com.restaurante.app.persistence.models;

import javax.persistence.*;
import java.util.Set;

enum Tipo{
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
    private Tipo tipo;

    @ManyToMany(mappedBy = "mesas")
    private Set<Reserva> reservas;
}
