package com.formacionbdi.app.item.servicioitem.models.services;

import com.formacionbdi.app.item.servicioitem.models.Item;
import com.formacionbdi.springboot.app.commons.models.entity.Producto;

import java.util.List;

public interface IItemService {
    public List<Item> findAll();
    public Item findById(Long id, Integer cantidad);
    public Producto save(Producto producto);
    public Producto edit(Producto producto, Long id);
    public void delete(Long id);
}
