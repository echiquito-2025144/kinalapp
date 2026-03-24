package com.erickchiquito.kinalapp.service;

import com.erickchiquito.kinalapp.entity.Cliente;

import java.util.List;
import java.util.Optional;

public interface IClienteService {
    /*
    *Interfaz: Es un contrato que dice QUÉ métodos debe tener
    * cualquier servicio de Clientes, No tiene
    * Implementación, solo la definición de los métodos
     **/

    //Metodo que devuelve una lista de todos los Clientes
    List<Cliente> ListarTodos();
    /*
    *List<Cliente> lo que hace es devolver una lista
    * de objetos de la entidad Clientes
     **/

    //Meotodo que guarda un cliente en la BD
    Cliente guardar(Cliente cliente);
    //Paramteros: Recibe in objeto Cliente con los datos a
    //guardar

    //Optional - Contenedor que puede o no tener valor
    //evita el error de NullPointerException
    Optional<Cliente> buscarPorDPI(String dpi);

    List<Cliente> listarActivos();

    //Metodo que actualiza un CLiente
    Cliente actualizar(String dpi, Cliente cliente);
    /*
    * Parametros - dpi: DPI del cliente a actualizar
    * CLiente cliente: objeto con los datos nuevos
    * Retorna un objeto de tipo CLiente ya actualizado
    **/

    /*
    * Metodo de tipo void para eliminar a un Cliente
    * void: no retorna ningun valor o dato
    * Elimina un CLiente  por su DPI
    **/
    void eliminar(String dpi);

    //boolean - Retorna true si existe y false si no existe
    boolean existePorDPI(String dpi);


}
