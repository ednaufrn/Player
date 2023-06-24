package br.imd.controle;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LoginService {
    private static final String ARQUIVO_BANCO_DADOS = "C:\\data\\logins.txt";

    public static boolean verificarCredenciais(String usuario, String senha) {
        try (BufferedReader br = new BufferedReader(new FileReader(ARQUIVO_BANCO_DADOS))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] partes = linha.split(",");
                if (partes.length == 2 && partes[0].equals(usuario) && partes[1].equals(senha)) {
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public void handleButtonClick() {
    }
}

