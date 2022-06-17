package com.restaurante.app.controller;

import com.restaurante.app.dto.MenuDTO;
import com.restaurante.app.dto.PedidoDTO;
import com.restaurante.app.services.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @PostMapping
    public ResponseEntity<PedidoDTO> ingresarPedido (@RequestBody PedidoDTO pedidoDTO){
        return new ResponseEntity<>(pedidoService.ingresarPedido(pedidoDTO), HttpStatus.CREATED);
    }

    @GetMapping
    public List<PedidoDTO> getPedido (){return pedidoService.obtenerPedido();}

    @GetMapping("/{id}")
    public ResponseEntity<PedidoDTO> getPedidoById(@PathVariable(name = "id") int id){
        return ResponseEntity.ok(pedidoService.buscarPedido(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePedido(@PathVariable(name = "id") int id){
        pedidoService.eliminarPedido(id);
        return new ResponseEntity<>("Pedido con ID:"+id+" eliminado con éxito",HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PedidoDTO> actualizarPedido (@PathVariable(name = "id") int id,
                                                   @RequestBody PedidoDTO pedidoDTO){
        PedidoDTO pedidoDTOresp = pedidoService.actualizarPedido(id, pedidoDTO);
        return new ResponseEntity<>(pedidoDTOresp,HttpStatus.OK);
    }

}