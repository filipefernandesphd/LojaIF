package br.edu.ifsudestemg.loja.servico;

import br.edu.ifsudestemg.loja.ApoioTeste;
import br.edu.ifsudestemg.loja.modelo.Pedido;
import br.edu.ifsudestemg.loja.modelo.ProdutoDigital;
import br.edu.ifsudestemg.loja.modelo.ProdutoFisico;
import br.edu.ifsudestemg.loja.modelo.TipoCliente;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ServicoEstoqueTest {
    private final ServicoEstoque servico = new ServicoEstoque();

    @Test
    void reservaAQuantidadeDeProdutoFisico() {
        ProdutoFisico produto = new ProdutoFisico(1, "Livro", 80, 10, 500);
        Pedido pedido = new Pedido(1, ApoioTeste.cliente(TipoCliente.COMUM), ApoioTeste.endereco("MG"));
        pedido.adicionarItem(produto, 3);

        servico.reservarItens(pedido);

        assertEquals(7, produto.getQuantidadeEmEstoque());
    }

    @Test
    void rejeitaReservaAcimaDoEstoque() {
        ProdutoFisico produto = new ProdutoFisico(1, "Livro", 80, 2, 500);
        Pedido pedido = new Pedido(1, ApoioTeste.cliente(TipoCliente.COMUM), ApoioTeste.endereco("MG"));
        pedido.adicionarItem(produto, 3);

        assertThrows(IllegalStateException.class, () -> servico.reservarItens(pedido));
    }

    @Test
    void naoAlteraNenhumItemQuandoAReservaNaoPodeSerCompleta() {
        ProdutoFisico disponivel = new ProdutoFisico(1, "Livro", 80, 5, 500);
        ProdutoFisico indisponivel = new ProdutoFisico(2, "Teclado", 200, 1, 800);
        Pedido pedido = new Pedido(1, ApoioTeste.cliente(TipoCliente.COMUM), ApoioTeste.endereco("MG"));
        pedido.adicionarItem(disponivel, 2);
        pedido.adicionarItem(indisponivel, 2);

        assertThrows(IllegalStateException.class, () -> servico.reservarItens(pedido));
        assertEquals(5, disponivel.getQuantidadeEmEstoque());
        assertEquals(1, indisponivel.getQuantidadeEmEstoque());
    }

    @Test
    void produtoDigitalPermaneceDisponivelAposReserva() {
        ProdutoDigital produto = new ProdutoDigital(2, "Curso", 100, "https://exemplo.test/curso");
        Pedido pedido = new Pedido(1, ApoioTeste.cliente(TipoCliente.COMUM), ApoioTeste.endereco("MG"));
        pedido.adicionarItem(produto, 100);

        servico.reservarItens(pedido);

        assertEquals(Integer.MAX_VALUE, produto.getQuantidadeEmEstoque());
    }
}
