package com.erickchiquito.kinalapp.controller;


import com.erickchiquito.kinalapp.entity.DetalleVenta;
import com.erickchiquito.kinalapp.service.IDetalleVentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/detalleVentas")
public class DetalleVentaController {

    private final IDetalleVentaService detalleVentaService;

    @Autowired
    public DetalleVentaController(IDetalleVentaService detalleVentaService){
        this.detalleVentaService = detalleVentaService;
    }

    @GetMapping
    public List<DetalleVenta> listar(){
        return detalleVentaService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalleVenta> obtenerPorId(@PathVariable int id){
        return detalleVentaService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<DetalleVenta> guardar(@RequestBody DetalleVenta detalleVenta){
        DetalleVenta nuevoDetalle = detalleVentaService.save(detalleVenta);
        return ResponseEntity.ok(nuevoDetalle);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DetalleVenta> actualizar(@PathVariable int id, @RequestBody DetalleVenta detalleVenta){
        Optional<DetalleVenta> existente = detalleVentaService.findById(id);

        if (existente.isPresent()){
            detalleVenta.setCodigoDetalleVenta(id);
            DetalleVenta actualizado = detalleVentaService.save(detalleVenta);
            return ResponseEntity.ok(actualizado);
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable int id){
        if (detalleVentaService.findById(id).isPresent()){
            detalleVentaService.deleteById(id);
            return ResponseEntity.noContent().build();
        }else {
            return ResponseEntity.notFound().build();
        }
    }
}
