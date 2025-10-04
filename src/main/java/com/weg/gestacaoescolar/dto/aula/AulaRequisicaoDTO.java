package com.weg.gestacaoescolar.dto.aula;

import java.time.LocalDateTime;

public record AulaRequisicaoDTO(
        int turmaId,
        LocalDateTime dataHora,
        String assunto
) {
}
