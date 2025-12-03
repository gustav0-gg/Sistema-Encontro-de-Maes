package gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    private static Stage primaryStage;
    private static Scene homeScene;
    private static Scene cadastroScene;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;

        // Cria a homepage
        HomePage homePage = new HomePage();
        homeScene = new Scene(homePage.getRoot(), 1100, 650);

        // Cria a tela de cadastro
        CadastroMaePage cadastroPage = new CadastroMaePage();
        cadastroScene = new Scene(cadastroPage.getRoot(), 1100, 650);

        // Carrega o CSS
        homeScene.getStylesheets().add(getClass().getResource("/gui/HomePage.css").toExternalForm());
        cadastroScene.getStylesheets().add(getClass().getResource("/gui/HomePage.css").toExternalForm());

        primaryStage.setScene(homeScene);
        primaryStage.setTitle("Página Inicial");
        primaryStage.show();
    }

    public static void mudarCena(String cena) {
        switch (cena) {
            case "home":
                primaryStage.setScene(homeScene);
                primaryStage.setTitle("Página Inicial");
                break;
            case "cadastro":
                primaryStage.setScene(cadastroScene);
                primaryStage.setTitle("Cadastro de Mãe");
                break;
            default:
                break;
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}