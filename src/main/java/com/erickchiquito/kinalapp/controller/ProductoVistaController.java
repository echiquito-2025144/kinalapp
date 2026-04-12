package com.erickchiquito.kinalapp.controller;

import com.erickchiquito.kinalapp.entity.Productos;
import com.erickchiquito.kinalapp.service.IProductoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/vista/productos")
public class ProductoVistaController {

    private final IProductoService productoService;

    public ProductoVistaController(IProductoService productoService){
        this.productoService = productoService;
    }

    @GetMapping
    public String listar(Model model){
        model.addAttribute("productos", productoService.listarTodos());
        return "productos";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("producto", new Productos());
        return "productos-form";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute("producto") Productos producto) {
        productoService.guardar(producto);
        return "redirect:/vista/productos";
    }
}