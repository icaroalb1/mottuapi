package com.mottu.mottuapi.dto;

import java.time.LocalDateTime;

public record LocalizacaoDTO(
        Long id,
        float coordenadaX,
        float coordenadaY,
        LocalDateTime dataHora
) {}
