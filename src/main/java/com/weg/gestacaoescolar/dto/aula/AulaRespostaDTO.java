package com.weg.gestacaoescolar.dto.aula;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

public record AulaRespostaDTO(

        @NotBlank(message = "ID invalido!!")
        int id,
        @NotBlank(message = "Nome da turma não existe!")
        String nomeTurma,
        @Future(message = "Data esta invalida!!")
        LocalDateTime dataHora,
        @NotBlank(message = "Assunto esta vazio!!")
        String assunto
) {
}
