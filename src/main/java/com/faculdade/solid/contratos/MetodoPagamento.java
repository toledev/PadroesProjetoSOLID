package com.faculdade.solid.contratos;

import com.faculdade.solid.dominio.ReciboPagamento;
import java.math.BigDecimal;

public interface MetodoPagamento {
    ReciboPagamento pagar(BigDecimal valor);
}

