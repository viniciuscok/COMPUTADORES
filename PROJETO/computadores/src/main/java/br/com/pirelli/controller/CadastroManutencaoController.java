/*package br.com.pirelli.controller;

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

import br.com.pirelli.filter.ManutencaoFilter;
import br.com.pirelli.model.Manutencao;
import br.com.pirelli.model.StatusManutencao;
import br.com.pirelli.model.TipoManutencao;
import br.com.pirelli.repository.Logins;
import br.com.pirelli.repository.Manutencoes;
import br.com.pirelli.service.CadastroManutencaoService;
import br.com.pirelli.service.exception.NomeFilialJaCadastradoException;
import br.com.pirelli.service.exception.SolucaoManutencaoException;

@Controller
@RequestMapping("/manutencoes")
public class CadastroManutencaoController {

	@Autowired
	private CadastroManutencaoService cadastroManutencaoService;

	@Autowired
	private Manutencoes manutencoes;

	@Autowired
	private Logins logins;

	@GetMapping("/nova")
	public ModelAndView nova(Manutencao manutencao) {
		ModelAndView mv = new ModelAndView("manutencao/CadastroManutencao");
		mv.addObject("tipoManutencao", TipoManutencao.values());
		mv.addObject("statusManutencao", StatusManutencao.values());
		mv.addObject("logins", logins.findAll());
		System.out.println("-----------------------------" + manutencao.UsuarioAutenticado());
		return mv;
	}

	@PostMapping(value = { "/nova", "{\\d+}" })
	public ModelAndView salvar(@Valid Manutencao manutencao, BindingResult result, RedirectAttributes attributes) {
		if (result.hasErrors()) {
			return nova(manutencao);
		}

		try {
			if (manutencao.getCodigo() == null) {
				cadastroManutencaoService.salvar(manutencao);
				attributes.addFlashAttribute("mensagem", "os salva com sucesso");
			} else {
				cadastroManutencaoService.salvar(manutencao);
				attributes.addFlashAttribute("mensagem", "os editada com sucesso");
			}

		} catch (NomeFilialJaCadastradoException e) {
			result.rejectValue("nome", e.getMessage(), e.getMessage());
			return nova(manutencao);
		}catch (SolucaoManutencaoException e) 
		{
			result.rejectValue("solucao", e.getMessage(), e.getMessage());
			return nova(manutencao);
		}

		return new ModelAndView("redirect:/manutencoes/nova");
	}

	@GetMapping
	public ModelAndView pesquisar(ManutencaoFilter manutencaoFilter, BindingResult result,
			@PageableDefault(size = 10) Pageable pageable, HttpServletRequest httpServletRequest) {
		ModelAndView mv = new ModelAndView("manutencao/PesquisaManutencoes");
		mv.addObject("tipoManutencao", TipoManutencao.values());
		mv.addObject("statusManutencao", StatusManutencao.values());

		PageWrapper<Manutencao> pagina = new PageWrapper<>(manutencoes.filtro(manutencaoFilter, pageable),
				httpServletRequest);

		mv.addObject("pagina", pagina);

		return mv;
	}
	
	@GetMapping("/{codigo}")
	public ModelAndView editar(@PathVariable("codigo") Manutencao manutencao)
	{
		ModelAndView mv = nova(manutencao);
		mv.addObject(manutencao);
		return mv;
	}

}
*/