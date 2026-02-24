package com.senai.Biblioteca.service;

import com.senai.Biblioteca.dao.UsuarioRepository;
import com.senai.Biblioteca.model.Usuario;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class UsuarioService {
    private UsuarioRepository repository;

    public UsuarioService(UsuarioRepository repository) {
        this.repository = repository;
    }

    public Usuario salvar(Usuario usuario) throws SQLException {
        return repository.salvar(usuario);
    }

    public List<Usuario> listarTodos () throws SQLException {
        return repository.listarTodos();
    }

    public Usuario buscarPorId (long id) throws SQLException {
        return repository.buscarPorId(id);
    }

    public void atualizar(Long id, Usuario usuario) throws SQLException{
        Usuario usuarioNoBanco = repository.buscarPorId(id);
        usuarioNoBanco.update(usuario.getNome(), usuario.getEmail());

        repository.atualizar(id, usuario);
    }

    public void deletar(long id) throws SQLException{
        repository.deletar(id);
    }

}
