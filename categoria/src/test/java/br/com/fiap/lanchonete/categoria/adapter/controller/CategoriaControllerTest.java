package br.com.fiap.lanchonete.categoria.adapter.controller;

import br.com.fiap.lanchonete.categoria.commons.dto.CategoriaDto;
import br.com.fiap.lanchonete.categoria.commons.domain.CategoriaTipoEnum;
import br.com.fiap.lanchonete.categoria.commons.persistence.CategoriaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CategoriaControllerTest {

    @Mock
    private CategoriaRepository repository;

    private CategoriaController controller;

    @BeforeEach
    void setUp() {
        controller = new CategoriaController(repository);
    }

    @Test
    void create_ShouldReturnCreatedCategoria() {
        CategoriaDto inputDto = new CategoriaDto(null, "Bebidas", CategoriaTipoEnum.BEBIDA, null, null);
        CategoriaDto savedDto = new CategoriaDto(1L, "Bebidas", CategoriaTipoEnum.BEBIDA, new Date(), new Date());
        
        when(repository.save(any(CategoriaDto.class))).thenReturn(savedDto);

        CategoriaDto result = controller.create(inputDto);

        assertNotNull(result.getId());
        assertEquals("Bebidas", result.getNome());
        assertEquals(CategoriaTipoEnum.BEBIDA, result.getTipo());
        verify(repository).save(any(CategoriaDto.class));
    }

    @Test
    void findById_ShouldReturnCategoria() {
        Long id = 1L;
        CategoriaDto dto = new CategoriaDto(id, "Bebidas", CategoriaTipoEnum.BEBIDA, new Date(), new Date());
        when(repository.findById(id)).thenReturn(dto);

        CategoriaDto result = controller.findById(id);

        assertEquals(id, result.getId());
        assertEquals("Bebidas", result.getNome());
        verify(repository).findById(id);
    }

    @Test
    void findByNome_ShouldReturnCategoriaList() {
        String nome = "Bebidas";
        List<CategoriaDto> dtos = Arrays.asList(
            new CategoriaDto(1L, nome, CategoriaTipoEnum.BEBIDA, new Date(), new Date())
        );
        when(repository.findByNome(nome)).thenReturn(dtos);

        List<CategoriaDto> result = controller.findByNome(nome);

        assertEquals(1, result.size());
        assertEquals(nome, result.get(0).getNome());
        verify(repository).findByNome(nome);
    }

    @Test
    void findAll_ShouldReturnAllCategorias() {
        List<CategoriaDto> dtos = Arrays.asList(
            new CategoriaDto(1L, "Bebidas", CategoriaTipoEnum.BEBIDA, new Date(), new Date()),
            new CategoriaDto(2L, "Lanches", CategoriaTipoEnum.LANCHE, new Date(), new Date())
        );
        when(repository.findAll()).thenReturn(dtos);

        List<CategoriaDto> result = controller.findAll();

        assertEquals(2, result.size());
        verify(repository).findAll();
    }

    @Test
    void update_ShouldReturnUpdatedCategoria() {
        CategoriaDto inputDto = new CategoriaDto(1L, "Bebidas Atualizadas", CategoriaTipoEnum.BEBIDA, null, null);
        CategoriaDto updatedDto = new CategoriaDto(1L, "Bebidas Atualizadas", CategoriaTipoEnum.BEBIDA, new Date(), new Date());
        
        when(repository.save(any(CategoriaDto.class))).thenReturn(updatedDto);

        CategoriaDto result = controller.update(inputDto);

        assertEquals(1L, result.getId());
        assertEquals("Bebidas Atualizadas", result.getNome());
        verify(repository).save(any(CategoriaDto.class));
    }

    @Test
    void deleteById_ShouldCallRepositoryDelete() {
        Long id = 1L;

        controller.deleteById(id);

        verify(repository).deleteById(id);
    }
}