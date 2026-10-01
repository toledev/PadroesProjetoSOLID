package com.faculdade.solid.dominio;

import java.math.BigDecimal;
import java.util.List;

public class Pedido {
    private final String codigo;
    private final Cliente cliente;
    private final List<ItemPedido> itens;
    private boolean pago;

    public Pedido(String codigo, Cliente cliente, List<ItemPedido> itens) {
        if (codigo == null || codigo.isBlank() || cliente == null || itens == null || itens.isEmpty()) {
            throw new IllegalArgumentException("Pedido precisa de codigo, cliente e pelo menos um item.");
        }
        this.codigo = codigo;
        this.cliente = cliente;
        this.itens = List.copyOf(itens);
    }

    public BigDecimal getTotal() {
        return itens.stream().map(ItemPedido::getSubtotal).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public void marcarComoPago() { pago = true; }
    public String getCodigo() { return codigo; }
    public Cliente getCliente() { return cliente; }
    public boolean isPago() { return pago; }
}

