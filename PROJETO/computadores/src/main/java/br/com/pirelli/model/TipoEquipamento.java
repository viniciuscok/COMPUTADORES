package br.com.pirelli.model;

public enum TipoEquipamento 
{
	COMPUTADOR("Computador"),
	IMPRESSORA("Impressora");
	
	private String descricao;
	
	private TipoEquipamento(String descricao) 
	{
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
	

}
