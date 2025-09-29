package Model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.time.OffsetDateTime;

/**
 * Classe abstrata que representa uma carteira de valores.
 * Gerencia operações de entrada, saída e histórico de dinheiro.
 */
public abstract class Carteira {

    
    private final BankService service;

    // Lista que armazena os objetos Money (cada unidade representa R$1,00 ou centavos, conforme implementação)
    protected final List<Money> money;

    public Carteira(final BankService serviceType) {
        this.service = serviceType;
        this.money = new ArrayList<>();
    }

    /**
     * Gera uma lista de Money com histórico associado.
     * @param amount Quantidade de unidades a serem geradas.
     * @param descricao Descrição da operação.
     * @return Lista de Money criada.
     */
    protected List<Money> generateMoney(final long amount, final String descricao) {
        var history = new MoneyAudit(
                UUID.randomUUID(),
                service,
                descricao,
                OffsetDateTime.now()
        );
        List<Money> generated = new ArrayList<>();
        for (int i = 0; i < amount; i++) {
            generated.add(new Money(history));
        }
        return generated;
    }

    /**
     * Retorna o saldo atual da carteira.
     * @return Quantidade de Money armazenada.
     */
    public long getFunds() {
        return money.size();
    }

    /**
     * Adiciona uma lista de Money à carteira, registrando histórico.
     * @param money Lista de Money a adicionar.
     * @param service Serviço bancário relacionado.
     * @param descricao Descrição da operação.
     */
    public void addMoney(final List<Money> money, final BankService service, final String descricao) {
        var history = new MoneyAudit(
                UUID.randomUUID(),
                service,
                descricao,
                OffsetDateTime.now()
        );
        money.forEach(m -> m.addHistory(history));
        this.money.addAll(money);
    }

    /**
     * Remove uma quantidade de Money da carteira, registrando histórico.
     * @param amount Quantidade a remover.
     * @param descricao Descrição da operação.
     * @return Lista de Money removida.
     */
    public List<Money> removeMoney(final long amount, final String descricao) {
        List<Money> toRemove = new ArrayList<>();
        for (int i = 0; i < amount && !money.isEmpty(); i++) {
            Money m = money.remove(0);
            m.addHistory(new MoneyAudit(
                    UUID.randomUUID(),
                    service,
                    descricao,
                    OffsetDateTime.now()
            ));
            toRemove.add(m);
        }
        return toRemove;
    }

    /**
     * Retorna o histórico de todas as transações financeiras da carteira.
     * @return Lista de MoneyAudit (histórico).
     */
    public List<MoneyAudit> getFinancialTransactions() {
        List<MoneyAudit> audits = new ArrayList<>();
        for (Money m : money) {
            audits.addAll(m.getHistory());
        }
        return audits;
    }

    @Override
    public String toString() {
        return "Carteira{" +
                "service=" + service +
                ", money=R$" + (money.size() / 100.0) +
                '}';
    }
}