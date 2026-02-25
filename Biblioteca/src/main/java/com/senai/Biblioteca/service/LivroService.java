package com.senai.Biblioteca.service;

import com.senai.Biblioteca.dao.LivroRepository;
import com.senai.Biblioteca.dto.Livro.LivroRequest;
import com.senai.Biblioteca.dto.Livro.LivroResponse;
import com.senai.Biblioteca.mapper.LivroMapper;
import com.senai.Biblioteca.model.Livro;
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

    public LivroResponse salvar(LivroRequest request) throws SQLException {
        Livro livro = mapper.paraEntidade(request);
        Livro livroSalvo = repository.salvar(livro);
        return  mapper.paraResposta(livroSalvo);
    }

    public List<LivroResponse> listarTodos () throws SQLException {

        List<Livro> livros = repository.listarTodos();

        return livros.stream()
                .map(mapper::paraResposta)
                .toList();
    }

    public LivroResponse buscarPorId (Long id) throws SQLException {
        LivroResponse response = mapper.paraResposta(
                repository.buscarPorId(id)
        );
        return response;
    }

    public void atualizar(Long id, LivroRequest livro) throws SQLException{

        Livro livroNoBanco = repository.buscarPorId(id);
        livroNoBanco.update(livro.titulo(), livro.autor(), livro.anoPublicacao());

        repository.atualizar(id, livroNoBanco);
    }

    public void deletar(Long id) throws SQLException{
        repository.deletar(id);
    }
}
