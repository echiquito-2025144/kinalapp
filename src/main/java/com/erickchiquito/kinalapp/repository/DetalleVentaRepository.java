package com.erickchiquito.kinalapp.repository;

import com.erickchiquito.kinalapp.entity.DetalleVenta;
import com.sun.jdi.connect.Connector;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetalleVentaRepository extends JpaRepository<DetalleVenta, Integer> {
    
}
