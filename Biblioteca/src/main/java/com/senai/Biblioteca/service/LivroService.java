package com.senai.Biblioteca.service;

import com.senai.Biblioteca.dao.LivroRepository;
import com.senai.Biblioteca.dao.UsuarioRepository;
import com.senai.Biblioteca.model.Livro;
import com.senai.Biblioteca.model.Usuario;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class LivroService {

    private LivroRepository repository;

    public LivroService(LivroRepository repository) {
        this.repository = repository;
    }

    public Livro salvar(Livro Livro) throws SQLException {
        return repository.salvar(Livro);
    }

    public List<Livro> listarTodos () throws SQLException {
        return repository.listarTodos();
    }

    public Livro buscarPorId (Long id) throws SQLException {
        return repository.buscarPorId(id);
    }

    public void atualizar(Long id, Livro livro) throws SQLException{
        Livro LivroNoBanco = repository.buscarPorId(id);
        LivroNoBanco.update(livro.getTitulo(), livro.getAutor(), livro.getAnoPublicacao());

        repository.atualizar(id, LivroNoBanco);
    }

    public void deletar(Long id) throws SQLException{
        repository.deletar(id);
    }
}
