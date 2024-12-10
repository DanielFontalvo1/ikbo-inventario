package com.gestion.ikbo_inventario.models.daos;

import com.gestion.ikbo_inventario.models.entities.Producto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ProductoDao extends CrudRepository<Producto, Integer>, PagingAndSortingRepository<Producto, Integer> {


    @Query("select p from Producto p where (p.idProducto = ?1 or p.nombreProducto = ?2) and p.estado.idEstado = 1")
    Producto getProductoByIdProductoOrNombreProducto(int idproducto, String nombreProducto);

    @Query(value= """
        SELECT p
         FROM Producto  p
       WHERE ( 0 = ?1  or p.idProducto = ?1)
         and ('%'= ?2 or p.nombreProducto like concat('%',?2,'%'))
         and p.estado.idEstado = 1
    """)
    Page<Producto> getAllByIdProductoAndNombreProducto(int idProducto, String nombreProducto, Pageable pageable);
}