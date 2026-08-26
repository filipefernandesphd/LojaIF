package br.edu.ifsudestemg.loja.modelo;

import java.util.Objects;

public abstract class Produto {
    private final long id;
    private final String nome;
    private final double preco;
    protected int quantidadeEmEstoque;

    protected Produto(long id, String nome, double preco, int quantidadeEmEstoque) {
        if (preco < 0 || quantidadeEmEstoque < 0) {
            throw new IllegalArgumentException("Preço e estoque não podem ser negativos");
        }
        this.id = id;
        this.nome = Objects.requireNonNull(nome);
        this.preco = preco;
        this.quantidadeEmEstoque = quantidadeEmEstoque;
    }

    public void retirarDoEstoque(int quantidade) {
        validarQuantidade(quantidade);
        if (quantidadeEmEstoque < quantidade) {
            throw new IllegalStateException("Estoque insuficiente para " + nome);
        }
        quantidadeEmEstoque -= quantidade;
    }

    public void adicionarAoEstoque(int quantidade) {
        validarQuantidade(quantidade);
        quantidadeEmEstoque += quantidade;
    }

    protected void validarQuantidade(int quantidade) {
        if (quantidade <= 0) {
            throw new IllegalArgumentException("A quantidade deve ser positiva");
        }
    }

    public abstract int getPesoEmGramas();

    public long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public double getPreco() {
        return preco;
    }

    public int getQuantidadeEmEstoque() {
        return quantidadeEmEstoque;
    }
}
