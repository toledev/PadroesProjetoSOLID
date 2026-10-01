package com.faculdade.solid.infra;

import com.faculdade.solid.contratos.CanalNotificacao;

public class Email implements CanalNotificacao {
    public void enviar(String destino, String mensagem) {
        System.out.println("E-mail para " + destino + ": " + mensagem);
    }
}

