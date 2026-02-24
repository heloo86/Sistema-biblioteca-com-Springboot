package com.senai.Biblioteca.service;

import com.senai.Biblioteca.dao.EmprestimoRepository;
import com.senai.Biblioteca.model.Emprestimo;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class EmprestimoService {

    private EmprestimoRepository repository;

    public EmprestimoService(EmprestimoRepository repository) {
        this.repository = repository;
    }

    public Emprestimo salvar (Emprestimo emprestimo) throws SQLException {
        return repository.salvar(emprestimo);
    }

    public List<Emprestimo> listar () throws SQLException{
        return repository.listarTodos();
    }
}
