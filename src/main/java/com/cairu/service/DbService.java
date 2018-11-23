package com.cairu.service;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.cairu.model.Evento;
import com.cairu.model.Noticia;
import com.cairu.model.OportunidadeProfissional;
import com.cairu.model.Usuario;
import com.cairu.model.enums.TipoUsuario;
import com.cairu.model.enums.TipoVaga;
import com.cairu.repository.EventoRepository;
import com.cairu.repository.NoticiaRepository;
import com.cairu.repository.OportunidadeProfissionalRepository;
import com.cairu.repository.UsuarioRepository;

@Service
public class DbService {

	@Autowired
	private NoticiaRepository noticiaRepository;
	@Autowired
	private OportunidadeProfissionalRepository oportunidadeRepository;
	@Autowired
	private UsuarioRepository usuarioRepository;
	@Autowired
	private EventoRepository eventoRepository;
	@Autowired
	private BCryptPasswordEncoder pe;

	public void instaciateDatabase() throws ParseException {

		BigDecimal remuneracao = BigDecimal.ZERO;
		
		OportunidadeProfissional oportunidade = new OportunidadeProfissional(null, "Empresa de teste", "Tecnologia da informação", 6, "empresateste@gmail.com", TipoVaga.ESTAGIO, 6, remuneracao, "Beneficios minimos: respirar", "Requisitos minimos ser você mesmo",null);
		OportunidadeProfissional oportunidade2 = new OportunidadeProfissional(null, "Empresa de teste 2", "Administração", 6, "empresateste2@gmail.com", TipoVaga.EMPREGO, 6, remuneracao, "Beneficios minimos: respirar", "Requisitos minimos ser você mesmo",null);	
		
		Usuario userNormal = new Usuario(null, "Maria Silva", "maria@gmail.com", "0515161839", pe.encode("123"));
		
		Usuario userAdminGeral = new Usuario(null, "Samir Silva", "samirsilva16@gmail.com", "0515161839", pe.encode("123"));
		userAdminGeral.addTipoUsuario(TipoUsuario.ADMIN);

		Usuario userAdminEvento= new Usuario(null, "Admin Evento", "evento@gmail.com", "0515161839", pe.encode("123"));
		userAdminEvento.addTipoUsuario(TipoUsuario.ADMIN_EVENTO);
		
		Usuario userAdminNoticia = new Usuario(null, "Admin Noticia", "noticia@gmail.com", "0515161839", pe.encode("123"));
		userAdminNoticia.addTipoUsuario(TipoUsuario.ADMIN_NOTICIA);
		
		Usuario userAdminOportunidade = new Usuario(null, "Admin Oportunidade", "oportunidade@gmail.com", "0515161839", pe.encode("123"));
		userAdminOportunidade.addTipoUsuario(TipoUsuario.ADMIN_OPORTUNIDADE);
		
		Noticia not = new Noticia(null, "Atencão noticia 1 !", "www.google.com",null);
		Noticia not2 = new Noticia(null, "Atencão noticia 2 !", "www.google.com",null);
		
		Evento event = new Evento(null, "Abertura dos portões", "12-12-2018", "Este evento é para abrirs os portões",null);
		Evento event2 = new Evento(null, "Encerramento de inscrições", "12-12-2018", "Este evento é para testar coisas",null);
		
		noticiaRepository.saveAll(Arrays.asList(not,not2));
		oportunidadeRepository.saveAll(Arrays.asList(oportunidade, oportunidade2));
		usuarioRepository.saveAll(Arrays.asList(userNormal,userAdminGeral,userAdminEvento,userAdminNoticia,userAdminOportunidade));
		eventoRepository.saveAll(Arrays.asList(event,event2));
	}
}
