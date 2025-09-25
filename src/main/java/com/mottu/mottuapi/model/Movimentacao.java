package com.mottu.mottuapi.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "movimentacao")
public class Movimentacao {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	private Moto moto;

	@ManyToOne(fetch = FetchType.LAZY)
	private Vaga vaga;

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private TipoMovimentacao tipo; // CHECKIN, CHECKOUT

	@Column(nullable = false)
	private LocalDateTime dataHora = LocalDateTime.now();

	@ManyToOne(fetch = FetchType.LAZY)
	private Usuario usuario;

	public Long getId() { return id; }
	public void setId(Long id) { this.id = id; }
	public Moto getMoto() { return moto; }
	public void setMoto(Moto moto) { this.moto = moto; }
	public Vaga getVaga() { return vaga; }
	public void setVaga(Vaga vaga) { this.vaga = vaga; }
	public TipoMovimentacao getTipo() { return tipo; }
	public void setTipo(TipoMovimentacao tipo) { this.tipo = tipo; }
	public LocalDateTime getDataHora() { return dataHora; }
	public void setDataHora(LocalDateTime dataHora) { this.dataHora = dataHora; }
	public Usuario getUsuario() { return usuario; }
	public void setUsuario(Usuario usuario) { this.usuario = usuario; }
} 