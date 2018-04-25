package br.com.pirelli.service.exception;

public class ComputadorJaCadastradoException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public ComputadorJaCadastradoException(String message) 
	{
		super(message);
	}

}
