package com.erickchiquito.kinalapp.controller;

import com.erickchiquito.kinalapp.entity.Usuario;
import com.erickchiquito.kinalapp.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/registro")
    public String registro(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "registro";
    }

    @PostMapping("/registro/guardar")
    public String guardarUsuario(@ModelAttribute("usuario") Usuario usuario) {
       
        usuario.setRol("Vendedor");
        usuario.setEstado(1);


        usuarioRepository.save(usuario);

        return "redirect:/login?success";
    }

}