var Computador = Computador || {};

Computador.MaskNumber = (function() {
	
	function MaskNumber() {
		this.decimal = $('.js-decimal');
		this.numeroInteiro = $('.js-numero-inteiro');
	}
	
	MaskNumber.prototype.enable = function() {
		this.numeroInteiro.maskNumber({ integer: true, thousands: '.' });
	}
	
	return MaskNumber;
	
}());

Computador.TipoComputador = (function() {
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
	var maskNumber = new Computador.MaskNumber();
	maskNumber.enable();
	
	var tipoComputador = new Computador.TipoComputador();
	tipoComputador.iniciar();
	
	
	
	//var comboCidade = new Brewer.ComboCidade(comboEstado);
	//comboCidade.iniciar();
	
});

/*
Computador.MaskMoney = (function() {
	
	function MaskMoney() {
		this.decimal = $('.js-decimal');
		this.plain = $('.js-plain');
	}
	
	MaskMoney.prototype.enable = function() {
//		this.decimal.maskMoney({ decimal: ',', thousands: '.' });
//		this.plain.maskMoney({ precision: 0, thousands: '.' });
		this.decimal.maskNumber({ decimal: ',', thousands: '.' });
		this.plain.maskNumber({ integer: true, thousands: '.' });
	}
	
	return MaskMoney;
	
}());

Computador.MaskPhoneNumber = (function() {
	
	function MaskPhoneNumber() {
		this.inputPhoneNumber = $('.js-phone-number');
	}
	
	MaskPhoneNumber.prototype.enable = function() {
		var maskBehavior = function (val) {
		  return val.replace(/\D/g, '').length === 11 ? '(00) 00000-0000' : '(00) 0000-00009';
		};
		
		var options = {
		  onKeyPress: function(val, e, field, options) {
		      field.mask(maskBehavior.apply({}, arguments), options);
		    }
		};
		
		this.inputPhoneNumber.mask(maskBehavior, options);
	}
	
	return MaskPhoneNumber;
	
}());

Computador.MaskCep = (function() {
	
	function MaskCep() {
		this.inputCep = $('.js-cep');
	}
	
	MaskCep.prototype.enable = function() {
		this.inputCep.mask('00.000-000');
	}
	
	return MaskCep;
	
}());

Computador.MaskDate = (function() {
	
	function MaskDate() {
		this.inputDate = $('.js-date');
	}
	
	MaskDate.prototype.enable = function() {
		this.inputDate.mask('00/00/0000');
		this.inputDate.datepicker({
			orientation: 'bottom',
			language: 'pt-BR',
			autoclose: true
		});
	}
	
	return MaskDate;
	
}());

Computador.Security = (function() {
	
	function Security() {
		this.token = $('input[name=_csrf]').val();
		this.header = $('input[name=_csrf_header]').val();
	}
	
	Security.prototype.enable = function() {
		$(document).ajaxSend(function(event, jqxhr, settings) {
			jqxhr.setRequestHeader(this.header, this.token);
		}.bind(this));
	}
	
	return Security;
	
}());

numeral.language('pt-br');

Computador.formatarMoeda = function(valor) {
	return numeral(valor).format('0,0.00');
}

Computador.recuperarValor = function(valorFormatado) {
	return numeral().unformat(valorFormatado);
}

$(function() {
	//var maskMoney = new Brewer.MaskMoney();
	//maskMoney.enable();
	
	//var maskPhoneNumber = new Brewer.MaskPhoneNumber();
	//maskPhoneNumber.enable();
	
	//var maskCep = new Brewer.MaskCep();
	//maskCep.enable();
	
	//var maskDate = new Brewer.MaskDate();
	//maskDate.enable();
	
	//var security = new Brewer.Security();
	//security.enable();
	
});*/
