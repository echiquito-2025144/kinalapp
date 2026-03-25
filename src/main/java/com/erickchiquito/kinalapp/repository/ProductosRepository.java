package com.erickchiquito.kinalapp.repository;

import com.erickchiquito.kinalapp.entity.Productos;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductosRepository extends JpaRepository<Productos, Integer> {
    List<Productos>findByEstado(int estado);

}
