package br.com.fatec.poo.vagas.DAO;

import br.com.fatec.poo.vagas.entity.Empresa;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmpresaDAOImpl implements EmpresaDAO{

    private static final String URL = "jdbc:mariadb://localhost:3306/vagasdb?allowMultiQueries=true";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    @Override
    public void adicionar(Empresa empresa) {

        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String sql = "INSERT INTO empresa " +
                    "(nome, telefone, cnpj, " +
                    "razaoSocial, endereco) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, empresa.getNome());
            stmt.setString(2, empresa.getTelefone());
            stmt.setString(3, empresa.getCnpj());
            stmt.setString(4, empresa.getRazaoSocial());
            stmt.setString(5, empresa.getEndereco());
            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Empresa> pesquisarPorNome(String nome) {
        List<Empresa> lista = new ArrayList<>();
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String sql = "SELECT * FROM empresa WHERE nome LIKE ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, "%" + nome + "%");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Empresa empresa = new Empresa();
                empresa.setId(rs.getInt("id"));
                empresa.setNome(rs.getString("nome"));
                empresa.setTelefone(rs.getString("telefone"));
                empresa.setCnpj(rs.getString("cnpj"));
                empresa.setRazaoSocial(rs.getString("razaoSocial"));
                empresa.setEndereco(rs.getString("endereco"));
                lista.add(empresa);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    @Override
    public void atualizarPorId(long id, Empresa empresa) {
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String sql = "UPDATE empresa SET nome = ?, telefone = ?," +
                    "cnpj = ?, razaoSocial = ?, endereco = ? WHERE id = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, empresa.getNome());
            stmt.setString(2, empresa.getTelefone());
            stmt.setString(3, empresa.getCnpj());
            stmt.setString(4, empresa.getRazaoSocial());
            stmt.setString(5, empresa.getEndereco());
            stmt.setLong(6, id);
            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void removerPorId(long id) {
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String sql = "DELETE FROM empresa WHERE id = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setLong(1, id);
            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}