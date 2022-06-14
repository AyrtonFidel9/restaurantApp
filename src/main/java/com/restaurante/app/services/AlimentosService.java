package com.restaurante.app.services;

import com.restaurante.app.dto.AlimentoDTO;
import com.restaurante.app.entity.Alimento;
import com.restaurante.app.entity.Menu;
import com.restaurante.app.entity.TipoMenu;
import com.restaurante.app.mapper.iAlimentoMapper;
import com.restaurante.app.repository.iAlimentoRepository;
import com.restaurante.app.repository.iMenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

        Menu res = menuRepository
                .findById(alimentoDTO.getIdMenu())
                .orElseThrow(
                        () -> new RuntimeException("Menu no encontrado")
                );
        Alimento ingAlimento = alimentoRepository.save(alimento);

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
                        new RuntimeException("Alimento no encontrado")
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
        return null;
    }

    @Override
    public List<AlimentoDTO> obtenerAlimentoPorTipo(TipoMenu tipo) {
        return null;
    }
}
