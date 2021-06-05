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
import br.com.fatec.poo.vagas.control.EmpresaControl;

public class EmpresaBoundary implements TelaStrategy {

    private TextField txtId = new TextField();
    private TextField txtNome = new TextField();
    private TextField txtTelefone = new TextField();
    private TextField txtCnpj = new TextField();
    private TextField txtRazaoSocial = new TextField();
    private TextField txtEndereco = new TextField();

    private Button btnAdicionar = new Button("Adicionar");
    private Button btnPesquisar = new Button("Pesquisar");
    private Button btnRemover = new Button("Remover");
    private Button btnAtualizar = new Button("Atualizar");
    private Button btnLimpar = new Button("Limpar");

    private EmpresaControl control = new EmpresaControl();

    @Override
    public Pane gerarTelaStrategy() {

        GridPane gp = new GridPane();

        gp.add(new Label("Id"), 0, 0);
        gp.add(new Label("Nome"), 0, 1);
        gp.add(new Label("Cnpj"), 0, 2);
        gp.add(new Label("Endereço"), 0, 3);
        gp.add(new Label("Telefone"), 0, 4);
        gp.add(new Label("Razão Social"), 0, 5);


        txtId.setMaxWidth(150);
        txtNome.setMaxWidth(150);
        txtCnpj.setMaxWidth(150);
        txtEndereco.setMaxWidth(150);
        txtTelefone.setMaxWidth(150);
        txtRazaoSocial.setMaxWidth(150);

        gp.add(txtId, 1, 0);
        gp.add(txtNome,1, 1);
        gp.add(txtCnpj,1, 2);
        gp.add(txtEndereco,1, 3);
        gp.add(txtTelefone,1, 4);
        gp.add(txtRazaoSocial,1, 5);

        GridPane gpButtons = new GridPane();
        gpButtons.add(btnAdicionar, 0, 6);
        gpButtons.add(btnPesquisar, 1, 6);
        gpButtons.add(btnAtualizar, 2, 6);
        gpButtons.add(btnRemover, 3, 6);
        gpButtons.add(btnLimpar, 4, 6);
        gpButtons.setHgap(2);
        gp.add(gpButtons, 1, 7);

        Pane pane = new Pane();
        if(control.getTable().getColumns().size() == 0) {
            control.generatedTable();
        }
        Node table = control.getTable();
        pane.getChildren().add(table);

        gp.add(pane, 1, 8);

        btnAdicionar.setOnAction((e)->{control.adicionar();});
        btnPesquisar.setOnAction((e)->{control.pesquisarPorNome();});
        btnAtualizar.setOnAction((e)->{control.atualizarPorId();});
        btnRemover.setOnAction((e)->{control.removerPorId();});
        btnLimpar.setOnAction((e) ->{control.eraseFields();});

        StringConverter longToStringConverter = new LongStringConverter();

        Bindings.bindBidirectional(txtId.textProperty(), control.idProperty(), longToStringConverter);
        Bindings.bindBidirectional(txtNome.textProperty(), control.nomeProperty());
        Bindings.bindBidirectional(txtCnpj.textProperty(), control.cnpjProperty());
        Bindings.bindBidirectional(txtEndereco.textProperty(), control.enderecoProperty());
        Bindings.bindBidirectional(txtTelefone.textProperty(), control.telefoneProperty());
        Bindings.bindBidirectional(txtRazaoSocial.textProperty(), control.razaoSocialProperty());

        return gp;
    }
}