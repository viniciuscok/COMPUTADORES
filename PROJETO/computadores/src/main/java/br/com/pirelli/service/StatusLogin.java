package br.com.pirelli.service;

import br.com.pirelli.repository.Logins;

public enum StatusLogin 
{
	ATIVAR {
		@Override
		public void executar(Long[] codigos, Logins logins) {
			logins.findByCodigoIn(codigos).forEach(u -> u.setAtivo(true));
		}
	},
	
	DESATIVAR {
		@Override
		public void executar(Long[] codigos, Logins logins) {
			logins.findByCodigoIn(codigos).forEach(u -> u.setAtivo(false));
		}
	};
	
	public abstract void executar(Long[] codigos, Logins logins);

}
