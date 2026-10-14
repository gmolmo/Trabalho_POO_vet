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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
        this.caminhoProdutos = Paths.get(diretorio, "atendimentos.csv");
        this.caminhoProdutos = Paths.get(diretorio, "vendas.csv");
    }
    public ArrayList LePessoas() throws IOException{
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
                        DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                        LocalDate data = LocalDate.parse(partes[6].trim(), formatador); 
                        PessoaFisica p = new PessoaFisica(partes[2].trim(), partes[4].trim(), partes[3].trim(), Integer.parseInt(partes[0].trim()), partes[5].trim(), data);
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
    public ArrayList LeAnimais() throws IOException{
        ArrayList<Animal> animais = new ArrayList<>();




        return animais;
    }    
    public ArrayList LeProdutos() throws IOException{
        ArrayList<Produto> produtos = new ArrayList<>();




        return produtos;
    }    
    public ArrayList LeServicos() throws IOException{
        ArrayList<Servico> servicos = new ArrayList<>();




        return servicos;
    }    
    public ArrayList LeAtendimentos() throws IOException{
        ArrayList<Atendimento> atendimentos = new ArrayList<>();




        return atendimentos;
    }    
    public ArrayList LeVendas() throws IOException{
        ArrayList<Venda> vendas = new ArrayList<>();




        return vendas;
    }


}
