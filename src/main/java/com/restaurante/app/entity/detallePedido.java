package com.restaurante.app.entity;


import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "DetallesPedidos")
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
    Alimento alimentos;

    private int cantidadAlimento;

    private BigDecimal subtotal;

    public detallePedidoKey getId() {
        return id;
    }

    public void setId(detallePedidoKey id) {
        this.id = id;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public Alimento getAlimentos() {
        return alimentos;
    }

    public void setAlimentos(Alimento alimentos) {
        this.alimentos = alimentos;
    }

    public int getCantidadAlimento() {
        return cantidadAlimento;
    }

    public void setCantidadAlimento(int cantidadAlimento) {
        this.cantidadAlimento = cantidadAlimento;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }
}
