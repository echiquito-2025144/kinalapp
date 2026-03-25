package com.erickchiquito.kinalapp.repository;

import com.erickchiquito.kinalapp.entity.Productos;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductosRepository extends JpaRepository<Productos,String> {
}
