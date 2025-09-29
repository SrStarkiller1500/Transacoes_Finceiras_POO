
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import Repository.AccountRepository;
import Repository.InvestmentRepository;
import Exception.ContaNaoLocalizada;
import Exception.FundoInsuficiente;
import Model.CarteiraAccount;
import Model.CarteiraInvestimento;
import Model.MoneyAudit;

public class Main {

    private static final AccountRepository accountRepository = new AccountRepository();
    private static final InvestmentRepository investmentRepository = new InvestmentRepository();
    private static final Scanner scanner = new Scanner(System.in);

    // Cores ANSI
    public static final String RESET = "\u001B[0m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";

    public static void main(String[] args) {

        System.out.println("Olá! Seja bem-vindo ao Banco XPTO");

        while (true) {
            printMenu();
            int option;
            try {
                option = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Digite um número válido!");
                continue;
            }

            switch (option) {
                case 1: createAccount(); break;
                case 2: createInvestment(); break;
                case 3: criarCarteiraInvestimento(); break;
                case 4: deposit(); break;
                case 5: withdraw(); break;
                case 6: transferenciaPorConta(); break;
                case 7: realizarInvestimento(); break;
                case 8: resgateInvestimento(); break;
                case 9: listContas(); break;
                case 10: listInvestimentos(); break;
                case 11: listCarteirasInvestimento(); break;
                case 12: atualizarInvestimentos(); break;
                case 13: historicoContas(); break;
                case 14: System.out.println("Saindo..."); System.exit(0); break;
                case 15: historicoInvestimentos(); break;
                default: System.out.println("Opção inválida!"); break;
            }
        }
    }

    private static void printMenu() {
        System.out.println("\n=============================");
        System.out.println("      BANCO XPTO - MENU");
        System.out.println("=============================");
        System.out.println("1  | Criar Conta");
        System.out.println("2  | Criar Investimento");
        System.out.println("3  | Criar Carteira de Investimento");
        System.out.println("4  | Depositar em Conta");
        System.out.println("5  | Sacar da Conta");
        System.out.println("6  | Transferência entre Contas");
        System.out.println("7  | Investir (Depositar em Carteira)");
        System.out.println("8  | Sacar Investimento");
        System.out.println("9  | Listar Contas");
        System.out.println("10 | Listar Investimentos");
        System.out.println("11 | Listar Carteiras de Investimento");
        System.out.println("12 | Atualizar Juros de Investimentos");
        System.out.println("13 | Histórico de Contas");
        System.out.println("14 | Sair");
        System.out.println("15 | Histórico de Investimentos");
        System.out.println("=============================");
        System.out.print("Escolha uma opção: ");
    }

    private static void createAccount() {
        System.out.println("Informe as chaves PIX (Separadas por ';'):");
        String pixKeysInput = scanner.nextLine();
        List<String> pixKeys = Arrays.asList(pixKeysInput.split(";"));
        System.out.println("Informe o valor inicial de depósito:");
        long amount = scanner.nextLong();
        scanner.nextLine(); // limpa buffer
        var wallet = accountRepository.create(pixKeys, amount);
        System.out.println("Conta criada com sucesso: " + wallet);
    }

    private static void createInvestment() {
        System.out.println("Informe a taxa do investimento:");
        int tax = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Informe o valor inicial de depósito:");
        long initialFounds = scanner.nextLong();
        scanner.nextLine();
        var investment = investmentRepository.create(tax, initialFounds);
        System.out.println("Investimento criado com sucesso: " + investment);
    }

    private static void deposit() {
        System.out.println("Informe a Chave PIX da conta para depósito:");
        String pixKey = scanner.nextLine();
        System.out.println("Informe o valor do depósito:");
        long amount = scanner.nextLong();
        scanner.nextLine();
        accountRepository.deposit(pixKey, amount);
        System.out.println("Depósito realizado com sucesso!");
    }

    private static void withdraw() {
        System.out.println("Informe a Chave PIX da conta para saque:");
        String pixKey = scanner.nextLine();
        System.out.println("Informe o valor do saque:");
        long amount = scanner.nextLong();
        scanner.nextLine();
        try {
            accountRepository.withdraw(pixKey, amount);
            System.out.println("Saque realizado com sucesso!");
        } catch (FundoInsuficiente | ContaNaoLocalizada e) {
            System.out.println(e.getMessage());
        }
    }

    private static void transferenciaPorConta() {
        System.out.println("Informe a Chave PIX da conta de origem:");
        String source = scanner.nextLine();
        System.out.println("Informe a Chave PIX da conta de destino:");
        String target = scanner.nextLine();
        System.out.println("Informe o valor da transferência:");
        long amount = scanner.nextLong();
        scanner.nextLine();
        accountRepository.transfer(source, target, amount);
        System.out.println("Transferência realizada com sucesso!");
    }

    private static void criarCarteiraInvestimento() {
        System.out.println("Informe a Chave PIX da conta:");
        String pix = scanner.nextLine();
        var account = accountRepository.getAccountByPix(pix);
        System.out.println("Informe o ID do Investimento:");
        long investmentId = scanner.nextLong();
        scanner.nextLine();
        var investmentWallet = investmentRepository.createInvestment(account, investmentId);
        System.out.println("Carteira de Investimento criada com sucesso: " + investmentWallet);
    }

    private static void realizarInvestimento() {
        System.out.println("Informe a Chave PIX da conta para investimento:");
        String pixKey = scanner.nextLine();
        System.out.println("Informe o valor que será investido:");
        long amount = scanner.nextLong();
        scanner.nextLine();
        investmentRepository.deposit(pixKey, amount);
        System.out.println("Investimento realizado com sucesso!");
    }

    private static void resgateInvestimento() {
        System.out.println("Informe a Chave PIX da conta para resgate do investimento:");
        String pixKey = scanner.nextLine();
        System.out.println("Informe o valor do saque:");
        long amount = scanner.nextLong();
        scanner.nextLine();
        try {
            investmentRepository.withdraw(pixKey, amount);
            System.out.println("Resgate realizado com sucesso!");
        } catch (FundoInsuficiente | ContaNaoLocalizada e) {
            System.out.println(e.getMessage());
        }
    }

    private static void listContas() {
        System.out.println("\n=== Lista de Contas ===");
        accountRepository.list().forEach(System.out::println);
    }

    private static void listInvestimentos() {
        System.out.println("\n=== Lista de Investimentos ===");
        investmentRepository.list().forEach(System.out::println);
    }

    private static void listCarteirasInvestimento() {
        System.out.println("\n=== Lista de Carteiras de Investimento ===");
        investmentRepository.listCarteiraInvestimentos().forEach(System.out::println);
    }

    private static void atualizarInvestimentos() {
        System.out.println("Informe o percentual de rendimento (0 a 1000%):");
        long percent = scanner.nextLong();
        scanner.nextLine();
        investmentRepository.updateAmount(percent);
        System.out.println("Investimentos atualizados com sucesso!");
    }

    private static void historicoContas() {
        System.out.println("Informe a Chave PIX da conta para verificar o histórico:");
        String pixKey = scanner.nextLine();
        try {
            var sortedHistory = accountRepository.getHistory(pixKey);
            if (sortedHistory.isEmpty()) {
                System.out.println("Nenhuma transação encontrada.");
                return;
            }

            System.out.println("\n=== HISTÓRICO DE CONTA ===");
            sortedHistory.forEach((dateTime, transactions) -> {
                System.out.println(dateTime.format(java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")));
                transactions.forEach(tx -> {
                    boolean isSaida = tx.description().toLowerCase().contains("saque") ||
                                      tx.description().toLowerCase().contains("transferência");
                    String type = isSaida ? "[-]" : "[+]";
                    String color = isSaida ? RED : GREEN;

                    System.out.printf("%s%s%s %s | ID: %s | Serviço: %s%n",
                            color, type, RESET, tx.description(), tx.transactionid(), tx.targetService());
                });
                System.out.println("------------------------------------");
            });

        } catch (ContaNaoLocalizada e) {
            System.out.println(e.getMessage());
        }
    }

    private static void historicoInvestimentos() {
        System.out.println("Informe a Chave PIX da conta para verificar o histórico de investimentos:");
        String pixKey = scanner.nextLine();
        try {
            var wallets = investmentRepository.listCarteiraInvestimentos().stream()
                    .filter(carteira -> carteira.getAccount().getPix().contains(pixKey))
                    .toList();

            if (wallets.isEmpty()) {
                System.out.println("Nenhum investimento encontrado para essa conta.");
                return;
            }

            System.out.println("\n=== HISTÓRICO DE INVESTIMENTOS ===");
            for (var carteira : wallets) {
                System.out.println("\nInvestimento ID: " + carteira.
                getInvestment().getId());
                System.out.println("Saldo Investido: " + carteira.getFunds());
                System.out.println("Movimentações:");
                carteira.getFinancialTransactions().forEach(tx -> {
                    boolean isSaida = tx.description().toLowerCase().contains("saque");
                    String type = isSaida ? "[-]" : "[+]";
                    String color = isSaida ? RED : GREEN;

                    System.out.printf("%s%s%s %s | Data: %s | ID: %s%n",
                            color, type, RESET,
                            tx.description(),
                            tx.createdATime().format(java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")),
                            tx.transactionid());
                });
                System.out.println("------------------------------------");
            }
        } catch (Exception e) {
            System.out.println("Erro ao buscar histórico de investimentos: " + e.getMessage());
        }
    }
}