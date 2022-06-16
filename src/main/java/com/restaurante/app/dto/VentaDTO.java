package com.restaurante.app.dto;
import com.restaurante.app.entity.Calificacion;
import com.restaurante.app.entity.TipoPago;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class VentaDTO {
    private int idVenta;
    private int idUsuario;
    private int idPedido;
    private int idRestaurante;
    private TipoPago formaDePago;
    private LocalDate fecha;
    private Calificacion calificacion;
    private BigDecimal impuestos;
    private BigDecimal propina;
    private BigDecimal total;
}
