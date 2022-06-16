package com.restaurante.app.dto;

import com.restaurante.app.entity.DetallePedidoKey;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class DetallePedidoDTO {
    private DetallePedidoKey id;

    private PedidoDTO pedido;

    private AlimentoDTO alimento;

    private int cantidadAlimento;

    private BigDecimal subTotal;
}
