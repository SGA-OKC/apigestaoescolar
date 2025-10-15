package com.weg.gestacaoescolar.service;

import com.weg.gestacaoescolar.dao.AulaDAO;
import com.weg.gestacaoescolar.dto.aula.AulaRequisicaoDTO;
import com.weg.gestacaoescolar.dto.aula.AulaRespostaDTO;
import com.weg.gestacaoescolar.mapper.AulaMapper;
import com.weg.gestacaoescolar.model.Aula;
import org.springframework.stereotype.Service;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class AulaService {

    private final AulaMapper mapper;

    private final AulaDAO repository;

    public AulaService(AulaMapper mapper, AulaDAO repository) {
        this.mapper = mapper;
        this.repository = repository;
    }

    public AulaRespostaDTO criarAula(AulaRequisicaoDTO requisicaoDTO) throws SQLException {
        Aula aula = repository.salvarAula(mapper.paraEntidade(requisicaoDTO));
        String nomeTurma = repository.buscarNomeTurmaPorId(aula.getId());

        return mapper.paraRespostaDTO(aula,nomeTurma);
    }

    public List<AulaRespostaDTO> buscarAulas() throws SQLException {
        List<Aula> aula = repository.listarAula();
        List<AulaRespostaDTO> respostaDTOS = new ArrayList<>();

        for (Aula aulas : aula) {
            String nomeTurma = repository.buscarNomeTurmaPorId(aulas.getTurmaId());

            respostaDTOS.add(mapper.paraRespostaDTO(aulas,nomeTurma));
        }
        return respostaDTOS;
    }

    public AulaRespostaDTO buscarAulaPorId(int id) throws SQLException {
        Aula aula = repository.buscarAulaPorID(id);

        if (aula == null){
            throw new RuntimeException("ID não existe!!");
        }

        String nomeTurma = repository.buscarNomeTurmaPorId(aula.getTurmaId());
        return mapper.paraRespostaDTO(repository.buscarAulaPorID(id),nomeTurma);
    }

    public AulaRespostaDTO atualizarAula(int id,AulaRequisicaoDTO requisicaoDTO) throws SQLException {
        Aula aula = repository.buscarAulaPorID(id);

        if (aula == null) {
            throw new RuntimeException("ID não existe!!");
        }

        Aula newAula = mapper.paraUpdate(aula,requisicaoDTO);
        repository.atualizarAula(id,newAula);
        String nomeTurma = repository.buscarNomeTurmaPorId(aula.getTurmaId());

        return mapper.paraRespostaDTO(newAula,nomeTurma);
    }

    public void deletarAula(int id) throws SQLException {
        Aula aula = repository.buscarAulaPorID(id);

        if (aula == null){
            throw new RuntimeException("ID não existe!!");
        }
        repository.deletarAula(id);
    }
}
