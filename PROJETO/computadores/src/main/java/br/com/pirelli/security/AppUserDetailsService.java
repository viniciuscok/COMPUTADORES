package br.com.pirelli.security;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.pirelli.model.Login;
import br.com.pirelli.repository.Logins;

@Service
public class AppUserDetailsService implements UserDetailsService
{

	@Autowired
	private Logins logins;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException 
	{
		Optional<Login> loginOptional = logins.verificarLogin(email);
		Login login = loginOptional.orElseThrow(() -> new UsernameNotFoundException("Usuário e/ou senha incorretos"));
		return new User(login.getEmail(), login.getSenha(), getPermissoes(login));
	}

	private Collection<? extends GrantedAuthority> getPermissoes(Login login) 
	{
		
		Set<SimpleGrantedAuthority> authorities = new HashSet<>();
		
		List<String> permissoes = logins.permissoes(login);
		permissoes.forEach(p -> authorities.add(new SimpleGrantedAuthority(p.toUpperCase())));
		
		return authorities;
	}

}
