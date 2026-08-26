package br.edu.ifsudestemg.loja.entrega;

import br.edu.ifsudestemg.loja.ApoioTeste;
import br.edu.ifsudestemg.loja.modelo.Cliente;
import br.edu.ifsudestemg.loja.modelo.Pedido;
import br.edu.ifsudestemg.loja.modelo.ProdutoFisico;
import br.edu.ifsudestemg.loja.modelo.TipoCliente;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CalculadoraFreteTest {
    private final CalculadoraFrete calculadora = new CalculadoraFrete();

    @Test
    void calculaFretePadraoPeloPesoEDestino() {
        Pedido pedido = ApoioTeste.pedido(TipoCliente.COMUM, 100, 2);

        assertEquals(16, calculadora.calcular(pedido, "PADRAO"), 0.001);
    }

    @Test
    void calculaFreteExpressoParaOutroEstado() {
        Cliente cliente = ApoioTeste.cliente(TipoCliente.COMUM);
        Pedido pedido = new Pedido(1, cliente, ApoioTeste.endereco("RJ"));
        pedido.adicionarItem(new ProdutoFisico(1, "Produto", 100, 10, 500), 2);

        assertEquals(49, calculadora.calcular(pedido, "EXPRESSA"), 0.001);
    }

    @Test
    void retiradaNaLojaNaoTemCusto() {
        Pedido pedido = ApoioTeste.pedido(TipoCliente.COMUM, 100, 2);

        assertEquals(0, calculadora.calcular(pedido, "RETIRADA"), 0.001);
    }
}
