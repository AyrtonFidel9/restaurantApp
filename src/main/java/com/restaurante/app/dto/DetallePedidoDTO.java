package com.restaurante.app.dto;

import com.restaurante.app.entity.DetallePedidoKey;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class DetallePedidoDTO {
    private int idPedido;

    private int idAlimento;

    private int cantidadAlimento;

    private BigDecimal subTotal;
}
