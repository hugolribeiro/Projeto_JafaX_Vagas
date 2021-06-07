package br.com.fatec.poo.vagas.boundary;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

import javax.swing.*;

public class LoginBoundary implements TelaStrategy{

    public LoginBoundary(ExecutorAcoes executor){
        this.executor = executor;
    }

    private TextField txtLogin = new TextField();
    private PasswordField passSenha = new PasswordField();
    private TextField txtSenha = new TextField();

    private Button btnEntrar = new Button("Entrar");
    private Button btnLimpar = new Button("Limpar");
    private ExecutorAcoes executor;

//    private static LoginControl control = new LoginControl();


    @Override
    public Pane gerarTelaStrategy() {
        GridPane gp = new GridPane();
        gp.setPadding(new Insets(10,10,10,10));
//        Não houve a necessidade
//        txtSenha.setManaged(false);
//        txtSenha.setVisible(false);

        CheckBox passCheckBox = new CheckBox("Mostrar/Esconder senha");

        txtSenha.managedProperty().bind(passCheckBox.selectedProperty());
        txtSenha.visibleProperty().bind(passCheckBox.selectedProperty());

        passSenha.managedProperty().bind(passCheckBox.selectedProperty().not());
        passSenha.visibleProperty().bind(passCheckBox.selectedProperty().not());

        txtSenha.textProperty().bindBidirectional(passSenha.textProperty());


//        vincular();

        gp.add(new Label("Login"), 0, 0);
        gp.add(new Label("Senha"), 0, 1);

        txtLogin.setMaxWidth(150);
        passSenha.setMaxWidth(150);
        GridPane gridButtons = new GridPane();

        gridButtons.add(btnEntrar,0,1);
        gridButtons.add(btnLimpar,1,1);
        gridButtons.setHgap(10);


        gp.add(txtLogin, 1, 0);
        gp.add(passSenha, 1, 1);
        gp.add(txtSenha, 1, 1);
        gp.add(passCheckBox, 2, 2);
        gp.add(gridButtons,1,2);


        btnEntrar.setOnAction((e) -> {
            String login = txtLogin.getText();
            String senha = passSenha.getText();
            String acao = btnEntrar.getText();
            if(login.equals("Admin") && (senha.equals("Admin"))){
                limparLogin();
                JOptionPane.showMessageDialog(null, "Você está logado como Administrador!",
                                              "Login", JOptionPane.INFORMATION_MESSAGE);
                executor.executaAcaoAdmin(login, senha, acao);
            }
            else if(login.equals("Usuario") && (senha.equals("Usuario"))){
                limparLogin();
                JOptionPane.showMessageDialog(null, "Você está logado como Usuario!",
                        "Login", JOptionPane.INFORMATION_MESSAGE);
                executor.executaAcaoUsuario(login,senha, acao);
            }
            else{
                JOptionPane.showMessageDialog(null, "Erro ao entrar",
                                              "Erro no Login", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        btnLimpar.setOnAction((e)->{
            limparLogin();
        });

        return gp;
    }

    public void limparLogin(){
        txtLogin.setText("");
        txtSenha.setText("");
        passSenha.setText("");
    }


//    public void vincular(){
//        StringConverter longToStringConverter = new LongStringConverter();
//
//        Bindings.bindBidirectional(txtLogin.textProperty(), control.idProperty(), longToStringConverter);
//        Bindings.bindBidirectional(txtSenha.textProperty(), control.nomeProperty());
//
//    }

}
