package application;

import domain.Animal;
import domain.Atendimento;
import domain.Pessoa;
import domain.PessoaFisica;
import domain.Produto;
import domain.Servico;
import domain.Venda;
import domain.reportClasses.ClienteRelatorio;
import domain.reportClasses.ItemRelatorio;
import domain.reportClasses.PrestadorRelatorio;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class GeraRelatorios
{
    private Cadastro cadastro;
    private final String[] animaisSuportados = {"Aves", "Cães", "Felinos", "Roedores"};
    private ArrayList<Integer> anos;
    private final String diretorioBase;
    private File pastaSaida;

    public GeraRelatorios(Cadastro cadastro, ArrayList<Integer> anos, String diretorioBase) {
        this.cadastro = cadastro;
        this.anos = anos;
        this.diretorioBase = diretorioBase;
        this.pastaSaida = new File(diretorioBase, "saida");
        if (!pastaSaida.exists())
            pastaSaida.mkdirs();
    }

    public void geraRelatorioFinanceiro()
    {
        File arquivoSaida = new File(this.pastaSaida, "01-relatorio.csv");
        try (PrintWriter escritor = new PrintWriter(new OutputStreamWriter(new FileOutputStream(arquivoSaida, true), StandardCharsets.UTF_8)))
        {
            for(Integer ano : this.anos)
            {
                if(!existeRegistroAno(ano))
                {
                    escritor.println("Não há registros contábeis para o ano " + ano + ".");
                    escritor.println("");

                    continue;
                }
                else
                {
                    escritor.println("Relatório do ano " + ano);
                    escritor.println("");
                    for (String familia : animaisSuportados)
                    {
                        escritor.println("Produtos Vendidos Para " + familia + ":");
                        ArrayList<ItemRelatorio> produtosFamilia = processaProdutosFamilia(familia, ano);
                        if (produtosFamilia.isEmpty())
                        {
                            escritor.println("Não há registros.");
                            escritor.println("");

                        }
                        else
                        {
                            for (ItemRelatorio item : produtosFamilia)
                            {
                                escritor.println(item.getNome() + "; " + formataMoeda(item.getFaturamento()));
                            }
                            escritor.println("");

                        }
                    }
                    for (String familia : animaisSuportados)
                    {
                        escritor.println("Atendimentos Realizados em " + familia + ":");
                        ArrayList<ItemRelatorio> servicosFamilia = processaServicosFamilia(familia, ano);
                        if (servicosFamilia.isEmpty())
                        {
                            escritor.println("Não há registros.");
                            escritor.println("");

                        }
                        else
                        {
                            for (ItemRelatorio item : servicosFamilia)
                            {
                            escritor.println(item.getNome() + "; " + formataMoeda(item.getFaturamento()));
                            }
                            escritor.println("");
                        }
                        
                    }
                }
                escritor.println("Resumo:");
                escritor.println("Total faturado em " + ano + ": " + formataMoeda(calculaFaturamentoAno(ano)));
                escritor.println("Lucro total em " + ano + ": " + formataMoeda(calculaLucroAno(ano)));
                escritor.println("");
            }
        } catch (IOException e) {
            System.out.println("Erro de I/O");
        }
    }
    public void geraEstatisticaPrestadores()
    {
        File arquivoSaida = new File(this.pastaSaida, "02-estatisticas-prestadores.csv");
        try (PrintWriter escritor = new PrintWriter(new OutputStreamWriter(new FileOutputStream(arquivoSaida, true), StandardCharsets.UTF_8)))
        {
            for(Integer ano : anos)
            {
                escritor.println("Estatísticas dos Prestadores em "+ano);
                escritor.println("");

                ArrayList<PrestadorRelatorio> prestadores = processaEstatisticaPrestadores(ano);
                prestadores.sort((p1, p2) -> {
                    int compTipo = p1.getTipo().compareTo(p2.getTipo());
                    if (compTipo != 0) return compTipo;

                    int compValor = Double.compare(p2.getValorRecebido(), p1.getValorRecebido());
                    if (compValor != 0) return compValor;

                    return p1.getNome().compareTo(p2.getNome());
                });
                if (prestadores.isEmpty())
                {
                    escritor.println("Não há registros.");
                    escritor.println("");
                }
                else
                {
                    for (PrestadorRelatorio pr : prestadores)
                    {
                        String servicosStr = String.join(", ", pr.getServicosExecutados());
                        escritor.println(pr.getTipo() + "; " + pr.getNome() + "; " + formataMoeda(pr.getValorRecebido()*0.8) + "; " + servicosStr);
                    }
                    escritor.println("");
                }
                
            }
        } catch (IOException e) {
            System.out.println("Erro de I/O");
        }
    }
    public void geraEstatisticaClientes() {
        File arquivoSaida = new File(this.pastaSaida, "03-estatisticas-clientes.csv");
        try (PrintWriter escritor = new PrintWriter(new OutputStreamWriter(new FileOutputStream(arquivoSaida, true), StandardCharsets.UTF_8))) {
            for (Integer ano : anos) {
                escritor.println("Estatísticas dos Clientes em " + ano);
                escritor.println("");

                ArrayList<ClienteRelatorio> clientes = processaEstatisticaClientes(ano);

                clientes.sort((c1, c2) -> {
                    int compValor = Double.compare(c2.getTotalGasto(), c1.getTotalGasto());
                    if (compValor != 0) return compValor;
                    return c1.getNome().compareTo(c2.getNome());
                });

                if (clientes.isEmpty()) {
                    escritor.println("Não há registros.");
                    escritor.println("");
                } else {
                    for (ClienteRelatorio cr : clientes) {
                        escritor.println(cr.getNome() + " gastou " + formataMoeda(cr.getTotalGasto()) + " em:");
                        escritor.println("");

                        escritor.println("Produtos:");
                        if (cr.getComprasFeitas().isEmpty())
                        {
                            escritor.println("Não há registros.");
                            escritor.println("");
                        } 
                        else
                        {
                            Map<Produto, Integer> produtosAgrupados = new LinkedHashMap<>();

                            for (Venda v : cr.getComprasFeitas())
                            {
                                Produto prod = v.getProdutoVendido();
                                if (prod != null) {
                                    produtosAgrupados.put(
                                        prod, 
                                        produtosAgrupados.getOrDefault(prod, 0) + v.getQuantidade()
                                    );
                                }
                            }

                            List<Map.Entry<Produto, Integer>> listaProdutos = new ArrayList<>(produtosAgrupados.entrySet());

                            listaProdutos.sort((e1, e2) -> {
                                double total1 = e1.getValue() * e1.getKey().getPrecoVenda();
                                double total2 = e2.getValue() * e2.getKey().getPrecoVenda();
                                
                                int compValor = Double.compare(total2, total1);
                                if (compValor != 0) return compValor;
                                
                                return e1.getKey().getNome().compareTo(e2.getKey().getNome());
                            });

                            for (Map.Entry<Produto, Integer> entry : listaProdutos)
                            {
                                Produto prod = entry.getKey();
                                int qtdTotal = entry.getValue();
                                double totalVenda = qtdTotal * prod.getPrecoVenda();
                                
                                escritor.println(prod.getNome() + "; " + formataMoeda(totalVenda));
                            }
                            escritor.println("");
                        }

                        for (String familia : animaisSuportados)
                        {
                            escritor.println("Serviços para " + familia + ":");
                            
                            Map<String, Double> servicosAgrupados = new LinkedHashMap<>();
                            
                            for (Atendimento a : cr.getAtendimentosFeitos())
                            {
                                if (a.getAnimalAtendido().getFamilia().equalsIgnoreCase(familia))
                                {
                                    Servico serv = a.getServicoPrestado();
                                    if (serv != null) {
                                        servicosAgrupados.put(
                                            serv.getNome(), 
                                            servicosAgrupados.getOrDefault(serv.getNome(), 0.0) + serv.getPreco()
                                        );
                                    }
                                }
                            }

                            if (servicosAgrupados.isEmpty())
                            {
                                escritor.println("Não há registros.");
                                escritor.println("");
                            }
                            else
                            {
                                List<Map.Entry<String, Double>> listaServicos = new ArrayList<>(servicosAgrupados.entrySet());
                                listaServicos.sort((e1, e2) -> {
                                    int compValor = Double.compare(e2.getValue(), e1.getValue());
                                    if (compValor != 0) return compValor;
                                    return e1.getKey().compareTo(e2.getKey());
                                });

                                for (Map.Entry<String, Double> entry : listaServicos)
                                {
                                    escritor.println(entry.getKey() + "; " + formataMoeda(entry.getValue()));
                                }
                                escritor.println("");
                            }
                        }
                    }
                }
            }
        
        } catch (IOException e) {
            System.out.println("Erro de I/O");
        }
    }

//métodos para auxiliar o relatório financeiro
    private boolean existeRegistroAno(Integer ano)
    {
        LocalDate dataInicio = LocalDate.of(ano, 1, 1);
        LocalDate dataFim = LocalDate.of(ano, 12, 31);

        for (Produto p : cadastro.getProdutosCadastrados())
        {
            if (!p.getInicioVigencia().isAfter(dataFim) && !p.getFimVigencia().isBefore(dataInicio)) {
                return true;
            }
        }
        for (Servico s : cadastro.getServicosCadastrados())
        {
            if (!s.getDataInicio().isAfter(dataFim) && !s.getDatafim().isBefore(dataInicio)) {
                return true;
            }
        }

        return false;
    }
    private ArrayList<ItemRelatorio> processaProdutosFamilia(String familia, Integer ano)
    {
        ArrayList<ItemRelatorio> produtos = new ArrayList<>();
        LocalDate dataInicio = LocalDate.of(ano, 1, 1);
        LocalDate dataFim = LocalDate.of(ano, 12, 31);

        for(Produto p : cadastro.getProdutosCadastrados())
        {
            boolean atendeFamilia = false;
            for (String f : p.getAnimaisDestinados())
            {
                if (f.equalsIgnoreCase(familia))
                {
                    atendeFamilia = true;
                    break;
                }
            }
            if(!atendeFamilia)
                continue;

            boolean vigente = !p.getInicioVigencia().isAfter(dataFim) && !p.getFimVigencia().isBefore(dataInicio);
            if (!vigente) continue;

            double faturamento = 0.0;
            double lucro = 0.0;

            for (Venda v : cadastro.getVendasCadastradas())
            {
                if (v.getIdProduto().equals(p.getId()) && v.getDataVenda().getYear() == ano)
                {
                    double fatVenda = v.getQuantidade() * p.getPrecoVenda();
                    double lucVenda = v.getQuantidade() * (p.getPrecoVenda() - p.getPrecoCompra());
                    
                    faturamento += fatVenda;
                    lucro += lucVenda;
                }
            }
            produtos.add(new ItemRelatorio(p.getNome(), faturamento, lucro));
        }

        produtos.sort((i1, i2) -> {
            int comp = Double.compare(i2.getFaturamento(), i1.getFaturamento());
            if (comp != 0) {
                return comp;
            }
            return i1.getNome().compareTo(i2.getNome());
        });

        return produtos;
    }   
    private ArrayList<ItemRelatorio> processaServicosFamilia(String familia, Integer ano) {

        ArrayList<ItemRelatorio> listaServicos = new ArrayList<>();

        LocalDate dataInicio = LocalDate.of(ano, 1, 1);
        LocalDate dataFim = LocalDate.of(ano, 12, 31);

        for (Servico s : cadastro.getServicosCadastrados())
        {
            boolean atendeFamilia = false;
            for (String f : s.getAnimaisDestinados())
            {
                    if (f.equalsIgnoreCase(familia))
                    {
                        atendeFamilia = true;
                        break;
                    }
            }
            if (!atendeFamilia) continue;

            boolean vigente = !s.getDataInicio().isAfter(dataFim) && !s.getDatafim().isBefore(dataInicio);
            if (!vigente) continue;

            String nomePrestador = s.getPrestadorServico().getNome();

            double faturamento = 0.0;

            for (Atendimento a : cadastro.getAtendimentosCadastrados())
            {
                if (a.getIdServicoPrestado().equals(s.getId()) && a.getData().getYear() == ano)
                {
                    Animal animal = a.getAnimalAtendido();
                    if (animal.getFamilia().equalsIgnoreCase(familia))
                    {
                        if (!a.getData().isBefore(s.getDataInicio()) && !a.getData().isAfter(s.getDatafim()))
                        {
                            faturamento += s.getPreco();
                        }
                    }
                }
            }

            double lucroEmpresa = faturamento * 0.20;
            
            String nomeFormatado = s.getNome() + "; " + nomePrestador;
            
            listaServicos.add(new ItemRelatorio(nomeFormatado, faturamento, lucroEmpresa));
        }

        listaServicos.sort((i1, i2) -> {
            int comp = Double.compare(i2.getFaturamento(), i1.getFaturamento());
            if (comp != 0) {
                return comp;
            }
            return i1.getNome().compareTo(i2.getNome());
        });

        return listaServicos;
    }
    private double calculaFaturamentoAno(Integer ano)
    {
        double faturamentoTotal = 0.0;
        LocalDate dataInicio = LocalDate.of(ano, 1, 1);
        LocalDate dataFim = LocalDate.of(ano, 12, 31);

        for (Produto p : cadastro.getProdutosCadastrados())
        {
            boolean vigente = !p.getInicioVigencia().isAfter(dataFim) && !p.getFimVigencia().isBefore(dataInicio);
            if (vigente)
            {
                for (Venda v : cadastro.getVendasCadastradas())
                {
                    if (v.getIdProduto().equals(p.getId()) && v.getDataVenda().getYear() == ano)
                    {
                        faturamentoTotal += v.getQuantidade() * p.getPrecoVenda();
                    }
                }
            }
        }
        for (Servico s : cadastro.getServicosCadastrados()) 
        {
            boolean vigente = !s.getDataInicio().isAfter(dataFim) && !s.getDatafim().isBefore(dataInicio);
            if (vigente) 
            {
                for (Atendimento a : cadastro.getAtendimentosCadastrados()) 
                {
                    if (a.getIdServicoPrestado().equals(s.getId()) && a.getData().getYear() == ano) 
                    {
                        Animal animal = a.getAnimalAtendido();
                        if (!a.getData().isBefore(s.getDataInicio()) && !a.getData().isAfter(s.getDatafim()))
                        {
                            faturamentoTotal += s.getPreco();
                        }
                    }
                }
            }
        }
                return faturamentoTotal;
    }
    private double calculaLucroAno(Integer ano) {
        double lucroTotal = 0.0;
        LocalDate dataInicio = LocalDate.of(ano, 1, 1);
        LocalDate dataFim = LocalDate.of(ano, 12, 31);

        for (Produto p : cadastro.getProdutosCadastrados())
        {
            boolean vigente = !p.getInicioVigencia().isAfter(dataFim) && !p.getFimVigencia().isBefore(dataInicio);
            if (vigente)
            {
                for (Venda v : cadastro.getVendasCadastradas())
                {
                    if (v.getIdProduto().equals(p.getId()) && v.getDataVenda().getYear() == ano)
                    {
                        lucroTotal += v.getQuantidade() * (p.getPrecoVenda() - p.getPrecoCompra());
                    }
                }
            }
        }

        for (Servico s : cadastro.getServicosCadastrados())
        {
            boolean vigente = !s.getDataInicio().isAfter(dataFim) && !s.getDatafim().isBefore(dataInicio);
            if (vigente)
            {
                for (Atendimento a : cadastro.getAtendimentosCadastrados())
                {
                    if (a.getIdServicoPrestado().equals(s.getId()) && a.getData().getYear() == ano)
                    {
                        Animal animal = a.getAnimalAtendido();
                        if (!a.getData().isBefore(s.getDataInicio()) && !a.getData().isAfter(s.getDatafim()))
                        {
                            lucroTotal += s.getPreco() * 0.20;
                        }
                    }
                }
            }
        }

        return lucroTotal;
    }
    private String formataMoeda(double valor)
    {
        return String.format(Locale.of("pt", "BR"), "R$ %.2f", valor);
    }

//métodos para auxiliar o relatório de prestadores
    private ArrayList<PrestadorRelatorio> processaEstatisticaPrestadores(Integer ano) {
        ArrayList<PrestadorRelatorio> listaPrestadores = new ArrayList<>();
        
        for(Pessoa p : cadastro.getPessoasCadastradas())
        {
            String tipo;
            if(p instanceof PessoaFisica)
            {
                tipo = "PF";
            }
            else
            {
                tipo = "PJ";
            }

            double valorRecebido = 0.0;
            int nAtendimentosTotal = 0;

            Map<Servico, Integer> contagemServicos = new HashMap<>();

            for (Servico s : cadastro.getServicosCadastrados())
            {
                if (s.getPrestadorServico().getId().equals(p.getId()))
                {
                    contagemServicos.put(s, 0);

                    for (Atendimento a : cadastro.getAtendimentosCadastrados())
                    {
                        if (a.getIdServicoPrestado().equals(s.getId()) && a.getData().getYear() == ano)
                        {
                            if (!a.getData().isBefore(s.getDataInicio()) && !a.getData().isAfter(s.getDatafim()))
                            {
                                contagemServicos.put(s, contagemServicos.get(s) + 1);
                                valorRecebido += s.getPreco();
                                nAtendimentosTotal++;
                            }
                        }
                    }
                }
            }

            if (tipo.equals("PF") && nAtendimentosTotal == 0)
            {
                continue;
            }

            String[] servicosFormatados;
            if (contagemServicos.isEmpty())
            {
                servicosFormatados = new String[]{"-"};
            }
            else
            {
                ArrayList<Map.Entry<Servico, Integer>> entradas = new ArrayList<>(contagemServicos.entrySet());
                entradas.sort((e1, e2) -> {
                    int compCount = Integer.compare(e2.getValue(), e1.getValue());
                    if (compCount != 0) return compCount;
                    return e1.getKey().getNome().compareTo(e2.getKey().getNome());
                });

                servicosFormatados = new String[entradas.size()];
                for (int i = 0; i < entradas.size(); i++)
                {
                    servicosFormatados[i] = entradas.get(i).getKey().getNome();
                }
            }

            listaPrestadores.add(new PrestadorRelatorio(tipo, p.getNome(), valorRecebido, servicosFormatados));
        }
        return listaPrestadores;
    }

//métodos para auxiliar o relatório de Clientes

    private ArrayList<ClienteRelatorio> processaEstatisticaClientes(Integer ano) {
        ArrayList<ClienteRelatorio> listaClientes = new ArrayList<>();
        
        for (Pessoa p : cadastro.getPessoasCadastradas())
        {
            if (!(p instanceof PessoaFisica))
                continue;

            double totalGasto = 0.0;
            ArrayList<Venda> comprasCliente = new ArrayList<>();
            ArrayList<Atendimento> atendimentosCliente = new ArrayList<>();

            for(Venda v : cadastro.getVendasCadastradas())
            {
                if(v.getDataVenda().getYear() == ano)
                {
                    if(v.getIdCliente().equals(p.getId()))
                    {
                        Produto prod = v.getProdutoVendido();
                        if (prod != null) {
                            totalGasto += v.getQuantidade() * prod.getPrecoVenda();
                            comprasCliente.add(v);
                        }
                    }
                }
            }
            for(Atendimento a : cadastro.getAtendimentosCadastrados())
            {
                if(a.getData().getYear() == ano)
                {
                    Animal animal = a.getAnimalAtendido();

                    if(animal != null && animal.getIdTutor().equals(p.getId()))
                    {
                        Servico s = a.getServicoPrestado();
                        if (s != null)
                        {
                            if (!a.getData().isBefore(s.getDataInicio()) && !a.getData().isAfter(s.getDatafim()))
                            {
                                totalGasto += s.getPreco();
                                atendimentosCliente.add(a);
                            }
                        }
                    }
                }
            }

            if (totalGasto == 0.0)
                continue;

            listaClientes.add(new ClienteRelatorio(p.getNome(), totalGasto, comprasCliente, atendimentosCliente));
        }
        
        return listaClientes;
    }
}