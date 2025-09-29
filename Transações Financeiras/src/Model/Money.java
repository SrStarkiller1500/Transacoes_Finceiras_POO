package Model;

import java.util.List;
import java.util.ArrayList;

/**
 * Representa uma unidade monetária, armazenando o histórico de auditorias (transações) associadas.
 */
public class Money {

    // Lista de auditorias (histórico) associadas a esta unidade de dinheiro
    private final List<MoneyAudit> history = new ArrayList<>();

    /**
     * Construtor que inicializa o Money com uma auditoria inicial.
     * @param history Auditoria inicial da transação
     */
    public Money(final MoneyAudit history) {
        this.history.add(history);
    }

    /**
     * Adiciona uma nova auditoria (transação) ao histórico desta unidade de dinheiro.
     * @param history Auditoria a ser adicionada
     */
    public void addHistory(final MoneyAudit history) {
        this.history.add(history);
    }

    /**
     * Retorna o histórico de auditorias desta unidade de dinheiro.
     * @return Lista de MoneyAudit
     */
    public List<MoneyAudit> getHistory() {
        return new ArrayList<>(history); // Retorna uma cópia para evitar modificações externas
    }

    @Override
    public String toString() {
        return "Money{" +
                "history=" + history +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Money)) return false;
        Money other = (Money) obj;
        return history.equals(other.history);
    }

    @Override
    public int hashCode() {
        return history.hashCode();
    }
}
