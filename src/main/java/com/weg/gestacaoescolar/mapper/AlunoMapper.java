package com.weg.gestacaoescolar.mapper;

import com.weg.gestacaoescolar.dto.aluno.AlunoRequisicaoDTO;
import com.weg.gestacaoescolar.dto.aluno.AlunoRespostaDTO;
import com.weg.gestacaoescolar.model.Aluno;
import org.springframework.stereotype.Component;

@Component
public class AlunoMapper {

    public Aluno paraEntidade(AlunoRequisicaoDTO requisicaoDTO) {
        return new Aluno(requisicaoDTO.nome(), requisicaoDTO.email(), requisicaoDTO.matricula(), requisicaoDTO.dataNascimento());
    }

    public AlunoRespostaDTO paraRespostaDto(Aluno aluno) {
        return new AlunoRespostaDTO(aluno.getId(), aluno.getNome(), aluno.getEmail(), aluno.getMatricula(), aluno.getDataNascimento());
    }

    public Aluno paraUpdate(AlunoRequisicaoDTO requisicaoDTO, Aluno aluno) {
        if ((requisicaoDTO.nome() != aluno.getNome() && requisicaoDTO.nome() != null)) {
            aluno.setNome(requisicaoDTO.nome());
        }

        if ((requisicaoDTO.email() != aluno.getEmail() && requisicaoDTO.email() != null)) {
            aluno.setEmail(requisicaoDTO.email());
        }
        return aluno;
    }
}
