package br.edu.ifsudestemg.loja.entrega;

import br.edu.ifsudestemg.loja.modelo.Endereco;
import br.edu.ifsudestemg.loja.modelo.Pedido;

public class EntregaTransportadora extends Entrega {
    private final String transportadora;

    public EntregaTransportadora(Endereco endereco, String transportadora) {
        super(endereco);
        this.transportadora = transportadora;
    }

    @Override
    public int calcularPrazoEmDias() {
        return endereco.getEstado().equals("MG") ? 3 : 7;
    }

    @Override
    public String despachar(Pedido pedido) {
        return transportadora.toUpperCase() + "-" + pedido.getId();
    }

    @Override
    public String rastrear(String codigoRastreio) {
        return "Objeto " + codigoRastreio + " em transporte para " + endereco.getCidade();
    }
}
