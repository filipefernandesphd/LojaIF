package br.edu.ifsudestemg.loja.modelo;

import java.util.Objects;

public class ItemPedido {
    private final Produto produto;
    private final int quantidade;
    private final double precoUnitario;

    public ItemPedido(Produto produto, int quantidade) {
        if (quantidade <= 0) {
            throw new IllegalArgumentException("A quantidade deve ser positiva");
        }
        this.produto = Objects.requireNonNull(produto);
        this.quantidade = quantidade;
        this.precoUnitario = produto.getPreco();
    }

    public double getSubtotal() {
        return precoUnitario * quantidade;
    }

    public Produto getProduto() {
        return produto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public double getPrecoUnitario() {
        return precoUnitario;
    }
}
