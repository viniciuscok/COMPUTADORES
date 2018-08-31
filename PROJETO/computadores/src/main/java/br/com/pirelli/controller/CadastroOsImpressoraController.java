package br.com.pirelli.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

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

import br.com.pirelli.filter.OsImpressoraFilter;
import br.com.pirelli.model.Manutencao;
import br.com.pirelli.model.OsImpressora;
import br.com.pirelli.model.StatusManutencao;
import br.com.pirelli.model.TipoManutencao;
import br.com.pirelli.repository.Impressoras;
import br.com.pirelli.repository.OsImpressoras;
import br.com.pirelli.service.CadastroOsImpressoraService;
import br.com.pirelli.service.exception.NomeFilialJaCadastradoException;
import br.com.pirelli.service.exception.SolucaoManutencaoException;
import br.com.pirelli.service.exception.StatusManutencaoException;

@Controller
@RequestMapping("/osimpressoras")
public class CadastroOsImpressoraController 
{
	@Autowired
	private Impressoras impressoras;
	
	@Autowired
	private CadastroOsImpressoraService cadastroOsImpressoraService;
	
	@Autowired
	private OsImpressoras osImpressoras;
	
	@GetMapping("/nova")
	public ModelAndView nova(OsImpressora osImpressora)
	{
		ModelAndView mv = new ModelAndView("osimpressora/CadastroOsImpressora");
		mv.addObject("tipoManutencao", TipoManutencao.values());
		mv.addObject("statusManutencao", StatusManutencao.values());
		mv.addObject("impressoras", impressoras.buscarImpressoraEmUsoOrdemCrescente()); //buscarComputadoresEmUsoOrdemCrescente());
		//mv.addObject("logins", logins.findAll());
		//System.out.println("-----------------------------"+ osComputador.UsuarioAutenticado());
		
		return mv;
	}
	
	@PostMapping(value= {"/nova", "{\\d+}"})
	public ModelAndView salvar(@Valid OsImpressora osImpressora, BindingResult result, RedirectAttributes attributes)
	{
		if(result.hasErrors())
		{
			return nova(osImpressora);
		}
		
		try
		{
			if(osImpressora.getCodigo() == null)
			{
				cadastroOsImpressoraService.salvar(osImpressora);
				attributes.addFlashAttribute("mensagem", "os salva com sucesso");
			}else
			{
				cadastroOsImpressoraService.salvar(osImpressora);
				attributes.addFlashAttribute("mensagem", "os editada com sucesso");
			}
		
		}catch(NomeFilialJaCadastradoException e)
		{
			result.rejectValue("nome", e.getMessage(), e.getMessage());
			return nova(osImpressora);
		}catch(SolucaoManutencaoException e)
		{
			result.rejectValue("solucao", e.getMessage(), e.getMessage());
			return nova(osImpressora);
		}catch(StatusManutencaoException e)
		{
			result.rejectValue("statusManutencao", e.getMessage(), e.getMessage());
			return nova(osImpressora);
		}
		
		return new ModelAndView("redirect:/osimpressoras/nova");
	}

	@GetMapping
	public ModelAndView pesquisar(OsImpressoraFilter osImpressoraFilter, BindingResult result, @PageableDefault(size=10) Pageable pageable, HttpServletRequest httpServletRequest )
	{
		ModelAndView mv = new ModelAndView("osimpressora/PesquisaOsImpressora");
		mv.addObject("tipoManutencao", TipoManutencao.values());
		mv.addObject("statusManutencao", StatusManutencao.values());
		mv.addObject("impressoras", impressoras.findAll()); //computadorOrdemCrescente());
		
		PageWrapper<OsImpressora> pagina = new PageWrapper<>(osImpressoras.filtro(osImpressoraFilter, pageable), httpServletRequest);
		
		mv.addObject("pagina", pagina);
		
		return mv;
	}
	
	@GetMapping("/{codigo}")
	public ModelAndView editar(@PathVariable("codigo") OsImpressora osImpressora)
	{
		ModelAndView mv = nova(osImpressora);
		mv.addObject(osImpressora);
		
		return mv;
	}
	
	@GetMapping("/visualizar/{codigo}")
	public ModelAndView visualizar(@PathVariable("codigo") OsImpressora osImpressora)
	{
		ModelAndView mv = new ModelAndView("osimpressora/VisualizarOsImpressora");
		mv.addObject(osImpressora);
		
		return mv;
	}

}
