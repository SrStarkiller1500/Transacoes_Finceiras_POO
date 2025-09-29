package Repository;

import java.time.OffsetDateTime;
import java.util.UUID;
import java.util.List;
import java.util.stream.Stream;

import Exception.FundoInsuficiente;
import Model.CarteiraAccount;
import Model.Money;
import Model.MoneyAudit;
import Model.BankService;

/**
 * Classe utilitária para operações comuns de repositórios.
 */

public final class CommonsRepositories {

    /**
     * Verifica se a carteira possui saldo suficiente para a transação.
     * @param account Conta de origem
     * @param amount Valor da transação
     * @throws FundoInsuficiente se saldo for insuficiente
     */
    public static void checkFundsForTransaction(final CarteiraAccount account, final long amount) {
        if (account.getFunds() < amount) {
            throw new FundoInsuficiente("A sua conta não possui dinheiro suficiente para realizar a transação");
        }
    }

    /**
     * Gera uma lista de Money com histórico associado à transação.
     * @param transactionId ID da transação
     * @param funds Quantidade de dinheiro a ser gerada
     * @param descricao Descrição da operação
     * @return Lista de Money criada
     */
    public static List<Money> generateMoney(final UUID transactionId, final long funds, final String descricao) {
        var history = new MoneyAudit(transactionId, BankService.CONTA_CORRENTE, descricao, OffsetDateTime.now());
        return Stream.generate(() -> new Money(history))
                     .limit(funds)
                     .toList();
    }
}
