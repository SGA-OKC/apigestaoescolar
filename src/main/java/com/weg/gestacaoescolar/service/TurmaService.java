package com.weg.gestacaoescolar.service;

import com.weg.gestacaoescolar.dao.TurmaDAO;
import com.weg.gestacaoescolar.dto.turma.TurmaRequisicaoDTO;
import com.weg.gestacaoescolar.dto.turma.TurmaRespostaDTO;
import com.weg.gestacaoescolar.mapper.TurmaMapper;
import com.weg.gestacaoescolar.model.Turma;
import com.weg.gestacaoescolar.model.TurmaResposta;
import org.springframework.stereotype.Service;
import java.sql.SQLException;
import java.util.List;

@Service
public class TurmaService {
    private final TurmaDAO repository;
    private final TurmaMapper mapper;
    public TurmaService(TurmaDAO repository, TurmaMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public TurmaRespostaDTO respostaDTO(TurmaRequisicaoDTO requisicaoDTO) throws SQLException {
        Turma turma = mapper.paraEntidade(requisicaoDTO);

        Turma newTurma = repository.salvarTurma(turma);
        repository.inserirAlunosTurma(newTurma.getId(), requisicaoDTO.idsAlunos());

        List<String> nomeAlunos = repository.buscarListaNomesPorId(requisicaoDTO.idsAlunos());

        TurmaResposta turmaResposta = repository.buscarTurmasPorId(newTurma.getId());

        return mapper.paraRespostaDTO(turmaResposta,nomeAlunos);
    }
}
