package com.mottu.mottuapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.mottu.mottuapi.model.Vaga;


import java.util.List;

public interface VagaRepository extends JpaRepository<Vaga, Long> {
    List<Vaga> findByOcupadaFalse(); // retorna vagas disponíveis
}
