package br.edu.ifsudestemg.loja.modelo;

import java.time.LocalDate;
import java.util.Objects;

public class CupomDesconto {
    private final String codigo;
    private final double percentual;
    private final LocalDate validade;

    public CupomDesconto(String codigo, double percentual, LocalDate validade) {
        if (percentual < 0 || percentual > 100) {
            throw new IllegalArgumentException("Percentual deve estar entre 0 e 100");
        }
        this.codigo = Objects.requireNonNull(codigo);
        this.percentual = percentual;
        this.validade = Objects.requireNonNull(validade);
    }

    public String getCodigo() {
        return codigo;
    }

    public double getPercentual() {
        return percentual;
    }

    public boolean estaValidoEm(LocalDate data) {
        return !data.isAfter(validade);
    }
}
