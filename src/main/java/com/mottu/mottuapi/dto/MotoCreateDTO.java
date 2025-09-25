package com.mottu.mottuapi.dto;

import com.mottu.mottuapi.model.StatusMoto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record MotoCreateDTO(
        @NotBlank String placa,
        @NotBlank String modelo,
        @NotNull StatusMoto status,
        @NotNull boolean ligada
) {}
