package br.com.fiap.lanchonete.categoria.adapter.gateway;

import br.com.fiap.lanchonete.categoria.commons.dto.CategoriaDto;
import br.com.fiap.lanchonete.categoria.commons.domain.CategoriaTipoEnum;
import br.com.fiap.lanchonete.categoria.commons.persistence.CategoriaRepository;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CategoriaGatewayTest {

    @Mock
    private CategoriaRepository repository;

    private CategoriaGateway gateway;

    @BeforeEach
    void setUp() {
        gateway = new CategoriaGateway(repository);
    }

    @Test
    void save_NewCategoria_ShouldSetCreatedAtAndUpdatedAt() {
        Categoria categoria = new Categoria(null, "Bebidas", CategoriaTipoEnum.BEBIDA, null, null);
        CategoriaDto dto = new CategoriaDto(1L, "Bebidas", CategoriaTipoEnum.BEBIDA, new Date(), new Date());
        
        when(repository.save(any(CategoriaDto.class))).thenReturn(dto);

        Categoria result = gateway.save(categoria);

        assertNotNull(result.getCreatedAt());
        assertNotNull(result.getUpdatedAt());
        verify(repository).save(any(CategoriaDto.class));
    }

    @Test
    void save_ExistingCategoria_ShouldOnlyUpdateUpdatedAt() {
        Date createdAt = new Date(System.currentTimeMillis() - 10000);
        Categoria categoria = new Categoria(1L, "Bebidas", CategoriaTipoEnum.BEBIDA, createdAt, null);
        CategoriaDto dto = new CategoriaDto(1L, "Bebidas", CategoriaTipoEnum.BEBIDA, createdAt, new Date());
        
        when(repository.save(any(CategoriaDto.class))).thenReturn(dto);

        Categoria result = gateway.save(categoria);

        assertNotNull(result.getUpdatedAt());
        verify(repository).save(any(CategoriaDto.class));
    }

    @Test
    void findById_ShouldReturnCategoria() {
        Long id = 1L;
        CategoriaDto dto = new CategoriaDto(id, "Bebidas", CategoriaTipoEnum.BEBIDA, new Date(), new Date());
        when(repository.findById(id)).thenReturn(dto);

        Categoria result = gateway.findById(id);

        assertEquals(id, result.getId());
        assertEquals("Bebidas", result.getNome());
        assertEquals(CategoriaTipoEnum.BEBIDA, result.getTipo());
        verify(repository).findById(id);
    }

    @Test
    void findByNome_ShouldReturnCategoriaList() {
        String nome = "Bebidas";
        List<CategoriaDto> dtos = Arrays.asList(
            new CategoriaDto(1L, nome, CategoriaTipoEnum.BEBIDA, new Date(), new Date())
        );
        when(repository.findByNome(nome)).thenReturn(dtos);

        List<Categoria> result = gateway.findByNome(nome);

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

        List<Categoria> result = gateway.findAll();

        assertEquals(2, result.size());
        verify(repository).findAll();
    }

    @Test
    void deleteById_ShouldCallRepositoryDelete() {
        Long id = 1L;

        gateway.deleteById(id);

        verify(repository).deleteById(id);
    }
}