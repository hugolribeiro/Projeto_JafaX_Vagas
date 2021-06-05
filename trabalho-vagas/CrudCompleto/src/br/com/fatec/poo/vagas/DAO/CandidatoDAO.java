package br.com.fatec.poo.vagas.DAO;

import br.com.fatec.poo.vagas.entity.Candidato;

import java.util.List;

public interface CandidatoDAO {
    void adicionar(Candidato candidato);
    List<Candidato> pesquisarPorNome(String nome);
    void atualizarPorId(long id, Candidato candidato);
    void removerPorId(long id);
}