package com.cairu.service;

import java.text.ParseException;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.cairu.model.Usuario;
import com.cairu.model.enums.TipoUsuario;
import com.cairu.repository.UsuarioRepository;

@Service
public class DbService {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private BCryptPasswordEncoder pe;

	public void instaciateDatabase() throws ParseException {
		
		Usuario userNormal = new Usuario(null, "Maria Silva", "maria@gmail.com", "0515161839", pe.encode("123"));
		
		Usuario userAdminGeral = new Usuario(null, "Samir Silva", "samirsilva16@gmail.com", "0515161839", pe.encode("123"));
		userAdminGeral.addTipoUsuario(TipoUsuario.ADMIN);

		Usuario userAdminEvento= new Usuario(null, "Admin Evento", "evento@gmail.com", "0515161839", pe.encode("123"));
		userAdminEvento.addTipoUsuario(TipoUsuario.ADMIN_EVENTO);
		
		Usuario userAdminNoticia = new Usuario(null, "Admin Noticia", "noticia@gmail.com", "0515161839", pe.encode("123"));
		userAdminNoticia.addTipoUsuario(TipoUsuario.ADMIN_NOTICIA);
		
		Usuario userAdminOportunidade = new Usuario(null, "Admin Oportunidade", "oportunidade@gmail.com", "0515161839", pe.encode("123"));
		userAdminOportunidade.addTipoUsuario(TipoUsuario.ADMIN_OPORTUNIDADE);
				
		usuarioRepository.saveAll(Arrays.asList(userNormal,userAdminGeral,userAdminEvento,userAdminNoticia,userAdminOportunidade));
	}
}
