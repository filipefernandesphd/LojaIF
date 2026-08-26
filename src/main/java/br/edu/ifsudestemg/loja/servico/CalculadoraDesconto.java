package br.edu.ifsudestemg.loja.servico;

import br.edu.ifsudestemg.loja.modelo.CupomDesconto;
import br.edu.ifsudestemg.loja.modelo.Pedido;

import java.time.LocalDate;

public class CalculadoraDesconto {
    public double calcular(Pedido pedido, CupomDesconto cupom) {
        double percentual;

        switch (pedido.getCliente().getTipo()) {
            case COMUM -> percentual = pedido.calcularSubtotal() >= 500 ? 3 : 0;
            case PREMIUM -> percentual = 10;
            case EMPRESARIAL -> percentual = pedido.calcularSubtotal() >= 1_000 ? 15 : 5;
            default -> percentual = 0;
        }

        if (cupom != null && cupom.estaValidoEm(LocalDate.now())) {
            percentual += cupom.getPercentual();
        }

        percentual = Math.min(percentual, 30);
        return pedido.calcularSubtotal() * percentual / 100.0;
    }
}
