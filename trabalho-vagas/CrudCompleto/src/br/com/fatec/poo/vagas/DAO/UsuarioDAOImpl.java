package br.com.fatec.poo.vagas.DAO;

import br.com.fatec.poo.vagas.entity.Usuario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAOImpl implements UsuarioDAO{

    private static final String URL = "jdbc:mariadb://localhost:3306/vagasdb?allowMultiQueries=true";
    private static final String USER = "root";
    private static final String PASSWORD = "123456";

    @Override
    public void adicionar(Usuario usuario) {
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String sql = "INSERT INTO usuario " +
                    "(nome, login, senha, " +
                    "registroFuncionario) VALUES (?, ?, ?, ?)";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getLogin());
            stmt.setString(3, usuario.getSenha());
            stmt.setInt(4, usuario.getRegistroFuncionario());
            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Usuario> pesquisarPorNome(String nome) {
        List<Usuario> lista = new ArrayList<>();
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String sql = "SELECT * FROM usuario WHERE nome LIKE ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, "%" + nome + "%");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setId(rs.getInt("id"));
                usuario.setNome(rs.getString("nome"));
                usuario.setLogin(rs.getString("login"));
                usuario.setSenha(rs.getString("senha"));
                usuario.setRegistroFuncionario(rs.getInt("registroFuncionario"));
                lista.add(usuario);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    @Override
    public void atualizarPorId(long id, Usuario usuario) {
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String sql = "UPDATE usuario SET nome = ?, login = ?," +
                    "senha = ?, registroFuncionario = ? WHERE id = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getLogin());
            stmt.setString(3, usuario.getSenha());
            stmt.setInt(4, usuario.getRegistroFuncionario());
            stmt.setLong(5, id);
            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removerPorId(long id) {
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String sql = "DELETE FROM usuario WHERE id = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setLong(1, id);
            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}