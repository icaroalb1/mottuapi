package com.mottu.mottuapi.service;

import com.mottu.mottuapi.exception.RecursoNaoEncontradoException;
import com.mottu.mottuapi.model.Localizacao;
import com.mottu.mottuapi.model.Moto;
import com.mottu.mottuapi.repository.LocalizacaoRepository;
import com.mottu.mottuapi.repository.MotoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocalizacaoService {

    private final LocalizacaoRepository localizacaoRepository;
    private final MotoRepository motoRepository;

    public LocalizacaoService(LocalizacaoRepository localizacaoRepository, MotoRepository motoRepository) {
        this.localizacaoRepository = localizacaoRepository;
        this.motoRepository = motoRepository;
    }

    public Localizacao registrarLocalizacao(Long motoId, Localizacao localizacao) {
        Moto moto = motoRepository.findById(motoId)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Moto não encontrada para localização"));
        localizacao.setMoto(moto);
        return localizacaoRepository.save(localizacao);
    }

    public List<Localizacao> listarPorMoto(Long motoId) {
        Moto moto = motoRepository.findById(motoId)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Moto não encontrada"));
        return localizacaoRepository.findByMoto(moto);
    }
}
