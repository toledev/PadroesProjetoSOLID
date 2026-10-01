package com.faculdade.solid.infra;

import com.faculdade.solid.contratos.CanalNotificacao;

public class WhatsApp implements CanalNotificacao {
    public void enviar(String destino, String mensagem) {
        System.out.println("WhatsApp para " + destino + ": " + mensagem);
    }
}

