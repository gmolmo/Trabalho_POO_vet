package application;

import domain.Animal;
import domain.Atendimento;
import domain.Pessoa;
import domain.Produto;
import domain.Servico;
import domain.Venda;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Cadastro {
    ArrayList<Pessoa> pessoasCadastradas;
    ArrayList<Animal> animaisCadastrados;
    ArrayList<Produto> produtosCadastrados;
    ArrayList<Venda> vendasCadastradas;
    ArrayList<Servico> servicosCadastrados;
    ArrayList<Atendimento> atendimentosCadastrados;

    String[] animaisSuportados = {"Aves", "Cães", "Felinos", "Roedores"};

    DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");


    //metodos que ligam classes dependentes, checam inconsistencias e resultam em listas cadastradas validas
    public void processaPessoas(ArrayList<Pessoa> pessoasRecebidas) throws InconsistenciaException
    {

    }
    public void processaAnimais(ArrayList<Animal> animaisRecebidos) throws InconsistenciaException
    {

    }
        public void processaProdutos(ArrayList<Produto> produtosRecebidos) throws InconsistenciaException
    {

    }
        public void processaVendas(ArrayList<Venda> vendasRecebidas) throws InconsistenciaException
    {

    }
        public void processaServicos(ArrayList<Servico> servicosRecebidos) throws InconsistenciaException
    { 

    }
        public void processaAtendimentos(ArrayList<Atendimento> atendimentosRecebidos) throws InconsistenciaException
    {

    }

    //FAZER OS MÉTODOS ISINRARRAY PRA CADA TIPO QUE NÃO PODE REPETIR
    
    private boolean pessoaEstaCadastrada(int idQuestionado)
    {
        for(int i = 0; i < this.pessoasCadastradas.size(); i++)
        {
            if(pessoasCadastradas.get(i).getId() == idQuestionado)
                return true;
        }
        return false;
    }   
    private boolean animalEstaCadastrado(int idQuestionado)
    {
        for(int i = 0; i < this.animaisCadastrados.size(); i++)
        {
            if(animaisCadastrados.get(i).getId() == idQuestionado)
                return true;
        }
        return false;
    }
    private boolean atendimentoEstaCadastrado(int idQuestionado)
    {
        for(int i = 0; i < this.atendimentosCadastrados.size(); i++)
        {
            if(atendimentosCadastrados.get(i).getId() == idQuestionado)
                return true;
        }
        return false;
    }
        private boolean vendaEstaCadastrado(int idQuestionado)
    {
        for(int i = 0; i < this.vendasCadastradas.size(); i++)
        {
            if(vendasCadastradas.get(i).getId() == idQuestionado)
                return true;
        }
        return false;
    }

    private boolean servicoEstaCadastrado(Servico servicoQuestionado)
    {
        for(int i = 0; i< this.servicosCadastrados.size(); i++)
        {
            Servico serv = servicosCadastrados.get(i);
            if(serv.getId() == servicoQuestionado.getId())
            {
                LocalDate inicio1 = serv.getDataInicio();
                LocalDate fim1 = serv.getDatafim();
                LocalDate inicio2 = servicoQuestionado.getDataInicio();
                LocalDate fim2 = servicoQuestionado.getDatafim();

                if(!inicio1.isAfter(fim2) && !fim1.isBefore(inicio2))
                    return true;
            }
        }
        return false;
    }
    private boolean produtoEstaCadastrado(Produto produtoQuestionado)
    {
        for(int i = 0; i< this.produtosCadastrados.size(); i++)
        {
            Produto prod = produtosCadastrados.get(i);
            if(prod.getId() == produtoQuestionado.getId())
            {
                LocalDate inicio1 = prod.getInicioVigencia();
                LocalDate fim1 = prod.getFimVigencia();
                LocalDate inicio2 = produtoQuestionado.getInicioVigencia();
                LocalDate fim2 = produtoQuestionado.getFimVigencia();

                if(!inicio1.isAfter(fim2) && !fim1.isBefore(inicio2))
                    return true;
            }
        }
        return false;
    }

    private boolean animalNaoSuportado(String familiaQuestionada)
    {
        for(int i = 0; i < 4; i++)
        {
            if(familiaQuestionada.equalsIgnoreCase(this.animaisSuportados[i]))
                return false;
        }
        return true;
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
