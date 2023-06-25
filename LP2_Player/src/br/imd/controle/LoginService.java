package br.imd.controle;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import br.imd.modelo.Usuario;
import br.imd.modelo.UsuarioComum;
import br.imd.modelo.UsuarioVip;
import br.imd.modelo.RetornoLogin;


public class LoginService {
	private static Usuario instance;
    private static final String ARQUIVO_BANCO_DADOS = "src/data/logins.txt";
    
    public static Usuario getInstance() {
        return instance;
    }

    public static RetornoLogin verificarCredenciais(String usuario, String senha) {
        try (BufferedReader br = new BufferedReader(new FileReader(ARQUIVO_BANCO_DADOS))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] partes = linha.split(",");
                if (partes.length == 4 && partes[0].equals(usuario) && partes[1].equals(senha)) {
                	if(partes[3].equals("2")) {
                		instance = new UsuarioVip(partes[0], partes[1], Integer.parseInt(partes[2]));
                		return new RetornoLogin(partes[3], null, (UsuarioVip) instance);
                	}
                	else if (partes[3].equals("1")) {
                		instance = new UsuarioComum(partes[0], partes[1], Integer.parseInt(partes[2]));
                		return new RetornoLogin(partes[3], (UsuarioComum) instance, null);
                	}
                	else {
                		return new RetornoLogin("0",null,null);
                	}
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new RetornoLogin("0",null,null);
    }
}

