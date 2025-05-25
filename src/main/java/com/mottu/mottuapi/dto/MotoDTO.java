package com.mottu.mottuapi.dto;

import com.mottu.mottuapi.model.StatusMoto;

import java.time.LocalDateTime;

public record MotoDTO(
        Long id,
        String placa,
        String modelo,
        StatusMoto status,
        boolean ligada,
        LocalDateTime dataCadastro
) {}
