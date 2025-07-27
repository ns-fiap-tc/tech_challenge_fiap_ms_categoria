package br.com.fiap.lanchonete.categoria.core.domain;

import br.com.fiap.lanchonete.categoria.commons.domain.CategoriaTipoEnum;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class CategoriaTest {

    @Test
    void constructor_WithAllParameters_ShouldCreateCategoria() {
        Long id = 1L;
        String nome = "Bebidas";
        CategoriaTipoEnum tipo = CategoriaTipoEnum.BEBIDA;
        Date createdAt = new Date();
        Date updatedAt = new Date();

        Categoria categoria = new Categoria(id, nome, tipo, createdAt, updatedAt);

        assertEquals(id, categoria.getId());
        assertEquals(nome, categoria.getNome());
        assertEquals(tipo, categoria.getTipo());
        assertEquals(createdAt, categoria.getCreatedAt());
        assertEquals(updatedAt, categoria.getUpdatedAt());
    }

    @Test
    void constructor_NoArgs_ShouldCreateEmptyCategoria() {
        Categoria categoria = new Categoria();

        assertNull(categoria.getId());
        assertNull(categoria.getNome());
        assertNull(categoria.getTipo());
        assertNull(categoria.getCreatedAt());
        assertNull(categoria.getUpdatedAt());
    }

    @Test
    void setters_ShouldUpdateFields() {
        Categoria categoria = new Categoria();
        Long id = 1L;
        String nome = "Lanches";
        CategoriaTipoEnum tipo = CategoriaTipoEnum.LANCHE;
        Date createdAt = new Date();
        Date updatedAt = new Date();

        categoria.setId(id);
        categoria.setNome(nome);
        categoria.setTipo(tipo);
        categoria.setCreatedAt(createdAt);
        categoria.setUpdatedAt(updatedAt);

        assertEquals(id, categoria.getId());
        assertEquals(nome, categoria.getNome());
        assertEquals(tipo, categoria.getTipo());
        assertEquals(createdAt, categoria.getCreatedAt());
        assertEquals(updatedAt, categoria.getUpdatedAt());
    }

    @Test
    void equals_SameObject_ShouldReturnTrue() {
        Categoria categoria = new Categoria(1L, "Bebidas", CategoriaTipoEnum.BEBIDA, new Date(), new Date());

        assertEquals(categoria, categoria);
    }

    /*@Test
    void toString_ShouldContainAllFields() {
        Date now = new Date();
        Categoria categoria = new Categoria(1L, "Bebidas", CategoriaTipoEnum.BEBIDA, now, now);

        String result = categoria.toString();

        assertTrue(result.contains("1"));
        assertTrue(result.contains("Bebidas"));
        assertTrue(result.contains("BEBIDA"));
    }*/
}