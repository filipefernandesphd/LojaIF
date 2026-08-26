package br.edu.ifsudestemg.loja.servico;

import br.edu.ifsudestemg.loja.ApoioTeste;
import br.edu.ifsudestemg.loja.modelo.CupomDesconto;
import br.edu.ifsudestemg.loja.modelo.Pedido;
import br.edu.ifsudestemg.loja.modelo.TipoCliente;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CalculadoraDescontoTest {
    private final CalculadoraDesconto calculadora = new CalculadoraDesconto();

    @Test
    void concedeDezPorCentoParaClientePremium() {
        Pedido pedido = ApoioTeste.pedido(TipoCliente.PREMIUM, 200, 2);

        assertEquals(40, calculadora.calcular(pedido, null), 0.001);
    }

    @Test
    void acumulaCupomValidoAteOLimitePermitido() {
        Pedido pedido = ApoioTeste.pedido(TipoCliente.PREMIUM, 200, 2);
        CupomDesconto cupom = new CupomDesconto("PROMO20", 20, LocalDate.now());

        assertEquals(120, calculadora.calcular(pedido, cupom), 0.001);
    }

    @Test
    void ignoraCupomVencido() {
        Pedido pedido = ApoioTeste.pedido(TipoCliente.COMUM, 100, 1);
        CupomDesconto cupom = new CupomDesconto("ANTIGO", 20, LocalDate.now().minusDays(1));

        assertEquals(0, calculadora.calcular(pedido, cupom), 0.001);
    }
}
