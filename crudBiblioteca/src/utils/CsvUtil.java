package utils;

import models.Aluno;
import models.Emprestimo;
import models.Livro;
import models.Bibliotecario;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CsvUtil {
    // Métodos para Ler dados do CSV e criar os objetos
    public static List<Livro> lerLivros(String caminho) {
        List<Livro> Livros = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(caminho))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(";");
                Livros.add(new Livro(dados[0], dados[1], dados[2], dados[3], Integer.parseInt(dados[4])));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Livros;
    }

    public static List<Aluno> lerAlunos(String caminho) {
        List<Aluno> Alunos = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(caminho))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(";");
                Alunos.add(new Aluno(dados[0], dados[1], dados[2], Double.parseDouble(dados[3])));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Alunos;
    }

    public static List<Bibliotecario> lerBibliotecarios(String caminho) {
        List<Bibliotecario> bibliotecarios = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(caminho))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(";");
                bibliotecarios.add(new Bibliotecario(dados[0], dados[1], dados[2]));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bibliotecarios;
    }

    public static List<Emprestimo> lerEmprestimos(String caminho) {
        List<Emprestimo> emprestimos = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        try (BufferedReader br = new BufferedReader(new FileReader(caminho))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                //System.out.println(linha);

                String[] dados = linha.split(";");
                String matriculaAluno = dados[0];
                String idLivro = dados[1];
                Date dataEmprestimo = sdf.parse(dados[2]);
                Date dataDevolucao = sdf.parse(dados[3]);

                Emprestimo emprestimo = new Emprestimo(matriculaAluno, idLivro, dataEmprestimo, dataDevolucao);
                emprestimos.add(emprestimo);


            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return emprestimos;
    }

    // Métodos para adicionar itens ao arquivo CSV
    public static void adicionarEmprestimo(String caminho, List<Emprestimo> emprestimos) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(caminho, true))) {
            for (Emprestimo emprestimo : emprestimos) {
                bw.write(emprestimo.toCsvString());
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void adicionarLivro (String caminho, List<Livro> Livros) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(caminho))) {
            for ( Livro Livro : Livros) {
                bw.write(Livro.getId() + ";" + Livro.getNome() + ";" + Livro.getAutor() + ";" + Livro.getLancamento() + ";" + Livro.getQuantidade());
                bw.newLine();
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void adicionarAluno(String caminhoArquivo, List<Aluno> Alunos) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(caminhoArquivo))) {
            for (Aluno Aluno : Alunos) {
                bw.write(Aluno.getNome() + ";" + Aluno.getMatricula() + ";" + Aluno.getSenha() + ";" + Aluno.getDebito());
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void removerLivro (String caminho, List<Livro> Livros){} // IMPLEMENTAR

    public static void removerAluno(String caminhoArquivo, List<Aluno> Alunos) {} // IMPLEMENTAR
}