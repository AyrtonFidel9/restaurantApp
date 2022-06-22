package com.restaurante.app.repository;

import com.restaurante.app.entity.Venta;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface iVentaRepository extends CrudRepository<Venta,Integer> {
    @Query(value = "SELECT p.id_usuario, a.nombre, a.apellido, sum(v.propina) as \"total_propina\", v.fecha FROM" +
            " ventas as v INNER JOIN pedidos as p on v.id_pedido = p.id INNER JOIN usuarios as a on " +
            "p.id_usuario = a.id group by p.id_usuario, v.fecha order by v.fecha , a.apellido asc",
            nativeQuery = true)
    ArrayList<Object> rpropinas ();
}
