package com.weg.gestacaoescolar.dto.aula;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

public record AulaRespostaDTO(

        int id,
        String nomeTurma,
        LocalDateTime dataHora,
        String assunto
) {
}
