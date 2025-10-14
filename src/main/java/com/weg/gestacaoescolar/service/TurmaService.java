package com.weg.gestacaoescolar.service;

import com.weg.gestacaoescolar.dao.TurmaDAO;
import com.weg.gestacaoescolar.dto.turma.TurmaRequisicaoDTO;
import com.weg.gestacaoescolar.dto.turma.TurmaRespostaDTO;
import com.weg.gestacaoescolar.mapper.TurmaMapper;
import com.weg.gestacaoescolar.model.Turma;
import com.weg.gestacaoescolar.model.TurmaResposta;
import org.springframework.stereotype.Service;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TurmaService {
    private final TurmaDAO repository;
    private final TurmaMapper mapper;
    private final Map<Integer, List<Integer>> turmaAlunosMap;

    public TurmaService(TurmaDAO repository, TurmaMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
        this.turmaAlunosMap = new HashMap<>();
    }

    public TurmaRespostaDTO criarTurma(TurmaRequisicaoDTO requisicaoDTO) throws SQLException {
        Turma turma = mapper.paraEntidade(requisicaoDTO);

        Turma newTurma = repository.salvarTurma(turma);
        repository.inserirAlunosTurma(newTurma.getId(), requisicaoDTO.idsAlunos());

        List<String> nomeAlunos = repository.buscarListaNomesPorId(requisicaoDTO.idsAlunos());

        TurmaResposta turmaResposta = repository.buscarTurmasPorId(newTurma.getId());

        return mapper.paraRespostaDTO(turmaResposta, nomeAlunos);
    }

    public List<TurmaRespostaDTO> buscarTurmas() throws SQLException {
        List<TurmaResposta> turmas = repository.buscarTurmas();
        List<TurmaRespostaDTO> respostaDTOS = new ArrayList<>();

        for (TurmaResposta turma : turmas) {
            List<String> nomeAlunos = repository.buscarListaNomeAlunosPorTurma(turma.getId());
            respostaDTOS.add(mapper.paraRespostaDTO(turma, nomeAlunos));
        }
        return respostaDTOS;
    }

    public TurmaRespostaDTO pesquisarTurmaPorId(int id) throws SQLException {
        TurmaResposta turma = repository.buscarTurmasPorId(id);
        List<Turma> turmas = repository.listarTurma();

        if (turma == null){
            throw new RuntimeException("ID não existe!!");
        }
        List<Integer> idAlunos = new ArrayList<>();
        List<String> nomeAlunos = new ArrayList<>();

        for (Turma t : turmas){
            if (t.getId() == id){
                idAlunos = turmaAlunosMap.getOrDefault(t.getId(),List.of());

                if (!idAlunos.isEmpty()){
                    nomeAlunos = repository.buscarListaNomesPorId(idAlunos);
                }
            }
        }
        return mapper.paraRespostaDTO(turma,nomeAlunos);
    }

    public void deletarTurma(int id) throws SQLException{
        Turma turma = repository.buscarTurmasPorId(id);

        if (turma == null){
            throw new RuntimeException("ID da turma não existe!!");
        }
        repository.deletarTurma(id);
    }

//    public TurmaRespostaDTO atualizarTurma(int id,TurmaRequisicaoDTO requisicaoDTO) throws SQLException {
//        Turma turma = repository.buscarTurmasPorId(id);
//
//        if (turma == null){
//            throw new RuntimeException("ID não existe!!");
//        }
//
//        List idAlunos = new ArrayList<>();
//        List nomeAlunos = new ArrayList<>();
//
//        if (!idAlunos.isEmpty()){
//            nomeAlunos = repository.buscarListaNomesPorId(idAlunos);
//        }
//
//        TurmaResposta newTurma = mapper.paraUpdate(requisicaoDTO,turma);
//        repository.atualizarTurma(id,newTurma);
//        return mapper.paraRespostaDTO(newTurma,nomeAlunos);
//    }
}
