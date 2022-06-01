package com.restaurante.app.persistence.models;


import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class detallePedido {
    @EmbeddedId
    detallePedidoKey id;

    @ManyToOne
    @MapsId("idPedido")
    @JoinColumn(name = "idPedido")
    Pedido pedido;

    @ManyToOne
    @MapsId("idAlimentos")
    @JoinColumn(name = "idAlimentos")
    Alimentos alimentos;

    private int cantidadAlimento;

    private BigDecimal subtotal;
}
