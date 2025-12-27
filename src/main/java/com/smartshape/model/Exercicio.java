package com.smartshape.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Exercicio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome; // Nome do exercicio ex: Supino reto, legpress
    private String grupoMuscular; // ex: peitoral, dorsal, quadriceps
    private String linkVideo; // URL do YouTube com exemplo da execução

    public Exercicio() {
    }

    public Exercicio(String nome, String grupoMuscular, String linkVideo) {
        this.nome = nome;
        this.grupoMuscular = grupoMuscular;
        this.linkVideo = linkVideo;
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

    public String getGrupoMuscular() {
        return grupoMuscular;
    }

    public void setGrupoMuscular(String grupoMuscular) {
        this.grupoMuscular = grupoMuscular;
    }

    public String getLinkVideo() {
        return linkVideo;
    }

    public void setLinkVideo(String linkVideo) {
        this.linkVideo = linkVideo;
    }
}
