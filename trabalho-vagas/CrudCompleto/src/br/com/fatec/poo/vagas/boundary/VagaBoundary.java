package br.com.fatec.poo.vagas.boundary;

import javafx.beans.binding.Bindings;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.util.StringConverter;
import javafx.scene.layout.Pane;
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.LongStringConverter;
import br.com.fatec.poo.vagas.control.VagaControl;

public class VagaBoundary implements TelaStrategy {

    private TextField txtId = new TextField();
    private TextField txtCargo = new TextField();
    private TextField txtSalario = new TextField();
    private TextField txtIdEmpresa = new TextField();

    private Button btnAdicionar = new Button("Adicionar");
    private Button btnPesquisar = new Button("Pesquisar");
    private Button btnRemover = new Button("Remover");
    private Button btnAtualizar = new Button("Atualizar");
    private Button btnLimpar = new Button("Limpar");

    private VagaControl control = new VagaControl();

    @Override
    public Pane gerarTelaStrategy(){
        GridPane gp = new GridPane();

        gp.add(new Label("Id"), 0, 0);
        gp.add(new Label("Cargo"), 0, 1);
        gp.add(new Label("Salário"), 0, 2);
        gp.add(new Label("IdEmpresa"), 0, 3);

        txtId.setMaxWidth(150);
        txtCargo.setMaxWidth(150);
        txtSalario.setMaxWidth(150);
        txtIdEmpresa.setMaxWidth(150);

        gp.add(txtId, 1, 0);
        gp.add(txtCargo,1, 1);
        gp.add(txtSalario,1, 2);
        gp.add(txtIdEmpresa, 1, 3);

        GridPane gpButtons = new GridPane();
        gpButtons.add(btnAdicionar, 0, 3);
        gpButtons.add(btnPesquisar, 1, 3);
        gpButtons.add(btnAtualizar, 2, 3);
        gpButtons.add(btnRemover, 3, 3);
        gpButtons.add(btnLimpar, 4, 3);
        gpButtons.setHgap(2);
        gp.add(gpButtons, 1, 4);

        Pane pane = new Pane();
        pane.setMaxWidth(800);
        if(control.getTable().getColumns().size() == 0) {
            control.generatedTable();
        }
        Node table = control.getTable();
        pane.getChildren().add(table);

        gp.add(pane, 1, 5);

        btnAdicionar.setOnAction((e)->{control.adicionar();});
        btnPesquisar.setOnAction((e)->{control.pesquisarPorCargo();});
        btnAtualizar.setOnAction((e)->{control.atualizarPorId();});
        btnRemover.setOnAction((e)->{control.removerPorId();});
        btnLimpar.setOnAction((e) ->{control.eraseFields();});

        StringConverter longToStringConverter = new LongStringConverter();
        StringConverter doubleToStringConverter = new DoubleStringConverter();

        Bindings.bindBidirectional(txtId.textProperty(), control.idProperty(), longToStringConverter);
        Bindings.bindBidirectional(txtCargo.textProperty(), control.cargoProperty());
        Bindings.bindBidirectional(txtSalario.textProperty(), control.salarioProperty(), doubleToStringConverter);
        Bindings.bindBidirectional(txtIdEmpresa.textProperty(), control.idEmpresaProperty());

        return gp;
    }
}