package com.restaurante.app.controller;

import com.restaurante.app.dto.VentaDTO;
import com.restaurante.app.entity.Venta;
import com.restaurante.app.services.VentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/venta")
@CrossOrigin(origins = "*")
public class VentaController {
    @Autowired
    private VentaService ventaService;

    @PostMapping
    public ResponseEntity<VentaDTO> ingresarVenta(@RequestBody VentaDTO ventaDTO)
    {
        return new ResponseEntity<>(ventaService.ingresarVenta(ventaDTO), HttpStatus.CREATED);
    }

    @GetMapping
    public List<VentaDTO> getVenta(){
        return ventaService.obtenerVentas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<VentaDTO>getVentaById(@PathVariable(name = "id") int id)
    {
        return ResponseEntity.ok(ventaService.buscarVenta(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<VentaDTO>actualizarVenta(@PathVariable(name = "id") int id, @RequestBody @Valid VentaDTO ventaDTO)
    {
        VentaDTO venta = ventaService.actualizarVenta(id, ventaDTO);
        return new ResponseEntity<>(venta,HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String>eliminarVenta(@PathVariable(name = "id") int id)
    {
        VentaDTO venta = ventaService.buscarVenta(id);
        ventaService.eliminarVenta(id);
        return new ResponseEntity<>("Venta con ID "+id+" borrada exitosamente",HttpStatus.OK);
    }


}
