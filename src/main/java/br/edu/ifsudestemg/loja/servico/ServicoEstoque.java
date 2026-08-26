package br.edu.ifsudestemg.loja.servico;

import br.edu.ifsudestemg.loja.modelo.ItemPedido;
import br.edu.ifsudestemg.loja.modelo.Pedido;
import br.edu.ifsudestemg.loja.repositorio.RepositorioProdutosArquivo;

public class ServicoEstoque {
    private final RepositorioProdutosArquivo repositorio = new RepositorioProdutosArquivo();

    public void reservarItens(Pedido pedido) {
        for (ItemPedido item : pedido.getItens()) {
            if (item.getProduto().getQuantidadeEmEstoque() < item.getQuantidade()) {
                throw new IllegalStateException("Estoque insuficiente para " + item.getProduto().getNome());
            }
        }
        for (ItemPedido item : pedido.getItens()) {
            item.getProduto().retirarDoEstoque(item.getQuantidade());
            repositorio.registrarEstoque(item.getProduto());
        }
    }

    public void devolverItens(Pedido pedido) {
        for (ItemPedido item : pedido.getItens()) {
            item.getProduto().adicionarAoEstoque(item.getQuantidade());
            repositorio.registrarEstoque(item.getProduto());
        }
    }
}
