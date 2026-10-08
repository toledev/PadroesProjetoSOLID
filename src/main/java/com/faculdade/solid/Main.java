package com.faculdade.solid;

import com.faculdade.solid.contratos.RepositorioPedido;
import com.faculdade.solid.dominio.Cliente;
import com.faculdade.solid.dominio.ItemPedido;
import com.faculdade.solid.dominio.Pedido;
import com.faculdade.solid.dominio.Produto;
import com.faculdade.solid.dominio.ReciboPagamento;
import com.faculdade.solid.infra.DescontoDezPorCento;
import com.faculdade.solid.infra.Email;
import com.faculdade.solid.infra.PagamentoCartao;
import com.faculdade.solid.infra.PagamentoPix;
import com.faculdade.solid.infra.RepositorioPedidoEmMemoria;
import com.faculdade.solid.infra.WhatsApp;
import com.faculdade.solid.servicos.NotificacaoService;
import com.faculdade.solid.servicos.PedidoService;
import java.math.BigDecimal;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        RepositorioPedido repositorio = new RepositorioPedidoEmMemoria();
        PedidoService pedidoServicePix = new PedidoService(
                repositorio, new DescontoDezPorCento(), new PagamentoPix());
        PedidoService pedidoServiceCartao = new PedidoService(
                repositorio, new DescontoDezPorCento(), new PagamentoCartao());

        Produto teclado = new Produto("Teclado", new BigDecimal("100.00"));
        Produto mouse = new Produto("Mouse", new BigDecimal("50.00"));

        Pedido pedidoPix = new Pedido("PED-001", new Cliente("Ana", "11999990000"),
                List.of(new ItemPedido(teclado, 1)));
        ReciboPagamento reciboPix = pedidoServicePix.processar(pedidoPix);
        new NotificacaoService(new WhatsApp()).avisarPagamentoConfirmado(pedidoPix, reciboPix);

        Pedido pedidoCartao = new Pedido("PED-002", new Cliente("Bruno", "bruno@email.com"),
                List.of(new ItemPedido(mouse, 2)));
        ReciboPagamento reciboCartao = pedidoServiceCartao.processar(pedidoCartao);
        new NotificacaoService(new Email()).avisarPagamentoConfirmado(pedidoCartao, reciboCartao);
    }
}
