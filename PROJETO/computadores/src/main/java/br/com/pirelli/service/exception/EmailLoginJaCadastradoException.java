package br.com.pirelli.service.exception;

public class EmailLoginJaCadastradoException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public EmailLoginJaCadastradoException(String message) 
	{
		super(message);
	}

}
