package br.imd.controle;

import java.util.ArrayList;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import br.imd.modelo.Diretorio;
import br.imd.modelo.Musica;
import br.imd.modelo.Usuario;


public class MusicaService {

	private static ArrayList<Musica> musicas;
	private static int musicaAtual = -1;
	
    private static final String ARQUIVO = "src/data/musicas.txt";
    
    public MusicaService () {
    	musicas = new ArrayList<Musica>();
    }
    
    public static ArrayList<Musica> getMusicas(Diretorio dir) {
		
		try (BufferedReader br = new BufferedReader(new FileReader(ARQUIVO))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] partes = linha.split(",");
                if (partes.length == 4 && Integer.parseInt(partes[1]) == dir.getId()) {
            		Musica musica = new Musica();
            		musica.setId(Integer.parseInt(partes[0]));
            		musica.setDiretorioId(Integer.parseInt(partes[1]));
            		musica.setTitulo(partes[2]);
            		musica.setCaminho(partes[2]);
            		musicas.add(musica);
            		
            		if (musicaAtual < 0) {
            			musicaAtual = 0;
            		}
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
		return musicas;
    }

	private void escreverNovaMusicaArquivo(String musica, Diretorio dir) {
		
		String arquivo = "src/data/musicas.txt";
		
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

            // writer.write(login + "," + senha + "," + novoId + "," + idTipo);
            writer.newLine();

            writer.close();

            System.out.println("A nova linha foi adicionada ao arquivo com sucesso.");

        } catch (IOException e) {
            System.out.println("Ocorreu um erro ao adicionar a nova linha ao arquivo: " + e.getMessage());
        }
	}
}

