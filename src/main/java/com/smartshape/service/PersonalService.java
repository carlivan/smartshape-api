package com.smartshape.service;

import com.smartshape.model.Personal;
import com.smartshape.repository.PersonalRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonalService {

    @Autowired
    private PersonalRepository personalRepository;

    public List<Personal> listarTodos(){
        return personalRepository.findAll();
    }

    public Personal listarPorId(Long id){
        Optional<Personal> personal = personalRepository.findById(id);
        return personal.orElse(null);
    }

    public Personal salvar(Personal personal){
        return personalRepository.save(personal);
    }

    public Personal atualizar(Long id, Personal personal){
        Personal aux = personalRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Personal com id: " + id + " não encontrado!!!"));

        BeanUtils.copyProperties(personal, aux, "id");

        return personalRepository.save(aux);
    }

    public void deletar(Long id){
        if(!personalRepository.existsById(id)){
            throw new RuntimeException("Personal não encontrado ou já deletado!!!");
        }
        personalRepository.deleteById(id);
    }
}
