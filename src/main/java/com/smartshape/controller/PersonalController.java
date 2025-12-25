package com.smartshape.controller;

import com.smartshape.model.Personal;
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

    @Autowired
    private PersonalService personalService;

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
        return ResponseEntity.noContent().build();
    }

}
