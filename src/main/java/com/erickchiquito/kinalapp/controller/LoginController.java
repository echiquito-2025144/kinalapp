package com.erickchiquito.kinalapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String login() {
        return "login"; // Busca login.html en templates
    }

    @PostMapping("/login")
    public String autenticar(@RequestParam String username,
                             @RequestParam String password,
                             Model model) {

        if ("admin".equals(username) && "1234".equals(password)) {
            return "redirect:/"; 
        } else {
            model.addAttribute("error", "Credenciales incorrectas. Intente de nuevo.");
            return "login";
        }
    }
}