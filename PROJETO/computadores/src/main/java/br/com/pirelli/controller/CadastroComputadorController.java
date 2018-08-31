package br.com.pirelli.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.pirelli.filter.ComputadorFilter;
import br.com.pirelli.model.Computador;
import br.com.pirelli.model.Modelo;
import br.com.pirelli.model.OsComputador;
import br.com.pirelli.model.So;
import br.com.pirelli.model.Status;
import br.com.pirelli.model.TipoComputador;
import br.com.pirelli.repository.Computadores;
import br.com.pirelli.repository.Filiais;
import br.com.pirelli.repository.Impressoras;
import br.com.pirelli.repository.Marcas;
import br.com.pirelli.repository.Modelos;
import br.com.pirelli.repository.Programas;
import br.com.pirelli.repository.Setores;
import br.com.pirelli.repository.Usuarios;
import br.com.pirelli.service.CadastroComputadorService;
import br.com.pirelli.service.exception.ComputadorJaCadastradoException;
import br.com.pirelli.service.exception.ImpossivelExcluirComputadorException;


@Controller
@RequestMapping("/computadores")
public class CadastroComputadorController 
{
	@Autowired
	private CadastroComputadorService cadastroComputadorService;
	
	@Autowired
	private Computadores computadores;
	
	@Autowired
	private Filiais filiais;
	
	@Autowired
	private Usuarios usuarios;
	
	@Autowired
	private Modelos modelos;
	
	@Autowired
	private Marcas marcas;
	
	@Autowired
	private Setores setores;
	
	@Autowired
	private Programas programas;
	
	@Autowired
	private Impressoras impressoras;
	
	@GetMapping("/novo")
	public ModelAndView novo(Computador computador)
	{
		ModelAndView mv = new ModelAndView("computador/CadastroComputador");
		mv.addObject("tipoComputadores", TipoComputador.values());
		mv.addObject("sistemas", So.values());
		mv.addObject("statusComputadores", Status.values());
		mv.addObject("filiais", filiais.findAll());
		mv.addObject("usuarios", usuarios.findAll());
		mv.addObject("marcas", marcas.findAll());
		mv.addObject("modelos", modelos.modeloComputadorOrdemCrescentePorTipo());
		mv.addObject("setores", setores.findAll());
		mv.addObject("programas", programas.findAll());
		mv.addObject("impressoras", impressoras.findAll());
		
		return mv;
	}
	
	@PostMapping(value= {"/novo", "{\\d+}"})
	public ModelAndView salvar(@Valid Computador computador, BindingResult result, RedirectAttributes attributes)
	{
		System.out.println("-----------------------------------------------passo 1");
		if(result.hasErrors())
		{
			return novo(computador);
		}
		
		try
		{
			if(computador.getCodigo() == null)
			{
				cadastroComputadorService.salvar(computador);
				attributes.addFlashAttribute("mensagem", "Computador cadastrado com sucesso");
			}else
			{
				System.out.println("-----------------------------------------------passo 3");
				cadastroComputadorService.salvar(computador);
				attributes.addFlashAttribute("mensagem", "Computador editado com sucesso");
			}
			
		}catch(ComputadorJaCadastradoException e)
		{
			result.rejectValue("nome", e.getMessage(), e.getMessage());
			return novo(computador);
		}
		
		
		
		return new ModelAndView("redirect:/computadores/novo");
	}
	
	@GetMapping
	public ModelAndView pesquisar(ComputadorFilter computadorFilter, BindingResult result, @PageableDefault(size=10) Pageable pageable, HttpServletRequest httpServletRequest)
	{
		ModelAndView mv = new ModelAndView("computador/PesquisaComputadores");
		mv.addObject("tipoComputadores", TipoComputador.values());
		mv.addObject("modelos", modelos.modeloComputadorOrdemCrescentePorTipo());
		mv.addObject("sistemas", So.values());
		mv.addObject("statusComputadores", Status.values());
		mv.addObject("marcas", marcas.findAll());
		mv.addObject("setores", setores.findAll());
		mv.addObject("usuarios", usuarios.findAll());
		
		PageWrapper<Computador> pagina = new PageWrapper<>(computadores.filtro(computadorFilter, pageable), httpServletRequest);
		
		mv.addObject("pagina", pagina);
		
		return mv;
		
	}
	
	@GetMapping("/{codigo}")
	public ModelAndView editar(@PathVariable("codigo") Computador computador)
	{
		ModelAndView mv = novo(computador);
		mv.addObject(computador);
		
		return mv;
	}
	
	@DeleteMapping("/{codigo}")
	public @ResponseBody ResponseEntity<?> excluir(@PathVariable("codigo") Computador computador)
	{
		try
		{
			cadastroComputadorService.excluir(computador);
		}catch(ImpossivelExcluirComputadorException e)
		{
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		
		return ResponseEntity.ok().build();
	}
	
	@RequestMapping(consumes = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody List<Modelo> pesquisar(@RequestParam(name="nome", defaultValue="oi") String nomeModelo) {
		
		return modelos.teste(nomeModelo);
	}
	
	@GetMapping("/visualizar/{codigo}")
	public ModelAndView visualizar(@PathVariable("codigo") Computador computador)
	{
		ModelAndView mv = new ModelAndView("computador/VisualizarComputadores");
		mv.addObject("programas", programas.findAll());
		mv.addObject("impressoras", impressoras.findAll());
		mv.addObject(computador);
		
		return mv;
	}
}
