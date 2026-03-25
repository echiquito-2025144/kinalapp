package com.erickchiquito.kinalapp.entity;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table( name = "Ventas")
public class Venta {
    @Id
    @Column(name = "Ventas")
    private int codigoVenta;
    @Column
    private LocalDate fechaVenta;
    @Column
    private BigDecimal total;
    @Column
    private int estado;

    @ManyToOne
    @JoinColumn( name = "Clientes_dpi_Cliente")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn( name = "Usuarios_codigo_usuario")
    private Usuario usuario;

    public Venta() {}

    public Venta(int codigoVenta, LocalDate fechaVenta, BigDecimal total, int estado, Cliente cliente, Usuario usuario) {
        this.codigoVenta = codigoVenta;
        this.fechaVenta = fechaVenta;
        this.total = total;
        this.estado = estado;
        this.cliente = cliente;
        this.usuario = usuario;
    }

    public int getCodigoVenta() {
        return codigoVenta;
    }

    public void setCodigoVenta(int codigoVenta) {
        this.codigoVenta = codigoVenta;
    }

    public LocalDate getFechaVenta() {
        return fechaVenta;
    }

    public void setFechaVenta(LocalDate fechaVenta) {
        this.fechaVenta = fechaVenta;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
