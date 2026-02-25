package com.senai.Biblioteca.service;

import com.senai.Biblioteca.dao.UsuarioRepository;
import com.senai.Biblioteca.dto.usuario.UsuarioRequest;
import com.senai.Biblioteca.dto.usuario.UsuarioResponse;
import com.senai.Biblioteca.mapper.UsuarioMapper;
import com.senai.Biblioteca.model.Usuario;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class UsuarioService {
    private final UsuarioRepository repository;
    private final UsuarioMapper mapper;

    public UsuarioService(UsuarioRepository repository, UsuarioMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public UsuarioResponse salvar(UsuarioRequest request) throws SQLException {
        Usuario usuario = mapper.paraEntidade(request);
        Usuario usuarioSalvo = repository.salvar(usuario);

        return mapper.paraResposta(usuarioSalvo);
    }

    public List<UsuarioResponse> listarTodos () throws SQLException {
       List<Usuario> usuarios = repository.listarTodos();

       return usuarios.stream()
               .map(mapper::paraResposta)
               .toList();
    }

    public UsuarioResponse buscarPorId (long id) throws SQLException {
        Usuario usuario = repository.buscarPorId(id);
        return mapper.paraResposta(usuario);
    }

    public void atualizar(Long id, UsuarioRequest usuario) throws SQLException{

        Usuario usuarioNoBanco = repository.buscarPorId(id);
        usuarioNoBanco.update(usuario.nome(), usuario.email());

        repository.atualizar(id, usuarioNoBanco);
    }

    public void deletar(long id) throws SQLException{
        repository.deletar(id);
    }

}
