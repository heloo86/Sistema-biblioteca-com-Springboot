package com.senai.Biblioteca.service;

import com.senai.Biblioteca.dao.LivroRepository;
import com.senai.Biblioteca.dao.UsuarioRepository;
import com.senai.Biblioteca.dto.Livro.LivroRequest;
import com.senai.Biblioteca.dto.Livro.LivroResponse;
import com.senai.Biblioteca.mapper.LivroMapper;
import com.senai.Biblioteca.model.Livro;
import com.senai.Biblioteca.model.Usuario;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class LivroService {

    private final LivroRepository repository;
    private final LivroMapper mapper;

    public LivroService(LivroRepository repository, LivroMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public Livro salvar(LivroRequest request) throws SQLException {
        Livro livro = mapper.paraEntidade(request);
        return repository.salvar(livro);
    }

    public List<LivroResponse> listarTodos () throws SQLException {
        return repository.listarTodos();
    }

    public LivroResponse buscarPorId (Long id) throws SQLException {
        return repository.buscarPorId(id);
    }

    public void atualizar(Long id, LivroRequest livro) throws SQLException{

        LivroResponse livroNoBanco = repository.buscarPorId(id);
        Livro livroAtualizado = mapper.paraEntidade(livroNoBanco);

        livroAtualizado.update(livro.titulo(), livro.autor(), livro.anoPublicacao());

        repository.atualizar(id, livroAtualizado);
    }

    public void deletar(Long id) throws SQLException{
        repository.deletar(id);
    }
}
