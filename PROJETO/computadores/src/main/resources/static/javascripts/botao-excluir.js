$(function()
{
	var excluirBtn = $('.js-exclusao-btn');
	
	excluirBtn.on('click', function onExcluirClicado(evento){
		event.preventDefault();
		var botaoClicado = $(evento.currentTarget);
		console.log('botao clicado', botaoClicado);
		var url = botaoClicado.data('url');
		console.log('url para analise', url);
		var objeto = botaoClicado.data('objeto');
		console.log('objeto para analise', objeto);
		
		swal({
			title:'tem Certeza?',
			text: 'excluir "'+objeto+'"? Voçê não poderá recuperar depois',
			showCancelButton: true,
			confirmButtonColor: '#DD6B55',
			confirmButtonText: 'Sim, exclua agora',
			closeOnConfirm: true
		}, onExcluirConfirmado.bind(url));
			
	});
	
	function onExcluirConfirmado(url)
	{
		console.log('url', url);
	}
		
});