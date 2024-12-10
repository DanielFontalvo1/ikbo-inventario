package com.gestion.ikbo_inventario.controllers;

import com.gestion.ikbo_inventario.models.dto.ProductoInventarioDto;
import com.gestion.ikbo_inventario.models.dto.ResponseDto;
import com.gestion.ikbo_inventario.models.service.interfaces.ProductoInventarioService;
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
@RequestMapping(value = "/api/producto-inventario")
public class ProductoInventarioController {

    @Autowired
    private ProductoInventarioService productoInventarioService;

    @PostMapping("/guardar-producto-inventario")
    public ResponseEntity<?> guardarProductoInventario(@RequestBody ProductoInventarioDto productoInventario){
        Map<String, Object> responseError = new HashMap<>();
        ResponseDto response = new ResponseDto();

        try{
            response = this.productoInventarioService.guardarProductoInventario(productoInventario);
        }catch (Exception e){
            responseError.put("mensaje", "Error al insertar en la base de datos");
            responseError.put("error", e.getMessage());
            return new ResponseEntity<Map<String, Object>>(responseError, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return ResponseEntity.ok(response);
    }

    @GetMapping("/listar-producto-inventario")
    public ResponseEntity<?> listarProductoInvetario(@RequestParam(required = false) String nombreProducto,
                                                     @PageableDefault(page = 0, size = 5) Pageable page){
        return ResponseEntity.ok(this.productoInventarioService.listarProductoInventario(nombreProducto, page));
    }

    @PostMapping("/actualizar-producto-invetario")
    public ResponseEntity<?> actualizarProductoInvetario(@RequestBody ProductoInventarioDto productoInventario){
        Map<String, Object> responseError = new HashMap<>();
        ResponseDto response = new ResponseDto();

        try{
            response = this.productoInventarioService.actualizarProductoInventario(productoInventario);
        }catch (Exception e){
            responseError.put("mensaje", "Error al insertar en la base de datos");
            responseError.put("error", e.getMessage());
            return new ResponseEntity<Map<String, Object>>(responseError, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return ResponseEntity.ok(response);
    }

    @PostMapping("/inactivar-producto-inventario")
    public ResponseEntity<?> inactivarProductoInvetario(@RequestBody ProductoInventarioDto productoInventario){
        Map<String, Object> responseError = new HashMap<>();
        ResponseDto response = new ResponseDto();

        try{
            response = this.productoInventarioService.inactivarProductoEnInventario(productoInventario);
        }catch (Exception e){
            responseError.put("mensaje", "Error al insertar en la base de datos");
            responseError.put("error", e.getMessage());
            return new ResponseEntity<Map<String, Object>>(responseError, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return ResponseEntity.ok(response);
    }
}
