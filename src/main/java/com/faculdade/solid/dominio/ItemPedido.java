package com.faculdade.solid.dominio;

import java.math.BigDecimal;

public class ItemPedido {
    private final Produto produto;
    private final int quantidade;

    public ItemPedido(Produto produto, int quantidade) {
        if (produto == null || quantidade <= 0) {
            throw new IllegalArgumentException("Produto e quantidade positiva sao obrigatorios.");
        }
        this.produto = produto;
        this.quantidade = quantidade;
    }

    public BigDecimal getSubtotal() {
        return produto.getPreco().multiply(BigDecimal.valueOf(quantidade));
    }
}

