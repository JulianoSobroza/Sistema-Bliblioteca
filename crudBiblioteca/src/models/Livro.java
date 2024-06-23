package models;

public class Livro {
    private final String id;
    private final String nome;
    private final String autor;
    private final String lançamento;
    private int quantidade;
    private boolean disponibilidade;

    public Livro(String id, String nome, String autor, String lancamento, int quantidade) {
        this.id = id;
        this.nome = nome;
        this.autor = autor;
        this.lançamento = lancamento;
        this.quantidade = quantidade;
        this.disponibilidade = quantidade > 0;
    }

    @Override
    public String toString() {
        return "Livro{" +
                "id='" + id + '\'' +
                ", nome='" + nome + '\'' +
                ", autor='" + autor + '\'' +
                ", lançamento='" + lançamento + '\'' +
                ", quantidade=" + quantidade +
                ", disponibilidade=" + disponibilidade +
                '}';
    }

    public String getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getAutor() {
        return autor;
    }

    public String getLancamento() {
        return lançamento;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public boolean isDisponivel() {
        return disponibilidade;
    }

    public void emprestar() {
        if (disponibilidade && quantidade > 0) {
            quantidade--;
            if (quantidade == 0) {
                disponibilidade = false;
            }
        }
    }

    public void devolver() {
        quantidade++;
        disponibilidade = true;
    }
}
