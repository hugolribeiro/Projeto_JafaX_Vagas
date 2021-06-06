package br.com.fatec.poo.vagas.entity;

public class Usuario {

    public Usuario() {
        Role roleUser = new Role();
        roleUser.setId(2);
        roleUser.setNome("user");
        this.setRole(roleUser);
    }

    private long id;
    private int registroFuncionario;
    private String nome;
    private String login;
    private String senha;
    private Role role;

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

    public void setRole(Role role) {
        this.role = role;
    }

    public Role getRole() {
        return this.role;
    }
}
