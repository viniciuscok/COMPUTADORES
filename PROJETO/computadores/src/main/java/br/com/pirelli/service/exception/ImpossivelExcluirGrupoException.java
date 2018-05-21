package br.com.pirelli.service.exception;

public class ImpossivelExcluirGrupoException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public ImpossivelExcluirGrupoException(String message) 
	{
		super(message);
	}

}
