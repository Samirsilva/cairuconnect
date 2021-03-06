package com.cairu.controller;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cairu.model.EmailRequest;
import com.cairu.model.TrocaSenhaRequest;
import com.cairu.security.JWTUtil;
import com.cairu.security.UserSS;
import com.cairu.service.AuthService;
import com.cairu.service.UserService;

@RestController
@RequestMapping(value = "/auth")	
public class AuthController {

	@Autowired
	private JWTUtil jwtUtil;
	
	@Autowired
	private AuthService service;
	
	@RequestMapping(value = "/refresh_token", method = RequestMethod.POST)
	public ResponseEntity<Void> refreshToken(HttpServletResponse response) {
		UserSS user = UserService.authenticated();
		String token = jwtUtil.generateToken(user.getUsername());
		response.addHeader("Authorization", "Bearer " + token);
		response.addHeader("access-control-expose-headers", "Authorization");
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value = "/forgot", method = RequestMethod.POST)
	public ResponseEntity<Void> forgot(@Valid @RequestBody EmailRequest objDto) throws MessagingException {
		service.sendNewPassword(objDto.getEmail());
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value = "/trocar_senha", method = RequestMethod.POST)
	public ResponseEntity<Void> trocarSenha(@Valid @RequestBody TrocaSenhaRequest request){
		service.trocarSenha(request);
		return ResponseEntity.noContent().build();
	}
}
