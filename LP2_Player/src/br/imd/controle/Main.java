package br.imd.controle;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;

public class Main extends Application {
    private static final String TITULO_JANELA = "Tela de Login";

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle(TITULO_JANELA);
        Parent root = FXMLLoader.load(getClass().getResource("/br/imd/visao/TelaLogin.fxml"));
        Scene scene = new Scene(root);
        
        primaryStage.setScene(scene);
        
        TextField usuarioTextField = (TextField) primaryStage.getScene().lookup("#usuario");
        PasswordField senhaPasswordField = (PasswordField) primaryStage.getScene().lookup("#senha");

        Button loginButton = (Button) primaryStage.getScene().lookup("#botaoLogin");

        loginButton.setOnAction(e -> {
            String usuario = usuarioTextField.getText();
            String senha = senhaPasswordField.getText();

            if (LoginService.verificarCredenciais(usuario, senha)) {
                System.out.println("Login bem-sucedido");
            } else {
                System.out.println("Login inválido");
            }
        });

        primaryStage.show();
    }
}
