package com.faculdade.solid.infra;

import com.faculdade.solid.contratos.MetodoPagamento;
import com.faculdade.solid.dominio.ReciboPagamento;
import java.math.BigDecimal;

public class PagamentoCartao implements MetodoPagamento {
    public ReciboPagamento pagar(BigDecimal valor) {
        return new ReciboPagamento("Pagamento aprovado via cartao", valor);
    }
}

