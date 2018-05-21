package br.com.pirelli.service.exception;

public class SenhaLoginObrigatoriaException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public SenhaLoginObrigatoriaException(String message) 
	{
		super(message);
	}

}
