package com.smartshape.service;

import com.smartshape.model.Personal;
import com.smartshape.repository.PersonalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonalService {

    @Autowired
    private PersonalRepository personalRepository;

    public List<Personal> listarTodos(){
        return personalRepository.findAll();
    }

    public Personal salvar(Personal personal){
        return personalRepository.save(personal);
    }
}
