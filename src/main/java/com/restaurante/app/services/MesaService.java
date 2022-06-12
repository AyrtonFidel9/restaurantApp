package com.restaurante.app.services;

import com.restaurante.app.dto.MesaDTO;
import com.restaurante.app.entity.Restaurante;
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

        //Determinar el tipo de mesa
        int asientos = mesa.getCapacidad();
        if (asientos == 1){
            mesa.setTipo(TipoMesas.individual);
        }
        else if(asientos == 2){
            mesa.setTipo(TipoMesas.pareja);
        }
        else if(asientos >= 3){
            mesa.setTipo(TipoMesas.familiar);
        }
        //settear la disponibilidad
        mesa.setEstado(false);

        //buscar y guardar el objeto restaurante
        Restaurante res = restauranteRepository.findById(idRes).orElseThrow(()->new RuntimeException("Restaurante no encontrado"));
        mesa.setRestaurante(res);

        Mesa ingMesa = mesaRepository.save(mesa);
        return mapper.toMesaDTO(ingMesa);
    }

    /*
    * Por validar
    *  Validar que la cantidad de mesas no sea superior a la capacidad del restaurante
    *  Validar que la cantidad no sea 0
    * */

    @Override
    public void eliminarMesa(int idMesa) {
        mesaRepository.deleteById(idMesa);
    }

    @Override
    public MesaDTO buscarMesa(int idMesa) {
        Optional<Mesa> mesaResult = mesaRepository.findById(idMesa);
        return mapper.toMesaDTO(mesaResult.orElseThrow(()-> new RuntimeException("Mesa no encontrada")));
    }

    @Override
    public List<MesaDTO> obtenerMesas() {
        return mapper.toMesasDTO((List<Mesa>)mesaRepository.findAll());
    }

    @Override
    public MesaDTO cambiarEstado(int idMesa, boolean estado) {
        MesaDTO mesa = buscarMesa(idMesa);
        mesa.setEstado(estado);
        mesaRepository.save(mapper.toMesa(mesa));
        return mesa;
    }
}
