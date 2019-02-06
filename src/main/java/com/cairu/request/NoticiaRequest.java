package com.cairu.request;

import java.io.Serializable;
import java.time.LocalDate;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.cairu.model.Noticia;

public class NoticiaRequest implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;

	@NotEmpty(message = "Preenchimento Obrigatório")
	@Length(min = 5, max = 80, message = "O tamanho deve ser entre 5 e 80 caracteres")
	private String informe;

	private String texto;
	
	private String imgLink;
	
	@NotEmpty(message = "Preenchimento Obrigatório")
	@Length(min = 5, max = 700, message = "O tamanho deve ser entre 5 e 700 caracteres")
	private String fonte;
	
	private LocalDate data;
	
	private String nomeLink;
	
	private String video;
	public NoticiaRequest() {

	}

	public NoticiaRequest(Noticia obj) {
		id = obj.getId();
		informe = obj.getInforme();
		texto = obj.getTexto();
		imgLink = obj.getLinkImg();
		fonte = obj.getFonte();
		data = obj.getData();
		nomeLink = obj.getNomeLink();
		video = obj.getNomeLink();
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

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public String getImgLink() {
		return imgLink;
	}

	public void setImgLink(String imgLink) {
		this.imgLink = imgLink;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public String getNomeLink() {
		return nomeLink;
	}

	public void setNomeLink(String nomeLink) {
		this.nomeLink = nomeLink;
	}

	public String getVideo() {
		return video;
	}

	public void setVideo(String video) {
		this.video = video;
	}

}
