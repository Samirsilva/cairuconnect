package com.cairu.request;

import java.io.Serializable;

import com.cairu.model.Evento;

public class EventoRequest implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;

	private String nome;

	private String descricao;
	
	private String periodoLetivo;
	
	public EventoRequest() {

	}

	public EventoRequest(Evento obj) {
		id = obj.getId();
		nome = obj.getNome();
		descricao = obj.getDescricao();
		periodoLetivo = obj.getPeriodoLetivo();

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

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getPeriodoLetivo() {
		return periodoLetivo;
	}

	public void setPeriodoletivo(String periodoletivo) {
		this.periodoLetivo = periodoletivo;
	}



}
