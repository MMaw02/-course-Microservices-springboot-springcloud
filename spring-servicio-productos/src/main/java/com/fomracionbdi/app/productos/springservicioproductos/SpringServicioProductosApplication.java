package com.fomracionbdi.app.productos.springservicioproductos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan({"com.formacionbdi.springboot.app.commons.models.entity"})
public class SpringServicioProductosApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringServicioProductosApplication.class, args);
	}

}
