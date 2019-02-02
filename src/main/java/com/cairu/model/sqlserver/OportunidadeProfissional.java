package com.cairu.model.sqlserver;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.cairu.model.enums.TipoVaga;
import com.cairu.model.mysql.Usuario;
import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class OportunidadeProfissional implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name= "id", unique = true, nullable = false)
	private Integer id;
	private String nomeEmpresa;
	private String curso;
	private Integer semestre;
	
	@Column(unique = true)
	private String email;
	private Integer tipoVaga;
	private Integer cargaHoraria;
	private BigDecimal remuneracao;
	private String beneficios;
	private String requisitos;

	@JsonBackReference
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "usuario_oportunidade")
	private Usuario idUsuario;
	
	public OportunidadeProfissional() {

	}

	public OportunidadeProfissional(Integer id, String nomeEmpresa, String curso, Integer semestre, String email,
			TipoVaga tipoVaga, Integer cargaHoraria, BigDecimal remuneracao, String beneficios, String requisitos, Usuario usuario) {
		super();
		this.id = id;
		this.nomeEmpresa = nomeEmpresa;
		this.curso = curso;
		this.semestre = semestre;
		this.email = email;
		this.tipoVaga = (tipoVaga == null) ? null : tipoVaga.getCod();
		this.cargaHoraria = cargaHoraria;
		this.remuneracao = remuneracao;
		this.beneficios = beneficios;
		this.requisitos = requisitos;
		this.idUsuario = usuario;
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
		return TipoVaga.toEnum(tipoVaga);
	}

	public void setTipoVaga(TipoVaga tipoVaga) {
		this.tipoVaga = tipoVaga.getCod();
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

	public void setRequisitos(String requisitos) {
		this.requisitos = requisitos;
	}

	public Usuario getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Usuario idUsuario) {
		this.idUsuario = idUsuario;
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
		OportunidadeProfissional other = (OportunidadeProfissional) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
