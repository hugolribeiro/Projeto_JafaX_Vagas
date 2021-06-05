package br.com.fatec.poo.vagas.control;

import br.com.fatec.poo.vagas.DAO.VagaDAO;
import br.com.fatec.poo.vagas.DAO.VagaDAOImpl;
import br.com.fatec.poo.vagas.entity.Vaga;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class VagaControl {

    private ObservableList<Vaga> vagas = FXCollections.observableArrayList();
    private TableView<Vaga> table = new TableView<Vaga>();

    private LongProperty id = new SimpleLongProperty(1);
    private StringProperty cargo = new SimpleStringProperty("");
    private DoubleProperty salario = new SimpleDoubleProperty(0);
    private VagaDAO service = new VagaDAOImpl();

    public void setEntity(Vaga vaga) {
        if (vaga != null) {
            id.set(vaga.getId());
            cargo.set(vaga.getCargo());
            salario.set(vaga.getSalario());
        }
    }

    public Vaga getEntity() {
        Vaga vaga = new Vaga();
        vaga.setId(id.get());
        vaga.setCargo(cargo.get());
        vaga.setSalario(salario.get());
        return vaga;
    }

    public void adicionar() {
        service.adicionar(getEntity());
        this.listarTodos();
    }

    public void pesquisarPorCargo() {
        vagas.clear();
        vagas.addAll(service.pesquisarPorCargo(getCargo()));

    }

    public void atualizarPorId() {
        service.atualizarPorId(getId(),getEntity());
        this.pesquisarPorCargo();
    }

    public void removerPorId() {
        service.removerPorId(getId());
        this.listarTodos();
    }

    public void eraseFields(){
        Vaga vaga = getEntity();
        vaga.setId(0);
        id.set(0);
        cargo.set("");
        salario.set(0);
        this.listarTodos();
    }

    private void listarTodos() {
        vagas.clear();
        vagas.addAll( service.pesquisarPorCargo(""));
    }

    public void generatedTable() {
        listarTodos();

        TableColumn<Vaga, Long> colId = new TableColumn<>("Id");
        colId.setCellValueFactory(new PropertyValueFactory<Vaga, Long>("id"));

        TableColumn<Vaga, String> colCargo = new TableColumn<>("Cargo");
        colCargo.setCellValueFactory(new PropertyValueFactory<Vaga, String>("cargo"));

        TableColumn<Vaga, Double> colSalario = new TableColumn<>("Salario");
        colSalario.setCellValueFactory(new PropertyValueFactory<Vaga, Double>("salario"));

        table.getColumns().addAll(colId, colCargo, colSalario);

        table.getSelectionModel().selectedItemProperty().addListener(
                (obs, antigo, novo) -> {
                    setEntity(novo);
                }
        );

        table.setMaxHeight(280);
        table.setMaxWidth(400);
        table.setItems(vagas);
    }

    public long getId() {
        return id.get();
    }
    public LongProperty idProperty() {
        return id;
    }
    public String getCargo() {
        return cargo.get();
    }
    public StringProperty cargoProperty() {
        return cargo;
    }
    public Double getSalario() {
        return salario.get();
    }
    public DoubleProperty salarioProperty() {
        return salario;
    }
    public TableView<Vaga> getTable() {
        return table;
    }

}