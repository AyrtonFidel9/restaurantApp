package com.restaurante.app.persistence.models;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;
import java.util.Set;

@Entity
public class Reserva {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="idRestaurante", referencedColumnName = "id")
    private Restaurante restaurante;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="idUsuario", referencedColumnName = "id")
    private Usuario usuario;

    private Date fecha;

    private Time hora;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "ReservasMesas",
            joinColumns = @JoinColumn(name = "idReserva"),
            inverseJoinColumns = @JoinColumn(name = "idMesa")
    )
    private Set<Mesas> mesas;
}
