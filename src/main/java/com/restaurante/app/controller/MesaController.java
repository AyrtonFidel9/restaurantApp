package com.restaurante.app.controller;

import com.restaurante.app.dto.MesaDTO;
import com.restaurante.app.services.MesaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;


@RestController
@RequestMapping("/api/mesas")
public class MesaController {
    @Autowired
    private MesaService mesaService;

    @PostMapping
    public ResponseEntity<MesaDTO> ingresarMesa (@RequestBody MesaDTO mesaDTO){
        return new ResponseEntity<>(mesaService.ingresarMesa(mesaDTO), HttpStatus.CREATED);
    }
}
