package com.formacionbdi.app.item.servicioitem.models.services;

import com.formacionbdi.app.item.servicioitem.models.Item;
import com.formacionbdi.springboot.app.commons.models.entity.Producto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ItemServiceImpl implements IItemService{

    @Autowired
    private RestTemplate clienteRest;

    @Override
    public List<Item> findAll() {
        List<Producto> productos = Arrays.asList(clienteRest.getForObject(
                "http://servicio-productos/product/list",
                Producto[].class));

        return productos.stream()
                .map(p -> new Item(p, 1))
                .collect(Collectors.toList());
    }

    @Override
    public Item findById(Long id, Integer cantidad) {

        Map<String, String> pathVariable = new HashMap<String, String>();
        pathVariable.put("id", id.toString());

        Producto producto = clienteRest.getForObject(
                "http://servicio-productos/product/{id}",
                Producto.class,
                pathVariable);

        return new Item(producto, cantidad);
    }

	@Override
	public Producto save(Producto producto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Producto edit(Producto producto, Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		
	}
}
