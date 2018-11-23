package com.cairu.service.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import com.cairu.controller.exception.FieldMessage;
import com.cairu.model.Usuario;
import com.cairu.repository.UsuarioRepository;
import com.cairu.request.UsuarioRequest;

public class UsuarioUpdateValidator implements ConstraintValidator<UsuarioUpdate, UsuarioRequest> {
	
	@Autowired
	private HttpServletRequest servletRequest;
	
	@Autowired
	private UsuarioRepository repo;
	
	@Override
	public void initialize(UsuarioUpdate ann) {
	}

	@Override
	public boolean isValid(UsuarioRequest objDto, ConstraintValidatorContext context) {
		
		@SuppressWarnings("unchecked")
		Map<String, String> map = (Map<String, String>) servletRequest.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
		Integer uriId = Integer.parseInt(map.get("id"));
		
		List<FieldMessage> list = new ArrayList<>();
		
		Usuario aux = repo.findByEmail(objDto.getEmail());
		if(aux != null && !aux.getId().equals(uriId)) {
			list.add(new FieldMessage("email", "Email já existente"));
		}
		
		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}
