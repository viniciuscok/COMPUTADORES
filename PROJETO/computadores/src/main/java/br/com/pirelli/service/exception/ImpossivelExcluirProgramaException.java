package br.com.pirelli.service.exception;

public class ImpossivelExcluirProgramaException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public ImpossivelExcluirProgramaException(String msg) 
	{
		super(msg);
	}

}
