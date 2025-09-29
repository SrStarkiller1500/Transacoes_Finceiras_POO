package Model;

import java.time.OffsetDateTime;
import java.util.UUID;
import java.util.List;
import java.util.stream.Stream;

/**
 * Representa uma carteira de investimento vinculada a um investimento e a uma conta corrente.
 * Permite operações de rendimento e consulta dos dados associados.
 */
public class CarteiraInvestimento extends Carteira {

    // Investimento associado à carteira
    private final Investimento investimento;
    // Conta corrente vinculada à carteira de investimento
    private final CarteiraAccount account;

    /**
     * Construtor que inicializa a carteira de investimento com um valor inicial transferido da conta.
     * @param investimento Investimento associado
     * @param account Conta corrente vinculada
     * @param amount Valor inicial a ser investido
     */
    public CarteiraInvestimento(final Investimento investimento, final CarteiraAccount account, final long amount) {
        super(BankService.INVESTIMENTO);
        this.investimento = investimento;
        this.account = account;
        // Remove dinheiro da conta e adiciona à carteira de investimento
        List<Money> dinheiroRemovido = account.removeMoney(amount, "Investimento inicial");
        addMoney(dinheiroRemovido, getService(), "Investimento inicial");
    }

    public BankService getService() {
        throw new UnsupportedOperationException("Unimplemented method 'getService'");
    }

    /**
     * Atualiza o saldo da carteira de investimento com base em um percentual de rendimento.
     * @param percent Percentual de rendimento a ser aplicado
     */
    public void updateIAmount(final long percent) {
        long amount = getFunds() * percent / 100;
        var history = new MoneyAudit(UUID.randomUUID(), getService(), "Rendimento de " + percent + "%", OffsetDateTime.now());
        var money = Stream.generate(() -> new Money(history)).limit(amount).toList();
        this.money.addAll(money);
    }

    /**
     * Retorna a conta corrente vinculada à carteira de investimento.
     * @return CarteiraAccount vinculada
     */
    public CarteiraAccount getAccount() {
        return account;
    }

    /**
     * Retorna representação textual da carteira de investimento.
     */
    @Override
    public String toString() {
        return "CarteiraInvestimento{" +
                "investimento=" + investimento +
                ", account=" + account +
                ", saldo=R$" + (getFunds() / 100.0) +
                '}';
    }

    public Investimento getInvestment() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getInvestment'");
    }
}
