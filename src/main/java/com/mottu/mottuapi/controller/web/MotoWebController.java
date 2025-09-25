package com.mottu.mottuapi.controller.web;

import com.mottu.mottuapi.model.Moto;
import com.mottu.mottuapi.model.StatusMoto;
import com.mottu.mottuapi.service.MotoService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/moto")
public class MotoWebController {

	private final MotoService motoService;

	public MotoWebController(MotoService motoService) { this.motoService = motoService; }

	@GetMapping
	public String list(Model model) {
		model.addAttribute("motos", motoService.listarTodas());
		return "moto/list";
	}

	@GetMapping("/novo")
	public String novo(Model model) {
		model.addAttribute("moto", new Moto());
		model.addAttribute("status", StatusMoto.values());
		return "moto/form";
	}

	@PostMapping
	public String salvar(@Valid @ModelAttribute("moto") Moto moto, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			model.addAttribute("status", StatusMoto.values());
			return "moto/form";
		}
		motoService.criar(moto);
		return "redirect:/moto";
	}

	@GetMapping("/editar/{id}")
	public String editar(@PathVariable Long id, Model model) {
		Moto existente = motoService.buscarPorId(id);
		model.addAttribute("moto", existente);
		model.addAttribute("status", StatusMoto.values());
		return "moto/form";
	}

	@PostMapping("/atualizar/{id}")
	public String atualizar(@PathVariable Long id, @Valid @ModelAttribute("moto") Moto moto, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			model.addAttribute("status", StatusMoto.values());
			return "moto/form";
		}
		motoService.atualizar(id, moto);
		return "redirect:/moto";
	}

	@PostMapping("/excluir/{id}")
	public String excluir(@PathVariable Long id) {
		motoService.deletar(id);
		return "redirect:/moto";
	}
} 