package br.com.pirelli.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.pirelli.repository.Computadores;
import br.com.pirelli.repository.Usuarios;

@Controller
public class HomeController 
{
	@Autowired
	private Usuarios usuarios;
	
	@Autowired
	private Computadores computadores;
	
	@GetMapping("/")
	public ModelAndView novo()
	{
		ModelAndView mv = new ModelAndView("home/home");
		mv.addObject("usuarios", usuarios.count());
		mv.addObject("totalNotebooks", computadores.buscarNotebook());
		mv.addObject("totalDesktops", computadores.buscarDesktop());
		mv.addObject("totalTouchs", computadores.buscarTouch());
		return mv;
	}

}
