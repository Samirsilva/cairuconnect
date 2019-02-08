package com.cairu.service;

import java.util.Random;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.cairu.model.TrocaSenhaRequest;
import com.cairu.model.Usuario;
import com.cairu.repository.UsuarioRepository;
import com.cairu.service.exception.ObjectNotFoundException;

@Service
public class AuthService {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private BCryptPasswordEncoder pe;
	
	@Autowired
	private EmailService emailService;
	
	private Random rand = new Random();
	
	public void sendNewPassword(String email) throws MessagingException {
		
		Usuario usuario = usuarioRepository.findByEmail(email);
		if (usuario == null) {
			throw new ObjectNotFoundException("Email não encontrado");
		}
		
		String newPass = newPassword();
		usuario.setSenha(pe.encode(newPass));
		
		usuarioRepository.save(usuario);
		emailService.sendNewPasswordEmail(usuario, newPass);
	}

	public void trocarSenha(TrocaSenhaRequest request) {
		
		Usuario usuario = usuarioRepository.findByEmail(request.getEmail());
		if (usuario == null) {
			throw new ObjectNotFoundException("Email não encontrado");
		}
		
		if(request.getSenha().equals(request.getSenhaConfirmacao())) {
			usuario.setSenha(pe.encode(request.getSenha()));
		}else {
			throw new ObjectNotFoundException("Senhas não conferem");

		}				
		usuarioRepository.save(usuario);
	}
	
	private String newPassword() {
		char[] vet = new char[10];
		for (int i=0; i<10; i++) {
			vet[i] = randomChar();
		}
		return new String(vet);
	}

	private char randomChar() {
		int opt = rand.nextInt(3);
		if (opt == 0) { // gera um digito
			return (char) (rand.nextInt(10) + 48);
		}
		else if (opt == 1) { // gera letra maiuscula
			return (char) (rand.nextInt(26) + 65);
		}
		else { // gera letra minuscula
			return (char) (rand.nextInt(26) + 97);
		}
	}
}
