(function($) {
    $.applyValidationFix = function() {
        $('form').each(function (index, form) {
            $.extend($(form).validate().settings, {
                ignore: []//':hidden:not([class~=selectized]), :hidden > .selectized, .selectize-control .selectize-input input'
            });
        });
    };
}(jQuery));

jQuery.extend(jQuery.validator.messages, {
    required: "* campo obbligatorio",
    email: "* inserisci un indirizzo mail valido",
    number: "* inserisci solo numeri",
    digits: "* inserisci solo numeri",
    maxlength: jQuery.validator.format("* non superare il numero limite di caratteri richiesto"),
    minlength: jQuery.validator.format("* inserisci il numero minimo di caratteri richiesto"),
    min: jQuery.validator.format( "* inserisci un valore maggiore di 0" ),
    require_from_group: "* seleziona almeno un elemento",
    requireorder: "* inserire valori non duplicati"
});


jQuery.validator.addMethod("orderValid",function(value, element) {
	var listorder = [];
	var validazione = true;
	  $('.contaimmagini').find('input[name$=".ordine"]').each(function( index ) {			  
		 var ordine = $(this).val();		 
		 if(ordine != ''){
			 listorder.push(ordine);
		 }
	  });		  
	  let hasDuplicate = listorder.some((val, i) => listorder.indexOf(val) !== i);		  
		  if(hasDuplicate == true){
			  validazione = false;
	      }	 
	  return validazione;	
}, "* inserire valori non duplicati");

jQuery.validator.addMethod("orderValidDocumenti",function(value, element) {
	var listorder = [];
	var validazione = true;
	  $('.contadocumenti').find('input[name$=".ordine"]').each(function( index ) {			  
		 var ordine = $(this).val();		 
		 if(ordine != ''){
			 listorder.push(ordine);
		 }
	  });		  
	  let hasDuplicate = listorder.some((val, i) => listorder.indexOf(val) !== i);		  
		  if(hasDuplicate == true){
			  validazione = false;
	      }	 
	  return validazione;	
}, "* inserire valori non duplicati");


jQuery.validator.addMethod("orderValidLink",function(value, element) {
	var listorder = [];
	var validazione = true;
	  $('.contalink').find('input[name$=".ordine"]').each(function( index ) {			  
		 var ordine = $(this).val();		 
		 if(ordine != ''){
			 listorder.push(ordine);
		 }
	  });		  
	  let hasDuplicate = listorder.some((val, i) => listorder.indexOf(val) !== i);		  
		  if(hasDuplicate == true){
			  validazione = false;
	      }	 
	  return validazione;	
}, "* inserire valori non duplicati");

 


var listorderImg = [];
function orderValidVip() {
	listorderImg = [];

	var validazione = true;
	  $('.contaimmaginivip').find('input[name$=".ordine"]').each(function( index ) {			  
		 var ordine = $(this).val();		 
		 if(ordine != ''){
			 listorderImg.push(ordine);
		 }
	  });	
	  $('.contaimmaginivipagg').find('input[name$=".ordine"]').each(function( index ) {			  
			 var ordine = $(this).val();		 
			 if(ordine != ''){
				 listorderImg.push(ordine);
			 }
		  });	
	  let hasDuplicate = listorderImg.some((val, i) => listorderImg.indexOf(val) !== i);		  
		  if(hasDuplicate == true){
			  validazione = false;
	      }	 
	  return validazione;	
};

function GetDuplicates(arr) { 
	var i, out=[], obj={}; for (i=0; i < arr.length; i++) obj[arr[i]] == undefined ? obj[arr[i]] ++ : out.push(arr[i]); 
	return GetUnique(out);
} 
function GetUnique(arr) { 
	return $.grep(arr, function(elem, index) { return index == $.inArray(elem, arr); });
} 




var listorderDoc = [];
function orderValidDocumentiVip() {
	 listorderDoc = [];
	var validazione = true;
	  $('.contadocumentivip').find('input[name$=".ordineNumerico"]').each(function( index ) {			  
		 var ordine = $(this).val();		 
		 if(ordine != ''){
			 listorderDoc.push(ordine);
		 }
	  });	
	  $('.contadocumentivipagg').find('input[name$=".ordineNumerico"]').each(function( index ) {			  
			 var ordine = $(this).val();		 
			 if(ordine != ''){
				 listorderDoc.push(ordine);
			 }
		  });	
	  let hasDuplicate = listorderDoc.some((val, i) => listorderDoc.indexOf(val) !== i);		  
		  if(hasDuplicate == true){
			  validazione = false;
	      }	 
	  return validazione;	
};

var listorderLink = [];
function orderValidLinkVip() {
	listorderLink = [];
	var validazione = true;
	  $('.contalinkvip').find('input[name$=".ordineNumerico"]').each(function( index ) {			  
		 var ordine = $(this).val();		 
		 if(ordine != ''){
			 listorderLink.push(ordine);
		 }
	  });	
	  $('.contalinkvipagg').find('input[name$=".ordineNumerico"]').each(function( index ) {			  
			 var ordine = $(this).val();		 
			 if(ordine != ''){
				 listorderLink.push(ordine);
			 }
		  });	
	  let hasDuplicate = listorderLink.some((val, i) => listorderLink.indexOf(val) !== i);		  
		  if(hasDuplicate == true){
			  validazione = false;
	      }	 
	  return validazione;	
};








function convertDataItaToEng(data){
	var dateParts = data.split('/');
	return dateParts[2] + '-' + dateParts[1] + '-' + dateParts[0];
}

function convertDataEngToIta(data){
	if (data !=null && data !=''){
		var dateParts = data.split('-');
		if (dateParts[2] != undefined){
			return dateParts[2] + '/' + dateParts[1] + '/' + dateParts[0];
		}else{
			return '';
		}
	}
}

function convertDatatableWrapper(d, columns, f){
	var wrapper = new Object();
	var det = new Object();
	det.draw = d['draw'];
	det.start = d['start'];
	det.length = d['length'];
	det.search = d['search'].value;
	det.order = columns[d['order'][0]['column']].name;
	det.dir = d['order'][0]['dir'];
	wrapper.filter = f;
	wrapper.detail = det;
	return JSON.stringify(wrapper);
}

if (!String.prototype.startsWith) {
    Object.defineProperty(String.prototype, 'startsWith', {
        value: function(search, rawPos) {
            var pos = rawPos > 0 ? rawPos|0 : 0;
            return this.substring(pos, pos + search.length) === search;
        }
    });
}

if (!String.prototype.endsWith) {
	String.prototype.endsWith = function(search, this_len) {
		if (this_len === undefined || this_len > this.length) {
			this_len = this.length;
		}
		return this.substring(this_len - search.length, this_len) === search;
	};
}

if (!Array.prototype.includes) {
  Object.defineProperty(Array.prototype, 'includes', {
    value: function(searchElement, fromIndex) {

      if (this == null) {
        throw new TypeError('"this" is null or not defined');
      }

      var o = Object(this);

      var len = o.length >>> 0;

      if (len === 0) {
        return false;
      }

      var n = fromIndex | 0;
      var k = Math.max(n >= 0 ? n : len - Math.abs(n), 0);

      function sameValueZero(x, y) {
        return x === y || (typeof x === 'number' && typeof y === 'number' && isNaN(x) && isNaN(y));
      }

      while (k < len) {
        if (sameValueZero(o[k], searchElement)) {
          return true;
        }
        k++;
      }

      return false;
    }
  });
}

function calculateDaysBetweenItalianDates(start, end){
	if (start == null || end ==null || start == "" || end == ""){
		return null;
	}
	var dateParts = start.split('/');
	var startDate = new Date(dateParts[2],dateParts[1],dateParts[0]);
	dateParts = end.split('/');
	var endDate = new Date(dateParts[2],dateParts[1],dateParts[0]);
	var diffInTime = endDate.getTime() - startDate.getTime(); 
	var diffInDays = diffInTime / (1000 * 3600 * 24); 
	return diffInDays;
}

$("input[data-type='currency']").on({
    keyup: function() {
      formatCurrency($(this));
    },
    blur: function() { 
      formatCurrency($(this), "blur");
    }
});

function formatNumber(n) {
  // format number 1000000 to 1,234,567
  return n.replace(/\D/g, "").replace(/\B(?=(\d{3})+(?!\d))/g, ",")
}

function formatCurrency(input, blur) {
	var input_val = input.val();
	if (input_val === "") return;
	var original_len = input_val.length;
	var caret_pos = input.prop("selectionStart");
	
	if (input_val.indexOf(".") >= 0) {
		var decimal_pos = input_val.indexOf(".");
		var left_side = input_val.substring(0, decimal_pos);
		var right_side = input_val.substring(decimal_pos);
		left_side = formatNumber(left_side);
		right_side = formatNumber(right_side);
		if (blur === "blur") {
			right_side += "00";
		}
		right_side = right_side.substring(0, 2);
		input_val = left_side + "." + right_side;
	} else {
		input_val = formatNumber(input_val);
		input_val = input_val;
		if (blur === "blur") {
			input_val += ".00";
		}
	}
	
	input.val(input_val);
	var updated_len = input_val.length;
	caret_pos = updated_len - original_len + caret_pos;
	input[0].setSelectionRange(caret_pos, caret_pos);
}

//$('.inputicondata2').after().click(function() {
//    alert("Yes");
//});
$(document).on('click', '#opencalendar' , function(){
	$(this).closest('div.inputicondata').find('input').focus();
}); 

$(document).on('click', '#opentime' , function(){

	$(this).closest('div.appendtime').find('input').select().click();

}); 
//	
//
//
//$(function(){
//    $('.inputicondata2\\:after').click(function() {
//        alert("Yes");
//    }); 
//});
$(document).ready(function(){
$('.inputicondata2').append("<div class=\"calendardatep\"><img src=\""+context+"/assets/images/CALENDARIO_full.svg\" id=\"opencalendar\" aria-hidden=\"true\"></div>");
$('.inputicondata').append("<div class=\"calendardatep\"><img src=\""+context+"/assets/images/CALENDARIO_full.svg\" id=\"opencalendar\" aria-hidden=\"true\"></div>");
$('.appendtime').append("<div class=\"calendartimep\"><img src=\""+context+"/assets/images/orario.svg\" id=\"opentime\" aria-hidden=\"true\"></div>");
});



function chkorder(){
	var listorder = [];
	var validazione = true;
	  $('.contaimmagini').find('input[name$="].ordine"]').each(function( index ) {			  
		 var ordine = $(this).val();		 
		 if(ordine !=''){
			 listorder.push(ordine);
		 }
	  });
	  
	 let hasDuplicate = listorder.some((val, i) => listorder.indexOf(val) !== i);		  
		  if(hasDuplicate == true){
			  validazione = false;
		  }	 
	 return validazione;
}

//Controllo valori duplicati campo ordine
function chkordervip(statoPubblicazione){
	
	if( (statoPubblicazione && statoPubblicazione == "PUBBLICATO")||(statoPubblicazione && statoPubblicazione == "REDATTO")) {	
	var listorder = [];
	var validazione = true;
	
	
		  $('.contaimmaginivip').find('input[name$="].ordine"]').each(function( index ) {			  
			 var ordine = $(this).val();		 
			 if(ordine != ''){
				 listorder.push(ordine);
			 }
		  });	
		  $('.contaimmaginivipagg').find('input[name$="].ordine"]').each(function( index ) {			  
				 var ordine = $(this).val();		 
				 if(ordine != ''){
					 listorder.push(ordine);
				 }
			  });	
	  		let hasDuplicate = listorder.some((val, i) => listorder.indexOf(val) !== i);		  
		  if(hasDuplicate == true){
			  
			  var listorderImgD = GetDuplicates(listorde2);	
			  
			  $('#immaginiVIPbox').find('input[name$="].ordine"]').each(function( index ) {	
				  if( listorderImgD.indexOf($(this).val()) != -1 ){	
				  $(this).addClass('error');
				  $(this).next('label').empty().html("* inserire valori non duplicati").show();
				  }
			  });	
			  $('#immaginiAggVIPbox').find('input[name$="].ordine"]').each(function( index ) {	
				  if( listorderImgD.indexOf($(this).val()) != -1 ){	
				  $(this).addClass('error');
				  $(this).next('label').empty().html("* inserire valori non duplicati").show();
				  }
			  });		    	
			  validazione = false;
	      }	else{
	    	  $('#immaginiVIPbox').find('input[name$="].ordine"]').each(function( index ) {		
				  $(this).removeClass('error');
				  $(this).next('label').hide();
			  });
	    	  $('#immaginiAggVIPbox').find('input[name$="].ordine"]').each(function( index ) {	
	    		  $(this).removeClass('error');
				  $(this).next('label').hide();
			  });
	      } 
		  
		  
		  
		  
		  var listorder2 = [];
		  $('.contadocumentivip').find('input[name$="].ordineNumerico"]').each(function( index ) {			  
				 var ordine = $(this).val();		 
				 if(ordine != ''){
					 listorder2.push(ordine);
				 }
			  });	
			  $('.contadocumentivipagg').find('input[name$="].ordineNumerico"]').each(function( index ) {			  
					 var ordine = $(this).val();		 
					 if(ordine != ''){
						 listorder2.push(ordine);
					 }
				  });	
		  		let hasDuplicate2 = listorder2.some((val, i) => listorder2.indexOf(val) !== i);		  
			  if(hasDuplicate2 == true){
				  
				  var listorderDocD = GetDuplicates(listorder2);					
				  $('#documentiVIPbox').find('input[name$="].ordineNumerico"]').each(function( index ) {
					  if( listorderDocD.indexOf($(this).val()) != -1 ){	
					  $(this).addClass('error');
					  $(this).next('label').empty().html("* inserire valori non duplicati").show();
					  }
				  });	
			
				  $('#documentiAggVIPbox').find('input[name$="].ordineNumerico"]').each(function( index ) {	
					  if( listorderDocD.indexOf($(this).val()) != -1 ){	
					  $(this).addClass('error');
					  $(this).next('label').empty().html("* inserire valori non duplicati").show();
					  }
				  });	
				  
				  
				  validazione = false;
		      }	else{
		    	  $('#documentiVIPbox').find('input[name$="].ordineNumerico"]').each(function( index ) {		
					  $(this).removeClass('error');
					  $(this).next('label').hide();
				  });
		    	  $('#documentiAggVIPbox').find('input[name$="].ordineNumerico"]').each(function( index ) {	
		    		  $(this).removeClass('error');
					  $(this).next('label').hide();
				  });
		      } 
		  
			  var listorder3 = [];
			  $('.contalinkvip').find('input[name$="].ordineNumerico"]').each(function( index ) {			  
					 var ordine = $(this).val();		 
					 if(ordine != ''){
						 listorder3.push(ordine);
					 }
				  });	
				  $('.contalinkvipagg').find('input[name$="].ordineNumerico"]').each(function( index ) {			  
						 var ordine = $(this).val();		 
						 if(ordine != ''){
							 listorder3.push(ordine);
						 }
					  });	
			  		let hasDuplicate3 = listorder3.some((val, i) => listorder3.indexOf(val) !== i);		  
				  if(hasDuplicate3 == true){
					  var listorderLinkD = GetDuplicates(listorder3);	
					  $('#linkVIPbox').find('input[name$="].ordineNumerico"]').each(function( index ) {	
						  if( listorderLinkD.indexOf($(this).val()) != -1 ){	
						  $(this).addClass('error');
						  $(this).next('label').empty().html("* inserire valori non duplicati").show();
						  }
					  });	
					  $('#linkAggVIPbox').find('input[name$="].ordineNumerico"]').each(function( index ) {	
						  if( listorderLinkD.indexOf($(this).val()) != -1 ){	
						  $(this).addClass('error');
						  $(this).next('label').empty().html("* inserire valori non duplicati").show();
						  }
					  });		    	
					  validazione = false;
			      }	else{
			    	  $('#linkVIPbox').find('input[name$="].ordineNumerico"]').each(function( index ) {		
						  $(this).removeClass('error');
						  $(this).next('label').hide();
					  });
			    	  $('#linkAggVIPbox').find('input[name$="].ordineNumerico"]').each(function( index ) {	
			    		  $(this).removeClass('error');
						  $(this).next('label').hide();
					  });
			      } 
		  
		  if(validazione == true){
			  return 0;
		  }else{
			  return 1;
		  }		  
	}
}





