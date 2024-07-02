import models.*;
import utils.CsvUtil;

import java.util.List;
import java.util.Scanner;

import static utils.CsvUtil.lerEmprestimos;

public class Main {
    public static String caminhoAlunos = "/home/azorbos/IntellijProjects/crudBiblioteca08/crudBiblioteca/data/alunos.csv";
    public static String caminhoBibiliotecarios = "/home/azorbos/IntellijProjects/crudBiblioteca08/crudBiblioteca/data/bibliotecarios.csv";
    public static String caminhoEmprestimos = "/home/azorbos/IntellijProjects/crudBiblioteca08/crudBiblioteca/data/emprestimos.csv";
    public static String caminhoLivros = "/home/azorbos/IntellijProjects/crudBiblioteca08/crudBiblioteca/data/livros.csv";

    public static void main(String[] args) {
        List<Livro> Livros = CsvUtil.lerLivros(caminhoLivros);
        List<Aluno> Alunos = CsvUtil.lerAlunos(caminhoAlunos);
        List<Bibliotecario> Bibliotecarios = CsvUtil.lerBibliotecarios(caminhoBibiliotecarios);
        List<Emprestimo> Emprestimos = CsvUtil.lerEmprestimos(caminhoEmprestimos);

        SistemaBiblioteca sistemaBiblioteca = new SistemaBiblioteca(Livros, Alunos, Bibliotecarios, Emprestimos);
        Scanner scanner = new Scanner(System.in);


        //SistemaBiblioteca.verificarEmprestimos(Emprestimos, Alunos, Livros);
        menuInicial(sistemaBiblioteca, scanner);
    }

    //Logins
    private static void loginAluno(Scanner scanner, SistemaBiblioteca sistemaBiblioteca) {
        System.out.print("Matrícula: ");
        String matricula = scanner.nextLine();
        System.out.print("Senha: ");
        String senha = scanner.nextLine();

        Aluno Aluno = sistemaBiblioteca.getAlunos().stream()
                .filter(a -> a.getMatricula().equals(matricula) && a.getSenha().equals(senha))
                .findFirst()
                .orElse(null);

        if (Aluno == null) {
            System.out.println("Aluno não encontrado ou senha incorreta.");
            return;
        }

        menuAluno(scanner, sistemaBiblioteca, Aluno);
    }

    private static void loginBibliotecario(Scanner scanner, SistemaBiblioteca sistemaBiblioteca) {
        System.out.print("Id: ");
        String id = scanner.nextLine();
        System.out.print("Senha: ");
        String senha = scanner.nextLine();

        Bibliotecario bibliotecario = sistemaBiblioteca.getBibliotecarios().stream()
                .filter(b -> b.getId().equals(id) && b.getSenha().equals(senha))
                .findFirst()
                .orElse(null);

        if (bibliotecario == null) {
            System.out.println("Bibliotecário não encontrado ou senha incorreta.");
            return;
        }

        menuBibliotecario(scanner, sistemaBiblioteca, bibliotecario);
    }

    //Menus
    public static void menuInicial(SistemaBiblioteca sistemaBiblioteca, Scanner scanner){
        while (true) {
            System.out.println("\n========== Biblioteca ==========");
            System.out.println("[1] Login Aluno");
            System.out.println("[2] Login Bibliotecário");
            System.out.println("================================");
            System.out.println("[3] Sair");
            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    loginAluno(scanner, sistemaBiblioteca);
                    break;
                case 2:
                    loginBibliotecario(scanner, sistemaBiblioteca);
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

    private static void menuBibliotecario(Scanner scanner, SistemaBiblioteca sistemaBiblioteca, Bibliotecario bibliotecario) {
        while (true) {
            System.out.println("\n========== Biblioteca ==========");
            System.out.println("==== Painel do bibliotecário ====");
            System.out.println("Bem vindo(a) " + bibliotecario.getNome());
            System.out.println("================================");

            System.out.println("[1] Adicionar livro");
            System.out.println("[2] Remover livro");
            System.out.println("[3] Listar livros");

            System.out.println("\n[4] Adicionar aluno");
            System.out.println("[5] Remover aluno");
            System.out.println("[6] Listar alunos");

            System.out.println("\n[7] Listar empréstimos");

            System.out.println("\n[8] Voltar");
            int opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir a nova linha

            switch (opcao) {
                case 1:
                    criarLivro(scanner, sistemaBiblioteca);
                    break;
                case 2:
                    deletarLivro(scanner, sistemaBiblioteca);
                    break;
                case 3:
                    listarLivros(sistemaBiblioteca);
                    break;
                case 4:
                    criarAluno(scanner, sistemaBiblioteca);
                    break;
                case 5:
                    deletarAluno(scanner, sistemaBiblioteca);
                    break;
                case 6:
                    listarAlunos(sistemaBiblioteca);
                    break;
                case 7:
                    listarEmprestimos(sistemaBiblioteca);
                    break;
                case 8:
                    return;
                default:
                    System.out.println("Opção inválida.");
                    break;
            }
        }
    }

    private static void menuAluno(Scanner scanner, SistemaBiblioteca sistemaBiblioteca, Aluno Aluno) {
        while (true) {
            System.out.println("\n========== Biblioteca ==========");
            System.out.println("======= Painel do Aluno ========");
            System.out.println("Bem vindo(a) " + Aluno.getNome());
            System.out.println("================================");
            if (Aluno.temDebito()) {
                System.out.printf("VOCÊ POSSUI UM DÉBITO DE R$%.2f\n", Aluno.getDebito());
                System.out.println("================================");
            }
            System.out.println("[1] Listar todos os livros da Biblioteca");
            System.out.println("\n[2] Realizar Empréstimo");
            System.out.println("[3] Fazer devolução");
            System.out.println("\n[4] Meus Livros");
            System.out.println("[5] Pagar débito");
            System.out.println("\n[0] Voltar");

            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1: // Mostrar TODA a biblioteca
                    listarLivros(sistemaBiblioteca);
                    break;
                case 2: // Fazer empréstimo
                    System.out.print("ID do Livro: ");
                    String idLivro = scanner.nextLine();
                    sistemaBiblioteca.realizarEmprestimoLivro(Aluno.getMatricula(), idLivro);
                    break;
                case 3: // Fazer devolução
                    System.out.println("Digite o ID do livro para devolução:");
                    String idDevolucao = scanner.nextLine();
                    sistemaBiblioteca.realizarDevolucaoLivro(Aluno.getMatricula(), idDevolucao);
                    break;
                case 4: // Livros do aluno
                    listarLivrosAluno(Aluno);
                    //Aluno.mostrarLivrosEmprestados();
                    //Aluno.atualizarLivrosEmprestados(caminhoEmprestimos);
                    break;
                case 5:  // Pagar débito
                    quitarDebito(Aluno, scanner);
                    break;
                case 0: // Voltar menu anterior
                    return;
                default:
                    System.out.println("Opção inválida.");
                    break;
            }
        }
    }

    // Métodos de Aluno
    public static void listarLivrosAluno(Aluno Aluno) {
        Aluno.atualizarLivrosEmprestados(caminhoEmprestimos);

        System.out.println("\nSeus Livros:");
        if (Aluno.getLivros().isEmpty()) {
            System.out.println("Você não possui livros emprestados.");
        } else {
            for (Livro Livro : Aluno.getLivros()) {
                System.out.println(Livro.getNome() + " - ID: " + Livro.getId());
            }
        }
    }

    public static void quitarDebito(Aluno Aluno, Scanner scanner){
        System.out.printf("\nSeu débito: R$%.2f", Aluno.getDebito());
        System.out.println("\nDigite o valor a ser pago:");
        double valor = scanner.nextDouble();
        scanner.nextLine(); // Consumir a nova linha
        Aluno.pagarDebito(valor);
        System.out.println("Débito pago com sucesso.");
    }

    //Métodos de Bibliotecário
    public static void criarLivro(Scanner scanner, SistemaBiblioteca sistemaBiblioteca){
        System.out.println("Digite as informações do livro separado por espaço(ID, Nome, Autor, Lançamento, Quantidade):");
        String[] livroDados = scanner.nextLine().split(" ");
        Livro novoLivro = new Livro(livroDados[0], livroDados[1], livroDados[2], livroDados[3], Integer.parseInt(livroDados[4]));
        System.out.println("Livro \"" + novoLivro.getNome() + "\"adicionado com sucesso. \nSeu Id: " + novoLivro.getId());
        sistemaBiblioteca.adicionarLivro(novoLivro);
        CsvUtil.adicionarLivro(caminhoLivros, sistemaBiblioteca.getLivros());
    }

    public static void deletarLivro(Scanner scanner, SistemaBiblioteca sistemaBiblioteca){
        System.out.print("Digite o ID do livro a ser removido:");
        String livroId = scanner.nextLine();
        Livro Livro = sistemaBiblioteca.getLivros().stream()
                .filter(l -> l.getId().equals(livroId))
                .findFirst()
                .orElse(null);
        if (Livro != null) {
            System.out.println("Livro " + Livro.getNome() + " removido com sucesso.");
            sistemaBiblioteca.removerLivro(Livro);
            CsvUtil.removerLivro(caminhoLivros, sistemaBiblioteca.getLivros());
        } else {
            System.out.println("Livro não encontrado.");
        }
    }

    public static void criarAluno (Scanner scanner, SistemaBiblioteca sistemaBiblioteca) {
        System.out.println("Digite as informações do aluno separado por espaço(Nome, Matrícula, Senha):");
        String[] alunoDados = scanner.nextLine().split(" ");
        double debitoInicial = 0;
        Aluno novoAluno = new Aluno(alunoDados[0], alunoDados[1], alunoDados[2], debitoInicial);
        sistemaBiblioteca.adicionarAluno(novoAluno);
        CsvUtil.adicionarAluno(caminhoAlunos, sistemaBiblioteca.getAlunos());
        System.out.println("Aluno " + novoAluno.getNome() + " adicionado com sucesso.");
    }

    public static void deletarAluno(Scanner scanner, SistemaBiblioteca sistemaBiblioteca){
        System.out.println("Digite a matrícula do aluno a ser removido:");
        String matriculaAluno = scanner.nextLine();
        Aluno aluno = sistemaBiblioteca.getAlunos().stream()
                .filter(a -> a.getMatricula().equals(matriculaAluno))
                .findFirst()
                .orElse(null);
        if (aluno != null) {
            sistemaBiblioteca.removerAluno(aluno);
            CsvUtil.adicionarAluno(caminhoAlunos, sistemaBiblioteca.getAlunos());
            System.out.println("Aluno " + aluno.getNome() + " removido com sucesso.");
        } else {
            System.out.println("Aluno não encontrado.");
        }
    }

    private static void listarLivros(SistemaBiblioteca sistemaBiblioteca) {
        for (Livro Livro : sistemaBiblioteca.getLivros()) {
            System.out.println("ID: " + Livro.getId() + ", Nome: " + Livro.getNome() + ", Autor: " + Livro.getAutor() + ", Disponível: " + Livro.isDisponivelBool());
        }
    }

    private static void listarAlunos(SistemaBiblioteca sistemaBiblioteca) {
        for (Aluno Aluno : sistemaBiblioteca.getAlunos()) {
            System.out.println("Nome: " + Aluno.getNome() + ", Matricula: " + Aluno.getMatricula() + ", Débito: R$" + Aluno.getDebito());
        }
    }

    private static void listarEmprestimos(SistemaBiblioteca sistemaBiblioteca) { // CORRIGIR
        for (Emprestimo emprestimo : sistemaBiblioteca.getEmprestimos()) {
            System.out.println("MatriculaAluno: " + emprestimo.getMatriculaAluno());
            System.out.println("IdLivro: " +emprestimo.getIdLivro());
            System.out.println("Data Empréstimo: " + emprestimo.getDataEmprestimo());
            System.out.println("Data Devolução: " + emprestimo.getDataDevolucao());
            System.out.println(" ");
        }
    }
}