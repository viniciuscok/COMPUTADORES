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
			
			//console.log('teste')
		}else
		{
			enderecoIP.val('');
			enderecoIP.attr('disabled', 'disabled');
			//console.log("Tipo de impressora selecionada", tipoImpressoraSelecionada.data('documento'));	
		}
		
		
	})
	
	var teste = $('#numeroDeSerieImpressora');
	function validateIP(id) {
        var RegExPattern = /^((25[0-5]|2[0-4][0-9]|1[0-9][0-9]|[1-9][0-9]|[0-9])\.){3}(25[0-5]|2[0-4][0-9]|1[0-9][0-9]|[1-9][0-9]|[0-9])$/;

        //if( (!(id.value.match(RegExPattern)) && (id.value!="")) || id.value=='0.0.0.0' || id.value=='255.255.255.255' ) {
        //   alert('IP inválido.');
        //   id.focus();
       // }
		id.focusout(function(){
	        var vendaMediaMensal = id.val();
	        if( (!(id.value.match(RegExPattern)) && (id.value!="")) || id.val()=='0.0.0.0' || id.val()=='255.255.255.255' ) {
	        	
	            alert('IP inválido.');
	            //id.focus();
	         }else
	        	 {
	        	 alert('entrou no else.');
	        	 }
	    });
        
	}
	validateIP(teste);
	
	
});