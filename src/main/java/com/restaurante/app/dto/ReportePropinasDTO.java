package com.restaurante.app.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.sql.Date;
@Data
public class ReportePropinasDTO implements java.io.Serializable{
    private int idUsuario;
    private String nombre;
    private String apellido;
    private BigDecimal propina;
    private Date fecha;
}
