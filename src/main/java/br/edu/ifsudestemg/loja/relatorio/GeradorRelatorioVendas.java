package br.edu.ifsudestemg.loja.relatorio;

import br.edu.ifsudestemg.loja.modelo.Pedido;
import br.edu.ifsudestemg.loja.modelo.StatusPedido;
import br.edu.ifsudestemg.loja.repositorio.RepositorioPedidosMySql;

import java.time.LocalDateTime;
import java.util.List;

public class GeradorRelatorioVendas {
    private final RepositorioPedidosMySql repositorio = new RepositorioPedidosMySql();
    private final ExportadorRelatorioArquivo exportador = new ExportadorRelatorioArquivo();

    public String gerar(String formato, String caminho) {
        List<Pedido> pedidos = repositorio.buscarTodos();
        long vendasAprovadas = pedidos.stream()
                .filter(p -> p.getStatus() == StatusPedido.PAGO || p.getStatus() == StatusPedido.ENVIADO)
                .count();
        double faturamento = pedidos.stream()
                .filter(p -> p.getStatus() == StatusPedido.PAGO || p.getStatus() == StatusPedido.ENVIADO)
                .mapToDouble(Pedido::calcularTotal)
                .sum();

        String conteudo;
        if (formato.equalsIgnoreCase("TEXTO")) {
            conteudo = "RELATÓRIO DE VENDAS\n"
                    + "Gerado em: " + LocalDateTime.now() + "\n"
                    + "Pedidos registrados: " + pedidos.size() + "\n"
                    + "Vendas aprovadas: " + vendasAprovadas + "\n"
                    + String.format("Faturamento: R$ %.2f%n", faturamento);
        } else if (formato.equalsIgnoreCase("CSV")) {
            conteudo = "metrica,valor\n"
                    + "pedidos," + pedidos.size() + "\n"
                    + "vendas_aprovadas," + vendasAprovadas + "\n"
                    + String.format("faturamento,%.2f%n", faturamento);
        } else {
            throw new IllegalArgumentException("Formato de relatório desconhecido: " + formato);
        }

        exportador.salvar(conteudo, caminho);
        return conteudo;
    }
}
