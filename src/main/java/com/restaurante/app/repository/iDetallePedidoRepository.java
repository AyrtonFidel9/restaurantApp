package com.restaurante.app.repository;

import com.restaurante.app.entity.DetallePedido;
import com.restaurante.app.entity.DetallePedidoKey;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.math.BigDecimal;

public interface iDetallePedidoRepository extends CrudRepository<DetallePedido, DetallePedidoKey> {

    @Query(value = "SELECT sum(subtotal) FROM detalle_pedido WHERE id_pedido = ?1", nativeQuery = true)
    BigDecimal sumSubTotalDetallePedidoByIdPedido(int idPedido);
}
