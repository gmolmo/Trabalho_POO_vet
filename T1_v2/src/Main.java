import application.Cadastro;
import application.GeraRelatorios;
import application.InconsistenciaException;
import application.Leitura;
import domain.Animal;
import domain.Atendimento;
import domain.Pessoa;
import domain.Produto;
import domain.Servico;
import domain.Venda;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {

        //captura o caminho passado como argumento
        String caminho = args[0];

        try {
            Leitura leitor = new Leitura(caminho);//cria um obj da classe leitura para parsear todos os arquivos
            ArrayList<Pessoa> pessoasLidas = leitor.LePessoas();
            ArrayList<Animal> animaisLidos = leitor.LeAnimais();
            ArrayList<Produto> produtosLidos = leitor.LeProdutos();
            ArrayList<Servico> servicosLidos = leitor.LeServicos();
            ArrayList<Atendimento> atendimentosLidos = leitor.LeAtendimentos();
            ArrayList<Venda> vendasLidas = leitor.LeVendas();//cada leitura retorna um array com as infos lidas

            Cadastro c = new Cadastro();//cria um obj para tratar as exceções de cadastro e leitura especificas
            c.processaPessoas(pessoasLidas);
            c.processaAnimais(animaisLidos);
            c.processaProdutos(produtosLidos);
            c.processaServicos(servicosLidos);
            c.processaAtendimentos(atendimentosLidos);
            c.processaVendas(vendasLidas);

            ArrayList<Integer> anos = new ArrayList<>();//um vetor para os anos lidos
            Scanner sc = new Scanner(System.in);
            while(sc.hasNextLine())
            {
                String linha = sc.nextLine().trim();//aqui, penso que tem um ano por linha
                if(linha.isBlank())
                    break;
                anos.add(Integer.parseInt(linha));
            }
            sc.close();

            GeraRelatorios printador = new GeraRelatorios(c, anos, caminho);
            printador.geraRelatorioFinanceiro();
            printador.geraEstatisticaPrestadores();
            printador.geraEstatisticaClientes();
            //caso nao caia em nenhum try, vai imprimir/criar os arquivos de relatório

        } catch (IOException e) {
            System.out.println("Erro de I/O");
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            System.out.println("Erro de formatação");
        } catch (InconsistenciaException e) {
            System.out.println(e.getMessage());
        }

    }
}

