var Pirelli = Pirelli || {};

/*Computador.MaskNumber = (function() {
	
	function MaskNumber() {
		this.decimal = $('.js-decimal');
		this.numeroInteiro = $('.js-numero-inteiro');
	}
	
	MaskNumber.prototype.enable = function() {
		this.numeroInteiro.maskNumber({ integer: true, thousands: '.' });
	}
	
	return MaskNumber;
	
}());*/

Pirelli.TipoComputador = (function() {
	function TipoComputador(){
		this.tipo = $('.js-tipoComputador');
		this.comboModelo = $('#modeloComputador');
		this.enderecoIP = $('#ipComputador');
		this.enviar = $('#computadorSalvar');
	}
	
	TipoComputador.prototype.iniciar = function() {
		
		this.tipo.on('change', onTipoComputadorAlterado.bind(this));
	}
	
	function onTipoComputadorAlterado(evento) {
		var computadorSelecionado = $(evento.currentTarget);
		if(computadorSelecionado.is(':checked'))
		{
			console.log(computadorSelecionado);
			
			this.comboModelo.removeAttr('disabled');
			
		}else
			{
				console.log('teste',evento);
			}
		
		
		
		//var resposta = $.ajax({
		//	url: this.modelo.data('url'),
		//	method: 'GET',
		//	contentType: 'application/json',
		//	data: {'nome': computadorSelecionado.data('tipo')}
		//});
			
		}
	
	return TipoComputador;
	
}());

$(function() {
	//var maskNumber = new Computador.MaskNumber();
	//maskNumber.enable();
	
	var tipoComputador = new Pirelli.TipoComputador();
	tipoComputador.iniciar();
	
	
	
	//var comboCidade = new Brewer.ComboCidade(comboEstado);
	//comboCidade.iniciar();
	
});

$( document ).ready(function() {
	var radios = document.getElementsByName('tipoComputador');

	for (var i = 0, length = radios.length; i < length; i++)
	{
	 if (radios[i].checked)
	 {
		 $('#modeloComputador').prop('disabled', false)
	  break;
	 }
	}
});

/*
$('#formcadastroimpressora').submit(function(){
	console.log("teste");
	$('#enderecoIPImpressora').prop('disabled', false);
})*/
