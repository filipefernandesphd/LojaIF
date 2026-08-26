package br.edu.ifsudestemg.loja.modelo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Pedido {
    private final long id;
    private final Cliente cliente;
    private final Endereco enderecoEntrega;
    private final LocalDateTime criadoEm;
    private final List<ItemPedido> itens = new ArrayList<>();
    private StatusPedido status = StatusPedido.ABERTO;
    private double desconto;
    private double frete;
    private String codigoRastreio;

    public Pedido(long id, Cliente cliente, Endereco enderecoEntrega) {
        this.id = id;
        this.cliente = Objects.requireNonNull(cliente);
        this.enderecoEntrega = Objects.requireNonNull(enderecoEntrega);
        this.criadoEm = LocalDateTime.now();
    }

    public void adicionarItem(Produto produto, int quantidade) {
        if (status != StatusPedido.ABERTO) {
            throw new IllegalStateException("Não é possível alterar um pedido processado");
        }
        itens.add(new ItemPedido(produto, quantidade));
    }

    public double calcularSubtotal() {
        return itens.stream().mapToDouble(ItemPedido::getSubtotal).sum();
    }

    public double calcularTotal() {
        return Math.max(0, calcularSubtotal() - desconto + frete);
    }

    public void marcarComoPago() {
        status = StatusPedido.PAGO;
    }

    public void marcarPagamentoRecusado() {
        status = StatusPedido.PAGAMENTO_RECUSADO;
    }

    public void marcarComoEnviado(String codigoRastreio) {
        if (status != StatusPedido.PAGO) {
            throw new IllegalStateException("Somente pedidos pagos podem ser enviados");
        }
        this.codigoRastreio = codigoRastreio;
        status = StatusPedido.ENVIADO;
    }

    public long getId() { return id; }
    public Cliente getCliente() { return cliente; }
    public Endereco getEnderecoEntrega() { return enderecoEntrega; }
    public LocalDateTime getCriadoEm() { return criadoEm; }
    public List<ItemPedido> getItens() { return Collections.unmodifiableList(itens); }
    public StatusPedido getStatus() { return status; }
    public double getDesconto() { return desconto; }
    public void setDesconto(double desconto) { this.desconto = Math.max(0, desconto); }
    public double getFrete() { return frete; }
    public void setFrete(double frete) { this.frete = Math.max(0, frete); }
    public String getCodigoRastreio() { return codigoRastreio; }
}
