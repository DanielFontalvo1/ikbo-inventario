package com.gestion.ikbo_inventario.models.service.interfaces;

import com.gestion.ikbo_inventario.models.dto.ProductoInventarioDto;
import com.gestion.ikbo_inventario.models.dto.ProductoInvetarioRDto;
import com.gestion.ikbo_inventario.models.dto.ResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductoInventarioService {

    ResponseDto guardarProductoInventario(ProductoInventarioDto productoInventario);

    Page<ProductoInvetarioRDto> listarProductoInventario(String nombreProducto, Pageable page);
}
