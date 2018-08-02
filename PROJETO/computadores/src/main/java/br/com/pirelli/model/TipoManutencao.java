package br.com.pirelli.model;

public enum TipoManutencao 
{
	CORRETIVA("Corretiva"),
	PREVENTIVA("Preventiva");
	
	private String descricao;
	
	private TipoManutencao(String descricao) 
	{
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
	
	

}
