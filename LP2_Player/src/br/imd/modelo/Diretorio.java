package br.imd.modelo;

class Diretorio {
    private int id;
    private String caminho;

    public Diretorio(int id, String caminho) {
        this.id = id;
        this.caminho = caminho;
    }

    // getters & setters

    public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setCaminho(String caminho) {
		this.caminho = caminho;
	}

	public String getCaminho() {
        return caminho;
    }
}
