package com.smartshape.controller;

import com.smartshape.model.Cliente;
import com.smartshape.model.Treino;
import com.smartshape.service.ClienteService;
import com.smartshape.service.TreinoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
    private final ClienteService clienteService;
    private final TreinoService treinoService;

    public ClienteController(ClienteService clienteService, TreinoService treinoService) {
        this.clienteService = clienteService;
        this.treinoService = treinoService;
    }

    @GetMapping
    public ResponseEntity<List<Cliente>> listar(){
        return ResponseEntity.ok(clienteService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> listarUm(@PathVariable Long id){
        return ResponseEntity.ok(clienteService.listarUm(id));
    }

    @PostMapping
    public ResponseEntity<Cliente> salvar(@RequestBody Cliente cliente){
        Cliente aux = clienteService.salvar(cliente);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(aux.getId())
                .toUri();

        return ResponseEntity.created(uri).body(aux);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cliente> atualizar(@PathVariable Long id, @RequestBody Cliente cliente){
        return ResponseEntity.ok(clienteService.atualizar(id, cliente));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id){
        clienteService.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/treinos")
    public ResponseEntity<List<Treino>> listarTreinosDoAluno(@PathVariable Long id){
        List<Treino> treinos = treinoService.buscarTreinosDoAluno(id);
        return ResponseEntity.ok(treinos);
    }
}
