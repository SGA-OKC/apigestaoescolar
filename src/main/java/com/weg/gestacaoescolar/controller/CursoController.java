package com.weg.gestacaoescolar.controller;

import com.weg.gestacaoescolar.dto.curso.CursoRequisicaoDTO;
import com.weg.gestacaoescolar.dto.curso.CursoRespostaDTO;
import com.weg.gestacaoescolar.service.CursoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/curso")
public class CursoController {

    private final CursoService service;

    public CursoController(CursoService service){
        this.service = service;
    }
    @PostMapping
    public ResponseEntity<CursoRespostaDTO> criarCurso(
            @Valid @RequestBody CursoRequisicaoDTO requisicaoDTO
    ){
        try{
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(service.criarCurso(requisicaoDTO));
        } catch (SQLException e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .build();
        }
    }
    @GetMapping
    public ResponseEntity<List<CursoRespostaDTO>> listarCursos(){
        try{
            return ResponseEntity.status(HttpStatus.OK)
                    .body(service.listarCursos());
        } catch (SQLException e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<CursoRespostaDTO> buscarCursoPorId(
            @PathVariable int id
        ){
        try{
            return ResponseEntity.status(HttpStatus.OK)
                    .body(service.pesquisarCursoPorId(id));
        } catch (SQLException e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<CursoRespostaDTO> atualizarCurso(
            @PathVariable int id,
            @Valid @RequestBody CursoRequisicaoDTO requisicaoDTO
        ){
        try{
            return ResponseEntity.status(HttpStatus.OK)
                    .body(service.atualizarCurso(id,requisicaoDTO));
        } catch (SQLException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .build();
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<CursoRespostaDTO> deletarCurso(
            @PathVariable int id
        ){
        try{
            service.deletarCurso(id);
            return ResponseEntity.status(HttpStatus.OK)
                    .build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .build();
        }
    }
}
