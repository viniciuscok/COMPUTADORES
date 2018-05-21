package br.com.pirelli.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.pirelli.model.Login;
import br.com.pirelli.repository.Grupos;
import br.com.pirelli.service.CadastroLoginService;
import br.com.pirelli.service.exception.EmailLoginJaCadastradoException;
import br.com.pirelli.service.exception.SenhaLoginObrigatoriaException;

@Controller
@RequestMapping("/logins")
public class CadastroLoginController 
{
	@Autowired
	private CadastroLoginService cadastroLoginService;
	
	@Autowired
	private Grupos grupos;

	@GetMapping("/novo")
	public ModelAndView novo(Login login)
	{
		ModelAndView mv = new ModelAndView("login/CadastroLogin");
		mv.addObject("grupos", grupos.findAll());
		
		return mv;
	}
	
	@PostMapping("/novo")
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
}
