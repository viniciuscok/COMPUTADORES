package br.com.pirelli.service.exception;

public class NomegrupoJaCadastradoException extends RuntimeException 
{

	private static final long serialVersionUID = 1L;
	
	public NomegrupoJaCadastradoException(String message) 
	{
		super(message);
	}

}
