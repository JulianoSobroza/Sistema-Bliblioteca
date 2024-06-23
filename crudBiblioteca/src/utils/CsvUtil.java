package utils;

import models.Aluno;
import models.Livro;
import models.Bibliotecario;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CsvUtil {
    public static List<Livro> lerLivros(String caminhoArquivo) {
        List<Livro> livros = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(caminhoArquivo))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(";");
                livros.add(new Livro(dados[0], dados[1], dados[2], dados[3], Integer.parseInt(dados[4])));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return livros;
    }

    public static List<Aluno> lerAlunos(String caminhoArquivo) {
        List<Aluno> alunos = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(caminhoArquivo))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(";");
                alunos.add(new Aluno(dados[0], dados[1], dados[2], Double.parseDouble(dados[3])));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return alunos;
    }

    public static List<Bibliotecario> lerBibliotecarios(String caminhoArquivo) {
        List<Bibliotecario> bibliotecarios = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(caminhoArquivo))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(";");
                bibliotecarios.add(new Bibliotecario(dados[0], dados[1], dados [3]));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bibliotecarios;
    }
}
