package br.com.pirelli.report;

import java.io.InputStream;
import java.sql.Connection;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

@Service
public class RelatorioService {
	
	@Autowired
	private DataSource dataSource;
	
	public byte[] relatorioPersonalizado(RelatorioPersonalizadoComputador relatorioPersonalizadoComputador) throws Exception 
	{
		Map<String, Object> parametros = new HashMap<>();
		
		parametros.put("format", "pdf");
		parametros.put("nomeMarca", relatorioPersonalizadoComputador.getMarca());
		parametros.put("nomeModelo", relatorioPersonalizadoComputador.getModelo());
		
		//return new ModelAndView("Busca_Personalizada_Computador", parametros);
		
		//Map<String, Object> parametros = new HashMap<>();
		//parametros.put("format", "pdf");
		
		InputStream inputStream = this.getClass()
				.getResourceAsStream("/relatorios/Busca_Personalizada_Computador.jasper");
		
		Connection con = this.dataSource.getConnection();
		
		try 
		{
			JasperPrint jasperPrint = JasperFillManager.fillReport(inputStream, parametros, con);
			return JasperExportManager.exportReportToPdf(jasperPrint);
		} finally {
			con.close();
		}
	}
	
	public byte[] buscarTodosComputadores() throws Exception
	{
		Map<String, Object> parametros = new HashMap<>();
		parametros.put("format", "pdf");
		
		InputStream inputStream = this.getClass()
				.getResourceAsStream("/relatorios/Buscar_Todos_Computadores.jasper");
		
		Connection con = this.dataSource.getConnection();
		
		try 
		{
			JasperPrint jasperPrint = JasperFillManager.fillReport(inputStream, parametros, con);
			return JasperExportManager.exportReportToPdf(jasperPrint);
		} finally {
			con.close();
		}
	}

}