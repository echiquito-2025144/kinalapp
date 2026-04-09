package com.erickchiquito.kinalapp.repository;

import com.erickchiquito.kinalapp.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UsuarioRepository extends JpaRepository<Usuario,Integer> {
    List<Usuario> findByEstado(int estado);
}
