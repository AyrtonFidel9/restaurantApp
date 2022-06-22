package com.restaurante.app.services;

import com.restaurante.app.dto.ReportePropinasDTO;
import com.restaurante.app.repository.iReportesRepository;
import com.restaurante.app.repository.iVentaRepository;
import org.hibernate.dialect.function.CastFunction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ReportesService {
    @Autowired
    private iVentaRepository ventaRepository;

    public List<ReportePropinasDTO> obtenerReporte()
    {
        Set<ReportePropinasDTO> repropinas = ventaRepository.rpropinas();

        System.out.println("propinas = " + repropinas);
        return repropinas.stream().collect(Collectors.toList());
    }
}
