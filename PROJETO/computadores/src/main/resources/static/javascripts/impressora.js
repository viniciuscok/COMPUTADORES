$(function()
{
	var tipoImpressora = $('.js-radio-tipo-impressora');
	
	tipoImpressora.on('change', function onTipoImpressoraAlterado(evento){
		var tipoImpressoraSelecionada = $(evento.currentTarget);
		var tipoImpressoraInput = $('#id_REDE');
		var enderecoIP = $('#enderecoIPImpressora');
		if(tipoImpressoraSelecionada.data('documento')==tipoImpressoraInput.data('documento'))
		{
			
			enderecoIP.removeAttr('disabled');
			enderecoIP.mask('999.999.999.999');
			
			//console.log('teste')
		}else
		{
			enderecoIP.val('');
			enderecoIP.attr('disabled', 'disabled');
			//console.log("Tipo de impressora selecionada", tipoImpressoraSelecionada.data('documento'));	
		}
		
		
	});
	
});

$( document ).ready(function() {
	var radios = document.getElementsByName('tipoImpressora');

	for (var i = 0, length = radios.length; i < length; i++)
	{
	 if (radios[i].checked)
	 {
		 $('#enderecoIPImpressora').prop('disabled', false)
	  break;
	 }
	}
});

$('#formcadastroimpressora').submit(function(){
	console.log("teste");
	$('#enderecoIPImpressora').prop('disabled', false);
})
