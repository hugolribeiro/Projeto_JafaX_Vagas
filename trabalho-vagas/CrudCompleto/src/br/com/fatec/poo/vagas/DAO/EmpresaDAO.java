package br.com.fatec.poo.vagas.DAO;

import br.com.fatec.poo.vagas.entity.Empresa;

import java.util.List;

public interface EmpresaDAO {
    void adicionar(Empresa empresa);
    List<Empresa> pesquisarPorNome(String nome);
    void atualizarPorId(long id, Empresa empresa);
    void removerPorId(long id);
}