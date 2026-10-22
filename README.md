# Sistema de Contabilidade para Pet Shop 🐾

## Sobre o Projeto

O sistema tem como objetivo auxiliar na gestão contábil de um estabelecimento voltado para animais de estimação. Ele processa lotes de dados informados por planilhas CSV, valida regras de consistência de domínio, gerencia relacionamentos entre entidades e emite relatórios financeiros e estatísticos por ano de referência.

---

## Funcionalidades

* **Leitura e Validação de Dados CSV:** Importação de dados utilizando codificação UTF-8.
* **Controle de Entidades:** Gerenciamento integrado de Pessoas (Físicas e Jurídicas), Animais de estimação (divididos nas famílias suportadas: *Cães, Felinos, Aves e Roedores*), Produtos, Serviços, Vendas e Atendimentos.
* **Validação de Inconsistências:** Tratamento de exceções customizadas (`InconsistenciaException`) para verificar IDs repetidos, sobreposição de vigências em produtos/serviços, chaves inválidas (tutores, clientes, animais) e formatação de datas.
* **Geração de Relatórios Contábeis:**
  1. **Relatório Financeiro (`01-relatorio.csv`):** Faturamento e lucro divididos por família de pets e de forma global.
  2. **Estatísticas dos Prestadores (`02-estatisticas-prestadores.csv`):** Listagem e ordenação de prestadores de serviço (PF/PJ) com base no volume financeiro e quantidade de atendimentos realizados.
  3. **Estatísticas dos Clientes (`03-estatisticas-clientes.csv`):** Detalhamento dos gastos de clientes com produtos e serviços ao longo do ano.

---

## Arquitetura

O código-fonte segue os princípios de Orientação a Objetos e divisão em pacotes:

* `application/`: Classes centrais de lógica, gerenciamento em memória, leitura de arquivos e geração de relatórios (`Cadastro`, `Leitura`, `GeraRelatorios`, exceções).
* `domain/`: Classes de modelagem das entidades de negócio (`Pessoa`, `PessoaFisica`, `PessoaJuridica`, `Animal`, `Produto`, `Servico`, `Venda`, `Atendimento`, além de classes auxiliares de relatório).
