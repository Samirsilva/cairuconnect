package com.cairu.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name= "nec_vagas")
public class OportunidadeProfissional implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name= "id", unique = true, nullable = false)
	private Integer id;
	
	@Column(name = "nomeempresa")
	private String nomeEmpresa;
		
	@Column(name = "sexo")
	private String sexo;
	
	@Column(name= "emailempresa", unique = true)
	private String email;
	
	@Column(name = "tipovaga")
	private String tipoVaga;
	
	@Column(name = "ch")
	private Integer cargaHoraria;
	
	@Column(name = "remuneracao")
	private BigDecimal remuneracao;
	
	@Column(name = "beneficios")
	private String beneficios;
	
	@Column(name = "competencia")
	private String requisitos;
	
	@Column(name = "datainicio")
	private LocalDateTime dataInicio;
	
	@Column(name = "datatermino")
	private LocalDateTime dataTermino;
	
	@Column(name = "disponibilidade")
	private String disponibilidade;
	
	@Column(name = "qtvagas")
	private Integer quantidadeVagas;
	
	@Column(name = "linkimagem")
	private String linkImg;
	
	public OportunidadeProfissional() {

	}

	public OportunidadeProfissional(Integer id, String nomeEmpresa, String sexo,
			String email, String tipoVaga, Integer cargaHoraria, BigDecimal remuneracao, String beneficios,
			String requisitos, LocalDateTime dataInicio, LocalDateTime dataTermino, String disponibilidade,
			Integer quantidadeVagas, String linkImg) {
		super();
		this.id = id;
		this.nomeEmpresa = nomeEmpresa;
		this.sexo = sexo;
		this.email = email;
		this.tipoVaga = tipoVaga;
		this.cargaHoraria = cargaHoraria;
		this.remuneracao = remuneracao;
		this.beneficios = beneficios;
		this.requisitos = requisitos;
		this.dataInicio = dataInicio;
		this.dataTermino = dataTermino;
		this.disponibilidade = disponibilidade;
		this.quantidadeVagas = quantidadeVagas;
		this.linkImg = linkImg;
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

	public String getTipoVaga() {
		return tipoVaga;
	}

	public void setTipoVaga(String tipoVaga) {
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

	public void setRequisitos(String requisitos) {
		this.requisitos = requisitos;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public LocalDateTime getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(LocalDateTime dataInicio) {
		this.dataInicio = dataInicio;
	}

	public LocalDateTime getDataTermino() {
		return dataTermino;
	}

	public void setDataTermino(LocalDateTime dataTermino) {
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
