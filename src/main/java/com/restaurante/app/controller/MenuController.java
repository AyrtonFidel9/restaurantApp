package com.restaurante.app.controller;

import com.restaurante.app.dto.MenuDTO;
import com.restaurante.app.services.MenuService;
import com.restaurante.app.services.MesaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.restaurante.app.dto.MesaDTO;

import java.util.List;

@RestController
@RequestMapping("/api/menus")
@CrossOrigin(origins = "*")
public class MenuController {
    @Autowired
    private MenuService menuService;

    @PostMapping
    public ResponseEntity<MenuDTO> ingresarMenu (@RequestBody MenuDTO menuDTO){
        return new ResponseEntity<>(menuService.ingresarMenu(menuDTO), HttpStatus.CREATED);
    }
    @GetMapping
    public List<MenuDTO> getMenu(){return menuService.obtenerMenu();}

    @GetMapping("/{id}")
    public ResponseEntity<MenuDTO> getMenuById(@PathVariable(name = "id") int id){
        return ResponseEntity.ok(menuService.buscarMenu(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMenu(@PathVariable(name = "id") int id){
        menuService.elimiarMenu(id);
        return new ResponseEntity<>("Menu con ID:"+id+" eliminado con éxito",HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MenuDTO> actualizarMenu (@PathVariable(name = "id") int id,
                                                   @RequestBody MenuDTO menuDTO){
        MenuDTO menuDTOresp = menuService.actualizarMenu(id, menuDTO);
        return new ResponseEntity<>(menuDTOresp,HttpStatus.OK);
    }

}
