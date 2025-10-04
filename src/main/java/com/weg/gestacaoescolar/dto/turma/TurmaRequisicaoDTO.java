package com.weg.gestacaoescolar.dto.turma;

import com.weg.gestacaoescolar.model.Aluno;

import java.util.List;

public record TurmaRequisicaoDTO(
        String nome,
        int cursoId,
        int professorId,
        List<Aluno> listAlunoIds
){
}
