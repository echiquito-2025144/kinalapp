package com.erickchiquito.kinalapp.controller;

import com.erickchiquito.kinalapp.entity.Cliente;
import com.erickchiquito.kinalapp.repository.ClienteRepository;
import com.erickchiquito.kinalapp.service.IClienteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.parser.Entity;
import java.util.List;

@RestController
//@RestController = @Controller + @RequestBody
@RequestMapping("/clientes")
//Todas las rutas en este controlador deben empezar por /clientes
public class ClienteController {
    //Inyectamos el SERVICIO y NO el rpositorio
    //El controlador solo debe tener conexion con el servicio
    private final IClienteService clienteService;
    //Como buena practica la inyeccion de dependencias debe hacerse por el constructor
    public ClienteController(IClienteService clienteService) {
        this.clienteService = clienteService;
    }

   //Responde peticiones GET
    @GetMapping
    //ResponseEntity nos permite controlar el codigo HTTP y el cuerpo
    public ResponseEntity<List<Cliente>> listar(){
        List<Cliente> clientes = clienteService.ListarTodos();
        //delegar al servicio
        return ResponseEntity.ok(clientes);
        // 200 ok con la lista de clientes
    }

    //{dpi} es ina variable de ruta(ruta a buscar)
    @GetMapping("/{dpi}")
    public ResponseEntity<Cliente> buscarPorDPI(@PathVariable String dpi){
        //@PathVariable Toma el valor de la URL y lo asigna al dpi
        return clienteService.buscarPorDPI(dpi)
                //Si Optional tiene valor, devuelve el 200 ok con el cliente
                .map(ResponseEntity::ok)
                //Si Optional esta vacio, devuelve 404 Not FOUND
                .orElse(ResponseEntity.notFound().build());
    }

    //POST
    @PostMapping
    public ResponseEntity<?> guardar(@RequestBody Cliente cliente){
        //@ResquestBody: Toma el JSON del cuerpo y lo convierte a un objeto de tipo CLiente
        //<?> significa: "Tipo generico" que puede ser un Cliente o un String
        try{
            Cliente nuevoCliente = clienteService.guardar(cliente);
            //Intentamos guradar el cliente pero puede lanzar una exception
            //De illegalArgument Exception
            return new ResponseEntity<>(nuevoCliente, HttpStatus.CREATED);
            //201 CREATED(mucho mas especifico que el 200 para la creacion de un cliente)
        }catch(IllegalArgumentException e){
            //SI hay error de validacion
            // 400 BAD REQUEST con el mensaje de error
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    //DELETE elimina un cliente
    @DeleteMapping("/{dpi}")
    public ResponseEntity<Void> eliminar(@PathVariable String dpi){
        //ResponseEntity<Void>: No devuelve cuerpo en la respuesta
        try{
            if(!clienteService.existePorDPI(dpi)){
                return ResponseEntity.notFound().build();
                //404 si no existe
            }
            clienteService.eliminar(dpi);
            return ResponseEntity.noContent().build();
            //204 NO CONTENT (se ejecuto correctamente y no devuelve cuerpo)

        }catch(RuntimeException e){
            return ResponseEntity.notFound().build();
            //404 NOT FOUND
        }
    }

    //Actualizar cliente a traves del DPI
    @PutMapping("/{dpi}")
    public ResponseEntity<?> actualizar(@PathVariable String dpi, @RequestBody Cliente cliente){
        try{
            if (!clienteService.existePorDPI(dpi)){
                //Verificar si existe antes de poder actualizar
                return ResponseEntity.notFound().build();
                //404 Not Found
            }
            //Actualizamos el cliente pero esto puede lanzar una exception
            Cliente clienteActualizado = clienteService.actualizar(dpi, cliente);
            return ResponseEntity.ok(clienteActualizado);
            //200 ok con el cliente ya actualizado
        }catch (IllegalArgumentException e){
            //Error cuando los datos sean incorrectos
            return ResponseEntity.badRequest().body(e.getMessage());
        }catch (RuntimeException e){
            //Posiblemente cualquier otro error como: cliente no encontrado, etc.
            //404 NOT FOUND
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/activos")
    public ResponseEntity<List<Cliente>> listarActivos(){
        List<Cliente> clientesActivos = clienteService.listarActivos();
        return ResponseEntity.ok(clientesActivos);
    }

}
