package com.gestion.ikbo_inventario.models.daos;

import com.gestion.ikbo_inventario.models.dto.ProductoInvetarioRDto;
import com.gestion.ikbo_inventario.models.entities.ProductoInventario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ProductoInventarioDao extends CrudRepository<ProductoInventario, Integer>,
        PagingAndSortingRepository<ProductoInventario, Integer> {

    @Query(nativeQuery = true, value = """
        select pi.consecutivo, p.id_producto as idProducto, pi.cantidad, 
               pi.fecha_caduca as fechaCaduca, p.nombre_producto as nombreProducto,
               CASE 
                 WHEN pi.fecha_caduca <= CURRENT_DATE THEN 'Vencido'
                 WHEN EXTRACT(DAY FROM AGE(fecha_caduca, CURRENT_DATE)) >= 1 and EXTRACT(DAY FROM AGE(fecha_caduca, CURRENT_DATE)) <= 3 THEN 'Por vencer'
                WHEN EXTRACT(DAY FROM AGE(fecha_caduca, CURRENT_DATE)) > 3 THEN 'Vigente'
                ELSE 'Desconocido'
               END as estado 
          from productos_inventario pi
           inner join productos p on p.id_producto = pi.producto
           inner join estados e on e.id_estado = pi.estado
        where ('%' = ?1 or p.nombre_producto = ?1)
          and  pi.estado = 1
        ORDER BY pi.fecha_caduca ASC;
    """)
    Page<ProductoInvetarioRDto> listarProductosInventario(String nombreProducto, Pageable page);

    @Query("select p from ProductoInventario p where p.producto.idProducto = ?1 and p.consecutivo = ?2")
    ProductoInventario getProductoInventarioByProducto_IdProductoAndConsecutivo(int idProducto, int consecutivo);

}