package br.com.pirelli.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.pirelli.model.Usuario;
import br.com.pirelli.service.CadastroUsuarioService;
import br.com.pirelli.service.exception.UsuarioJaCadastradoException;

@Controller
@RequestMapping("/usuarios")
public class CadastroUsuarioController 
{
	
	@Autowired
	private CadastroUsuarioService cadastroUsuarioService;
	
	@GetMapping("/novo")
	public ModelAndView novo(Usuario usuario)
	{
		ModelAndView mv = new ModelAndView("usuario/CadastroUsuario");
		
		return mv;
	}
	
	@PostMapping("/novo")
	public ModelAndView salvar(@Valid Usuario usuario, BindingResult result, RedirectAttributes attributes)
	{
		if(result.hasErrors())
		{
			return novo(usuario);
		}
		
		try
		{
			cadastroUsuarioService.salvar(usuario);
		}catch(UsuarioJaCadastradoException e)
		{
			result.rejectValue("nome", e.getMessage(), e.getMessage());
			
			return novo(usuario);
		}
		
		attributes.addFlashAttribute("mensagem", "Usuário Cadastrado com sucesso");
		
		return new ModelAndView("redirect:/usuarios/novo");
	}
}
