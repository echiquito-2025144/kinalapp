package com.erickchiquito.kinalapp.controller;

import com.erickchiquito.kinalapp.entity.Cliente;
import com.erickchiquito.kinalapp.service.IClienteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/vista/clientes")
public class ClienteViewController {

    private final IClienteService clienteService;

    public ClienteViewController(IClienteService clienteService) {
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
        return "cliente-form";
    }

    // Guardar el cliente y redirigir
    @PostMapping("/guardar")
    public String guardar(@ModelAttribute("cliente") Cliente cliente) {
        clienteService.guardar(cliente);
        return "redirect:/vista/clientes";
    }
}