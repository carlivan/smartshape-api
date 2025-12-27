package com.smartshape.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Treino {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome; // ex: treino A - Força

    @ManyToOne
    @JsonIgnoreProperties({"senha", "personal"})
    private Cliente cliente; // dono do treino

    @ManyToOne
    private Personal personal; // Quem montou o treino (nulo = aluno montou)

    @JsonManagedReference
    @OneToMany(mappedBy = "treino", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemTreino> itens; // A lista de exercicio do treino

    public Treino() {
    }

    public Treino(String nome, Cliente cliente, Personal personal, List<ItemTreino> itens) {
        this.nome = nome;
        this.cliente = cliente;
        this.personal = personal;
        this.itens = itens;
    }

    public void adicionarItem(ItemTreino item){
        this.itens.add(item);
        item.setTreino(this);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Personal getPersonal() {
        return personal;
    }

    public void setPersonal(Personal personal) {
        this.personal = personal;
    }

    public List<ItemTreino> getItens() {
        return itens;
    }

    public void setItens(List<ItemTreino> itens) {
        this.itens = itens;
    }
}
