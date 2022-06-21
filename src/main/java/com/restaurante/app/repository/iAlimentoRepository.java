package com.restaurante.app.repository;

import com.restaurante.app.entity.Alimento;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface iAlimentoRepository extends CrudRepository<Alimento, Integer> {
    List<Alimento> findByMenuId(int id);
}
