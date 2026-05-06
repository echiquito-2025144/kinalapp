package com.erickchiquito.kinalapp.service;

import com.erickchiquito.kinalapp.entity.Venta;

import java.util.List;
import java.util.Optional;

public interface IVentaService {
    List<Venta> findAll();
    Optional<Venta> findById(int id);
    Venta save(Venta venta);
    void deleteById(int id);
}
