package com.faculdade.solid.dominio;

public class Cliente {
    private final String nome;
    private final String contato;

    public Cliente(String nome, String contato) {
        if (nome == null || nome.isBlank() || contato == null || contato.isBlank()) {
            throw new IllegalArgumentException("Nome e contato sao obrigatorios.");
        }
        this.nome = nome;
        this.contato = contato;
    }

    public String getNome() { return nome; }
    public String getContato() { return contato; }
}

