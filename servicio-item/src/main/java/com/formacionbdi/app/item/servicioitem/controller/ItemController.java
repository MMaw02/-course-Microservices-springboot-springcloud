package com.formacionbdi.app.item.servicioitem.controller;

import com.formacionbdi.app.item.servicioitem.models.Item;
import com.formacionbdi.springboot.app.commons.models.entity.Producto;
import com.formacionbdi.app.item.servicioitem.models.services.IItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RefreshScope
@RestController
public class ItemController {

    @Autowired
    private CircuitBreakerFactory cbFactory;

    @Value("${configuracion.texto}")
    private String texto;
    
    @Autowired
    private Environment env;

    @Autowired
    @Qualifier("serviceFeign")
    private IItemService itemService;

    @GetMapping("/list")
    public List<Item> list(
            @RequestParam(name = "nombre", required = false) String nombre,
            @RequestHeader(name = "token-request", required = false) String token) {
        System.out.println(nombre);
        System.out.println(token);
        return itemService.findAll();
    }

    @GetMapping("/{id}/cantidad/{cantidad}")
    public Item detail(@PathVariable Long id, @PathVariable Integer cantidad) {
        return cbFactory.create("items")
                .run(
                        () -> itemService.findById(id, cantidad),
                        e -> metodoAlternativo(id, cantidad)
                );
    }
    
    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public Producto save(@RequestBody Producto producto) {
    	return itemService.save(producto);
    }
    
    @PutMapping("/edit/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Producto edit(@RequestBody Producto producto, @PathVariable Long id) {
    	return itemService.edit(producto, id);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
    	itemService.delete(id);
    }
    
    public Item metodoAlternativo(Long id, Integer cantidad) {
        Item item = new Item();
        Producto producto = new Producto();

        item.setCantidad(cantidad);
        producto.setId(id);
        producto.setNombre("Camara Sony");
        producto.setPrecio(500.00);
        item.setProducto(producto);
        return item;
    }

    @GetMapping("/obtener-config")
    public ResponseEntity<?> obtenerConfig(@Value("${server.port}") String puerto) {
        Map<String, String> json = new HashMap<>();
        json.put("texto", texto);
        json.put("puerto", puerto);
        
        if (env.getActiveProfiles().length>0 && env.getActiveProfiles()[0].equals("dev")) {
			json.put("autor.nombre", env.getProperty("configuracion.autor.nombre"));
			json.put("autor.email", env.getProperty("configuracion.autor.email"));
		}
        return new ResponseEntity<Map<String, String>>(json, HttpStatus.OK);
    }
    
}
