package br.com.pirelli.service.exception;

public class ImpossivelExcluirComputadorException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public ImpossivelExcluirComputadorException(String message)
	{
		super(message);
	}

}
