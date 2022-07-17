package com.restaurante.app.services;

import com.restaurante.app.dto.ReservaDTO;
import com.restaurante.app.entity.*;
import com.restaurante.app.exceptions.ResourceNotFoundException;
import com.restaurante.app.exceptions.RestauranteAppException;
import com.restaurante.app.repository.iReservaRepository;
import com.restaurante.app.mapper.iReservaMapper;
import com.restaurante.app.repository.iRestauranteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.restaurante.app.repository.iUsuarioRepository;
import com.restaurante.app.repository.iMesaRepository;
import com.restaurante.app.repository.iReservaMesaRepository;
import com.restaurante.app.mapper.iReservaMesaMapper;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalTime;
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
    private iReservaMesaRepository reservaMesaRepository;

    @Autowired
    private iUsuarioRepository usuarioRepository;

    @Autowired
    private iMesaRepository mesaRepository;
    @Autowired
    private iReservaMapper mapper;

    @Autowired
    private iReservaMesaMapper reservaMesaMapper;


    @Override
    public ReservaDTO ingresarReserva(ReservaDTO reservaDTO) {


        // se obtiene la hora actual
        LocalTime horaActual = LocalTime.now();

        // se obtiene la fecha actual
        //comprobar que la hora de la reserva sea mayor a la actual
        LocalDate fechaActual = LocalDate.now();

        if(reservaDTO.getFecha().isBefore(fechaActual)){
            throw new RestauranteAppException(HttpStatus.BAD_REQUEST,
                    "La fecha "+reservaDTO.getFecha()+" no se encuentra habilitada para una reservacion," +
                            "elija otra fecha, por favor");
        }
        else if(reservaDTO.getHora().isBefore(horaActual) && reservaDTO.getFecha().isEqual(fechaActual)){
                throw new RestauranteAppException(HttpStatus.BAD_REQUEST,
                        "La hora "+reservaDTO.getHora()+" no se encuentra disponible para una reservacion, " +
                                "no se puede reservar en el pasado");

        }

        reservaDTO.setIdRestaurante(1);
        //capturar el id del restaurante
        int idRest = reservaDTO.getIdRestaurante();
        //capturar el id del usuario
        int idUsu = reservaDTO.getIdUsuario();

        Reserva reserva = mapper.toReserva(reservaDTO);

        //buscar y guardar el objeto restaurante
        Restaurante res = restauranteRepository.findById(idRest)
                .orElseThrow(() -> new ResourceNotFoundException("Restaurante","id",idRest));

        reserva.setRestaurante(res);


        if((reservaDTO.getHora().isBefore(res.getHoraApertura()) ||
                reservaDTO.getHora().isAfter(res.getHoraCierre()))){
            throw new RestauranteAppException(HttpStatus.BAD_REQUEST,
                    "La hora "+ reservaDTO.getHora() +
                            " esta fuera de las horas laborables del restaurante");
        }

        //buscar y guardar el objeto usuario
        Usuario usuarioRes = usuarioRepository.findById(idUsu)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario","id",idUsu));

        reserva.setUsuario(usuarioRes);

        if(reservaRepository.countByUsuarioIdAndFecha(usuarioRes.getId(), fechaActual) > 2){
            throw new RestauranteAppException(HttpStatus.BAD_REQUEST,
                    "El usuario "+usuarioRes.getNombre()+" "+usuarioRes.getApellido()+" solo" +
                            " puede realizar como máximo 2 reservaciones por día");
        }

        if(reservaDTO.getIdReserva() != 0){
            reservaMesaRepository.deleteReservaMesaByIdReserva(reservaDTO.getIdReserva());
        }

        //Ingresar las mesas que forman parte de la reserva
        reserva.setReservaMesas(
                createListReservasMesas(reserva, reservaDTO));

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
                .orElseThrow(()-> new RestauranteAppException(HttpStatus.BAD_REQUEST,"Reserva no encontrada")));
    }

    @Override
    public List<ReservaDTO> listarReservas() {
        return mapper.toReservasDTO((List<Reserva>)reservaRepository.findAll());
    }


    @Override
    public ReservaDTO actualizarReserva(int idReserva, ReservaDTO reservaDTO) {
        buscarReserva(idReserva); // compruebo si existe la reserva
        reservaDTO.setIdReserva(idReserva);
        return ingresarReserva(reservaDTO);
    }
    /*
    * VALIDACIONES
    * - Validar que la fecha no sea menor a la actual Listo
    * - Validar la disponibilidad en la hora especificada
    * - Validar que la hora se encuentre en horas laborales
    * - Validar que la mesa no se encuentr ocupada
    * */

    @Override
    public Set<ReservaMesa> createListReservasMesas(Reserva reserva, ReservaDTO reservaDTO){

        Set<ReservaMesa> listReservaMesa = Collections.EMPTY_SET;
        listReservaMesa = reservaDTO
                .getReservaMesas()
                .stream()
                .map(reservaMesa -> {
                    Mesa mesa = mesaRepository.findById(reservaMesa
                                    .getId().getIdMesa())
                            .orElseThrow(() -> new ResourceNotFoundException("Mesa","id",
                                    reservaMesa.getId().getIdMesa()));

                    LocalTime horaFin = reserva.getHora().plusMinutes(
                            (long) reserva.getDuracion());

                    Set<ReservaMesa> setReservas =
                            reservaMesaRepository
                                    .findReservaMesaByFechaAndHoraBetween(
                                            reserva.getFecha(),reserva.getHora(), horaFin);

                    if(!setReservas.isEmpty()){
                        setReservas.stream().forEach(resmes->{
                            if(resmes.getId().getIdMesa() == mesa.getId()){
                                throw new RestauranteAppException(HttpStatus.BAD_REQUEST,
                                        "La Mesa "+mesa.getNombre()+
                                                " se encuentra reservada entre "
                                                +reserva.getHora()+" - "+horaFin +
                                                " en la fecha "+ reserva.getFecha());
                            }
                        });

                    }

                    //actualizar el estado de la mesa
                    //mesa.setEstado(true);
                    System.out.println(mesa.toString());
                    ReservaMesa reservarMesa = new ReservaMesa();
                    reservarMesa.setMesas(mesa);
                    reservarMesa.setReserva(reserva);
                    reservarMesa.setFecha(reserva.getFecha());
                    reservarMesa.setHora(reserva.getHora());
                    return reservarMesa;
                }).collect(Collectors.toSet());
        return listReservaMesa;
    }

    @Override
    public List<ReservaDTO> listaReservasByUserId(int idUser) {
        return mapper.toReservasDTO(reservaRepository.findReservaByUsuarioId(idUser));
    }
}
