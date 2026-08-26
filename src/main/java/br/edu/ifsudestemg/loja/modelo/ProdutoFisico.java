package br.edu.ifsudestemg.loja.modelo;

public class ProdutoFisico extends Produto {
    private final int pesoEmGramas;

    public ProdutoFisico(long id, String nome, double preco, int quantidadeEmEstoque, int pesoEmGramas) {
        super(id, nome, preco, quantidadeEmEstoque);
        if (pesoEmGramas <= 0) {
            throw new IllegalArgumentException("O peso deve ser positivo");
        }
        this.pesoEmGramas = pesoEmGramas;
    }

    @Override
    public int getPesoEmGramas() {
        return pesoEmGramas;
    }
}
