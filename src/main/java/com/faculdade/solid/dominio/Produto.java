package com.faculdade.solid.dominio;

import java.math.BigDecimal;

public class Produto {
    private final String nome;
    private final BigDecimal preco;

    public Produto(String nome, BigDecimal preco) {
        if (nome == null || nome.isBlank() || preco == null || preco.signum() <= 0) {
            throw new IllegalArgumentException("Produto precisa de nome e preco positivo.");
        }
        this.nome = nome;
        this.preco = preco;
    }

    public String getNome() { return nome; }
    public BigDecimal getPreco() { return preco; }
}

