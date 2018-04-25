package br.com.pirelli.model;

public enum So 
{
	WINDOWS98_32BITS("Windows 98"),
	WINDOWS2000_32BITS("Windows 2000"),
	WINDOWSXP_32BITS("Windows xp"),
	WINDOWS7_32BITS("Windows 7 32bits"),
	WINDOWS7_64BITS("Windows 7 64bits"),
	WINDOWSVISTA_32BITS("Windows vista 32bits"),
	WINDOWSVISTA_64BITS("Windows vista 64bits"),
	WINDOWS8_32BITS("Windows 8 32bits"),
	WINDOWS8_64BITS("Windows 8 64bits"),
	WINDOWS10_32BITS("Windwos 10 32bits"),
	WINDOWS10_64BITS("Windwos 10 64bits");
	
	
	private String descricao;
	
	private So(String descricao) 
	{
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
	
	
	

}
