package com.weg.gestacaoescolar.service;

import com.weg.gestacaoescolar.dao.CursoDAO;
import com.weg.gestacaoescolar.dto.curso.CursoRequisicaoDTO;
import com.weg.gestacaoescolar.dto.curso.CursoRespostaDTO;
import com.weg.gestacaoescolar.mapper.CursoMapper;
import com.weg.gestacaoescolar.model.Curso;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CursoService {

    private final CursoMapper mapper;
    private final CursoDAO repository;
    private final Map<Integer, List<Integer>> cursoProfessoresMap;


    public CursoService(CursoMapper mapper, CursoDAO repository){
        this.mapper = mapper;
        this.repository = repository;
        this.cursoProfessoresMap = new HashMap<>();
    }

    public CursoRespostaDTO criarCurso(CursoRequisicaoDTO requisicaoDTO) throws SQLException {
        List<String> nomesProf = repository.listaProfessor(requisicaoDTO.listProfIds());

        Curso newCurso = repository.salvarCurso(mapper.paraEntidade(requisicaoDTO));

        cursoProfessoresMap.put(newCurso.getId(), requisicaoDTO.listProfIds());

        return mapper.paraRespostaDTO(newCurso, nomesProf);
    }



//    public List<CursoRespostaDTO> listarCursos() throws SQLException {
//        List<Curso> cursos = repository.listarCurso();
//        List<CursoRespostaDTO> respostaDTOS = new ArrayList<>();
//
//        for (Curso curso : cursos){
//            respostaDTOS.add(mapper.paraRespostaDTO(curso));
//        }
//        return respostaDTOS;
//    }

//    public CursoRespostaDTO pesquisarAlunoPorId(int id) throws SQLException {
//        Curso curso = repository.buscarCursoPorId(id);
//
//        if (curso == null){
//            throw new RuntimeException("ID não existe!!");
//        }
//        return mapper.paraRespostaDTO(curso);
//    }
//
//    public void deletarCurso(int id) throws SQLException {
//        if (!repository.cursoExistePorId(id)) {
//            throw new RuntimeException("ID não existe!!");
//        }
//        repository.deletarCurso(id);
//    }
//
//    public CursoRespostaDTO atualizarCurso(int id,CursoRequisicaoDTO requisicaoDTO) throws SQLException {
//        Curso curso = repository.buscarCursoPorId(id);
//
//        if (curso.getId() == 0){
//            throw new RuntimeException("ID não existe!!");
//        }
//        Curso newCurso = mapper.paraUpdate(requisicaoDTO,curso);
//        repository.salvarCurso(newCurso);
//        return mapper.paraRespostaDTO(newCurso);
//    }
}
