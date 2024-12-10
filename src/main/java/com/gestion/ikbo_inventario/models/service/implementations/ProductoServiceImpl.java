package com.gestion.ikbo_inventario.models.service.implementations;

import com.gestion.ikbo_inventario.models.daos.ProductoDao;
import com.gestion.ikbo_inventario.models.dto.ProductoDto;
import com.gestion.ikbo_inventario.models.dto.ResponseDto;
import com.gestion.ikbo_inventario.models.entities.Estado;
import com.gestion.ikbo_inventario.models.entities.Producto;
import com.gestion.ikbo_inventario.models.service.interfaces.ProductoService;
import com.gestion.ikbo_inventario.utils.ResourceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;

@Service
public class ProductoServiceImpl implements ProductoService{

    @Autowired
    private ProductoDao productoDao;

    @Override
    @Transactional
    public ResponseDto guardarProducto(ProductoDto producto) {
        ResponseDto response = null;

        response = this.validarDtoDeGuardado(producto);

        if(response != null){
            return response;
        }

        Producto newProducto = new Producto();

        newProducto.setNombreProducto(producto.getNombreProducto());
        newProducto.setPrecioUnitario(producto.getPrecioUnitario());

        try {
            newProducto.setFechaRegistro(ResourceUtil.obtenerFechaActual());
        } catch (ParseException e) {
            throw new RuntimeException("error al obtener la fecha actual");
        }

        Estado estado = new Estado();
        estado.setIdEstado(1);
        newProducto.setEstado(estado);

        this.productoDao.save(newProducto);

        response = new ResponseDto();
        response.setRespuesta(ResourceUtil.productExit);
        response.setValor(newProducto.getIdProducto());

        return response;
    }

    @Override
    public Producto consultarProductoxNombreOrIdProducto(int idproducto, String nombreProducto) {
        return this.productoDao.getProductoByIdProductoOrNombreProducto(idproducto, nombreProducto);
    }

    @Override
    public Page<Producto> listarProductos(int idProducto, String nombreProducto, Pageable page) {
        return this.productoDao.getAllByIdProductoAndNombreProducto(idProducto,
                nombreProducto == null? "%": nombreProducto, page);
    }

    public ResponseDto validarDtoDeGuardado(ProductoDto producto){
        if(producto.getNombreProducto()==null || producto.getNombreProducto().isEmpty()){
            return ResponseDto.builder().respuesta(ResourceUtil.notProductNombre).valor(0).build();
        }
        if(producto.getPrecioUnitario() < 0){
            return ResponseDto.builder().respuesta(ResourceUtil.notProductoPrecioUnitario).valor(0).build();
        }
        return null;
    }
}
