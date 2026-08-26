package br.edu.ifsudestemg.loja.pagamento;

import br.edu.ifsudestemg.loja.modelo.Pedido;

public class Boleto implements MeioPagamento {
    private final String banco;

    public Boleto(String banco) {
        this.banco = banco;
    }

    @Override
    public ResultadoPagamento pagar(Pedido pedido) {
        return new ResultadoPagamento(true, "Boleto registrado como pago", "BOLETO-" + pedido.getId());
    }

    @Override
    public ResultadoPagamento estornar(Pedido pedido) {
        return new ResultadoPagamento(true, "Devolução bancária solicitada", "DEV-BOL-" + pedido.getId());
    }

    @Override
    public boolean parcelar(Pedido pedido, int numeroParcelas) {
        return false;
    }

    @Override
    public String gerarCodigoBarras(Pedido pedido) {
        return "00190." + banco.hashCode() + "." + pedido.getId();
    }
}
