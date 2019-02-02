package com.cairu.request;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import com.cairu.model.enums.TipoVaga;
import com.cairu.model.sqlserver.OportunidadeProfissional;

public class OportunidadeProfissionalRequest implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;

	private String nomeEmpresa;

	private String curso;

	private Integer semestre;

	@NotEmpty(message = "Preenchimento Obrigatório")
	@Email(message = "Email inválido")
	private String email;

	private TipoVaga tipoVaga;

	private Integer cargaHoraria;

	private BigDecimal remuneracao;

	private String beneficios;

	private String requisitos;

	private Integer idUsuario;
	public OportunidadeProfissionalRequest() {

	}

	public OportunidadeProfissionalRequest(OportunidadeProfissional obj) {
		id = obj.getId();
		nomeEmpresa = obj.getNomeEmpresa();
		curso = obj.getCurso();
		semestre = obj.getSemestre();
		email = obj.getEmail();
		tipoVaga = obj.getTipoVaga();
		cargaHoraria = obj.getCargaHoraria();
		remuneracao = obj.getRemuneracao();
		beneficios = obj.getBeneficios();
		requisitos = obj.getRequisitos();
		idUsuario = obj.getIdUsuario().getId();
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

	public String getCurso() {
		return curso;
	}

	public void setCurso(String curso) {
		this.curso = curso;
	}

	public Integer getSemestre() {
		return semestre;
	}

	public void setSemestre(Integer semestre) {
		this.semestre = semestre;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public TipoVaga getTipoVaga() {
		return tipoVaga;
	}

	public void setTipoVaga(TipoVaga tipoVaga) {
		this.tipoVaga = tipoVaga;
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

	public Integer getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}

	
}
