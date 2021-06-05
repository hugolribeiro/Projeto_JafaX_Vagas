package br.com.fatec.poo.vagas.control;

import br.com.fatec.poo.vagas.DAO.RoleDAO;
import br.com.fatec.poo.vagas.DAO.RoleDAOImpl;
import br.com.fatec.poo.vagas.entity.Role;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class RoleControl {

    private ObservableList<Role> roles = FXCollections.observableArrayList();
    private TableView<Role> table = new TableView<>();

    private LongProperty id = new SimpleLongProperty(1);
    private StringProperty nome = new SimpleStringProperty("");
    private RoleDAO service = new RoleDAOImpl();

    public void setEntity(Role role) {
        if (role != null) {
            id.set(role.getId());
            nome.set(role.getNome());
        }
    }

    public Role getEntity() {
        Role role = new Role();
        role.setId(id.get());
        role.setNome(nome.get());
        return role;
    }

    public void adicionar() {
        service.adicionar(getEntity());
        this.listarTodos();
    }

    public void pesquisarPorNome() {
        roles.clear();
        roles.addAll(service.pesquisarPorNome(getNome()));
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
        Role role = getEntity();
        role.setId(0);
        id.set(0);
        nome.set("");
        this.listarTodos();
    }

    private void listarTodos() {
        roles.clear();
        roles.addAll( service.pesquisarPorNome(""));
    }

    public void generatedTable() {
        TableColumn<Role, Long> colId = new TableColumn<>("Id");
        colId.setCellValueFactory(new PropertyValueFactory<Role, Long>("id"));

        TableColumn<Role, String> colNome = new TableColumn<>("Nome");
        colNome.setCellValueFactory(new PropertyValueFactory<Role, String>("nome"));

        table.getColumns().addAll(colId, colNome);

        table.getSelectionModel().selectedItemProperty().addListener(
                (obs, antigo, novo) -> {
                    setEntity(novo);
                }
        );

        table.setItems(roles);
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
    public TableView<Role> getTable() {
        return table;
    }
}