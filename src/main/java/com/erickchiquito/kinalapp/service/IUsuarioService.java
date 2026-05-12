package com.erickchiquito.kinalapp.service;

import com.erickchiquito.kinalapp.entity.Usuario;

import java.util.List;
import java.util.Optional;

public interface IUsuarioService {

    List<Usuario> listarTodos();

    Usuario guardar(Usuario usuario);

    Optional<Usuario> buscarPorCodigo(int codigoUsuario);

    List<Usuario> listarActivos();

    Usuario actualizar(int codigoUsuario, Usuario usuario);

    void eliminar(int codigoUsuario);

    boolean existePorCodigo(int codigoUsuario);

    Usuario actualizarRol(int codigoUsuario, String nuevoRol);
}
