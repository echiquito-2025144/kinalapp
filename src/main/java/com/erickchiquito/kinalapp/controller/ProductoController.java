package com.erickchiquito.kinalapp.controller;

import com.erickchiquito.kinalapp.entity.Productos;
import com.erickchiquito.kinalapp.service.IProductoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/productos")
public class ProductoController {

    private final IProductoService productoService;

    public ProductoController(IProductoService productoService){
        this.productoService = productoService;
    }

    @GetMapping
    public ResponseEntity<List<Productos>> listar(){
        return ResponseEntity.ok(productoService.listarTodos());
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<Productos> buscarPorCodigo(@PathVariable int codigo){
        return productoService.buscarPorCodigo(codigo)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> guardar(@RequestBody Productos producto){
        try{
            Productos nuevo = productoService.guardar(producto);
            return new ResponseEntity<>(nuevo, HttpStatus.CREATED);
        }catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{codigo}")
    public ResponseEntity<Void> eliminar(@PathVariable int codigo){
        try{
            if (!productoService.existePorCodigo(codigo)){
                return ResponseEntity.notFound().build();
            }
            productoService.eliminar(codigo);
            return ResponseEntity.noContent().build();
        }catch (RuntimeException e){
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/activos")
    public ResponseEntity<List<Productos>> listarActivos(){
        return ResponseEntity.ok(productoService.listarActivos());
    } 
}
