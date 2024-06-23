package models;

import java.util.List;

public class Biblioteca {
    private List<Livro> livros;
    private List<Aluno> alunos;
    private List<Bibliotecario> bibliotecarios;

    public Biblioteca(List<Livro> livros, List<Aluno> alunos, List<Bibliotecario> bibliotecarios) {
        this.livros = livros;
        this.alunos = alunos;
        this.bibliotecarios = bibliotecarios;
    }

    public List<Livro> getLivros() {
        return livros;
    }

    public List<Aluno> getAlunos() {
        return alunos;
    }

    public List<Bibliotecario> getBibliotecarios() {
        return bibliotecarios;
    }

    public void adicionarLivro(Livro livro) {
        livros.add(livro);
    }

    public void removerLivro(Livro livro) {
        livros.remove(livro);
    }

    public void adicionarAluno(Aluno aluno) {
        alunos.add(aluno);
    }

    public void removerAluno(Aluno aluno) {
        alunos.remove(aluno);
    }
}
