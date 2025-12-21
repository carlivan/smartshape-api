package com.smartshape.controller;

import com.smartshape.model.Personal;
import com.smartshape.service.PersonalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/personais")
public class PersonalController {

    @Autowired
    private PersonalService personalService;

    @GetMapping
    public List<Personal> listar(){
        return personalService.listarTodos();
    }

    @GetMapping("/{id}")
    public Personal listarPorId(@PathVariable Long id){
        return personalService.listarPorId(id);
    }

    @PostMapping
    public Personal criar(@RequestBody Personal personal){
        return personalService.salvar(personal);
    }

    @PutMapping
    public Personal atualizar(@RequestBody Personal personal){
        return personalService.atualizar(personal);
    }

    @DeleteMapping("/{id}")
    public String deletar(@PathVariable Long id){
        return personalService.deletar(id);
    }

}
