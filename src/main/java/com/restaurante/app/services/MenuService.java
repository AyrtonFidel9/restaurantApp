package com.restaurante.app.services;

import com.restaurante.app.dto.MenuDTO;
import com.restaurante.app.entity.Menu;
import com.restaurante.app.entity.Restaurante;
import com.restaurante.app.exceptions.ResourceNotFoundException;
import com.restaurante.app.mapper.iMenuMapper;
import com.restaurante.app.repository.iMenuRepository;
import com.restaurante.app.repository.iRestauranteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
                        new ResourceNotFoundException("Restaurante","id",menuDTO.getIdRestaurante()));
        menu.setRestaurante(res);

        Menu ingMenu = menuRepository.save(menu);
        return mapper.toMenuDTO(ingMenu);
    }

    @Override
    public void elimiarMenu(int idMenu) {menuRepository.deleteById(idMenu);}

    @Override
    public MenuDTO buscarMenu(int idMenu) {
        Optional<Menu> menuResult = menuRepository.findById(idMenu);
        return mapper.toMenuDTO(menuResult
                .orElseThrow(()->
                        new ResourceNotFoundException("Menu","id",idMenu)));
    }

    @Override
    public List<MenuDTO> obtenerMenu() {
        return mapper.toMenusDTO((List<Menu>) menuRepository.findAll());
    }

    @Override
    public MenuDTO actualizarMenu(int idMenu, MenuDTO menuDTO) {

        if (menuRepository.existsById(idMenu)){
            menuDTO.setId(idMenu);
            return ingresarMenu(menuDTO);
        }else{
            throw new ResourceNotFoundException("Menu", "id", idMenu);
        }
    }
}
