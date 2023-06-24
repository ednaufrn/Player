package br.imd.modelo;

class UsuarioAdmin extends Usuario {
    public UsuarioAdmin(String login, String senha, int id) {
        super(login, senha, id);
    }

    public void iniciarAplicacao() {
        System.out.println("Iniciando aplicação");
    }
}