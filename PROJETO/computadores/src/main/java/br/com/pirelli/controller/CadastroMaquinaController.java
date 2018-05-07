package br.com.pirelli.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.pirelli.model.Maquina;
import br.com.pirelli.repository.Computadores;
import br.com.pirelli.service.CadastroMaquinaService;
import br.com.pirelli.service.exception.MaquinaJaCadastradaException;

@Controller
@RequestMapping("/maquinas")
public class CadastroMaquinaController 
{
	@Autowired
	private CadastroMaquinaService cadastroMaquinaService;
	@Autowired
	private Computadores computadores;
	
	
	@GetMapping("/nova")
	public ModelAndView nova(Maquina maquina)
	{
		ModelAndView mv = new ModelAndView("maquina/CadastroMaquina");
		mv.addObject("computadores", computadores.findAll());
		
		return mv;
	}
	
	@PostMapping("/nova")
	public ModelAndView salvar(@Valid Maquina maquina, BindingResult result, RedirectAttributes attributes)
	{
		
		if(result.hasErrors())
		{
			return nova(maquina);
		}
		
		try
		{
			cadastroMaquinaService.salvar(maquina);
		}catch (MaquinaJaCadastradaException e) 
		{
			result.rejectValue("nome", e.getMessage(), e.getMessage());
			return nova(maquina);
		}
		
		attributes.addFlashAttribute("mensagem", "Maquina cadastrada com sucesso");
		
		return new ModelAndView("redirect:/maquinas/nova");
	}

}
