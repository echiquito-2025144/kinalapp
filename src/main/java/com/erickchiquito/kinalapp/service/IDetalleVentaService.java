package com.erickchiquito.kinalapp.service;

import com.erickchiquito.kinalapp.entity.DetalleVenta;

import java.util.List;
import java.util.Optional;

public interface IDetalleVentaService {
    List<DetalleVenta> findAll();
    Optional<DetalleVenta> findById(int id);
    DetalleVenta save(DetalleVenta detalleVenta);
    void deleteById(int id);
}
