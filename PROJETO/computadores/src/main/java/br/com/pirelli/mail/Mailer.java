package br.com.pirelli.mail;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import br.com.pirelli.model.Impressora;

@Component
public class Mailer 
{
	@Autowired
	private JavaMailSender javaMailSender;
	
	List<String> emails;
	
	@Async
	public void enviar(Impressora impressora)
	{
		emails = new ArrayList<>();
		emails.add("pirellifsa@zoho.com");
		emails.add(impressora.getEmail());
		emails.add("antonio.carmo.st@pirelli.com");
		SimpleMailMessage mensagem = new SimpleMailMessage();
		mensagem.setFrom("vinicius email<viniciuscok1234@gmail.com>");
		mensagem.setTo(emails.toArray(new String[emails.size()]));
		mensagem.setSubject("Cartuchos");
		mensagem.setText("Bom dia\r\n" + 
				"\r\n" + 
				"Materiais relacionados abaixo estão no almoxarifado aguardando retirada.\r\n" + 
				"\r\n" + 
				"MATERIAL: Cartucho\r\n" + 
				"EQUIP. SÉRIE: "+impressora.getNumeroDeSerie());
		
		javaMailSender.send(mensagem);
	}

}

