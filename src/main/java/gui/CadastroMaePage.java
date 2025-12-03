package gui;

import dao.MaeDAO;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.Mae;

import java.time.LocalDate;

public class CadastroMaePage {

    private BorderPane root;
    private MaeDAO maeDAO = new MaeDAO();

    public CadastroMaePage() {
        root = new BorderPane();
        root.getStyleClass().add("root");

        // Barra superior (igual à homepage)
        HBox topBar = new HBox();
        topBar.getStyleClass().add("top-bar");
        topBar.setPadding(new Insets(15));
        topBar.setSpacing(40);
        topBar.setAlignment(Pos.CENTER);

        Button btnInicial = new Button("Inicial");
        Button btnEncontros = new Button("Encontros");

        btnInicial.getStyleClass().add("top-button");
        btnEncontros.getStyleClass().add("top-button");

        topBar.getChildren().addAll(btnInicial, btnEncontros);

        // Conteúdo central
        VBox content = new VBox();
        content.getStyleClass().add("content");
        content.setAlignment(Pos.TOP_CENTER);
        content.setSpacing(30);
        content.setPadding(new Insets(40));

        Label titulo = new Label("Nova Mãe no Caminho de Fé");
        titulo.getStyleClass().add("label-bemvinda"); // Reusando o estilo do título da homepage

        // Formulário
        VBox form = new VBox();
        form.setSpacing(20);
        form.setAlignment(Pos.CENTER);
        form.setPadding(new Insets(20));
        form.getStyleClass().add("form-box");

        // Campo Nome
        VBox campoNome = new VBox(5);
        Label labelNome = new Label("Nome");
        labelNome.getStyleClass().add("form-label");
        TextField textNome = new TextField();
        textNome.setPromptText("Insira o nome");
        textNome.getStyleClass().add("form-field");
        campoNome.getChildren().addAll(labelNome, textNome);

        // Campo Endereço
        VBox campoEndereco = new VBox(5);
        Label labelEndereco = new Label("Endereço");
        labelEndereco.getStyleClass().add("form-label");
        TextField textEndereco = new TextField();
        textEndereco.setPromptText("Insira o endereço");
        textEndereco.getStyleClass().add("form-field");
        campoEndereco.getChildren().addAll(labelEndereco, textEndereco);

        // Campo Telefone
        VBox campoTelefone = new VBox(5);
        Label labelTelefone = new Label("Telefone");
        labelTelefone.getStyleClass().add("form-label");
        TextField textTelefone = new TextField();
        textTelefone.setPromptText("Insira o número de telefone");
        textTelefone.getStyleClass().add("form-field");
        campoTelefone.getChildren().addAll(labelTelefone, textTelefone);

        // Campo Data de Aniversário
        VBox campoData = new VBox(5);
        Label labelData = new Label("Data de Aniversário");
        labelData.getStyleClass().add("form-label");
        DatePicker datePicker = new DatePicker();
        datePicker.setPromptText("Selecione a data de aniversário");
        datePicker.getStyleClass().add("form-field");
        campoData.getChildren().addAll(labelData, datePicker);

        // Botão Salvar
        Button btnSalvar = new Button("Salvar");
        btnSalvar.getStyleClass().add("form-button");

        // Ação do botão Salvar
        btnSalvar.setOnAction(e -> {
            // Validação básica
            if (textNome.getText().isEmpty() || textEndereco.getText().isEmpty() || 
                textTelefone.getText().isEmpty() || datePicker.getValue() == null) {
                // Mostrar mensagem de erro
                System.out.println("Preencha todos os campos!");
                return;
            }

            Mae mae = new Mae(0, null, null, null, null);
            mae.setNome(textNome.getText());
            mae.setEndereco(textEndereco.getText());
            mae.setTelefone(textTelefone.getText());
            mae.setData_aniversario(datePicker.getValue());

            maeDAO.inserir(mae);

            // Limpar campos após salvar
            textNome.clear();
            textEndereco.clear();
            textTelefone.clear();
            datePicker.setValue(null);

            System.out.println("Mãe cadastrada com sucesso!");
        });

        form.getChildren().addAll(campoNome, campoEndereco, campoTelefone, campoData, btnSalvar);
        content.getChildren().addAll(titulo, form);

        root.setTop(topBar);
        root.setCenter(content);

        // Ações dos botões da barra superior
        btnInicial.setOnAction(e -> {
        	Main.mudarCena("home");
        });

        btnEncontros.setOnAction(e -> {
            // Voltar para a homepage
            ;
        });
    }

    public BorderPane getRoot() {
        return root;
    }
}