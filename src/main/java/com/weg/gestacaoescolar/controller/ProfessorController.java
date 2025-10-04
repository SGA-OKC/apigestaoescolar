package com.weg.gestacaoescolar.controller;

import com.weg.gestacaoescolar.dto.professor.ProfessorRequisicaoDTO;
import com.weg.gestacaoescolar.dto.professor.ProfessorRespostaDTO;
import com.weg.gestacaoescolar.service.ProfessorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/professor")
public class ProfessorController {

    private final ProfessorService service;

    public ProfessorController(ProfessorService service){
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<ProfessorRespostaDTO>> listarProfessor (){
        try{
            return ResponseEntity.status(HttpStatus.OK)
                    .body(service.listarProfessor());
        } catch (SQLException e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProfessorRespostaDTO> buscarProfessorPorId(
            @PathVariable int id
        ){
        try{
            return ResponseEntity.status(HttpStatus.OK)
                    .body(service.pesquisarProfessorPorId(id));
        } catch (SQLException e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .build();
        }
    }

    @PostMapping
    public ResponseEntity<ProfessorRespostaDTO> criarProfessor(
            @RequestBody ProfessorRequisicaoDTO requisicaoDTO
        ){
        try{
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(service.criarProfessor(requisicaoDTO));
        } catch (SQLException e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProfessorRespostaDTO> atualizarProfessor(
            @PathVariable int id,
            @RequestBody ProfessorRequisicaoDTO requisicaoDTO
        ){
        try{
            return ResponseEntity.status(HttpStatus.OK)
                    .body(service.atualizarProfessor(id,requisicaoDTO));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ProfessorRespostaDTO> deletarProfessor(
            @PathVariable int id
        ){
        try{
            service.deletarProfessor(id);
            return ResponseEntity.status(HttpStatus.OK)
                    .build();
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .build();
        }
    }

}
