package com.mottu.mottuapi.repository;

import com.mottu.mottuapi.model.Localizacao;
import com.mottu.mottuapi.model.Moto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocalizacaoRepository extends JpaRepository<Localizacao, Long> {
    List<Localizacao> findByMoto(Moto moto);
}
