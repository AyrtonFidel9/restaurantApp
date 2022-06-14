package com.restaurante.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/menus")
public class MenuController {
    @Autowired
    private MesaService mesaService;

    @PostMapping
    public ResponseEntity<MesaDTO> ingresarMesa (@RequestBody MesaDTO mesaDTO){
        return new ResponseEntity<>(mesaService.ingresarMesa(mesaDTO), HttpStatus.CREATED);
    }
}
