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

import br.com.pirelli.model.Modelo;
import br.com.pirelli.repository.TipoModelos;
import br.com.pirelli.service.CadastroModeloService;
import br.com.pirelli.service.exception.ModeloJacadastradoException;

@Controller
@RequestMapping("/modelos")
public class CadastroModeloController 
{
	@Autowired
	private TipoModelos tipoModelos;
	
	@Autowired
	private CadastroModeloService cadastroModeloService;
	
	@GetMapping("/novo")
	public ModelAndView novo(Modelo modelo)
	{
		ModelAndView mv = new ModelAndView("modelo/CadastroModelo");
		mv.addObject("equipamentos", tipoModelos.findAll());
		
		return mv;
	}
	
	@PostMapping("/novo")
	public ModelAndView salvar(@Valid Modelo modelo, BindingResult result, RedirectAttributes attributes)
	{
		if(result.hasErrors())
		{
			return novo(modelo);
		}
		
		try
		{
			cadastroModeloService.salvar(modelo);
		}catch(ModeloJacadastradoException e)
		{
			result.rejectValue("nome", e.getMessage(), e.getMessage());
			return novo(modelo);
		}
		
		attributes.addFlashAttribute("mensagem", "Modelo cadastrado com sucesso");
		
		return new ModelAndView("redirect:/modelos/novo");
	}

}
