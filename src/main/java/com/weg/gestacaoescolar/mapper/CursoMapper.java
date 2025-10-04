package com.weg.gestacaoescolar.mapper;

import com.weg.gestacaoescolar.dto.curso.CursoRequisicaoDTO;
import com.weg.gestacaoescolar.dto.curso.CursoRespostaDTO;
import com.weg.gestacaoescolar.model.Curso;
import org.springframework.stereotype.Component;

@Component
public class CursoMapper {

    public Curso paraEntidade(CursoRequisicaoDTO requisicaoDTO) {
        return new Curso(requisicaoDTO.nome(), requisicaoDTO.codigo());
    }

    public CursoRespostaDTO paraRespostaDTO(Curso curso) {
        return new CursoRespostaDTO(curso.getId(), curso.getNome(), curso.getCodigo());
    }

    public Curso paraUpdate(CursoRequisicaoDTO requisicaoDTO, Curso curso) {
        if ((requisicaoDTO.nome() != curso.getNome() && requisicaoDTO.nome() != null)) {
            curso.setNome(requisicaoDTO.nome());
        }

        if ((requisicaoDTO.codigo() != curso.getCodigo() && requisicaoDTO.codigo() != null)) {
                curso.setCodigo(requisicaoDTO.codigo());
        }
        return curso;
    }
}

