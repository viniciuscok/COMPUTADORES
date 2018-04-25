/*package br.com.pirelli.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import br.com.pirelli.model.TipoModelo;

@Component
public class TipoModeloConverter implements Converter<String, TipoModelo>
{

	@Override
	public TipoModelo convert(String codigo) 
	{
		if(!StringUtils.isEmpty(codigo))
		{
			TipoModelo tipoModelo = new TipoModelo();
			tipoModelo.setCodigo(Long.valueOf(codigo));
		}
		return null;
	}
	
}
*/