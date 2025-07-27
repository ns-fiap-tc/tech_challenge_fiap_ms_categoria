package br.com.fiap.lanchonete.categoria.core.usecase.impl;

import br.com.fiap.lanchonete.categoria.adapter.gateway.CategoriaGateway;
import br.com.fiap.lanchonete.categoria.commons.domain.CategoriaTipoEnum;
import br.com.fiap.lanchonete.categoria.core.domain.Categoria;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CategoriaUseCasesImplTest {

    @Mock
    private CategoriaGateway gateway;

    private CategoriaUseCasesImpl useCase;

    @BeforeEach
    void setUp() {
        useCase = new CategoriaUseCasesImpl(gateway);
    }

    @Test
    void save_ShouldReturnSavedCategoria() {
        Categoria categoria = new Categoria(1L, "Bebidas", CategoriaTipoEnum.BEBIDA, new Date(), new Date());
        when(gateway.save(categoria)).thenReturn(categoria);

        Categoria result = useCase.save(categoria);

        assertEquals(categoria, result);
        verify(gateway).save(categoria);
    }

    @Test
    void findById_ShouldReturnCategoria() {
        Long id = 1L;
        Categoria categoria = new Categoria(id, "Bebidas", CategoriaTipoEnum.BEBIDA, new Date(), new Date());
        when(gateway.findById(id)).thenReturn(categoria);

        Categoria result = useCase.findById(id);

        assertEquals(categoria, result);
        verify(gateway).findById(id);
    }

    @Test
    void findByNome_ShouldReturnCategoriaList() {
        String nome = "Bebidas";
        List<Categoria> categorias = Arrays.asList(
            new Categoria(1L, nome, CategoriaTipoEnum.BEBIDA, new Date(), new Date())
        );
        when(gateway.findByNome(nome)).thenReturn(categorias);

        List<Categoria> result = useCase.findByNome(nome);

        assertEquals(categorias, result);
        verify(gateway).findByNome(nome);
    }

    @Test
    void findAll_ShouldReturnAllCategorias() {
        List<Categoria> categorias = Arrays.asList(
            new Categoria(1L, "Bebidas", CategoriaTipoEnum.BEBIDA, new Date(), new Date()),
            new Categoria(2L, "Lanches", CategoriaTipoEnum.LANCHE, new Date(), new Date())
        );
        when(gateway.findAll()).thenReturn(categorias);

        List<Categoria> result = useCase.findAll();

        assertEquals(categorias, result);
        verify(gateway).findAll();
    }

    @Test
    void deleteById_ShouldCallGatewayDelete() {
        Long id = 1L;

        useCase.deleteById(id);

        verify(gateway).deleteById(id);
    }
}