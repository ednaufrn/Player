# Projeto Media Player

Este projeto Media Player é um aplicativo de gerenciamento de músicas, onde os usuários podem organizar suas músicas em diretórios e reproduzi-las. O aplicativo foi desenvolvido para a disciplina Linguagem de Programação II - BTI - IMD/UFRN

## Tecnologias Utilizadas

- JavaFX 20
- JDK 20
- Scene Builder

## Autores

- Edna Juvencio Nunes
- Mateus Alves de Oliveira

## Especificações

O projeto possui as seguintes classes e funcionalidades:

- Classe `Usuario`: Representa um usuário do sistema e possui atributos como login, senha e ID.
- Classe `UsuarioComum`: Extensão da classe `Usuario`, representa um usuário comum com recursos de gerenciamento de músicas.
- Classe `UsuarioVip`: Extensão da classe `Usuario`, representa um usuário VIP com recursos de gerenciamento de músicas e criação de novos usuarios para ter acesso ao Player.
- Classe `Diretorio`: Representa um diretório de músicas e possui uma lista de músicas.
- Classe `Musica`: Representa uma música com atributos como título, caminho do arquivo e duração.
- Classe `GerenciadorMusica`: Responsável por gerenciar o aplicativo, incluindo a adição, remoção e listagem de músicas.

O projeto utiliza JavaFX para a criação da interface gráfica do aplicativo, JDK para a execução do código Java e Scene Builder para a criação e edição das interfaces de usuário.

## Executando o Projeto com o Eclipse

1. Certifique-se de ter o JDK 20 instalado em seu sistema.
2. Baixe e configure o Scene Builder.
3. Clone este repositório.
4. Abra o Eclipse.
5. Importe o projeto em "File" > "Import" > "Existing Projects into Workspace".
6. Selecione o diretório do projeto e clique em "Finish".
7. Configure a biblioteca do JavaFX no projeto.
8. No pacote principal do projeto, encontre a classe `Main.java` e execute-a.

