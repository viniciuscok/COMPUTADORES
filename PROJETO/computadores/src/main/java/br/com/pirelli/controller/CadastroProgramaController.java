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

import br.com.pirelli.filter.ProgramaFilter;
import br.com.pirelli.model.Programa;
import br.com.pirelli.repository.Programas;
import br.com.pirelli.service.CadastroProgramaService;
import br.com.pirelli.service.exception.ImpossivelExcluirProgramaException;
import br.com.pirelli.service.exception.ProgramaJaCadastradoException;

@Controller
@RequestMapping("/programas")
public class CadastroProgramaController 
{
	@Autowired
	private CadastroProgramaService cadastroProgramaService;
	
	@Autowired
	private Programas programas;
	
	@GetMapping("/novo")
	public ModelAndView novo(Programa programa)
	{
		ModelAndView mv = new ModelAndView("programa/CadastroPrograma");
		return mv;
	}
	
	@PostMapping(value= {"/novo", "{\\d+}"})
	public ModelAndView novo(@Valid Programa programa, BindingResult result, RedirectAttributes attributes)
	{
		if(result.hasErrors())
		{
			return novo(programa);
		}
		
		try
		{
			if(programa.getCodigo() == null)
			{
				
				cadastroProgramaService.salvar(programa);
				attributes.addFlashAttribute("mensagem", "Programa salvo com sucesso.");
			}else
			{
				cadastroProgramaService.salvar(programa);
				attributes.addFlashAttribute("mensagem", "Programa editado com sucesso.");
			}
			
		}catch(ProgramaJaCadastradoException e)
		{
			result.rejectValue("nome", e.getMessage(), e.getMessage());
			return novo(programa);
		}
		
		
		
		return new ModelAndView("redirect:/programas/novo");
	}

	@GetMapping
	public ModelAndView pesquisar(ProgramaFilter programaFilter, @PageableDefault(size=2) Pageable pageable, HttpServletRequest httpServletRequest)
	{
		ModelAndView mv = new ModelAndView("programa/PesquisaProgramas");
		
		PageWrapper<Programa> pagina = new PageWrapper<>(programas.findByNome(programaFilter.getNome(), pageable), httpServletRequest);
		
		mv.addObject("pagina", pagina);
		
		return mv;
	}
	
	@GetMapping("/{codigo}")
	public ModelAndView editar(@PathVariable("codigo") Programa programa)
	{
		ModelAndView mv = novo(programa);
		mv.addObject(programa);
		return mv;
	}
	
	@DeleteMapping("/{codigo}")
	public @ResponseBody ResponseEntity<?> excluir(@PathVariable("codigo") Programa programa)
	{
		try
		{
			cadastroProgramaService.excluir(programa);
		}catch(ImpossivelExcluirProgramaException e)
		{
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		
		
		return ResponseEntity.ok().build();
	}
	
}
