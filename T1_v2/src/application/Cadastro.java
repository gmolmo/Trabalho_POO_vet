package application;

import domain.Animal;
import domain.Atendimento;
import domain.Pessoa;
import domain.Produto;
import domain.Servico;
import domain.Venda;
import java.util.ArrayList;

public class Cadastro {
    ArrayList<Pessoa> pessoasCadastradas;
    ArrayList<Animal> animaisCadastrados;
    ArrayList<Produto> produtosCadastrados;
    ArrayList<Venda> vendasCadastradas;
    ArrayList<Servico> servicosCadastrados;
    ArrayList<Atendimento> atendimentosCadastrados;

    public void processaPessoas(ArrayList<Pessoa> pessoasRecebidas)
    {
        
    }
    public void processaAnimais(ArrayList<Animal> animaisRecebidos)
    {

    }
        public void processaProdutos(ArrayList<Produto> produtosRecebidos)
    {

    }
        public void processaVendas(ArrayList<Pessoa> vendasRecebidas)
    {

    }
        public void processaServicos(ArrayList<Pessoa> servicosRecebidos)
    {

    }
        public void processaAtendimentos(ArrayList<Pessoa> atendimentosRecebidos)
    {

    }

    //Getters para o GeraRelatorio
    public ArrayList<Pessoa> getPessoasCadastradas() {
        return pessoasCadastradas;
    }
    public ArrayList<Animal> getAnimaisCadastrados() {
        return animaisCadastrados;
    }
    public ArrayList<Produto> getProdutosCadastrados() {
        return produtosCadastrados;
    }
    public ArrayList<Venda> getVendasCadastradas() {
        return vendasCadastradas;
    }
    public ArrayList<Servico> getServicosCadastrados() {
        return servicosCadastrados;
    }
    public ArrayList<Atendimento> getAtendimentosCadastrados() {
        return atendimentosCadastrados;
    }
    
}
