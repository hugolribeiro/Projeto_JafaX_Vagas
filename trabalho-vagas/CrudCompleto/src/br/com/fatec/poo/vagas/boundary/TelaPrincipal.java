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


public class TelaPrincipal extends Application implements ExecutorAcoes {

    private Button btnCandidato = new Button("Candidato");
    private Button btnVagas = new Button("Vagas");
    private Button btnUsuario = new Button("Usuario");
    private Button btnEmpresa = new Button("Empresa");
    private Button btnSair = new Button("Sair");

    private TelaStrategy telaCandidato = new CandidatoBoundary();
    private TelaStrategy telaUsuario = new UsuarioBoundary();
    private TelaStrategy telaVagas = new VagaBoundary();
    private TelaStrategy telaEmpresa = new EmpresaBoundary();
    private TelaStrategy telaLogin = new LoginBoundary(this);


    private static CandidatoControl controlCandidato = new CandidatoControl();
    private static UsuarioControl controlUsuario = new UsuarioControl();
    private static VagaControl controlVagas = new VagaControl();
    private static EmpresaControl controlEmpresa = new EmpresaControl();

    private static BorderPane panePrincipal = new BorderPane();


    @Override
    public void start(Stage stage) throws Exception {

        Scene scn = new Scene(panePrincipal, 700, 470);

        GridPane menuButtons = new GridPane();
        Label topLogo= new Label("Logo");
        topLogo.setPrefWidth(700);
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

        btnSair.setPrefWidth(100);
        btnSair.setPrefHeight(50);

        menuButtons.add(btnUsuario, 0, 0);
        menuButtons.add(btnCandidato, 0, 1);
        menuButtons.add(btnVagas, 0, 2);
        menuButtons.add(btnEmpresa, 0, 3);
        menuButtons.add(btnSair, 0, 4);
        menuButtons.setVgap(20);
        menuButtons.setStyle("-fx-padding: 20;-fx-border-color: #E7E7E7");

        disableAllButtons();

        panePrincipal.setLeft(menuButtons);
        panePrincipal.setTop(topLogo);

        //panePrincipal.setCenter(telaCandidato.gerarTelaStrategy());
        panePrincipal.setCenter(telaLogin.gerarTelaStrategy());

        btnCandidato.setOnAction((e) -> {
            panePrincipal.setCenter(telaCandidato.gerarTelaStrategy());

        });

        btnUsuario.setOnAction((e) -> {
            panePrincipal.setCenter(telaUsuario.gerarTelaStrategy());
        });

        btnEmpresa.setOnAction((e) -> {
            panePrincipal.setCenter(telaEmpresa.gerarTelaStrategy());

        });

        btnVagas.setOnAction((e) -> {
            panePrincipal.setCenter(telaVagas.gerarTelaStrategy());

        });

        btnSair.setOnAction((e) -> {
            panePrincipal.setCenter(telaLogin.gerarTelaStrategy());
            disableAllButtons();
        });

        stage.setTitle("Sistema Vagas de Emprego");
        stage.setScene(scn);
        stage.show();
    }

    public static void main(String[] args) {
        Application.launch(TelaPrincipal.class, args);
    }

    @Override
    public void executaAcaoAdmin(String login, String senha, String acao) {
        if ("Admin".equals(login) && "Admin".equals(senha) && "Entrar".equals(acao)){
            panePrincipal.setCenter(telaCandidato.gerarTelaStrategy());
            enableButtons(login);
        }
    }
    public void executaAcaoUsuario(String login, String senha, String acao) {
        if ("Usuario".equals(login) && "Usuario".equals(senha) && "Entrar".equals(acao)){
            panePrincipal.setCenter(telaCandidato.gerarTelaStrategy());
            enableButtons(login);
        }
    }

    public void disableAllButtons(){
        btnUsuario.setDisable(true);
        btnVagas.setDisable(true);
        btnEmpresa.setDisable(true);
        btnCandidato.setDisable(true);
        btnSair.setDisable(true);
    }

    public void enableButtons(String login){
        btnVagas.setDisable(false);
        btnEmpresa.setDisable(false);
        btnCandidato.setDisable(false);
        btnSair.setDisable(false);
        if(login.equals("Admin")) {
            btnUsuario.setDisable(false);
        }
    }
}