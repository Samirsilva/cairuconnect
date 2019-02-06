package com.cairu.service;

import java.util.Date;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.cairu.model.Usuario;

public abstract class AbstractEmailService implements EmailService{

	@Autowired
	private TemplateEngine templateEngine;
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	@Value("${default.sender}")
	private String sender;
	
	@Override
	public void sendOrderConfirmationEmail(Usuario obj) {
		SimpleMailMessage sm = prepareSimpleMailMessageFromPedido(obj);
		sendEmail(sm);
	}

	protected SimpleMailMessage prepareSimpleMailMessageFromPedido(Usuario obj) {
		SimpleMailMessage sm = new SimpleMailMessage();
		sm.setTo(obj.getEmail());
		sm.setFrom(sender);
		sm.setSubject("Cadastro confirmado! Código: " + obj.getId());
		sm.setSentDate(new Date(System.currentTimeMillis()));
		sm.setText(obj.toString());
		return sm;		
	}

	protected String htmlFromTemplatePedido(Usuario obj) {
		Context context = new Context();
		context.setVariable("usuario", obj);
		return templateEngine.process("email/ConfirmacaoUsuario", context);
	}
	
	@Override
	public void sendOrderConfirmationHtmlEmail(Usuario obj) {
		try {
			MimeMessage mm = prepareMimeMessageFromPedido(obj);
			sendHtmlEmail(mm);
		}
		catch (MessagingException e) {
			sendOrderConfirmationEmail(obj);
		}
	}
	
	protected MimeMessage prepareMimeMessageFromPedido(Usuario obj) throws MessagingException {
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		MimeMessageHelper mmh = new MimeMessageHelper(mimeMessage, true);
		mmh.setTo(obj.getEmail());
		mmh.setFrom(sender);
		mmh.setSubject(obj.getId() + "seu cadastro foi efetuado com sucesso.");
		mmh.setSentDate(new Date(System.currentTimeMillis()));
		mmh.setText(htmlFromTemplatePedido(obj), true);
		return mimeMessage;
	}	
	
	@Override
	public void sendNewPasswordEmail(Usuario usuario, String newPass) {
		SimpleMailMessage sm = prepareNewPasswordEmail(usuario, newPass);
		sendEmail(sm);
	}
	
	protected SimpleMailMessage prepareNewPasswordEmail(Usuario usuario, String newPass) {
		SimpleMailMessage sm = new SimpleMailMessage();
		sm.setTo(usuario.getEmail());
		sm.setFrom(sender);
		sm.setSubject("Solicitação de nova senha CAIRU CONNECT");
		sm.setSentDate(new Date(System.currentTimeMillis()));
		sm.setText("Nova senha: " + newPass);
		return sm;
	}
}