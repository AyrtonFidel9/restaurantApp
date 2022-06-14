package com.restaurante.app.repository;

import com.restaurante.app.entity.Menu;
import org.springframework.data.repository.CrudRepository;

public interface iMenuRepository extends CrudRepository<Menu, Integer> {

}
