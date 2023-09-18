package com.formacionbdi.springboot.app.commons.usuarios.models.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Data;

@Entity
@Table(name= "usuarios")
@Data
public class Usuario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(unique = true, length = 20)
	private String username;
	@Column(length = 60)
	private String password;
	private Boolean enabled;
	private String nombre;
	private String apellido;
	@Column(unique = true, length = 100)
	private String email;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "usuarios_roles",joinColumns = @JoinColumn(name="usuario_id"), inverseJoinColumns = @JoinColumn(name="role_id"),
	uniqueConstraints = @UniqueConstraint(columnNames = {"usuario_id", "role_id"}))
	private List<Role> roles;
}