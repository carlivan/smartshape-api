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

    @PostMapping
    public Personal criar(@RequestBody Personal personal){
        return personalService.salvar(personal);
    }
}
