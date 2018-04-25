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

import br.com.pirelli.model.Computador;
import br.com.pirelli.model.Setor;
import br.com.pirelli.model.So;
import br.com.pirelli.model.Status;
import br.com.pirelli.model.TipoComputador;
import br.com.pirelli.repository.Filiais;
import br.com.pirelli.repository.Marcas;
import br.com.pirelli.repository.Modelos;
import br.com.pirelli.repository.Programas;
import br.com.pirelli.repository.Setores;
import br.com.pirelli.repository.Usuarios;
import br.com.pirelli.service.CadastroComputadorService;
import br.com.pirelli.service.exception.ComputadorJaCadastradoException;


@Controller
@RequestMapping("/computadores")
public class CadastroComputadorController 
{
	@Autowired
	private CadastroComputadorService cadastroComputadorService;
	
	@Autowired
	private Filiais filiais;
	
	@Autowired
	private Usuarios usuarios;
	
	@Autowired
	private Modelos modelos;
	
	@Autowired
	private Marcas marcas;
	
	@Autowired
	private Setores setores;
	
	@Autowired
	private Programas programas;
	
	@GetMapping("/novo")
	public ModelAndView novo(Computador computador)
	{
		ModelAndView mv = new ModelAndView("computador/CadastroComputador");
		mv.addObject("tipoComputadores", TipoComputador.values());
		mv.addObject("sistemas", So.values());
		mv.addObject("statusComputadores", Status.values());
		mv.addObject("filiais", filiais.findAll());
		mv.addObject("usuarios", usuarios.findAll());
		mv.addObject("marcas", marcas.findAll());
		mv.addObject("modelos", modelos.findAll());
		mv.addObject("setores", setores.findAll());
		mv.addObject("programas", programas.findAll());
		
		return mv;
	}
	
	@PostMapping("/novo")
	public ModelAndView salvar(@Valid Computador computador, BindingResult result, RedirectAttributes attributes)
	{
		if(result.hasErrors())
		{
			return novo(computador);
		}
		
		try
		{
			cadastroComputadorService.salvar(computador);
		}catch(ComputadorJaCadastradoException e)
		{
			result.rejectValue("nome", e.getMessage(), e.getMessage());
			return novo(computador);
		}
		
		attributes.addFlashAttribute("mensagem", "Computador cadastrado com sucesso");
		
		return new ModelAndView("redirect:/computadores/novo");
	}
}
