/*package br.com.pirelli.controller.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.thymeleaf.util.StringUtils;

import br.com.pirelli.model.OsComputador;

@Component
public class OsComputadorConverter implements Converter<String, OsComputador>
{
	@Override
	public OsComputador convert(String codigo) {
		if (!StringUtils.isEmpty(codigo)) {
			OsComputador osComputador = new OsComputador();
			osComputador.setCodigo(Long.valueOf(codigo));
			return osComputador;
		}
		
		return null;
	}

}
*/