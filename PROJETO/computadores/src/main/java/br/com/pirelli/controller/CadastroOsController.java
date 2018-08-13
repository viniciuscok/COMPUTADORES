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
import br.com.pirelli.model.Os;
import br.com.pirelli.model.TipoEquipamento;
import br.com.pirelli.repository.Computadores;
import br.com.pirelli.service.CadastroOsService;
import br.com.pirelli.service.exception.NomeFilialJaCadastradoException;

@Controller
@RequestMapping("/os")
public class CadastroOsController 
{
	@Autowired
	private Computadores computadores;
	
	@Autowired
	private CadastroOsService cadastroOsService;
	
	@GetMapping("/nova")
	public ModelAndView nova(Os os)
	{
		ModelAndView mv = new ModelAndView("os/CadastroOs");
		mv.addObject("tipoEquipamentos", TipoEquipamento.values());
		mv.addObject("computadores", computadores.findAll());
		
		return mv;
	}
	
	@PostMapping(value= {"/nova", "{\\d+}"})
	public ModelAndView salvar(@Valid Os os, BindingResult result, RedirectAttributes attributes)
	{
		if(result.hasErrors())
		{
			return nova(os);
		}
		
		try
		{
			if(os.getCodigo() == null)
			{
				os.setEquipamento(new Computador());
				cadastroOsService.salvar(os);
				attributes.addFlashAttribute("mensagem", "os salva com sucesso");
			}else
			{
				cadastroOsService.salvar(os);
				attributes.addFlashAttribute("mensagem", "os editada com sucesso");
			}
		
		}catch(NomeFilialJaCadastradoException e)
		{
			result.rejectValue("nome", e.getMessage(), e.getMessage());
			return nova(os);
		}
		
		return new ModelAndView("redirect:/os/nova");
	}

}
