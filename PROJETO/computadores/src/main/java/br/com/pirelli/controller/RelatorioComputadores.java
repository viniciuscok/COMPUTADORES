package br.com.pirelli.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.pirelli.service.RelatorioService;

@Controller
@RequestMapping("/relatorios")
public class RelatorioComputadores 
{
	@Autowired
	private RelatorioService relatorioService;
	
	@GetMapping("/todoscomputadores")
	public ModelAndView relatorioVendasEmitidas() {
		ModelAndView mv = new ModelAndView("relatorio/RelatorioTodosComputadores");
		
		return mv;
	}
	
	@PostMapping("/todoscomputadores")
	public ResponseEntity<byte[]> gerarRelatorioVendasEmitidas() throws Exception {
		byte[] relatorio = relatorioService.gerarRelatorioVendasEmitidas(); 
		
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_PDF_VALUE)
				.body(relatorio);
	}

}
