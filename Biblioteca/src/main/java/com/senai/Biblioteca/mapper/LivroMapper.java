package com.senai.Biblioteca.mapper;

import com.senai.Biblioteca.dto.Livro.LivroRequest;
import com.senai.Biblioteca.dto.Livro.LivroResponse;
import com.senai.Biblioteca.model.Livro;

public class LivroMapper {

    public Livro paraEntidade(LivroRequest dto){
        Livro livro = new Livro(
                dto.titulo(),
                dto.autor(),
                dto.anoPublicacao()
        );
        return livro;
    }

    public  Livro paraEntidade(LivroResponse dto){
        Livro livro = new Livro(
                dto.titulo(),
                dto.autor(),
                dto.anoPublicacao()
        );
        return livro;
    }

    public LivroResponse paraResposta(Livro livro){
        LivroResponse response = new LivroResponse(
                livro.getId(),
                livro.getTitulo(),
                livro.getAutor(),
                livro.getAnoPublicacao()
        );
        return response;
    }


}
