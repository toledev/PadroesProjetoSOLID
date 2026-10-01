package com.faculdade.solid.infra;

import com.faculdade.solid.contratos.RepositorioPedido;
import com.faculdade.solid.dominio.Pedido;
import java.util.ArrayList;
import java.util.List;

public class RepositorioPedidoEmMemoria implements RepositorioPedido {
    private final List<Pedido> pedidos = new ArrayList<>();

    public void salvar(Pedido pedido) {
        pedidos.add(pedido);
        System.out.println("Pedido " + pedido.getCodigo() + " salvo.");
    }
}

