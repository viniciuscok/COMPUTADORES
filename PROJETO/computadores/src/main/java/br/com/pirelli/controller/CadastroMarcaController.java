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

import br.com.pirelli.model.Marca;
import br.com.pirelli.service.CadastroMarcaService;
import br.com.pirelli.service.exception.MarcaJaCadastradaException;

@Controller
@RequestMapping("/marcas")
public class CadastroMarcaController 
{
	@Autowired
	private CadastroMarcaService cadastroMarcaService;
	
	@GetMapping("/nova")
	public ModelAndView nova(Marca marca)
	{
		ModelAndView mv = new ModelAndView("marca/CadastroMarca");
		return mv;
	}

	@PostMapping("/nova")
	public ModelAndView salvar(@Valid Marca marca, BindingResult result, RedirectAttributes attributes)
	{
		if(result.hasErrors())
		{
			return nova(marca);
		}
		
		try
		{
			cadastroMarcaService.salvar(marca);
		}catch(MarcaJaCadastradaException e)
		{
			result.rejectValue("nome", e.getMessage(), e.getMessage());
			return nova(marca);
		}
		
		attributes.addFlashAttribute("mensagem", "Marca inserida com sucesso");
		
		return new ModelAndView("redirect:/marcas/nova");
	}
}
