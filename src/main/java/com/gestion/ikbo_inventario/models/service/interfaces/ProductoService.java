package com.gestion.ikbo_inventario.models.service.interfaces;

import com.gestion.ikbo_inventario.models.dto.ProductoDto;
import com.gestion.ikbo_inventario.models.dto.ResponseDto;
import com.gestion.ikbo_inventario.models.entities.Producto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductoService {

    ResponseDto guardarProducto(ProductoDto producto);

    Producto consultarProductoxNombreOrIdProducto(int idproducto, String nombreProducto);

    Page<Producto> listarProductos(int idProducto, String nombreProducto, Pageable page);

}
