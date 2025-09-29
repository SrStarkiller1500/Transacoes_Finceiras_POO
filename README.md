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

---

## Tecnologias Utilizadas
- **Java 17** (ou superior)
- Estrutura orientada a objetos com **Records**, **Enums** e **Collections**
- **Console interativo** para menu e fluxo de operações
- **VSCode**
- Controle de versão via **Git/GitHub**

---

## Estrutura do Projeto

### Algumas Funções do Sistema, o Menu Interativo
O menu do sistema, acessado via console, permite:

1. Criar conta com PIX
2. Criar investimento
3. Criar carteira de investimento
4. Depositar em conta
5. Sacar da conta
6. Transferência entre contas
7. Investir (depositar em carteira)
8. Sacar investimento
9. Listar contas
10. Listar investimentos
11. Listar carteiras de investimento
12. Atualizar juros de investimentos
13. Histórico de contas
14. Sair
15. Histórico de investimentos

### Histórico de Transações
- As transações são registradas com **ID único**, descrição e **data/hora formatada**.
- O extrato exibe entradas `[+]` em **verde** e saídas `[-]` em **vermelho**.
- Permite visualizar todas as movimentações de contas e investimentos.

---

## Conceitos de POO Aplicados

- **Encapsulamento**: Acesso a dados via métodos, sem expor atributos diretamente.
- **Herança e Composição**: Estrutura de entidades relacionadas (Conta → CarteiraInvestimento).
- **Polimorfismo**: Métodos como `addMoney` e `removeMoney` tratados para diferentes entidades.
- **Abstração**: Uso de repositórios para simular persistência.
- **Records e Enums**: Facilita criação de entidades imutáveis e estados predefinidos.