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

/**
 * A classe {@code Cadastro} gerencia o armazenamento em memória e a validação 
 * de consistência de todas as entidades do sistema (pessoas, animais, produtos, serviços, vendas e atendimentos).
 * <p>
 * Atua como um gerenciador de regras de entrada para validação dos objetos que foram criados
 */
public class Cadastro {
    private ArrayList<Pessoa> pessoasCadastradas;
    private ArrayList<Animal> animaisCadastrados;
    private ArrayList<Produto> produtosCadastrados;
    private ArrayList<Venda> vendasCadastradas;
    private ArrayList<Servico> servicosCadastrados;
    private ArrayList<Atendimento> atendimentosCadastrados;

    private final String[] animaisSuportados = {"Aves", "Cães", "Felinos", "Roedores"};

    private final DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    /**
     * Construtor padrão da classe. Inicializa as listas dinâmicas que receberão as instâncias processadas.
     */
    public Cadastro()
    {
        this.pessoasCadastradas = new ArrayList<>();
        this.animaisCadastrados = new ArrayList<>();
        this.produtosCadastrados = new ArrayList<>();
        this.vendasCadastradas = new ArrayList<>();
        this.servicosCadastrados = new ArrayList<>();
        this.atendimentosCadastrados = new ArrayList<>();
    }

    /**
     * Processa a lista de pessoas recebida do arquivo CSV, validando a existência 
     * do ID único e o formato da data de nascimento.
     * 
     * @param pessoasRecebidas Lista de objetos {@link Pessoa} brutos lidos do arquivo.
     * @throws InconsistenciaException Se houver ID repetido ou data inválida
     */
    public void processaPessoas(ArrayList<Pessoa> pessoasRecebidas) throws InconsistenciaException
    {
        for(int i = 0; i<pessoasRecebidas.size();i++)
        {
            Pessoa p = pessoasRecebidas.get(i);
            if(pessoaEstaCadastrada(p.getId()))
                throw new InconsistenciaException("ID repetido "+p.getId()+" na classe Pessoa.");
            
            if(p instanceof PessoaFisica pf)
            {
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

    /**
     * Processa a lista de animais recebida do arquivo CSV, validando a existência 
     * do ID único, se possui tutor válido, o formato da data de nascimento e a família ser suportada.
     * 
     * @param animaisRecebidos Lista de objetos {@link Animal} brutos lidos do arquivo.
     * @throws InconsistenciaException Se houver ID repetido ou data inválida, família não suportada ou tutor inexistente
     */
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
                throw new InconsistenciaException("O Tutor de ID "+a.getIdTutor()+" do Animal de ID "+a.getId()+" não está cadastrado.");
            }
            else
            {
                PessoaFisica tutor = getPessoaFisicaCadastrada(a.getIdTutor());
                if(tutor == null)
                {
                    throw new InconsistenciaException("O Tutor de ID "+a.getIdTutor()+" do Animal de ID "+a.getId()+" não está cadastrado.");
                }
                else
                    a.setTutor(tutor);
                
            }//se existir liga o tutor

            try {//tenta gerar a data
                LocalDate data = LocalDate.parse(a.getDataString(),formatador);
                a.setDataNascimento(data);
            } catch (DateTimeParseException e) {//se nao->inconsistencia exception
                    throw new InconsistenciaException("A data de nascimento "+a.getDataString()+" do Animal "+a.getId()+" é inválida.");
                }

            if(animalNaoSuportado(a.getFamilia()))        //se der, verifica se é de familia aceita
            {//se nao->inconsistencia exception
                throw new InconsistenciaException("A Família "+a.getFamilia()+" do Animal de ID "+a.getId()+" não é suportada.");
            }

            animaisCadastrados.add(a);
            //se passou por tudo isso, adiciona
        }
                
    }

    /**
     * Processa a lista de produtos recebidos do arquivo CSV, validando a existência 
     * do ID único com data de vigência e o formato da data de vigência.
     * 
     * @param produtosRecebidos Lista de objetos {@link Produto} brutos lidos do arquivo.
     * @throws InconsistenciaException Se houver ID repetido ou data inválida
     */
    public void processaProdutos(ArrayList<Produto> produtosRecebidos) throws InconsistenciaException
    {
        for(int i = 0; i < produtosRecebidos.size(); i++)
        {
            Produto p = produtosRecebidos.get(i);
            //alocar as datas e verificar CADA uma
            try {
                LocalDate data = LocalDate.parse(p.getDatainiString(),formatador);
                p.setInicioVigencia(data);
            } catch (DateTimeParseException e) {
                throw new InconsistenciaException("A data "+p.getDatainiString()+" de início de vigência do Produto "+p.getId()+" é inválida.");
            }
            try {
                LocalDate data = LocalDate.parse(p.getDatafimString(),formatador);
                p.setFimVigencia(data);
            } catch (DateTimeParseException e) {
                throw new InconsistenciaException("A data "+p.getDatafimString()+" de fim de vigência do Produto "+p.getId()+" é inválida.");
            }//se nao, inconsistenciaException
            
            if(produtoEstaCadastrado(p))//se sim, verificar se já tem um igual cadastrado com sobreposicao de datas
                throw new InconsistenciaException("Sobreposição de datas no Produto de ID "+p.getId()+".");
            //se sim, inconsistenciaException
            
            produtosCadastrados.add(p);
            //se nao, adiciona
        }
        
    }

    /**
     * Processa a lista de vendas recebidos do arquivo CSV, validando a existência 
     * do ID único e o formato da data, além da existência do cliente e produto válido.
     * 
     * @param vendasRecebidas Lista de objetos {@link Venda} brutos lidos do arquivo.
     * @throws InconsistenciaException Se houver ID repetido, data inválida, produto inválido ou cliente inválido.
     */
    public void processaVendas(ArrayList<Venda> vendasRecebidas) throws InconsistenciaException
    {
        for(int i = 0; i < vendasRecebidas.size(); i++)
        {
            Venda v = vendasRecebidas.get(i);

            if(vendaEstaCadastrado(v.getId()))
            {
                throw new InconsistenciaException("ID repetido "+v.getId()+" na classe Venda.");
            }

            if(!pessoaEstaCadastrada(v.getIdCliente()))
            {
                throw new InconsistenciaException("Cliente de ID "+v.getIdCliente()+" da Venda de ID "+v.getId()+" não cadastrado.");
            }
            else
            {
                v.setCliente(getPessoaFisicaCadastrada(v.getIdCliente()));
            }
            try {
                LocalDate data = LocalDate.parse(v.getDataString(),formatador);
                v.setDataVenda(data);
            } catch (DateTimeParseException e) {
                throw new InconsistenciaException("A data "+v.getDataString()+" da Venda "+v.getId()+" é inválida.");
            }
            if(!produtoValidoParaData(v.getIdProduto(), v.getDataVenda()))
            {
                throw new InconsistenciaException("Não há registro de Produto de ID "+v.getIdProduto()+" na Data "+v.getDataString()+" para a Venda de ID "+v.getId()+".");
            }
            else
            {
                v.setProdutoVendido(getProdutoValidoParaVenda(v.getIdProduto(), v.getDataVenda()));
            }

            vendasCadastradas.add(v);

        }
    } 
    /**
     * Processa a lista de serviços recebidos do arquivo CSV, validando a existência 
     * do ID único com data de vigência e o formato da data de vigência.
     * 
     * @param servicosRecebidos Lista de objetos {@link Servico} brutos lidos do arquivo.
     * @throws InconsistenciaException Se houver ID repetido ou data inválida
     */
    public void processaServicos(ArrayList<Servico> servicosRecebidos) throws InconsistenciaException
    { 
        for(int i = 0; i < servicosRecebidos.size(); i++)
        {
            Servico s = servicosRecebidos.get(i);
            if(pessoaEstaCadastrada(s.getIdPrestadorServico()))
            {
                s.setPrestadorServico(getPessoaCadastrada(s.getIdPrestadorServico()));
            }        //verifica se o prestador de serviço esta cadastrado
            
            try {//verifica as datas de inicio e fim de vigencia
                LocalDate data = LocalDate.parse(s.getDatainiString(),formatador);
                s.setDataInicio(data);
            } catch (DateTimeParseException e) {
                throw new InconsistenciaException("A data "+s.getDatainiString()+" de início de vigência do Serviço "+s.getId()+" é inválida.");
            }
            try {
                LocalDate data = LocalDate.parse(s.getDatafimString(),formatador);
                s.setDatafim(data);
            } catch (DateTimeParseException e) {
                throw new InconsistenciaException("A data "+s.getDatafimString()+" de fim de vigência do Serviço "+s.getId()+" é inválida.");
            }//se nao, inconsistenciaException

            if(servicoEstaCadastrado(s))        //verifica se existe um serviço de mesmo id e sobreposicao de datas
                throw new InconsistenciaException("Sobreposição de datas no Serviço de ID "+s.getId()+".");

            servicosCadastrados.add(s);
        }
    }

    /**
     * Processa a lista de atendimentos recebidos do arquivo CSV, validando a existência 
     * do ID único e o formato da data, além da existência do cliente, animal e serviço válido.
     * 
     * @param vendasRecebidas Lista de objetos {@link Venda} brutos lidos do arquivo.
     * @throws InconsistenciaException Se houver ID repetido, data inválida, produto inválido ou cliente inválido.
     */
    public void processaAtendimentos(ArrayList<Atendimento> atendimentosRecebidos) throws InconsistenciaException
    {
        for(int i = 0; i < atendimentosRecebidos.size(); i++)
        {
            Atendimento a = atendimentosRecebidos.get(i);

            if(atendimentoEstaCadastrado(a.getId()))
                throw new InconsistenciaException("ID repetido "+a.getId()+" na classe Atendimento.");
            try {
                LocalDate data = LocalDate.parse(a.getDataString(), formatador);
                a.setData(data);
            } catch (DateTimeParseException e) {
                throw new InconsistenciaException("A data "+a.getDataString()+" do Atendimento "+a.getId()+" é inválida.");
            }
            if(!animalEstaCadastrado(a.getIdAnimal()))
                throw new InconsistenciaException("Animal de ID "+a.getIdAnimal()+" do Atendimento de ID "+a.getId()+" não cadastrado.");
            a.setAnimalAtendido(getAnimalCadastrado(a.getIdAnimal()));

            if (!servicoValidoParaData(a.getIdServicoPrestado(), a.getData())) {
                throw new InconsistenciaException("Não há registro de Serviço de ID " + a.getIdServicoPrestado() + " na Data " + a.getDataString() + " para o Atendimento de ID " + a.getId() + ".");
            } else {
                a.setServicoPrestado(getServicoValidoParaData(a.getIdServicoPrestado(), a.getData()));
            }

            atendimentosCadastrados.add(a);
        }
    }

/*---------------- Métodos Auxiliares------------- */
    private Pessoa getPessoaCadastrada(BigInteger idDesejado)
    {
        for(int i = 0; i < this.pessoasCadastradas.size(); i++)
        {
            if(this.pessoasCadastradas.get(i).getId().equals(idDesejado))
                return this.pessoasCadastradas.get(i);
        }
        return null;
    }
    private PessoaFisica getPessoaFisicaCadastrada(BigInteger idDesejado)
    {
        for(int i = 0; i < this.pessoasCadastradas.size(); i++)
        {
            if(this.pessoasCadastradas.get(i).getId().equals(idDesejado) &&this.pessoasCadastradas.get(i) instanceof PessoaFisica)
                return (PessoaFisica)this.pessoasCadastradas.get(i);
        }
        return null;
    }
    private Produto getProdutoValidoParaVenda(BigInteger idProdutoDesejado, LocalDate dataVenda)
    {
        for (Produto prod : produtosCadastrados)
        {
            if (prod.getId().equals(idProdutoDesejado))
            {
                LocalDate inicio = prod.getInicioVigencia();
                LocalDate fim = prod.getFimVigencia();
                
                if (!dataVenda.isBefore(inicio) && !dataVenda.isAfter(fim))
                {
                    return prod;
                }
            }
        }
        return null;
    }
    private Animal getAnimalCadastrado(BigInteger idAnimalDesejado)
    {
        for(int i = 0; i < animaisCadastrados.size(); i++)
        {
            if(animaisCadastrados.get(i).getId().equals(idAnimalDesejado))
                return animaisCadastrados.get(i);
        }
        return null;
    }

    private Servico getServicoValidoParaData(BigInteger idServico, LocalDate dataAtendimento)
    {
        for (Servico serv : servicosCadastrados)
        {
            if (serv.getId().equals(idServico))
            {
                LocalDate inicio = serv.getDataInicio();
                LocalDate fim = serv.getDatafim();
                
                if (!dataAtendimento.isBefore(inicio) && !dataAtendimento.isAfter(fim))
                {
                    return serv;
                }
            }
        }
    return null;
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

    private boolean produtoValidoParaData(BigInteger idProdutoDesejado, LocalDate dataVenda)
    {
        for (Produto prod : produtosCadastrados)
        {
            if (prod.getId().equals(idProdutoDesejado))
            {
                LocalDate inicio = prod.getInicioVigencia();
                LocalDate fim = prod.getFimVigencia();
                
                if (!dataVenda.isBefore(inicio) && !dataVenda.isAfter(fim))
                {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean servicoValidoParaData(BigInteger idServico, LocalDate dataAtendimento)
    {
        for (Servico serv : servicosCadastrados)
        {
            if (serv.getId().equals(idServico))
            {
                LocalDate inicio = serv.getDataInicio();
                LocalDate fim = serv.getDatafim();
                
                // Verifica se a data do atendimento está dentro do período de vigência
                if (!dataAtendimento.isBefore(inicio) && !dataAtendimento.isAfter(fim))
                {
                    return true;
                }
            }
        }
        return false;
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
