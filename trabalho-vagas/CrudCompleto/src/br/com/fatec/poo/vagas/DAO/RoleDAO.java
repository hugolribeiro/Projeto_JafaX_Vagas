package br.com.fatec.poo.vagas.DAO;

import br.com.fatec.poo.vagas.entity.Role;

import java.util.List;

public interface RoleDAO {
    void adicionar(Role role);
    List<Role> pesquisarPorNome(String nome);
    void atualizarPorId(long id, Role role);
    void removerPorId(long id);
}