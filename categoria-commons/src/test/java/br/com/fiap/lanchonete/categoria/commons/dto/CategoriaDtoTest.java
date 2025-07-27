package br.com.fiap.lanchonete.categoria.commons.dto;

import br.com.fiap.lanchonete.categoria.commons.domain.CategoriaTipoEnum;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class CategoriaDtoTest {

    @Test
    void constructor_WithAllParameters_ShouldCreateCategoriaDto() {
        Long id = 1L;
        String nome = "Bebidas";
        CategoriaTipoEnum tipo = CategoriaTipoEnum.BEBIDA;
        Date createdAt = new Date();
        Date updatedAt = new Date();

        CategoriaDto dto = new CategoriaDto(id, nome, tipo, createdAt, updatedAt);

        assertEquals(id, dto.getId());
        assertEquals(nome, dto.getNome());
        assertEquals(tipo, dto.getTipo());
        assertEquals(createdAt, dto.getCreatedAt());
        assertEquals(updatedAt, dto.getUpdatedAt());
    }

    @Test
    void constructor_NoArgs_ShouldCreateEmptyCategoriaDto() {
        CategoriaDto dto = new CategoriaDto();

        assertNull(dto.getId());
        assertNull(dto.getNome());
        assertNull(dto.getTipo());
        assertNull(dto.getCreatedAt());
        assertNull(dto.getUpdatedAt());
    }

    @Test
    void setters_ShouldUpdateFields() {
        CategoriaDto dto = new CategoriaDto();
        Long id = 1L;
        String nome = "Lanches";
        CategoriaTipoEnum tipo = CategoriaTipoEnum.LANCHE;
        Date createdAt = new Date();
        Date updatedAt = new Date();

        dto.setId(id);
        dto.setNome(nome);
        dto.setTipo(tipo);
        dto.setCreatedAt(createdAt);
        dto.setUpdatedAt(updatedAt);

        assertEquals(id, dto.getId());
        assertEquals(nome, dto.getNome());
        assertEquals(tipo, dto.getTipo());
        assertEquals(createdAt, dto.getCreatedAt());
        assertEquals(updatedAt, dto.getUpdatedAt());
    }

    @Test
    void equals_SameObject_ShouldReturnTrue() {
        CategoriaDto dto = new CategoriaDto(1L, "Bebidas", CategoriaTipoEnum.BEBIDA, new Date(), new Date());

        assertEquals(dto, dto);
    }
}