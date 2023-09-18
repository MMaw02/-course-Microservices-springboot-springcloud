package com.formacionbdi.app.gateway.serviciogatewayserver.security;

import java.util.Base64;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import reactor.core.publisher.Mono;

@Component
public class AuthenticationManagerJwt implements ReactiveAuthenticationManager {

	@Value("${config.security.oauth.jwt.key}")
	private String llaveJwt;
// authentication → Esto es lo que se va a pasar por el filtro. Vamos a tener el token JWT.
//		just → Convertimo un objeto común en un objeto reactivo. Token String a Token Mono.
//				Usamos expresiones lambda
	
	@Override
	public Mono<Authentication> authenticate(Authentication authentication) {
		
		return Mono.just(authentication.getCredentials().toString())
				.map(token -> {
					SecretKey llave = Keys.hmacShaKeyFor(Base64.getEncoder().encode(llaveJwt.getBytes()));
//					Tenemos que convertirlo a Base64 para que sea más robusta, con java.util java8
//					hmacShaKeyFor y encode de Base64 reciben Byte, no se puede trabajar con String.
					return Jwts.parserBuilder().setSigningKey(llave).build().parseClaimsJws(token).getBody();
				})
				.map(claims -> {
					String username = claims.get("username", String.class);
					List<String> roles = claims.get("authorities", List.class);
					Collection<GrantedAuthority> authorities = roles.stream().map(SimpleGrantedAuthority::new)
							.collect(Collectors.toList());
					return new UsernamePasswordAuthenticationToken(username, null, authorities);
				});
	}

	
}

//							Esto es puede simplificar, lo hace en el minuto 17:40