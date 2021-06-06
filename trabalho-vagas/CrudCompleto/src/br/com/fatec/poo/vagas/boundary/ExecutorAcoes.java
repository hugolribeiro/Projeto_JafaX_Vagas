package br.com.fatec.poo.vagas.boundary;

public interface ExecutorAcoes {

    void executaAcaoAdmin(String login, String senha, String acao);
    void executaAcaoUsuario(String login, String senha, String acao);
}
