package com.smartshape.config;

import com.smartshape.model.Cliente;
import com.smartshape.model.Exercicio;
import com.smartshape.model.Personal;
import com.smartshape.repository.ClienteRepository;
import com.smartshape.repository.ExercicioRepository;
import com.smartshape.repository.PersonalRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class DataInitializer implements CommandLineRunner {

    private final ExercicioRepository exercicioRepository;
    private final PersonalRepository personalRepository;
    private final ClienteRepository clienteRepository;

    public DataInitializer(ExercicioRepository exercicioRepository, PersonalRepository personalRepository, ClienteRepository clienteRepository) {
        this.exercicioRepository = exercicioRepository;
        this.personalRepository = personalRepository;
        this.clienteRepository = clienteRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        // inicializar exercicios
        if (exercicioRepository.count() == 0) {
            exercicioRepository.save(new Exercicio("Supino Reto", "Peitoral", "url-video-1"));
            exercicioRepository.save(new Exercicio("Agachamento Livre", "Pernas", "url-video-2"));
            exercicioRepository.save(new Exercicio("Rosca Direta", "Bíceps", "url-video-3"));
            System.out.println("✅ Biblioteca de exercícios inicializada!");
        }

        // inicializar personais
        if (personalRepository.count() == 0){
            Personal p1 = new Personal();
            p1.setNome("Fulano Personal");
            p1.setEmail("fulano@mail.com");
            p1.setSenha("123456");
            p1.setCref("123456-G/SP");

            Personal p2 = new Personal();
            p2.setNome("Carla Trainer");
            p2.setEmail("carla@mail.com");
            p2.setSenha("123456");
            p2.setCref("654321-G/SP");

            personalRepository.saveAll(Arrays.asList(p1, p2));
            System.out.println("✅ Personais inicializados!");

            // 3. Inicializar Alunos (Clientes) vinculados aos personais acima
            if (clienteRepository.count() == 0) {
                Cliente c1 = new Cliente();
                c1.setNome("João Silva");
                c1.setEmail("joao@email.com");
                c1.setSenha("123");
                c1.setPersonal(p1); // Vinculando o João ao Ricardo

                Cliente c2 = new Cliente();
                c2.setNome("Maria Oliveira");
                c2.setEmail("maria@email.com");
                c2.setSenha("123");
                c2.setPersonal(p1); // Maria também treina com o Ricardo

                Cliente c3 = new Cliente();
                c3.setNome("Lucas Souza");
                c3.setEmail("lucas@email.com");
                c3.setSenha("123");
                c3.setPersonal(p2); // Lucas treina com a Carla

                clienteRepository.saveAll(Arrays.asList(c1, c2, c3));
                System.out.println("✅ Clientes inicializados e vinculados!");
            }
        }
    }
}
