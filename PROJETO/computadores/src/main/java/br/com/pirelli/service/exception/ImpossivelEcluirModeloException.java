package br.com.pirelli.service.exception;

public class ImpossivelEcluirModeloException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public ImpossivelEcluirModeloException(String message)
	{
		super(message);
	}

}
