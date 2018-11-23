package com.cairu.model.enums;

public enum TipoVaga {

	EMPREGO(1, "EMPREGO"),
	ESTAGIO(2, "ESTAGIO");
	
	private int cod;
	private String descricao;

	private TipoVaga(int cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}

	public int getCod() {
		return cod;
	}

	public String getDescricao() {
		return descricao;
	}

	public static TipoVaga toEnum(Integer cod) {
	
		if(cod == null) {
		return null;
		}
		for(TipoVaga x : TipoVaga.values()) {
			if(cod.equals(x.getCod())) {
				return x;
			}
		}
		throw new IllegalArgumentException("Id inválido: " + cod); 
	}	
}
