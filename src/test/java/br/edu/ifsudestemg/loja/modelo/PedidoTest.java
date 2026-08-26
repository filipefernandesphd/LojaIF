package br.edu.ifsudestemg.loja.modelo;

import br.edu.ifsudestemg.loja.ApoioTeste;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PedidoTest {
    @Test
    void calculaSubtotalETotalComDescontoEFrete() {
        Pedido pedido = ApoioTeste.pedido(TipoCliente.COMUM, 100, 2);
        pedido.setDesconto(20);
        pedido.setFrete(15);

        assertEquals(200, pedido.calcularSubtotal(), 0.001);
        assertEquals(195, pedido.calcularTotal(), 0.001);
    }

    @Test
    void impedeAlterarPedidoDepoisDoPagamento() {
        Pedido pedido = ApoioTeste.pedido(TipoCliente.COMUM, 100, 1);
        pedido.marcarComoPago();

        assertThrows(IllegalStateException.class,
                () -> pedido.adicionarItem(new ProdutoFisico(2, "Outro", 20, 2, 100), 1));
    }
}
