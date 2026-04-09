package com.erickchiquito.kinalapp.service;

import com.erickchiquito.kinalapp.entity.Productos;

import java.util.List;
import java.util.Optional;

public interface IProductoService {

    //Listar todos los productos
    List<Productos> listarTodos();

    //Guardar producto
    Productos guardar(Productos producto);

    //Buscar por codigo
    Optional<Productos> buscarPorCodigo(int codigo);

    //Listar productos activos
    List<Productos> listarActivos();

    //Actualizar producto
    Productos actualizar(int codigo, Productos producto);

    //Eliminar producto
    void eliminar(int codigo);

    //Verificar si existe
    boolean existePorCodigo(int codigo);
}
