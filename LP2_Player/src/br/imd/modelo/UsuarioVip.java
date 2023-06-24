package br.imd.modelo;

class UsuarioVip extends Usuario {
    public UsuarioVip(String login, String senha, int id) {
        super(login, senha, id);
    }

    public void criarPlaylist(Playlist playlist) {
        // Implementar a lógica para criar a playlist
        System.out.println("Criando playlist: " + playlist.getNome());
    }

    public void cadastrarUsuario(Usuario usuario) {
        // Implementar a lógica para cadastrar um novo usuário
        System.out.println("Cadastrando usuário: " + usuario.getLogin());
    }

    public void atrelarPlaylist(Playlist playlist) {
        // Implementar a lógica para atrelar a playlist a um usuário
        System.out.println("Atrelando playlist: " + playlist.getNome());
    }
}