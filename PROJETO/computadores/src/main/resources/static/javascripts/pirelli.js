var Pirelli = Pirelli || {};

Pirelli.MaskDate = (function() {
	
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

Pirelli.MaskNumber = (function() {
	
	function MaskNumber() {
		this.decimal = $('.js-decimal');
		this.numeroInteiro = $('.js-numero-inteiro');
	}
	
	MaskNumber.prototype.enable = function() {
		this.numeroInteiro.maskNumber({ integer: true, thousands: '.' });
	}
	
	return MaskNumber;
	
}());

$(function() {
	
	var maskDate = new Pirelli.MaskDate();
	maskDate.enable();
	
	var maskNumber = new Pirelli.MaskNumber();
	maskNumber.enable();
	
});
