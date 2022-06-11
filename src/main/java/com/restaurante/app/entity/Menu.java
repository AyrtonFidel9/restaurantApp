package com.restaurante.app.entity;

import javax.persistence.*;
import java.util.Set;

enum TipoMenu {
    bebidas,
    postres,
    primeros,
    segundos,
    ensaladas,
    sopas
}

@Entity
@Table(name = "Menus")
public class Menu {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="idRestaurante", referencedColumnName = "id")
    private Restaurante restaurante;

    private String nombre;

    @Enumerated(EnumType.STRING)
    private TipoMenu tipo;

    @OneToMany(mappedBy = "menu",cascade = CascadeType.ALL)
    private Set<Alimento> alimentos;

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

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public TipoMenu getTipo() {
        return tipo;
    }

    public void setTipo(TipoMenu tipo) {
        this.tipo = tipo;
    }

    public Set<Alimento> getAlimentos() {
        return alimentos;
    }

    public void setAlimentos(Set<Alimento> alimentos) {
        this.alimentos = alimentos;
    }
}
