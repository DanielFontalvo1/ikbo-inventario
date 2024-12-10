package com.gestion.ikbo_inventario.models.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ProductoDto {
    private String nombreProducto;
    private Float precioUnitario;
}
