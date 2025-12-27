package com.smartshape.service;

import com.smartshape.model.Cliente;
import com.smartshape.model.Exercicio;
import com.smartshape.model.ItemTreino;
import com.smartshape.model.Treino;
import com.smartshape.repository.ClienteRepository;
import com.smartshape.repository.ExercicioRepository;
import com.smartshape.repository.TreinoRepository;
import jakarta.transaction.Transactional;
import org.hibernate.cache.spi.support.AbstractReadWriteAccess;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TreinoService {
    private final TreinoRepository treinoRepository;
    private final ClienteRepository clienteRepository;
    private final ExercicioRepository exercicioRepository;

    public TreinoService(TreinoRepository treinoRepository, ClienteRepository clienteRepository, ExercicioRepository exercicioRepository) {
        this.treinoRepository = treinoRepository;
        this.clienteRepository = clienteRepository;
        this.exercicioRepository = exercicioRepository;
    }

    @Transactional
    public Treino salvar(Treino treino){
        // Verifica se o cliente do treino existe
        Cliente cliente = clienteRepository.findById(treino.getCliente().getId())
                .orElseThrow(() -> new RuntimeException("Cliente inexistente ou não encontrado"));
        treino.setCliente(cliente);

        System.out.println(treino);

        // Preparar os itens
        for (ItemTreino item: treino.getItens()){
            // validar se o exercicio existe na biblioteca
            Exercicio ex = exercicioRepository.findById(item.getExercicio().getId())
                    .orElseThrow(() -> new RuntimeException("Exercicio não existe na biblioteca"));

            item.setExercicio(ex);
            item.setTreino(treino);
        }
        System.out.println(treino);
        return treinoRepository.save(treino);
    }

    public List<Treino> buscarTreinos(){
        return treinoRepository.findAll();
    }

    public List<Treino> buscarTreinosDoAluno(Long clienteId){
        return treinoRepository.findByClienteId(clienteId);
    }
}
