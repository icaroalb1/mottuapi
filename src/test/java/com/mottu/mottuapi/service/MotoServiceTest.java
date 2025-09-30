package com.mottu.mottuapi.service;

import com.mottu.mottuapi.model.Moto;
import com.mottu.mottuapi.model.StatusMoto;
import com.mottu.mottuapi.repository.MotoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MotoServiceTest {

    @Mock
    private MotoRepository motoRepository;

    @InjectMocks
    private MotoService motoService;

    private Moto moto;

    @BeforeEach
    void setUp() {
        moto = new Moto();
        moto.setId(1L);
        moto.setPlaca("ABC1234");
        moto.setModelo("Honda CG 160");
        moto.setStatus(StatusMoto.DISPONIVEL);
        moto.setLigada(false);
    }

    @Test
    void listarTodas_DeveRetornarListaDeMotos() {
        // Given
        List<Moto> motos = Arrays.asList(moto);
        when(motoRepository.findAll()).thenReturn(motos);

        // When
        List<Moto> resultado = motoService.listarTodas();

        // Then
        assertEquals(1, resultado.size());
        assertEquals("ABC1234", resultado.get(0).getPlaca());
        verify(motoRepository).findAll();
    }

    @Test
    void buscarPorId_QuandoMotoExiste_DeveRetornarMoto() {
        // Given
        when(motoRepository.findById(1L)).thenReturn(Optional.of(moto));

        // When
        Moto resultado = motoService.buscarPorId(1L);

        // Then
        assertNotNull(resultado);
        assertEquals("ABC1234", resultado.getPlaca());
        verify(motoRepository).findById(1L);
    }

    @Test
    void buscarPorId_QuandoMotoNaoExiste_DeveLancarExcecao() {
        // Given
        when(motoRepository.findById(1L)).thenReturn(Optional.empty());

        // When & Then
        assertThrows(RuntimeException.class, () -> motoService.buscarPorId(1L));
        verify(motoRepository).findById(1L);
    }

    @Test
    void criar_DeveSalvarMoto() {
        // Given
        when(motoRepository.save(any(Moto.class))).thenReturn(moto);

        // When
        Moto resultado = motoService.criar(moto);

        // Then
        assertNotNull(resultado);
        verify(motoRepository).save(moto);
    }

    @Test
    void atualizar_QuandoMotoExiste_DeveAtualizarMoto() {
        // Given
        Moto motoAtualizada = new Moto();
        motoAtualizada.setPlaca("XYZ5678");
        motoAtualizada.setModelo("Yamaha Fazer 250");
        
        when(motoRepository.findById(1L)).thenReturn(Optional.of(moto));
        when(motoRepository.save(any(Moto.class))).thenReturn(motoAtualizada);

        // When
        Moto resultado = motoService.atualizar(1L, motoAtualizada);

        // Then
        assertNotNull(resultado);
        verify(motoRepository).findById(1L);
        verify(motoRepository).save(any(Moto.class));
    }

    @Test
    void deletar_DeveExcluirMoto() {
        // Given
        when(motoRepository.findById(1L)).thenReturn(Optional.of(moto));

        // When
        motoService.deletar(1L);

        // Then
        verify(motoRepository).findById(1L);
        verify(motoRepository).delete(moto);
    }
}
