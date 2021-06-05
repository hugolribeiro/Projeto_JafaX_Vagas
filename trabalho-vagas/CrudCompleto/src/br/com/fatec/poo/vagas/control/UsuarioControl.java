package br.com.fatec.poo.vagas.control;

import br.com.fatec.poo.vagas.DAO.UsuarioDAO;
import br.com.fatec.poo.vagas.DAO.UsuarioDAOImpl;
import br.com.fatec.poo.vagas.entity.Usuario;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class UsuarioControl {

    private ObservableList<Usuario> usuarios = FXCollections.observableArrayList();
    private TableView<Usuario> table = new TableView<Usuario>();

    private LongProperty id = new SimpleLongProperty(1);
    private StringProperty nome = new SimpleStringProperty("");
    private StringProperty login = new SimpleStringProperty("");
    private StringProperty senha = new SimpleStringProperty("");
    private IntegerProperty registroFuncionario = new SimpleIntegerProperty(0);
    private UsuarioDAO service = new UsuarioDAOImpl();

    public void setEntity(Usuario usuario) {
        if (usuario != null) {
            id.set(usuario.getId());
            nome.set(usuario.getNome());
            login.set(usuario.getLogin());
            senha.set(usuario.getSenha());
            registroFuncionario.set(usuario.getRegistroFuncionario());
        }
    }

    public Usuario getEntity() {
        Usuario usuario = new Usuario();
        usuario.setId(id.get());
        usuario.setNome(nome.get());
        usuario.setLogin(login.get());
        usuario.setSenha(senha.get());
        usuario.setRegistroFuncionario(registroFuncionario.get());
        return usuario;
    }

    public void adicionar() {
        service.adicionar(getEntity());
        this.listarTodos();
    }

    public void pesquisarPorNome() {
        usuarios.clear();
        usuarios.addAll(service.pesquisarPorNome(getNome()));
    }

    public void atualizarPorId() {
        service.atualizarPorId(getId(),getEntity());
        this.pesquisarPorNome();
    }

    public void removerPorId() {
        service.removerPorId(getId());
        this.listarTodos();
    }

    public void eraseFields(){
        Usuario usuario = getEntity();
        usuario.setId(0);
        id.set(0);
        nome.set("");
        login.set("");
        senha.set("");
        registroFuncionario.set(0);
        this.listarTodos();
    }

    private void listarTodos() {
        usuarios.clear();
        usuarios.addAll( service.pesquisarPorNome(""));
    }

    public void generatedTable() {
        TableColumn<Usuario, Long> colId = new TableColumn<>("Id");
        colId.setCellValueFactory(new PropertyValueFactory<Usuario, Long>("id"));

        TableColumn<Usuario, String> colNome = new TableColumn<>("Nome");
        colNome.setCellValueFactory(new PropertyValueFactory<Usuario, String>("nome"));

        TableColumn<Usuario, String> colLogin = new TableColumn<>("Login");
        colLogin.setCellValueFactory(new PropertyValueFactory<Usuario, String>("login"));

        TableColumn<Usuario, String > colSenha = new TableColumn<>("Senha");
        colSenha.setCellValueFactory(new PropertyValueFactory<Usuario, String>("Senha"));

        TableColumn<Usuario, String> colRegistroFuncionario = new TableColumn<>("Registro do Funcionario");
        colRegistroFuncionario.setCellValueFactory(new PropertyValueFactory<Usuario, String>("registroFuncionario"));

        table.getColumns().addAll(colId, colNome, colRegistroFuncionario, colLogin, colSenha);

        table.getSelectionModel().selectedItemProperty().addListener(
                (obs, antigo, novo) -> {
                    setEntity(novo);
                }
        );

        table.setItems(usuarios);
    }

    public long getId() {
        return id.get();
    }
    public LongProperty idProperty() {
        return id;
    }
    public String getNome() {
        return nome.get();
    }
    public StringProperty nomeProperty() {
        return nome;
    }
    public String getLogin() {
        return login.get();
    }
    public StringProperty loginProperty() {
        return login;
    }
    public String getSenha() {
        return senha.get();
    }
    public StringProperty senhaProperty() {
        return senha;
    }
    public Integer getRegistroFuncionario() {
        return registroFuncionario.get();
    }
    public IntegerProperty registroFuncionarioProperty() {
        return registroFuncionario;
    }
    public TableView<Usuario> getTable() {
        return table;
    }
}