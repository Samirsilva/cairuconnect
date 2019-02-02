package com.cairu.model.mysql;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.cairu.model.enums.TipoUsuario;
import com.cairu.model.sqlserver.Evento;
import com.cairu.model.sqlserver.Noticia;
import com.cairu.model.sqlserver.OportunidadeProfissional;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Usuario implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name= "id", unique = true, nullable = false)
	private Integer id;
	private String nome;

	@Column(unique = true)
	private String email;
	
	private String cpfOuCnpj;

	@JsonIgnore
	private String senha;

	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = "tipo_usuario")
	@Column(name = "tipos_de_usuario")
	private Set<Integer> tiposDeUsuario = new HashSet<Integer>();
	
	@JsonManagedReference
	@OneToMany(fetch = FetchType.LAZY, mappedBy="idUsuario", cascade = {CascadeType.ALL})
	private Set<Evento> eventos = new HashSet<Evento>();

	@JsonManagedReference
	@OneToMany(fetch = FetchType.LAZY, mappedBy="idUsuario", cascade = {CascadeType.ALL})
	private Set<Noticia> noticias = new HashSet<Noticia>();
	
	@JsonManagedReference
	@OneToMany(fetch = FetchType.LAZY, mappedBy="idUsuario", cascade = {CascadeType.ALL})
	private Set<OportunidadeProfissional> oportunidades = new HashSet<OportunidadeProfissional>();
	
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

	public Set<Evento> getEventos() {
		return eventos;
	}

	public void setEventos(Set<Evento> eventos) {
		this.eventos = eventos;
	}

	public Set<Noticia> getNoticias() {
		return noticias;
	}

	public void setNoticias(Set<Noticia> noticias) {
		this.noticias = noticias;
	}

	public Set<OportunidadeProfissional> getOportunidades() {
		return oportunidades;
	}

	public void setOportunidades(Set<OportunidadeProfissional> oportunidades) {
		this.oportunidades = oportunidades;
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
