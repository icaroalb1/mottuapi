package com.mottu.mottuapi.service;

import com.mottu.mottuapi.exception.RecursoNaoEncontradoException;
import com.mottu.mottuapi.model.*;
import com.mottu.mottuapi.repository.MovimentacaoRepository;
import com.mottu.mottuapi.repository.MotoRepository;
import com.mottu.mottuapi.repository.VagaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MovServiceTest {

    @Mock
    private MovimentacaoRepository movimentacaoRepository;

    @Mock
    private MotoRepository motoRepository;

    @Mock
    private VagaRepository vagaRepository;

    @InjectMocks
    private MovService movService;

    private Moto moto;
    private Vaga vaga;
    private Usuario usuario;

    @BeforeEach
    void setUp() {
        moto = new Moto();
        moto.setId(1L);
        moto.setPlaca("ABC1234");
        moto.setModelo("Honda CG 160");

        vaga = new Vaga();
        vaga.setId(1L);
        vaga.setOcupada(false);

        usuario = new Usuario();
        usuario.setId(1L);
        usuario.setUsername("admin");
    }

    @Test
    void checkin_QuandoVagaDisponivel_DeveRealizarCheckin() {
        // Given
        when(motoRepository.findById(1L)).thenReturn(Optional.of(moto));
        when(vagaRepository.findById(1L)).thenReturn(Optional.of(vaga));
        when(vagaRepository.save(any(Vaga.class))).thenReturn(vaga);
        when(movimentacaoRepository.save(any(Movimentacao.class))).thenReturn(new Movimentacao());

        // When
        Movimentacao resultado = movService.checkin(1L, 1L, usuario);

        // Then
        assertNotNull(resultado);
        assertTrue(vaga.isOcupada());
        assertEquals(moto, vaga.getMoto());
        verify(vagaRepository).save(vaga);
        verify(movimentacaoRepository).save(any(Movimentacao.class));
    }

    @Test
    void checkin_QuandoVagaOcupada_DeveLancarExcecao() {
        // Given
        vaga.setOcupada(true);
        when(motoRepository.findById(1L)).thenReturn(Optional.of(moto));
        when(vagaRepository.findById(1L)).thenReturn(Optional.of(vaga));

        // When & Then
        assertThrows(IllegalStateException.class, () -> movService.checkin(1L, 1L, usuario));
    }

    @Test
    void checkin_QuandoMotoNaoExiste_DeveLancarExcecao() {
        // Given
        when(motoRepository.findById(1L)).thenReturn(Optional.empty());

        // When & Then
        assertThrows(RecursoNaoEncontradoException.class, () -> movService.checkin(1L, 1L, usuario));
    }

    @Test
    void checkout_QuandoVagaOcupada_DeveRealizarCheckout() {
        // Given
        vaga.setOcupada(true);
        vaga.setMoto(moto);
        when(vagaRepository.findById(1L)).thenReturn(Optional.of(vaga));
        when(vagaRepository.save(any(Vaga.class))).thenReturn(vaga);
        when(movimentacaoRepository.save(any(Movimentacao.class))).thenReturn(new Movimentacao());

        // When
        Movimentacao resultado = movService.checkout(1L, usuario);

        // Then
        assertNotNull(resultado);
        assertFalse(vaga.isOcupada());
        assertNull(vaga.getMoto());
        verify(vagaRepository).save(vaga);
        verify(movimentacaoRepository).save(any(Movimentacao.class));
    }

    @Test
    void checkout_QuandoVagaLivre_DeveLancarExcecao() {
        // Given
        when(vagaRepository.findById(1L)).thenReturn(Optional.of(vaga));

        // When & Then
        assertThrows(IllegalStateException.class, () -> movService.checkout(1L, usuario));
    }

    @Test
    void checkout_QuandoVagaNaoExiste_DeveLancarExcecao() {
        // Given
        when(vagaRepository.findById(1L)).thenReturn(Optional.empty());

        // When & Then
        assertThrows(RecursoNaoEncontradoException.class, () -> movService.checkout(1L, usuario));
    }
}
