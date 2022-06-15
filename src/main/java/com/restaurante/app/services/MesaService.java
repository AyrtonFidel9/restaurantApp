package com.restaurante.app.services;

import com.restaurante.app.dto.MesaDTO;
import com.restaurante.app.entity.Restaurante;
import com.restaurante.app.exceptions.ResourceNotFoundException;
import com.restaurante.app.exceptions.RestauranteAppException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.restaurante.app.repository.iMesaRepository;
import com.restaurante.app.mapper.iMesaMapper;
import com.restaurante.app.entity.Mesa;
import com.restaurante.app.entity.TipoMesas;
import com.restaurante.app.repository.iRestauranteRepository;

import java.util.List;
import java.util.Optional;

@Service
public class MesaService implements iMesaService{

    @Autowired
    private iMesaRepository mesaRepository;

    @Autowired
    private iRestauranteRepository restauranteRepository;

    @Autowired
    private iMesaMapper mapper;

    @Override
    public MesaDTO ingresarMesa(MesaDTO mesaDTO) {

        // validar que la cantidad de asientos no sea 0
        if(mesaDTO.getCapacidad()==0){
            throw new RestauranteAppException(
                    HttpStatus.BAD_REQUEST,
                   "La cantidad de asientos o capacidad de la mesa no puede ser 0");
        }

        if(mesaDTO.getNombre().isEmpty()){
            throw new RestauranteAppException(
                    HttpStatus.BAD_REQUEST,
                    "No existe un nombre para la mesa, no se aceptan nombres vacíos");
        }

        mesaDTO.setIdRestaurante(1);
        int idRes = mesaDTO.getIdRestaurante();
        Mesa mesa = mapper.toMesa(mesaDTO);
        //ingresar el nombre de la mesa
        mesa.setNombre(mesaDTO.getNombre());
        //Settear el tipo de mesa
        mesa.setTipo(getMesaType(mesa.getCapacidad()));
        //settear la disponibilidad
        mesa.setEstado(false);

        //buscar y guardar el objeto restaurante
        Restaurante res = restauranteRepository
                .findById(idRes)
                .orElseThrow(()->
                        new ResourceNotFoundException("Restaurante","id",idRes));
        mesa.setRestaurante(res);

        int cantMesasOnTable = mesaRepository.countMesaByRestauranteId(idRes);

        // VALIDAR que la cantidad de mesas a agregar sea la soportada por el restaurante
        if(cantMesasOnTable >= res.getCantMesas()){
            throw new RestauranteAppException(
                    HttpStatus.BAD_REQUEST,
                    "Ya no se pueden ingresar mas mesas, el limite es: "+res.getCantMesas());
        }

        Mesa ingMesa = mesaRepository.save(mesa);
        return mapper.toMesaDTO(ingMesa);
    }

    /*
    * Por validar
    *  Validar que la cantidad de mesas no sea superior a la capacidad del restaurante - LISTO
    *  Validar que no sea una cadena vacia el nombre
    *  Validar que la cantidad no sea 0
    *  Validar que la el nombre UNIQUE no se repita con un mensaje o excepcion
    * */

    @Override
    public void eliminarMesa(int idMesa) {
        mesaRepository.deleteById(idMesa);
    }

    @Override
    public MesaDTO buscarMesa(int idMesa) {
        Optional<Mesa> mesaResult = mesaRepository.findById(idMesa);
        return mapper.toMesaDTO(mesaResult
                .orElseThrow(()->
                        new ResourceNotFoundException("Mesa","id",idMesa)));
    }

    @Override
    public List<MesaDTO> obtenerMesas() {

        return mapper.toMesasDTO((List<Mesa>)mesaRepository.findAll());
    }

    @Override
    public MesaDTO cambiarEstado(int idMesa, boolean estado) {
        MesaDTO mesaDTO = buscarMesa(idMesa);
        Mesa mesa = mapper.toMesa(mesaDTO);
        mesa.setEstado(estado);
        mesa.setRestaurante(restauranteRepository
                .findById(mesaDTO.getIdRestaurante())
                .orElseThrow(()->
                        new ResourceNotFoundException("Restaurante","id",
                                mesaDTO.getIdRestaurante())));
        mesaRepository.save(mesa);
        return mapper.toMesaDTO(mesa);
    }

    @Override
    public MesaDTO actualizarMesa(int idMesa, MesaDTO mesaDTO) {

        mesaDTO.setIdRestaurante(1);
        Mesa mesa = mapper.toMesa(buscarMesa(idMesa));

        mesa.setNombre(mesaDTO.getNombre());

        mesa.setTipo(
                getMesaType(mesaDTO.getCapacidad())
        );

        mesa.setRestaurante(restauranteRepository
                .findById(mesaDTO.getIdRestaurante())
                .orElseThrow(()->
                        new ResourceNotFoundException("Restaurante","id",
                                mesaDTO.getIdRestaurante())));

        mesa.setEstado(mesaDTO.isEstado());

        mesa.setCapacidad(mesaDTO.getCapacidad());

        mesaRepository.save(mesa);

        return mapper.toMesaDTO(mesa);
    }

    // Funcion para determinar el tipo de mesa
    @Override
    public TipoMesas getMesaType(int numAsientos) {
        if (numAsientos == 1){
            return TipoMesas.individual;
        }
        else
            if(numAsientos == 2){
                return TipoMesas.pareja;
        }
        else
            if(numAsientos >= 3){
                return TipoMesas.familiar;
        }
        return null;
    }
}