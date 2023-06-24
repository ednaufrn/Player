package br.imd.controle;

import java.io.IOException;

import br.imd.modelo.RetornoLogin;
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
            
            RetornoLogin retorno = LoginService.verificarCredenciais(usuario, senha);
            
            if (retorno.getCodigo() != "0") {
                System.out.println("Login bem-sucedido");
                if(retorno.getUsuarioComum() == null) {
                	try {
						Parent telaVip = FXMLLoader.load(getClass().getResource("/br/imd/visao/TelaUsuarioVIP.fxml"));
						Scene sceneUsuarioVip = new Scene(telaVip);
						primaryStage.setScene(sceneUsuarioVip);
					} catch (IOException e1) {
						e1.printStackTrace();
					}
                }
                else {
                	try {
						Parent telaComum = FXMLLoader.load(getClass().getResource("/br/imd/visao/TelaUsuario.fxml"));
						Scene sceneUsuarioComum = new Scene(telaComum);
						primaryStage.setScene(sceneUsuarioComum);
					} catch (IOException e1) {
						e1.printStackTrace();
					}
                }
            } else {
                System.out.println("Login inválido");
            }
        });

        primaryStage.show();
    }
}
