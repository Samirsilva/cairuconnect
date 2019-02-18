package com.cairu.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.cairu.model.enums.TipoUsuario;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name= "usuario")
public class Usuario implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name= "id", unique = true, nullable = false)
	private Integer id;
	private String nome;

	@Column(unique = true)
	private String email;
	
	@Column(name= "cpfcnpj", unique = true, nullable = false)
	private String cpfOuCnpj;

	@JsonIgnore
	private String senha;

	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = "tipo_usuario")
	@Column(name = "tipos_de_usuario")
	private Set<Integer> tiposDeUsuario = new HashSet<Integer>();
	
	public Usuario() {
		addTipoUsuario(TipoUsuario.USUARIO);
	}

	public Usuario(Integer id, String nome, String email, String cpfOuCnpj, String senha) {
		super();
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.cpfOuCnpj = cpfOuCnpj;
		this.senha = senha;
		addTipoUsuario(TipoUsuario.USUARIO);

	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public String getCpfOuCnpj() {
		return cpfOuCnpj;
	}

	public void setCpfOuCnpj(String cpfOuCnpj) {
		this.cpfOuCnpj = cpfOuCnpj;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Set<TipoUsuario> getTiposDeUsuario() {
		return tiposDeUsuario.stream().map(x -> TipoUsuario.toEnum(x)).collect(Collectors.toSet());
	}

	public void addTipoUsuario(TipoUsuario tipo) {
		tiposDeUsuario.add(tipo.getCod());
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Usuario: ");
		builder.append(nome);
		builder.append(", Email: ");
		builder.append(email);
		builder.append(", CpfOuCnpj: ");
		builder.append(cpfOuCnpj);
		return builder.toString();
	}
	
}
