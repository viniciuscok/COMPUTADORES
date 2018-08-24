package br.com.pirelli.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.codehaus.groovy.runtime.StackTraceUtils;
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

import br.com.pirelli.filter.OsComputadorFilter;
import br.com.pirelli.model.OsComputador;
import br.com.pirelli.model.StatusManutencao;
import br.com.pirelli.model.TipoManutencao;
import br.com.pirelli.repository.Computadores;
import br.com.pirelli.repository.OsComputadores;
import br.com.pirelli.service.CadastroOsComputadorService;
import br.com.pirelli.service.exception.NomeFilialJaCadastradoException;
import br.com.pirelli.service.exception.SolucaoManutencaoException;
import br.com.pirelli.service.exception.StatusManutencaoException;

@Controller
@RequestMapping("/oscomputadores")
public class CadastroOsComputadorController 
{
	@Autowired
	private Computadores computadores;
	
	@Autowired
	private CadastroOsComputadorService cadastroOsComputadorService;
	
	@Autowired
	private OsComputadores osComputadores;
	
	@GetMapping("/nova")
	public ModelAndView nova(OsComputador osComputador)
	{
		ModelAndView mv = new ModelAndView("oscomputador/CadastroOsComputador");
		mv.addObject("tipoManutencao", TipoManutencao.values());
		mv.addObject("statusManutencao", StatusManutencao.values());
		mv.addObject("computadores", computadores.buscarComputadoresEmUsoOrdemCrescente());
		//mv.addObject("logins", logins.findAll());
		System.out.println("-----------------------------"+ osComputador.UsuarioAutenticado());
		return mv;
	}
	
	@PostMapping(value= {"/nova", "{\\d+}"})
	public ModelAndView salvar(@Valid OsComputador osComputador, BindingResult result, RedirectAttributes attributes)
	{
		if(result.hasErrors())
		{
			return nova(osComputador);
		}
		
		try
		{
			if(osComputador.getCodigo() == null)
			{
				cadastroOsComputadorService.salvar(osComputador);
				attributes.addFlashAttribute("mensagem", "os salva com sucesso");
			}else
			{
				cadastroOsComputadorService.salvar(osComputador);
				attributes.addFlashAttribute("mensagem", "os editada com sucesso");
			}
		
		}catch(NomeFilialJaCadastradoException e)
		{
			result.rejectValue("nome", e.getMessage(), e.getMessage());
			return nova(osComputador);
		}catch(SolucaoManutencaoException e)
		{
			result.rejectValue("solucao", e.getMessage(), e.getMessage());
			return nova(osComputador);
		}catch(StatusManutencaoException e)
		{
			result.rejectValue("statusManutencao", e.getMessage(), e.getMessage());
			return nova(osComputador);
		}
		
		return new ModelAndView("redirect:/oscomputadores/nova");
	}

	@GetMapping
	public ModelAndView pesquisar(OsComputadorFilter osComputadorFilter, BindingResult result, @PageableDefault(size=10) Pageable pageable, HttpServletRequest httpServletRequest )
	{
		ModelAndView mv = new ModelAndView("oscomputador/PesquisaOsComputadores");
		mv.addObject("tipoManutencao", TipoManutencao.values());
		mv.addObject("statusManutencao", StatusManutencao.values());
		mv.addObject("computadores", computadores.computadorOrdemCrescente());
		
		PageWrapper<OsComputador> pagina = new PageWrapper<>(osComputadores.filtro(osComputadorFilter, pageable), httpServletRequest);
		
		mv.addObject("pagina", pagina);
		
		return mv;
	}
	
	@GetMapping("/{codigo}")
	public ModelAndView editar(@PathVariable("codigo") OsComputador osComputador)
	{
		ModelAndView mv = nova(osComputador);
		mv.addObject(osComputador);
		
		return mv;
	}
	
	@GetMapping("/visualizar/{codigo}")
	public ModelAndView visualizar(@PathVariable("codigo") OsComputador osComputador)
	{
		ModelAndView mv = new ModelAndView("oscomputador/VisualizarOsComputadores");
		mv.addObject(osComputador);
		
		return mv;
	}
}
