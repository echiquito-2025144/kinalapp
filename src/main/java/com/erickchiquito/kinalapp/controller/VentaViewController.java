package com.erickchiquito.kinalapp.controller;

import com.erickchiquito.kinalapp.entity.Venta;
import com.erickchiquito.kinalapp.service.IClienteService;
import com.erickchiquito.kinalapp.service.IUsuarioService;
import com.erickchiquito.kinalapp.service.IVentaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;

@Controller
@RequestMapping("/vista/ventas")
public class VentaViewController {

    private final IVentaService ventaService;
    private final IClienteService clienteService;
    private final IUsuarioService usuarioService;

    public VentaViewController(IVentaService ventaService, IClienteService clienteService, IUsuarioService usuarioService) {
        this.ventaService = ventaService;
        this.clienteService = clienteService;
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("ventas", ventaService.findAll());
        return "ventas";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        Venta venta = new Venta();
        venta.setFechaVenta(LocalDate.now());
        model.addAttribute("venta", venta);
        model.addAttribute("clientes", clienteService.ListarTodos());
        model.addAttribute("usuarios", usuarioService.listarTodos());
        return "venta-form";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute("venta") Venta venta) {
        // Solo permitimos guardar ventas nuevas
        ventaService.save(venta);
        return "redirect:/vista/ventas";
    }
}