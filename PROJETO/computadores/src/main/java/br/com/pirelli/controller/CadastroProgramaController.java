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

import br.com.pirelli.model.Programa;
import br.com.pirelli.service.CadastroProgramaService;
import br.com.pirelli.service.exception.ProgramaJaCadastradoException;

@Controller
@RequestMapping("/programas")
public class CadastroProgramaController 
{
	@Autowired
	private CadastroProgramaService cadastroProgramaService;
	
	@GetMapping("/novo")
	public ModelAndView novo(Programa programa)
	{
		ModelAndView mv = new ModelAndView("programa/CadastroPrograma");
		return mv;
	}
	
	@PostMapping("/novo")
	public ModelAndView novo(@Valid Programa programa, BindingResult result, RedirectAttributes attributes)
	{
		if(result.hasErrors())
		{
			return novo(programa);
		}
		
		try
		{
			cadastroProgramaService.salvar(programa);
		}catch(ProgramaJaCadastradoException e)
		{
			result.rejectValue("nome", e.getMessage(), e.getMessage());
			return novo(programa);
		}
		
		attributes.addFlashAttribute("mensagem", "Programa cadastrado com sucesso");
		
		return new ModelAndView("redirect:/programas/novo");
	}

}
