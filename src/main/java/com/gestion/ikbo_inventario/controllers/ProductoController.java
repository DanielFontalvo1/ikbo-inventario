package com.gestion.ikbo_inventario.controllers;

import com.gestion.ikbo_inventario.models.dto.ProductoDto;
import com.gestion.ikbo_inventario.models.dto.ResponseDto;
import com.gestion.ikbo_inventario.models.service.interfaces.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value="/api/productos")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @PostMapping("/guardar-producto")
    public ResponseEntity<?> guardarProducto(@RequestBody ProductoDto producto){
        Map<String, Object> responseError = new HashMap<>();
        ResponseDto response = new ResponseDto();

        try{
            response = this.productoService.guardarProducto(producto);
        }catch (Exception e){
            responseError.put("mensaje", "Error al insertar en la base de datos");
            responseError.put("error", e.getMessage());
            return new ResponseEntity<Map<String, Object>>(responseError, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return ResponseEntity.ok(response);
    }

    @GetMapping("/consultar-producto")
    public ResponseEntity<?> consultarProductoPorIdOrNombre(@RequestParam(required = false, defaultValue = "0") int idProducto,
                                                            @RequestParam(required = false) String nombreProducto){
        return ResponseEntity.ok(this.productoService.consultarProductoxNombreOrIdProducto(idProducto, nombreProducto));
    }

    @GetMapping("/listar-productos")
    public ResponseEntity<?> listarProductos(@RequestParam(required = false, defaultValue = "0") int idProducto,
                                             @RequestParam(required = false) String nombreProducto,
                                             @PageableDefault(page = 0, size = 5)Pageable page){
        return ResponseEntity.ok(this.productoService.listarProductos(idProducto,nombreProducto,page ));
    }

}
