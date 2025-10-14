package com.weg.gestacaoescolar.controller;

import com.weg.gestacaoescolar.dto.turma.TurmaRequisicaoDTO;
import com.weg.gestacaoescolar.dto.turma.TurmaRespostaDTO;
import com.weg.gestacaoescolar.service.TurmaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@Controller
@RequestMapping("/turmas")
public class TurmaController {

    private final TurmaService service;

    public TurmaController(TurmaService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<TurmaRespostaDTO> criarTurma(
            @Valid @RequestBody TurmaRequisicaoDTO requisicaoDTO
    ) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(service.criarTurma(requisicaoDTO));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .build();
        }
    }

    @GetMapping
    public ResponseEntity<List<TurmaRespostaDTO>> listarTurma() {
        try {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(service.buscarTurmas());
        } catch (SQLException e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<TurmaRespostaDTO> buscarTurmaPorId(
            @PathVariable int id
        ){
        try{
            return ResponseEntity.status(HttpStatus.OK)
                    .body(service.pesquisarTurmaPorId(id));
        } catch (SQLException e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .build();
        }
    }

//    @PutMapping("/{id}")
//    public ResponseEntity<TurmaRespostaDTO> atualizarTurma(
//            @PathVariable int id,
//            @Valid @RequestBody TurmaRequisicaoDTO requisicaoDTO
//        ){
//        try{
//            return ResponseEntity.status(HttpStatus.OK)
//                    .body(service.atualizarTurma(id,requisicaoDTO));
//        } catch (SQLException e){
//            e.printStackTrace();
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                    .build();
//        }
//    }

    @DeleteMapping("/{id}")
    public ResponseEntity<TurmaRespostaDTO> deletarTurma(
            @PathVariable int id
        ){
        try{
            service.deletarTurma(id);
            return ResponseEntity.status(HttpStatus.OK)
                    .build();
        } catch (SQLException e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .build();
        }
    }
}