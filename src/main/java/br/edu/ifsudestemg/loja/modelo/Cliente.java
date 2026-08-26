package br.edu.ifsudestemg.loja.modelo;

import java.util.Objects;

public class Cliente {
    private final long id;
    private final String nome;
    private final String email;
    private final String telefone;
    private final TipoCliente tipo;

    public Cliente(long id, String nome, String email, String telefone, TipoCliente tipo) {
        this.id = id;
        this.nome = Objects.requireNonNull(nome);
        this.email = Objects.requireNonNull(email);
        this.telefone = Objects.requireNonNull(telefone);
        this.tipo = Objects.requireNonNull(tipo);
    }

    public long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getTelefone() {
        return telefone;
    }

    public TipoCliente getTipo() {
        return tipo;
    }
}
