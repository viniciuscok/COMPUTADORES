$(function()
{
	
	var excluirBtn = $('.js-exclusao-btn');
	if (window.location.search.indexOf('excluido') > -1) {
		swal('Pronto!', 'Excluído com sucesso!', 'success');
	}
	
	excluirBtn.on('click', function onExcluirClicado(evento){
		event.preventDefault();
		var botaoClicado = $(evento.currentTarget);
		console.log('botao clicado', botaoClicado);
		var url = botaoClicado.data('url');
		var objeto = botaoClicado.data('objeto');
		swal({
			title:'Tem Certeza?',
			text: 'Excluir "'+objeto+'"? Voçê não poderá recuperar depois.',
			cancelButtonText:'Cancelar',
			showCancelButton: true,
			confirmButtonColor: '#DD6B55',
			confirmButtonText: 'Sim, excluir agora.',
			closeOnConfirm: false
		}, function onExcluirConfirmado(){
			$.ajax({
				url: botaoClicado.data('url'),
				method: 'DELETE',
				success: function onExcluidoSucesso(){
					var urlAtual = window.location.href;
					var separador = urlAtual.indexOf('?') > -1 ? '&' : '?';
					var novaUrl = urlAtual.indexOf('excluido') > -1 ? urlAtual : urlAtual + separador + 'excluido';
					
					window.location = novaUrl;
				},
				error: function onErroExcluir(e){
					swal('Oops!', e.reponseText, 'error');
				},
				
			});
		});
			
	});
	
	
		
});