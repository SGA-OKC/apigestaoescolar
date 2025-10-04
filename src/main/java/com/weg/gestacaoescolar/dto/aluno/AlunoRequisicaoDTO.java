package com.weg.gestacaoescolar.dto.aluno;

import java.time.LocalDate;

public record AlunoRequisicaoDTO(
        String nome,
        String email,
        String matricula,
        LocalDate dataNascimento
) {
}
