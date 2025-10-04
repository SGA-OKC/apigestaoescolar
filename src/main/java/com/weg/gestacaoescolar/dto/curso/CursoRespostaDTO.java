package com.weg.gestacaoescolar.dto.curso;

import com.weg.gestacaoescolar.model.Professor;

import java.util.List;

public record CursoRespostaDTO(
        int id,
        String nome,
        String codigo,
        List<Professor> listProfNomes
) {
}
