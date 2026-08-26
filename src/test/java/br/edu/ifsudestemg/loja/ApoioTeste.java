package br.edu.ifsudestemg.loja;

import br.edu.ifsudestemg.loja.modelo.Cliente;
import br.edu.ifsudestemg.loja.modelo.Endereco;
import br.edu.ifsudestemg.loja.modelo.Pedido;
import br.edu.ifsudestemg.loja.modelo.ProdutoFisico;
import br.edu.ifsudestemg.loja.modelo.TipoCliente;

public final class ApoioTeste {
    private ApoioTeste() {
    }

    public static Cliente cliente(TipoCliente tipo) {
        return new Cliente(1, "Cliente Teste", "cliente@teste.com", "32999999999", tipo);
    }

    public static Endereco endereco(String estado) {
        return new Endereco("Rua de Teste, 10", "Cidade", estado, "36000-000");
    }

    public static Pedido pedido(TipoCliente tipo, double preco, int quantidade) {
        Pedido pedido = new Pedido(10, cliente(tipo), endereco("MG"));
        pedido.adicionarItem(new ProdutoFisico(1, "Produto", preco, 50, 1_000), quantidade);
        return pedido;
    }
}
