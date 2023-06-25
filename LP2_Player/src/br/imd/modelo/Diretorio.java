package br.imd.modelo;

public class Diretorio {
    private int id;
    private int usuarioId;
    private String titulo;
    private String caminho;

    // getters & setters

    public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

    public int getUsuarioId() {
		return usuarioId;
	}

	public void setUsuarioId(int id) {
		this.usuarioId = id;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getTitulo() {
        return titulo;
    }

	public void setCaminho(String caminho) {
		this.caminho = caminho;
	}

	public String getCaminho() {
        return caminho;
    }
}
