package com.weg.gestacaoescolar.controller;

import com.weg.gestacaoescolar.dto.turma.TurmaRequisicaoDTO;
import com.weg.gestacaoescolar.dto.turma.TurmaRespostaDTO;
import com.weg.gestacaoescolar.service.TurmaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
                    .body(service.respostaDTO(requisicaoDTO));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .build();
        }
    }
}