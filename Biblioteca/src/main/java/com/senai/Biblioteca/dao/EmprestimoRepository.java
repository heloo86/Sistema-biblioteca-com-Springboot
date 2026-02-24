package com.senai.Biblioteca.dao;

import com.senai.Biblioteca.model.Emprestimo;
import com.senai.Biblioteca.utils.Conexao;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Repository
public class EmprestimoRepository {

    public Emprestimo salvar(Emprestimo emprestimo) throws SQLException {

        String sql = "INSERT INTO emprestimo (livro_id, usuario_id, data_emprestimo, data_devolucao) VALUES (?, ?, ?, ?)";

        try (Connection connection = Conexao.conectar();
             PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){

            statement.setLong(1, emprestimo.getLivroId());
            statement.setLong(2, emprestimo.getUsuarioId());
            statement.setObject(3,Date.valueOf(emprestimo.getDataEmprestimo()) );
            statement.setObject(4,Date.valueOf(emprestimo.getDataDevolucao()));

            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();

            if (resultSet.next()){
                return new Emprestimo(
                        resultSet.getLong(1),
                        resultSet.getLong(2),
                        resultSet.getLong(3),
                        (LocalDate) resultSet.getObject(4),
                        (LocalDate) resultSet.getObject(5)
                );
            }
        }
        return null;
    };

    public List<Emprestimo> listarTodos() throws SQLException {

        String sql = "SELECT id, livro_id, usuario_id, data_emprestimo, data_devolucao FROM emprestimo";
        List<Emprestimo> emprestimos = new ArrayList<>();

        try (Connection connection = Conexao.conectar();
        PreparedStatement statement = connection.prepareStatement(sql)){

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()){
                Emprestimo emprestimo = new Emprestimo(
                        resultSet.getLong(1),
                        resultSet.getLong(2),
                        resultSet.getLong(3),
                        (LocalDate) resultSet.getObject(4),
                        (LocalDate) resultSet.getObject(5)
                );

                emprestimos.add(emprestimo);
            }
        }
        return emprestimos;
    };

    public Emprestimo buscarPorId (int id){
        return null;
    }

    public void atualizar (long id, Emprestimo emprestimo){}

    public void deletar (long id){}
}
