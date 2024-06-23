package models;

public class Aluno {
    private final String nome;
    private final String matricula;
    private String senha;
    private double debito;

    public Aluno(String matricula, String nome, String senha, double debito) {
        this.matricula = matricula;
        this.nome = nome;
        this.senha = senha;
        this.debito = debito;
    }

    @Override
    public String toString() {
        return "Aluno{" +
                "nome='" + nome + '\'' +
                ", matricula='" + matricula + '\'' +
                ", senha='" + senha + '\'' +
                ", debito=" + debito +
                '}';
    }

    public String getMatricula() {
        return matricula;
    }

    public String getNome() {
        return nome;
    }

    public double getDebito() {
        return debito;
    }

    public void adicionarDebito(double valor) {
        this.debito += valor;
    }

    public void pagarDebito(double valor) {
        this.debito -= valor;
    }

    public boolean temDebito() {
        return debito > 0;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
