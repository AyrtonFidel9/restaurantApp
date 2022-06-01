package com.restaurante.app.persistence.models;
import javax.persistence.*;
import java.util.Set;

@Entity
public class Alimentos {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="idMenu", referencedColumnName = "id")
    private Menu menu;

    @Lob()
    private byte[] imagen;

    private String nombre;

    private String descripcion;

    private float precio;

    private boolean disponibilidad;

    @Enumerated(EnumType.STRING)
    private TipoMenu tipo;

    @OneToMany(mappedBy = "alimentos")
    private Set<detallePedido> detallePedidos;

}
