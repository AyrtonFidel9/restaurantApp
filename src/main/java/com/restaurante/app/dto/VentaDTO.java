package com.restaurante.app.dto;
import com.restaurante.app.entity.Calificacion;
import com.restaurante.app.entity.TipoPago;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class VentaDTO {
    private int idVenta;
    @NotNull(message = "El Usuario es requerido")
    private int idUsuario;
    @NotNull(message = "El Pedido es requerido")
    private int idPedido;
    private int idRestaurante;
    //@NotNull(message = "Debe ingresar el tipo de pago")
    private TipoPago formaDePago;
    //@NotNull(message = "El campo fecha es requerido")
    private LocalDate fecha;
    @DateTimeFormat(pattern = "HH:mm")
    //@NotNull(message = "El campo hora es requerido")
    private LocalTime hora;
    private Calificacion calificacion;
    private BigDecimal impuestos;
    @DecimalMin("0.00")
    private BigDecimal propina;
    private BigDecimal total;
}
