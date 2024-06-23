package models;

public class Sistema {
    private Biblioteca biblioteca;
    public Sistema(Biblioteca biblioteca) {
        this.biblioteca = biblioteca;
    }

    public void realizarEmprestimo(String matriculaAluno, String livroId) {
        // Encontrar o aluno pelo ID
        Aluno aluno = biblioteca.getAlunos().stream()
                .filter(a -> a.getMatricula().equals(matriculaAluno))
                .findFirst()
                .orElse(null);
        if (aluno == null) {
            System.out.println("Aluno não encontrado.");
            return;
        }

        // Verificar se o aluno tem débitos pendentes
        if (aluno.temDebito()) {
            System.out.println("Aluno possui débitos pendentes.");
            return;
        }

        // Encontrar o livro pelo ID
        Livro livro = biblioteca.getLivros().stream()
                .filter(l -> l.getId().equals(livroId))
                .findFirst()
                .orElse(null);
        if (livro == null) {
            System.out.println("Livro não encontrado.");
            return;
        }

        // Verificar se o livro está disponível
        if (!livro.isDisponivel()) {
            System.out.println("Livro não está disponível.");
            return;
        }

        // Realizar o empréstimo
        livro.emprestar();
        System.out.println("Empréstimo realizado com sucesso.");
    }


    public void realizarDevolucao(String matriculaAluno, String livroId) {
        // Encontrar o aluno pela matrícula
        Aluno aluno = biblioteca.getAlunos().stream()
                .filter(a -> a.getMatricula().equals(matriculaAluno))
                .findFirst()
                .orElse(null);
        if (aluno == null) {
            System.out.println("Aluno não encontrado.");
            return;
        }

        // Encontrar o livro pelo ID
        Livro livro = biblioteca.getLivros().stream()
                .filter(l -> l.getId().equals(livroId))
                .findFirst()
                .orElse(null);
        if (livro == null) {
            System.out.println("Livro não encontrado.");
            return;
        }

        // Realizar a devolução
        livro.devolver();
        System.out.println("Devolução realizada com sucesso.");
    }


    public void pagarDebito(String matriculaAluno, double valor) {
        // Encontrar o aluno pela matrícula
        Aluno aluno = biblioteca.getAlunos().stream()
                .filter(a -> a.getMatricula().equals(matriculaAluno))
                .findFirst()
                .orElse(null);
        if (aluno == null) {
            System.out.println("Aluno não encontrado.");
            return;
        }

        // Realizar o pagamento do débito
        aluno.pagarDebito(valor);
        System.out.println("Pagamento realizado com sucesso.");
    }

}