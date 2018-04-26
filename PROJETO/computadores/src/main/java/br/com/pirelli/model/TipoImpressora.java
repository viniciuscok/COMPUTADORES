package br.com.pirelli.model;

public enum TipoImpressora
{
	REDE("Rede", "000.000.000.000"),
	LOCAL("Local");
	
	private String descricao;
	private String enderecoIP;
	
	private TipoImpressora(String descricao) 
	{
		this.descricao = descricao;
	}
	
	private TipoImpressora(String descricao, String enderecoIP) 
	{
		this.descricao = descricao;
		this.enderecoIP = enderecoIP;
	}

	public String getDescricao() {
		return descricao;
	}

	public String getEnderecoIP() {
		return enderecoIP;
	}
	
	

}
