package com.erickchiquito.kinalapp.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table( name = "Ventas")
public class Venta {
    @Id
    @Column(name = "Ventas")
    private int codigoVenta;
    @Column
    private DateTimeFormat fechaVenta;
    @Column
    private long total;
    @Column
    private int estado;

    public Venta() {
    }

    public Venta(int codigoVenta, DateTimeFormat fechaVenta, long total, int estado) {
        this.codigoVenta = codigoVenta;
        this.fechaVenta = fechaVenta;
        this.total = total;
        this.estado = estado;
    }

    public int getCodigoVenta() {
        return codigoVenta;
    }

    public void setCodigoVenta(int codigoVenta) {
        this.codigoVenta = codigoVenta;
    }

    public DateTimeFormat getFechaVenta() {
        return fechaVenta;
    }

    public void setFechaVenta(DateTimeFormat fechaVenta) {
        this.fechaVenta = fechaVenta;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }
}
