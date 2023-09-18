package com.formacionbdi.app.item.servicioitem.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.formacionbdi.springboot.app.commons.models.entity.Producto;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Item {
    private Producto producto;
    private Integer cantidad;

    public Double getTotal() {
        return producto.getPrecio() * cantidad.doubleValue();
    }
}
