package br.com.pirelli.service.exception;

public class SetorJaCadastradoException extends RuntimeException 
{

	private static final long serialVersionUID = 1L;
	
	public SetorJaCadastradoException(String message)
	{
		super(message);
	}

}
