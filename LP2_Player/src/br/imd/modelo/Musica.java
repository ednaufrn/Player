package br.imd.modelo;

public class Musica {
	private int id;
	private int diretorioId;
    private String titulo;
    private String caminho;
    
    // getters & setters

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

    public int getDiretorioId() {
		return diretorioId;
	}

	public void setDiretorioId(int id) {
		this.diretorioId = id;
	}

    
    public String getTitulo() {
		return titulo;
		
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getCaminho() {
		return caminho;
	}

	public void setCaminho(String caminho) {
		this.caminho = caminho;
	}

}