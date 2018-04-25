package br.com.pirelli.service.exception;

public class ProgramaJaCadastradoException extends RuntimeException 
{
	private static final long serialVersionUID = 1L;

	public ProgramaJaCadastradoException(String message) 
	{
		super(message);
	}
}
