package com.restaurante.app.services;

import com.restaurante.app.dto.VentaDTO;
import com.restaurante.app.entity.*;
import com.restaurante.app.exceptions.ResourceNotFoundException;
import com.restaurante.app.exceptions.RestauranteAppException;
import com.restaurante.app.mapper.iVentaMapper;
import com.restaurante.app.repository.*;
import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
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
    private iDetallePedidoRepository detallePedidoRepository;
    @Autowired
    private iVentaMapper mapper;

    @Override
    public VentaDTO ingresarVenta(VentaDTO ventaDTO)
    {
        ventaDTO.setIdRestaurante(1);
        ventaDTO.setImpuestos(BigDecimal.valueOf(0.12));
        ventaDTO.setFecha(LocalDate.now());
        ventaDTO.setHora(LocalTime.now());
        int idRes = ventaDTO.getIdRestaurante();
        int idUser = ventaDTO.getIdUsuario();
        int idP = ventaDTO.getIdPedido();
        Venta venta = mapper.toVenta(ventaDTO);

        Usuario user = usuarioRepository
                .findById(idUser)
                .orElseThrow(()->
                        new ResourceNotFoundException("Usuario","id",idUser));
        if (user.getRol() != Rol.ROLE_CAJERO){
            throw new RestauranteAppException(HttpStatus.BAD_REQUEST,
                    "El usuario: "+user.getNombre()+" no esta autorizado para realizar esta accion");
        }
        venta.setUsuario(user);

        Pedido pedido = pedidoRepository.findById(idP).orElseThrow(()->
                new RuntimeException("Pedido no encontrado"));
        if(pedido.getEstadoPedido() != EstadoPedido.entregado) {
            throw new RestauranteAppException(HttpStatus.BAD_REQUEST,"El pedido: "+ pedido.getId() +" se encuentra " +
                    pedido.getEstadoPedido() + " y necesita ser entregado");
        }
        venta.setPedido(pedido);

        Restaurante res = restauranteRepository.findById(idRes).
                orElseThrow(()->
                        new RuntimeException("Restaurante no encontrado"));
        venta.setRestaurante(res);

        if(ventaDTO.getHora().isBefore(res.getHoraApertura()) ||
                ventaDTO.getHora().isAfter(res.getHoraApertura())){
            throw new RestauranteAppException(HttpStatus.BAD_REQUEST,
                    "La hora "+ ventaDTO.getHora() +
                            " esta fuera de las horas laborables del restaurante");
        }



        venta.setTotal(obtenerTotal(ventaDTO));
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
        if(ventaRepository.existsById(idVenta))
        {
            ventaDTO.setIdUsuario(idVenta);
            return ingresarVenta(ventaDTO);
        }
        else{
            throw new ResourceNotFoundException("Usuario","id",idVenta);
        }
        /*
        Venta vantigua = ventaRepository.findById(idVenta)
                .orElseThrow(()-> new ResourceNotFoundException("Venta","id",ventaDTO.getIdVenta()));
        vantigua.setId(idVenta);
        ingresarVenta(mapper.toVentaDTO(vantigua));
        return null;

        Venta venta = mapper.toVenta(buscarVenta(idVenta));
        venta.setFormaDePago(ventaDTO.getFormaDePago());
        venta.setFecha(ventaDTO.getFecha());
        venta.setHora(ventaDTO.getHora());
        venta.setCalificacion(ventaDTO.getCalificacion());
        venta.setPropina(ventaDTO.getPropina());
        venta.setTotal(ventaDTO.getTotal());
        ventaRepository.save(venta);
        return mapper.toVentaDTO(venta);
        */
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

        BigDecimal impuesto = BigDecimal.valueOf(1+ventaDTO.getImpuestos().floatValue());
        System.out.println("impuesto ->>> "+impuesto);
        System.out.println("ID PEDIDO _>>>> "+ventaDTO.getIdPedido());
        System.out.println("subtotal suma ->>> "+detallePedidoRepository.sumSubTotalDetallePedidoByIdPedido(ventaDTO.getIdPedido()));
        BigDecimal total = detallePedidoRepository.sumSubTotalDetallePedidoByIdPedido(ventaDTO.getIdPedido()).multiply(impuesto);
        System.out.println(total);
        return total;
        /*
        iDetallePedidoRepository detallePedidoRepository;
        detallePedidoRepository.sumSubTotalDetallePedidoByIdPedido(ventaDTO.getIdPedido());
        ventaDTO.setTotal(detallePedidoRepository*ventaDTO.getImpuestos());*/
    }

}
