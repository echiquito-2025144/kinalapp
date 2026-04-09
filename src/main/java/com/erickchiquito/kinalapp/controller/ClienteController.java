package com.erickchiquito.kinalapp.controller;

import com.erickchiquito.kinalapp.entity.Cliente;
import com.erickchiquito.kinalapp.repository.ClienteRepository;
import com.erickchiquito.kinalapp.service.IClienteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.parser.Entity;
import java.util.List;

@Controller
@RequestMapping("/vista/clientes")
public class ClienteController {

    private final IClienteService clienteService;

    public ClienteController(IClienteService clienteService) {
        this.clienteService = clienteService;
    }


    @GetMapping
    public String listar(Model model) {
        model.addAttribute("clientes", clienteService.ListarTodos());
        return "clientes";
    }


    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("cliente", new Cliente());
        return "cliente-form"; // cliente-form.html en templates
    }

   
    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Cliente cliente) {
        clienteService.guardar(cliente);
        return "redirect:/vista/clientes";
    }

}