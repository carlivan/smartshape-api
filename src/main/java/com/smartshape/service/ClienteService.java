package com.smartshape.service;

import com.smartshape.model.Cliente;
import com.smartshape.model.Personal;
import com.smartshape.repository.ClienteRepository;
import com.smartshape.repository.PersonalRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;
    private final PersonalRepository personalRepository;

    public ClienteService(ClienteRepository clienteRepository, PersonalRepository personalRepository) {
        this.clienteRepository = clienteRepository;
        this.personalRepository = personalRepository;
    }

    public Cliente listarUm(Long id){
        return clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente inexistente  ou não encontrado!!!"));
    }

    public List<Cliente> listar(){
        return clienteRepository.findAll();
    }

    public Cliente salvar(Cliente cliente){

        // Verificar a presença de um personal
        if(cliente.getPersonal() != null && cliente.getPersonal().getId() != null){
            Long personalId = cliente.getPersonal().getId();
            Personal personal = personalRepository.findById(personalId)
                    .orElseThrow(() -> new RuntimeException("Personal com id: " + personalId + " não cadastrado ou inexistente"));

            // Vincular o personal encontrado
            cliente.setPersonal(personal);
        }
        return clienteRepository.save(cliente);
    }

    public Cliente atualizar(Long id, Cliente novoCliente){
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Cliente inexistente ou não encontrado!!!"));

        BeanUtils.copyProperties(novoCliente, cliente, "id");

        return clienteRepository.save(cliente);
    }

    public void deletar(Long id){
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente inexistente ou já deletado!!!"));

        clienteRepository.deleteById(id);
    }

    public Cliente buscarAlunoDoPersonal(Long personalId, Long clienteId){
        Cliente cliente = listarUm(clienteId);

        if (cliente.getPersonal() == null || !cliente.getPersonal().getId().equals(personalId)){
            throw new RuntimeException("Acesso negado: Aluno não pertence ao personal.");
        }

        return cliente;
    }

    public Cliente atualizarAlunoDoPersonal(Long personalId, Long clienteId, Cliente cliente){
        Cliente aux = buscarAlunoDoPersonal(personalId, clienteId);

        BeanUtils.copyProperties(cliente, aux, "id", "personal", "senha");

        return clienteRepository.save(aux);
    }

}
