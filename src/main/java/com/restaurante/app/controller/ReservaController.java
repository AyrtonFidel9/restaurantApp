package com.restaurante.app.controller;

import com.restaurante.app.dto.ReservaDTO;
import com.restaurante.app.services.ReservaMesaService;
import com.restaurante.app.services.ReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/reservas")
@CrossOrigin(origins = "*")
public class ReservaController {
    @Autowired
    private ReservaService reservaService;

    @Autowired
    private ReservaMesaService reservaMesaService;

    @PostMapping
    public ResponseEntity<ReservaDTO> ingresarReserva (@RequestBody @Valid ReservaDTO reservaDTO){
        return new ResponseEntity<>(reservaService.ingresarReserva(reservaDTO), HttpStatus.CREATED);
    }

    @GetMapping
    public List<ReservaDTO> getReservas (){
        return reservaService.listarReservas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReservaDTO> getReservaById(@PathVariable(name = "id") int id){
        return ResponseEntity.ok(reservaService.buscarReserva(id));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMesa (@PathVariable(name = "id") int id){
        reservaService.buscarReserva(id);
        reservaService.eliminarReserva(id);
        return new ResponseEntity<>("La Reserva del usuario "+id+" ha sido eliminada con éxito", HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<ReservaDTO> actualizarReserva (@PathVariable(name = "id") int id,
                                                         @RequestBody @Valid ReservaDTO reservaDTO){
        ReservaDTO reserva = reservaService.actualizarReserva(id, reservaDTO);
        return new ResponseEntity<>(reserva,HttpStatus.OK);
    }

    @GetMapping("/usuario/{id}")
    public List<ReservaDTO> getReservasByUserId(@PathVariable(name = "id") int id){
        return reservaService.listaReservasByUserId(id);
    }

}
