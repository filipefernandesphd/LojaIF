package br.edu.ifsudestemg.loja;

import br.edu.ifsudestemg.loja.entrega.EntregaTransportadora;
import br.edu.ifsudestemg.loja.entrega.RetiradaNaLoja;
import br.edu.ifsudestemg.loja.modelo.Cliente;
import br.edu.ifsudestemg.loja.modelo.CupomDesconto;
import br.edu.ifsudestemg.loja.modelo.Endereco;
import br.edu.ifsudestemg.loja.modelo.Pedido;
import br.edu.ifsudestemg.loja.modelo.ProdutoDigital;
import br.edu.ifsudestemg.loja.modelo.ProdutoFisico;
import br.edu.ifsudestemg.loja.modelo.TipoCliente;
import br.edu.ifsudestemg.loja.notificacao.NotificadorSms;
import br.edu.ifsudestemg.loja.pagamento.CartaoCredito;
import br.edu.ifsudestemg.loja.pagamento.Pix;
import br.edu.ifsudestemg.loja.relatorio.GeradorRelatorioVendas;
import br.edu.ifsudestemg.loja.repositorio.RepositorioPedidosMySql;
import br.edu.ifsudestemg.loja.servico.ProcessadorPedido;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        RepositorioPedidosMySql.limpar();
        System.out.println("=== LOJA IF ===");

        ProdutoFisico teclado = new ProdutoFisico(1, "Teclado mecânico", 350, 8, 900);
        ProdutoFisico livro = new ProdutoFisico(2, "Livro de Engenharia de Software", 120, 15, 600);
        ProdutoDigital curso = new ProdutoDigital(3, "Curso de testes automatizados", 180,
                "https://loja.exemplo/cursos/testes");
        System.out.println("Produtos cadastrados: teclado, livro e curso digital.");

        Cliente ana = new Cliente(1, "Ana Souza", "ana@exemplo.com", "32999990001", TipoCliente.PREMIUM);
        Cliente bruno = new Cliente(2, "Bruno Lima", "bruno@exemplo.com", "32999990002", TipoCliente.COMUM);
        Endereco enderecoAna = new Endereco("Rua das Flores, 10", "Juiz de Fora", "MG", "36000-000");
        Endereco enderecoLoja = new Endereco("Av. Central, 500", "Juiz de Fora", "MG", "36010-000");
        ProcessadorPedido processador = new ProcessadorPedido();

        Pedido pedidoEntrega = new Pedido(1001, ana, enderecoAna);
        pedidoEntrega.adicionarItem(teclado, 1);
        pedidoEntrega.adicionarItem(curso, 1);
        CupomDesconto cupom = new CupomDesconto("AULA5", 5, LocalDate.now().plusDays(7));
        EntregaTransportadora entrega = new EntregaTransportadora(enderecoAna, "Rápido Sul");
        processador.processar(pedidoEntrega, cupom, "PADRAO",
                new CartaoCredito("1234", 2_000), entrega);
        System.out.println("Rastreamento: " + entrega.rastrear(pedidoEntrega.getCodigoRastreio()));

        Pedido pedidoRetirada = new Pedido(1002, bruno, enderecoLoja);
        pedidoRetirada.adicionarItem(livro, 2);
        RetiradaNaLoja retirada = new RetiradaNaLoja(enderecoLoja, "Loja Centro");
        processador.processar(pedidoRetirada, null, "RETIRADA",
                new Pix("bruno@pix", 500), retirada);
        new NotificadorSms().enviar(bruno, "Retirada liberada", retirada.rastrear(pedidoRetirada.getCodigoRastreio()));

        String relatorio = new GeradorRelatorioVendas().gerar("TEXTO", "target/relatorio-vendas.txt");
        System.out.println("\n" + relatorio);
        System.out.println("Estoque final: teclado=" + teclado.getQuantidadeEmEstoque()
                + ", livro=" + livro.getQuantidadeEmEstoque());
    }
}
