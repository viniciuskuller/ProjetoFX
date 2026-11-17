package org.example;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.time.LocalDate;

public class Controller {
    private TextField campoNome = new TextField();
    private TextField campoPrecoAtacado = new TextField();
    private TextField campoPrecoVenda = new TextField();
    private TextField campoQuantidade = new TextField();
    private DatePicker pickerValidade = new DatePicker();
    private DatePicker pickerLote = new DatePicker();
    private Button btnSalvar= new Button("Salvar Produto");

    private TableView<Produto> tabelaProdutos = new TableView<>();
    private ObservableList<Produto> listaProdutos = FXCollections.observableArrayList();

    public Controller(){
        configurarTabela();

        btnSalvar.setOnAction(evento -> aoClicarSalvar);
    }
    public VBox getLayout() {
        // Organiza os campos do formulário
        VBox formulario = new VBox(10); // 10px de espaçamento entre os itens
        formulario.getChildren().addAll(
                new Label("Nome do Produto:"), campoNome,
                new Label("Preço Atacado:"), campoPrecoAtacado,
                new Label("Preço Venda:"), campoPrecoVenda,
                new Label("Quantidade:"), campoQuantidade,
                new Label("Data do Lote:"), pickerLote,
                new Label("Data de Validade:"), pickerValidade,
                btnSalvar
        );

        // Junta o formulário e a tabela no layout principal
        VBox layoutPrincipal = new VBox(20);
        layoutPrincipal.setPadding(new Insets(15));
        layoutPrincipal.getChildren().addAll(formulario, tabelaProdutos);

        return layoutPrincipal;
    }



    private void configurarTabela() {
        TableColumn<Produto, String> colNome = new TableColumn<>("Nome");
        colNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        TableColumn<Produto, Double> colVenda = new TableColumn<>("Preço Venda");
        colVenda.setCellValueFactory(new PropertyValueFactory<>("precoVenda"));
        TableColumn<Produto, Integer> colQtd = new TableColumn<>("Qtd");
        colQtd.setCellValueFactory(new PropertyValueFactory<>("quantidade"));
        TableColumn<Produto, Long> colDias = new TableColumn<>("Dias Restantes");
        colDias.setCellValueFactory(new PropertyValueFactory<>("diasRestantes"));

        tabelaProdutos.getColumns().addAll(colNome, colVenda, colQtd, colDias);
        tabelaProdutos.setItems(listaProdutos);
    }
    private void aoClicarSalvar() {
        try {
            String nome = campoNome.getText();
            double atacado = Double.parseDouble(campoPrecoAtacado.getText());
            double venda = Double.parseDouble(campoPrecoVenda.getText());
            int qtd = Integer.parseInt(campoQuantidade.getText());
            LocalDate lote = pickerLote.getValue();
            LocalDate validade = pickerValidade.getValue();
            Produto produto = new Produto(nome, atacado, venda, qtd, lote, validade);
            listaProdutos.add(produto);
            limparCampos();
        } catch (NumberFormatException e) {
            mostrarErro("Preencha os preços e a quantidade apenas com números válidos.");
        } catch (IllegalArgumentException e) {
            mostrarErro(e.getMessage());
        }
    }



    private void mostrarErro(String mensagem) {
        Alert alerta = new Alert(Alert.AlertType.ERROR);
        alerta.setTitle("Erro de Validação");
        alerta.setHeaderText(null);
        alerta.setContentText(mensagem);
        alerta.showAndWait();
    }
    private void limparCampos() {
        campoNome.clear();
        campoPrecoAtacado.clear();
        campoPrecoVenda.clear();
        campoQuantidade.clear();
        pickerLote.setValue(null);
        pickerValidade.setValue(null);
        campoNome.requestFocus();

    }
}
