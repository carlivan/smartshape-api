package com.smartshape.controller;

import com.smartshape.dto.ClienteDTO;
import com.smartshape.model.Cliente;
import com.smartshape.model.Personal;
import com.smartshape.repository.ClienteRepository;
import com.smartshape.service.ClienteService;
import com.smartshape.service.PersonalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/personais")
public class PersonalController {

    private final PersonalService personalService;
    private final ClienteService clienteService;
    private final ClienteRepository clienteRepository;

    public PersonalController(PersonalService personalService, ClienteService clienteService, ClienteRepository clienteRepository) {
        this.personalService = personalService;
        this.clienteService = clienteService;
        this.clienteRepository = clienteRepository;
    }

    @GetMapping
    public ResponseEntity<List<Personal>> listar(){
        return ResponseEntity.ok(personalService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Personal> listarPorId(@PathVariable Long id){
        return ResponseEntity.ok(personalService.listarPorId(id));
    }

    @PostMapping
    public ResponseEntity<Personal> criar(@RequestBody Personal personal){
        Personal aux = personalService.salvar(personal);

        // URI
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(aux.getId())
                .toUri();

        return ResponseEntity.created(uri).body(aux);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Personal> atualizar(@PathVariable Long id, @RequestBody Personal personal){
        return ResponseEntity.ok(personalService.atualizar(id, personal));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id){
        personalService.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/quantidade-clientes")
    public ResponseEntity<Long> quantidadeClientes(@PathVariable Long id){
        long quantidade = personalService.qtdClientes(id);
        return ResponseEntity.ok(quantidade);
    }

    // Listar somente nome dos clientes
    @GetMapping("{personalId}/clientes/lista")
    public ResponseEntity<List<ClienteDTO>> listarClientes(@PathVariable Long personalId){
        return ResponseEntity.ok(clienteRepository.findByPersonalId(personalId));
    }

    // Detalhar determinado aluno
    @GetMapping("/{personalId}/clientes/{clienteId}")
    public ResponseEntity<Cliente> verDetalhesAluno(@PathVariable Long personalId, @PathVariable Long clienteId){
        return ResponseEntity.ok(clienteService.buscarAlunoDoPersonal(personalId, clienteId));
    }

    // Editar determinado aluno
    @PutMapping("/{personalId}/clientes/{clienteId}")
    public ResponseEntity<Cliente> editarAluno(@PathVariable Long personalId, @PathVariable Long clienteId,
                                               @RequestBody Cliente cliente){
        return ResponseEntity.ok(clienteService.atualizarAlunoDoPersonal(personalId, clienteId, cliente));
    }


}
