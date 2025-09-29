#🤑 Sistema Bancário XPTO 📊

## Descrição do Projeto
Este projeto é um **sistema bancário simulado**, desenvolvido em Java, que permite:

- Criação de contas com chaves PIX;
- Depósitos e saques em contas;
- Transferências entre contas via PIX;
- Criação de investimentos e carteiras de investimento;
- Atualização de rendimentos de investimentos;
- Acompanhamento de histórico de transações (extrato de contas e investimentos).

O objetivo do projeto é aplicar conceitos de **Programação Orientada a Objetos (POO)**, como **herança, encapsulamento, polimorfismo, abstração e reuso de código**, além de praticar boas práticas de desenvolvimento e documentação.

O projeto também faz uso da **Stream API** do Java para manipulação e filtragem de coleções, tornando o código mais legível e conciso.

---

## Tecnologias Utilizadas
- **Java 17** (ou superior)
- Estrutura orientada a objetos com **Records**, **Enums** e **Collections**
- **Stream API** para processamento de listas e históricos
- **Console interativo** para menu e fluxo de operações
- **VSCode** 
- Controle de versão via **Git/GitHub**

---

## Estrutura do Projeto
Projeto divido em diversos PACKAGES para deixar a estrutura mais organizada.

### Algumas funcionalidades do sistema

Esse sistema possui um menu que é acessado via console com diversas opções que permitem realizar algumas operaões financeiras como:

=============================
1 | Criar Conta
2 | Criar Investimento
3 | Criar Carteira de Investimento
4 | Depositar em Conta
5 | Sacar da Conta
6 | Transferência entre Contas
7 | Investir (Depositar em Carteira)
8 | Sacar Investimento
9 | Listar Contas
10 | Listar Investimentos
11 | Listar Carteiras de Investimento
12 | Atualizar Juros de Investimentos
13 | Histórico de Contas
14 | Sair
15 | Histórico de Investimentos

### Histórico de Contas e Investimentos

- As transações são registradas com **ID único**, descrição e **data/hora formatada**.
- O extrato exibe entradas `[+]` em **verde** e saídas `[-]` em **vermelho**.
- Permite visualizar todas as movimentações de contas e investimentos.
- O sistema utiliza **Stream API** para agrupar e filtrar transações, facilitando a leitura e organização dos históricos.

---

## Conceitos de POO Aplicados

- **Encapsulamento**: Acesso a dados via métodos, sem expor atributos diretamente.
- **Herança e Composição**: Estrutura de entidades relacionadas (Conta → CarteiraInvestimento).
- **Polimorfismo**: Métodos como `addMoney` e `removeMoney` tratados para diferentes entidades.
- **Abstração**: Uso de repositórios para simular persistência.
- **Records e Enums**: Facilita criação de entidades imutáveis e estados predefinidos.
- **Stream API**: Manipulação de coleções de forma funcional e concisa.