package com.faculdade.solid.infra;

import com.faculdade.solid.contratos.PoliticaDesconto;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class DescontoDezPorCento implements PoliticaDesconto {
    public BigDecimal aplicar(BigDecimal valorOriginal) {
        return valorOriginal.multiply(new BigDecimal("0.90")).setScale(2, RoundingMode.HALF_UP);
    }
}

