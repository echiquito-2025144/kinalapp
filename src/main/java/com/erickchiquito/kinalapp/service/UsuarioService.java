package com.erickchiquito.kinalapp.service;


import com.erickchiquito.kinalapp.entity.Usuario;
import com.erickchiquito.kinalapp.repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UsuarioService implements IUsuarioService{

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository){
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Usuario> listarTodos(){
        return usuarioRepository.findAll();
    }

    @Override
    public Usuario guardar(Usuario usuario){
        return usuarioRepository.save(usuario);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Usuario> buscarPorCodigo(int codigoUsuario){
        return usuarioRepository.findById(codigoUsuario);
    }

    @Override
    public Usuario actualizar(int codigoUsuario, Usuario usuario){
        if (!usuarioRepository.existsById(codigoUsuario))
            throw new RuntimeException("Usuario no encontrado con codigo" + codigoUsuario);
        usuario.setCodigoUsuario(codigoUsuario);
        return usuarioRepository.save(usuario);
    }

    @Override
    public void eliminar (int codigoUsuario){
        if (!usuarioRepository.existsById(codigoUsuario))
            throw new RuntimeException("Usuario no encontrado con codigo " + codigoUsuario);
        usuarioRepository.deleteById(codigoUsuario);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existePorCodigo(int codigoUsuario){
        return usuarioRepository.existsById(codigoUsuario);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Usuario> listarActivos(){
        return usuarioRepository.findByEstado(1);
    }

}
