package com.smartshape.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

@Entity
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String email;
    private String senha;

    @Enumerated(EnumType.STRING)
    private Genero genero;

    //muitos clientes para um personal
    //optional = true permite que o cliente sejacadastrado sem um personal
    @ManyToOne
    @JoinColumn(name = "personal_id")
    @JsonIgnoreProperties({"senha", "cliente"})
    private Personal personal;

    private boolean usaTreinoPersonal = false;

    public Cliente() {
    }

    public Cliente(String nome, String email, String senha, Genero genero, Personal personal, boolean usaTreinoPersonal) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.genero = genero;
        this.personal = personal;
        this.usaTreinoPersonal = usaTreinoPersonal;
    }

    public Cliente(Long id, String nome, String email, String senha, Genero genero,Personal personal, boolean usaTreinoPersonal) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.genero = genero;
        this.personal = personal;
        this.usaTreinoPersonal = usaTreinoPersonal;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }

    public Personal getPersonal() {
        return personal;
    }

    public void setPersonal(Personal personal) {
        this.personal = personal;
    }

    public boolean isUsaTreinoPersonal() {
        return usaTreinoPersonal;
    }

    public void setUsaTreinoPersonal(boolean usaTreinoPersonal) {
        this.usaTreinoPersonal = usaTreinoPersonal;
    }
}
