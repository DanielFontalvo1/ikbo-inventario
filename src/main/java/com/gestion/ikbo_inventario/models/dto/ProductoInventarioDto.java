package com.gestion.ikbo_inventario.models.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ProductoInventarioDto {

    private String fechaCaduca;
    private int cantidad;
    private int idProducto;
    private int consecutivo;
}
