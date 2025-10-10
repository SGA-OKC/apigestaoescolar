package com.weg.gestacaoescolar.dto.turma;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.PositiveOrZero;

import java.util.List;

public record TurmaRespostaDTO(
        @NotBlank(message = "ID inválido!")
        int id,
        @NotBlank(message = "Nome inválido!")
        String nome,
        @PositiveOrZero(message = "ID de curso inválido!")
        String nomeCurso,
        @PositiveOrZero(message = "ID de professor inválido!")
        String nomeProfessor,
        @NotEmpty(message = "Lista inválida!")
        List<String> nomeAlunos
){
}
