package com.restaurante.app.persistence.models;

import javax.persistence.*;
import java.util.Set;

enum Rol {
    mesero,
    cliente,
    administrador,
    cocinero,
    cajero
}
@Entity
public class Usuario {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="idRestaurante", referencedColumnName = "id")
    private Restaurante restaurante;

    private String nombre;

    private String apellido;

    private String cedula;

    private String email;

    private String password;

    @Enumerated(EnumType.STRING)
    private Rol rol;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private Set<Reserva> reservas;
}
