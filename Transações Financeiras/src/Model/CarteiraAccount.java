package Model;

import java.util.List;

/**
 * Representa uma carteira de conta corrente vinculada a chaves PIX.
 * Permite operações de depósito e consulta das chaves PIX associadas.
 */
public class CarteiraAccount extends Carteira {

    // Lista de chaves PIX associadas à conta
    private final List<String> pix;

    /**
     * Construtor padrão, inicializa a carteira com as chaves PIX.
     * @param pix Lista de chaves PIX
     */
    public CarteiraAccount(final List<String> pix) {
        super(BankService.CONTA_CORRENTE); 
        this.pix = pix;
    }

    /**
     * Construtor que inicializa a carteira com valor inicial e chaves PIX.
     * @param amount Valor inicial a ser depositado
     * @param pix Lista de chaves PIX
     */
    public CarteiraAccount(final long amount, final List<String> pix) {
        super(BankService.CONTA_CORRENTE);
        this.pix = pix;
        addMoney(amount, "Valor Inicial");
    }

    /**
     * Realiza depósito na carteira, adicionando dinheiro com histórico.
     * @param amount Valor a ser depositado
     * @param descricao Descrição da operação
     */
    public void addMoney(final long amount, String descricao) {
        List<Money> generatedMoney = generateMoney(amount, descricao);
        this.money.addAll(generatedMoney);
    }

    /**
     * Retorna a lista de chaves PIX associadas à conta.
     * @return Lista de chaves PIX
     */
    public List<String> getPix() {
        return pix;
    }

    /**
     * Retorna representação textual da carteira, incluindo as chaves PIX.
     */
    @Override
    public String toString() {
        return "CarteiraAccount{" +
                "pix=" + pix +
                ", saldo=R$" + (getFunds() / 100.0) +
                '}';
    }

    public BankService getService() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getService'");
    }
}