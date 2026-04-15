package com.erickchiquito.kinalapp.controller;


import com.erickchiquito.kinalapp.entity.Usuario;
import com.erickchiquito.kinalapp.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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

    @PostMapping("/login")
    public String autenticar(@RequestParam String username,
                             @RequestParam String password,
                             Model model) {

        Usuario user = usuarioRepository.findByUsername(username);

        if (user != null && user.getPassword().equals(password)) {
            return "redirect:/";
        } else {
            model.addAttribute("error", "Credenciales incorrectas. Verifique su usuario y contraseña.");
            return "login";
        }
    }


    @GetMapping("/registro")
    public String registro() {
        return "registro";
    }

    @PostMapping("/registro/guardar")
    public String guardarUsuario(@RequestParam String username,
                                 @RequestParam String password,
                                 @RequestParam String email) {

        Usuario nuevoUsuario = new Usuario();
        nuevoUsuario.setUsername(username);
        nuevoUsuario.setPassword(password);
        nuevoUsuario.setEmail(email);
        nuevoUsuario.setRol("Vendedor");
        nuevoUsuario.setEstado(1);

        usuarioRepository.save(nuevoUsuario);

        return "redirect:/login?success";
    }
}