package br.edu.ifsudestemg.loja.relatorio;

import br.edu.ifsudestemg.loja.ApoioTeste;
import br.edu.ifsudestemg.loja.modelo.Pedido;
import br.edu.ifsudestemg.loja.modelo.TipoCliente;
import br.edu.ifsudestemg.loja.repositorio.RepositorioPedidosMySql;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertTrue;

class GeradorRelatorioVendasTest {
    @TempDir
    Path pastaTemporaria;

    @Test
    void geraResumoDasVendasRegistradas() {
        RepositorioPedidosMySql.limpar();
        Pedido pedido = ApoioTeste.pedido(TipoCliente.COMUM, 100, 2);
        pedido.marcarComoPago();
        new RepositorioPedidosMySql().salvar(pedido);

        String relatorio = new GeradorRelatorioVendas().gerar("TEXTO",
                pastaTemporaria.resolve("vendas.txt").toString());

        assertTrue(relatorio.contains("Vendas aprovadas: 1"));
        assertTrue(relatorio.contains("Faturamento: R$ 200,00")
                || relatorio.contains("Faturamento: R$ 200.00"));
    }
}
