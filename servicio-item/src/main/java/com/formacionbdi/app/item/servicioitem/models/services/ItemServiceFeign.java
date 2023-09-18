package com.formacionbdi.app.item.servicioitem.models.services;

import com.formacionbdi.app.item.servicioitem.clientes.ProductoClienteFeign;
import com.formacionbdi.app.item.servicioitem.models.Item;
import com.formacionbdi.springboot.app.commons.models.entity.Producto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service("serviceFeign")
public class ItemServiceFeign implements IItemService{

    @Autowired
    private ProductoClienteFeign clienteFeign;

    @Override
    public List<Item> findAll() {
        return clienteFeign.list().stream().map(p -> new Item(p, 1))
                .collect(Collectors.toList());
    }

    @Override
    public Item findById(Long id, Integer cantidad) {
        return new Item(clienteFeign.detail(id), cantidad);
    }

	@Override
	public Producto save(Producto producto) {
		return clienteFeign.save(producto);
	}

	@Override
	public Producto edit(Producto producto, Long id) {
		return clienteFeign.edit(producto, id);
	}

	@Override
	public void delete(Long id) {
		clienteFeign.delete(id);
	}
}
