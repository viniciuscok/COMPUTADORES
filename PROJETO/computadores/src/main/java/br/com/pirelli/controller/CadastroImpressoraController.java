package br.com.pirelli.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.pirelli.model.Impressora;
import br.com.pirelli.model.CategoriaImpressora;
import br.com.pirelli.model.Status;
import br.com.pirelli.model.TipoImpressora;
import br.com.pirelli.repository.Filiais;
import br.com.pirelli.repository.Marcas;
import br.com.pirelli.repository.Modelos;
import br.com.pirelli.repository.Toners;
import br.com.pirelli.service.CadastroImpressoraService;

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
		
		return mv;
	}
	
	
}
