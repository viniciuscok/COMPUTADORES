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

import br.com.pirelli.filter.TipoModeloFilter;
import br.com.pirelli.model.TipoModelo;
import br.com.pirelli.repository.TipoModelos;
import br.com.pirelli.service.CadastroTipoModeloService;
import br.com.pirelli.service.exception.TipoModeloJaCadastradoException;

@Controller
@RequestMapping("/tipomodelos")
public class CadastroTipoModeloController 
{
	@Autowired
	private CadastroTipoModeloService cadastroTipoModeloService;
	
	@Autowired
	private TipoModelos tipoModelos;
	
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
	
	@GetMapping
	public ModelAndView pesquisar(TipoModeloFilter tipoModeloFilter, @PageableDefault(size=10) Pageable pageable, HttpServletRequest httpServletRequest)
	{
		ModelAndView mv = new ModelAndView("tipoModelo/PesquisaTipoModelos");
		
		PageWrapper<TipoModelo> pagina = new PageWrapper<>(tipoModelos.findByNome(tipoModeloFilter.getNome(), pageable), httpServletRequest);
		
		mv.addObject("pagina", pagina);
		
		return mv;
	}

}
