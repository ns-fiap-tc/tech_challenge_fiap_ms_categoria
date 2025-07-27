package br.com.fiap.lanchonete.categoria.adapter.presenter;

import br.com.fiap.lanchonete.categoria.commons.dto.CategoriaDto;
import br.com.fiap.lanchonete.categoria.commons.domain.CategoriaTipoEnum;
import br.com.fiap.lanchonete.categoria.core.domain.Categoria;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CategoriaPresenterTest {

    private CategoriaPresenter presenter;

    @BeforeEach
    void setUp() {
        presenter = new CategoriaPresenter();
    }

    @Test
    void toDto_ShouldConvertCategoriaToCategoriaDto() {
        Date now = new Date();
        Categoria categoria = new Categoria(1L, "Bebidas", CategoriaTipoEnum.BEBIDA, now, now);

        CategoriaDto result = presenter.toDto(categoria);

        assertEquals(1L, result.getId());
        assertEquals("Bebidas", result.getNome());
        assertEquals(CategoriaTipoEnum.BEBIDA, result.getTipo());
        assertEquals(now, result.getCreatedAt());
        assertEquals(now, result.getUpdatedAt());
    }

    @Test
    void toDomain_ShouldConvertCategoriaDtoToCategoria() {
        Date now = new Date();
        CategoriaDto dto = new CategoriaDto(1L, "Bebidas", CategoriaTipoEnum.BEBIDA, now, now);

        Categoria result = presenter.toDomain(dto);

        assertEquals(1L, result.getId());
        assertEquals("Bebidas", result.getNome());
        assertEquals(CategoriaTipoEnum.BEBIDA, result.getTipo());
        assertEquals(now, result.getCreatedAt());
        assertEquals(now, result.getUpdatedAt());
    }

    @Test
    void map_ShouldConvertCategoriaListToDtoList() {
        Date now = new Date();
        List<Categoria> categorias = Arrays.asList(
            new Categoria(1L, "Bebidas", CategoriaTipoEnum.BEBIDA, now, now),
            new Categoria(2L, "Lanches", CategoriaTipoEnum.LANCHE, now, now)
        );

        List<CategoriaDto> result = presenter.map(categorias);

        assertEquals(2, result.size());
        assertEquals("Bebidas", result.get(0).getNome());
        assertEquals("Lanches", result.get(1).getNome());
    }

    @Test
    void toDto_WithNullCategoria_ShouldReturnNull() {
        CategoriaDto result = presenter.toDto(null);
        assertNull(result);
    }

    @Test
    void toDomain_WithNullDto_ShouldReturnNull() {
        Categoria result = presenter.toDomain(null);
        assertNull(result);
    }
}