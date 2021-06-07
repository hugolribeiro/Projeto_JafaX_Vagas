package br.com.fatec.poo.vagas.DAO;

import br.com.fatec.poo.vagas.entity.Vaga;
import br.com.fatec.poo.vagas.entity.Usuario;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VagaDAOImpl implements VagaDAO{

    private static final String URL = "jdbc:mariadb://localhost:3306/vagasdb?allowMultiQueries=true";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    @Override
    public void adicionar(Vaga vaga) {
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String sql = "INSERT INTO vaga " +
                    "(cargo, salario, idEmpresa) VALUES (?, ?, ?)";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, vaga.getCargo());
            stmt.setDouble(2, vaga.getSalario());
            stmt.setString(3, vaga.getIdEmpresa());
            stmt.execute();
        } catch (SQLIntegrityConstraintViolationException cv) {
            JOptionPane.showMessageDialog(null, "IdEmpresa não existe! Crie primeiro em EMPRESA",
                                          "ERRO - ID", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Vaga> pesquisarPorCargo(String cargo) {
        List<Vaga> lista = new ArrayList<>();
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String sql = "SELECT vaga.id, vaga.cargo, vaga.salario, empresa.nome FROM vaga INNER JOIN " +
                    "empresa ON vaga.idEmpresa=empresa.id WHERE cargo LIKE ? ORDER BY idEmpresa;";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, "%" + cargo + "%");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Vaga vaga = new Vaga();
                vaga.setId(rs.getInt("id"));
                vaga.setCargo(rs.getString("cargo"));
                vaga.setSalario(rs.getDouble("salario"));
                vaga.setIdEmpresa(rs.getString("nome"));
                lista.add(vaga);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    @Override
    public void atualizarPorId(long id, Vaga vaga) {
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String sql = "UPDATE vaga SET cargo = ?, salario = ? WHERE id = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, vaga.getCargo());
            stmt.setDouble(2, vaga.getSalario());
            stmt.setLong(3, id);
            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removerPorId(long id) {
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String sql = "DELETE FROM vaga WHERE id = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setLong(1, id);
            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}