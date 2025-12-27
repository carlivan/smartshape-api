package com.smartshape.controller;

import com.smartshape.model.Treino;
import com.smartshape.service.TreinoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/treinos")
public class TreinoController {

    private final TreinoService treinoService;

    public TreinoController(TreinoService treinoService) {
        this.treinoService = treinoService;
    }

    @PostMapping
    public ResponseEntity<Treino> criarTreino(@RequestBody Treino treino){

        Treino novoTreino = treinoService.salvar(treino);

        return new ResponseEntity<>(novoTreino, HttpStatus.CREATED);
    }

    @GetMapping
    public List<Treino> listarTreinos(){
        return treinoService.buscarTreinos();
    }
}
