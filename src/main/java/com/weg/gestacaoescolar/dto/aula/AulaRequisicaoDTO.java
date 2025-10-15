package com.weg.gestacaoescolar.dto.aula;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.PositiveOrZero;

import java.time.LocalDateTime;

public record AulaRequisicaoDTO(
        @PositiveOrZero(message = "O Campo deve ser maior que 0")
        int turmaId,
        @Past(message = "A data selecionada ainda não aconteceu! Selecione uma data do passado!")
        LocalDateTime dataHora,
        @NotEmpty(message = "O campo não pode ser vazio!")
        String assunto
) {
}
