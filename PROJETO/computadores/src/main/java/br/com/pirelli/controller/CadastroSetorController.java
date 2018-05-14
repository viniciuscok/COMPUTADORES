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

import br.com.pirelli.filter.SetorFilter;
import br.com.pirelli.model.Maquina;
import br.com.pirelli.model.Programa;
import br.com.pirelli.model.Setor;
import br.com.pirelli.repository.Setores;
import br.com.pirelli.service.CadastroSetorService;
import br.com.pirelli.service.exception.ImpossivelExcluirProgramaException;
import br.com.pirelli.service.exception.ImpossivelExcluirSetorException;
import br.com.pirelli.service.exception.SetorJaCadastradoException;

@Controller
@RequestMapping("/setores")
public class CadastroSetorController 
{
	@Autowired
	private CadastroSetorService cadastroSetorService;
	
	@Autowired
	private Setores setores;
	
	@GetMapping("/novo")
	public ModelAndView novo(Setor setor)
	{
		ModelAndView mv = new ModelAndView("setor/CadastroSetor");
		
		return mv;
	}

	@PostMapping(value= {"/novo", "{\\d+}"})
	public ModelAndView salvar(@Valid Setor setor, BindingResult result, RedirectAttributes attributes)
	{
		if(result.hasErrors())
		{
			return novo(setor);
		}
		
		try
		{
			if(setor.getCodigo() == null)
			{
				cadastroSetorService.salvar(setor);
				attributes.addFlashAttribute("mensagem", "Setor salvo com sucesso.");
			}else
			{
				cadastroSetorService.salvar(setor);
				attributes.addFlashAttribute("mensagem", "Setor editado com sucesso.");
			}
			
		}catch (SetorJaCadastradoException e) 
		{
			result.rejectValue("nome", e.getMessage(), e.getMessage());
			return novo(setor);
		}
		
		
		
		return new ModelAndView("redirect:/setores/novo");
	}
	
	@GetMapping
	public ModelAndView pesquisar(SetorFilter setorFilter, @PageableDefault(size=10) Pageable pageable, HttpServletRequest httpServletRequest)
	{
		ModelAndView mv = new ModelAndView("setor/PesquisaSetores");
		
		PageWrapper<Setor> pagina = new PageWrapper<>(setores.findByNome(setorFilter.getNome(), pageable), httpServletRequest);
		
		mv.addObject("pagina", pagina);
		
		return mv;
	}
	
	@GetMapping("/{codigo}")
	public ModelAndView editar(@PathVariable("codigo") Setor setor)
	{
		ModelAndView mv = novo(setor);
		mv.addObject(setor);
		
		return mv;
	}
	
	@DeleteMapping("/{codigo}")
	public @ResponseBody ResponseEntity<?> excluir(@PathVariable("codigo") Setor setor)
	{
		try
		{
			cadastroSetorService.excluir(setor);
		}catch(ImpossivelExcluirSetorException e)
		{
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		
		
		return ResponseEntity.ok().build();
	}
}
