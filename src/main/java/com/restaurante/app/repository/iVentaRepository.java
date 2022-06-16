package com.restaurante.app.repository;

import com.restaurante.app.entity.Venta;
import org.springframework.data.repository.CrudRepository;

public interface iVentaRepository extends CrudRepository<Venta,Integer> {
}
