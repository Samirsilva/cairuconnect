package com.cairu.request;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.cairu.model.Evento;

public class EventoRequest implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;

	@NotEmpty(message = "Preenchimento Obrigatório")
	@Length(min = 5, max = 80, message = "O tamanho deve ser entre 5 e 80 caracteres")
	private String nome;

	private String data;

	@NotEmpty(message = "Preenchimento Obrigatório")
	@Length(min = 5, max = 700, message = "O tamanho deve ser entre 5 e 700 caracteres")
	private String descricao;

	private Integer idUsuario;

	public EventoRequest() {

	}

	public EventoRequest(Evento obj) {
		id = obj.getId();
		nome = obj.getNome();
		data = obj.getData();
		descricao = obj.getDescricao();
		idUsuario = obj.getIdUsuario().getId();
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

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Integer getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}

}
