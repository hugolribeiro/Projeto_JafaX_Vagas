package br.com.fatec.poo.vagas.boundary;

import javafx.beans.binding.Bindings;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.util.StringConverter;
import javafx.util.converter.IntegerStringConverter;
import javafx.util.converter.LongStringConverter;
import br.com.fatec.poo.vagas.control.UsuarioControl;

public class UsuarioBoundary implements TelaStrategy {

    private TextField txtId = new TextField();
    private TextField txtNome = new TextField();
    private TextField txtRegistroFuncionario = new TextField();
    private TextField txtLogin = new TextField();
    private TextField txtSenha = new TextField();

    private Button btnAdicionar = new Button("Adicionar");
    private Button btnPesquisar = new Button("Pesquisar");
    private Button btnRemover = new Button("Remover");
    private Button btnAtualizar = new Button("Atualizar");
    private Button btnLimpar = new Button("Limpar");

    private static UsuarioControl control = new UsuarioControl();

    @Override
    public Pane gerarTelaStrategy() {

        GridPane gp = new GridPane();

        StringConverter longToStringConverter = new LongStringConverter();
        StringConverter StringConverter = new IntegerStringConverter();

        Bindings.bindBidirectional(txtId.textProperty(), control.idProperty(), longToStringConverter);
        Bindings.bindBidirectional(txtNome.textProperty(), control.nomeProperty());
        Bindings.bindBidirectional(txtRegistroFuncionario.textProperty(), control.registroFuncionarioProperty(), StringConverter);
        Bindings.bindBidirectional(txtLogin.textProperty(), control.loginProperty());
        Bindings.bindBidirectional(txtSenha.textProperty(), control.senhaProperty());

        gp.add(new Label("Id"), 0, 0);
        gp.add(new Label("Nome"), 0, 1);
        gp.add(new Label("Registro do Funcionario"), 0, 2);
        gp.add(new Label("Login"), 0, 3);
        gp.add(new Label("Senha"), 0, 4);

        txtId.setMaxWidth(150);
        txtNome.setMaxWidth(150);
        txtRegistroFuncionario.setMaxWidth(150);
        txtLogin.setMaxWidth(150);
        txtSenha.setMaxWidth(150);

        gp.add(txtId, 1, 0);
        gp.add(txtNome, 1, 1);
        gp.add(txtRegistroFuncionario, 1, 2);
        gp.add(txtLogin, 1, 3);
        gp.add(txtSenha, 1, 4);

        GridPane gpButtons = new GridPane();
        gpButtons.add(btnAdicionar, 0, 5);
        gpButtons.add(btnPesquisar, 1, 5);
        gpButtons.add(btnAtualizar, 2, 5);
        gpButtons.add(btnRemover, 3, 5);
        gpButtons.add(btnLimpar, 4, 5);
        gpButtons.setHgap(2);
        gp.add(gpButtons, 1, 6);

        Pane pane = new Pane();
        if(control.getTable().getColumns().size() == 0) {
            control.generatedTable();
        }
        Node table = control.getTable();
        pane.getChildren().add(table);

        gp.add(pane, 1, 8);

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
}
