package com.weg.gestacaoescolar.mapper;

import com.weg.gestacaoescolar.dto.professor.ProfessorRequisicaoDTO;
import com.weg.gestacaoescolar.dto.professor.ProfessorRespostaDTO;
import com.weg.gestacaoescolar.model.Professor;
import org.springframework.stereotype.Component;

@Component
public class ProfessorMapper {

    public Professor paraEntidade(ProfessorRequisicaoDTO requisicaoDTO){
        return new Professor(requisicaoDTO.nome(), requisicaoDTO.email(), requisicaoDTO.disciplina());
    }

    public ProfessorRespostaDTO paraRespostaDTO(Professor professor ){
        return new ProfessorRespostaDTO(professor.getId(), professor.getNome(), professor.getEmail(), professor.getDisciplina());
    }

    public Professor paraUpdate(ProfessorRequisicaoDTO requisicaoDTO, Professor professor) {
        if ((requisicaoDTO.nome() != professor.getNome() && requisicaoDTO.nome() != null)) {
            professor.setNome(requisicaoDTO.nome());
        }

        if ((requisicaoDTO.email() != professor.getEmail() && requisicaoDTO.email() != null)) {
            professor.setEmail(requisicaoDTO.email());
        }
        return professor;
    }
}
