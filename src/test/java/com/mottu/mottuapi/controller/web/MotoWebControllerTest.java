package com.mottu.mottuapi.controller.web;

import com.mottu.mottuapi.model.Moto;
import com.mottu.mottuapi.model.StatusMoto;
import com.mottu.mottuapi.service.MotoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MotoWebControllerTest {

    @Mock
    private MotoService motoService;

    @Mock
    private Model model;

    @Mock
    private BindingResult bindingResult;

    @InjectMocks
    private MotoWebController motoWebController;

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
    void list_DeveRetornarViewList() {
        // Given
        List<Moto> motos = Arrays.asList(moto);
        when(motoService.listarTodas()).thenReturn(motos);

        // When
        String viewName = motoWebController.list(model);

        // Then
        assertEquals("moto/list", viewName);
        verify(model).addAttribute("motos", motos);
        verify(motoService).listarTodas();
    }

    @Test
    void novo_DeveRetornarViewForm() {
        // When
        String viewName = motoWebController.novo(model);

        // Then
        assertEquals("moto/form", viewName);
        verify(model).addAttribute(eq("moto"), any(Moto.class));
        verify(model).addAttribute("status", StatusMoto.values());
    }

    @Test
    void salvar_QuandoSemErros_DeveRedirecionarParaList() {
        // Given
        when(bindingResult.hasErrors()).thenReturn(false);

        // When
        String viewName = motoWebController.salvar(moto, bindingResult, model);

        // Then
        assertEquals("redirect:/moto", viewName);
        verify(motoService).criar(moto);
    }

    @Test
    void salvar_QuandoComErros_DeveRetornarForm() {
        // Given
        when(bindingResult.hasErrors()).thenReturn(true);

        // When
        String viewName = motoWebController.salvar(moto, bindingResult, model);

        // Then
        assertEquals("moto/form", viewName);
        verify(model).addAttribute("status", StatusMoto.values());
        verify(motoService, never()).criar(any());
    }

    @Test
    void editar_DeveRetornarViewFormComMoto() {
        // Given
        when(motoService.buscarPorId(1L)).thenReturn(moto);

        // When
        String viewName = motoWebController.editar(1L, model);

        // Then
        assertEquals("moto/form", viewName);
        verify(model).addAttribute("moto", moto);
        verify(model).addAttribute("status", StatusMoto.values());
        verify(motoService).buscarPorId(1L);
    }

    @Test
    void atualizar_QuandoSemErros_DeveRedirecionarParaList() {
        // Given
        when(bindingResult.hasErrors()).thenReturn(false);

        // When
        String viewName = motoWebController.atualizar(1L, moto, bindingResult, model);

        // Then
        assertEquals("redirect:/moto", viewName);
        verify(motoService).atualizar(1L, moto);
    }

    @Test
    void atualizar_QuandoComErros_DeveRetornarForm() {
        // Given
        when(bindingResult.hasErrors()).thenReturn(true);

        // When
        String viewName = motoWebController.atualizar(1L, moto, bindingResult, model);

        // Then
        assertEquals("moto/form", viewName);
        verify(model).addAttribute("status", StatusMoto.values());
        verify(motoService, never()).atualizar(any(), any());
    }

    @Test
    void excluir_DeveRedirecionarParaList() {
        // When
        String viewName = motoWebController.excluir(1L);

        // Then
        assertEquals("redirect:/moto", viewName);
        verify(motoService).deletar(1L);
    }
}
