package br.com.fatec.poo.vagas.DAO;

import br.com.fatec.poo.vagas.entity.Usuario;

import java.util.List;

public interface UsuarioDAO {
    void adicionar(Usuario usuario);
    List<Usuario> pesquisarPorNome(String nome);
    void atualizarPorId(long id, Usuario usuario);
    void removerPorId(long id);
}