package com.restaurante.app.repository;

import com.restaurante.app.entity.EstadoPedido;
import com.restaurante.app.entity.Pedido;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

public interface iPedidoRepository extends CrudRepository<Pedido, Integer> {

}
