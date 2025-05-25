package com.mottu.mottuapi.controller;

import com.mottu.mottuapi.model.Moto;
import com.mottu.mottuapi.service.MotoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/motos")
public class MotoController {

    private final MotoService service;

    public MotoController(MotoService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Moto>> listar() {
        return ResponseEntity.ok(service.listarTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Moto> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<Moto> criar(@RequestBody Moto moto) {
        return ResponseEntity.ok(service.criar(moto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Moto> atualizar(@PathVariable Long id, @RequestBody Moto moto) {
        return ResponseEntity.ok(service.atualizar(id, moto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
