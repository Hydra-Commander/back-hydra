package com.hc.hydracommander.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

import com.hc.hydracommander.model.Parameter;

public record CreateCommandDTO(
        @NotBlank(message = "O campo 'id' é obrigatório e não pode estar em branco.")
        String commandId,

        @NotBlank(message = "O campo 'name' é obrigatório e não pode estar em branco.")
        String name,

        String description,

        @NotNull(message = "O campo 'isCustom' é obrigatório e não pode estar em branco.")
        boolean isCustom,
        List<Parameter> parameters
) {
}
