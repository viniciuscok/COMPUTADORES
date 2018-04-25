package br.com.pirelli.service.exception;

public class ImpressoraJaExisteException extends RuntimeException 
{

	private static final long serialVersionUID = 1L;
	
	public ImpressoraJaExisteException(String message) 
	{
		super(message);
	}

}
