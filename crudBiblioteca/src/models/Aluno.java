package models;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Aluno {
    private final String nome;
    private final String matricula;
    private String senha;
    private double debito;
    private List<Livro> livrosEmprestados;

    public Aluno(String nome, String matricula, String senha, double debito) {
        this.nome = nome;
        this.matricula = matricula;
        this.senha = senha;
        this.debito = debito;
        this.livrosEmprestados = new ArrayList<>();
    }

    public Aluno(String nome) {
        this.nome = getNome();
        this.matricula = getMatricula();
        this.livrosEmprestados = new ArrayList<>();
    }

    @Override
    public String toString() {
        return "Aluno{" +
                "nome='" + nome + '\'' +
                ", matricula='" + matricula + '\'' +
                ", senha='" + senha + '\'' +
                ", debito=" + debito +
                ", livrosEmprestados=" + livrosEmprestados +
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

    public void emprestarLivro(Livro Livro) {
        livrosEmprestados.add(Livro);
    }

    public void devolverLivro(Livro Livro) {
        livrosEmprestados.remove(Livro);
    }

    public List<Livro> getLivros() {
        return livrosEmprestados;
    }

    public List<Livro> getLivrosEmprestados() {
        return livrosEmprestados;
    }

    public void setLivrosEmprestados(List<Livro> livrosEmprestados) {
        this.livrosEmprestados = livrosEmprestados;
    }

    // tentativa de atualizar os livros emprestados ao inicar a aplicação
    public void tamanho (){
        System.out.println("Tamanho: " + livrosEmprestados.size());
    }

    public void atualizarLivrosEmprestados(String caminhoCSV) {

        //List<Livro> livros = new ArrayList<>();
        List<Emprestimo> emprestimos = new ArrayList<>();
        List<Livro> livros = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        try (BufferedReader br = new BufferedReader(new FileReader(caminhoCSV))) {
            String linha;
            while ((linha = br.readLine()) != null) {

                String[] dados = linha.split(";");
                String matriculaAluno = dados[0];
                String idLivro = dados[1];
                Date dataEmprestimo = sdf.parse(dados[2]);
                Date dataDevolucao = sdf.parse(dados[3]);

                if (dados[0].equals(this.matricula)) {
                    Livro livro = new Livro(idLivro, "Nome livro", "Autor Livro ", " Lançamento", 1);
                    //tamanho();
                    livros.add(livro);
                    setLivrosEmprestados(livros);
                    //Emprestimo emprestimo = new Emprestimo(matriculaAluno, idLivro, dataEmprestimo, dataDevolucao);
                    //emprestimos.add(emprestimo);
                    //setLivrosEmprestados(emprestimo);
                }
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    public void mostrarLivrosEmprestados(){
        for (Livro livro : livrosEmprestados){
            System.out.println(livro);
        }
    }
}

