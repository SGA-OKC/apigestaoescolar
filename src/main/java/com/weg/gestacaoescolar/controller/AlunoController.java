package com.weg.gestacaoescolar.controller;

import com.weg.gestacaoescolar.dto.aluno.AlunoRequisicaoDTO;
import com.weg.gestacaoescolar.dto.aluno.AlunoRespostaDTO;
import com.weg.gestacaoescolar.service.AlunoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/aluno")
public class AlunoController {

    private AlunoService service;

    public AlunoController(AlunoService service){
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<AlunoRespostaDTO>> listarAlunos (){
        try {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(service.listarAluno());
        } catch (SQLException e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<AlunoRespostaDTO> buscarAlunoPorId(
            @PathVariable int id
        ){
        try {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(service.pesquisarAlunoPorId(id));
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .build();
        }
    }

    @PostMapping
    public ResponseEntity<AlunoRespostaDTO> criarAluno (
            @RequestBody AlunoRequisicaoDTO requisicaoDTO
            ){
        try{
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(service.criarAlunos(requisicaoDTO));
        } catch (SQLException e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<AlunoRespostaDTO> atualizarAluno(
            @PathVariable int id,
            @RequestBody AlunoRequisicaoDTO requisicaoDTO
        ){
        try{
            return ResponseEntity.status(HttpStatus.OK)
                    .body(service.atualizarAluno(id,requisicaoDTO));
        } catch (Exception e) {
            return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<AlunoRespostaDTO> deletarAluno(
            @PathVariable int id
        ){
        try{
            service.deletarAluno(id);
            return ResponseEntity.status(HttpStatus.OK)
                    .build();
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .build();
        }
    }
}
