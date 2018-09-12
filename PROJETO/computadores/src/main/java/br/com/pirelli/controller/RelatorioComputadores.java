package br.com.pirelli.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.pirelli.report.RelatorioPersonalizadoComputador;
import br.com.pirelli.report.RelatorioService;
import br.com.pirelli.repository.Marcas;
import br.com.pirelli.repository.Modelos;

@Controller
@RequestMapping("/relatorios")
public class RelatorioComputadores 
{
	@Autowired
	private RelatorioService relatorioService;
	
	@Autowired
	private Marcas marcas;
	
	@Autowired
	private Modelos modelos;
	
	@GetMapping()
	public ModelAndView relatorioVendasEmitidas() {
		ModelAndView mv = new ModelAndView("relatorio/Computadores");
		mv.addObject("marcas", marcas.findAll());
		mv.addObject("modelos", modelos.findAll());
		mv.addObject(new RelatorioPersonalizadoComputador());
		
		return mv;
	}
	
	@PostMapping(params= "todoscomputadores")
	public ResponseEntity<byte[]> buscarTodosComputadores() throws Exception {
		byte[] relatorio = relatorioService.buscarTodosComputadores(); 
		
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_PDF_VALUE)
				.body(relatorio);
	}
	
	
	@PostMapping(params="buscaPersonalizadas")
	public ResponseEntity<byte[]> relatorioP(RelatorioPersonalizadoComputador relatorioPersonalizadoComputador) throws Exception
	{
		byte[] relatorio = relatorioService.relatorioPersonalizado(relatorioPersonalizadoComputador); 
		
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_PDF_VALUE)
				.body(relatorio);
	}

}
