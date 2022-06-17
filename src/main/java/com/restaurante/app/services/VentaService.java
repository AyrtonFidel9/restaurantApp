package com.restaurante.app.services;

import com.restaurante.app.dto.UsuarioDTO;
import com.restaurante.app.dto.VentaDTO;
import com.restaurante.app.entity.Pedido;
import com.restaurante.app.entity.Restaurante;
import com.restaurante.app.entity.Usuario;
import com.restaurante.app.entity.Venta;
import com.restaurante.app.exceptions.ResourceNotFoundException;
import com.restaurante.app.mapper.iVentaMapper;
import com.restaurante.app.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class VentaService implements iVentaService {
    @Autowired
    private iVentaRepository ventaRepository;
    @Autowired
    private iUsuarioRepository usuarioRepository;
    @Autowired
    private iPedidoRepository pedidoRepository;
    @Autowired
    private iRestauranteRepository restauranteRepository;
    @Autowired
    private iVentaMapper mapper;

    @Override
    public VentaDTO ingresarVenta(VentaDTO ventaDTO)
    {
        ventaDTO.setIdRestaurante(1);
        int idRes = ventaDTO.getIdRestaurante();
        int idUser = ventaDTO.getIdUsuario();
        int idP = ventaDTO.getIdPedido();
        Venta venta = mapper.toVenta(ventaDTO);

        Usuario user = usuarioRepository.findById(idUser).
                orElseThrow(()->
                        new RuntimeException("Usuario no encontrado"));
        venta.setUsuario(user);

        Pedido pedido = pedidoRepository.findById(idP).orElseThrow(()->
                new RuntimeException("Pedido no encontrado"));
        venta.setPedido(pedido);

        Restaurante res = restauranteRepository.findById(idRes).
                orElseThrow(()->
                        new RuntimeException("Restaurante no encontrado"));
        venta.setRestaurante(res);

        Venta igventa = ventaRepository.save(venta);
        return mapper.toVentaDTO(igventa);
    }

    @Override
    public VentaDTO buscarVenta(int idVenta){
        Optional<Venta> ventaresult = ventaRepository.findById(idVenta);
        return mapper.toVentaDTO(ventaresult.orElseThrow(()->
                new ResourceNotFoundException("Venta","id",idVenta)));
    }

    @Override
    public VentaDTO actualizarVenta(int idVenta, VentaDTO ventaDTO)
    {
        Venta venta = mapper.toVenta(buscarVenta(idVenta));
        venta.setFormaDePago(ventaDTO.getFormaDePago());
        venta.setFecha(ventaDTO.getFecha());
        venta.setCalificacion(ventaDTO.getCalificacion());
        venta.setPropina(ventaDTO.getPropina());
        venta.setTotal(ventaDTO.getTotal());
        ventaRepository.save(venta);
        return mapper.toVentaDTO(venta);
    }

    @Override
    public void eliminarVenta(int idVenta)
    {
        ventaRepository.deleteById(idVenta);
    }

    @Override
    public List<VentaDTO> obtenerVentas(){
        return mapper.toVentaDTO((List<Venta>)ventaRepository.findAll());
    }

    @Override
    public BigDecimal obtenerTotal(VentaDTO ventaDTO)
    {
        iDetallePedidoRepository detallePedidoRepository = null;
        BigDecimal impuesto = BigDecimal.valueOf(1).add(ventaDTO.getImpuestos());
        BigDecimal total = detallePedidoRepository.sumSubTotalDetallePedidoByIdPedido(ventaDTO.getIdPedido()).multiply(impuesto);
        return total;
        /*
        iDetallePedidoRepository detallePedidoRepository;
        detallePedidoRepository.sumSubTotalDetallePedidoByIdPedido(ventaDTO.getIdPedido());
        ventaDTO.setTotal(detallePedidoRepository*ventaDTO.getImpuestos());*/
    }

}
