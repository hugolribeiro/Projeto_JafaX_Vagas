package br.com.fatec.poo.vagas.control;

import br.com.fatec.poo.vagas.DAO.EmpresaDAO;
import br.com.fatec.poo.vagas.DAO.EmpresaDAOImpl;
import br.com.fatec.poo.vagas.entity.Empresa;
import br.com.fatec.poo.vagas.entity.Vaga;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class EmpresaControl {

    private static final String URL = "jdbc:mariadb://localhost:3306/vagasdb?allowMultiQueries=true";
    private static final String USER = "root";
    private static final String PASSWORD = "123456";

    private ObservableList<Empresa> empresas = FXCollections.observableArrayList();
    private TableView<Empresa> table = new TableView<Empresa>();

    private LongProperty id = new SimpleLongProperty(1);
    private StringProperty nome = new SimpleStringProperty("");
    private StringProperty cnpj = new SimpleStringProperty("");
    private StringProperty razaoSocial = new SimpleStringProperty("");
    private StringProperty endereco = new SimpleStringProperty("");
    private StringProperty telefone = new SimpleStringProperty("");
    private EmpresaDAO service = new EmpresaDAOImpl();

    public void setEntity(Empresa empresa) {
        if (empresa != null) {
            id.set(empresa.getId());
            nome.set(empresa.getNome());
            telefone.set(empresa.getTelefone());
            cnpj.set(empresa.getCnpj());
            razaoSocial.set(empresa.getRazaoSocial());
            endereco.set(empresa.getEndereco());
        }
    }

    public Empresa getEntity() {
        Empresa empresa = new Empresa();
        empresa.setId(id.get());
        empresa.setNome(nome.get());
        empresa.setTelefone(telefone.get());
        empresa.setCnpj(cnpj.get());
        empresa.setRazaoSocial(razaoSocial.get());
        empresa.setEndereco(endereco.get());
        return empresa;
    }

    public void adicionar() {
        service.adicionar(getEntity());
        this.listarTodos();
    }

    public void pesquisarPorNome() {
        empresas.clear();
        empresas.addAll(service.pesquisarPorNome(getNome()));
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
        Empresa empresa = getEntity();
        empresa.setId(0);
        id.set(0);
        nome.set("");
        cnpj.set("");
        razaoSocial.set("");
        telefone.set("");
        endereco.set("");
        this.listarTodos();
    }

    private void listarTodos() {
        empresas.clear();
        empresas.addAll( service.pesquisarPorNome(""));
    }

    public void generatedTable() {
        listarTodos();

        TableColumn<Empresa, Long> colId = new TableColumn<>("Id");
        colId.setCellValueFactory(new PropertyValueFactory<Empresa, Long>("id"));

        TableColumn<Empresa, String> colNome = new TableColumn<>("Nome");
        colNome.setCellValueFactory(new PropertyValueFactory<Empresa, String>("nome"));

        TableColumn<Empresa, String> colCnpj = new TableColumn<>("Cnpj");
        colCnpj.setCellValueFactory(new PropertyValueFactory<Empresa, String>("Cnpj"));

        TableColumn<Empresa, String > colRazaoSocial = new TableColumn<>("Razao Social");
        colRazaoSocial.setCellValueFactory(new PropertyValueFactory<Empresa, String>("razaoSocial"));

        TableColumn<Empresa, String> colTelefone = new TableColumn<>("Telefone");
        colTelefone.setCellValueFactory(new PropertyValueFactory<Empresa, String>("telefone"));

        TableColumn<Empresa, String> colEndereco = new TableColumn<>("Endereço");
        colEndereco.setCellValueFactory(new PropertyValueFactory<Empresa, String>("endereco"));

        table.getColumns().addAll(colId, colNome, colCnpj, colRazaoSocial, colTelefone, colEndereco);

        table.getSelectionModel().selectedItemProperty().addListener(
                (obs, antigo, novo) -> {
                    setEntity(novo);
                }
        );
        table.setMaxHeight(200);

        table.setItems(empresas);
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
    public String getTelefone() {
        return telefone.get();
    }
    public StringProperty telefoneProperty() {
        return telefone;
    }
    public String getCnpj() {
        return cnpj.get();
    }
    public StringProperty cnpjProperty() {
        return cnpj;
    }
    public String getRazaoSocial() {
        return razaoSocial.get();
    }
    public StringProperty razaoSocialProperty() {
        return razaoSocial;
    }
    public String getEndereco() {
        return endereco.get();
    }
    public StringProperty enderecoProperty() {
        return endereco;
    }
    public TableView<Empresa> getTable() {
        return table;
    }
}