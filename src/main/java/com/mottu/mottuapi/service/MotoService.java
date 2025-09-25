package com.mottu.mottuapi.service;

import com.mottu.mottuapi.exception.RecursoNaoEncontradoException;
import com.mottu.mottuapi.model.Moto;
import com.mottu.mottuapi.repository.MotoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MotoService {

    private final MotoRepository repository;

    public MotoService(MotoRepository repository) {
        this.repository = repository;
    }

    public List<Moto> listarTodas() {
        return repository.findAll();
    }

    public Moto buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Moto não encontrada com ID: " + id));
    }

    public Moto criar(Moto moto) {
        if (repository.existsByPlaca(moto.getPlaca())) {
            throw new IllegalArgumentException("Já existe uma moto com essa placa.");
        }
        return repository.save(moto);
    }

    public Moto atualizar(Long id, Moto novaMoto) {
        Moto existente = buscarPorId(id);
        existente.setPlaca(novaMoto.getPlaca());
        existente.setModelo(novaMoto.getModelo());
        existente.setStatus(novaMoto.getStatus());
        existente.setLigada(novaMoto.isLigada());
        return repository.save(existente);
    }

    public void deletar(Long id) {
        Moto moto = buscarPorId(id);
        repository.delete(moto);
    }
}
