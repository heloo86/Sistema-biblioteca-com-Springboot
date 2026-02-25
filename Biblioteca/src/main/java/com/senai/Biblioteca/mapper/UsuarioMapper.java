package com.senai.Biblioteca.mapper;

import com.senai.Biblioteca.dto.usuario.UsuarioRequest;
import com.senai.Biblioteca.dto.usuario.UsuarioResponse;
import com.senai.Biblioteca.model.Usuario;

public class UsuarioMapper {

    public Usuario paraEntidade(UsuarioRequest request){
        return new Usuario(
                request.nome(),
                request.email()
        );
    }

    public UsuarioResponse paraResposta(Usuario usuario){
        return new UsuarioResponse(
                usuario.getId(),
                usuario.getNome(),
                usuario.getEmail()
        );
    }

}
