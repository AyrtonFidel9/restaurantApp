package com.restaurante.app.persistence.models;

import javax.persistence.*;

enum Tipo{
    individual,
    pareja,
    familiar
}

@Entity
public class Mesas {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int idMesa;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="idRestaurante", referencedColumnName = "id")
    private Restaurante restaurante;

    private int capacidad;

    private boolean estado;
    @Enumerated(EnumType.STRING)
    private Tipo tipo;

}
