package br.imd.controle;

import java.io.File;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuBar;
import javafx.scene.control.ProgressBar;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import br.imd.modelo.Diretorio;
import br.imd.modelo.Musica;
import br.imd.modelo.Usuario;


public class UsuarioService {
	@FXML
    private ListView<String> listaDiretorios;
	@FXML
    private ListView<String> listaMusicas;
	@FXML
    private ProgressBar progressBar;
	@FXML
    private Label usernameLabel;
	@FXML
    private MenuBar menuBar;

    private ObservableList<String> itensDiretorios;
    private ObservableList<String> itensMusicas;
    private MusicaService musicaService;
    private DiretorioService diretorioService;
    private MediaPlayer mediaPlayer;
    private Timeline progressTimeline;

    public UsuarioService() {
    	musicaService = new MusicaService();
    	diretorioService = new DiretorioService();
    	itensDiretorios = FXCollections.observableArrayList();
    	itensMusicas = FXCollections.observableArrayList();
    }
    
    @FXML
    public void initialize() {
    	loginInformacoes();
        listarDiretorios();
    }
    
    @FXML
    private void logout(ActionEvent event) {
    	if (mediaPlayer != null) {    		
    		mediaPlayer.stop();
    	}
    	Main.getInstance().logout();
    }

    
    private void loginInformacoes() {
    	if (usernameLabel != null) {
    		Usuario user = LoginService.getInstance();
    		usernameLabel.setText(user.getLogin());
    	}
    }
    
    public void atualizarProgressBar(double valor) {
        progressBar.setProgress(valor);
    }
    
    @FXML
    public void onDiretorioClick() {
        String selectedItem = listaDiretorios.getSelectionModel().getSelectedItem();

        if (selectedItem != null) {
        	System.out.println(selectedItem);
        	diretorioService.setDiretorioAtual(selectedItem);
        	listarMusicas();
        }
    }
    
    @FXML
    public void onMusicaClick() {
        String selectedItem = listaMusicas.getSelectionModel().getSelectedItem();
        Musica musica = musicaService.getMusica(selectedItem);
        tocarMusica(false);
    }
    
    private void tocarMusica(Boolean autoPlay) {
    	if (mediaPlayer != null) {    		
    		mediaPlayer.stop();
    	}
    	Musica musica = MusicaService.getMusicaAtual();
    	if (musica != null) {
        	System.out.println("file:" + musica.getCaminho());
        	Media media = new Media("file:" + musica.getCaminho());
            mediaPlayer = new MediaPlayer(media);
            if (autoPlay) {            	
            	playMusica();
            }
        }
    }
    
    @FXML
    public void next() {
    	MusicaService.next();
    	tocarMusica(true);
    }
    
    @FXML
    public void left() {
    	MusicaService.left();
    	tocarMusica(true);
    }
    
    @FXML
    public void playMusica() {
    	if (mediaPlayer != null) {
	        mediaPlayer.setOnError(() -> {
	            System.out.println("Erro ao carregar a música.");
	        });
	
	        mediaPlayer.setOnEndOfMedia(() -> {
	            next();
	        });
	        
	     // Verifique se o MediaPlayer está pausado
	        if (mediaPlayer.getStatus() == MediaPlayer.Status.PAUSED) {
	            // Continuar de onde parou
	            mediaPlayer.play();
	        } else {
	            // Iniciar do zero
	            progressBar.setProgress(0.0);
	            progressTimeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
	                double progress = mediaPlayer.getCurrentTime().toSeconds() / mediaPlayer.getTotalDuration().toSeconds();
	                progressBar.setProgress(progress);
	            }));
	            progressTimeline.setCycleCount(Timeline.INDEFINITE);

	            // Iniciar o MediaPlayer e o Timeline
	            mediaPlayer.play();
	            progressTimeline.play();
	        }
    	}
    }
    
    @FXML
    public void pauseMusica() {
    	if (mediaPlayer != null && mediaPlayer.getStatus() == MediaPlayer.Status.PLAYING) {
            mediaPlayer.pause();
        }
    }
    
    @FXML
    public void handleProgressBarClick(MouseEvent event) {
        if (mediaPlayer != null && mediaPlayer.getStatus() == MediaPlayer.Status.PLAYING) {
            double mouseX = event.getX();
            double progressBarWidth = progressBar.getWidth();
            double progress = mouseX / progressBarWidth;
            Duration newTime = mediaPlayer.getTotalDuration().multiply(progress);
            mediaPlayer.seek(newTime);
        }
    }

    public void listarDiretorios() {
    	if (listaDiretorios == null) {
            System.out.println("Erro: listaDiretorios está nulo.");
            return;
        }
        for (Diretorio dir : diretorioService.getDiretorios()) {
            itensDiretorios.add(dir.getTitulo());
        }

        listaDiretorios.setItems(itensDiretorios);
        
        if (!itensDiretorios.isEmpty()) {
        	listarMusicas();
        }
    }

    public void listarMusicas() {
    	listaMusicas.getItems().clear();
    	
    	if (itensMusicas == null) {
            System.out.println("Erro: itensMusicas está nulo.");
            return;
        }
        Diretorio dir = diretorioService.getDiretorioAtual();
        
        MusicaService musicas = new MusicaService();
        for (Musica musica : musicas.getMusicas(dir)) {
            itensMusicas.add(musica.getTitulo());
        }

        listaMusicas.setItems(itensMusicas);
    }
	
	@FXML
	public void AdicionarDiretorio(ActionEvent event){
		DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Selecione um diretório");

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        File diretorioSelecionado = directoryChooser.showDialog(stage);

        if (diretorioSelecionado != null) {
            System.out.println("Diretório selecionado: " + diretorioSelecionado.getAbsolutePath());
            Boolean sucesso = diretorioService.escreverNovoDiretorioArquivo(
            		diretorioSelecionado.getName(),
            		diretorioSelecionado.getAbsolutePath()
            );
            	
            if (sucesso) {
                itensDiretorios.add(diretorioSelecionado.getName());
                listaDiretorios.setItems(itensDiretorios);
            }
            
        }
	}
	
	@FXML
	public void AdicionarMusica(ActionEvent event){
    	
		FileChooser fileChooser = new FileChooser();
        
        fileChooser.setTitle("Selecione uma Musica");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Áudios", "*.mp3", "*.wav"));
        
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        File arquivoSelecionado = fileChooser.showOpenDialog(stage);
        
        if (arquivoSelecionado != null) {
        	Diretorio dir = diretorioService.getDiretorioAtual();
        	System.out.println(arquivoSelecionado.getAbsolutePath());
        	musicaService.escreverNovaMusicaArquivo(arquivoSelecionado.getName(), arquivoSelecionado.getAbsolutePath(), dir);
        	listarMusicas();
        }
	}
}

