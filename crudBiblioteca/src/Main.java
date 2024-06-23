import models.*;
import utils.CsvUtil;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        List<Livro> livros = CsvUtil.lerLivros("data/livros.csv");
        List<Aluno> alunos = CsvUtil.lerAlunos("data/usuarios.csv");
        List<Bibliotecario> bibliotecarios = CsvUtil.lerBibliotecarios("data/usuarios.csv");

        Biblioteca biblioteca = new Biblioteca(livros, alunos, bibliotecarios);
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Login Aluno\n 2. Login Bibliotecário\n2. Listar Livros\n3. Sair");
            int opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir a nova linha

            switch (opcao) {
                case 1:
                    System.out.println("Login");
                    String login = scanner.nextLine();
                    System.out.println("Senha");
                    String senha = scanner.nextLine();

                    /*
                    // Lógica de login
                    System.out.println("Digite seu ID:");
                    String id = scanner.nextLine();
                    Aluno usuario = biblioteca.getAlunos().stream()
                            .filter(aluno -> aluno.getMatricula().equals(id))
                            .findFirst()
                            .orElse(null);
                    if (usuario == null) {
                        usuario = biblioteca.getBibliotecarios().stream()
                                .filter(bibliotecario -> bibliotecario.getId().equals(id))
                                .findFirst()
                                .orElse(null);
                    }

                    if (usuario == null) {
                        System.out.println("Usuário não encontrado.");
                        break;
                    }

                    if (usuario instanceof Bibliotecario) {
                        bibliotecarioMenu(scanner, biblioteca, (Bibliotecario) usuario);
                    } else {
                        alunoMenu(scanner, biblioteca, usuario);
                    }

                     */
                    break;
                case 2:
                    listarLivros(biblioteca);
                    break;
                case 3:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opção inválida.");
                    break;
            }
        }
    }

    private static void bibliotecarioMenu(Scanner scanner, Biblioteca biblioteca, Bibliotecario bibliotecario) {
        while (true) {
            System.out.println("1. Adicionar Livro\n2. Remover Livro\n3. Adicionar Aluno\n4. Remover Aluno\n5. Voltar");
            int opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir a nova linha

            switch (opcao) {
                case 1:
                    System.out.println("Digite as informações do livro (ID, Nome, Autor, Lançamento, Quantidade):");
                    String[] livroDados = scanner.nextLine().split(",");
                    Livro novoLivro = new Livro(livroDados[0], livroDados[1], livroDados[2], livroDados[3], Integer.parseInt(livroDados[4]));
                    bibliotecario.adicionarLivro(biblioteca, novoLivro);
                    break;
                case 2:
                    System.out.println("Digite o ID do livro a ser removido:");
                    String livroId = scanner.nextLine();
                    Livro livro = biblioteca.getLivros().stream()
                            .filter(l -> l.getId().equals(livroId))
                            .findFirst()
                            .orElse(null);
                    if (livro != null) {
                        bibliotecario.removerLivro(biblioteca, livro);
                    } else {
                        System.out.println("Livro não encontrado.");
                    }
                    break;
                case 3:
                    System.out.println("Digite as informações do aluno (Nome, Matrícula, Senha, Débito):");
                    String[] alunoDados = scanner.nextLine().split(",");
                    Aluno novoAluno = new Aluno(alunoDados[0], alunoDados[1], alunoDados[2], Double.parseDouble(alunoDados[3]));
                    bibliotecario.adicionarAluno(biblioteca, novoAluno);
                    break;
                case 4:
                    System.out.println("Digite a matrícula do aluno a ser removido:");
                    String matriculaAluno = scanner.nextLine();
                    Aluno aluno = biblioteca.getAlunos().stream()
                            .filter(a -> a.getMatricula().equals(matriculaAluno))
                            .findFirst()
                            .orElse(null);
                    if (aluno != null) {
                        bibliotecario.removerAluno(biblioteca, aluno);
                    } else {
                        System.out.println("Aluno não encontrado.");
                    }
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Opção inválida.");
                    break;
            }
        }
    }

    private static void alunoMenu(Scanner scanner, Biblioteca biblioteca, Aluno aluno) {
        while (true) {
            System.out.println("1. Listar Livros\n2. Empréstimo de Livro\n3. Devolução de Livro\n4. Pagar Débito\n5. Voltar");
            int opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir a nova linha

            switch (opcao) {
                case 1:
                    listarLivros(biblioteca);
                    break;
                case 2:
                    if (aluno.temDebito()) {
                        System.out.println("Você tem débito pendente. Pague o débito antes de fazer um empréstimo.");
                        break;
                    }
                    System.out.println("Digite o ID do livro para empréstimo:");
                    String emprestimoLivroId = scanner.nextLine();
                    Livro livroParaEmprestimo = biblioteca.getLivros().stream()
                            .filter(l -> l.getId().equals(emprestimoLivroId))
                            .findFirst()
                            .orElse(null);
                    if (livroParaEmprestimo != null && livroParaEmprestimo.isDisponivel()) {
                        livroParaEmprestimo.emprestar();
                        System.out.println("Livro emprestado com sucesso.");
                    } else {
                        System.out.println("Livro não disponível.");
                    }
                    break;
                case 3:
                    System.out.println("Digite o ID do livro para devolução:");
                    String devolucaoLivroId = scanner.nextLine();
                    Livro livroParaDevolucao = biblioteca.getLivros().stream()
                            .filter(l -> l.getId().equals(devolucaoLivroId))
                            .findFirst()
                            .orElse(null);
                    if (livroParaDevolucao != null) {
                        livroParaDevolucao.devolver();
                        System.out.println("Livro devolvido com sucesso.");
                    } else {
                        System.out.println("Livro não encontrado.");
                    }
                    break;
                case 4:
                    System.out.println("Digite o valor a ser pago:");
                    double valor = scanner.nextDouble();
                    scanner.nextLine(); // Consumir a nova linha
                    aluno.pagarDebito(valor);
                    System.out.println("Débito pago com sucesso.");
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Opção inválida.");
                    break;
            }
        }
    }

    private static void listarLivros(Biblioteca biblioteca) {
        for (Livro livro : biblioteca.getLivros()) {
            System.out.println("ID: " + livro.getId() + ", Nome: " + livro.getNome() + ", Autor: " + livro.getAutor() + ", Disponível: " + livro.isDisponivel());
        }
    }
}
