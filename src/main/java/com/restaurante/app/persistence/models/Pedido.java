package com.restaurante.app.persistence.models;


import javax.persistence.*;
import java.util.Set;

@Entity
public class Pedido {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="idRestaurante", referencedColumnName = "id")
    private Restaurante restaurante;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="idMesa", referencedColumnName = "id")
    private Mesas mesas;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="idUsuario", referencedColumnName = "id")
    private Usuario usuario;

    @OneToOne(mappedBy = "pedido", cascade = CascadeType.ALL)
    private Ventas ventas;

    @OneToMany(mappedBy = "pedido")
    private Set<detallePedido> detallePedidos;



}
