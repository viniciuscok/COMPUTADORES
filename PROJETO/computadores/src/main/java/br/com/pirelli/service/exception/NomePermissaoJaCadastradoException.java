package br.com.pirelli.service.exception;

public class NomePermissaoJaCadastradoException extends RuntimeException 
{

	private static final long serialVersionUID = 1L;
	
	public NomePermissaoJaCadastradoException(String message)
	{
		super(message);
	}

}
