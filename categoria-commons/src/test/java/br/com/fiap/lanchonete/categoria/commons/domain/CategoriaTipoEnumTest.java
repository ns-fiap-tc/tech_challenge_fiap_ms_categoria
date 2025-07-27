package br.com.fiap.lanchonete.categoria.commons.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CategoriaTipoEnumTest {

    @Test
    void bebida_ShouldHaveCorrectProperties() {
        CategoriaTipoEnum bebida = CategoriaTipoEnum.BEBIDA;

        assertEquals(1L, bebida.getId());
        assertFalse(bebida.isCozinhar());
    }

    @Test
    void sobremesa_ShouldHaveCorrectProperties() {
        CategoriaTipoEnum sobremesa = CategoriaTipoEnum.SOBREMESA;

        assertEquals(2L, sobremesa.getId());
        assertFalse(sobremesa.isCozinhar());
    }

    @Test
    void acompanhamento_ShouldHaveCorrectProperties() {
        CategoriaTipoEnum acompanhamento = CategoriaTipoEnum.ACOMPANHAMENTO;

        assertEquals(3L, acompanhamento.getId());
        assertTrue(acompanhamento.isCozinhar());
    }

    @Test
    void lanche_ShouldHaveCorrectProperties() {
        CategoriaTipoEnum lanche = CategoriaTipoEnum.LANCHE;

        assertEquals(4L, lanche.getId());
        assertTrue(lanche.isCozinhar());
    }

    @Test
    void values_ShouldReturnAllEnumValues() {
        CategoriaTipoEnum[] values = CategoriaTipoEnum.values();

        assertEquals(4, values.length);
        assertEquals(CategoriaTipoEnum.BEBIDA, values[0]);
        assertEquals(CategoriaTipoEnum.SOBREMESA, values[1]);
        assertEquals(CategoriaTipoEnum.ACOMPANHAMENTO, values[2]);
        assertEquals(CategoriaTipoEnum.LANCHE, values[3]);
    }

    @Test
    void valueOf_ShouldReturnCorrectEnum() {
        assertEquals(CategoriaTipoEnum.BEBIDA, CategoriaTipoEnum.valueOf("BEBIDA"));
        assertEquals(CategoriaTipoEnum.SOBREMESA, CategoriaTipoEnum.valueOf("SOBREMESA"));
        assertEquals(CategoriaTipoEnum.ACOMPANHAMENTO, CategoriaTipoEnum.valueOf("ACOMPANHAMENTO"));
        assertEquals(CategoriaTipoEnum.LANCHE, CategoriaTipoEnum.valueOf("LANCHE"));
    }
}