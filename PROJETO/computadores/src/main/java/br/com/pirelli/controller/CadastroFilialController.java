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

import br.com.pirelli.filter.FilialFilter;
import br.com.pirelli.model.Filial;
import br.com.pirelli.repository.Filiais;
import br.com.pirelli.service.CadastroFilialService;
import br.com.pirelli.service.exception.ImpossivelExcluirFilialException;
import br.com.pirelli.service.exception.NomeFilialJaCadastradoException;

@Controller
@RequestMapping("/filiais")
public class CadastroFilialController 
{
	@Autowired
	private CadastroFilialService cadastroFilialService;
	
	@Autowired
	private Filiais filiais;
	
	@GetMapping("/nova")
	public ModelAndView nova(Filial filial)
	{
		ModelAndView mv = new ModelAndView("filial/CadastroFilial");
		return mv;
	}
	
	@PostMapping(value= {"/nova", "{\\d+}"})
	public ModelAndView salvar(@Valid Filial filial, BindingResult result, RedirectAttributes attributes)
	{
		if(result.hasErrors())
		{
			return nova(filial);
		}
		
		try
		{
			if(filial.getCodigo() == null)
			{
				cadastroFilialService.salvar(filial);
				attributes.addFlashAttribute("mensagem", "Filial salva com sucesso");
			}else
			{
				cadastroFilialService.salvar(filial);
				attributes.addFlashAttribute("mensagem", "Filial editada com sucesso");
			}
		
		}catch(NomeFilialJaCadastradoException e)
		{
			result.rejectValue("nome", e.getMessage(), e.getMessage());
			return nova(filial);
		}
		
		return new ModelAndView("redirect:/filiais/nova");
	}
	
	@GetMapping
	public ModelAndView pesquisar(FilialFilter filialFilter, BindingResult result, @PageableDefault(size=10) Pageable pageable, HttpServletRequest httpServletRequest )
	{
		ModelAndView mv = new ModelAndView("filial/PesquisaFiliais");
		
		PageWrapper<Filial> pagina = new PageWrapper<>(filiais.findByNome(filialFilter.getNome(), pageable), httpServletRequest);
		
		mv.addObject("pagina", pagina);
		
		return mv;
	}
	
	@GetMapping("/{codigo}")
	public ModelAndView editar(@PathVariable("codigo") Filial filial)
	{
		ModelAndView mv = nova(filial);
		mv.addObject(filial);
		
		return mv;
	}
	
	@DeleteMapping("/{codigo}")
	public @ResponseBody ResponseEntity<?> excluir(@PathVariable("codigo") Filial filial)
	{
		try
		{
			cadastroFilialService.excluir(filial);
		}catch(ImpossivelExcluirFilialException e)
		{
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		
		return ResponseEntity.ok().build();
	}
}
