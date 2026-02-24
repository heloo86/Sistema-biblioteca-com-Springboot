package com.senai.Biblioteca.model;


public class Usuario {

    Long id;
    String nome;
    String email;

    public Usuario(Long id, String nome, String email) {
        this.id = id;
        this.nome = nome;
        this.email = email;
    }

    public Usuario(String nome, String email) {
        this.nome = nome;
        this.email = email;
    }

    public Usuario() {
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

    // Regra de negocio
    public void update(String nome, String email) {
        if(!nome.isBlank()){
            this.nome = nome;
        }
        if(!email.isBlank()){
            this.email = email;
        }
    }
}
