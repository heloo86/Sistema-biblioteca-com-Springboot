package com.senai.Biblioteca.dao;

import com.senai.Biblioteca.dto.usuario.UsuarioResponse;
import com.senai.Biblioteca.model.Usuario;
import com.senai.Biblioteca.utils.Conexao;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UsuarioRepository {

    public Usuario salvar(Usuario usuario) throws SQLException {

        String sql = "INSERT INTO usuario (nome, email) VALUES (?, ?)";

        try(Connection connection = Conexao.conectar();
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){

            statement.setString(1, usuario.getNome());
            statement.setString(2, usuario.getEmail());
            statement.executeUpdate();

            ResultSet resultSet = statement.getGeneratedKeys();

            if (resultSet.next()){
                usuario.setId(resultSet.getLong(1));
                return usuario;
            }

        }

        return null;
    };

    public List<Usuario> listarTodos() throws SQLException {

        String sql = "SELECT id, nome, email FROM usuario";
        List<Usuario> usuarios = new ArrayList<>();

        try(Connection connection = Conexao.conectar();
        PreparedStatement statement = connection.prepareStatement(sql)){

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()){
                Usuario usuario = new Usuario(
                        resultSet.getLong(1),
                        resultSet.getString(2),
                        resultSet.getString(3)
                );
                usuarios.add(usuario);
            }
        }

        return usuarios;
    }

    public Usuario buscarPorId (Long id) throws SQLException {

        String sql = "SELECT id, nome, email FROM  usuario WHERE id = ?";
        Usuario usuario;

        try(Connection connection = Conexao.conectar();
        PreparedStatement statement = connection.prepareStatement(sql)){

            statement.setLong(1, id);

            ResultSet resultSet = statement.executeQuery();

            if(resultSet.next()){
                usuario = new Usuario(
                        resultSet.getLong(1),
                        resultSet.getString(2),
                        resultSet.getString(3)
                );
                return usuario;
            }
        }
        return null;
    }

    public void atualizar (long id, Usuario usuario) throws SQLException {

        String sql ="UPDATE usuario SET nome = ?, email = ? WHERE id = ?";

        try(Connection connection = Conexao.conectar();
        PreparedStatement statement = connection.prepareStatement(sql)){

            statement.setString(1, usuario.getNome());
            statement.setString(2, usuario.getEmail());
            statement.setLong(3, id);

            statement.executeUpdate();
        }
    }

    public void deletar (long id) throws SQLException {
        String sql = "DELETE FROM usuario WHERE id = ?";

        try(Connection connection = Conexao.conectar();
        PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setLong(1, id);
            statement.executeUpdate();
        }
    }
}
