package br.imd.controle;

import java.util.ArrayList;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import br.imd.modelo.Diretorio;
import br.imd.modelo.Usuario;


public class DiretorioService {

	private static ArrayList<Diretorio> diretorios;
	private static int diretorioAtual = -1;
	private static final String ARQUIVO = "src/data/diretorios.txt";

    public DiretorioService() {
    	Usuario user = LoginService.getInstance();
    	diretorios = new ArrayList<Diretorio>();
		carregarDiretorios();
    	
    }
    
    
    public static ArrayList<Diretorio> getDiretorios() {
        return diretorios;
    }
    
    
    public static Diretorio getDiretorioAtual() {
        return diretorios.get(diretorioAtual);
    }
    
    
    private void carregarDiretorios() {
    	Usuario user = LoginService.getInstance();
    	Diretorio dir;
		
		try (BufferedReader br = new BufferedReader(new FileReader(ARQUIVO))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] partes = linha.split(",");
                if (partes.length == 3 && Integer.parseInt(partes[1]) == user.getId()) {
            		dir = new Diretorio();
            		dir.setId(Integer.parseInt(partes[0]));
            		dir.setUsuarioId(Integer.parseInt(partes[1]));
            		dir.setCaminho(partes[2]);
            		diretorios.add(dir);
            		
            		if (diretorioAtual < 0) {
            			diretorioAtual = 0;
            		}
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    
    public static void escreverNovoDiretorioArquivo(String caminho) {
		
		Usuario user = LoginService.getInstance();
		
		try {
            BufferedReader reader = new BufferedReader(new FileReader(ARQUIVO));
            String ultimaLinha = null;
            String linha;
            while ((linha = reader.readLine()) != null) {
                ultimaLinha = linha;
            }
            reader.close();

            int ultimoId = 0;
            if (ultimaLinha != null) {
            	String[] campos = ultimaLinha.split(",");
            	ultimoId = Integer.parseInt(campos[0]);
            }

            int novoId = ultimoId + 1;

            BufferedWriter writer = new BufferedWriter(new FileWriter(ARQUIVO, true));          

            writer.write(novoId + "," + user.getId() + "," + caminho);
            writer.newLine();
            
            Diretorio dir = new Diretorio();
    		dir.setId(novoId);
    		dir.setUsuarioId(user.getId());
    		dir.setCaminho(caminho);
    		diretorios.add(dir);

            writer.close();

            System.out.println("A nova linha foi adicionada ao arquivo com sucesso.");

        } catch (IOException e) {
            System.out.println("Ocorreu um erro ao adicionar a nova linha ao arquivo: " + e.getMessage());
        }
	}
}

