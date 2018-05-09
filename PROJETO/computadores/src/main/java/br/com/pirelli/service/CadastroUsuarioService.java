package br.com.pirelli.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.pirelli.model.Usuario;
import br.com.pirelli.repository.Usuarios;
import br.com.pirelli.service.exception.UsuarioJaCadastradoException;

@Service
public class CadastroUsuarioService 
{
	@Autowired
	private Usuarios usuarios;
	
	public Usuario salvar(Usuario usuario)
	{
		Optional<Usuario> optional = usuarios.findByNomeStartingWithIgnoreCase(usuario.getNome());
		if(optional.isPresent() && usuario.getCodigo() == null)
		{
			throw new UsuarioJaCadastradoException("Usuário já cadastrado");
		}
		
		return usuarios.saveAndFlush(usuario);
	}
}
