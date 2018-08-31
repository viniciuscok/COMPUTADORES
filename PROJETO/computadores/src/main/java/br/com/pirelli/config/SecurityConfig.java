package br.com.pirelli.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import br.com.pirelli.security.AppUserDetailsService;

@EnableWebSecurity
@ComponentScan(basePackageClasses = AppUserDetailsService.class)
public class SecurityConfig extends WebSecurityConfigurerAdapter
{
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception 
	{
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
		
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception 
	{
		web.ignoring()
		.antMatchers("/login/**")
		.antMatchers("/layout/**")
		.antMatchers("/images/**");
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception 
	{
		http
			.authorizeRequests()
				.antMatchers("/computadores/novo").hasRole("CADASTRAR_COMPUTADOR")
				.antMatchers("/filiais/nova").hasRole("CADASTRAR_FILIAL")
				.antMatchers("/grupos/novo").hasRole("CADASTRAR_GRUPO")
				.antMatchers("/impressoras/novo").hasRole("CADASTRAR_IMPRESSORA")
				.antMatchers("/logins/novo").hasRole("CADASTRAR_LOGIN")
				.antMatchers("/maquinas/nova").hasRole("CADASTRAR_MAQUINA")
				.antMatchers("/marcas/nova").hasRole("CADASTRAR_MARCA")
				.antMatchers("/modelos/novo").hasRole("CADASTRAR_MODELO")
				.antMatchers("/permissoes/nova").hasRole("CADASTRAR_PERMISSAO")
				.antMatchers("/programas/novo").hasRole("CADASTRAR_PROGRAMA")
				.antMatchers("/setores/novo").hasRole("CADASTRAR_SETOR")
				.antMatchers("/tipomodelos/novo").hasRole("CADASTRAR_TIPO_MODELO")
				.antMatchers("/toners/novo").hasRole("CADASTRAR_TONER")
				.antMatchers("/usuarios/novo").hasRole("CADASTRAR_USUARIO")
				.antMatchers("/oscomputadores/nova").hasRole("CADASTRAR_OSCOMPUTADOR")
				.antMatchers("/osimpressoras/nova").hasRole("CADASTRAR_OSIMPRESSORA")
				//PESQUISA
				.antMatchers("/computadores").hasRole("PESQUISAR_COMPUTADOR")
				.antMatchers("/filiais").hasRole("PESQUISAR_FILIAL")
				.antMatchers("/grupos").hasRole("PESQUISAR_GRUPO")
				.antMatchers("/impressoras").hasRole("PESQUISAR_IMPRESSORA")
				.antMatchers("/logins").hasRole("PESQUISAR_LOGIN")
				.antMatchers("/maquinas").hasRole("PESQUISAR_MAQUINA")
				.antMatchers("/marcas").hasRole("PESQUISAR_MARCA")
				.antMatchers("/modelos").hasRole("PESQUISAR_MODELO")
				.antMatchers("/permissoes").hasRole("PESQUISAR_PERMISSAO")
				.antMatchers("/programas").hasRole("PESQUISAR_PROGRAMA")
				.antMatchers("/setores").hasRole("PESQUISAR_SETOR")
				.antMatchers("/tipomodelos").hasRole("PESQUISAR_TIPO_MODELO")
				.antMatchers("/toners").hasRole("PESQUISAR_TONER")
				.antMatchers("/usuarios").hasRole("PESQUISAR_USUARIO")
				.antMatchers("/oscomputadores").hasRole("PESQUISAR_OSCOMPUTADOR")
				.antMatchers("/osimpressoras").hasRole("PESQUISAR_OSIMPRESSORA")
				// MENU
				.antMatchers().hasRole("MENU_CADASTRAR")
				.antMatchers().hasRole("MENU_BUSCAR")
				.antMatchers().hasRole("MENU_CONFIGURACOES")
				.antMatchers().hasRole("MENU_MANUTENCAO")
				.antMatchers().hasRole("SISTEMA")
				.antMatchers().hasRole("ADMINISTRADOR")
				.anyRequest().authenticated()
				.and()
			.formLogin()
				.loginPage("/index")
				.permitAll()
				.and()
			.logout()
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
				.and().csrf().disable()
			//.exceptionHandling()
				//.accessDeniedPage("/403")
				//.and()
			.sessionManagement()
				.invalidSessionUrl("/index")
				.maximumSessions(1)
				.expiredUrl("/index");
		
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}