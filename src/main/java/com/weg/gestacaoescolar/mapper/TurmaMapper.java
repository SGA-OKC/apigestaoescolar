package com.weg.gestacaoescolar.mapper;

import com.weg.gestacaoescolar.dto.turma.TurmaRequisicaoDTO;
import com.weg.gestacaoescolar.dto.turma.TurmaRespostaDTO;
import com.weg.gestacaoescolar.model.Turma;

public class TurmaMapper {

    public Turma paraEntidade(TurmaRequisicaoDTO requisicaoDTO){
        return new Turma(requisicaoDTO.nome(), requisicaoDTO.cursoId(), requisicaoDTO.professorId());
    }

//    public TurmaRespostaDTO paraRespostaDTO(Turma turma){
//        return new TurmaRespostaDTO(turma.getId(), turma.getNome(), turma.getCursoId(), turma.getProfessorId());
//    }

//    public Turma paraUpdate(TurmaRequisicaoDTO requisicaoDTO, Turma turma){
//        if ((!requisicaoDTO.nome() != turma.getNome() && !requisicaoDTO.nome() != null)) {
//
//        }
//    }
}
