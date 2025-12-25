package com.smartshape.controller;

import com.smartshape.model.Cliente;
import com.smartshape.service.ClienteService;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
    private ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
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
}
