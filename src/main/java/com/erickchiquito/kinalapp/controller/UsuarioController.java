package com.erickchiquito.kinalapp.controller;

import com.erickchiquito.kinalapp.entity.Usuario;
import com.erickchiquito.kinalapp.service.IUsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios") // Todas las rutas empiezan con /usuarios
public class UsuarioController {
    private final IUsuarioService usuarioService;
    public UsuarioController(IUsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }
    // Listar todos los usuarios
    @GetMapping
    public ResponseEntity<List<Usuario>> listar() {
        return ResponseEntity.ok(usuarioService.listarTodos());
    }
    // Buscar usuario por codigoUsuario
    @GetMapping("/{codigoUsuario}")
    public ResponseEntity<Usuario> buscarPorCodigo(@PathVariable int codigoUsuario) {
        return usuarioService.buscarPorCodigo(codigoUsuario)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    // Guardar un nuevo usuario
    @PostMapping
    public ResponseEntity<?> guardar(@RequestBody Usuario usuario) {
        try {
            Usuario nuevo = usuarioService.guardar(usuario);
            return new ResponseEntity<>(nuevo, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    // Actualizar usuario existente
    @PutMapping("/{codigoUsuario}")
    public ResponseEntity<?> actualizar(@PathVariable int codigoUsuario, @RequestBody Usuario usuario) {
        try {
            if (!usuarioService.existePorCodigo(codigoUsuario)) {
                return ResponseEntity.notFound().build();
            }
            Usuario actualizado = usuarioService.actualizar(codigoUsuario, usuario);
            return ResponseEntity.ok(actualizado);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    // Eliminar usuario
    @DeleteMapping("/{codigoUsuario}")
    public ResponseEntity<Void> eliminar(@PathVariable int codigoUsuario) {
        try {
            if (!usuarioService.existePorCodigo(codigoUsuario)) {
                return ResponseEntity.notFound().build();
            }
            usuarioService.eliminar(codigoUsuario);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    // Listar usuarios activos
    @GetMapping("/activos")
    public ResponseEntity<List<Usuario>> listarActivos() {
        return ResponseEntity.ok(usuarioService.listarActivos());
    }
}
