package com.restaurante.app.services;

import com.restaurante.app.dto.MenuDTO;
import com.restaurante.app.entity.Menu;
import com.restaurante.app.entity.Restaurante;
import com.restaurante.app.mapper.iMenuMapper;
import com.restaurante.app.repository.iMenuRepository;
import com.restaurante.app.repository.iRestauranteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuService implements iMenusService{

    @Autowired
    private iRestauranteRepository restauranteRepository;

    @Autowired
    private iMenuRepository menuRepository;

    @Autowired
    private iMenuMapper mapper;

    @Override
    public MenuDTO ingresarMenu(MenuDTO menuDTO) {
        menuDTO.setIdRestaurante(1);

        Menu menu = mapper.toMenu(menuDTO);

        menu.setNombre(menuDTO.getNombre());
        menu.setTipo(menuDTO.getTipo());

        Restaurante res = restauranteRepository
                .findById(menuDTO.getIdRestaurante())
                .orElseThrow(()->
                        new RuntimeException("Restaurante no encontrado"));
        menu.setRestaurante(res);

        Menu ingMenu = menuRepository.save(menu);
        return mapper.toMenuDTO(ingMenu);
    }

    @Override
    public void elimiarMenu(int idMenu) {

    }

    @Override
    public MenuDTO buscarMenu(int idMenu) {
        return null;
    }

    @Override
    public List<MenuDTO> obtenerMenu() {
        return null;
    }

    @Override
    public MenuDTO actualizarMenu(int idMenu, MenuDTO menuDTO) {
        return null;
    }
}
