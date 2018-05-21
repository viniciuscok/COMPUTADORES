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

import br.com.pirelli.filter.ImpressoraFilter;
import br.com.pirelli.model.CategoriaImpressora;
import br.com.pirelli.model.Filial;
import br.com.pirelli.model.Impressora;
import br.com.pirelli.model.Status;
import br.com.pirelli.model.TipoImpressora;
import br.com.pirelli.repository.Filiais;
import br.com.pirelli.repository.Impressoras;
import br.com.pirelli.repository.Marcas;
import br.com.pirelli.repository.Modelos;
import br.com.pirelli.repository.Setores;
import br.com.pirelli.repository.Toners;
import br.com.pirelli.service.CadastroImpressoraService;
import br.com.pirelli.service.exception.ImpossivelExcluirImpressora;
import br.com.pirelli.service.exception.ImpressoraJaExisteException;

@Controller
@RequestMapping("/impressoras")
public class CadastroImpressoraController 
{
	@Autowired
	private CadastroImpressoraService cadastroImpressoraService;
	
	@Autowired
	private Filiais filiais;
	
	@Autowired
	private Modelos modelos;
	
	@Autowired
	private Marcas marcas;
	
	@Autowired
	private Toners toners;
	
	@Autowired
	private Setores setores;
	
	@Autowired
	private Impressoras impressoras;
	
	@GetMapping("/novo")
	public ModelAndView novo(Impressora impressora)
	{
		ModelAndView mv = new ModelAndView("impressora/CadastroImpressora");
		mv.addObject("filiais", filiais.findAll());
		mv.addObject("modelos", modelos.buscarModeloImpressoraOrdemCrescente());
		mv.addObject("marcas", marcas.marcaOrdemCrescente());
		mv.addObject("toners", toners.findAll());
		mv.addObject("statusImpressora", Status.values());
		mv.addObject("tipoImpressoras", TipoImpressora.values());
		mv.addObject("categorias", CategoriaImpressora.values());
		mv.addObject("setores", setores.setorOrdemCrescente());
		
		return mv;
	}
	
	@PostMapping(value= {"/novo", "{\\d+}"})
	public ModelAndView salvar(@Valid Impressora impressora, BindingResult result, RedirectAttributes attributes)
	{
		if(result.hasErrors())
		{
			return novo(impressora);
		}
		
		try
		{
			if(impressora.getCodigo() == null)
			{
				cadastroImpressoraService.salvar(impressora);
				attributes.addFlashAttribute("mensagem", "Impressora salva com sucesso.");
			}else
			{
				cadastroImpressoraService.salvar(impressora);
				attributes.addFlashAttribute("mensagem", "Impressora editada com sucesso.");
			}
			
		}catch(ImpressoraJaExisteException e)
		{
			result.rejectValue("enderecoIP", e.getMessage(), e.getMessage());
			return novo(impressora);
		}
		
		
		
		return new ModelAndView("redirect:/impressoras/novo");
	}
	
	@GetMapping
	public ModelAndView pesquisar(ImpressoraFilter impressoraFilter, @PageableDefault(size=10) Pageable pageable, HttpServletRequest httpServletRequest)
	{
		ModelAndView mv = new ModelAndView("impressora/PesquisaImpressoras");
		mv.addObject("categorias", CategoriaImpressora.values());
		mv.addObject("modelos", modelos.findAll());
		mv.addObject("tipoImpressoras", TipoImpressora.values());
		mv.addObject("statusImpressora", Status.values());
		mv.addObject("marcas", marcas.findAll());
		mv.addObject("setores", setores.findAll());
		
		PageWrapper<Impressora> pagina = new PageWrapper<>(impressoras.filtro(impressoraFilter, pageable), httpServletRequest);
		
		mv.addObject("pagina", pagina);
		
		return mv;
		
	}
	
	@GetMapping("/{codigo}")
	public ModelAndView editar(@PathVariable("codigo") Impressora impressora)
	{
		ModelAndView mv = novo(impressora);
		mv.addObject(impressora);
		
		return mv;
	}
	
	@DeleteMapping("/{codigo}")
	public @ResponseBody ResponseEntity<?> excluir(@PathVariable("codigo") Impressora impressora)
	{
		try
		{
			cadastroImpressoraService.excluir(impressora);
		}catch(ImpossivelExcluirImpressora e)
		{
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		
		return ResponseEntity.ok().build();
	}
	
	
	
}
