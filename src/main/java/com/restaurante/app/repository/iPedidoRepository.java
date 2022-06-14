package com.restaurante.app.repository;

import com.restaurante.app.entity.Pedido;
import org.springframework.data.repository.CrudRepository;

public interface iPedidoRepository extends CrudRepository<Pedido, Integer> {
}
