package com.restaurante.app.services;

import com.restaurante.app.entity.ReservaMesa;
import com.restaurante.app.repository.iReservaMesaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.restaurante.app.mapper.iReservaMesaMapper;
import com.restaurante.app.exceptions.RestauranteAppException;
@Service
public class ReservaMesaService implements iReservaMesaService{

    @Autowired
    private iReservaMesaRepository reservaMesaRepository;

    @Autowired
    private iReservaMesaMapper mapper;

    @Override
    public void eliminarReservaMesa(int idReserva, int idMesa) {

        ReservaMesa searchResMes = reservaMesaRepository
                .findReservaMesaById_IdReservaAndId_IdMesa(idReserva, idMesa);
        if(searchResMes == null){
            throw new RestauranteAppException(HttpStatus.BAD_REQUEST, "No se a encontrado el detalle de la reserva de la mesa");
        }
        reservaMesaRepository.delete(searchResMes);
    }
}
