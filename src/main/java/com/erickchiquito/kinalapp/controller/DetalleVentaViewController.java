package com.erickchiquito.kinalapp.controller;

import com.erickchiquito.kinalapp.entity.DetalleVenta;
import com.erickchiquito.kinalapp.service.IDetalleVentaService;
import com.erickchiquito.kinalapp.service.IProductoService;
import com.erickchiquito.kinalapp.service.IVentaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/vista/detalles")
public class DetalleVentaViewController {

    private final IDetalleVentaService detalleService;
    private final IVentaService ventaService;
    private final IProductoService productoService;

    public DetalleVentaViewController(IDetalleVentaService detalleService, IVentaService ventaService, IProductoService productoService) {
        this.detalleService = detalleService;
        this.ventaService = ventaService;
        this.productoService = productoService;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("detalles", detalleService.findAll());
        return "detalles";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("detalle", new DetalleVenta());
        model.addAttribute("ventas", ventaService.findAll());
        model.addAttribute("productos", productoService.listarTodos());
        return "detalle-form";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute("detalle") DetalleVenta detalle) {
        detalleService.save(detalle);
        return "redirect:/vista/detalles";
    }
}