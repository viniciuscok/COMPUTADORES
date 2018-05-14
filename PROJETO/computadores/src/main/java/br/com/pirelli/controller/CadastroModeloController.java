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

import br.com.pirelli.filter.ModeloFilter;
import br.com.pirelli.model.Modelo;
import br.com.pirelli.repository.Modelos;
import br.com.pirelli.repository.TipoModelos;
import br.com.pirelli.service.CadastroModeloService;
import br.com.pirelli.service.exception.ImpossivelEcluirModeloException;
import br.com.pirelli.service.exception.ModeloJacadastradoException;

@Controller
@RequestMapping("/modelos")
public class CadastroModeloController 
{
	@Autowired
	private TipoModelos tipoModelos;
	
	@Autowired
	private CadastroModeloService cadastroModeloService;
	
	@Autowired
	private Modelos modelos;
	
	@GetMapping("/novo")
	public ModelAndView novo(Modelo modelo)
	{
		ModelAndView mv = new ModelAndView("modelo/CadastroModelo");
		mv.addObject("equipamentos", tipoModelos.tipoModeloOrdemCrescente());
		
		return mv;
	}
	
	@PostMapping(value= {"/novo", "{\\d+}"})
	public ModelAndView salvar(@Valid Modelo modelo, BindingResult result, RedirectAttributes attributes)
	{
		if(result.hasErrors())
		{
			return novo(modelo);
		}
		
		try
		{
			if(modelo.getCodigo() == null)
			{
				cadastroModeloService.salvar(modelo);
				attributes.addFlashAttribute("mensagem", "Modelo salvo com sucesso.");
			}else
			{
				cadastroModeloService.salvar(modelo);
				attributes.addFlashAttribute("mensagem", "Modelo editado com sucesso.");
			}
			
		}catch(ModeloJacadastradoException e)
		{
			result.rejectValue("nome", e.getMessage(), e.getMessage());
			return novo(modelo);
		}
		
		
		
		return new ModelAndView("redirect:/modelos/novo");
	}
	
	@GetMapping
	public ModelAndView pesquisar(ModeloFilter modeloFilter, @PageableDefault(size=10) Pageable pageable, HttpServletRequest httpServletRequest)
	{
		ModelAndView mv = new ModelAndView("modelo/PesquisaModelos");
		
		PageWrapper<Modelo> pagina = new PageWrapper<>(modelos.findByNome(modeloFilter.getNome(), pageable), httpServletRequest);
		
		mv.addObject("pagina", pagina);
		
		return mv;
		
	}
	
	@GetMapping("/{codigo}")
	public ModelAndView editar(@PathVariable("codigo") Modelo modelo)
	{
		ModelAndView mv = novo(modelo);
		mv.addObject(modelo);
		
		return mv;
	}
	
	@DeleteMapping("{codigo}")
	public @ResponseBody ResponseEntity<?> excluir(@PathVariable("codigo") Modelo modelo)
	{
		try
		{
			cadastroModeloService.excluir(modelo);
		}catch(ImpossivelEcluirModeloException e)
		{
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		
		return ResponseEntity.ok().build();
	}

}
