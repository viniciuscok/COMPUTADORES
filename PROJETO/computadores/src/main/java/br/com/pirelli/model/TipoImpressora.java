package br.com.pirelli.model;

public enum TipoImpressora
{
	REDE("Rede"),
	LOCAL("Local");
	
	private String descricao;
	
	private TipoImpressora(String descricao) 
	{
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
	
	

}
