package models;

public class Livro {
    private final String id;
    private final String nome;
    private final String autor;
    private final String lancamento;
    private int quantidade;
    private boolean disponibilidade;

    public Livro(String nome) {
        this.id = "";
        this.nome = nome;
        this.autor = "";
        this.lancamento = "";
        this.quantidade = 0;
        this.disponibilidade = false;
    }

    public Livro(String id, String nome, String autor, String lancamento, int quantidade) {
        this.id = id;
        this.nome = nome;
        this.autor = autor;
        this.lancamento = lancamento;
        this.quantidade = quantidade;
        this.disponibilidade = quantidade > 0;
    }

    @Override
    public String toString() {
        return "Livro{" +
                "id='" + id + '\'' +
                ", nome='" + nome + '\'' +
                ", autor='" + autor + '\'' +
                ", lançamento='" + lancamento + '\'' +
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
        return lancamento;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
        this.disponibilidade = quantidade > 0; //
    }

    public String isDisponivel(boolean disponibilidade) {
        String temLivro;
        String sim = "Sim";
        String nao = "Não";
        if(disponibilidade){
            temLivro = sim;
        } else {
            temLivro = nao;
        }
        return temLivro;
    }

    public boolean isDisponivelBool() {
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