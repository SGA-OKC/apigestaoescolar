package com.weg.gestacaoescolar.dto.aula;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

public record AulaRequisicaoDTO(
        @NotBlank(message = "ID da turma invalido!")
        int turmaId,

        @Future(message = "Data invalida!!")
        LocalDateTime dataHora,

        @NotBlank(message = "Assunto esta em branco!")
        String assunto
) {
}
