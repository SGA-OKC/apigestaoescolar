package com.weg.gestacaoescolar.controller;

import com.weg.gestacaoescolar.dto.aula.AulaRequisicaoDTO;
import com.weg.gestacaoescolar.dto.aula.AulaRespostaDTO;
import com.weg.gestacaoescolar.service.AulaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/aulas")
public class AulaController {
    private final AulaService service;
    public AulaController(AulaService service){
        this.service = service;
    }
    @PostMapping
    public ResponseEntity<AulaRespostaDTO> criarAula(
            @Valid @RequestBody AulaRequisicaoDTO requisicaoDTO
    ){
        try {
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(service.criarAula(requisicaoDTO));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .build();
        }
    }
    @GetMapping
    public ResponseEntity<List<AulaRespostaDTO>> listarAulas(){
        try{
            return ResponseEntity.status(HttpStatus.OK)
                    .body(service.buscarAulas());
        } catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .build();
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<AulaRespostaDTO> pesquisarAulaPorId(
            @PathVariable int id
        ){
        try{
            return ResponseEntity.status(HttpStatus.OK)
                    .body(service.buscarAulaPorId(id));
        } catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .build();
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<AulaRespostaDTO> atualizarAulas(
            @PathVariable int id,
            @Valid @RequestBody AulaRequisicaoDTO requisicaoDTO
        ){
        try{
            return ResponseEntity.status(HttpStatus.OK)
                    .body(service.atualizarAula(id,requisicaoDTO));
        } catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .build();
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<AulaRespostaDTO> deletarAulas(
            @PathVariable int id
        ){
        try{
            service.deletarAula(id);
            return ResponseEntity.status(HttpStatus.OK)
                    .build();
        } catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .build();
        }
    }
}
