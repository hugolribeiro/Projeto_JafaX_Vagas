package br.com.fatec.poo.vagas.boundary;

import javafx.beans.binding.Bindings;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.util.StringConverter;
import javafx.util.converter.LongStringConverter;
import br.com.fatec.poo.vagas.control.CandidatoControl;

import java.util.Collection;

public class CandidatoBoundary implements TelaStrategy {

    protected TextField txtId = new TextField();
    private TextField txtNome = new TextField();
    private TextField txtTelefone = new TextField();
    private TextField txtCpf = new TextField();
    private TextField txtCurriculo = new TextField();
    private TextField txtEmail = new TextField();

    private Button btnAdicionar = new Button("Adicionar");
    private Button btnPesquisar = new Button("Pesquisar");
    private Button btnRemover = new Button("Remover");
    private Button btnAtualizar = new Button("Atualizar");
    private Button btnLimpar = new Button("Limpar");

    private Button btnCandidato = new Button("Candidato");
    private Button btnVagas = new Button("Vagas");
    private Button btnUsuario = new Button("Usuario");
    private Button btnEmpresa = new Button("Empresa");

    private static CandidatoControl control = new CandidatoControl();


    @Override
    public Pane gerarTelaStrategy() {
        GridPane gp = new GridPane();

        vincular();

        gp.add(new Label("Id"), 0, 0);
        gp.add(new Label("Nome"), 0, 1);
        gp.add(new Label("Cpf"), 0, 2);
        gp.add(new Label("Email"), 0, 3);
        gp.add(new Label("Telefone"), 0, 4);
        gp.add(new Label("Curriculo"), 0, 5);

        txtId.setMaxWidth(150);
        txtNome.setMaxWidth(150);
        txtCpf.setMaxWidth(150);
        txtEmail.setMaxWidth(150);
        txtTelefone.setMaxWidth(150);
        txtCurriculo.setMaxWidth(150);
        txtId.setDisable(false);

        gp.add(txtId, 1, 0);
        gp.add(txtNome, 1, 1);
        gp.add(txtCpf, 1, 2);
        gp.add(txtEmail, 1, 3);
        gp.add(txtTelefone, 1, 4);
        gp.add(txtCurriculo, 1, 5);

        GridPane gpButtons = new GridPane();
        gpButtons.add(btnAdicionar, 0, 6);
        gpButtons.add(btnPesquisar, 1, 6);
        gpButtons.add(btnAtualizar, 2, 6);
        gpButtons.add(btnRemover, 3, 6);
        gpButtons.add(btnLimpar, 4, 6);
        gpButtons.setHgap(2);
        gp.add(gpButtons, 1, 7);

        btnAdicionar.setOnAction((e) -> {
            control.adicionar();
        });
        btnPesquisar.setOnAction((e) -> {
            control.pesquisarPorNome();
        });
        btnAtualizar.setOnAction((e) -> {
            control.atualizarPorId();
        });
        btnRemover.setOnAction((e) -> {
            control.removerPorId();
        });
        btnLimpar.setOnAction((e) -> {
            control.eraseFields();
        });

        return gp;
    }

    public void vincular(){
        StringConverter longToStringConverter = new LongStringConverter();

        Bindings.bindBidirectional(txtId.textProperty(), control.idProperty(), longToStringConverter);
        Bindings.bindBidirectional(txtNome.textProperty(), control.nomeProperty());
        Bindings.bindBidirectional(txtCpf.textProperty(), control.cpfProperty());
        Bindings.bindBidirectional(txtEmail.textProperty(), control.emailProperty());
        Bindings.bindBidirectional(txtTelefone.textProperty(), control.telefoneProperty());
        Bindings.bindBidirectional(txtCurriculo.textProperty(), control.curriculoProperty());
    }

}