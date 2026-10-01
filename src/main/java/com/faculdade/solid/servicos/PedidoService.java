package com.faculdade.solid.servicos;

import com.faculdade.solid.contratos.MetodoPagamento;
import com.faculdade.solid.contratos.PoliticaDesconto;
import com.faculdade.solid.contratos.RepositorioPedido;
import com.faculdade.solid.dominio.Pedido;
import com.faculdade.solid.dominio.ReciboPagamento;

public class PedidoService {
    private final RepositorioPedido repositorio;
    private final PoliticaDesconto politicaDesconto;

    public PedidoService(RepositorioPedido repositorio, PoliticaDesconto politicaDesconto) {
        this.repositorio = repositorio;
        this.politicaDesconto = politicaDesconto;
    }

    public ReciboPagamento processar(Pedido pedido, MetodoPagamento pagamento) {
        ReciboPagamento recibo = pagamento.pagar(politicaDesconto.aplicar(pedido.getTotal()));
        pedido.marcarComoPago();
        repositorio.salvar(pedido);
        return recibo;
    }
}

