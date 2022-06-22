package com.restaurante.app.controller;

import com.restaurante.app.dto.ReportePropinasDTO;
import com.restaurante.app.services.ReportesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/reportes")
@CrossOrigin(origins = "*")
public class ReportesController {
    @Autowired
    private ReportesService reportesService;
    @GetMapping
    public List<ReportePropinasDTO> getReporte(){
        return reportesService.obtenerReporte();
    }
}
