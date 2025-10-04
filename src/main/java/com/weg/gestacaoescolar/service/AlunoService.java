package com.weg.gestacaoescolar.service;

import com.weg.gestacaoescolar.dao.AlunoDAO;
import com.weg.gestacaoescolar.dto.aluno.AlunoRequisicaoDTO;
import com.weg.gestacaoescolar.dto.aluno.AlunoRespostaDTO;
import com.weg.gestacaoescolar.mapper.AlunoMapper;
import com.weg.gestacaoescolar.model.Aluno;
import org.springframework.stereotype.Service;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class AlunoService {

    private final AlunoMapper mapper;
    private final AlunoDAO repository;

    public AlunoService(AlunoDAO repository, AlunoMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public AlunoRespostaDTO criarAlunos(AlunoRequisicaoDTO requisicaoDto) throws SQLException {
        return mapper.paraRespostaDto(repository.salvarAluno(mapper.paraEntidade(requisicaoDto)));
    }

    public List<AlunoRespostaDTO> listarAluno() throws SQLException {
        List<Aluno> alunos = repository.listAlunos();
        List<AlunoRespostaDTO> respostaDTOS = new ArrayList<>();

        for (Aluno aluno : alunos) {
            respostaDTOS.add(mapper.paraRespostaDto(aluno));
        }
        return respostaDTOS;
    }

    public AlunoRespostaDTO pesquisarAlunoPorId(int id) throws SQLException {
        Aluno aluno = repository.buscarAlunoPorID(id);

        if (aluno == null) {
            throw new RuntimeException("ID não existe!!");
        }
        return mapper.paraRespostaDto(aluno);
    }

    public void deletarAluno(int id) throws SQLException {
        if (!repository.alunoExiste(id)) {
            throw new RuntimeException("ID não existe!!");
        }
        repository.deleteAluno(id);
    }

    public AlunoRespostaDTO atualizarAluno(int id, AlunoRequisicaoDTO requisicaoDTO) throws SQLException {
        Aluno aluno = repository.buscarAlunoPorID(id);

        if (aluno.getId() == 0){
            throw new RuntimeException("ID não existe!!");
        }
        Aluno newUser = mapper.paraUpdate(requisicaoDTO,aluno);
        repository.salvarAluno(newUser);
        return mapper.paraRespostaDto(newUser);
    }
}

