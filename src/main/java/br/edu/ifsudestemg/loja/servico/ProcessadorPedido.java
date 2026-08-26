package br.edu.ifsudestemg.loja.servico;

import br.edu.ifsudestemg.loja.entrega.CalculadoraFrete;
import br.edu.ifsudestemg.loja.entrega.Entrega;
import br.edu.ifsudestemg.loja.modelo.CupomDesconto;
import br.edu.ifsudestemg.loja.modelo.ItemPedido;
import br.edu.ifsudestemg.loja.modelo.Pedido;
import br.edu.ifsudestemg.loja.notificacao.NotificadorEmail;
import br.edu.ifsudestemg.loja.pagamento.MeioPagamento;
import br.edu.ifsudestemg.loja.pagamento.ResultadoPagamento;
import br.edu.ifsudestemg.loja.repositorio.RepositorioPedidosMySql;

public class ProcessadorPedido {
    private final CalculadoraDesconto calculadoraDesconto = new CalculadoraDesconto();
    private final CalculadoraFrete calculadoraFrete = new CalculadoraFrete();
    private final ServicoEstoque servicoEstoque = new ServicoEstoque();
    private final RepositorioPedidosMySql repositorio = new RepositorioPedidosMySql();
    private final NotificadorEmail notificador = new NotificadorEmail();

    public ResultadoPagamento processar(Pedido pedido, CupomDesconto cupom, String modalidadeFrete,
                                        MeioPagamento meioPagamento, Entrega entrega) {
        if (pedido.getItens().isEmpty()) {
            throw new IllegalStateException("O pedido precisa possuir ao menos um item");
        }

        pedido.setDesconto(calculadoraDesconto.calcular(pedido, cupom));
        pedido.setFrete(calculadoraFrete.calcular(pedido, modalidadeFrete));
        servicoEstoque.reservarItens(pedido);

        ResultadoPagamento resultado = meioPagamento.pagar(pedido);
        if (!resultado.isAprovado()) {
            servicoEstoque.devolverItens(pedido);
            pedido.marcarPagamentoRecusado();
            repositorio.salvar(pedido);
            notificador.enviar(pedido.getCliente(), "Pagamento recusado", resultado.getMensagem());
            imprimirComprovante(pedido, resultado);
            return resultado;
        }

        pedido.marcarComoPago();
        String codigoRastreio = entrega.despachar(pedido);
        pedido.marcarComoEnviado(codigoRastreio);
        repositorio.salvar(pedido);
        notificador.enviar(pedido.getCliente(), "Pedido confirmado",
                "Total: R$ " + String.format("%.2f", pedido.calcularTotal())
                        + ". Código: " + codigoRastreio);
        imprimirComprovante(pedido, resultado);
        return resultado;
    }

    private void imprimirComprovante(Pedido pedido, ResultadoPagamento resultado) {
        System.out.println("\n=== COMPROVANTE DO PEDIDO " + pedido.getId() + " ===");
        for (ItemPedido item : pedido.getItens()) {
            System.out.printf("%dx %s: R$ %.2f%n", item.getQuantidade(), item.getProduto().getNome(),
                    item.getSubtotal());
        }
        System.out.printf("Subtotal: R$ %.2f%n", pedido.calcularSubtotal());
        System.out.printf("Desconto: R$ %.2f%n", pedido.getDesconto());
        System.out.printf("Frete: R$ %.2f%n", pedido.getFrete());
        System.out.printf("Total: R$ %.2f%n", pedido.calcularTotal());
        System.out.println("Pagamento: " + resultado.getMensagem());
        System.out.println("Status: " + pedido.getStatus());
    }
}
