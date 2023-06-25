package br.imd.modelo;

public class Musica {
	private int id;
	private int duracao;
    private String titulo;
    private String album;
    private String artista;
    private String caminho;

    public Musica(int id, int duracao, String titulo, String album, String artista, String caminho) {
    	this.id = id; 
    	this.duracao = duracao;
        this.titulo = titulo;
        this.album = album;
        this.artista = artista;
        this.caminho = caminho;
    }
    
    // getters & setters
    
    public String getTitulo() {
		return titulo;
		
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public int getDuracao() {
		return duracao;
	}

	public void setDuracao(int duracao) {
		this.duracao = duracao;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAlbum() {
		return album;
	}

	public void setAlbum(String album) {
		this.album = album;
	}

	public String getArtista() {
		return artista;
	}

	public void setArtista(String artista) {
		this.artista = artista;
	}

	public String getCaminho() {
		return caminho;
	}

	public void setCaminho(String caminho) {
		this.caminho = caminho;
	}

}