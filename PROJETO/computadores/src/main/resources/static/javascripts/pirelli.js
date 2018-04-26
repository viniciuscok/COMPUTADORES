$(function()
{
	var tipoImpressora = $('.js-radio-tipo-impressora');
	
	tipoImpressora.on('change', function onTipoImpressoraAlterado(evento){
		var tipoImpressoraSelecionada = $(evento.currentTarget);
		var tipoImpressoraInput = $('#id_REDE');
		var enderecoIP = $('#enderecoIPImpressora');
		if(tipoImpressoraSelecionada.data('documento')===tipoImpressoraInput.data('documento'))
		{
			
			enderecoIP.removeAttr('disabled');
			
			enderecoIP.mask('999.999.999.999');
			enderecoIP.focusout( function(){
				enderecoIP.val();
				enderecoIP.ipInput();
				enderecoIP.setIp('192.168.1.1');
				alert(enderecoIP.val());
				console.log(enderecoIP.validate);
			    console.log(enderecoIP.val());
			});
			//console.log('teste')
		}else
		{
			enderecoIP.val('');
			enderecoIP.attr('disabled', 'disabled');
			//console.log("Tipo de impressora selecionada", tipoImpressoraSelecionada.data('documento'));	
		}
		
		
	})
	
	var teste = $('#numeroDeSerieImpressora');
	teste.mask('099.099.099.099');
	teste.ipInput();
	teste.keyup(function() {
		teste.focusout( function(){
		    console.log(teste.val());
		});
		  console.log('oi', teste);
		  
		});
	
	
});