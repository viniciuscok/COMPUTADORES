package br.com.pirelli.model;

public enum StatusManutencao 
{
	ABERTO("Em Aberto"),
	SEMSOLUCAO("Sem solução"),
	FECHADO("Fechado");
	
	private String descricao;
	
	private StatusManutencao(String descricao) 
	{
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
	
	

}
