package br.edu.ifsudestemg.loja.pagamento;

import br.edu.ifsudestemg.loja.modelo.Pedido;

public class CartaoCredito implements MeioPagamento {
    private final String ultimosDigitos;
    private double limiteDisponivel;

    public CartaoCredito(String ultimosDigitos, double limiteDisponivel) {
        this.ultimosDigitos = ultimosDigitos;
        this.limiteDisponivel = limiteDisponivel;
    }

    @Override
    public ResultadoPagamento pagar(Pedido pedido) {
        if (pedido.calcularTotal() > limiteDisponivel) {
            return new ResultadoPagamento(false, "Limite insuficiente", "");
        }
        limiteDisponivel -= pedido.calcularTotal();
        return new ResultadoPagamento(true, "Pagamento no cartão aprovado", "CARTAO-" + pedido.getId());
    }

    @Override
    public ResultadoPagamento estornar(Pedido pedido) {
        limiteDisponivel += pedido.calcularTotal();
        return new ResultadoPagamento(true, "Estorno realizado", "ESTORNO-" + pedido.getId());
    }

    @Override
    public boolean parcelar(Pedido pedido, int numeroParcelas) {
        return numeroParcelas >= 1 && numeroParcelas <= 12;
    }

    @Override
    public String gerarCodigoBarras(Pedido pedido) {
        return "";
    }

    public String getUltimosDigitos() {
        return ultimosDigitos;
    }

    public double getLimiteDisponivel() {
        return limiteDisponivel;
    }
}
