package br.edu.ifsudestemg.loja.repositorio;

import br.edu.ifsudestemg.loja.modelo.Produto;

import java.util.LinkedHashMap;
import java.util.Map;

public class RepositorioProdutosArquivo {
    private final Map<Long, Integer> registros = new LinkedHashMap<>();

    public void registrarEstoque(Produto produto) {
        registros.put(produto.getId(), produto.getQuantidadeEmEstoque());
        System.out.println("Estoque do produto " + produto.getId() + " registrado em arquivo.");
    }

    public Integer consultarQuantidade(long produtoId) {
        return registros.get(produtoId);
    }
}
