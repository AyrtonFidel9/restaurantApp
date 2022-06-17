package com.restaurante.app.repository;

import com.restaurante.app.entity.DetallePedido;
import com.restaurante.app.entity.DetallePedidoKey;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;

@Repository
public interface iDetallePedidoRepository extends CrudRepository<DetallePedido, DetallePedidoKey> {

    @Query(value = "SELECT SUM(subtotal) FROM detalles_pedidos WHERE id_pedido=:id", nativeQuery = true)
    BigDecimal sumSubTotalDetallePedidoByIdPedido(@Param("id") int idPedido);
}
