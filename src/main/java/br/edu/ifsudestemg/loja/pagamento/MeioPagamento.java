package br.edu.ifsudestemg.loja.pagamento;

import br.edu.ifsudestemg.loja.modelo.Pedido;

public interface MeioPagamento {
    ResultadoPagamento pagar(Pedido pedido);

    ResultadoPagamento estornar(Pedido pedido);

    boolean parcelar(Pedido pedido, int numeroParcelas);

    String gerarCodigoBarras(Pedido pedido);
}
