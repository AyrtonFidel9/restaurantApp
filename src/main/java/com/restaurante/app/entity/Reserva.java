package com.restaurante.app.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;

@Entity
@Table(name = "Reservas")
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

    private LocalDate fecha;

    private LocalTime hora;

    @OneToMany(mappedBy = "reserva")
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

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    public Set<ReservaMesa> getReservaMesas() {
        return reservaMesas;
    }

    public void setReservaMesas(Set<ReservaMesa> reservaMesas) {
        this.reservaMesas = reservaMesas;
    }
}
