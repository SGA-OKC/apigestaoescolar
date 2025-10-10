package com.weg.gestacaoescolar.dto.turma;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.PositiveOrZero;

import java.util.List;

public record TurmaRequisicaoDTO(
        @NotBlank(message = "Nome inválido!")
        String nome,

        @PositiveOrZero(message = "ID de curso inválido!")
        int cursoId,
        @PositiveOrZero (message = "ID de professor inválido!")
        int professorId,

        @NotEmpty(message = "Lista inválida!")
        List<Integer> idsAlunos
){
}
