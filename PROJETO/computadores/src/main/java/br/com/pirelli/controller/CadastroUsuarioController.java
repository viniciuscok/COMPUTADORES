package br.com.pirelli.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.pirelli.filter.UsuarioFilter;
import br.com.pirelli.model.Maquina;
import br.com.pirelli.model.Usuario;
import br.com.pirelli.repository.Usuarios;
import br.com.pirelli.service.CadastroUsuarioService;
import br.com.pirelli.service.exception.UsuarioJaCadastradoException;

@Controller
@RequestMapping("/usuarios")
public class CadastroUsuarioController 
{
	
	@Autowired
	private CadastroUsuarioService cadastroUsuarioService;
	
	@Autowired
	private Usuarios usuarios;
	
	@GetMapping("/novo")
	public ModelAndView novo(Usuario usuario)
	{
		ModelAndView mv = new ModelAndView("usuario/CadastroUsuario");
		
		return mv;
	}
	
	@PostMapping(value= {"/novo", "{\\d+}"})
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
	
	@GetMapping
	public ModelAndView pesquisar(UsuarioFilter usuarioFilter, @PageableDefault(size=10) Pageable pageable, HttpServletRequest httpServletRequest)
	{
		ModelAndView mv = new ModelAndView("usuario/PesquisaUsuarios");
		
		PageWrapper<Usuario> pagina = new PageWrapper<>(usuarios.findByNome(usuarioFilter.getNome(), pageable), httpServletRequest);
		
		mv.addObject("pagina", pagina);
		
		return mv;
	}
	
	@GetMapping("/{codigo}")
	public ModelAndView editar(@PathVariable("codigo") Usuario usuario)
	{
		ModelAndView mv = novo(usuario);
		mv.addObject(usuario);
		
		return mv;
	}
}
