package com.weg.gestacaoescolar.dto.curso;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public record CursoRespostaDTO(
        int id,
        String nome,
        String codigo,
        List<String> listProfNomes
) {
}
