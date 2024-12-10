package com.gestion.ikbo_inventario.utils;

import lombok.experimental.UtilityClass;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@UtilityClass
public class ResourceUtil {

    public static final String productExit = "Producto guardado exitosamente";
    public static final String notProductNombre = "No hay nombre para el producto";
    public static final String notProductoPrecioUnitario = "Precio unitario del producto incorrecto";
    public static final String notFechaCaducaProductoInventario = "Fecha de caducidad invalidad";
    public static final String notCantidadProductoInventario ="La cantidad del producto debe ser mayor a cero";
    public static final String productoInventarioExit = "Producto guardado en el inventario exitosamente";
    public static final String productoInvetarioUpdate = "Producto en invetario actualizado exitosamente";
    public static final String productoNotFound = "Producto no existe";
    public static final String productoInvetarioInactivo ="Producto en invetario inactivado";

    public static Date obtenerFechaActual() throws ParseException {
        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
        Date fechaActual = new Date();
        String fechaFormateada = formatoFecha.format(fechaActual);
        return formatoFecha.parse(fechaFormateada);
    }

    public static Date getFechaConvertida(Date fechaConvertir) {
        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
        String fechaDateString = formatoFecha.format(fechaConvertir);
        Date fecha = null;

        try {
            fecha = formatoFecha.parse(fechaDateString);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fecha;
    }

    public static Date convertitFechaStringaDate(String fechaConvertir) {
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        Date fecha=null;
        try {
            fecha = formato.parse(fechaConvertir);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fecha;
    }
}
