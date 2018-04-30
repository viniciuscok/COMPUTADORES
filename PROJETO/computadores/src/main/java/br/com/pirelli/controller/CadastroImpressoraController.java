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

import br.com.pirelli.model.Impressora;
import br.com.pirelli.model.CategoriaImpressora;
import br.com.pirelli.model.Status;
import br.com.pirelli.model.TipoImpressora;
import br.com.pirelli.repository.Filiais;
import br.com.pirelli.repository.Marcas;
import br.com.pirelli.repository.Modelos;
import br.com.pirelli.repository.Setores;
import br.com.pirelli.repository.Toners;
import br.com.pirelli.service.CadastroImpressoraService;
import br.com.pirelli.service.exception.ComputadorJaCadastradoException;
import br.com.pirelli.service.exception.ImpressoraJaExisteException;

@Controller
@RequestMapping("/impressoras")
public class CadastroImpressoraController 
{
	@Autowired
	private CadastroImpressoraService cadastroImpressoraService;
	
	@Autowired
	private Filiais filiais;
	
	@Autowired
	private Modelos modelos;
	
	@Autowired
	private Marcas marcas;
	
	@Autowired
	private Toners toners;
	
	@Autowired
	private Setores setores;
	
	@GetMapping("/novo")
	public ModelAndView novo(Impressora impressora)
	{
		ModelAndView mv = new ModelAndView("impressora/CadastroImpressora");
		mv.addObject("filiais", filiais.findAll());
		mv.addObject("modelos", modelos.findAll());
		mv.addObject("marcas", marcas.findAll());
		mv.addObject("toners", toners.findAll());
		mv.addObject("statusImpressora", Status.values());
		mv.addObject("tipoImpressoras", TipoImpressora.values());
		mv.addObject("categorias", CategoriaImpressora.values());
		mv.addObject("setores", setores.findAll());
		
		return mv;
	}
	
	@PostMapping("/novo")
	public ModelAndView salvar(@Valid Impressora impressora, BindingResult result, RedirectAttributes attributes)
	{
		if(result.hasErrors())
		{
			return novo(impressora);
		}
		
		try
		{
			cadastroImpressoraService.salvar(impressora);
		}catch(ImpressoraJaExisteException e)
		{
			result.rejectValue("enderecoIP", e.getMessage(), e.getMessage());
			return novo(impressora);
		}
		
		attributes.addFlashAttribute("mensagem", "Impressora cadastrada com sucesso");
		
		return new ModelAndView("redirect:/impressoras/novo");
	}
	
	
}
