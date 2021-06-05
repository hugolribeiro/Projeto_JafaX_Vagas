package br.com.fatec.poo.vagas.boundary;

import br.com.fatec.poo.vagas.control.CandidatoControl;
import br.com.fatec.poo.vagas.control.EmpresaControl;
import br.com.fatec.poo.vagas.control.UsuarioControl;
import br.com.fatec.poo.vagas.control.VagaControl;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


public class TelaPrincipal extends Application {

    private Button btnCandidato = new Button("Candidato");
    private Button btnVagas = new Button("Vagas");
    private Button btnUsuario = new Button("Usuario");
    private Button btnEmpresa = new Button("Empresa");

    private TelaStrategy telaCandidato = new CandidatoBoundary();
    private TelaStrategy telaUsuario = new UsuarioBoundary();
    private TelaStrategy telaVagas = new VagaBoundary();
    private TelaStrategy telaEmpresa = new EmpresaBoundary();


    private static CandidatoControl controlCandidato = new CandidatoControl();
    private static UsuarioControl controlUsuario = new UsuarioControl();
    private static VagaControl controlVagas = new VagaControl();
    private static EmpresaControl controlEmpresa = new EmpresaControl();

    private static BorderPane panePrincipal = new BorderPane();


    @Override
    public void start(Stage stage) throws Exception {

        CandidatoBoundary cd = new CandidatoBoundary();

        Scene scn = new Scene(panePrincipal, 600, 450);

        GridPane menuButtons = new GridPane();
        Label topLogo= new Label("Logo");
        topLogo.setPrefWidth(600);
        topLogo.setPrefHeight(80);
        topLogo.setStyle("-fx-border-color: #E7E7E7");

        btnUsuario.setPrefWidth(100);
        btnUsuario.setPrefHeight(50);

        btnCandidato.setPrefWidth(100);
        btnCandidato.setPrefHeight(50);

        btnVagas.setPrefWidth(100);
        btnVagas.setPrefHeight(50);

        btnEmpresa.setPrefWidth(100);
        btnEmpresa.setPrefHeight(50);

        menuButtons.add(btnUsuario, 0, 0);
        menuButtons.add(btnCandidato, 0, 1);
        menuButtons.add(btnVagas, 0, 2);
        menuButtons.add(btnEmpresa, 0, 3);
        menuButtons.setVgap(20);
        menuButtons.setStyle("-fx-padding: 20;-fx-border-color: #E7E7E7");


        panePrincipal.setLeft(menuButtons);
        panePrincipal.setTop(topLogo);

        panePrincipal.setCenter(telaCandidato.gerarTelaStrategy());
        panePrincipal.setBottom(controlCandidato.getTable());

        btnCandidato.setOnAction((e) -> {
            controlCandidato.getTable().getColumns().clear();
            controlCandidato.generatedTable();
            panePrincipal.setCenter(telaCandidato.gerarTelaStrategy());
            panePrincipal.setBottom(controlCandidato.getTable());
        });

        btnUsuario.setOnAction((e) -> {
//            Outro método de fazer ao invés do .clear() abaixo

//            if(controlUsuario.getTable().getColumns().size() == 0){
//                controlUsuario.generatedTable();
//                panePrincipal.setBottom(controlUsuario.getTable());
//            }
            controlUsuario.getTable().getColumns().clear();
            controlUsuario.generatedTable();
            panePrincipal.setCenter(telaUsuario.gerarTelaStrategy());
            panePrincipal.setBottom(controlUsuario.getTable());
        });

        btnEmpresa.setOnAction((e) -> {
            controlEmpresa.getTable().getColumns().clear();
            controlEmpresa.generatedTable();
            panePrincipal.setCenter(telaEmpresa.gerarTelaStrategy());
            panePrincipal.setBottom(controlEmpresa.getTable());
        });

        btnVagas.setOnAction((e) -> {
            controlVagas.getTable().getColumns().clear();
            controlVagas.generatedTable();
            panePrincipal.setCenter(telaVagas.gerarTelaStrategy());
            panePrincipal.setBottom(controlVagas.getTable());
        });

        stage.setTitle("Título Principal");
        stage.setScene(scn);
        stage.show();
    }

    public static void main(String[] args) {
        Application.launch(TelaPrincipal.class, args);
    }
}