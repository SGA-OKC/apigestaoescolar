package com.weg.gestacaoescolar.mapper;

import com.weg.gestacaoescolar.dto.turma.TurmaRequisicaoDTO;
import com.weg.gestacaoescolar.dto.turma.TurmaRespostaDTO;
import com.weg.gestacaoescolar.model.Turma;
import com.weg.gestacaoescolar.model.TurmaResposta;

import java.util.List;

public class TurmaMapper {

    public Turma paraEntidade(TurmaRequisicaoDTO requisicaoDTO){
        return new Turma(requisicaoDTO.nome(), requisicaoDTO.cursoId(), requisicaoDTO.professorId());
    }

    public TurmaRespostaDTO paraRespostaDTO(TurmaResposta turma, List<String> nomeAlunos){
        return new TurmaRespostaDTO(turma.getId(), turma.getNome(), turma.getNomeCurso(), turma.getNomeProfessor(), nomeAlunos);
    }

    public Turma paraUpdate(TurmaRequisicaoDTO requisicaoDTO, Turma turma){
        if ((requisicaoDTO.nome() != turma.getNome() && requisicaoDTO.nome() != null)) {
            turma.setNome(requisicaoDTO.nome());
        }

        if ((requisicaoDTO.professorId() != turma.getProfessor_id() && requisicaoDTO.professorId() != 0)) {
            turma.setProfessor_id(requisicaoDTO.professorId());
        }
        if ((requisicaoDTO.cursoId() != turma.getCurso_id() && requisicaoDTO.cursoId() != 0)){
            turma.setCurso_id(requisicaoDTO.cursoId());
        }
        return turma;
    }
}
