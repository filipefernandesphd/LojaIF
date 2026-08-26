package br.edu.ifsudestemg.loja.modelo;

public class ProdutoDigital extends Produto {
    private final String enderecoParaDownload;

    public ProdutoDigital(long id, String nome, double preco, String enderecoParaDownload) {
        super(id, nome, preco, 0);
        this.enderecoParaDownload = enderecoParaDownload;
    }

    @Override
    public void retirarDoEstoque(int quantidade) {
        validarQuantidade(quantidade);
    }

    @Override
    public void adicionarAoEstoque(int quantidade) {
        validarQuantidade(quantidade);
    }

    @Override
    public int getQuantidadeEmEstoque() {
        return Integer.MAX_VALUE;
    }

    @Override
    public int getPesoEmGramas() {
        return 0;
    }

    public String getEnderecoParaDownload() {
        return enderecoParaDownload;
    }
}
