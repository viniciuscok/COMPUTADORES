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

import br.com.pirelli.filter.MonitorFilter;
import br.com.pirelli.model.Monitor;
import br.com.pirelli.model.Status;
import br.com.pirelli.repository.Filiais;
import br.com.pirelli.repository.Marcas;
import br.com.pirelli.repository.Modelos;
import br.com.pirelli.repository.Monitores;
import br.com.pirelli.repository.Setores;
import br.com.pirelli.service.CadastroMonitorService;
import br.com.pirelli.service.exception.ImpossivelExcluirComputadorException;
import br.com.pirelli.service.exception.MonitorJaCadastradoException;

@Controller
@RequestMapping("/monitores")
public class CadastroMonitorController 
{
	@Autowired
	private CadastroMonitorService cadastroMonitorService;
	
	@Autowired
	private Monitores monitores;
	
	@Autowired
	private Filiais filiais;
	
	@Autowired
	private Modelos modelos;
	
	@Autowired
	private Marcas marcas;
	
	@Autowired
	private Setores setores;
	
	@GetMapping("/novo")
	public ModelAndView novo(Monitor monitor)
	{
		ModelAndView mv = new ModelAndView("monitor/CadastroMonitor");
		mv.addObject("statusMonitores", Status.values());
		mv.addObject("filiais", filiais.findAll());
		mv.addObject("marcas", marcas.findAll());
		mv.addObject("modelos", modelos.modeloMonitorOrdemCrescentePorTipo());
		mv.addObject("setores", setores.setorOrdemCrescente());
		
		return mv;
	}
	
	@PostMapping(value= {"/novo", "{\\d+}"})
	public ModelAndView salvar(@Valid Monitor monitor, BindingResult result, RedirectAttributes attributes)
	{
		
		if(result.hasErrors())
		{
			return novo(monitor);
		}
		
		try
		{
			if(monitor.getCodigo() == null)
			{
				cadastroMonitorService.salvar(monitor);
				attributes.addFlashAttribute("mensagem", "Monitor cadastrado com sucesso");
			}else
			{
				cadastroMonitorService.salvar(monitor);
				attributes.addFlashAttribute("mensagem", "Monitor editado com sucesso");
			}
			
		}catch(MonitorJaCadastradoException e)
		{
			result.rejectValue("numeroDeSerie", e.getMessage(), e.getMessage());
			return novo(monitor);
		}
		
		
		
		return new ModelAndView("redirect:/monitores/novo");
	}
	
	@GetMapping
	public ModelAndView pesquisar(MonitorFilter monitorFilter, BindingResult result, @PageableDefault(size=10) Pageable pageable, HttpServletRequest httpServletRequest)
	{
		ModelAndView mv = new ModelAndView("monitor/PesquisaMonitores");
		mv.addObject("modelos", modelos.modeloMonitorOrdemCrescentePorTipo());
		mv.addObject("statusMonitores", Status.values());
		mv.addObject("marcas", marcas.findAll());
		mv.addObject("setores", setores.setorOrdemCrescente());
		
		PageWrapper<Monitor> pagina = new PageWrapper<>(monitores.filtro(monitorFilter, pageable), httpServletRequest);
		
		mv.addObject("pagina", pagina);
		
		return mv;
		
	}
	
	@GetMapping("/{codigo}")
	public ModelAndView editar(@PathVariable("codigo") Monitor monitor)
	{
		ModelAndView mv = novo(monitor);
		mv.addObject(monitor);
		
		return mv;
	}
	
	@DeleteMapping("/{codigo}")
	public @ResponseBody ResponseEntity<?> excluir(@PathVariable("codigo") Monitor monitor)
	{
		try
		{
			cadastroMonitorService.excluir(monitor);
		}catch(ImpossivelExcluirComputadorException e)
		{
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		
		return ResponseEntity.ok().build();
	}
	
	
	@GetMapping("/visualizar/{codigo}")
	public ModelAndView visualizar(@PathVariable("codigo") Monitor monitor)
	{
		ModelAndView mv = new ModelAndView("monitor/VisualizarMonitores");
		mv.addObject(monitor);
		
		return mv;
	}

}
