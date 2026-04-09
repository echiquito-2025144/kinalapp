package com.erickchiquito.kinalapp.service;

import com.erickchiquito.kinalapp.entity.Productos;
import com.erickchiquito.kinalapp.repository.ProductosRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProductoService implements IProductoService{

    private final ProductosRepository productosRepository;

    public ProductoService(ProductosRepository productosRepository){
        this.productosRepository = productosRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Productos> listarTodos(){
        return productosRepository.findAll();
    }

    @Override
    public Productos guardar(Productos producto){
        if(producto.getEstado() ==0 ){
            producto.setEstado(1);
        }
        return productosRepository.save(producto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Productos> buscarPorCodigo(int codigo){
        return productosRepository.findById(codigo);
    }

    @Override
    public Productos actualizar(int codigo, Productos producto){
        if(!productosRepository.existsById(codigo)){
            throw new RuntimeException("Producto no encontrado con codigo " + codigo);
        }
        producto.setCodigoProducto(codigo);
        return productosRepository.save(producto);
    }

    @Override
    public void eliminar(int codigo){
        if(!productosRepository.existsById(codigo)){
            throw new RuntimeException("Producto no encontrado con codigo " + codigo);
        }
        productosRepository.deleteById(codigo);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existePorCodigo(int codigo){
        return productosRepository.existsById(codigo);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Productos> listarActivos(){
        return productosRepository.findByEstado(1);
    }

}
