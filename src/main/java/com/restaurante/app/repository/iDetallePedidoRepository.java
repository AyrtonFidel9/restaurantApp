package com.restaurante.app.repository;

import com.restaurante.app.entity.DetallePedido;
import com.restaurante.app.entity.DetallePedidoKey;
import org.hibernate.annotations.SQLDelete;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Set;

@Repository
public interface iDetallePedidoRepository extends CrudRepository<DetallePedido, DetallePedidoKey> {

    @Query(value = "SELECT SUM(subtotal) FROM detalles_pedidos WHERE id_pedido=:id", nativeQuery = true)
    BigDecimal sumSubTotalDetallePedidoByIdPedido(@Param("id") int idPedido);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM detalles_pedidos WHERE id_pedido = :id", nativeQuery = true)
    void eliminar(@Param("id") int idPedido);

    @Query(value = "SELECT * FROM detalles_pedidos WHERE id_pedido = :id", nativeQuery = true)
    Set<DetallePedido> pedidosbyid(@Param("id") int idPedido);

}
