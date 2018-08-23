package br.com.pirelli.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import br.com.pirelli.model.OsComputador;
import br.com.pirelli.repository.Computadores;
import br.com.pirelli.repository.Impressoras;
import br.com.pirelli.repository.OsComputadores;
import br.com.pirelli.repository.OsImpressoras;
import br.com.pirelli.repository.Usuarios;

@Controller
public class HomeController 
{
	@Autowired
	private Usuarios usuarios;
	
	@Autowired
	private Computadores computadores;
	
	@Autowired
	private Impressoras impressoras;
	
	@Autowired
	private OsComputadores osComputadores;
	
	@Autowired
	private OsImpressoras osImpressoras;
	
	@GetMapping("/")
	public ModelAndView novo()
	{
		ModelAndView mv = new ModelAndView("home/home");
		mv.addObject("usuarios", usuarios.count());
		mv.addObject("totalNotebooks", computadores.buscarNotebook());
		mv.addObject("totalDesktops", computadores.buscarDesktop());
		mv.addObject("totalTouchs", computadores.buscarTouch());
		mv.addObject("totalImpTermicas", impressoras.impressoraTermica());
		mv.addObject("totalImpEscritorio", impressoras.impressoraEscritorio());
		mv.addObject("osComputadores", osComputadores.pesquisarOsComputadorAberto());
		mv.addObject("osImpressoras", osImpressoras.pesquisarOsImpressoraAberto());
		return mv;
	}
	
	//@GetMapping("/{codigo}")
	//public ModelAndView editar(@PathVariable("codigo") OsComputador osComputador)
	//{
	//	ModelAndView mv = new ModelAndView("oscomputador/PesquisaOsComputadores/{codigo}");
	//	mv.addObject(osComputador);
	//	
	//	return mv;
	//}
	
	//@GetMapping("/visualizar/{codigo}")
	//public ModelAndView visualizar(@PathVariable("codigo") OsComputador osComputador)
	//{
	//	ModelAndView mv = new ModelAndView("oscomputador/VisualizarOsComputadores");
	//	mv.addObject(osComputador);
		
	//	return mv;
	//}

}
