package com.weg.gestacaoescolar.dto.turma;

import com.weg.gestacaoescolar.model.Aluno;

import java.util.List;

public record TurmaRespostaDTO(
        int id,
        String nome,
        String nomeCurso,
        String nomeProfessor,
        List<Aluno> listAlunosNome
){
}
