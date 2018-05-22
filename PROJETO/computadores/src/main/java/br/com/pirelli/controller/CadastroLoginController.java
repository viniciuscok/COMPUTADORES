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
import br.com.pirelli.filter.LoginFilter;
import br.com.pirelli.model.Grupo;
import br.com.pirelli.model.Login;
import br.com.pirelli.repository.Grupos;
import br.com.pirelli.repository.Logins;
import br.com.pirelli.service.CadastroLoginService;
import br.com.pirelli.service.exception.EmailLoginJaCadastradoException;
import br.com.pirelli.service.exception.ImpossivelExcluirGrupoException;
import br.com.pirelli.service.exception.SenhaLoginObrigatoriaException;

@Controller
@RequestMapping("/logins")
public class CadastroLoginController 
{
	@Autowired
	private CadastroLoginService cadastroLoginService;
	
	@Autowired
	private Grupos grupos;
	
	@Autowired
	private Logins logins;

	@GetMapping("/novo")
	public ModelAndView novo(Login login)
	{
		ModelAndView mv = new ModelAndView("login/CadastroLogin");
		mv.addObject("grupos", grupos.findAll());
		
		return mv;
	}
	
	@PostMapping(value= {"/novo", "{\\d+}"})
	public ModelAndView salvar(@Valid Login login, BindingResult result, RedirectAttributes attributes) {
		if (result.hasErrors()) {
			return novo(login);
		}
		try
		{
			if(login.getCodigo() == null)
			{
				cadastroLoginService.salvar(login);
				attributes.addFlashAttribute("mensagem", "Login salvo com sucesso");
			}else
			{
				cadastroLoginService.salvar(login);
				attributes.addFlashAttribute("mensagem", "Login editado com sucesso");
			}
		}catch(EmailLoginJaCadastradoException e)
		{
			result.rejectValue("email", e.getMessage(), e.getMessage());
			return novo(login);
		}catch(SenhaLoginObrigatoriaException e)
		{
			result.rejectValue("senha", e.getMessage(), e.getMessage());
			return novo(login);
		}
		
		
		return new ModelAndView("redirect:/logins/novo");
	}
	
	@GetMapping
	public ModelAndView pesquisar(LoginFilter loginFilter, BindingResult result, @PageableDefault(size=10) Pageable pageable, HttpServletRequest httpServletRequest )
	{
		ModelAndView mv = new ModelAndView("login/PesquisaLogins");
		
		PageWrapper<Login> pagina = new PageWrapper<>(logins.findByNome(loginFilter.getNome(), pageable), httpServletRequest);
		
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
