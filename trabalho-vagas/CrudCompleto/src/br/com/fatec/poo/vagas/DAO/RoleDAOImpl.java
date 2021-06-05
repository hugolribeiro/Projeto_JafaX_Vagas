package br.com.fatec.poo.vagas.DAO;

import br.com.fatec.poo.vagas.entity.Role;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RoleDAOImpl implements RoleDAO{

    private static final String URL = "jdbc:mariadb://localhost:3306/vagasdb?allowMultiQueries=true";
    private static final String USER = "root";
    private static final String PASSWORD = "123456";

    @Override
    public void adicionar(Role role) {
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String sql = "INSERT INTO role " +
                    "(nome) VALUES (?)";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, role.getNome());
            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Role> pesquisarPorNome(String nome) {
        List<Role> lista = new ArrayList<>();
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String sql = "SELECT * FROM role WHERE nome LIKE ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, "%" + nome + "%");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Role role = new Role();
                role.setId(rs.getInt("id"));
                role.setNome(rs.getString("nome"));
                lista.add(role);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    @Override
    public void atualizarPorId(long id, Role role) {
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String sql = "UPDATE role SET nome = ? WHERE id = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, role.getNome());
            stmt.setLong(2, id);
            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removerPorId(long id) {
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String sql = "DELETE FROM role WHERE id = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setLong(1, id);
            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}