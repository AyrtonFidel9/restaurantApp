package com.restaurante.app.repository;

import com.restaurante.app.entity.EstadoPedido;
import com.restaurante.app.entity.Pedido;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface iPedidoRepository extends CrudRepository<Pedido, Integer> {

    @Modifying
    @Transactional
    @Query(value = "UPDATE pedidos SET estado_pedido= :estado WHERE id = :id", nativeQuery = true)
    void actualizarEstado(@Param("id") int idPedido, @Param("estado")EstadoPedido estado);

}
