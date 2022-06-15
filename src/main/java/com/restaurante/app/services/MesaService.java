package com.restaurante.app.services;

import com.restaurante.app.dto.MesaDTO;
import com.restaurante.app.entity.Restaurante;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
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
                        new RuntimeException("Restaurante no encontrado"));
        mesa.setRestaurante(res);

        Mesa ingMesa = mesaRepository.save(mesa);
        return mapper.toMesaDTO(ingMesa);
    }

    /*
    * Por validar
    *  Validar que la cantidad de mesas no sea superior a la capacidad del restaurante
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
                        new RuntimeException("Mesa no encontrada")));
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
                        new RuntimeException("Restaurante no encontrado")));
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
                        new RuntimeException("Restaurante no encontrado")));

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