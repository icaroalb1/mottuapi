package com.mottu.mottuapi.service;

import com.mottu.mottuapi.exception.RecursoNaoEncontradoException;
import com.mottu.mottuapi.model.*;
import com.mottu.mottuapi.repository.MovimentacaoRepository;
import com.mottu.mottuapi.repository.MotoRepository;
import com.mottu.mottuapi.repository.VagaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MovService {

	private final MovimentacaoRepository movimentacaoRepository;
	private final MotoRepository motoRepository;
	private final VagaRepository vagaRepository;

	public MovService(MovimentacaoRepository movimentacaoRepository, MotoRepository motoRepository, VagaRepository vagaRepository) {
		this.movimentacaoRepository = movimentacaoRepository;
		this.motoRepository = motoRepository;
		this.vagaRepository = vagaRepository;
	}

	public List<Movimentacao> listar() { return movimentacaoRepository.findAll(); }

	@Transactional
	public Movimentacao checkin(Long motoId, Long vagaId, Usuario usuario) {
		Moto moto = motoRepository.findById(motoId).orElseThrow(() -> new RecursoNaoEncontradoException("Moto não encontrada"));
		Vaga vaga = vagaRepository.findById(vagaId).orElseThrow(() -> new RecursoNaoEncontradoException("Vaga não encontrada"));
		if (vaga.isOcupada()) {
			throw new IllegalStateException("Vaga já ocupada");
		}
		vaga.setOcupada(true);
		vaga.setMoto(moto);
		Movimentacao mov = new Movimentacao();
		mov.setMoto(moto);
		mov.setVaga(vaga);
		mov.setTipo(TipoMovimentacao.CHECKIN);
		mov.setUsuario(usuario);
		vagaRepository.save(vaga);
		return movimentacaoRepository.save(mov);
	}

	@Transactional
	public Movimentacao checkout(Long vagaId, Usuario usuario) {
		Vaga vaga = vagaRepository.findById(vagaId).orElseThrow(() -> new RecursoNaoEncontradoException("Vaga não encontrada"));
		if (!vaga.isOcupada() || vaga.getMoto() == null) {
			throw new IllegalStateException("Vaga já está livre");
		}
		Movimentacao mov = new Movimentacao();
		mov.setMoto(vaga.getMoto());
		mov.setVaga(vaga);
		mov.setTipo(TipoMovimentacao.CHECKOUT);
		mov.setUsuario(usuario);
		vaga.setOcupada(false);
		vaga.setMoto(null);
		vagaRepository.save(vaga);
		return movimentacaoRepository.save(mov);
	}
} 