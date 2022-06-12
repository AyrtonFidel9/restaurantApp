package com.restaurante.app.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class detallePedidoKey implements Serializable {

    @Column(name = "idPedido")
    private int idPedido;
    @Column(name = "idAlimentos")
    private int idAlimentos;

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public int getIdAlimentos() {
        return idAlimentos;
    }

    public void setIdAlimentos(int idAlimentos) {
        this.idAlimentos = idAlimentos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof detallePedidoKey)) return false;
        detallePedidoKey that = (detallePedidoKey) o;
        return getIdPedido() == that.getIdPedido() && getIdAlimentos() == that.getIdAlimentos();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIdPedido(), getIdAlimentos());
    }
}
