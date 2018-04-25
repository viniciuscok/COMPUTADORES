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

import br.com.pirelli.model.Setor;
import br.com.pirelli.service.CadastroSetorService;
import br.com.pirelli.service.exception.SetorJaCadastradoException;

@Controller
@RequestMapping("/setores")
public class CadastroSetorController 
{
	@Autowired
	private CadastroSetorService cadastroSetorService;
	
	@GetMapping("/novo")
	public ModelAndView novo(Setor setor)
	{
		ModelAndView mv = new ModelAndView("setor/CadastroSetor");
		
		return mv;
	}

	@PostMapping("/novo")
	public ModelAndView salvar(@Valid Setor setor, BindingResult result, RedirectAttributes attributes)
	{
		if(result.hasErrors())
		{
			return novo(setor);
		}
		
		try
		{
			cadastroSetorService.salvar(setor);
		}catch (SetorJaCadastradoException e) 
		{
			result.rejectValue("nome", e.getMessage(), e.getMessage());
			return novo(setor);
		}
		
		attributes.addFlashAttribute("mensagem", "Setor cadastrado com sucesso");
		
		return new ModelAndView("redirect:/setores/novo");
	}
}
