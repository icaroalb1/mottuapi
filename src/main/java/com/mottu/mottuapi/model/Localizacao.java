package com.mottu.mottuapi.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Localizacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "coordenada_x", nullable = false)
    private float coordenadaX;

    @Column(name = "coordenada_y", nullable = false)
    private float coordenadaY;

    @Column(name = "data_hora")
    private LocalDateTime dataHora = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "moto_id")
    private Moto moto;

    // Getters e Setters
    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public float getCoordenadaX() { return coordenadaX; }

    public void setCoordenadaX(float coordenadaX) { this.coordenadaX = coordenadaX; }

    public float getCoordenadaY() { return coordenadaY; }

    public void setCoordenadaY(float coordenadaY) { this.coordenadaY = coordenadaY; }

    public LocalDateTime getDataHora() { return dataHora; }

    public void setDataHora(LocalDateTime dataHora) { this.dataHora = dataHora; }

    public Moto getMoto() { return moto; }

    public void setMoto(Moto moto) { this.moto = moto; }
}
