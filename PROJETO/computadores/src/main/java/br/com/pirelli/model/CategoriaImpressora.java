package br.com.pirelli.model;

public enum CategoriaImpressora 
{
	IMPRESSORA_ESCRITORIO("Impressora de Escritório"),
	IMPRESSORA_TERMICA("Impressora Térmica");
	
	private String descricao;
	
	private CategoriaImpressora(String descricao) 
	{
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	

}
