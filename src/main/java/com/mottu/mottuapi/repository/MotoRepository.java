package com.mottu.mottuapi.repository;

import com.mottu.mottuapi.model.Moto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MotoRepository extends JpaRepository<Moto, Long> {
    // Exemplo: buscar moto pela placa
    boolean existsByPlaca(String placa);
}
