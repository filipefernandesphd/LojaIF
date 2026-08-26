package br.edu.ifsudestemg.loja.pagamento;

import br.edu.ifsudestemg.loja.ApoioTeste;
import br.edu.ifsudestemg.loja.modelo.Pedido;
import br.edu.ifsudestemg.loja.modelo.TipoCliente;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PagamentosTest {
    @Test
    void cartaoAprovaEDescontaOLimite() {
        Pedido pedido = ApoioTeste.pedido(TipoCliente.COMUM, 150, 1);
        CartaoCredito cartao = new CartaoCredito("1234", 500);

        ResultadoPagamento resultado = cartao.pagar(pedido);

        assertTrue(resultado.isAprovado());
        assertEquals(350, cartao.getLimiteDisponivel(), 0.001);
    }

    @Test
    void pixRecusaQuandoOSaldoEInsuficiente() {
        Pedido pedido = ApoioTeste.pedido(TipoCliente.COMUM, 150, 1);
        Pix pix = new Pix("chave@pix", 100);

        assertFalse(pix.pagar(pedido).isAprovado());
        assertEquals(100, pix.getSaldo(), 0.001);
    }

    @Test
    void boletoGeraCodigoParaOPedido() {
        Pedido pedido = ApoioTeste.pedido(TipoCliente.COMUM, 150, 1);

        String codigo = new Boleto("Banco Escola").gerarCodigoBarras(pedido);

        assertTrue(codigo.contains(String.valueOf(pedido.getId())));
    }
}
