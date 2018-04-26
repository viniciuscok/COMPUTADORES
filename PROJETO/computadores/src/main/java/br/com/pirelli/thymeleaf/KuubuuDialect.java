package br.com.pirelli.thymeleaf;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Component;
import org.thymeleaf.dialect.AbstractProcessorDialect;
import org.thymeleaf.processor.IProcessor;
import org.thymeleaf.standard.StandardDialect;

@Component
public class KuubuuDialect extends AbstractProcessorDialect
{

	protected KuubuuDialect() 
	{
		super("kuubuu", "kuubuu", StandardDialect.PROCESSOR_PRECEDENCE);
	}

	@Override
	public Set<IProcessor> getProcessors(String dialectPrefix) 
	{
		final Set<IProcessor> processadores = new HashSet<>();
		processadores.add(new ErrorTextAttributeTagProcessor(dialectPrefix));
		processadores.add(new PaginationElementTagProcessor(dialectPrefix));
		processadores.add(new OrderElementTagProcessor(dialectPrefix));
		return processadores;
	}

}
