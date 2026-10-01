package com.faculdade.solid.servicos;

import com.faculdade.solid.contratos.CanalNotificacao;
import com.faculdade.solid.dominio.Pedido;
import com.faculdade.solid.dominio.ReciboPagamento;

public class NotificacaoService {
    private final CanalNotificacao canal;

    public NotificacaoService(CanalNotificacao canal) {
        this.canal = canal;
    }

    public void avisarPagamentoConfirmado(Pedido pedido, ReciboPagamento recibo) {
        canal.enviar(pedido.getCliente().getContato(),
                "Pedido " + pedido.getCodigo() + " confirmado. " + recibo.getDescricao()
                        + ". Total pago: R$ " + recibo.getValor());
    }
}

