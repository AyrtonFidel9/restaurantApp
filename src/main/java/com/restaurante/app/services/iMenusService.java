package com.restaurante.app.services;

import com.restaurante.app.dto.MenuDTO;

import java.util.List;

public interface iMenusService {
    MenuDTO ingresarMenu(MenuDTO menuDTO);

    void eliminarMenu(int idMenu);

    MenuDTO buscarMenu(int idMenu);

    List<MenuDTO> obtenerMenu();

    MenuDTO actualizarMenu(int idMenu, MenuDTO menuDTO);

}
