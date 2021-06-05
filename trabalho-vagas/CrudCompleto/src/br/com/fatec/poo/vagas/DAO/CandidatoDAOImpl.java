package br.com.fatec.poo.vagas.DAO;

import br.com.fatec.poo.vagas.entity.Candidato;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CandidatoDAOImpl implements CandidatoDAO {

    private static final String URL = "jdbc:mariadb://localhost:3306/vagasdb?allowMultiQueries=true";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    @Override
    public void adicionar(Candidato candidato) {
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String sql = "INSERT INTO candidato " +
                    "(nome, telefone, email, " +
                    "cpf, curriculo) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, candidato.getNome());
            stmt.setString(2, candidato.getTelefone());
            stmt.setString(3, candidato.getEmail());
            stmt.setString(4, candidato.getCpf());
            stmt.setString(5, candidato.getCurriculo());
            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Candidato> pesquisarPorNome(String nome) {
        List<Candidato> lista = new ArrayList<>();
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String sql = "SELECT * FROM candidato WHERE nome LIKE ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, "%" + nome + "%");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Candidato candidato = new Candidato();
                candidato.setId(rs.getInt("id"));
                candidato.setNome(rs.getString("nome"));
                candidato.setTelefone(rs.getString("telefone"));
                candidato.setEmail(rs.getString("email"));
                candidato.setCpf(rs.getString("cpf"));
                candidato.setCurriculo(rs.getString("curriculo"));
                lista.add(candidato);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    @Override
    public void atualizarPorId(long id, Candidato candidato) {
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String sql = "UPDATE candidato SET nome = ?, telefone = ?," +
                    "email = ?, cpf = ?, curriculo = ? WHERE id = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, candidato.getNome());
            stmt.setString(2, candidato.getTelefone());
            stmt.setString(3, candidato.getEmail());
            stmt.setString(4, candidato.getCpf());
            stmt.setString(5, candidato.getCurriculo());
            stmt.setLong(6, id);
            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removerPorId(long id) {
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String sql = "DELETE FROM candidato WHERE id = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setLong(1, id);
            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}