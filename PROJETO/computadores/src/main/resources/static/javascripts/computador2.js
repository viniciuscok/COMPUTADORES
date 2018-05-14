$(function()
{
	var tipoComputador = $('.js-radio-tipo-computador');
	var enderecoIP = $('#ipComputador');
	var numeroInteiro = $('.js-numero-inteiro');
	
	
	tipoComputador.on('change', function onTipoImpressoraAlterado(evento){
		var tipoImpressoraSelecionada = $(evento.currentTarget);
		var comboModelo = $('#modeloComputador');	
		comboModelo.removeAttr('disabled');
		
	});
	//mascara de endereco ip
	enderecoIP.mask('999.999.999.999');
	numeroInteiro.maskNumber({ integer: true, thousands: '.' });
		
});