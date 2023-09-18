package com.fomracionbdi.app.productos.springservicioproductos.models.dao;

import com.formacionbdi.springboot.app.commons.models.entity.Producto;
import org.springframework.data.repository.CrudRepository;

public interface IProductoDao extends CrudRepository<Producto, Long> {

}