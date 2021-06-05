package br.com.fatec.poo.vagas.control;

import br.com.fatec.poo.vagas.DAO.CandidatoDAO;
import br.com.fatec.poo.vagas.DAO.CandidatoDAOImpl;
import br.com.fatec.poo.vagas.entity.Candidato;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;



public class CandidatoControl {

    private ObservableList<Candidato> candidatos = FXCollections.observableArrayList();
    private TableView<Candidato> table = new TableView<Candidato>();

    private LongProperty id = new SimpleLongProperty(1);
    private StringProperty nome = new SimpleStringProperty("");
    private StringProperty telefone = new SimpleStringProperty("");
    private StringProperty email = new SimpleStringProperty("");
    private StringProperty cpf = new SimpleStringProperty("");
    private StringProperty curriculo = new SimpleStringProperty("");
    private CandidatoDAO service = new CandidatoDAOImpl();

    public void setEntity(Candidato candidato) {
        if (candidato != null) {
            id.set(candidato.getId());
            nome.set(candidato.getNome());
            telefone.set(candidato.getTelefone());
            email.set(candidato.getEmail());
            cpf.set(candidato.getCpf());
            curriculo.set(candidato.getCurriculo());
        }
    }

    public Candidato getEntity() {
        Candidato candidato = new Candidato();
        candidato.setId(id.get());
        candidato.setNome(nome.get());
        candidato.setTelefone(telefone.get());
        candidato.setEmail(email.get());
        candidato.setCpf(cpf.get());
        candidato.setCurriculo(curriculo.get());
        return candidato;
    }

    public void adicionar() {
        service.adicionar(getEntity());
        this.listarTodos();
    }

    public void pesquisarPorNome() {
        candidatos.clear();
        candidatos.addAll(service.pesquisarPorNome(getNome()));
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
        Candidato candidato = getEntity();
        candidato.setId(0);
        id.set(0);
        nome.set("");
        cpf.set("");
        email.set("");
        telefone.set("");
        curriculo.set("");
        this.listarTodos();
    }

    private void listarTodos() {
        candidatos.clear();
        candidatos.addAll( service.pesquisarPorNome(""));
    }

    public void generatedTable() {
        listarTodos();
        TableColumn<Candidato, Long> colId = new TableColumn<>("Id");
        colId.setCellValueFactory(new PropertyValueFactory<Candidato, Long>("id"));

        TableColumn<Candidato, String> colNome = new TableColumn<>("Nome");
        colNome.setCellValueFactory(new PropertyValueFactory<Candidato, String>("nome"));

        TableColumn<Candidato, String> colCpf = new TableColumn<>("Cpf");
        colCpf.setCellValueFactory(new PropertyValueFactory<Candidato, String>("cpf"));

        TableColumn<Candidato, String > colEmail = new TableColumn<>("Email");
        colEmail.setCellValueFactory(new PropertyValueFactory<Candidato, String>("email"));

        TableColumn<Candidato, String> colCurriculo = new TableColumn<>("Curriculo");
        colCurriculo.setCellValueFactory(new PropertyValueFactory<Candidato, String>("curriculo"));

        table.getColumns().addAll(colId, colNome, colCpf, colEmail, colCurriculo);

        table.getSelectionModel().selectedItemProperty().addListener(
                (obs, antigo, novo) -> {
                    setEntity(novo);
                }
        );
        table.setMaxHeight(200);

        table.setItems(candidatos);
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
    public String getCpf() {
        return cpf.get();
    }
    public StringProperty cpfProperty() {
        return cpf;
    }
    public String getEmail() {
        return email.get();
    }
    public StringProperty emailProperty() {
        return email;
    }
    public String getCurriculo() {
        return curriculo.get();
    }
    public StringProperty curriculoProperty() {
        return curriculo;
    }
    public TableView<Candidato> getTable() {
        return table;
    }
}