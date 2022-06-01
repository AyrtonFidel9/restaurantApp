package com.restaurante.app.persistence.models;

import javax.persistence.*;

enum Tipo {
    bebidas,
    postres,
    primeros,
    segundos,
    ensaladas,
    sopas
}

@Entity
public class Menu {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="idRestaurante", referencedColumnName = "id")
    private Restaurante restaurante;

    private String nombre;

    @Enumerated(EnumType.STRING)
    private Tipo tipo;
}
