package com.weg.gestacaoescolar.dto.curso;

import com.weg.gestacaoescolar.model.Professor;

import java.util.List;

public record CursoRequisicaoDTO(
        String nome,
        String codigo,
        List<Professor> listProfIds
) {
}
