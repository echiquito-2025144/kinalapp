package com.erickchiquito.kinalapp.service;

import com.erickchiquito.kinalapp.entity.DetalleVenta;
import com.erickchiquito.kinalapp.repository.DetalleVentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DetalleVentaService implements IDetalleVentaService{

    private final DetalleVentaRepository detalleVentaRepository;

    @Autowired
    public DetalleVentaService(DetalleVentaRepository detalleVentaRepository){
        this.detalleVentaRepository = detalleVentaRepository;
    }

    @Override
    public List<DetalleVenta> findAll(){
        return detalleVentaRepository.findAll();
    }

    @Override
    public Optional<DetalleVenta> findById(int id){
        return detalleVentaRepository.findById(id);
    }

    @Override
    public DetalleVenta save(DetalleVenta detalleVenta){
        return detalleVentaRepository.save(detalleVenta);
    }

    @Override
    public void deleteById(int id){
        detalleVentaRepository.deleteById(id);
    }
}
