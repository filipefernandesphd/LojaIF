package br.edu.ifsudestemg.loja.entrega;

import br.edu.ifsudestemg.loja.modelo.Endereco;
import br.edu.ifsudestemg.loja.modelo.Pedido;

public class RetiradaNaLoja extends Entrega {
    private final String nomeLoja;

    public RetiradaNaLoja(Endereco enderecoDaLoja, String nomeLoja) {
        super(enderecoDaLoja);
        this.nomeLoja = nomeLoja;
    }

    @Override
    public void atualizarEndereco(Endereco novoEndereco) {
        System.out.println("O endereço de uma retirada é definido pela loja escolhida.");
    }

    @Override
    public int calcularPrazoEmDias() {
        return 0;
    }

    @Override
    public String despachar(Pedido pedido) {
        return "RETIRADA-" + pedido.getId();
    }

    @Override
    public String rastrear(String codigoRastreio) {
        return "Pedido disponível no balcão da " + nomeLoja;
    }
}
