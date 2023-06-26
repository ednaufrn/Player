package br.imd.controle;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.ProgressBar;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.DirectoryChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import br.imd.modelo.Diretorio;
import br.imd.modelo.Musica;
import br.imd.modelo.Usuario;


public class UsuarioVipService extends UsuarioService {

	public void AdicionarUsuario(){
		Stage stage = new Stage();
	    stage.setTitle("Modal");

	    try {
	        Parent telaCadastroUsuario = FXMLLoader.load(getClass().getResource("/br/imd/visao/TelaCadastroUsuario.fxml"));
	        Scene sceneUsuarioVip = new Scene(telaCadastroUsuario);
	        stage.setScene(sceneUsuarioVip);
	        stage.initModality(Modality.APPLICATION_MODAL);

	        stage.showAndWait();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	
	
	public void Cadastrar(ActionEvent event) throws IOException {
		Node node = (Node) event.getSource();
	    Stage stage = (Stage) node.getScene().getWindow();
        
		TextField usuarioTextField = (TextField) stage.getScene().lookup("#NovoUsuario");
        PasswordField senhaPasswordField = (PasswordField) stage.getScene().lookup("#senhaNovoUsuario");
        CheckBox vip = (CheckBox) stage.getScene().lookup("#checkBoxVip");
        
        String usuario = usuarioTextField.getText();
        String senha = senhaPasswordField.getText();
        boolean checkBox = vip.isSelected();
        String tipoUsuario;
        if(checkBox) {
        	tipoUsuario = "2";
        }
        else {
        	tipoUsuario = "1";
        }
        
        escreverNovoUsuarioArquivo(usuario, senha, tipoUsuario);
        
        stage.close();
	}
	
	private void escreverNovoUsuarioArquivo(String login, String senha, String idTipo) {
		
		String arquivo = "src/data/logins.txt";
		
		try {
            BufferedReader reader = new BufferedReader(new FileReader(arquivo));
            String ultimaLinha = null;
            String linha;
            while ((linha = reader.readLine()) != null) {
                ultimaLinha = linha;
            }
            reader.close();

            int ultimoId = 0;
            if (ultimaLinha != null) {
            	String[] campos = ultimaLinha.split(",");
            	ultimoId = Integer.parseInt(campos[2]);
            }

            int novoId = ultimoId + 1;

            BufferedWriter writer = new BufferedWriter(new FileWriter(arquivo, true));

            writer.write(login + "," + senha + "," + novoId + "," + idTipo);
            writer.newLine();

            writer.close();

            System.out.println("A nova linha foi adicionada ao arquivo com sucesso.");

        } catch (IOException e) {
            System.out.println("Ocorreu um erro ao adicionar a nova linha ao arquivo: " + e.getMessage());
        }
	}
}

