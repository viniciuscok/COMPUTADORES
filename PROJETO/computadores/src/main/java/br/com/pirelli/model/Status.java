package br.com.pirelli.model;

public enum Status 
{
	ATIVO("Ativo"),
	CHAMADO("Chamado"),
	DESATIVADO("Desativado"),
	MANUTENCAO("Manutenção");
	
	private String descricao;
	
	Status(String descricao)
	{
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

}
