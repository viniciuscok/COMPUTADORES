package br.com.pirelli.controller.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.thymeleaf.util.StringUtils;

import br.com.pirelli.model.Login;

@Component
public class LoginConverter implements Converter<String, Login>
{	

		@Override
		public Login convert(String codigo) {
			if (!StringUtils.isEmpty(codigo)) {
				Login login = new Login();
				login.setCodigo(Long.valueOf(codigo));
				return login;
			}
			
			return null;
		}

	

}
