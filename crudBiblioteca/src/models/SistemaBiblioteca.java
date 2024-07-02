package models;

import utils.CsvUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SistemaBiblioteca {
    public static String caminhoEmprestimos = "/home/azorbos/IntellijProjects/crudBiblioteca08/crudBiblioteca/data/emprestimos.csv";

    private List<Livro> Livros;
    private List<Aluno> Alunos;
    private List<Bibliotecario> bibliotecarios;
    private List<Emprestimo> emprestimos;

    public SistemaBiblioteca(List<Livro> Livros, List<Aluno> Alunos, List<Bibliotecario> bibliotecarios, List<Emprestimo> emprestimos) {
        this.Livros = Livros;
        this.Alunos = Alunos;
        this.bibliotecarios = bibliotecarios;
        this.emprestimos = emprestimos;
    }

    public static void verificarEmprestimos(List<Emprestimo> emprestimos, List<Aluno> alunos, List<Livro> livros) {
        for (Emprestimo emprestimo : emprestimos) {
            String matriculaAluno = emprestimo.getMatriculaAluno();//Obtenção das Matrículas dos Alunos e IDs dos Livros
            String idLivro = emprestimo.getLivro().getId();

            Aluno aluno = alunos.stream()//Busca pelo Aluno na Lista de Alunos
                    .filter(a -> a.getMatricula().equals(matriculaAluno))
                    .findFirst()
                    .orElse(null);

            //Busca pelo Livro na Lista de Livros:
            Livro livro = livros.stream()
                    .filter(l -> l.getId().equals(idLivro))
                    .findFirst()
                    .orElse(null);

            if (aluno != null && livro != null) {
                aluno.emprestarLivro(livro);
            }
        }
    }

    public List<Livro> getLivros() {
        return Livros;
    }

    public List<Aluno> getAlunos() {
        return Alunos;
    }

    public List<Bibliotecario> getBibliotecarios() {
        return bibliotecarios;
    }

    public List<Emprestimo> getEmprestimos() {
        return emprestimos;
    }
    // Métodos para os bibliotecários
    public void adicionarLivro(Livro Livro) {
        Livros.add(Livro);
    }

    public void removerLivro(Livro Livro) {
        Livros.remove(Livro);
    }

    public void adicionarAluno(Aluno Aluno) {
        Alunos.add(Aluno);
    }

    public void removerAluno(Aluno Aluno) {
        Alunos.remove(Aluno);
    }

    // Métodos para os alunos
    public void realizarEmprestimoLivro(String matriculaAluno, String idLivro) {
        // procurar um aluno específico na lista Alunos com base em sua matrícula
        Aluno aluno = Alunos.stream()
                .filter(a -> a.getMatricula().equals(matriculaAluno))
                .findFirst()
                .orElse(null);

        // Verifica se o livro está disponível
        Livro Livro = Livros.stream()
                .filter(l -> l.getId().equals(idLivro) && l.isDisponivelBool())
                .findFirst()
                .orElse(null);

        if (Livro == null) {
            System.out.println("Livro não disponível.");
            return;
        }

        Livro.emprestar();
        aluno.emprestarLivro(Livro);

        Date dataEmprestimo = new Date();
        Date dataDevolucao = new Date(dataEmprestimo.getTime() + (7 * 24 * 60 * 60 * 1000)); // 7 dias depois

        // Criando um objeto emprestimo
        Emprestimo emprestimo = new Emprestimo(aluno.getMatricula(), Livro.getId(), dataEmprestimo, dataDevolucao);
        emprestimos.add(emprestimo);

        // Gravando no CSV apenas o novo emprestimo
        List<Emprestimo> novoEmprestimo = new ArrayList<>();
        novoEmprestimo.add(emprestimo);
        CsvUtil.adicionarEmprestimo(caminhoEmprestimos, novoEmprestimo);

        // Mensagem ao usuário
        System.out.println("Você pegou o livro " + Livro.getNome());
    }

    public void realizarDevolucaoLivro(String matriculaAluno, String idLivro) {
        Aluno aluno = Alunos.stream()
                .filter(a -> matriculaAluno.equals(a.getMatricula()))
                .findFirst()
                .orElse(null);

        if (aluno == null) {
            System.out.println("Aluno não encontrado.");
            return;
        }

        Livro livro = aluno.getLivrosEmprestados().stream()
                .filter(l -> l.getId().equals(idLivro))
                .findFirst()
                .orElse(null);

        if (livro == null) {
            System.out.println("Livro não encontrado.");
            return;
        }

        livro.devolver();
        aluno.devolverLivro(livro);

        Emprestimo emprestimo = emprestimos.stream()
                .filter(e -> e.getAluno().equals(aluno.getMatricula()) && e.getLivro().getId().equals(idLivro))
                .findFirst()
                .orElse(null);

        if (emprestimo != null) {
            emprestimos.remove(emprestimo);

            // Atualizando o CSV com a nova lista de empréstimos
            CsvUtil.adicionarEmprestimo(caminhoEmprestimos, emprestimos);

            System.out.println("Devolução realizada com sucesso.");
        } else {
            System.out.println("Empréstimo não encontrado. Está caindo aqui");
        }

        // Verifica se o livro foi devolvido corretamente
        Livro livroParaDevolucao = Livros.stream()
                .filter(l -> l.getId().equals(idLivro))
                .findFirst()
                .orElse(null);

        if (livroParaDevolucao != null) {
            System.out.println("Você devolveu o livro " + livroParaDevolucao.getNome());
        } else {
            System.out.println("Livro não encontrado na biblioteca.");
        }
    }
}