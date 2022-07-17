package com.restaurante.app.controller;

import com.restaurante.app.dto.AlimentoDTO;
import com.restaurante.app.services.AlimentosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/alimentos")
@CrossOrigin(origins = "*")
public class AlimentoController {
    @Autowired
    private AlimentosService alimentosService;

    @PostMapping
    public ResponseEntity<AlimentoDTO> ingresarAliemnto(@RequestBody @Valid AlimentoDTO alimentoDTO){
        return new ResponseEntity<>(alimentosService.ingresarAlimento(alimentoDTO), HttpStatus.CREATED);
    }

    @GetMapping
    public List<AlimentoDTO> getAlimento(){return alimentosService.obtenerAlimento();}

    @GetMapping("/{id}")
    public ResponseEntity<AlimentoDTO> getAlimentoById(@PathVariable(name = "id") int id){
        return ResponseEntity.ok(alimentosService.buscarAlimento(id));
    }

    @GetMapping("/byMenu/{idMenu}")
    public List<AlimentoDTO> getAlimentoByIdMenu(@PathVariable(name = "idMenu") int id){
        return alimentosService.obtenerAlimentoByIdMenu(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAlimento(@PathVariable(name = "id") int id){
        AlimentoDTO alimento = alimentosService.buscarAlimento(id);
        alimentosService.eliminarAlimento(id);
        return new ResponseEntity<>("Alimento con ID:"+id+" eliminado con éxito",HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AlimentoDTO> actualizarAlimento(@PathVariable(name = "id") int id,
                                                 @RequestBody @Valid AlimentoDTO alimentoDTO){
        AlimentoDTO alimentoResp = alimentosService.actualizarAlimento(id, alimentoDTO);
        return new ResponseEntity<>(alimentoResp,HttpStatus.OK);
    }

}
