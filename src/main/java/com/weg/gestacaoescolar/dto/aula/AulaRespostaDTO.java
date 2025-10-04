package com.weg.gestacaoescolar.dto.aula;

import java.time.LocalDateTime;

public record AulaRespostaDTO(
        int id,
        String nomeTurma,
        LocalDateTime dataHora,
        String assunto
) {
}
