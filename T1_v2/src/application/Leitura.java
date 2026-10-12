package application;

import java.nio.file.Path;
import java.nio.file.Paths;

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

}
