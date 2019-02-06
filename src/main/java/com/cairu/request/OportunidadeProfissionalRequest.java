package com.cairu.request;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import com.cairu.model.OportunidadeProfissional;

public class OportunidadeProfissionalRequest implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;

	private String nomeEmpresa;

	private String sexo;

	@NotEmpty(message = "Preenchimento Obrigatório")
	@Email(message = "Email inválido")
	private String email;

	private String tipoVaga;

	private Integer cargaHoraria;

	private BigDecimal remuneracao;

	private String beneficios;

	private String requisitos;

	private LocalDate dataInicio;
	
	private LocalDate dataTermino;
	
	private String disponibilidade;
	
	private Integer quantidadeVagas;
	
	private String linkImg;

	public OportunidadeProfissionalRequest() {

	}

	public OportunidadeProfissionalRequest(OportunidadeProfissional obj) {
		id = obj.getId();
		nomeEmpresa = obj.getNomeEmpresa();
		sexo = obj.getSexo();
		email = obj.getEmail();
		tipoVaga = obj.getTipoVaga();
		cargaHoraria = obj.getCargaHoraria();
		remuneracao = obj.getRemuneracao();
		beneficios = obj.getBeneficios();
		requisitos = obj.getRequisitos();
		dataInicio = obj.getDataInicio();
		dataTermino = obj.getDataTermino();
		disponibilidade = obj.getDisponibilidade();
		quantidadeVagas = obj.getQuantidadeVagas();
		linkImg = obj.getLinkImg();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNomeEmpresa() {
		return nomeEmpresa;
	}

	public void setNomeEmpresa(String nomeEmpresa) {
		this.nomeEmpresa = nomeEmpresa;
	}


	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getCargaHoraria() {
		return cargaHoraria;
	}

	public void setCargaHoraria(Integer cargaHoraria) {
		this.cargaHoraria = cargaHoraria;
	}

	public BigDecimal getRemuneracao() {
		return remuneracao;
	}

	public void setRemuneracao(BigDecimal remuneracao) {
		this.remuneracao = remuneracao;
	}

	public String getBeneficios() {
		return beneficios;
	}

	public void setBeneficios(String beneficios) {
		this.beneficios = beneficios;
	}

	public String getRequisitos() {
		return requisitos;
	}

	public void setRequisitos(String rquisitos) {
		this.requisitos = rquisitos;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getTipoVaga() {
		return tipoVaga;
	}

	public void setTipoVaga(String tipoVaga) {
		this.tipoVaga = tipoVaga;
	}

	public LocalDate getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(LocalDate dataInicio) {
		this.dataInicio = dataInicio;
	}

	public LocalDate getDataTermino() {
		return dataTermino;
	}

	public void setDataTermino(LocalDate dataTermino) {
		this.dataTermino = dataTermino;
	}

	public String getDisponibilidade() {
		return disponibilidade;
	}

	public void setDisponibilidade(String disponibilidade) {
		this.disponibilidade = disponibilidade;
	}

	public Integer getQuantidadeVagas() {
		return quantidadeVagas;
	}

	public void setQuantidadeVagas(Integer quantidadeVagas) {
		this.quantidadeVagas = quantidadeVagas;
	}

	public String getLinkImg() {
		return linkImg;
	}

	public void setLinkImg(String linkImg) {
		this.linkImg = linkImg;
	}
	
	
}
