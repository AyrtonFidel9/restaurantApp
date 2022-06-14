package com.restaurante.app.services;

import com.restaurante.app.dto.MenuDTO;
import com.restaurante.app.entity.Menu;
import com.restaurante.app.mapper.iMenuMapper;
import com.restaurante.app.repository.iMenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MenuService implements iMenusService{

    @Autowired
    private iMenuRepository menuRepository;

    @Autowired
    private iMenuMapper mapper;

    @Override
    public MenuDTO ingresarMenu(MenuDTO menuDTO) {
        Menu menu = mapper.toMenu(menuDTO);
        Menu ingMenu = menuRepository.save(menu);
        return mapper.toMenuDTO(ingMenu);
    }
}
