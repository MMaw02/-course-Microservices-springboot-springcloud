package com.fomracionbdi.app.productos.springservicioproductos.models.service;

import com.formacionbdi.springboot.app.commons.models.entity.Producto;

import java.util.List;

public interface IProductService {
    public List<Producto> findAll();
    public Producto findById(Long id);
    public Producto save(Producto producto);
    public void deleteById(Long id);
}
