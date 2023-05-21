$(document).ready(function() {

	$('#comune').selectize({
		placeholder: window.mexJS['js.placeholder.selcomune'],
	    valueField: 'comune',
	    labelField: 'comune',
	    searchField: 'comune',
	    maxOptions: 8000,
	    options: window.comuniList,
	    onType: function(input) {
	        if (input.length < 3 && input.length > 0) {
	        	$('#comune').siblings('.selectize-control').find('.selectize-dropdown.single.form-control.Input-text').css('display','none');
	        } else {
	        	$('#comune').siblings('.selectize-control').find('.selectize-dropdown.single.form-control.Input-text').css('display','');
	        }
	    },
	    render: {
	    	option: function(item, escape) {
	        	return '<div class="option">'+escape(item.comune)+'</div>';
	        }
	    },
	    onChange: function(value) {
	    	if (!value.length) {
	    		$("#comEstero").prop('disabled', false);
	    		return;
	    	} else {
	    		$('.selectized.error').siblings('div.selectize-control.form-control.Input-text').attr('style', 'border: 1px solid #0075BF !important');
	    	}
	    	if (!this.disabled){
	    		$('#formEventReporting').validate().element('#comune');
	    	}
	    	var data = this.options[value];
	    	$('#codIstat').attr('value', data.codComune);
	    	$("#comEstero").val('');
	    	$("#comEstero").prop('disabled', true);
	    }
	});
	
	$('#comEstero').on('input',function() {
		if (this.value) {
			$('#comune')[0].selectize.clear();
			$('#comune')[0].selectize.disable();
			$('#codIstat').attr('value', '');
    		$('.selectized.error').siblings('div.selectize-control.form-control.Input-text').attr('style', 'border: 1px solid #0075BF !important');
    		$('#comune-error').remove();
		} else {
			$('#comune')[0].selectize.enable();
		}
	});

	
	$('#formEventReporting').validate({
		errorPlacement: function(error, element) {
			  if(element.attr('name') == 'comune') {
				  error.appendTo(element.parent('div'));
			  } else {
			    error.insertAfter(element);
			  }
		},
		rules: {
			nome: {
				required: true,
				normalizer: function(value) {return $.trim(value);}
			},
			dataDa: {
				required: true,
				normalizer: function(value) {return $.trim(value);}
			},
			dataA: {
				required: true,
				normalizer: function(value) {return $.trim(value);}
			},
			comune: {
				required: true,
				normalizer: function(value) {return $.trim(value);}
			},
			riferimento: {
				required: true,
				normalizer: function(value) {return $.trim(value);}
			}
		}
	});
	
	// Gestione Modale
	$('#Modal').on('hide.bs.modal', function(e) {
		$('#t_ricercaEvento').DataTable().destroy();
		$('#bloccotabellamodel').hide();
	});
	
	$('#Modal').on('shown.bs.modal', function() {

		var dataSource = [];
		$.each(listSegn, function(index, value) {
			var x = [listSegn[index]];
			dataSource[index] = x;
		});
		$('#bloccotabellamodel').show();
		$('#t_ricercaEvento').DataTable({
			deferRender: true,
			destroy: true,
			scrollY: "114px",
			scrollX: false,
			language: {url: context+'/assets/json/Italian.json'},
			paginate: false,
			searching: false,
			data: dataSource,
	        initComplete: function(settings, json) {
	        	$('#overlay').hide();
	        },
	        preDrawCallback: function(settings) {
	            var api = new $.fn.dataTable.Api(settings);
	            var pagination = $(this).closest('.dataTables_wrapper').find('.dataTables_paginate');
	            pagination.toggle(api.page.info().pages > 1);
	        }
		});
	});
});

// Reset dei campi inseriti
$('#resetcampi').click(function() {
	$('#formEventReporting').find('input, textarea').val('');
    $('#comune')[0].selectize.clear();
    $('#comune')[0].selectize.enable();
    $('.start-date').datepicker('setEndDate', null);
	$('.end-date').datepicker('setStartDate', dataserver);
    $('#comEstero').prop('disabled', false);
    $('[id*="-error"]').remove();
    $('#formEventReporting').validate().resetForm();
    $("div.selectize-control.form-control.Input-text.single").css('border', '0px');
        
});

//var dataoggi = moment(dataserver, 'YYYY-MM-DD').startOf('day');
//var dataoggiuk = moment(dataoggi, 'YYYY-MM-DD').toDate();
var dataoggiit = moment(dataserver, 'YYYY-MM-DD').format('DD/MM/YYYY');

$('.end-date').datepicker({format: 'dd/mm/yyyy', language: 'it', autoclose: true,  constrainInput: false});
//, startDate: dataoggiit
$('.start-date').datepicker({format: 'dd/mm/yyyy', language: 'it', autoclose: true,  constrainInput: false}).on('change', function () {
	var minDate = $(this).val();
		
	var datainput = moment(minDate, 'DD/MM/YYYY').format('YYYY-MM-DD');
	//datainput = moment(datainput, 'YYYY-MM-DD').toDate();
		
	if(datainput < dataserver){
		minDate = dataoggiit;
	}else{
		minDate = minDate;
	}

	$('.end-date').val('').datepicker('destroy').datepicker({format: 'dd/mm/yyyy', language: 'it', autoclose: true,  constrainInput: false, startDate: minDate});
});

// Salva segnalazione controllando se sono presenti altri eventi nel giorno e nel comune inserito
function salvaSegnalazione() {	
	
	var verificacomune = true;
	var comuneevest = $('#comEstero').val();
	var comuneeve = $('#comune').val();	
	
	if(comuneeve == '' && comuneevest == ''){	
		$('#comune').closest('div').find('div.selectize-control.form-control.Input-text').attr('style', 'border: 1px solid #c10512 !important');
		$('#comEstero').attr('style', 'border: 1px solid #c10512 !important');
		$('label#comuneobberror').show();
	
		verificacomune = false;
	} else {
		$('#comune').closest('div').find('div.selectize-control.form-control.Input-text').attr('style', 'border: 1px solid #0075BF !important');
		$('#comEstero').attr('style', 'border: 1px solid #0075BF !important');
		$('label#comuneobberror').hide();
	}
	
	if($('#formEventReporting').valid() && verificacomune == true){
		controlloSegnalazioniPresenti();
	} else {
		return false;
	}
}

$(document).on('change', '#comune, #comEstero' , function(){	

	$('#comune').closest('div').find('div.selectize-control.form-control.Input-text').attr('style', 'border: 1px solid #0075BF !important');
	$('#comEstero').attr('style', 'border: 1px solid #0075BF !important');
	$('#comuneobberror').hide();
	
});


$(document).on('change', '#dataDa, #dataA' , function(){
$(this).removeClass('error');
$(this).closest('div').find('.error').remove();
});


// Inizializzo la variabile affinchè possa essere letto dalla modale allo show
var listSegn = null;

function controlloSegnalazioniPresenti(){
	
	var dataDa = convertDataItaToEng($('#dataDa').val());
	var codIstat = $('#codIstat').val();
	
	listSegn = null;
	
	$.ajax({
		url: context + '/listSegnalazione?dataDa=' + dataDa + '&codIstat=' + codIstat,
		type: 'GET',
		cache: false,
		beforeSend: function(){
			$('#overlay').show();
		},
		complete: function(){
			$('#overlay').hide();
		},
		success: function(data) {
			
			listSegn = data;
			if (listSegn && listSegn.length > 0) {
				$('#Modal').modal('show');
			} else {
				alertify.confirm("<i class=\"icon-errore\" style=\"font-size:64px; color:#a66300;\"></i><br><br>" + window.mexJS['js.segnalazione.conferma']).set({
					title: window.mexJS['js.segnalazione.conferma.titolo']}).set('labels', {ok:'Si', cancel:'No'}).set('onok', function(){
					
					inviaSegnalazione();
				}).set('oncancel', function(){
					return;
				});
			}
			
		},
		error: function() {
			alertify.alert(window.mexJS['js.error.errore'], "<img src=\"" + context + "/assets/images/ERRORE.svg\"><br/><br/><br/>" + window.mexJS['js.error.servererror']).set('label', 'Chiudi'); 
		}
	});
}

// Annullamento invio segnalazione
function annullaSegnalazione() {
	
	$('#Modal').modal('hide');
	//alertify.alert(window.mexJS['js.esito.operazione'], "<i class=\"icon-successo\" style=\"font-size:64px; color:#58CB7D;\"></i><br/><br/><br/>" + window.mexJS['js.esitodettaglio.segnalazione.annullato']).set('label', 'Chiudi'); 

}

// Invio segnalazione
function inviaSegnalazione() {

	var jsonDTO = $('#formEventReporting').serializeFormJSON();
	
	$('#Modal').modal('hide');
	
	$.ajax({
		url: context + '/aggiungiSegnalazione',
		type: 'POST',
		contentType: 'application/json; charset=UTF-8',
		data: JSON.stringify(jsonDTO),
		beforeSend: function(){
			$('#overlay').show();
		},
		complete: function(){
			$('#overlay').hide();
		},
		success: function() {
			alertify.alert("Azione confermata", "<i class=\"icon-successo\" style=\"font-size:64px; color:#58CB7D;\"></i><br/><br/><br/>"+window.mexJS['js.esitodettaglio.segnalazione.successo']).set('label', 'Chiudi'); 
			$("#resetcampi").click();
	
			$.applyValidationFix();
		},
		error: function() {
			
			alertify.alert(window.mexJS['js.error.errore'], "<img src=\""+context+"/assets/images/ERRORE.svg\"><br/><br/><br/>"+window.mexJS['js.error.servererror']).set('label', 'Chiudi'); 
		}
	});
}

function strip(html)
{
   var tmp = document.createElement("DIV");
   tmp.innerHTML = html;
   return tmp.textContent||tmp.innerText;
}

$(document).ready(function(){
	$(document).on("change, click, focusout", "textarea, #nome", function() {
		$(this).val(function(index, old){			
			old = old.replace(/‘/g,"'");
			old = old.replace(/’/g,"'");
			old = old.replace(/“/g,"\"");
			old = old.replace(/”/g,"\""); 			
			old = old.replace(/–/g,"-");
			old = old.replace(/—/g,"-"); 			
		old = strip(old);		
		old = cleanString(old);
		return old;
		});
	});
});

