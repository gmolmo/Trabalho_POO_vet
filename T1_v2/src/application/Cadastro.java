package application;

import domain.Animal;
import domain.Atendimento;
import domain.Pessoa;
import domain.PessoaFisica;
import domain.Produto;
import domain.Servico;
import domain.Venda;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
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
        for(int i = 0; i<pessoasRecebidas.size();i++)
        {
            Pessoa p = pessoasRecebidas.get(i);
            if(pessoaEstaCadastrada(p.getId()))
                throw new InconsistenciaException("ID repetido "+p.getId()+" na classe Pessoa");
            
            if(p instanceof PessoaFisica)
            {
                PessoaFisica pf = (PessoaFisica)p;
                try {
                    LocalDate data = LocalDate.parse(pf.getDataString(),formatador);
                    pf.setData(data);
                    this.pessoasCadastradas.add(pf);
                } catch (DateTimeParseException e) {
                    throw new InconsistenciaException("A data de nascimento "+pf.getDataString()+" da Pessoa "+pf.getId()+" é inválida.");
                }
            }
            else
            {   
                this.pessoasCadastradas.add(p);
            }
        }
        //se existe, lança uma inconsistencia exception com o id repetido
        //Se não, parseia a data para setar
        //se gerar inconsistencia, cathcar ela e lançar a inconsistencia exception com a dataString invalidada
    }
    public void processaAnimais(ArrayList<Animal> animaisRecebidos) throws InconsistenciaException
    {
        //confere se o animal já existe no array
        for(int i = 0; i < animaisRecebidos.size(); i++)
        {
            Animal a = animaisRecebidos.get(i);
            if(animalEstaCadastrado(a.getId()))//se ja existe->inconsistencia exception
                throw new InconsistenciaException("ID repetido "+a.getId()+" na classe Animal.");

            if( !pessoaEstaCadastrada(a.getIdTutor()))//se nao, tenta ligar o tutor  
            {//se o tutor nao existir->inconsistencia exception
                throw new InconsistenciaException("O Tutor de ID "+a.getIdTutor()+" do Animal de ID "+a.getId()+" não cadastrado.");
            }
            else
            {
                //pega cadastro, verifica se é pessoaFisica, se nao
                //throw new InconsistenciaException("O Tutor de ID "+a.getIdTutor()+" do Animal de ID "+a.getId()+" não cadastrado.");
            }//se existir liga o tutor

            try {//tenta gerar a data
                LocalDate data = LocalDate.parse(a.getDataString(),formatador);
                a.setDataNascimento(data);
            } catch (DateTimeParseException e) {//se nao->inconsistencia exception
                    throw new InconsistenciaException("A data de nascimento "+a.getDataString()+" do Animal "+a.getId()+" é inválida.");
                }
        }
        


        
        
        
        //se der, verifica se é de familia aceita
        //se nao->inconsistencia exception
        //se passou por tudo isso, adiciona
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
    
    private boolean pessoaEstaCadastrada(BigInteger idQuestionado)
    {
        for(int i = 0; i < this.pessoasCadastradas.size(); i++)
        {
            if(pessoasCadastradas.get(i).getId().equals(idQuestionado))
                return true;
        }
        return false;
    }   
    private boolean animalEstaCadastrado(BigInteger idQuestionado)
    {
        for(int i = 0; i < this.animaisCadastrados.size(); i++)
        {
            if(animaisCadastrados.get(i).getId().equals(idQuestionado))
                return true;
        }
        return false;
    }
    private boolean atendimentoEstaCadastrado(BigInteger idQuestionado)
    {
        for(int i = 0; i < this.atendimentosCadastrados.size(); i++)
        {
            if(atendimentosCadastrados.get(i).getId().equals(idQuestionado))
                return true;
        }
        return false;
    }
        private boolean vendaEstaCadastrado(BigInteger idQuestionado)
    {
        for(int i = 0; i < this.vendasCadastradas.size(); i++)
        {
            if(vendasCadastradas.get(i).getId().equals(idQuestionado))
                return true;
        }
        return false;
    }

    private boolean servicoEstaCadastrado(Servico servicoQuestionado)
    {
        for(int i = 0; i< this.servicosCadastrados.size(); i++)
        {
            Servico serv = servicosCadastrados.get(i);
            if(serv.getId().equals(servicoQuestionado.getId()))
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
            if(prod.getId().equals(produtoQuestionado.getId()))
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
