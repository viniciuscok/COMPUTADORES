package br.com.pirelli.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.pirelli.filter.TonerFilter;
import br.com.pirelli.model.Toner;
import br.com.pirelli.repository.Filiais;
import br.com.pirelli.repository.Marcas;
import br.com.pirelli.repository.Modelos;
import br.com.pirelli.repository.Toners;
import br.com.pirelli.service.CadastroTonerService;
import br.com.pirelli.service.exception.ImpossivelExcluirTonerException;
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
		mv.addObject("modelos", modelos.buscarModeloTonerOrdemCrescente());
		mv.addObject("marcas", marcas.marcaOrdemCrescente());
		
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
			if(toner.getCodigo() == null)
			{
				cadastroTonerService.salvar(toner);
				attributes.addFlashAttribute("mensagem", "Toner salvo com sucesso.");
			}else
			{
				cadastroTonerService.salvar(toner);
				attributes.addFlashAttribute("mensagem", "Toner salvo com sucesso.");
			}
			
		}catch(TonerJaCadastradoException e)
		{
			result.rejectValue("modelo", e.getMessage(), e.getMessage());
			return novo(toner);
		}
		
		
		
		return new ModelAndView("redirect:/toners/novo");
		
	}
	
	@GetMapping
	public ModelAndView pesquisar(TonerFilter tonerFilter, @PageableDefault(size=10) Pageable pageable, HttpServletRequest httpServletRequest)
	{
		ModelAndView mv = new ModelAndView("toner/PesquisaToners");
		mv.addObject("modelos", modelos.buscarModeloTonerOrdemCrescente());
		
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
	
	
	@DeleteMapping("/{codigo}")
	public @ResponseBody ResponseEntity<?> excluir(@PathVariable("codigo") Toner toner)
	{
		try
		{
			cadastroTonerService.excluir(toner);
		}catch(ImpossivelExcluirTonerException e)
		{
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		
		
		return ResponseEntity.ok().build();
				
	}
	

}
