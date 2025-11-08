package com.mottu.mottuapi.integration;

import com.mottu.mottuapi.model.Moto;
import com.mottu.mottuapi.model.StatusMoto;
import com.mottu.mottuapi.repository.MotoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
class MotoIntegrationTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private MotoRepository motoRepository;

    @Test
    void salvarMoto_DevePersistirNoBanco() {
        // Given
        Moto moto = new Moto();
        moto.setPlaca("ABC1234");
        moto.setModelo("Honda CG 160");
        moto.setStatus(StatusMoto.DISPONIVEL);
        moto.setLigada(false);

        // When
        Moto motoSalva = motoRepository.save(moto);
        entityManager.flush();

        // Then
        assertNotNull(motoSalva.getId());
        assertEquals("ABC1234", motoSalva.getPlaca());
        assertEquals("Honda CG 160", motoSalva.getModelo());
        assertEquals(StatusMoto.DISPONIVEL, motoSalva.getStatus());
        assertFalse(motoSalva.isLigada());
    }

    @Test
    void buscarMotoPorId_QuandoExiste_DeveRetornarMoto() {
        // Given
        Moto moto = new Moto();
        moto.setPlaca("XYZ5678");
        moto.setModelo("Yamaha Fazer 250");
        moto.setStatus(StatusMoto.EM_MANUTENCAO);
        moto.setLigada(true);
        
        Moto motoSalva = entityManager.persistAndFlush(moto);

        // When
        Optional<Moto> resultado = motoRepository.findById(motoSalva.getId());

        // Then
        assertTrue(resultado.isPresent());
        assertEquals("XYZ5678", resultado.get().getPlaca());
        assertEquals(StatusMoto.EM_MANUTENCAO, resultado.get().getStatus());
    }

    @Test
    void listarTodasMotos_DeveRetornarListaCompleta() {
        // Given
        Moto moto1 = new Moto();
        moto1.setPlaca("ABC1234");
        moto1.setModelo("Honda CG 160");
        moto1.setStatus(StatusMoto.DISPONIVEL);
        
        Moto moto2 = new Moto();
        moto2.setPlaca("XYZ5678");
        moto2.setModelo("Yamaha Fazer 250");
        moto2.setStatus(StatusMoto.EM_MANUTENCAO);
        
        entityManager.persistAndFlush(moto1);
        entityManager.persistAndFlush(moto2);

        // When
        List<Moto> motos = motoRepository.findAll();

        // Then
        assertEquals(5, motos.size());
        assertTrue(motos.stream().anyMatch(m -> "ABC1234".equals(m.getPlaca())));
        assertTrue(motos.stream().anyMatch(m -> "XYZ5678".equals(m.getPlaca())));
    }

    @Test
    void deletarMoto_DeveRemoverDoBanco() {
        // Given
        Moto moto = new Moto();
        moto.setPlaca("ABC1234");
        moto.setModelo("Honda CG 160");
        Moto motoSalva = entityManager.persistAndFlush(moto);

        // When
        motoRepository.deleteById(motoSalva.getId());
        entityManager.flush();

        // Then
        Optional<Moto> resultado = motoRepository.findById(motoSalva.getId());
        assertFalse(resultado.isPresent());
    }
}
