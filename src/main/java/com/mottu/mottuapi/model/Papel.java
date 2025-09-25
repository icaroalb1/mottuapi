package com.mottu.mottuapi.model;

import jakarta.persistence.*;

@Entity
@Table(name = "papel")
public class Papel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(unique = true, nullable = false)
	private String nome; // e.g., ROLE_ADMIN, ROLE_OPERADOR

	public Long getId() { return id; }
	public void setId(Long id) { this.id = id; }
	public String getNome() { return nome; }
	public void setNome(String nome) { this.nome = nome; }
} 