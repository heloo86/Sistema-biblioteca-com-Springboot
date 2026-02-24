package com.senai.Biblioteca.dao;

import com.senai.Biblioteca.dto.Livro.LivroResponse;
import com.senai.Biblioteca.model.Emprestimo;
import com.senai.Biblioteca.model.Livro;
import com.senai.Biblioteca.utils.Conexao;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class LivroRepository {

    public Livro salvar(Livro livro) throws SQLException {
        String sql = "INSERT INTO livro (titulo, autor, ano_publicacao) VALUES (?, ?, ?)";

        try (Connection connection = Conexao.conectar();
             PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, livro.getTitulo());
            statement.setString(2, livro.getAutor());
            statement.setInt(3, livro.getAnoPublicacao());
            statement.executeUpdate();

            ResultSet resultSet = statement.getGeneratedKeys();

            if (resultSet.next()) {
                livro.setId(resultSet.getLong(1));
                return livro;
            }
        }

        return null;
    };

    public List<LivroResponse> listarTodos() throws SQLException{
        String sql = "SELECT id, titulo, autor, ano_publicacao FROM livro";
        List<LivroResponse> livros = new ArrayList<>();

        try (Connection connection = Conexao.conectar();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                LivroResponse rsponse = new LivroResponse(
                        resultSet.getLong(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getInt(4)
                );
                livros.add(rsponse);
            }
        }

        return livros;
    };

    public LivroResponse buscarPorId (Long id) throws SQLException{
        String sql = "SELECT id, titulo, autor, ano_publicacao FROM livro WHERE id = ?";
        LivroResponse response;

        try (Connection connection = Conexao.conectar();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setLong(1, id);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                response = new LivroResponse(
                        resultSet.getLong(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getInt(4)
                );

                return response;
            }
        }

        return null;
    }

    public void atualizar (long id, Livro livro) throws SQLException{
        String sql = "UPDATE livro SET titulo = ?, autor = ?, ano_publicacao = ? WHERE id = ?";

        try (Connection connection = Conexao.conectar();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, livro.getTitulo());
            statement.setString(2, livro.getAutor());
            statement.setInt(3, livro.getAnoPublicacao());
            statement.setLong(4, id);

            statement.executeUpdate();
        }
    }

    public void deletar (long id) throws SQLException{
        String sql = "DELETE FROM livro WHERE id = ?";

        try (Connection connection = Conexao.conectar();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setLong(1, id);
            statement.executeUpdate();
        }
    }

}
