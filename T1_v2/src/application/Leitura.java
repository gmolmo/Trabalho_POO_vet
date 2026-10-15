package application;

import domain.Animal;
import domain.Atendimento;
import domain.Pessoa;
import domain.PessoaFisica;
import domain.PessoaJuridica;
import domain.Produto;
import domain.Servico;
import domain.Venda;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Leitura {
    Path caminhoPessoas;
    Path caminhoAnimais;
    Path caminhoServicos;
    Path caminhoProdutos;
    Path caminhoAtendimentos;
    Path caminhoVendas;
    String diretorio;

    public Leitura(String diretorio)
    {
        this.diretorio = diretorio;
        this.caminhoPessoas = Paths.get(diretorio,"pessoas.csv");
        this.caminhoAnimais = Paths.get(diretorio,"animais.csv");
        this.caminhoServicos = Paths.get(diretorio,"servicos.csv");
        this.caminhoProdutos = Paths.get(diretorio, "produtos.csv");
        this.caminhoAtendimentos = Paths.get(diretorio, "atendimentos.csv");
        this.caminhoVendas = Paths.get(diretorio, "vendas.csv");
    }
    public ArrayList<Pessoa> LePessoas() throws IOException{
        ArrayList<Pessoa> pessoas = new ArrayList<>();

        try(BufferedReader leitor = Files.newBufferedReader(caminhoPessoas, StandardCharsets.UTF_8)){
            String linha = leitor.readLine();//cabeçalho do arquivo

            while((linha = leitor.readLine())!=null)// == hasnextline
            {
                if(linha.isBlank())
                    continue;
                String[] partes = linha.split(";");

                switch (partes[1]) {
                    case "F":
                        PessoaFisica p = new PessoaFisica(partes[2].trim(), partes[4].trim(), partes[3].trim(), Integer.parseInt(partes[0].trim()), partes[5].trim(), partes[6].trim());
                        pessoas.add(p);
                        break;
                    case "J":
                        PessoaJuridica pj = new PessoaJuridica(partes[2].trim(), partes[4].trim(), partes[3].trim(), Integer.parseInt(partes[0].trim()), partes[5].trim());
                        pessoas.add(pj);
                        break;
                    default:
                        throw new AssertionError();
                }
            }
        }

        return pessoas;
    }    
    public ArrayList<Animal> LeAnimais() throws IOException{
        ArrayList<Animal> animais = new ArrayList<>();

        try(BufferedReader leitor = Files.newBufferedReader(caminhoAnimais, StandardCharsets.UTF_8)){
            String linha = leitor.readLine();//cabeçalho do arquivo

            while((linha = leitor.readLine())!=null)// == hasnextline
            {
                if(linha.isBlank())
                    continue;
                String[] partes = linha.split(";");

               Animal a = new Animal(Integer.parseInt(partes[1].trim()), partes[2].trim(),partes[4].trim(),partes[3].trim(),Integer.parseInt(partes[0]));
                animais.add(a);
            }
        }

        return animais;
    }    
    public ArrayList<Produto> LeProdutos() throws IOException{
        ArrayList<Produto> produtos = new ArrayList<>();

        try(BufferedReader leitor = Files.newBufferedReader(caminhoProdutos, StandardCharsets.UTF_8)){
            String linha = leitor.readLine();//cabeçalho do arquivo

            while((linha = leitor.readLine())!=null)// == hasnextline
            {
                if(linha.isBlank())
                    continue;
                String[] partes = linha.split(";");

                String[] animaisDestinados = partes[3].trim().split(",");
                Produto a = new Produto(Integer.parseInt(partes[0].trim()),partes[1].trim(), Double.parseDouble(partes[2].trim()),Double.parseDouble(partes[4].trim()),partes[5].trim(),partes[6].trim(),animaisDestinados);
                produtos.add(a);
            }
        }

        return produtos;
    }    
    public ArrayList<Servico> LeServicos() throws IOException{
        ArrayList<Servico> servicos = new ArrayList<>();

        try(BufferedReader leitor = Files.newBufferedReader(caminhoServicos, StandardCharsets.UTF_8)){
            String linha = leitor.readLine();//cabeçalho do arquivo

            while((linha = leitor.readLine())!=null)// == hasnextline
            {
                if(linha.isBlank())
                    continue;
                String[] partes = linha.split(";");

                String[] animaisDestinados = partes[3].trim().split(",");

                Servico a = new Servico(partes[2].trim(),Double.parseDouble(partes[4].trim()),animaisDestinados,Integer.parseInt(partes[1].trim()),Integer.parseInt(partes[0].trim()),partes[5].trim(),partes[6].trim());
                servicos.add(a);
            }
        }


        return servicos;
    }    
    public ArrayList<Atendimento> LeAtendimentos() throws IOException{
        ArrayList<Atendimento> atendimentos = new ArrayList<>();

       try(BufferedReader leitor = Files.newBufferedReader(caminhoAtendimentos, StandardCharsets.UTF_8)){
            String linha = leitor.readLine();//cabeçalho do arquivo

            while((linha = leitor.readLine())!=null)// == hasnextline
            {
                if(linha.isBlank())
                    continue;
                String[] partes = linha.split(";");

                Atendimento a = new Atendimento(Integer.parseInt(partes[2].trim()),partes[3].trim(),Integer.parseInt(partes[0].trim()),Integer.parseInt(partes[1].trim()));
                atendimentos.add(a);
            }
        }

        return atendimentos;
    }    
    public ArrayList<Venda> LeVendas() throws IOException{
        ArrayList<Venda> vendas = new ArrayList<>();

       try(BufferedReader leitor = Files.newBufferedReader(caminhoVendas, StandardCharsets.UTF_8)){
            String linha = leitor.readLine();//cabeçalho do arquivo

            while((linha = leitor.readLine())!=null)// == hasnextline
            {
                if(linha.isBlank())
                    continue;
                String[] partes = linha.split(";");

                Venda a = new Venda(Integer.parseInt(partes[1].trim()),Integer.parseInt(partes[2].trim()),Integer.parseInt(partes[3].trim()),Integer.parseInt(partes[0].trim()),partes[4].trim());
                vendas.add(a);
            }
        }


        return vendas;
    }


}
