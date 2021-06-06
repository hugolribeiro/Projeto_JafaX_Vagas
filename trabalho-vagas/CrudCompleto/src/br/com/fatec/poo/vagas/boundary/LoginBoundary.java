package br.com.fatec.poo.vagas.boundary;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class LoginBoundary implements TelaStrategy{

    public LoginBoundary(ExecutorAcoes executor){
        this.executor = executor;
    }

    private TextField txtLogin = new TextField();
    private PasswordField txtSenha = new PasswordField();
//    private TextField txtSenha = new TextField();

    private Button btnEntrar = new Button("Entrar");
    private Button btnLimpar = new Button("Limpar");
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
        GridPane gridButons = new GridPane();

        gridButons.add(btnEntrar,0,1);
        gridButons.add(btnLimpar,1,1);
        gridButons.setHgap(10);


        gp.add(txtLogin, 1, 0);
        gp.add(txtSenha, 1, 1);
        gp.add(gridButons,1,2);


        btnEntrar.setOnAction((e) -> {
            String login = txtLogin.getText();
            String senha = txtSenha.getText();
            String acao = btnEntrar.getText();
            if(login.equals("Admin") && (senha.equals("Admin"))){
                limparLogin();
                System.out.println("Você está logado como admin");
                executor.executaAcaoAdmin(login, senha, acao);
            }
            else if(login.equals("Usuario") && (senha.equals("Usuario"))){
                limparLogin();
                System.out.println("Você está logado como usuario");
                executor.executaAcaoUsuario(login,senha, acao);
            }
            else{
                System.out.println("Erro ao entrar");
            }
        });

        btnLimpar.setOnAction((e)->{
            limparLogin();
        });

//        String role = txtLogin.getText();
//        if role == "Admin" = telaprincipalcompleta
//                if role == "usuario" = telaprincipal sem empresa e sem qualquercoisa

        return gp;
    }

    public void limparLogin(){
        txtLogin.setText("");
        txtSenha.setText("");
    }


//    public void vincular(){
//        StringConverter longToStringConverter = new LongStringConverter();
//
//        Bindings.bindBidirectional(txtLogin.textProperty(), control.idProperty(), longToStringConverter);
//        Bindings.bindBidirectional(txtSenha.textProperty(), control.nomeProperty());
//
//    }

}
