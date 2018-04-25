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

import br.com.pirelli.model.TipoModelo;
import br.com.pirelli.service.CadastroTipoModeloService;
import br.com.pirelli.service.exception.TipoModeloJaCadastradoException;

@Controller
@RequestMapping("/tipomodelos")
public class CadastroTipoModeloController 
{
	@Autowired
	private CadastroTipoModeloService cadastroTipoModeloService;
	
	@GetMapping("/novo")
	public ModelAndView novo(TipoModelo tipoModelo)
	{
		ModelAndView mv = new ModelAndView("tipoModelo/CadastroTipoModelo");
		
		return mv;
	}
	
	@PostMapping("/novo")
	public ModelAndView salvar(@Valid TipoModelo tipoModelo, BindingResult result, RedirectAttributes attributes)
	{
		if(result.hasErrors())
		{
			return novo(tipoModelo);
		}
		
		try
		{
			cadastroTipoModeloService.salvar(tipoModelo);
		}catch(TipoModeloJaCadastradoException e)
		{
			result.rejectValue("nome", e.getMessage(), e.getMessage());
			
			return novo(tipoModelo);
		}
		
		attributes.addFlashAttribute("mensagem", "Equipamento Cadastrado com sucesso");
		
		return new ModelAndView("redirect:/tipomodelos/novo");
	}

}
