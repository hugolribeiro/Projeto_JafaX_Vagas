package br.com.fatec.poo.vagas.boundary;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import javafx.util.converter.LongStringConverter;
import br.com.fatec.poo.vagas.control.RoleControl;

public class RoleBoundary extends Application {

    private TextField txtId = new TextField();
    private TextField txtNome = new TextField();

    private Button btnAdicionar = new Button("Adicionar");
    private Button btnPesquisar = new Button("Pesquisar");
    private Button btnRemover = new Button("Remover");
    private Button btnAtualizar = new Button("Atualizar");
    private Button btnLimpar = new Button("Limpar");

    private RoleControl control = new RoleControl();

    @Override
    public void start(Stage stage) throws Exception {

        GridPane gp = new GridPane();
        BorderPane panePrincipal = new BorderPane();
        Scene scn = new Scene(panePrincipal, 600, 400);

        gp.add(new Label("Id"), 0, 0);
        gp.add(new Label("Nome"), 0, 1);

        txtId.setMaxWidth(150);
        txtNome.setMaxWidth(150);

        gp.add(txtId, 1, 0);
        gp.add(txtNome,1, 1);

        GridPane gpButtons = new GridPane();
        gpButtons.add(btnAdicionar, 0, 2);
        gpButtons.add(btnPesquisar, 1, 2);
        gpButtons.add(btnAtualizar, 2, 2);
        gpButtons.add(btnRemover, 3, 2);
        gpButtons.add(btnLimpar, 4, 2);
        gpButtons.setHgap(2);
        gp.add(gpButtons, 1, 3);

        control.generatedTable();
        panePrincipal.setTop(gp);
        panePrincipal.setCenter(control.getTable());

        btnAdicionar.setOnAction((e)->{control.adicionar();});
        btnPesquisar.setOnAction((e)->{control.pesquisarPorNome();});
        btnAtualizar.setOnAction((e)->{control.atualizarPorId();});
        btnRemover.setOnAction((e)->{control.removerPorId();});
        btnLimpar.setOnAction((e) ->{control.eraseFields();});

        StringConverter longToStringConverter = new LongStringConverter();

        Bindings.bindBidirectional(txtId.textProperty(), control.idProperty(), longToStringConverter);
        Bindings.bindBidirectional(txtNome.textProperty(), control.nomeProperty());

        stage.setScene(scn);
        stage.setTitle("CRUD role");
        stage.show();
    }

    public static void main(String[] args) {
        Application.launch(RoleBoundary.class, args);
    }
}