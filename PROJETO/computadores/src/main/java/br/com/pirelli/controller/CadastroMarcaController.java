package br.com.pirelli.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.pirelli.filter.MarcaFilter;
import br.com.pirelli.model.Marca;
import br.com.pirelli.repository.Marcas;
import br.com.pirelli.service.CadastroMarcaService;
import br.com.pirelli.service.exception.MarcaJaCadastradaException;

@Controller
@RequestMapping("/marcas")
public class CadastroMarcaController 
{
	@Autowired
	private CadastroMarcaService cadastroMarcaService;
	
	@Autowired
	private Marcas marcas;
	
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
	
	@GetMapping
	public ModelAndView pesquisar(MarcaFilter marcaFilter, @PageableDefault(size=10) Pageable pageable, HttpServletRequest httpServletRequest)
	{
		ModelAndView mv = new ModelAndView("marca/PesquisaMarcas");
		
		PageWrapper<Marca> pagina = new PageWrapper<>(marcas.findByNome(marcaFilter.getNome(), pageable), httpServletRequest);
		
		mv.addObject("pagina", pagina);
		
		return mv;
	}
}
