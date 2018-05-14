package br.com.pirelli.service.exception;

public class ImpossivelExcluirMarcaException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public ImpossivelExcluirMarcaException(String message)
	{
		super(message);
	}

}
