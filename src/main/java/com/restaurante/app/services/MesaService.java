package com.restaurante.app.services;

import com.restaurante.app.dto.MesaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.restaurante.app.repository.iMesaRepository;
import com.restaurante.app.mapper.iMesaMapper;
import com.restaurante.app.entity.Mesa;

@Service
public class MesaService implements iMesaService{

    @Autowired
    private iMesaRepository mesaRepository;

    @Autowired
    private iMesaMapper mapper;

    @Override
    public MesaDTO ingresarMesa(MesaDTO mesaDTO) {
        Mesa mesa = mapper.toMesa(mesaDTO);
        Mesa ingMesa = mesaRepository.save(mesa);
        return mapper.toMesaDTO(ingMesa);
    }
}
