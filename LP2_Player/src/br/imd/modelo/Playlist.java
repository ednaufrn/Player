package br.imd.modelo;

import java.util.ArrayList;
import java.util.List;

class Playlist {
    private int id;
    private String nome;
    public int getId() {
		return id;
	}

    // getters & setters

	public void setId(int id) {
		this.id = id;
	}

	public List<Musica> getMusicas() {
		return musicas;
	}

	public void setMusicas(List<Musica> musicas) {
		this.musicas = musicas;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	private List<Musica> musicas;

    public Playlist(int id, String nome) {
        this.id = id;
        this.nome = nome;
        this.musicas = new ArrayList<>();
    }

    public void adicionarMusica(Musica musica) {
        musicas.add(musica);
    }

    public String getNome() {
        return nome;
    }
}