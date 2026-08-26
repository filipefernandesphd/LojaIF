package br.edu.ifsudestemg.loja.repositorio;

import br.edu.ifsudestemg.loja.modelo.Pedido;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class RepositorioPedidosMySql {
    private static final Map<Long, Pedido> TABELA_PEDIDOS = new LinkedHashMap<>();

    public void salvar(Pedido pedido) {
        TABELA_PEDIDOS.put(pedido.getId(), pedido);
        System.out.println("Pedido " + pedido.getId() + " salvo na tabela pedidos.");
    }

    public Pedido buscarPorId(long id) {
        return TABELA_PEDIDOS.get(id);
    }

    public List<Pedido> buscarTodos() {
        return new ArrayList<>(TABELA_PEDIDOS.values());
    }

    public static void limpar() {
        TABELA_PEDIDOS.clear();
    }
}
