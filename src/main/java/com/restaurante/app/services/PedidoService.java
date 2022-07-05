package com.restaurante.app.services;

import com.restaurante.app.dto.PedidoDTO;
import com.restaurante.app.entity.*;
import com.restaurante.app.exceptions.ResourceNotFoundException;
import com.restaurante.app.exceptions.RestauranteAppException;
import com.restaurante.app.repository.*;
import com.restaurante.app.mapper.iPedidoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PedidoService implements iPedidoService{

    @Autowired
    private iRestauranteRepository restauranteRepository;

    @Autowired
    private iMesaRepository mesaRepository;

    @Autowired
    private iUsuarioRepository usuarioRepository;

    @Autowired
    private iAlimentoRepository alimentoRepository;

    @Autowired
    private iPedidoRepository pedidoRepository;

    @Autowired
    private iDetallePedidoRepository detallePedidoRepository;

    @Autowired
    private iPedidoMapper mapper;

    @Override
    public PedidoDTO ingresarPedido(PedidoDTO pedidoDTO) {

        Set<DetallePedido> detallePedidos = null;
        
        pedidoDTO.setIdRestaurante(1);

        pedidoDTO.setFecha(LocalDate.now());
        pedidoDTO.setHora(LocalTime.now());

        int idRes = pedidoDTO.getIdRestaurante();

        int idUser = pedidoDTO.getIdUsuario();
        Pedido pedido= mapper.toPedido(pedidoDTO);
        
        if (pedidoDTO.getIdPedido() != 0) {
            if (pedidoDTO.getDetallePedidos() != null) {
                detallePedidoRepository.eliminar(pedidoDTO.getIdPedido());
            } else {
                detallePedidos = detallePedidoRepository.pedidosbyid(pedidoDTO.getIdPedido());
            }
        }
        
        
        // buscar y guardar el objeto restaurante
        Restaurante res = restauranteRepository
                .findById(idRes)
                .orElseThrow(()->
                        new RuntimeException("Restaurante no encontrado"));
        pedido.setRestaurante(res);

        if((pedidoDTO.getHora().isBefore(res.getHoraApertura()) ||
                pedidoDTO.getHora().isAfter(res.getHoraCierre()))){
            throw new RestauranteAppException(HttpStatus.BAD_REQUEST,
                    "La hora "+ pedidoDTO.getHora() +
                            " esta fuera de las horas laborables del restaurante");
        }

        // buscar y gurdar el objeto usuario
        Usuario user = usuarioRepository
                .findById(idUser)
                .orElseThrow(()->
                        new ResourceNotFoundException("Usuario","id",idUser));

        if (user.getRol() != Rol.ROLE_MESERO){
            throw new RestauranteAppException(HttpStatus.BAD_REQUEST,
                    "El usuario: "+user.getNombre()+" no esta autorizado para realizar esta accion");
        }
        pedido.setUsuario(user);

        // buscar y guardar el objeto mesa
        Mesa mesa = mesaRepository
                .findById(pedidoDTO.getIdMesa())
                .orElseThrow(()->
                        new ResourceNotFoundException("Mesa","id",pedidoDTO.getIdMesa()));
        pedido.setMesas(mesa);


        if (pedidoDTO.getDetallePedidos() != null){
            // Ingresar los detalles que forman parte del pedido
            Set<DetallePedido> listDetallePedido = Collections.EMPTY_SET;
            listDetallePedido = pedidoDTO
                    .getDetallePedidos()
                    .stream()
                    .map(detallePedido -> {

                        Alimento alimento = alimentoRepository.findById(detallePedido.getIdAlimento())
                                .orElseThrow(() -> new ResourceNotFoundException("Alimento","id",detallePedido.getIdAlimento()));

                        DetallePedido detallarPedido = new DetallePedido();
                        detallarPedido.setAlimentos(alimento);
                        detallarPedido.setPedido(pedido);
                        //System.out.println(detallePedido.getCantidadAlimento());

                        if (detallePedido.getCantidadAlimento() < 1){
                            throw new RestauranteAppException(HttpStatus.BAD_REQUEST, "La cantidad de alimentos debe ser al menos 1");
                        }

                        detallarPedido.setCantidadAlimento(detallePedido.getCantidadAlimento());
                        detallarPedido.setSubtotal(
                                BigDecimal.valueOf(alimento.getPrecio() * detallarPedido.getCantidadAlimento())
                        );
                        return detallarPedido;
                    }).collect(Collectors.toSet());
            pedido.setDetallePedidos(listDetallePedido);
        }else {
            pedido.setDetallePedidos(detallePedidos);
        }

        pedidoRepository.save(pedido);
        return mapper.toPedidoDTO(pedido);
    }

    @Override
    public void eliminarPedido(int idPedido) {
        pedidoRepository.deleteById(idPedido);
    }

    @Override
    public PedidoDTO buscarPedido(int idPedido) {
        return mapper.toPedidoDTO(pedidoRepository.findById(idPedido)
                .orElseThrow(()-> new RuntimeException("Pedido no encontrada")));
    }

    @Override
    public List<PedidoDTO> obtenerPedido() {
        return mapper.toPedidosDTO((List<Pedido>)pedidoRepository.findAll());
    }

    @Override
    public PedidoDTO actualizarPedido(int idPedido, PedidoDTO pedidoDTO) {
        if (pedidoRepository.existsById(idPedido)){
            pedidoDTO.setIdPedido(idPedido);
            return ingresarPedido(pedidoDTO);
        }else
            throw new ResourceNotFoundException("Pedido" , "id", idPedido);
    }

}
