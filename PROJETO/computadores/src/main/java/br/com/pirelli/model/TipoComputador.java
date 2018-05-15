package br.com.pirelli.model;

public enum TipoComputador 
{
	DESKTOP("Desktop", "DESKTOP"),
	NOTEBOOK("Notebook", "NOTEBOOK"),
	TOUCH("Touch", "TOUCH");
	
	private String descricao;
	private String tipo;
	
	private TipoComputador(String descricao, String tipo) 
	{
		this.descricao = descricao;
		this.tipo = tipo;
	}

	public String getDescricao() {
		return descricao;
	}

	public String getTipo() {
		return tipo;
	}

	

	

	
	
	
	
	
	
}
