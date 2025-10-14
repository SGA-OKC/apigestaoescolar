package com.weg.gestacaoescolar.dto.curso;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public record CursoRespostaDTO(
        @NotBlank(message = "ID inválido!")
        int id,
        @NotBlank(message = "Nome inválido!")
        String nome,
        @NotBlank(message = "Codigo inválido!")
        String codigo,
        @NotEmpty(message = "Lista inválida!")
        List<String> listProfNomes
) {
}
