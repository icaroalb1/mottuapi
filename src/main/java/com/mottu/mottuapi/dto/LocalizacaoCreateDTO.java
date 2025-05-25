package com.mottu.mottuapi.dto;

import jakarta.validation.constraints.NotNull;

public record LocalizacaoCreateDTO(
        @NotNull float coordenadaX,
        @NotNull float coordenadaY
) {}
