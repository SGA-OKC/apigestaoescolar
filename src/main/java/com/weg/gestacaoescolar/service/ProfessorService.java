package com.weg.gestacaoescolar.service;

import com.weg.gestacaoescolar.dao.ProfessorDAO;
import com.weg.gestacaoescolar.dto.professor.ProfessorRequisicaoDTO;
import com.weg.gestacaoescolar.dto.professor.ProfessorRespostaDTO;
import com.weg.gestacaoescolar.mapper.ProfessorMapper;
import com.weg.gestacaoescolar.model.Professor;
import org.springframework.stereotype.Service;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProfessorService {
    private final ProfessorMapper mapper;
    private final ProfessorDAO repository;

    public ProfessorService(ProfessorDAO repository,ProfessorMapper mapper){
        this.repository = repository;
        this.mapper = mapper;
    }

    public ProfessorRespostaDTO criarProfessor(ProfessorRequisicaoDTO requisicaoDTO) throws SQLException {
        return mapper.paraRespostaDTO(repository.salvarProfessor(mapper.paraEntidade(requisicaoDTO)));
    }

    public List<ProfessorRespostaDTO> listarProfessor() throws SQLException{
        List<Professor> professors = repository.listProfessores();
        List<ProfessorRespostaDTO> respostaDTOS = new ArrayList<>();

        for (Professor professor : professors){
            respostaDTOS.add(mapper.paraRespostaDTO(professor));
        }
        return respostaDTOS;
    }

    public ProfessorRespostaDTO pesquisarProfessorPorId(int id) throws SQLException {
        Professor professors = repository.buscarProfessorPorId(id);

        if (professors.getId() == 0){
            throw new RuntimeException("ID não existe!!");
        }
        return mapper.paraRespostaDTO(professors);
    }

    public void deletarProfessor(int id) throws SQLException {
        if (!repository.professorExistePorId(id)){
            throw new RuntimeException("ID não existe!!");
        }
        repository.deletarProfessor(id);
    }

    public ProfessorRespostaDTO atualizarProfessor(int id, ProfessorRequisicaoDTO requisicaoDTO) throws SQLException {
        Professor professor = repository.buscarProfessorPorId(id);

        if (professor.getId() == 0){
            throw new RuntimeException("ID não existe!!");
        }
        Professor profNew = mapper.paraUpdate(requisicaoDTO,professor);
        repository.salvarProfessor(profNew);
        return mapper.paraRespostaDTO(profNew);
    }
}
