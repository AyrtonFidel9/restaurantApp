package com.restaurante.app.services;

import com.restaurante.app.dto.AlimentoDTO;
import com.restaurante.app.entity.Alimento;
import com.restaurante.app.entity.Menu;
import com.restaurante.app.entity.TipoMenu;
import com.restaurante.app.exceptions.ResourceNotFoundException;
import com.restaurante.app.exceptions.RestauranteAppException;
import com.restaurante.app.mapper.iAlimentoMapper;
import com.restaurante.app.repository.iAlimentoRepository;
import com.restaurante.app.repository.iMenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AlimentosService implements iAlimentoService{

    @Autowired
    private iAlimentoRepository alimentoRepository;

    @Autowired
    private iMenuRepository menuRepository;

    @Autowired
    private iAlimentoMapper mapper;

    @Override
    public AlimentoDTO ingresarAlimento(AlimentoDTO alimentoDTO) {
        Alimento alimento = mapper.toAlimento(alimentoDTO);

        alimento.setDescripcion(alimentoDTO.getDescripcion());

        Menu menu = menuRepository
                .findById(alimentoDTO.getIdMenu())
                .orElseThrow(
                        () -> new ResourceNotFoundException("Menu", "id",alimentoDTO.getIdMenu())
                );
        Alimento ingAlimento = alimentoRepository.save(alimento);

        if (alimentoDTO.getTipo().toString().length() <2){
            throw new RestauranteAppException(HttpStatus.NOT_ACCEPTABLE, "La longuitud del nombre de los alimentos debe ser mayor a 2");
        }

        if (alimentoDTO.getPrecio()<0){
            throw new RestauranteAppException(HttpStatus.BAD_REQUEST ,"El precio no puede ser negativo");
        }

        return mapper.toAlimentoDTO(ingAlimento);
    }

    @Override
    public void eliminarAlimento(int idAlimento) {
        alimentoRepository.deleteById(idAlimento);
    }

    @Override
    public AlimentoDTO buscarAlimento(int idAlimento) {
        Optional<Alimento> alimentoResult = alimentoRepository.findById(idAlimento);

        return mapper.toAlimentoDTO(alimentoResult
                .orElseThrow(()->
                        new ResourceNotFoundException("Alimento","id",idAlimento)
                ));
    }

    @Override
    public List<AlimentoDTO> obtenerAlimento() {
        return mapper.toAlimentosDTO((List<Alimento>) alimentoRepository.findAll());
    }

    @Override
    public AlimentoDTO cambiarDisponibilidad(int idAlimeto, boolean disponibilidad) {
        AlimentoDTO alimentoDTO = buscarAlimento(idAlimeto);
        Alimento alimento = mapper.toAlimento(alimentoDTO);
        alimento.setDisponibilidad(disponibilidad);
        alimentoRepository.save(alimento);
        return mapper.toAlimentoDTO(alimento);
    }

    @Override
    public AlimentoDTO actualizarAlimento(int idAlimento, AlimentoDTO alimentoDTO) {
        AlimentoDTO alimentoEncontrado = buscarAlimento(idAlimento);
        alimentoEncontrado = alimentoDTO;
        return mapper.toAlimentoDTO(alimentoRepository.save(mapper.toAlimento(alimentoEncontrado)));
    }

    @Override
    public List<AlimentoDTO> obtenerAlimentoPorTipo(TipoMenu tipo) {
        return null;
    }
}
