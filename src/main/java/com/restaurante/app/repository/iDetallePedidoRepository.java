package com.restaurante.app.repository;

import com.restaurante.app.entity.DetallePedido;
import com.restaurante.app.entity.DetallePedidoKey;
import org.springframework.data.repository.CrudRepository;

public interface iDetallePedidoRepository extends CrudRepository<DetallePedido, DetallePedidoKey> {
}
