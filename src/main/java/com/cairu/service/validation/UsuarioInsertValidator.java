package com.cairu.service.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.cairu.controller.exception.FieldMessage;
import com.cairu.model.Usuario;
import com.cairu.repository.UsuarioRepository;
import com.cairu.request.NovoUsuarioRequest;

public class UsuarioInsertValidator implements ConstraintValidator<UsuarioInsert, NovoUsuarioRequest> {
	
	@Autowired
	private UsuarioRepository repo;
	
	@Override
	public void initialize(UsuarioInsert ann) {
	}

	@Override
	public boolean isValid(NovoUsuarioRequest objRequest, ConstraintValidatorContext context) {
		List<FieldMessage> list = new ArrayList<>();
		
		Usuario aux = repo.findByEmail(objRequest.getEmail());
		if(aux != null) {
			list.add(new FieldMessage("email", "Email já existente"));
		}
		Usuario aux2 = repo.findByCpfCnpj(objRequest.getCpfCnpj());
		if(aux2 != null) {
			list.add(new FieldMessage("cpfCnpj", "CPF ou CNPJ já existente"));

		}
		
		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}
