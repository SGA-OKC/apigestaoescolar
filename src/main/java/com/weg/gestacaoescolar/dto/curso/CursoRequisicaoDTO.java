package com.weg.gestacaoescolar.dto.curso;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public record CursoRequisicaoDTO(
        @NotBlank(message = "Nome inválido!")
        String nome,
        @NotBlank(message = "Codigo inválido!")
        String codigo,
        @NotEmpty(message = "Lista inválida!")
        List<Integer> listProfIds
) {
}
