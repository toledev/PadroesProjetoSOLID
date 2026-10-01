package com.faculdade.solid.contratos;

import java.math.BigDecimal;

public interface PoliticaDesconto {
    BigDecimal aplicar(BigDecimal valorOriginal);
}

