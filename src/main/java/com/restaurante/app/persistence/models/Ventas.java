package com.restaurante.app.persistence.models;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Set;

enum TipoPago{
    efectivo,
    debito,
    credito,
    transferencia
}

enum Calificacion{
    excelente,
    muy_bueno,
    bueno,
    malo,
    muy_malo
}

@Entity
public class Ventas {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="idUsuario", referencedColumnName = "id")
    private Usuario usuario;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="idPedido", referencedColumnName = "id")
    private Pedido pedido;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="idRestaurante", referencedColumnName = "id")
    private Restaurante restaurante;

    @Enumerated(EnumType.STRING)
    private TipoPago formaDePago;

    private Date fecha;

    @Enumerated(EnumType.STRING)
    private Calificacion calificacion;

    private BigDecimal impuestos;

    private BigDecimal propina;

    private BigDecimal total;

}
