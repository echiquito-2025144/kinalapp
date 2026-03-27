package com.erickchiquito.kinalapp.entity;


import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "DetalleVenta")
public class DetalleVenta {

    @Id
    @Column(name = "codigo_detalle_venta")
    private int codigoDetalleVenta;

    @Column(name = "cantidad")
    private int cantidad;

    @Column(name = "precio_unitario")
    private BigDecimal precioUnitario;

    @Column(name = "subtotal")
    private BigDecimal subtotal;

    @ManyToOne
    @JoinColumn(name = "ventas_codigo_venta")
    private Venta venta;

    @ManyToOne
    @JoinColumn(name = "productos_codigo_producto")
    private Productos productos;

    public DetalleVenta() {
    }

    public DetalleVenta(int codigoDetalleVenta, int cantidad, BigDecimal precioUnitario, BigDecimal subtotal, Venta venta, Productos productos) {
        this.codigoDetalleVenta = codigoDetalleVenta;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.subtotal = subtotal;
        this.venta = venta;
        this.productos = productos;
    }

    public int getCodigoDetalleVenta() {
        return codigoDetalleVenta;
    }

    public void setCodigoDetalleVenta(int codigoDetalleVenta) {
        this.codigoDetalleVenta = codigoDetalleVenta;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public BigDecimal getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(BigDecimal precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }

    public Venta getVenta() {
        return venta;
    }

    public void setVenta(Venta venta) {
        this.venta = venta;
    }

    public Productos getProductos() {
        return productos;
    }

    public void setProductos(Productos productos) {
        this.productos = productos;
    }
}
