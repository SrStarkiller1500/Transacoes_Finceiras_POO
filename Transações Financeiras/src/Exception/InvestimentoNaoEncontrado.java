package Exception;

public class InvestimentoNaoEncontrado extends RuntimeException {
    public InvestimentoNaoEncontrado(String message) {
        super(message);
    }

}
