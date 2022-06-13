package com.restaurante.app.services;

import com.restaurante.app.dto.ReservaDTO;
import com.restaurante.app.entity.*;
import com.restaurante.app.repository.iReservaRepository;
import com.restaurante.app.mapper.iReservaMapper;
import com.restaurante.app.repository.iRestauranteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.restaurante.app.mapper.iMesaMapper;
import com.restaurante.app.mapper.iUsuarioMapper;
import com.restaurante.app.repository.iUsuarioRepository;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ReservaService implements iReservaService{
    @Autowired
    private iReservaRepository reservaRepository;

    @Autowired
    private iRestauranteRepository restauranteRepository;

    @Autowired
    private iUsuarioRepository usuarioRepository;

    @Autowired
    private MesaService mesaService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private iReservaMapper mapper;

    @Autowired
    private iMesaMapper mesaMapper;
    @Autowired
    private iUsuarioMapper usuarioMapper;

    @Override
    public ReservaDTO ingresarReserva(ReservaDTO reservaDTO) {

        reservaDTO.setIdRestaurante(1);
        //capturar el id del restaurante
        int idRest = reservaDTO.getIdRestaurante();
        //capturar el id del usuario
        int idUsu = reservaDTO.getIdUsuario();

        Reserva reserva = mapper.toReserva(reservaDTO);

        //buscar y guardar el objeto restaurante
        Restaurante res = restauranteRepository.findById(idRest)
                .orElseThrow(()->new RuntimeException("Restaurante no encontrado"));
        reserva.setRestaurante(res);

        //buscar y guardar el objeto usuario
        //1era forma
        Usuario usuarioRes = usuarioRepository.findById(idUsu)
                .orElseThrow(()->new RuntimeException("Usuario no encontrado"));
        //2da forma
        /*Usuario usuarioRes = usuarioMapper
                .toUsuario(usuarioService.buscarUsuario(idUsu));*/
        reserva.setUsuario(usuarioRes);

        //Ingresar las mesas que forman parte de la reserva
        Set<ReservaMesa> listReservaMesa = Collections.EMPTY_SET;
        listReservaMesa = reservaDTO
                .getReservaMesas()
                .stream()
                .map(reservaMesa -> {
                    System.out.println(reservaMesa.getId().getIdMesa());
                    Mesa mesa =  mesaMapper.toMesa(
                            mesaService
                                    .buscarMesa(reservaMesa.getId().getIdMesa()));

                    ReservaMesa reserva_mesa = new ReservaMesa();
                    reserva_mesa.setMesas(mesa);
                    reserva_mesa.setReserva(reservaMesa.getReserva());

                    return reserva_mesa;
                }).collect(Collectors.toSet());
        reserva.setReservaMesas(listReservaMesa);

        Reserva ingReserva = reservaRepository.save(reserva);
        return mapper.toReservaDTO(ingReserva);
    }

    @Override
    public void eliminarReserva(int idReserva) {
        reservaRepository.deleteById(idReserva);
    }

    @Override
    public ReservaDTO buscarReserva(int idReserva) {
        return mapper.toReservaDTO(reservaRepository.findById(idReserva)
                .orElseThrow(()-> new RuntimeException("Reserva no encontrada")));
    }

    @Override
    public List<ReservaDTO> listarReservas() {
        return mapper.toReservasDTO((List<Reserva>)reservaRepository.findAll());
    }

    @Override
    public ReservaDTO actualizarReserva(int idReserva, ReservaDTO reservaDTO) {
        Reserva reserva = mapper.toReserva(buscarReserva(idReserva));

        reserva.setFecha(reservaDTO.getFecha());
        reserva.setHora(reservaDTO.getHora());
        //reserva.setUsuario();
        reserva.setRestaurante(restauranteRepository
                .findById(reservaDTO.getIdRestaurante())
                .orElseThrow(()->new RuntimeException("Restaurante no encontrado")));

        reservaRepository.save(reserva);
        return mapper.toReservaDTO(reserva);
    }
    /*
    * VALIDACIONES
    * - Validar que la fecha no sea menor a la actual
    * - Validar la disponibilidad en la hora especificada
    * - Validar que la hora se encuentre en horas laborales
    * */
}
