package com.cairu.request;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.cairu.model.Noticia;

public class NoticiaRequest implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;

	@NotEmpty(message = "Preenchimento Obrigatório")
	@Length(min = 5, max = 80, message = "O tamanho deve ser entre 5 e 80 caracteres")
	private String informe;

	@NotEmpty(message = "Preenchimento Obrigatório")
	@Length(min = 5, max = 700, message = "O tamanho deve ser entre 5 e 700 caracteres")
	private String fonte;
	
	private Integer idUsuario;

	public NoticiaRequest() {

	}

	public NoticiaRequest(Noticia obj) {
		id = obj.getId();
		informe = obj.getInforme();
		fonte = obj.getFonte();
		idUsuario = obj.getIdUsuario().getId();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getInforme() {
		return informe;
	}

	public void setInforme(String informe) {
		this.informe = informe;
	}

	public String getFonte() {
		return fonte;
	}

	public void setFonte(String fonte) {
		this.fonte = fonte;
	}

	public Integer getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}

}
