package com.fomracionbdi.app.productos.springservicioproductos.controller;

import com.formacionbdi.springboot.app.commons.models.entity.Producto;
import com.fomracionbdi.app.productos.springservicioproductos.models.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
//@RequestMapping("/product")
public class ProductoController {

    @Autowired
    private IProductService productService;

    @GetMapping("/list")
    public List<Producto> listProduct() {
        return productService.findAll();
    }

    @GetMapping("/{id}")
    public Producto detail(@PathVariable Long id) throws InterruptedException {

        if (id.equals(10L)) {
//            Lanzamos una EXCEPCIÓN si el ID es 10.
            throw new IllegalStateException("Producto no encontrado!");
        }
        if (id.equals(7L)) {
//            Hacemos una ESPERA si el ID es 7
            TimeUnit.SECONDS.sleep(5L);
        }

        return productService.findById(id);
    }
    
    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public Producto save(@RequestBody Producto product) {
    	return productService.save(product);
    }
    
    @PutMapping("/edit/{id}")
//    Cada ves que vamos a guardar el tipo de HTTP es 201
    @ResponseStatus(HttpStatus.CREATED)
    public Producto edit(@RequestBody Producto product, @PathVariable Long id) {
    	Producto productDB = productService.findById(id);
    	productDB.setNombre(product.getNombre());
    	productDB.setPrecio(product.getPrecio());
    	return productService.save(productDB);
    }
    
    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
    	productService.deleteById(id);
    }
}
