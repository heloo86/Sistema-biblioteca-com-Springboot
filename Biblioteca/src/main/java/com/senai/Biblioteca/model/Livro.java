package com.senai.Biblioteca.model;

import java.math.BigInteger;
import java.time.LocalDate;

public class Livro {
    long id;
    String titulo;
    String autor;
    int anoPublicacao;

    public Livro(long id, String titulo, String autor, int anoPublicacao) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.anoPublicacao = anoPublicacao;
    }

    public Livro(String titulo, String autor, int anoPublicacao) {
        this.titulo = titulo;
        this.autor = autor;
        this.anoPublicacao = anoPublicacao;
    }

    public Livro() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public int getAnoPublicacao() {
        return anoPublicacao;
    }

    public void setAnoPublicacao(int anoPublicacao) {
        this.anoPublicacao = anoPublicacao;
    }

    public void update(String titulo, String autor, int anoPublicacao) {

        if(!titulo.isBlank()){
            this.titulo = titulo;
        }

        if(!autor.isBlank()){
            this.autor = autor;
        }

        if(anoPublicacao > 1000 && anoPublicacao < 3000){
            this.anoPublicacao = anoPublicacao;
        }
    }
}
