package com.erickchiquito.kinalapp.service;

import com.erickchiquito.kinalapp.entity.Cliente;
import com.erickchiquito.kinalapp.repository.ClienteRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;



/**
 * Anotación que registra un Bean como un Bean de Spring
 * Que la clase contiene la lógica del negocio
 */
@Service
/*
*Por defecto todos los métodos de esta clase serán transaccionales
* Una transcaccióin es que pude o no ocurrir algo
 */
@Transactional
public class ClienteService implements IClienteService{
    /*
     * private: solo es accesible dentro de la misma clase
     * final: No puede cambiar, porque es constante
     * ClienteRepository: Es el repositorio para acceder a la BD
     * Inyección de Dependencia ya que Spring nos da el repositorio
     **/
    private final ClienteRepository clienteRepository;

    /*
    * Constructor: este se ejecuta al crear un objeto
    * Spring pasa el repositorio automáticamente (Inyección de Dependencias)
     */

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
        //Asignar el repositorio a nuestra variable de clase
    }

    //Indica que se está implementando un metodo de la interfaz
    @Override
    // Optimizar la consulta, solo lectura, para que no bloquee la BD
    @Transactional(readOnly = true)
    public List<Cliente> ListarTodos() {
        return clienteRepository.findAll();
        //findAll() es un metodo de Spring que hace el select * from Clientes
        //Este metodo es de JpaRepository
    }



    @Override
    public Cliente guardar(Cliente cliente) {
        /**
         * Metodo guardar, crea un Cliente
         * Aca es donde colocamos la logica del negocio antes de guardar
         * Pero primero validamos el dato
         */
        validarCliente(cliente);
        if (cliente.getEstado() == 0)
            cliente.setEstado(1);
        return clienteRepository.save(cliente);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Cliente> buscarPorDPI(String dpi) {
        //Buscar un cliente por DPI
        return clienteRepository.findById(dpi);
        //Optional nos evita el NullPointerException
    }

    @Override
    public Cliente actualizar(String dpi, Cliente cliente) {
        //Metodo para actualizar un cliente existente
        if (!clienteRepository.existsById(dpi)){
            throw new RuntimeException("El cliente no se encontró con el DPI"+dpi);
            //Si no existe se lanza una excepcion (error controlado)
        }
        cliente.setDPICliente(dpi);
        //Asegurarnos que el DPI del objeto coincida con el de la URL
        //Por seguridad usamos el DPI de la URL y no el que viene en el JSON
        validarCliente(cliente);

        return clienteRepository.save(cliente);
        /*
        *save() este no solo sirve para guardar sino tambien para actualizar. Si el dato
        * existe (dpi) entonces hace UPDATE pero si no existe hace un INSERT pero
        * antes verificamos si existe o no el reguistro
        */

    }

    @Override
    public void eliminar(String dpi) {
        //Eliminar un cliente
        if(!clienteRepository.existsById(dpi)){
            throw new RuntimeException("El cliente no se encontró con el DPI"+dpi);
        }
        clienteRepository.deleteById(dpi);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existePorDPI(String dpi) {
        //Verificar si existe un cliente
        return clienteRepository.existsById(dpi);
    }

    //Metodo privado(solo puede utilizarse dentro de la clase)
    private void validarCliente(Cliente cliente){
        /*
        *Validaciones del negocio: este metodo se hara privado porque es
        * algo interno del servicio
        */
        if(cliente.getDPICliente() == null || cliente.getDPICliente().trim().isEmpty()){
            //Si el DPI es null o esta vacio despues de quitar espacios
            //Lanza una excepcion con un mensaje
            throw new IllegalArgumentException("El DPI es un dato obligatorio");
        }
        if (cliente.getNombreCliente()==null || cliente.getNombreCliente().trim().isEmpty()){
            throw new IllegalArgumentException("El nombre es un dato obligatorio");
        }
        if (cliente.getApellidoCliente()==null || cliente.getApellidoCliente().trim().isEmpty()){
            throw new IllegalArgumentException("El apellido es un dato obligatorio");
        }

    }

    @Override
    @Transactional(readOnly = true)
    public List<Cliente> listarActivos() {
        return clienteRepository.findByEstado(1);
    }

}
