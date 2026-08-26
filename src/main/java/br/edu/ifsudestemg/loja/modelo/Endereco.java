package br.edu.ifsudestemg.loja.modelo;

import java.util.Objects;

public class Endereco {
    private final String logradouro;
    private final String cidade;
    private final String estado;
    private final String cep;

    public Endereco(String logradouro, String cidade, String estado, String cep) {
        this.logradouro = Objects.requireNonNull(logradouro);
        this.cidade = Objects.requireNonNull(cidade);
        this.estado = Objects.requireNonNull(estado);
        this.cep = Objects.requireNonNull(cep);
    }

    public String getLogradouro() {
        return logradouro;
    }

    public String getCidade() {
        return cidade;
    }

    public String getEstado() {
        return estado;
    }

    public String getCep() {
        return cep;
    }

    @Override
    public String toString() {
        return logradouro + ", " + cidade + "/" + estado + " - " + cep;
    }
}
