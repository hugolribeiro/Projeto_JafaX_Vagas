package br.com.fatec.poo.vagas.entity;

public class Vaga {

    private long id;
    private String cargo;
    private double salario;
    private long idEmpresa;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public long getIdEmpresa(){
        return idEmpresa;
    }

    public void setIdEmpresa(long idEmpresa){
        this.idEmpresa = idEmpresa;
    }

}
