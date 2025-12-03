package gui;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import dao.MaeDAO;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.Mae;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.text.Font;

public class HomePage {

    private BorderPane root;
    private MaeDAO maeDAO = new MaeDAO();

    public HomePage() {
        root = new BorderPane();
        root.getStyleClass().add("root");

        //--------------------------------------------
        // BARRA SUPERIOR
        //--------------------------------------------
        HBox topBar = new HBox();
        topBar.getStyleClass().add("top-bar");
        topBar.setPadding(new Insets(15));
        topBar.setSpacing(40);
        topBar.setAlignment(Pos.CENTER);

        Button btnCadastro = new Button("Cadastro");
        Button btnEncontros = new Button("Encontros");
        
        btnCadastro.getStyleClass().add("top-button");
        btnEncontros.getStyleClass().add("top-button");

        // Ações dos botões
        btnCadastro.setOnAction(e -> {
            Main.mudarCena("cadastro");
        });

        btnEncontros.setOnAction(e -> {
            // Já estamos na homepage, então não faz nada
        });

        topBar.getChildren().addAll(btnCadastro, btnEncontros);

        //--------------------------------------------
        // CONTEÚDO CENTRAL
        //--------------------------------------------
        VBox content = new VBox();
        content.getStyleClass().add("content");
        content.setAlignment(Pos.TOP_CENTER);
        content.setSpacing(30);
        content.setPadding(new Insets(40));

        Label bemVinda = new Label("Bem vinda!");
        bemVinda.getStyleClass().add("label-bemvinda");

        Label tituloAniv = new Label("Aniversariantes do mês");
        tituloAniv.getStyleClass().add("label-aniversariantes");

        TableView<Mae> tabela = criarTabelaAniversariantes();

        VBox tableBox = new VBox(tabela);
        tableBox.getStyleClass().add("table-box");
        tableBox.setAlignment(Pos.CENTER);
        tableBox.setPadding(new Insets(20));

        content.getChildren().addAll(bemVinda, tituloAniv, tableBox);

        //--------------------------------------------
        // DEFINIR LAYOUT FINAL
        //--------------------------------------------
        root.setTop(topBar);
        root.setCenter(content);
    }

    private TableView<Mae> criarTabelaAniversariantes(){
        TableView<Mae> tabela = new TableView<>();
        tabela.getStyleClass().add("aniversariantes-table");
        tabela.setPrefWidth(800);
        tabela.setPrefHeight(300);

        TableColumn<Mae, String> colNome = new TableColumn<>("Aniversariantes");
        colNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colNome.getStyleClass().add("table-column");
        colNome.setPrefWidth(600);
        
        // Centralizar conteúdo da coluna Nome
        colNome.setCellFactory(tc -> {
            javafx.scene.control.TableCell<Mae, String> cell = new javafx.scene.control.TableCell<>() {
                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty || item == null) {
                        setText(null);
                    } else {
                        setText(item);
                    }
                }
            };
            cell.setAlignment(Pos.CENTER);
            return cell;
        });

        TableColumn<Mae, String> colData = new TableColumn<>("Data");
        colData.setCellValueFactory(cellData -> {
            LocalDate d = cellData.getValue().getData_aniversario();
            String dataFormatada = d.format(DateTimeFormatter.ofPattern("dd/MM"));
            return new SimpleStringProperty(dataFormatada);
        });
        colData.getStyleClass().add("table-column");
        colData.setPrefWidth(200);
        
        // Centralizar conteúdo da coluna Data
        colData.setCellFactory(tc -> {
            javafx.scene.control.TableCell<Mae, String> cell = new javafx.scene.control.TableCell<>() {
                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty || item == null) {
                        setText(null);
                    } else {
                        setText(item);
                    }
                }
            };
            cell.setAlignment(Pos.CENTER);
            return cell;
        });

        tabela.getColumns().addAll(colNome, colData);
        
        // Faz as colunas ocuparem toda a largura da tabela
        tabela.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        carregarAniversariantesMes(tabela);

        return tabela;
    }

    private void carregarAniversariantesMes(TableView<Mae> tabela){
        int mesAtual = LocalDate.now().getMonthValue();
        List<Mae> lista = maeDAO.listarAniversariantesDoMes(mesAtual);
        tabela.getItems().setAll(lista);
    }

    public BorderPane getRoot() {
        return root;
    }
}