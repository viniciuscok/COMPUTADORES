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

import br.com.pirelli.model.Toner;
import br.com.pirelli.repository.Filiais;
import br.com.pirelli.repository.Marcas;
import br.com.pirelli.repository.Modelos;
import br.com.pirelli.service.CadastroTonerService;
import br.com.pirelli.service.exception.TonerException;

@Controller
@RequestMapping("/toners")
public class CadastroTonerController 
{
	@Autowired
	private CadastroTonerService cadastroTonerService;
	
	@Autowired
	private Filiais filiais;
	
	@Autowired
	private Marcas marcas;
	
	@Autowired
	private Modelos modelos;
	
	
	@GetMapping("/novo")
	public ModelAndView novo(Toner toner)
	{
		ModelAndView mv = new ModelAndView("toner/CadastroToner");
		mv.addObject("filiais", filiais.findAll());
		mv.addObject("modelos", modelos.findAll());
		mv.addObject("marcas", marcas.findAll());
		
		return mv;
	}
	
	@PostMapping("/novo")
	public ModelAndView salvar(@Valid Toner toner, BindingResult result, RedirectAttributes attributes)
	{
		if(result.hasErrors())
		{
			return novo(toner);
		}
		
		try
		{
			cadastroTonerService.salvar(toner);
		}catch(TonerException e)
		{
			result.rejectValue("modelo", e.getMessage(), e.getMessage());
		}
		
		attributes.addFlashAttribute("mensagem", "Toner inserido com sucesso");
		
		return new ModelAndView("redirect:/toners/novo");
		
	}

}
