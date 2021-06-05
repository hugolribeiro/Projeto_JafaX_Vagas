package br.com.fatec.poo.vagas.entity;

public class Usuario {

    private long id;
    private int registroFuncionario;
    private String nome;
    private String login;
    private String senha;
    //private boolean logado; ATENÇÃO --> ACHO QUE É UMA VARIÁVEL DO SISTEMA E Ñ DE USER
    //private Role role; // ATEÇÃO --> IMPLEMENTAR DEPOIS


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getRegistroFuncionario() {
        return registroFuncionario;
    }

    public void setRegistroFuncionario(int registroFuncionario) {
        this.registroFuncionario = registroFuncionario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
