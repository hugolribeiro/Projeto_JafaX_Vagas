package br.com.fatec.poo.vagas.boundary;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class LoginBoundary implements TelaStrategy{

    public LoginBoundary(ExecutorAcoes executor){
        this.executor = executor;
    }

    private TextField txtLogin = new TextField();
    private TextField txtSenha = new TextField();

    private Button btnEntrar = new Button("Entrar");
    private ExecutorAcoes executor;

//    private static LoginControl control = new LoginControl();


    @Override
    public Pane gerarTelaStrategy() {
        GridPane gp = new GridPane();

//        vincular();

        gp.add(new Label("Login"), 0, 0);
        gp.add(new Label("Senha"), 0, 1);

        txtLogin.setMaxWidth(150);
        txtSenha.setMaxWidth(150);

        gp.add(txtLogin, 1, 0);
        gp.add(txtSenha, 1, 1);
        gp.add(btnEntrar,1,2);


        btnEntrar.setOnAction((e) -> {
            System.out.println("Parabéns, você está logado");
            String acao = btnEntrar.getText();
            executor.executaAcao(acao);
        });

        return gp;
    }


//    public void vincular(){
//        StringConverter longToStringConverter = new LongStringConverter();
//
//        Bindings.bindBidirectional(txtLogin.textProperty(), control.idProperty(), longToStringConverter);
//        Bindings.bindBidirectional(txtSenha.textProperty(), control.nomeProperty());
//
//    }

}
