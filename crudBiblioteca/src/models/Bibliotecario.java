package models;

public class Bibliotecario {
    private String nome;
    private String id;
    private String senha;

    public Bibliotecario(String nome, String senha, String id) {
        this.nome = nome;
        this.senha = senha;
        this.id = id;
    }

    @Override
    public String toString() {
        return "Bibliotecario{" +
                "nome='" + nome + '\'' +
                ", email='" + senha + '\'' +
                ", id='" + id + '\'' +
                '}';
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    // Métodos para o bibliotecário

    public void adicionarAluno(SistemaBiblioteca sistemaBiblioteca, Aluno Aluno) {
        sistemaBiblioteca.adicionarAluno(Aluno);
    }

    public void removerAluno(SistemaBiblioteca sistemaBiblioteca, Aluno Aluno) {
        sistemaBiblioteca.removerAluno(Aluno);
    }

    // Métodos para adicionar e remover livros
    public void adicionarLivro(SistemaBiblioteca sistemaBiblioteca, Livro Livro) {
        sistemaBiblioteca.adicionarLivro(Livro);
    }

    public void removerLivro(SistemaBiblioteca sistemaBiblioteca, Livro Livro) {
        sistemaBiblioteca.removerLivro(Livro);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
