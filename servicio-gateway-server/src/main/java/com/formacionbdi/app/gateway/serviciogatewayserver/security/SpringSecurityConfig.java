package com.formacionbdi.app.gateway.serviciogatewayserver.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@EnableWebFluxSecurity
public class SpringSecurityConfig {
	
	@Autowired
	private JwtAuthenticationFilter authenticationFilter;

	@Bean
	public SecurityWebFilterChain configure(ServerHttpSecurity httpSecurity) {
		return httpSecurity.authorizeExchange()
				.pathMatchers("/api/security/oauth/**").permitAll()
				.pathMatchers(HttpMethod.GET,
						"/api/product/list",
						"/api/item/list",
						"/api/usuario/usuarios",
						"/api/item/{id}/cantidad/{cantidad}",
						"/api/product/{id}").permitAll()
				.pathMatchers(HttpMethod.GET, "/api/usuario/usuarios/{id}").hasAnyRole("ADMIN", "USER")
				.pathMatchers("/api/product/**", "/api/item/**", "/api/usuario/usuarios/**").hasRole("ADMIN")
				.anyExchange().authenticated()
				.and().addFilterAt(authenticationFilter, SecurityWebFiltersOrder.AUTHENTICATION)
				.csrf().disable()
				.build();
	}
	
	
}
