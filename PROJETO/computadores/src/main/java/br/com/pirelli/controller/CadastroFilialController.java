package br.com.pirelli.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.pirelli.filter.FilialFilter;
import br.com.pirelli.model.Filial;
import br.com.pirelli.repository.Filiais;
import br.com.pirelli.service.CadastroFilialService;
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
	
	@PostMapping("/nova")
	public ModelAndView salvar(@Valid Filial filial, BindingResult result, RedirectAttributes attributes)
	{
		if(result.hasErrors())
		{
			return nova(filial);
		}
		
		try
		{
			cadastroFilialService.salvar(filial);
		
		}catch(NomeFilialJaCadastradoException e)
		{
			result.rejectValue("nome", e.getMessage(), e.getMessage());
			return nova(filial);
		}
		
		attributes.addFlashAttribute("mensagem", "Filial salva com sucesso");
		
		return new ModelAndView("redirect:/filiais/nova");
	}
	
	@GetMapping
	public ModelAndView pesquisar(FilialFilter filialFilter, BindingResult result, @PageableDefault(size=10) Pageable pageable, HttpServletRequest httpServletRequest )
	{
		ModelAndView mv = new ModelAndView("filial/PesquisaFiliais");
		
		PageWrapper<Filial> pagina = new PageWrapper<>(filiais.findByNome(filialFilter.getNome(), pageable), httpServletRequest);
		//PageWrapper<Filial> pagina = new PageWrapper<>(filiais.findAll(pageable),httpServletRequest);
		//List<Filial> fi = pagina.getConteudo();
		//for (Filial filial : fi)
		//{
		//	System.out.println(filial.getCodigo());
		//	System.out.println(filial.getNome());
		//}
		//System.out.println(pagina.getConteudo());
		mv.addObject("pagina", pagina);
		//mv.addObject("paginas", filiais.findByNome(filialFilter.getNome(), pageable));
		//pageable.
		return mv;
	}
}
