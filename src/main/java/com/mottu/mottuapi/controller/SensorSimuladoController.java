package com.mottu.mottuapi.controller;

import com.mottu.mottuapi.model.Vaga;
import com.mottu.mottuapi.repository.VagaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sensor")
public class SensorSimuladoController {

    @Autowired
    private VagaRepository vagaRepository;

    @PostMapping("/vaga/{id}/detectar")
    public ResponseEntity<String> detectarMoto(@PathVariable Long id) {
        Vaga vaga = vagaRepository.findById(id).orElse(null);
        if (vaga == null) {
            return ResponseEntity.badRequest().body("Vaga não encontrada.");
        }

        if (!vaga.isOcupada()) {
            vaga.setOcupada(true);
            vagaRepository.save(vaga);
            return ResponseEntity.ok("Sensor: Moto detectada. Vaga ocupada.");
        } else {
            return ResponseEntity.ok("Sensor: Vaga já estava ocupada.");
        }
    }
}
