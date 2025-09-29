package Repository;

import java.time.OffsetDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;


import Exception.ContaNaoLocalizada;
import Exception.ChavePixEmUso;
import Model.CarteiraAccount;
import Model.MoneyAudit;

public class AccountRepository {
    private final Map<String, CarteiraAccount> accounts = new HashMap<>();

    /**
     * Cria uma nova conta com chaves PIX e saldo inicial.
     * Garante que nenhuma chave PIX já esteja em uso.
     * @param pix Lista de chaves PIX
     * @param initialFunds Saldo inicial
     * @return A nova conta criada
     */
    public CarteiraAccount create(final List<String> pix, final long initialFunds) {
        var pixInUse = new HashSet<>(accounts.keySet());
        for (String chave : pix) {
            if (pixInUse.contains(chave)) {
                throw new ChavePixEmUso("A chave PIX " + chave + " já está em uso por outra conta");
            }
        }
        var newAccount = new CarteiraAccount(initialFunds, pix);
        
        // Aqui, vamos adicionar todas as chaves PIX como chave no Map
        for (String chavePix : pix) {
            accounts.put(chavePix, newAccount);
        }
        
        return newAccount;
    }

    /**
     * Realiza o depósito em uma conta identificada pela chave PIX.
     * @param pix Chave PIX da conta
     * @param fundsAmount Valor a ser depositado
     */
    public void deposit(final String pix, final long fundsAmount) {
        var target = getAccountByPix(pix);
        target.addMoney(fundsAmount, "Depósito realizado via PIX");
    }

    /**
     * Realiza o saque de uma conta identificada pela chave PIX.
     * @param pix Chave PIX da conta
     * @param amount Valor a ser sacado
     * @return Valor sacado
     */
    public long withdraw(final String pix, final long amount) {
        var source = getAccountByPix(pix);
        CommonsRepositories.checkFundsForTransaction(source, amount);
        source.removeMoney(amount, "Saque realizado via PIX");
        return amount;
    }

    /**
     * Realiza a transferência entre duas contas via PIX.
     * @param sourcePix Chave PIX da conta de origem
     * @param targetPix Chave PIX da conta de destino
     * @param amount Valor a ser transferido
     */
    public void transfer(final String sourcePix, final String targetPix, final long amount) {
        var source = getAccountByPix(sourcePix);
        CommonsRepositories.checkFundsForTransaction(source, amount);
        var target = getAccountByPix(targetPix);
        var message = String.format("Transferência via PIX de %d realizada da conta %s para a conta %s", 
                                    amount, sourcePix, targetPix);
        var removedMoney = source.removeMoney(amount, message);
        target.addMoney(removedMoney, target.getService(), message);
    }

    /**
     * Busca uma conta através de uma chave PIX.
     * @param pix Chave PIX
     * @return A conta correspondente à chave PIX
     * @throws ContaNaoLocalizada Exceção lançada se a conta não for encontrada
     */
    public CarteiraAccount getAccountByPix(String pix) {
        var account = accounts.get(pix);
        if (account == null) {
            throw new ContaNaoLocalizada("A conta com a chave PIX " + pix + " não foi localizada.");
        }
        return account;
    }

    /**
     * Lista todas as contas cadastradas.
     * @return Lista de CarteiraAccount
     */
    public List<CarteiraAccount> list() {
        return new ArrayList<>(this.accounts.values());
    }

    /**
     * Retorna o histórico de transações de uma conta, agrupado por data/hora truncada.
     * @param pix Chave PIX da conta
     * @return Mapa de OffsetDateTime para lista de MoneyAudit
     */
public Map<OffsetDateTime, List<MoneyAudit>> getHistory(final String pix) {
    var account = getAccountByPix(pix);
    return account.getFinancialTransactions().stream()
        .filter(audit -> audit.createdATime() != null) // ou getCreatedAt(), se você implementar
        .collect(Collectors.groupingBy(
            audit -> audit.createdATime().truncatedTo(ChronoUnit.SECONDS)
        ));
}

}