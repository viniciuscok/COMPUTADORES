package br.com.pirelli.service.exception;

public class ImpossivelExcluirMaquinaException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public ImpossivelExcluirMaquinaException(String message)
	{
		super(message);
	}

}
