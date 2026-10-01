package com.faculdade.solid.dominio;

import java.math.BigDecimal;

public class ReciboPagamento {
    private final String descricao;
    private final BigDecimal valor;

    public ReciboPagamento(String descricao, BigDecimal valor) {
        this.descricao = descricao;
        this.valor = valor;
    }

    public String getDescricao() { return descricao; }
    public BigDecimal getValor() { return valor; }
}

