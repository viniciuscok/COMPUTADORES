package br.com.pirelli.model;

public enum TipoComputador 
{
	DESKTOP("Desktop"),
	NOTEBOOK("Notebook"),
	TOUCH("Touch");
	
	private String descricao;
	
	private TipoComputador(String descricao) 
	{
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
	
	
	
}
