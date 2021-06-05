package br.com.fatec.poo.vagas.DAO;

import br.com.fatec.poo.vagas.entity.Vaga;

import java.util.List;

public interface VagaDAO {
    void adicionar(Vaga vaga);
    List<Vaga> pesquisarPorCargo(String cargo);
    void atualizarPorId(long id, Vaga vaga);
    void removerPorId(long id);
}