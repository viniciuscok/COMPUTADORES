package br.com.pirelli.service.exception;

public class ImpossivelExcluirImpressora extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public ImpossivelExcluirImpressora(String message) 
	{
		super(message);
	}

}
