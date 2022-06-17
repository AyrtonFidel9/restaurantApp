package com.restaurante.app.services;

import com.restaurante.app.dto.DetallePedidoDTO;
import com.restaurante.app.dto.VentaDTO;
import com.restaurante.app.entity.Venta;

import java.math.BigDecimal;
import java.util.List;

public interface iVentaService {
    VentaDTO ingresarVenta(VentaDTO ventaDTO);
    VentaDTO buscarVenta(int idVenta);
    VentaDTO actualizarVenta(int idVenta, VentaDTO ventaDTO);
    void eliminarVenta(int idVenta);
    List<VentaDTO> obtenerVentas();
    BigDecimal obtenerTotal(VentaDTO ventaDTO);

}
