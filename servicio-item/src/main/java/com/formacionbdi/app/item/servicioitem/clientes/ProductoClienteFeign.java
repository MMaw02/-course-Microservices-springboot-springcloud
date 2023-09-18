package com.formacionbdi.app.item.servicioitem.clientes;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.formacionbdi.springboot.app.commons.models.entity.Producto;

import java.util.List;

@FeignClient(name = "servicio-productos")
public interface ProductoClienteFeign {

    @GetMapping("/list")
    public List<Producto> list();

    @GetMapping("/{id}")
    public Producto detail(@PathVariable Long id);
    
    @PostMapping("/save")
    public Producto save(@RequestBody Producto producto);
    
    @PutMapping("/edit/{id}")
    public Producto edit(@RequestBody Producto product, @PathVariable Long id);
    
    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id);
}
