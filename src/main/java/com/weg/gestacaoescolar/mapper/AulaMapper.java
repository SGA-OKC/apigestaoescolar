package com.weg.gestacaoescolar.mapper;

import com.weg.gestacaoescolar.dto.aula.AulaRequisicaoDTO;
import com.weg.gestacaoescolar.dto.aula.AulaRespostaDTO;
import com.weg.gestacaoescolar.model.Aula;
import org.springframework.stereotype.Component;

@Component
public class AulaMapper {

    public Aula paraEntidade(AulaRequisicaoDTO requisicaoDTO) {
        return new Aula(requisicaoDTO.turmaId(), requisicaoDTO.dataHora(),requisicaoDTO.assunto());
    }

    public AulaRespostaDTO paraRespostaDTO(Aula aula, String nomeTurma) {
        return new AulaRespostaDTO(aula.getId(),nomeTurma, aula.getDataHora(), aula.getAssunto());
    }

    public Aula paraUpdate(Aula aula,AulaRequisicaoDTO requisicaoDTO){
        if (requisicaoDTO.turmaId() != 0 && requisicaoDTO.turmaId() != aula.getTurmaId()) {
            aula.setTurmaId(requisicaoDTO.turmaId());
        }

        if (requisicaoDTO.dataHora() != null && !requisicaoDTO.dataHora().equals(aula.getDataHora())) {
            aula.setDataHora(requisicaoDTO.dataHora());
        }

        if (requisicaoDTO.assunto() != null && !requisicaoDTO.assunto().equals(aula.getAssunto())) {
            aula.setAssunto(requisicaoDTO.assunto());
        }
        return aula;
    }
}
