package com.mottu.mottuapi.controller.web;

import com.mottu.mottuapi.model.Usuario;
import com.mottu.mottuapi.repository.MotoRepository;
import com.mottu.mottuapi.repository.UsuarioRepository;
import com.mottu.mottuapi.repository.VagaRepository;
import com.mottu.mottuapi.service.MovService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/mov")
public class MovController {

	private final MovService movService;
	private final MotoRepository motoRepository;
	private final VagaRepository vagaRepository;
	private final UsuarioRepository usuarioRepository;

	public MovController(MovService movService, MotoRepository motoRepository, VagaRepository vagaRepository, UsuarioRepository usuarioRepository) {
		this.movService = movService;
		this.motoRepository = motoRepository;
		this.vagaRepository = vagaRepository;
		this.usuarioRepository = usuarioRepository;
	}

	@GetMapping
	public String list(Model model) {
		model.addAttribute("movs", movService.listar());
		return "mov/list";
	}

	@GetMapping("/checkin")
	public String checkinForm(Model model) {
		model.addAttribute("motos", motoRepository.findAll());
		model.addAttribute("vagas", vagaRepository.findAll());
		return "mov/checkin";
	}

	@PostMapping("/checkin")
	public String doCheckin(@RequestParam Long motoId, @RequestParam Long vagaId, @AuthenticationPrincipal UserDetails user) {
		Usuario u = usuarioRepository.findByUsername(user.getUsername()).orElseThrow();
		movService.checkin(motoId, vagaId, u);
		return "redirect:/mov";
	}

	@GetMapping("/checkout")
	public String checkoutForm(Model model) {
		model.addAttribute("vagas", vagaRepository.findAll());
		return "mov/checkout";
	}

	@PostMapping("/checkout")
	public String doCheckout(@RequestParam Long vagaId, @AuthenticationPrincipal UserDetails user) {
		Usuario u = usuarioRepository.findByUsername(user.getUsername()).orElseThrow();
		movService.checkout(vagaId, u);
		return "redirect:/mov";
	}
} 