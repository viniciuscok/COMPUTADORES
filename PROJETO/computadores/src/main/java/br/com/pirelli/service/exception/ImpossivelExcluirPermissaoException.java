package br.com.pirelli.service.exception;

public class ImpossivelExcluirPermissaoException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public ImpossivelExcluirPermissaoException(String message) 
	{
		super(message);
	}

}
