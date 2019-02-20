package com.cairu.request;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.cairu.model.Usuario;
import com.cairu.service.validation.UsuarioInsert;

@UsuarioInsert
public class NovoUsuarioRequest implements Serializable {
	private static final long serialVersionUID = 1L;

	@NotEmpty(message = "Preenchimento obrigatório")
	@Length(min = 5, max = 120, message = "O tamanho deve ser entre 5 a 120 caracteres")
	private String nome;

	@NotEmpty(message = "Preenchimento obrigatório")
	@Email(message = "Email inválido")
	private String email;

	@NotEmpty(message = "Preenchimento obrigatório")
	@Length(min = 11, max = 14, message = "O tamanho deve ser entre 11 a 14 caracteres")
	private String cpfCnpj;

	@NotEmpty(message = "Preenchimento obrigatório")
	@Length(min = 5, max = 10, message = "O tamanho deve ser entre 5 a 10 caracteres")
	private String senha;

	public NovoUsuarioRequest() {

	}

	public NovoUsuarioRequest(Usuario obj) {
		nome = obj.getNome();
		email = obj.getEmail();
		cpfCnpj = obj.getCpfCnpj();
		senha = obj.getSenha();

	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCpfCnpj() {
		return cpfCnpj;
	}

	public void setCpfCnpj(String cpfCnpj) {
		this.cpfCnpj = cpfCnpj;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

}
