package com.restaurante.app.services;

import com.restaurante.app.dto.ReservaDTO;
import com.restaurante.app.entity.*;
import com.restaurante.app.repository.iReservaRepository;
import com.restaurante.app.mapper.iReservaMapper;
import com.restaurante.app.repository.iRestauranteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.restaurante.app.repository.iUsuarioRepository;
import com.restaurante.app.repository.iMesaRepository;


import java.util.Collections;
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
    private iMesaRepository mesaRepository;
    @Autowired
    private iReservaMapper mapper;

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
                .orElseThrow(() -> new RuntimeException("Restaurante no encontrado"));

        reserva.setRestaurante(res);

        //buscar y guardar el objeto usuario
        Usuario usuarioRes = usuarioRepository.findById(idUsu)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        reserva.setUsuario(usuarioRes);

        //Ingresar las mesas que forman parte de la reserva
        Set<ReservaMesa> listReservaMesa = Collections.EMPTY_SET;
        listReservaMesa = reservaDTO
                .getReservaMesas()
                .stream()
                .map(reservaMesa -> {
                    Mesa mesa = mesaRepository.findById(reservaMesa
                                    .getId().getIdMesa())
                            .orElseThrow(() -> new RuntimeException("Mesa no encontrada"));

                    System.out.println(mesa.toString());
                    ReservaMesa reservarMesa = new ReservaMesa();
                    reservarMesa.setMesas(mesa);
                    reservarMesa.setReserva(reserva);
                    return reservarMesa;
                }).collect(Collectors.toSet());
        reserva.setReservaMesas(listReservaMesa);

        reservaRepository.save(reserva);
        return mapper.toReservaDTO(reserva);
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
