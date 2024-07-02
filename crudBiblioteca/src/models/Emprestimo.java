package models;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Emprestimo {
    private String matriculaAluno;
    private String idLivro;
    private Date dataEmprestimo;
    private Date dataDevolucao;

    public Emprestimo(String matriculaAluno, String idLivro, Date dataEmprestimo, Date dataDevolucao) {
        this.matriculaAluno = matriculaAluno;
        this.idLivro = idLivro;
        this.dataEmprestimo = dataEmprestimo;
        this.dataDevolucao = dataDevolucao;
    }

    @Override
    public String toString() {
        return "Emprestimo{" +
                "matriculaAluno='" + matriculaAluno + '\'' +
                ", idLivro='" + idLivro + '\'' +
                ", dataEmprestimo=" + dataEmprestimo +
                ", dataDevolucao=" + dataDevolucao +
                '}';
    }

    public String toCsvString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return matriculaAluno + ";" + idLivro + ";" + sdf.format(dataEmprestimo) + ";" + sdf.format(dataDevolucao);
    }

    // Getters e setters
    public String getMatriculaAluno() {
        return matriculaAluno;
    }

    public String getIdLivro() {
        return idLivro;
    }

    public Date getDataEmprestimo() {
        return dataEmprestimo;
    }

    public Date getDataDevolucao() {
        return dataDevolucao;
    }

    public Object getAluno() {
        return this.matriculaAluno;
    }

    public Livro getLivro() {
        return new Livro(this.idLivro);
    }
}
