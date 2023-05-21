$('#formSchedaB2BDMS').initializeBindingOnForm();

(function() {
	poll = function() {
		var eventoid = $("#eventoId").text();
		if (eventoid) {
			$.ajax({
				type: 'POST',
				url: context+'/lockEvento?idEvento='+eventoid,
				success: function(response) {
				//	console.log('Lock rinnovato su evento: '+eventoid);
				}
			});
		}
	}
	if (window.pollInterval != null) {
		clearInterval(window.pollInterval);
		window.pollInterval = null;
	}
	pollInterval = setInterval(function() { poll(); }, 15000);
	poll();
})();

function ritornaAEvento(){
//	visualizzaEvento($('#eventoId').text());
//	window.enterInRed = true;
	location.reload();
}

$('#prodotto').selectize({
	valueField: 'prodotto',
    labelField: 'prodotto',
    searchField: 'prodotto',
    plugins: ['remove_button'],
    placeholder: window.mexJS['js.placeholder.prodotti'],
    options: window.prodotti,
    onChange: function(value) {
    	if (value.length){
    		//$('#prodotto').siblings('div.scegliere').attr('style', 'color: #0075BF !important');
    		$('#prodotto').siblings('div.selectize-control.form-control.Input-text').attr('style', 'border: 1px solid #0075BF !important');
    		$('#prodotto').siblings('#prodotto-error').hide();
    	} else {
    		//$('#prodotto').siblings('div.scegliere').attr('style', 'color: #c10512 !important');
    		$('#prodotto').siblings('div.selectize-control.form-control.Input-text').attr('style', 'border: 1px solid #c10512 !important');
    		$('#prodotto').siblings('#prodotto-error').show();
    	}
    },
    render: {
        option: function(item, escape) {
        	return '<div class="option">'+escape(item.prodotto)+'</div>';
        }
    }
});

$('#segmento').selectize({
	valueField: 'segmento',
    labelField: 'segmento',
    searchField: 'segmento',
    plugins: ['remove_button'],
    placeholder: window.mexJS['js.placeholder.segmenti'],
    options: [{'segmento':'Lusso ed esperienze esclusive'},{'segmento':'Capacità di spesa media'},{'segmento':'Budget basso'}],
    onChange: function(value) {
    	if (value.length){
    		//$('#segmento').siblings('div.scegliere').attr('style', 'color: #0075BF !important');
    		$('#segmento').siblings('div.selectize-control.form-control.Input-text').attr('style', 'border: 1px solid #0075BF !important');
    		$('#segmento').siblings('#segmento-error').hide();
    	} else {
    		//$('#segmento').siblings('div.scegliere').attr('style', 'color: #c10512 !important');
    		$('#segmento').siblings('div.selectize-control.form-control.Input-text').attr('style', 'border: 1px solid #c10512 !important');
    		$('#segmento').siblings('#segmento-error').show();
    	}
    },
    render: {
        option: function(item, escape) {
        	return '<div class="option">'+escape(item.segmento)+'</div>';
        }
    }
});

$('#sottotipologieSet').selectize({
	valueField: 'sottotipologia',
    labelField: 'sottotipologia',
    searchField: 'sottotipologia',
    plugins: ['remove_button'],
    placeholder: "Seleziona una o più",
    options: [{'sottotipologia':'fiera'}, {'sottotipologia':'workshop b2b'}, {'sottotipologia':'roadshow'}, {'sottotipologia':'educational'}],
    onChange: function(value) {
    	if (value.length){
    		//$('#sottotipologieSet').siblings('div.scegliere').attr('style', 'color: #0075BF !important');
    		$('#sottotipologieSet').siblings('div.selectize-control.form-control.Input-text').attr('style', 'border: 1px solid #0075BF !important');
    		$('#sottotipologieSet').siblings('#sottotipologieSet-error').hide();
    	} else {
    		//$('#sottotipologieSet').siblings('div.scegliere').attr('style', 'color: #c10512 !important');
    		$('#sottotipologieSet').siblings('div.selectize-control.form-control.Input-text').attr('style', 'border: 1px solid #c10512 !important');
    		$('#sottotipologieSet').siblings('#sottotipologieSet-error').show();
    	}
    },
    render: {
        option: function(item, escape) {
        	return '<div class="option">'+escape(item.sottotipologia)+'</div>';
        }
    }
});

$('#modalitaPartecipazione').selectize({
	valueField: 'modalitaPartecipazione',
    labelField: 'modalitaPartecipazione',
    searchField: 'modalitaPartecipazione',
    plugins: ['remove_button'],
    placeholder: "Seleziona una o più",
    options: [{'modalitaPartecipazione':'badge'},{'modalitaPartecipazione':'postazione'},{'modalitaPartecipazione':'invio materiale'}],
    onChange: function(value) {
    	if (value.length){
    		//$('#modalitaPartecipazione').siblings('div.scegliere').attr('style', 'color: #0075BF !important');
    		$('#modalitaPartecipazione').siblings('div.selectize-control.form-control.Input-text').attr('style', 'border: 1px solid #0075BF !important');
    		$('#modalitaPartecipazione').siblings('#modalitaPartecipazione-error').hide();
    	} else {
    		//$('#modalitaPartecipazione').siblings('div.scegliere').attr('style', 'color: #c10512 !important');
    		$('#modalitaPartecipazione').siblings('div.selectize-control.form-control.Input-text').attr('style', 'border: 1px solid #c10512 !important');
    		$('#modalitaPartecipazione').siblings('#modalitaPartecipazione-error').show();
    	}
    },
    render: {
        option: function(item, escape) {
        	return '<div class="option">'+escape(item.modalitaPartecipazione)+'</div>';
        }
    }
});


$('[name="dataDaAccreditamento"]').datepicker({ format: 'dd/mm/yyyy', autoclose: true,  constrainInput: false, language: "it"/*, startDate: new Date()*/ });

$('[name="dataAAccreditamento"]').datepicker({ format: 'dd/mm/yyyy', autoclose: true,  constrainInput: false, language: "it"/*, startDate: new Date()*/ });

$('[name="dataDaAccreditamento"]').datepicker({format: 'dd/mm/yyyy', autoclose: true,  constrainInput: false, language: 'it'}).on('changeDate', function(selected) {
	var minDate = new Date(selected.date.valueOf());
	if (minDate < new Date()) minDate = new Date();
	$('[name="dataAAccreditamento"]').datepicker('setStartDate', minDate);
});

$('[name="dataAAccreditamento"]').datepicker({format: 'dd/mm/yyyy', language: 'it', autoclose: true,  constrainInput: false, startDate: new Date()}).on('changeDate', function(selected) {
	var minDate = new Date(selected.date.valueOf());
	$('[name="dataDaAccreditamento"]').datepicker('setEndDate', minDate);
});


function popupSalva(isFinal){
	alertify.confirm("<i class=\"icon-errore\" style=\"font-size:64px; color:#a66300;\"></i><br><br>"+window.mexJS['js.button.sicuro']).set({
		title:window.mexJS['js.tipologia.attenzione']}).set('labels', {ok:'Si', cancel:'No'}).set('onok', function(){
			salvaScheda(isFinal);
		}).set('oncancel', function(){
			return;
		});
}

function salvaScheda(statoPubblicazione){
	$('#overlay').show();

	if(statoPubblicazione == "BOZZA" && $('#salvaB2BDMS').is(":visible")) {
		if($('#pubblTab .nav-item.active').next().is(":hidden") || $('#pubblTabContent .nav-item.active').next().length == 0) {
			window.tabsave2 = $('#pubblTab .nav-item.active').attr('id');
		} else {
			window.tabsave2 = $('#pubblTab .nav-item.active').next().attr('id');
		}
	} else {
		var formValid3 = $("#formSchedaB2BDMS").validate().form();
		if (!formValid3 ){
			$('#overlay').hide();
			return;
		}
	}
	
	enableForm();
	var metadata = $('#formSchedaB2BDMS').serializeFormJSON();	
	
	$.ajax({
        type: "POST",
        url: context+"/saveB2BDMSScheda/" + $("#eventoId").text() + "?noteAggiuntive=" + encodeURIComponent(' ') + "&statoPubblicazione=" + statoPubblicazione,
        data: JSON.stringify(metadata),
		contentType: 'application/json',
        success: function(response) {
        	$.ajax({
                type: "GET",
                url: context+"/b2bdmsScheda/" + $("#eventoId").text(),
                success: function(response2) {
                	document.getElementById("divSchedaAccessoria").style.display = 'none';
                	$("#divSchedaAccessoria").empty();
                	$("#divSchedaAccessoria").html(response2);
                    document.getElementById("divSchedaAccessoria").style.display = 'block';
                    var disableInput = caricaContenutoScheda();
                	if(disableInput){
                		disableForm();
                	}
                    if (statoPubblicazione == "PUBBLICATO"){
                    	alertify.alert(window.mexJS["js.esito.operazione"], "<i class=\"icon-successo\" style=\"font-size:64px; color:#58CB7D;\"></i><br/><br/><br/>"+window.mexJS["js.label.pubblicazione.success"]).set('label', 'Chiudi'); 
                    }
                    if(window.tabsave2 != null && window.tabsave2 != ""){
                		$('#'+window.tabsave2+'-tab').click();
                	}
                }
            });
        }
	});
}

function riportaUltimaPubblicazione() {
	alertify.confirm("<i class=\"icon-errore\" style=\"font-size:64px; color:#a66300;\"></i><br><br>"+window.mexJS['js.button.sicuro']).set({
		title:window.mexJS['js.tipologia.attenzione']}).set('labels', {ok:'Si', cancel:'No'}).set('onok', function(){
			$('#overlay').show();
			enableForm();
			var metadata = $('#formSchedaB2BDMS').serializeFormJSON();
			$.ajax({
		        type: "PUT",
		        url: context+"/recoverVipScheda/" + $("#eventoId").text(),
		        data: JSON.stringify(metadata),
				contentType: 'application/json',
		        success: function(response) {
		        	$.ajax({
		                type: "GET",
		                url: context+"/b2bdmsScheda/" + $("#eventoId").text(),
		                success: function(response2) {
		                	document.getElementById("divSchedaAccessoria").style.display = 'none';
		                	$("#divSchedaAccessoria").empty();
		                	$("#divSchedaAccessoria").html(response2);
		                    document.getElementById("divSchedaAccessoria").style.display = 'block';
		                    var disableInput = caricaContenutoScheda();
		                	if(disableInput){
		                		disableForm();
		                	}
		                }
		            });
		        }
			});
		}).set('oncancel', function(){
			return;
		});
}

function revocaUltimaPubblicazione() {
	alertify.confirm("<i class=\"icon-errore\" style=\"font-size:64px; color:#a66300;\"></i><br><br>"+window.mexJS['js.button.sicuro']).set({
		title:window.mexJS['js.tipologia.attenzione']}).set('labels', {ok:'Si', cancel:'No'}).set('onok', function(){
			$('#overlay').show();
			enableForm();
			var metadata = $('#formSchedaB2BDMS').serializeFormJSON();
			$.ajax({
		        type: "DELETE",
		        url: context+"/deleteB2BDMSScheda/" + $("#eventoId").text(),
		        data: JSON.stringify(metadata),
				contentType: 'application/json',
		        success: function(response) {
		        	$.ajax({
		                type: "GET",
		                url: context+"/b2bdmsScheda/" + $("#eventoId").text(),
		                success: function(response2) {
		                	document.getElementById("divSchedaAccessoria").style.display = 'none';
		                	$("#divSchedaAccessoria").empty();
		                	$("#divSchedaAccessoria").html(response2);
		                    document.getElementById("divSchedaAccessoria").style.display = 'block';
		                    var disableInput = caricaContenutoScheda();
		                	if(disableInput){
		                		disableForm();
		                	}
		                }
		            });
		        }
			});
		}).set('oncancel', function(){
			return;
		});
}

function caricaContenutoScheda(){
	var disableInput = false;
	$.ajax({
        type: "GET",
        async: false, 
        url: context+"/getB2BDMSScheda/" + $("#eventoId").text(),
        dataType : "json",
        success: function(response) {
        //	$('#statoPubblicazione').text("Stato corrente scheda di pubblicazione: " + response.statoPubblicazione);
        	$('#statoPubblicazione').text(response.statoPubblicazione);
        	if (response.statoPubblicazione == 'BOZZA') {
        		if(!window.flagPrm["pubblicazioneB2BDMS"]){
            		$('#pubblicaB2BDMS').attr('hidden', true);
            	} else {
            		$('#compilaB2BDMS').attr('hidden', true);
            	}
        		if(!response.fgPubblicato){
        			$('#riportaB2BDMS').attr('hidden', true);
        		}
        		$('#bozzaB2BDMS').attr('hidden', true);
        		$('#creaBozzaB2BDMS').attr('hidden', true);
        		$('#pubblicatoPubblicazione').text("Non disponibile");
        		$('#autorePubblicazione').text("Non disponibile");
        	} else if(response.statoPubblicazione == 'PUBBLICATO'){
        		if(!window.flagPrm["pubblicazioneB2BDMS"]){
        			$('#bozzaB2BDMS').attr('hidden', true);
        		}
        		$('#salvaB2BDMS').attr('hidden', true);
        		$('#pubblicaB2BDMS').attr('hidden', true);
        		$('#compilaB2BDMS').attr('hidden', true);
        		$('#riportaB2BDMS').attr('hidden', true);
        		$('#creaBozzaB2BDMS').attr('hidden', true);        		
        		disableInput =true;        		
        		$('#idLablePubblicazione').removeAttr('hidden');
        		var data = response.dataPubblicazione.substr(0,10);
        		data = convertDataEngToIta(data) + " alle ore" +  response.dataPubblicazione.substr(10);        		
        		$('#idLablePubblicazione').text('Ultima pubblicazione effettuata in data ' + data + ' a cura di ' + response.nomeOwner + " " + response.cognomeOwner);
          		$('#pubblicatoPubblicazione').text(data);
        		$('#autorePubblicazione').text(response.nomeOwner + " " + response.cognomeOwner);

        	}
        	if (response.pubblicazioneId) {
        		$('#idPubblicazione').val(response.pubblicazioneId);
        		$('#statoPubblicazione').removeAttr('hidden');
        		$('[data-name="aggiungId"]').attr('id','labelEvent');
        	}
        	if (!response.fgPubblicato) {
        		$('#revocaB2BDMS').attr('hidden', true);
        	}
        	$('#formSchedaB2BDMS').deserializeJSONForm(response.genericMetadata,true);
        }
	});
	return disableInput;
}

function fileDocumentiFeed(inputName, counter){
	$('#overlay').show();
	$('[data-name="multilinguaDivD"]').removeAttr('hidden');
	var idEvento = $('#eventoId').text();
	var idPubblicazione = $('#idPubblicazione').val();
	if(!idEvento){
		return;
	}
	if(!idPubblicazione){
		return;
	}
	var file = $('[name="'+ inputName +'"]')[0].files[0];
	var filename = file.name;
	if (file.name.lastIndexOf("\\")>0){
		filename = file.name.substr(file.name.lastIndexOf("\\")+1);
	}
	var formData = new FormData();
	formData.append("documento", file, filename);
    $.ajax({
        url : context+'/submitFile/documento/' + idEvento+ '?idPubblicazione=' + idPubblicazione + '&avoidTypeCheck=true',
        data : formData,
        type : 'POST',
        processData: false,
        contentType: false,
        success : function(data){
        	var json = JSON.parse(data);
        	$('[name="documentoSetAggiunto[' + counter + '].documentoId"]').val(json.allegatoId);
        	$('[name="documentoSetAggiunto[' + counter + '].estensione"]').val(json.estensione);
        	$('[name="documentoSetAggiunto[' + counter + '].dimensione"]').val(json.dimensione);
        	$('[name="documentoSetAggiunto[' + counter + '].nomeOriginale"]').val(json.nomeOriginale);
        	$('[name="documentoSetAggiuntoLoad"]').val("");
        	$('#overlay').hide();
        },
    	error: function(error){
    		if (error.status == 400){
    			alertify.alert(window.mexJS['js.error.errore'], "<i class=\"icon-errore\" style=\"font-size:64px; color:#a66300;\"></i><br/><br/><br/>"+window.mexJS['js.error.estensione']).set('label', 'Chiudi'); 
    		} else if (error.status == 406) {
    			alertify.alert(window.mexJS['js.error.errore'], "<i class=\"icon-errore\" style=\"font-size:64px; color:#a66300;\"></i><br/><br/><br/>"+window.mexJS['js.error.dimensioni']).set('label', 'Chiudi'); 
    		} else if (error.status == 413) {
    			alertify.alert(window.mexJS['js.error.errore'], "<i class=\"icon-errore\" style=\"font-size:64px; color:#a66300;\"></i><br/><br/><br/>"+window.mexJS['js.error.peso']).set('label', 'Chiudi'); 
    		}
    		else if (error.status == 415) {
    			alertify.alert(window.mexJS['js.error.errore'], "<img src=\""+context+"/assets/images/ERRORE.svg\"><br/><br/><br/>"+window.mexJS['js.error.file.valido']).set('label', 'Chiudi'); 
    		}
    		
    		$('[data-deletebutton="documentoSetAggiunto[' + counter + ']"]').click();
    		$('[name="documentoSetAggiuntoLoad"]').val("");
    		$('#overlay').hide();
    	}
    });
}

function initDownloadDocAgg(inputName, event){
	$('[data-name="'+ inputName +'"]').click(function (event) {
        event.preventDefault();
    	var counter = inputName.substr(inputName.indexOf("[") + 1);
    	counter = counter.substr(0, counter.indexOf("]"));
		var documentoId = $('[name="documentoSetAggiunto[' + counter + '].documentoId"]').val();
    	var estensione = $('[name="documentoSetAggiunto[' + counter + '].estensione"]').val();
    	var eventoId = $('#eventoId').text();
    	var hrefVal = $('[data-name="'+ inputName +'"]').attr("href") + "/getFile/" + eventoId + "/documento/" + documentoId + "." + estensione+ '?idPubblicazione=' + $('#idPubblicazione').val();
    	var link = document.createElement('a');
    //	console.log(hrefVal);
    	link.href = hrefVal;
    	link.download = documentoId+'.'+estensione;
    	document.body.appendChild(link);
    	link.click();
    	document.body.removeChild(link);
	});
}

function disableForm(){
	$("#formSchedaB2BDMS :input").prop('disabled', true);
	for (i=0; i < $("#formSchedaB2BDMS .selectized").length; i++) {
		$("#formSchedaB2BDMS .selectized").eq(i).selectize()[0].selectize.disable();
	}
}

function enableForm(){
	$("#formSchedaB2BDMS :input").prop('disabled', false);
	for (i=0; i < $("#formSchedaB2BDMS .selectized").length; i++) {
		$("#formSchedaB2BDMS .selectized").eq(i).selectize()[0].selectize.enable();
	}
}

function inizializzaValidatore() {
	$('#formSchedaB2BDMS').validate({
		ignore: [],
		onkeydown: true,
		errorPlacement: function(error, element) { error.appendTo(element.parent('div')); },
		invalidHandler: function() {
			setTimeout(function() {
				$('a.rch').removeClass('rch');
				//$('.selectized.error').siblings('div.scegliere').attr('style', 'color: #c10512 !important');
				$('.selectized.error').siblings('div.selectize-control.form-control.Input-text').attr('style', 'border: 1px solid #c10512 !important');
				var validatePane = $('input.error, textarea.error, select.error').parents('.tab-pane').each(function() {
					var id = $(this).attr('id');
					if(id) {
						var ch = $('[href^="#' + id + '"]').text().trim();
						if (id != "itbox" && id != "immaginibox" && id != "documentibox" && id != "videobox"){
							$('#labelValid').removeAttr('hidden');
							$('#labelValidazioni').removeAttr('hidden');
							$('#labelValidazioni').append("<br>"+ch);
						}
						$('[href^="#' + id + '"]').addClass('rch');
					} else {
						//TODO
					}
				});
			});
		}
	});	
	$('#prodotto').rules("add", { required: true, normalizer: function(value) { return $.trim(value); }});
	$('#segmento').rules("add", { required: true, normalizer: function(value) { return $.trim(value); }});
	$('#sottotipologieSet').rules("add", { required: true, normalizer: function(value) { return $.trim(value); }});
	$('#modalitaPartecipazione').rules("add", { required: true, normalizer: function(value) { return $.trim(value); }});
	$("#nomePartecipante").rules("add", { required: true, normalizer: function(value) { return $.trim(value); }});
	$("#cognomePartecipante").rules("add", { required: true, normalizer: function(value) { return $.trim(value); }});
	$("#telefonoPartecipante").rules("add", { required: true, normalizer: function(value) { return $.trim(value); }});
	$("#mailPartecipante").rules("add", { required: true, normalizer: function(value) { return $.trim(value); }});
}

$(document).ready(function() {	
	if (window.enterInRed){
		if(caricaContenutoScheda()){
			disableForm();
		}
		window.enterInRed = false;
	}
	$('#formSchedaB2BDMS :input').not('[type="checkbox"], [type="radio"]').mouseenter(function(){
		$(this).attr('title', $(this).val());
	});
	inizializzaValidatore();
	$('#overlay').hide();
});

$(document).ready(function(){
	$('.inputicondata2').append("<div class=\"calendardatep\"><img src=\""+context+"/assets/images/CALENDARIO_full.svg\" id=\"opencalendar\" aria-hidden=\"true\"></div>");
	$('.inputicondata').append("<div class=\"calendardatep\"><img src=\""+context+"/assets/images/CALENDARIO_full.svg\" id=\"opencalendar\" aria-hidden=\"true\"></div>");
	});
