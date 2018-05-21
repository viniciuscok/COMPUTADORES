package br.com.pirelli.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SeguracaController 
{
	@GetMapping("/index")
	public String login(@AuthenticationPrincipal User user) {
		if (user != null) {
			return "redirect:/";
		}
		
		return "index";
	}
	
	@GetMapping("/403")
	public String acessoNegado()
	{
		return "403";
	}

}
