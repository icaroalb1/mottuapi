package com.mottu.mottuapi.config;

import com.mottu.mottuapi.model.Papel;
import com.mottu.mottuapi.model.Usuario;
import com.mottu.mottuapi.repository.PapelRepository;
import com.mottu.mottuapi.repository.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.Set;

@Configuration
public class DataInitializer {

	@Bean
	CommandLineRunner initUsers(PapelRepository papelRepository, UsuarioRepository usuarioRepository, PasswordEncoder encoder) {
		return args -> {
			Papel adminRole = papelRepository.findByNome("ROLE_ADMIN").orElseGet(() -> {
				Papel p = new Papel();
				p.setNome("ROLE_ADMIN");
				return papelRepository.save(p);
			});
			Papel operadorRole = papelRepository.findByNome("ROLE_OPERADOR").orElseGet(() -> {
				Papel p = new Papel();
				p.setNome("ROLE_OPERADOR");
				return papelRepository.save(p);
			});

			// Admin
			Usuario admin = usuarioRepository.findByUsername("admin").orElseGet(() -> {
				Usuario u = new Usuario();
				u.setUsername("admin");
				return u;
			});
			admin.setPassword(encoder.encode("123456"));
			admin.setEnabled(true);
			Set<Papel> adminPapeis = new HashSet<>();
			adminPapeis.add(adminRole);
			admin.setPapeis(adminPapeis);
			usuarioRepository.save(admin);

			// Operador
			Usuario oper = usuarioRepository.findByUsername("oper").orElseGet(() -> {
				Usuario u = new Usuario();
				u.setUsername("oper");
				return u;
			});
			oper.setPassword(encoder.encode("123456"));
			oper.setEnabled(true);
			Set<Papel> operPapeis = new HashSet<>();
			operPapeis.add(operadorRole);
			oper.setPapeis(operPapeis);
			usuarioRepository.save(oper);
		};
	}
} 