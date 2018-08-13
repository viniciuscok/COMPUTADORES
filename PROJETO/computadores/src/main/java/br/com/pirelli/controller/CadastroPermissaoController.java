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

import br.com.pirelli.filter.PermissaoFilter;
import br.com.pirelli.model.Permissao;
import br.com.pirelli.repository.Permissoes;
import br.com.pirelli.service.CadastroPermissaoService;
import br.com.pirelli.service.exception.ImpossivelExcluirFilialException;
import br.com.pirelli.service.exception.NomePermissaoJaCadastradoException;

@Controller
@RequestMapping("/permissoes")
public class CadastroPermissaoController 
{
	@Autowired
	private CadastroPermissaoService cadastroPermissaoService;
	
	@Autowired
	private Permissoes permissoes;
	
	@GetMapping("/nova")
	public ModelAndView nova(Permissao permissao)
	{
		ModelAndView mv = new ModelAndView("permissao/CadastroPermissao");
		return mv;
	}
	
	@PostMapping(value= {"/nova", "{\\d+}"})
	public ModelAndView salvar(@Valid Permissao permissao, BindingResult result, RedirectAttributes attributes)
	{
		if(result.hasErrors())
		{
			return nova(permissao);
		}
		
		try
		{
			if(permissao.getCodigo() == null)
			{
				cadastroPermissaoService.salvar(permissao);
				attributes.addFlashAttribute("mensagem", "Permissão salva com sucesso");
			}else
			{
				cadastroPermissaoService.salvar(permissao);
				attributes.addFlashAttribute("mensagem", "Permissão editada com sucesso");
			}
		
		}catch(NomePermissaoJaCadastradoException e)
		{
			result.rejectValue("nome", e.getMessage(), e.getMessage());
			return nova(permissao);
		}
		
		return new ModelAndView("redirect:/permissoes/nova");
	}
	
	@GetMapping
	public ModelAndView pesquisar(PermissaoFilter permissaoFilter, BindingResult result, @PageableDefault(size=10) Pageable pageable, HttpServletRequest httpServletRequest )
	{
		ModelAndView mv = new ModelAndView("permissao/PesquisaPermissoes");
		
		PageWrapper<Permissao> pagina = new PageWrapper<>(permissoes.findByNome(permissaoFilter.getNome(), pageable), httpServletRequest);
		
		mv.addObject("pagina", pagina);
		
		return mv;
	}
	
	@GetMapping("/{codigo}")
	public ModelAndView editar(@PathVariable("codigo") Permissao permissao)
	{
		ModelAndView mv = nova(permissao);
		mv.addObject(permissao);
		
		return mv;
	}
	
	@DeleteMapping("/{codigo}")
	public @ResponseBody ResponseEntity<?> excluir(@PathVariable("codigo") Permissao permissao)
	{
		try
		{
			cadastroPermissaoService.excluir(permissao);
		}catch(ImpossivelExcluirFilialException e)
		{
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		
		return ResponseEntity.ok().build();
	}

}
