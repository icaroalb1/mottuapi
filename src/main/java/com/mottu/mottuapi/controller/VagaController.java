package com.mottu.mottuapi.controller;

import com.mottu.mottuapi.model.Moto;
import com.mottu.mottuapi.repository.MotoRepository;
import com.mottu.mottuapi.repository.VagaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import com.mottu.mottuapi.model.Vaga;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vagas")
public class VagaController {

    @Autowired
    private VagaRepository vagaRepository;

    @Autowired
    private MotoRepository motoRepository;

    @GetMapping
    public List<Vaga> listarTodas() {
        return vagaRepository.findAll();
    }

    @GetMapping("/disponiveis")
    public List<Vaga> listarDisponiveis() {
        return vagaRepository.findByOcupadaFalse();
    }

    @PostMapping("/{id}/ocupar")
    public ResponseEntity<String> ocuparVaga(@PathVariable Long id, @RequestParam Long motoId) {
        Vaga vaga = vagaRepository.findById(id).orElse(null);
        Moto moto = motoRepository.findById(motoId).orElse(null);

        if (vaga == null || moto == null) {
            return ResponseEntity.badRequest().body("Vaga ou Moto não encontrada.");
        }

        if (vaga.isOcupada()) {
            return ResponseEntity.badRequest().body("Vaga já está ocupada.");
        }

        vaga.setOcupada(true);
        vaga.setMoto(moto);
        vagaRepository.save(vaga);
        return ResponseEntity.ok("Vaga ocupada com sucesso.");
    }

    @PostMapping("/{id}/liberar")
    public ResponseEntity<String> liberarVaga(@PathVariable Long id) {
        Vaga vaga = vagaRepository.findById(id).orElse(null);
        if (vaga == null) {
            return ResponseEntity.badRequest().body("Vaga não encontrada.");
        }

        vaga.setOcupada(false);
        vaga.setMoto(null);
        vagaRepository.save(vaga);
        return ResponseEntity.ok("Vaga liberada com sucesso.");
    }

    @PostMapping
    public Vaga criarVaga(@RequestBody Vaga vaga) {
        return vagaRepository.save(vaga);
    }
}
