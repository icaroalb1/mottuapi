package com.mottu.mottuapi.controller;

import com.mottu.mottuapi.model.Localizacao;
import com.mottu.mottuapi.service.LocalizacaoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/localizacoes")
public class LocalizacaoController {

    private final LocalizacaoService service;

    public LocalizacaoController(LocalizacaoService service) {
        this.service = service;
    }

    @PostMapping("/{motoId}")
    public ResponseEntity<Localizacao> registrar(
            @PathVariable Long motoId,
            @RequestBody Localizacao localizacao
    ) {
        return ResponseEntity.ok(service.registrarLocalizacao(motoId, localizacao));
    }

    @GetMapping("/{motoId}")
    public ResponseEntity<List<Localizacao>> listarPorMoto(@PathVariable Long motoId) {
        return ResponseEntity.ok(service.listarPorMoto(motoId));
    }
}
