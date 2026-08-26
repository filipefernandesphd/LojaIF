package br.edu.ifsudestemg.loja.servico;

import br.edu.ifsudestemg.loja.ApoioTeste;
import br.edu.ifsudestemg.loja.entrega.EntregaTransportadora;
import br.edu.ifsudestemg.loja.modelo.Pedido;
import br.edu.ifsudestemg.loja.modelo.ProdutoFisico;
import br.edu.ifsudestemg.loja.modelo.StatusPedido;
import br.edu.ifsudestemg.loja.modelo.TipoCliente;
import br.edu.ifsudestemg.loja.pagamento.CartaoCredito;
import br.edu.ifsudestemg.loja.pagamento.Pix;
import br.edu.ifsudestemg.loja.repositorio.RepositorioPedidosMySql;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ProcessadorPedidoTest {
    @BeforeEach
    void limparRepositorio() {
        RepositorioPedidosMySql.limpar();
    }

    @Test
    void concluiPedidoAprovadoEAtualizaEstoque() {
        ProdutoFisico produto = new ProdutoFisico(1, "Livro", 100, 5, 500);
        Pedido pedido = new Pedido(1, ApoioTeste.cliente(TipoCliente.PREMIUM), ApoioTeste.endereco("MG"));
        pedido.adicionarItem(produto, 2);

        var resultado = new ProcessadorPedido().processar(pedido, null, "PADRAO",
                new CartaoCredito("1234", 1_000),
                new EntregaTransportadora(pedido.getEnderecoEntrega(), "Entrega Escola"));

        assertTrue(resultado.isAprovado());
        assertEquals(StatusPedido.ENVIADO, pedido.getStatus());
        assertEquals(3, produto.getQuantidadeEmEstoque());
    }

    @Test
    void registraRecusaSemAlterarEstoque() {
        ProdutoFisico produto = new ProdutoFisico(1, "Livro", 100, 5, 500);
        Pedido pedido = new Pedido(1, ApoioTeste.cliente(TipoCliente.COMUM), ApoioTeste.endereco("MG"));
        pedido.adicionarItem(produto, 2);

        new ProcessadorPedido().processar(pedido, null, "PADRAO", new Pix("pix", 10),
                new EntregaTransportadora(pedido.getEnderecoEntrega(), "Entrega Escola"));

        assertEquals(StatusPedido.PAGAMENTO_RECUSADO, pedido.getStatus());
        assertEquals(5, produto.getQuantidadeEmEstoque());
    }
}
