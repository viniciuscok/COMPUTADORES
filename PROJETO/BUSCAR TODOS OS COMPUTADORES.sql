SELECT comp.codigo, comp.nome, mo.nome, comp.processador, comp.memoria, comp.hd, comp.sistema_operacional FROM equipamento as eq 
	inner join computador as comp on eq.codigo = comp.codigo
    inner join modelo as mo on mo.codigo = eq.codigo_modelo order by comp.nome;
		
        