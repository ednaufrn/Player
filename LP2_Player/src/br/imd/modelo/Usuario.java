package br.imd.modelo;

public abstract class Usuario {
    private String login;
    private String senha;
    private String id;

    public Usuario(String login, String senha, String id) {
        this.login = login;
        this.senha = senha;
        this.id = id;
    }

	public void tocarMusica(int idMusica) {
        // Implementar a lógica para tocar a música pelo ID
        System.out.println("Tocando música com ID: " + idMusica);
    }

    public void adicionarDiretorio(Diretorio diretorio) {
        // Implementar a lógica para adicionar o diretório
        System.out.println("Adicionando diretório: " + diretorio.getCaminho());
    }
    
    // getters & setters

    public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}