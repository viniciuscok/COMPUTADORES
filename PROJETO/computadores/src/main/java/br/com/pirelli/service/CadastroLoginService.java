package br.com.pirelli.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import br.com.pirelli.model.Login;
import br.com.pirelli.repository.Logins;
import br.com.pirelli.service.exception.EmailLoginJaCadastradoException;
import br.com.pirelli.service.exception.SenhaLoginObrigatoriaException;

@Service
public class CadastroLoginService 
{
	@Autowired
	private Logins logins;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Transactional
	public Login salvar(Login login)
	{
		Optional<Login> emailPresente = logins.findByEmailStartingWithIgnoreCase(login.getEmail());
		
		if(login.getCodigo() == null && emailPresente.isPresent())
		{
			throw new EmailLoginJaCadastradoException("E-mail já cadastrado");
		}
		
		if(login.isNovo() && StringUtils.isEmpty(login.getSenha()))
		{
			throw new SenhaLoginObrigatoriaException("Senha obrigatória para novo Login.");
		}
		
		if(login.isNovo() || !StringUtils.isEmpty(login.getSenha()))
		{
			login.setSenha(this.passwordEncoder.encode(login.getSenha()));	
		}else if(StringUtils.isEmpty(login.getSenha()))
		{
			login.setSenha(emailPresente.get().getSenha());
		}
		login.setConfirmacaoSenha(login.getSenha());
		
		if (!login.isNovo() && login.getAtivo() == null) {
			login.setAtivo(emailPresente.get().getAtivo());
		}
		return logins.save(login);
		
	}
	
	@Transactional
	public void alterarStatus(Long[] codigos, StatusLogin statusLogin) {
		statusLogin.executar(codigos, logins);
	}

}
