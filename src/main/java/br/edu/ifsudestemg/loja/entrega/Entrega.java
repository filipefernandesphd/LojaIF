package br.edu.ifsudestemg.loja.entrega;

import br.edu.ifsudestemg.loja.modelo.Endereco;
import br.edu.ifsudestemg.loja.modelo.Pedido;

import java.util.Objects;

public abstract class Entrega {
    protected Endereco endereco;

    protected Entrega(Endereco endereco) {
        this.endereco = Objects.requireNonNull(endereco);
    }

    public void atualizarEndereco(Endereco novoEndereco) {
        this.endereco = Objects.requireNonNull(novoEndereco);
    }

    public abstract int calcularPrazoEmDias();

    public abstract String despachar(Pedido pedido);

    public abstract String rastrear(String codigoRastreio);

    public Endereco getEndereco() {
        return endereco;
    }
}
