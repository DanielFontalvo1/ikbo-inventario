package com.gestion.ikbo_inventario.models.service.implementations;

import com.gestion.ikbo_inventario.models.daos.ProductoInventarioDao;
import com.gestion.ikbo_inventario.models.dto.ProductoInventarioDto;
import com.gestion.ikbo_inventario.models.dto.ProductoInvetarioRDto;
import com.gestion.ikbo_inventario.models.dto.ResponseDto;
import com.gestion.ikbo_inventario.models.entities.Estado;
import com.gestion.ikbo_inventario.models.entities.Producto;
import com.gestion.ikbo_inventario.models.entities.ProductoInventario;
import com.gestion.ikbo_inventario.models.service.interfaces.ProductoInventarioService;
import com.gestion.ikbo_inventario.models.service.interfaces.ProductoService;
import com.gestion.ikbo_inventario.utils.ResourceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ProductoInventarioServiceImpl implements ProductoInventarioService {

    @Autowired
    private ProductoInventarioDao productoInventarioDao;

    @Autowired
    private ProductoService productoService;

    @Override
    public ResponseDto guardarProductoInventario(ProductoInventarioDto productoInventario) {
        ResponseDto response = null;

        response = this.validarDtoGuardado(productoInventario);

        if (response != null) {
            return response;
        }

        ProductoInventario newProductoInventario = new ProductoInventario();

        newProductoInventario.setFechaCaduca(ResourceUtil.convertitFechaStringaDate(productoInventario.getFechaCaduca()));
        newProductoInventario.setCantidad(productoInventario.getCantidad());

        Producto producto = new Producto();
        producto.setIdProducto(productoInventario.getIdProducto());
        newProductoInventario.setProducto(producto);

        Estado estado = new Estado();
        estado.setIdEstado(1);
        newProductoInventario.setEstado(estado);

        this.productoInventarioDao.save(newProductoInventario);

        response = new ResponseDto();
        response.setRespuesta(ResourceUtil.productoInventarioExit);
        response.setValor(newProductoInventario.getConsecutivo());

        return response;
    }

    @Override
    public Page<ProductoInvetarioRDto> listarProductoInventario(String nombreProducto, Pageable page) {
        return this.productoInventarioDao.listarProductosInventario(nombreProducto==null?"%":nombreProducto, page);
    }

    public ResponseDto validarDtoGuardado(ProductoInventarioDto productoInventario){
        Date fechaActual;
        Date fechaCaduca = ResourceUtil.convertitFechaStringaDate(productoInventario.getFechaCaduca());

        try{
             fechaActual = ResourceUtil.obtenerFechaActual();
        }catch (Exception e){
            throw new RuntimeException("error al obtener la fecha actual");
        }

        if(fechaCaduca.before(fechaActual)){
            return ResponseDto.builder().respuesta(ResourceUtil.notFechaCaducaProductoInventario).valor(0).build();
        }

        if(productoInventario.getCantidad() <= 0){
            return ResponseDto.builder().respuesta(ResourceUtil.notCantidadProductoInventario).valor(0).build();
        }

        return null;
    }
}
