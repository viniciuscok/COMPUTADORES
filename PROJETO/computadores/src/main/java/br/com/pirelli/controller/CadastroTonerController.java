package br.com.pirelli.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.pirelli.filter.ImpressoraFilter;
import br.com.pirelli.filter.TonerFilter;
import br.com.pirelli.model.CategoriaImpressora;
import br.com.pirelli.model.Impressora;
import br.com.pirelli.model.Maquina;
import br.com.pirelli.model.Status;
import br.com.pirelli.model.TipoImpressora;
import br.com.pirelli.model.Toner;
import br.com.pirelli.repository.Filiais;
import br.com.pirelli.repository.Marcas;
import br.com.pirelli.repository.Modelos;
import br.com.pirelli.repository.Toners;
import br.com.pirelli.service.CadastroTonerService;
import br.com.pirelli.service.exception.TonerException;
import br.com.pirelli.service.exception.TonerJaCadastradoException;

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
	
	@Autowired
	private Toners toners;
	
	
	@GetMapping("/novo")
	public ModelAndView novo(Toner toner)
	{
		ModelAndView mv = new ModelAndView("toner/CadastroToner");
		mv.addObject("filiais", filiais.findAll());
		mv.addObject("modelos", modelos.findAll());
		mv.addObject("marcas", marcas.findAll());
		
		return mv;
	}
	
	@PostMapping(value= {"/novo", "{\\d+}"})
	public ModelAndView salvar(@Valid Toner toner, BindingResult result, RedirectAttributes attributes)
	{
		if(result.hasErrors())
		{
			return novo(toner);
		}
		
		try
		{
			cadastroTonerService.salvar(toner);
		}catch(TonerJaCadastradoException e)
		{
			result.rejectValue("modelo", e.getMessage(), e.getMessage());
			return novo(toner);
		}
		
		attributes.addFlashAttribute("mensagem", "Toner inserido com sucesso");
		
		return new ModelAndView("redirect:/toners/novo");
		
	}
	
	@GetMapping
	public ModelAndView pesquisar(TonerFilter tonerFilter, @PageableDefault(size=2) Pageable pageable, HttpServletRequest httpServletRequest)
	{
		ModelAndView mv = new ModelAndView("toner/PesquisaToners");
		mv.addObject("modelos", modelos.findAll());
		
		PageWrapper<Toner> pagina = new PageWrapper<>(toners.filtro(tonerFilter, pageable), httpServletRequest);
		
		mv.addObject("pagina", pagina);
		
		return mv;
		
	}
	
	@GetMapping("/{codigo}")
	public ModelAndView editar(@PathVariable("codigo") Toner toner)
	{
		ModelAndView mv = novo(toner);
		mv.addObject(toner);
		
		return mv;
	}
	

}
