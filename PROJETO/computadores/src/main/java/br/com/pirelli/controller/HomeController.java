package br.com.pirelli.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController 
{
	
	@GetMapping("/")
	public ModelAndView novo()
	{
		ModelAndView mv = new ModelAndView("home/home");
		
		return mv;
	}

}
