package com.gestion.ikbo_inventario.models.daos;

import com.gestion.ikbo_inventario.models.entities.Estado;
import org.springframework.data.repository.CrudRepository;

public interface EstadoDao extends CrudRepository<Estado, Integer> {
}