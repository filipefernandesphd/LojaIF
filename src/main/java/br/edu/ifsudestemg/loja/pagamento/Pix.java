package br.edu.ifsudestemg.loja.pagamento;

import br.edu.ifsudestemg.loja.modelo.Pedido;

public class Pix implements MeioPagamento {
    private final String chave;
    private double saldo;

    public Pix(String chave, double saldo) {
        this.chave = chave;
        this.saldo = saldo;
    }

    @Override
    public ResultadoPagamento pagar(Pedido pedido) {
        if (pedido.calcularTotal() > saldo) {
            return new ResultadoPagamento(false, "Saldo insuficiente", "");
        }
        saldo -= pedido.calcularTotal();
        return new ResultadoPagamento(true, "Pix confirmado", "PIX-" + pedido.getId());
    }

    @Override
    public ResultadoPagamento estornar(Pedido pedido) {
        saldo += pedido.calcularTotal();
        return new ResultadoPagamento(true, "Pix devolvido", "DEV-PIX-" + pedido.getId());
    }

    @Override
    public boolean parcelar(Pedido pedido, int numeroParcelas) {
        return numeroParcelas == 1;
    }

    @Override
    public String gerarCodigoBarras(Pedido pedido) {
        return chave + "|" + pedido.getId() + "|" + String.format("%.2f", pedido.calcularTotal());
    }

    public double getSaldo() {
        return saldo;
    }
}
