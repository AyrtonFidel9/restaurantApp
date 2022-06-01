package com.restaurante.app.persistence.models;

import javax.persistence.*;
import java.io.Serializable;

@Embeddable
public class detallePedidoKey implements Serializable {

    @Column(name = "idPedido")
    private int idPedido;
    @Column(name = "idAlimentos")
    private int idAlimentos;

}
