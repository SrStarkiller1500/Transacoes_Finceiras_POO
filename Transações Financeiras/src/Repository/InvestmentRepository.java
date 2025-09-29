package Repository;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

import Exception.CarteiraNaoEncontrada;
import Exception.InvestimentoNaoEncontrado;
import Model.CarteiraInvestimento;
import Model.Investimento;
import Model.CarteiraAccount;


public class InvestmentRepository {

    private final AtomicLong nextId = new AtomicLong(0);
    private final List<Investimento> investimentos = new ArrayList<>();
    private final List<CarteiraInvestimento> carteiraInvestimentos = new ArrayList<>();

    public Investimento create(final long tax, final long saldoInicial) {
        long id = nextId.incrementAndGet();
        var investimento = new Investimento(id, tax, saldoInicial);
        investimentos.add(investimento);
        return investimento;
    }

    public CarteiraInvestimento createInvestment(final CarteiraAccount account, final long investimentoId) {
        var investimento = findInvestimentoById(investimentoId);
        checkSufficientFunds(account, investimento.saldoInicial());
        var carteira = new CarteiraInvestimento(investimento, account, investimento.saldoInicial());
        carteiraInvestimentos.add(carteira);
        return carteira;
    }

    public CarteiraInvestimento deposit(final String pix, final long valor) {
        var carteira = findCarteiraByAccountPix(pix);
        checkSufficientFunds(carteira.getAccount(), valor);
        carteira.addMoney(carteira.getAccount().removeMoney(valor, pix), carteira.getService(), "Investimento");
        return carteira;
    }

    public CarteiraInvestimento withdraw(final String pix, final long valor) {
    var carteira = findCarteiraByAccountPix(pix);
    CommonsRepositories.checkFundsForTransaction(carteira.getAccount(), valor);
    carteira.getAccount().addMoney(carteira.removeMoney(valor, pix), carteira.getService(), "Saque de investimento");

    if (carteira.getFunds() == 0) {
        carteiraInvestimentos.remove(carteira);
    }
    return carteira;
}

    public void updateAmount(final long percent) {
        if (percent < 0 || percent > 1000) {
            throw new IllegalArgumentException("Percentual de rendimento deve estar entre 0 e 1000%");
        }
        carteiraInvestimentos.forEach(carteira -> carteira.updateIAmount(percent));
    }

    private void checkSufficientFunds(CarteiraAccount account, long valor) {
        CommonsRepositories.checkFundsForTransaction(account, valor);
    }

    public Investimento findInvestimentoById(final long id) {
        return investimentos.stream()
                .filter(investimento -> investimento.getId() == id)
                .findFirst()
                .orElseThrow(() -> new InvestimentoNaoEncontrado("O investimento com o id " + id + " não foi encontrado."));
    }

    public CarteiraInvestimento findCarteiraByAccountPix(final String pix) {
        return carteiraInvestimentos.stream()
                .filter(carteira -> carteira.getAccount().getPix().contains(pix))
                .findFirst()
                .orElseThrow(() -> new CarteiraNaoEncontrada("A carteira com o pix " + pix + " não foi encontrada."));
    }

    public List<CarteiraInvestimento> listCarteiraInvestimentos() {
        return new ArrayList<>(this.carteiraInvestimentos);
    }

    public List<Investimento> list() {
        return new ArrayList<>(this.investimentos);
    }
}

