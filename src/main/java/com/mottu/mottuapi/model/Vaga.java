package com.mottu.mottuapi.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Vaga {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private boolean ocupada;

    @OneToOne
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Moto moto; // null se vaga estiver vazia

    // Getters e Setters
    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public boolean isOcupada() { return ocupada; }

    public void setOcupada(boolean ocupada) { this.ocupada = ocupada; }

    public Moto getMoto() { return moto; }

    public void setMoto(Moto moto) { this.moto = moto; }
}
