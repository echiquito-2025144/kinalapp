package com.erickchiquito.kinalapp.service;


import com.erickchiquito.kinalapp.entity.Venta;
import com.erickchiquito.kinalapp.repository.VentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VentaService implements IVentaService{

    private final VentaRepository ventaRepository;

    @Autowired
    public VentaService(VentaRepository ventaRepository){
        this.ventaRepository = ventaRepository;
    }

    @Override
    public List<Venta> findAll(){
        return ventaRepository.findAll();
    }

    @Override
    public Optional<Venta> findById(int id){
        return ventaRepository.findById(id);
    }

    @Override
    public Venta save(Venta venta){
        return ventaRepository.save(venta);
    }

    @Override
    public void deleteById(int id){
        ventaRepository.deleteById(id);
    }

}
