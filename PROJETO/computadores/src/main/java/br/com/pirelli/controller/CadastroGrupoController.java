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

import br.com.pirelli.filter.GrupoFilter;
import br.com.pirelli.model.Grupo;
import br.com.pirelli.repository.Grupos;
import br.com.pirelli.repository.Permissoes;
import br.com.pirelli.service.CadastroGrupoService;
import br.com.pirelli.service.exception.ImpossivelExcluirGrupoException;
import br.com.pirelli.service.exception.NomegrupoJaCadastradoException;

@Controller
@RequestMapping("/grupos")
public class CadastroGrupoController 
{
	@Autowired
	private CadastroGrupoService cadastroGrupoService;
	
	@Autowired
	private Grupos grupos;
	
	@Autowired
	private Permissoes permissoes;
	
	@GetMapping("/novo")
	public ModelAndView novo(Grupo grupo)
	{
		ModelAndView mv = new ModelAndView("grupo/CadastroGrupo");
		mv.addObject("permissoes", permissoes.findAll());
		return mv;
	}
	
	@PostMapping(value= {"/novo", "{\\d+}"})
	public ModelAndView salvar(@Valid Grupo grupo, BindingResult result, RedirectAttributes attributes)
	{
		if(result.hasErrors())
		{
			return novo(grupo);
		}
		
		try
		{
			if(grupo.getCodigo() == null)
			{
				cadastroGrupoService.salvar(grupo);
				attributes.addFlashAttribute("mensagem", "Grupo salvo com sucesso");
			}else
			{
				cadastroGrupoService.salvar(grupo);
				attributes.addFlashAttribute("mensagem", "Grupo editado com sucesso");
			}
		
		}catch(NomegrupoJaCadastradoException e)
		{
			result.rejectValue("nome", e.getMessage(), e.getMessage());
			return novo(grupo);
		}
		
		return new ModelAndView("redirect:/grupos/novo");
	}
	
	@GetMapping
	public ModelAndView pesquisar(GrupoFilter grupoFilter, BindingResult result, @PageableDefault(size=10) Pageable pageable, HttpServletRequest httpServletRequest )
	{
		ModelAndView mv = new ModelAndView("grupo/PesquisaGrupos");
		
		PageWrapper<Grupo> pagina = new PageWrapper<>(grupos.findByNome(grupoFilter.getNome(), pageable), httpServletRequest);
		
		mv.addObject("pagina", pagina);
		
		return mv;
	}
	
	@GetMapping("/{codigo}")
	public ModelAndView editar(@PathVariable("codigo") Grupo grupo)
	{
		ModelAndView mv = novo(grupo);
		mv.addObject(grupo);
		
		return mv;
	}
	
	@DeleteMapping("/{codigo}")
	public @ResponseBody ResponseEntity<?> excluir(@PathVariable("codigo") Grupo grupo)
	{
		try
		{
			cadastroGrupoService.excluir(grupo);
		}catch(ImpossivelExcluirGrupoException e)
		{
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		
		return ResponseEntity.ok().build();
	}

}
