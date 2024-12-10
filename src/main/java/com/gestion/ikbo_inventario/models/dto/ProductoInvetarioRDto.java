package com.gestion.ikbo_inventario.models.dto;

import java.util.Date;

public interface ProductoInvetarioRDto {

    int getConsecutivo();
    int getIdProducto();
    String getNombreProducto();
    int getCantidad();
    Date getFechaCaduca();
    String getEstado();
}
