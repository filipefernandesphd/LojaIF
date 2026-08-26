package br.edu.ifsudestemg.loja.entrega;

import br.edu.ifsudestemg.loja.modelo.Pedido;

public class CalculadoraFrete {
    public double calcular(Pedido pedido, String modalidade) {
        int pesoTotal = pedido.getItens().stream()
                .mapToInt(item -> item.getProduto().getPesoEmGramas() * item.getQuantidade())
                .sum();

        if (modalidade.equalsIgnoreCase("RETIRADA")) {
            return 0;
        } else if (modalidade.equalsIgnoreCase("PADRAO")) {
            double base = pedido.getEnderecoEntrega().getEstado().equals("MG") ? 12 : 22;
            return base + pesoTotal / 1000.0 * 2;
        } else if (modalidade.equalsIgnoreCase("EXPRESSA")) {
            double base = pedido.getEnderecoEntrega().getEstado().equals("MG") ? 25 : 45;
            return base + pesoTotal / 1000.0 * 4;
        }
        throw new IllegalArgumentException("Modalidade de frete desconhecida: " + modalidade);
    }
}
