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

import br.com.pirelli.filter.MaquinaFilter;
import br.com.pirelli.model.Maquina;
import br.com.pirelli.repository.Computadores;
import br.com.pirelli.repository.Impressoras;
import br.com.pirelli.repository.Maquinas;
import br.com.pirelli.repository.Setores;
import br.com.pirelli.service.CadastroMaquinaService;
import br.com.pirelli.service.exception.ImpossivelExcluirMaquinaException;
import br.com.pirelli.service.exception.MaquinaJaCadastradaException;
import br.com.pirelli.service.exception.SigmaMaquinaJaCadastradaException;

@Controller
@RequestMapping("/maquinas")
public class CadastroMaquinaController 
{
	@Autowired
	private CadastroMaquinaService cadastroMaquinaService;
	@Autowired
	private Computadores computadores;
	@Autowired
	private Impressoras impressoras;
	@Autowired
	private Maquinas maquinas;
	@Autowired
	private Setores setores;
	
	
	@GetMapping("/nova")
	public ModelAndView nova(Maquina maquina)
	{
		ModelAndView mv = new ModelAndView("maquina/CadastroMaquina");
		mv.addObject("computadores", computadores.computadorOrdemCrescente());
		mv.addObject("impressoras", impressoras.impressoraTermicaOrdemCrescente());
		mv.addObject("setores", setores.setorOrdemCrescente());
		
		
		return mv;
	}
	
	@PostMapping(value= {"/nova", "{\\d+}"})
	public ModelAndView salvar(@Valid Maquina maquina, BindingResult result, RedirectAttributes attributes)
	{
		
		if(result.hasErrors())
		{
			return nova(maquina);
		}
		
		try
		{
			if(maquina.getCodigo() == null)
			{
				cadastroMaquinaService.salvar(maquina);
				attributes.addFlashAttribute("mensagem", "Maquina salva com sucesso.");
			}else
			{
				cadastroMaquinaService.salvar(maquina);
				attributes.addFlashAttribute("mensagem", "Maquina editada com sucesso.");
			}
		}catch (MaquinaJaCadastradaException e) 
		{
			result.rejectValue("nome", e.getMessage(), e.getMessage());
			return nova(maquina);
		}catch(SigmaMaquinaJaCadastradaException e)
		{
			result.rejectValue("sigma", e.getMessage(), e.getMessage());
			return nova(maquina);
		}
		
		return new ModelAndView("redirect:/maquinas/nova");
	}
	
	@GetMapping
	public ModelAndView pesquisa(MaquinaFilter maquinaFilter, @PageableDefault(size=10) Pageable pageable, HttpServletRequest httpServletRequest)
	{
		ModelAndView mv = new ModelAndView("maquina/PesquisaMaquinas");
		mv.addObject("computadores", computadores.computadorOrdemCrescente());
		mv.addObject("impressoras", impressoras.findAll());
		
		PageWrapper<Maquina> pagina = new PageWrapper<>(maquinas.filtro(maquinaFilter, pageable), httpServletRequest);
		mv.addObject("pagina", pagina);
		return mv;
	}
	
	@GetMapping("/{codigo}")
	public ModelAndView editar(@PathVariable("codigo") Maquina maquina)
	{
		ModelAndView mv = nova(maquina);
		mv.addObject(maquina);
		
		return mv;
	}
	
	@DeleteMapping("{codigo}")
	public @ResponseBody ResponseEntity<?> excluir(@PathVariable("codigo") Maquina maquina)
	{
		try
		{
			cadastroMaquinaService.excluir(maquina);
		}catch(ImpossivelExcluirMaquinaException e)
		{
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		
		return ResponseEntity.ok().build();
	}

}
