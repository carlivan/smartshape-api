package com.smartshape.service;

import com.smartshape.model.Personal;
import com.smartshape.repository.PersonalRepository;
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

    public Personal atualizar(Personal personal){
        Personal aux = listarPorId(personal.getId());
        if (aux != null){
            aux.setNome(personal.getNome());
            aux.setCref(personal.getCref());
            aux.setEmail(personal.getEmail());
            aux.setSenha(personal.getSenha());
            return personalRepository.save(aux);
        }
        return null;
    }

    public String deletar(Long id){
        Personal aux = listarPorId(id);
        if (aux != null){
            personalRepository.deleteById(id);
            return "Deletado com sucesso!!!";
        }
        return "Deletado ou não encontrado!!";
    }
}
