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
        Scanner sc = new Scanner(System.in);
        ArrayList<Integer> anos = new ArrayList<>();
        while(sc.hasNextLine())
            anos.add(sc.nextInt());
        
        sc.close();

        String caminho = args[0];

        try {
            Leitura leitor = new Leitura(caminho);

            try {
                ArrayList<Pessoa> pessoasLidas = leitor.LePessoas();
                ArrayList<Animal> animaisLidos = leitor.LeAnimais();
                ArrayList<Produto> produtosLidos = leitor.LeProdutos();
                ArrayList<Servico> servicosLidos = leitor.LeServicos();
                ArrayList<Atendimento> atendimentosLidos = leitor.LeAtendimentos();
                ArrayList<Venda> vendasLidas = leitor.LeVendas();


                Cadastro c = new Cadastro();
                try {
                    c.processaPessoas(pessoasLidas);
                    c.processaAnimais(animaisLidos);
                    c.processaProdutos(produtosLidos);
                    c.processaServicos(servicosLidos);
                    c.processaAtendimentos(atendimentosLidos);
                    c.processaVendas(vendasLidas);
                } catch (InconsistenciaException e) {
                    System.out.println(e.getMessage());
                }

                GeraRelatorios printador = new GeraRelatorios(c, anos, caminho);
                printador.geraRelatorioFinanceiro();
                printador.geraEstatisticaPrestadores();
                printador.geraEstatisticaClientes();

            } catch (IOException e) {//todas as leituras lançam essa 
                System.out.println("Erro de formatação");
            }
        } catch (IOException e){
            System.out.println(e.getMessage());
        }
    }
}
