package com.restaurante.app.controller;

import com.restaurante.app.dto.MesaDTO;
import com.restaurante.app.services.MesaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import java.util.List;

@RestController
@RequestMapping("/api/mesas")
public class MesaController {
    @Autowired
    private MesaService mesaService;

    @PostMapping
    public ResponseEntity<MesaDTO> ingresarMesa (@RequestBody MesaDTO mesaDTO){
        return new ResponseEntity<>(mesaService.ingresarMesa(mesaDTO), HttpStatus.CREATED);
    }
    @GetMapping
    public List<MesaDTO> getMesas(){
        return mesaService.obtenerMesas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<MesaDTO> getMesaById(@PathVariable(name = "id") int id){
        return ResponseEntity.ok(mesaService.buscarMesa(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMesa(@PathVariable(name = "id") int id) {
        MesaDTO mesa = mesaService.buscarMesa(id);
        mesaService.eliminarMesa(id);
        return new ResponseEntity<>("Mesa de tipo: "+mesa.getTipo()+" con ID: "+id+" eliminada con exito", HttpStatus.OK);
    }
}