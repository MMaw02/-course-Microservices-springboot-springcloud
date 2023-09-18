package com.formacionbdi.springboot.app.oauth.service;

import com.formacionbdi.springboot.app.commons.usuarios.models.entity.Usuario;

public interface IUsuarioService {

	public Usuario findByUsername(String username);
}
