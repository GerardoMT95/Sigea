$('#formSchedaVIP').initializeBindingOnForm();

var disableInputScheda = false; // Variabile usata per la disabilitazione degli input delle schede
//GESTIONE LOCK EVENTO 
(function() {
	poll = function() {
		var eventoid = $("#eventoId").text();
		if (eventoid) {
		//console.log("Effettuando chiamata evento... "+ new Date());
			$.ajax({
				type: 'POST',
				url: context+'/lockEvento?idEvento='+eventoid,
				success: function(response) {
					//console.log('Lock rinnovato su evento: '+eventoid+" "+new Date());
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

$('.popup-with-form').magnificPopup({
	type: 'ajax',
	closeOnBgClick: false
});

function replacehtmlfull() {		
	$("#VIPattDGContent").find('div.boxInput > textarea').each(function( index ) {		
		var id = $(this).attr('id');
		var oldx = $(this).val();		
		if(oldx){
			oldx = oldx.replace(/‘/g,"'");
			oldx = oldx.replace(/’/g,"'");
			oldx = oldx.replace(/“/g,"\"");
			oldx = oldx.replace(/”/g,"\""); 			
			oldx = oldx.replace(/–/g,"-");
			oldx = oldx.replace(/—/g,"-"); 	
			oldx = oldx.replace(/\\r\\n/g, "<br />");                		
			oldx = cleanString(oldx);
			oldx = ConvertCharacter(oldx);
			
			$('#'+id).summernote('code', oldx);		
			$('#'+id).closest('div').find('textarea.note-codable').val(oldx);
			$('#'+id).closest('div').find('div.note-editable').html(oldx);
		}				
	});	
	setTimeout(function(){ 		
		$(".note-editable").each(function( index ) {
			$(this).keyup();
		});	
	}, 500);
	return true;
}

function attivitaToEventoOK(){
	$('[id*="Evento"]').css("display", "");
	$("[value='evento']").prop('checked', true);
	$("[value='attivita']").prop('checked', false);
	$('#prodotto')[0].selectize.clear();
	$('#tipologiaVIP')[0].selectize.clear();
	$('#mezzo')[0].selectize.clear();
	//$("[name='riferimento']").val('');
	//$("[name='infopointId']").val('');
	//$('#infopointVIP')[0].selectize.clear();
	$('#valore')[0].selectize.clear();
	$('[id*="Attivita"]').css("display", "none");
	
	
	$('#boxDatigenerali').find('textarea[name^="descrizioneMulti."]').each(function( index ) {
		$(this).summernote('destroy');  
		$(this).attr("data-maxlengthtml","8000");		  
	});
	
	
	setTimeout(function(){ 
	enableSummerNoteDesc();		
		$(".note-editable").each(function( index ) {
			$(this).keyup();
		});	
	}, 500);
	
	
}

function eventoToAttivitaOK(){
	$('[id*="Attivita"]').css("display", "");
	$('#vidVIP').css("display", "");
	$('#docVIP').css("display", "");
	$("[value='evento']").prop('checked', false);
	$("[value='attivita']").prop('checked', true);
	$('#prodotto')[0].selectize.clear();
	//$('#tipologiaEventoVIP')[0].selectize.clear();
	$('#ranking')[0].selectize.clear();
	$('#attribuzioniEvento input:checkbox').prop('checked', false);
	$('[id*="Evento"]').css("display", "none");
	
	$('#boxDatigenerali').find('textarea[name^="descrizioneMulti."]').each(function( index ) {
		$(this).summernote('destroy'); 
		$(this).attr("data-maxlengthtml","4000");		  
	});
	
	
	setTimeout(function(){ 	
		enableSummerNoteDesc();
		$(".note-editable").each(function( index ) {
			$(this).keyup();
		});	
	}, 500);
	
}

function attivitaToEventoCANC(){
	$("[value='evento']").prop('checked', false);
	$("[value='attivita']").prop('checked', true);
}

function eventoToAttivitaCANC(){
	$("[value='evento']").prop('checked', true);
	$("[value='attivita']").prop('checked', false);
}

function clickRadioTipologia(nomeRadio, evento){
	$('[name="'+ nomeRadio +'"]').on( evento, function(e) {
		if($(this).prop("checked")){
			if($(this).val() == 'evento'){
					attivitaToEventoOK();
			}else if ($(this).val() == 'attivita') {
					eventoToAttivitaOK();
			}
		}
	});
}

globalSelectizedMIBACT = $('#tipologieMIBACT').selectize({
	valueField: 'idMIBACT',
    labelField: 'tipologiaMIBACT',
    searchField: 'tipologiaMIBACT',
    plugins: ['remove_button'],
    maxItems: '3',
    placeholder: window.mexJS['js.placeholder.mibact'],
    options: window.tipologieMIBACT,
    render: {
        option: function(item, escape) {
        	return '<div class="option">'+escape(item.tipologiaMIBACT)+'</div>';
        }
    },
    onItemAdd: function(value) {
    	globalSelectizedMIBACT[0].selectize.close();
    },
    onItemRemove: function () {
    	globalSelectizedMIBACT[0].selectize.close();
    }
});

$('#ranking').selectize({
	valueField: 'ranking',
    labelField: 'valore',
    searchField: 'ranking',
    placeholder: window.mexJS['js.placeholder.ranking'],
    options: [{'ranking':'1', 'valore':'1. Non fruibile'},
    	{'ranking':'2', 'valore':'2. Non rilevante'},
    	{'ranking':'3', 'valore':'3. Poco interessante'},
    	{'ranking':'4', 'valore':'4. Possibile attrattività'},
    	{'ranking':'5','valore':'5. Apprezzabile'},
    	{'ranking':'6','valore':'6. Degno di nota'},
    	{'ranking':'7','valore':'7. Consigliato'},
    	{'ranking':'8','valore':'8. Da visitare'},
    	{'ranking':'9','valore':'9. Da non perdere'},
    	{'ranking':'10','valore':'10. SuperUser'}],
    render: {
        option: function(item, escape) {
        	return '<div class="option">'+escape(item.valore)+'</div>';
        }
    }
});

$('#valore').selectize({
	valueField: 'valore',
    labelField: 'valorelabel',
    searchField: 'valore',
    placeholder: window.mexJS['js.placeholder.valori'],
    //options: window.valoriAttrattivita,
    options: [{'valore':'Non fruibile','valorelabel':'1. Non fruibile'},
    	{'valore':'Non rilevante','valorelabel':'2. Non rilevante'},
    	{'valore':'Poco interessante','valorelabel':'3. Poco interessante'},
    	{'valore':'Possibile attrattività','valorelabel':'4. Possibile attrattività'},
    	{'valore':'Apprezzabile','valorelabel':'5. Apprezzabile'},
    	{'valore':'Degno di nota','valorelabel':'6. Degno di nota'},
    	{'valore':'Consigliato','valorelabel':'7. Consigliato'},
    	{'valore':'Da visitare','valorelabel':'8. Da visitare'},
    	{'valore':'Da non perdere','valorelabel':'9. Da non perdere'},
    	{'valore':'SuperUser','valorelabel':'10. SuperUser'}],    
    render: {
        option: function(item, escape) {
        	return '<div class="option">'+escape(item.valorelabel)+'</div>';
        }
    }
});

$('#mezzo').selectize({
	valueField: 'mezzo',
    labelField: 'mezzo',
    searchField: 'mezzo',
    placeholder: window.mexJS['js.placeholder.mezzi'],
    options: window.mezzi,
    render: {
        option: function(item, escape) {
        	return '<div class="option">'+escape(item.mezzo)+'</div>';
        }
    }
});

globalSelectizedProdotto = $('#prodotto').selectize({
	valueField: 'prodotto',
    labelField: 'prodotto',
    searchField: 'prodotto',
    plugins: ['remove_button'],
    placeholder: window.mexJS['js.placeholder.prodotti'],
    options: window.prodotti,
    render: {
        option: function(item, escape) {
        	return '<div class="option">'+escape(item.prodotto)+'</div>';
        }
    },
    onItemAdd: function(value) {
    	globalSelectizedProdotto[0].selectize.close();
    },
    onItemRemove: function () {
    	globalSelectizedProdotto[0].selectize.close();
    }

});

$('#tipologiaVIP').selectize({
	valueField: 'tipologia',
    labelField: 'tipologia',
    searchField: 'tipologia',
    placeholder: window.mexJS['js.placeholder.tipologiascheda'],
    options: window.tipologieAttivita,
    render: {
        option: function(item, escape) {
        	return '<div class="option">'+escape(item.tipologia)+'</div>';
        }
    }
});

function popupSalva(isFinal){
	alertify.confirm("<i class=\"icon-errore\" style=\"font-size:64px; color:#a66300;\"></i><br><br>"+window.mexJS['js.button.sicuro']).set({
		title:window.mexJS['js.tipologia.attenzione']}).set('labels', {ok:'Si', cancel:'No'}).set('onok', function(){
			salvaScheda(isFinal);
		}).set('oncancel', function(){
			return;
		});
}

$(document).on( "change", "#prodotto", function() {		
	if( $(this).val() != ''){
		$("#prodotto").next('div').removeClass('error');
		$("#prodotto-error").hide();
		$("#prodottoV-tab").removeClass('rch');
	      if($('ul#pubblTab.nav.nav-tabs>li').find('a.nav-link.rch').length == 0){
	        	$('#labelValid').hide();   
	        } 
	}	
});

$(document).on( "change", "#tipologieMIBACT", function() {		
	if( $(this).val() != ''){
		$("#tipologieMIBACT").next('div').removeClass('error');
		$("#tipologieMIBACT-error").hide();
		//$("#datigeneraliV-tab").removeClass('rch');
	      if($('ul#pubblTab.nav.nav-tabs>li').find('a.nav-link.rch').length == 0){
	        	$('#labelValid').hide();   
	        } 
	}	
});



$(document).on( "change", "#itattVIP_titolo", function() {		
	if( $(this).val() != ''){
		$("#itattVIP_titolo").next('div').removeClass('error');
		$("#itattVIP_titolo-error").hide();
		//$("#datigeneraliV-tab").removeClass('rch');
	      if($('ul#pubblTab.nav.nav-tabs>li').find('a.nav-link.rch').length == 0){
	        	$('#labelValid').hide();   
	        } 
	}	
});

$(document).on( "change", "#itattVIP_abstract", function() {		
	if( $(this).val() != ''){
		$("#itattVIP_abstract").next('div').removeClass('error');
		$("#itattVIP_abstract-error").hide();
		//$("#datigeneraliV-tab").removeClass('rch');
	      if($('ul#pubblTab.nav.nav-tabs>li').find('a.nav-link.rch').length == 0){
	        	$('#labelValid').hide();   
	        } 
	}	
});

$(document).on( "change", "#itattVIP_descrizione", function() {		
	if( $(this).val() != ''){
		$("#itattVIP_descrizione").next('div').removeClass('error');
		$("#itattVIP_descrizione-error").hide();
		//$("#datigeneraliV-tab").removeClass('rch');
	      if($('ul#pubblTab.nav.nav-tabs>li').find('a.nav-link.rch').length == 0){
	        	$('#labelValid').hide();   
	        } 
	}	
});


$(document).on( "change", "#tipologieMIBACT, #itattVIP_titolo, #itattVIP_abstract, #itattVIP_descrizione", function() {		
	if($("#tipologieMIBACT").val() != '' && $("#itattVIP_titolo").val() != '' && $("#itattVIP_abstract").val() != '' && $("#itattVIP_descrizione").val() != ''){	
		$("#datigeneraliV-tab").removeClass('rch');	  
	}	
});



/*
$(document).on( "change", 'input[name$=".titoloMulti.IT"], input[name$=".link"]', function() {	
	if( $(this).val() != ''){
		$(this).removeClass('error');
		$(this).closest('div').find('.error').hide();		
	}else{
		$(this).addClass('error');
		$(this).closest('div').find('.error').show();
	}	
});
*/
//IMMAGINI
$(document).on( "change", 'input[name^="immagineSet"], input[name^="immagineSetAggiunto"]', function() {	
	if( $(this).val() != ''){
	//	$(this).removeClass('error');
//		$(this).next('label').removeClass('active').hide();		
	//	$("#immaginiVIP-tab").removeClass('rch');	
		$("#videoVIP-tab").removeClass('rch');	
//		$("#labelValid").hide();			
		if(	$("#boxMedia label.error.active").length  == 0){			
			$("#mediaV-tab").removeClass('rch');	
		}
		
	}else{
	//	$(this).addClass('error');
	//	$(this).next('label').addClass('active').show();	
	}	
});

$(document).on( "change", 'input[name$=".ordine"], input[name$=".ordineNumerico"]', function() {	

controlloVIP();

});


//ALTRI ALLEGATI
$(document).on( "change", 'input[name^="documentoSet"], input[name^="documentoSetAggiunto"]', function() {	
	if( $(this).val() != ''){
	//	$(this).removeClass('error');
	//	$(this).next('label').removeClass('active').hide();			
	//	$("#documentiVIP-tab").removeClass('rch');	
//		$("#mediaV-tab").removeClass('rch');	
//		$("#labelValid").hide();	
		
		if(	$("#boxMedia label.error.active").length  == 0){
			
			$("#mediaV-tab").removeClass('rch');	
			
		}
		
	}else{
	//	$(this).addClass('error');
//		$(this).next('label').addClass('active').show();	
	}	
});

//LINK ESTERNI
$(document).on( "change", 'input[name^="linkSet"], input[name^="linkSetAggiunto"]', function() {	
	if( $(this).val() != ''){
	//	$(this).removeClass('error');
	//	$(this).next('label').removeClass('active').hide();	
		controlloVIP();
//		$("#videoVIP-tab").removeClass('rch');	
//		
//	if(	$("#boxMedia label.error.active").length  == 0){
//		$("#mediaV-tab").removeClass('rch');	
//	}
//		
//		
////		$("#mediaV-tab").removeClass('rch');	
////		$("#labelValid").hide();	
//	}else{
//	//	$(this).addClass('error');
//	//	$(this).next('label').addClass('active').show();	
	}	
});

//$.validator.setDefaults({ 
//    ignore: [],
//    // any other default options and/or rules
//});

function salvaScheda(statoPubblicazione){	
	
    gestValidatoreVip(statoPubblicazione); 
        	
	replacehtmlfull();

	$(".note-editable").each(function( index ) {
		$(this).keyup();
	});	

	validationSummerNote();
	
    if($('ul#pubblTab.nav.nav-tabs>li').find('a.nav-link.rch').length == 0){
    	$('#labelValid').hide(); 
    	var validform2 = true;
    }else{
		$("#labelValid").show();
		var validform2 = false;
    }       
    
    var validform3 = true;
    var validform = true;

    
	if(statoPubblicazione == 'PUBBLICATO' || statoPubblicazione == 'REDATTO'){
		
		
		// validazione campo Prodotti Tab CATEGORIA
		var listatesmp = $("#prodotto").val();
		if( listatesmp.length == 0 ){
			$("#prodotto").next('div').addClass('error');
			$("#prodotto-error").show();
			$("#prodottoV-tab").addClass('rch');		
			validform = false;			
		}else{
			$("#prodotto").next('div').removeClass('error');
			$("#prodotto-error").hide();
			$("#prodottoV-tab").removeClass('rch');
		      if($('ul#pubblTab.nav.nav-tabs>li').find('a.nav-link.rch').length == 0){
		        	$('#labelValid').hide();   
		        } 			
		}		
		
		// validazione campo tipologieMIBACT Tab DATI GENERALI
		var listatesmp1 = $("#tipologieMIBACT").val();
		if( listatesmp1 == "" ){
			$("#tipologieMIBACT").next('div').addClass('error');
			$("#tipologieMIBACT-error").show();
			$("#datigeneraliV-tab").addClass('rch');		
			validform3 = false;			
		}else{
			$("#tipologieMIBACT").next('div').removeClass('error');
			$("#tipologieMIBACT-error").hide();			
		}	
	
		// validazione campo itattVIP_titolo Tab DATI GENERALI
		var listatesmp2 = $("#itattVIP_titolo").val();
		if( listatesmp2 == "" ){
			$("#itattVIP_titolo").next('div').addClass('errorV');
			$("#itattVIP_titolo-error").show();
			$("#datigeneraliV-tab").addClass('rch');		
			validform3 = false;			
		}else{
			$("#itattVIP_titolo").next('div').removeClass('errorV');
			$("#itattVIP_titolo-error").hide();		
		}
		
		// validazione campo itattVIP_abstract Tab DATI GENERALI
		var listatesmp3 = $("#itattVIP_abstract").val();
		if( listatesmp3 == "" ){
			$("#itattVIP_abstract").next('div').addClass('errorV');
			$("#itattVIP_abstract-error").show();
			$("#datigeneraliV-tab").addClass('rch');		
			validform3 = false;			
		}else{
			$("#itattVIP_abstract").next('div').removeClass('errorV');
			$("#itattVIP_abstract-error").hide();			
		}
		
		// validazione campo itattVIP_descrizione Tab DATI GENERALI
		var listatesmp4 = $("#itattVIP_descrizione").val();
		if( listatesmp4 == "" ){
			$("#itattVIP_descrizione").next('div').addClass('errorV');
			$("#itattVIP_descrizione-error").show();
			$("#datigeneraliV-tab").addClass('rch');		
			validform3 = false;			
		}else{
			$("#itattVIP_descrizione").next('div').removeClass('errorV');
			$("#itattVIP_descrizione-error").hide();				
		}

		if(validform3 == true){
		$("#datigeneraliV-tab").removeClass('rch');
		    if($('ul#pubblTab.nav.nav-tabs>li').find('a.nav-link.rch').length == 0){
		      	$('#labelValid').hide();   
		      } 	
		}
		
		$('#videoAggVIPbox').find('input[name$="].titoloMulti.IT"]').each(function() {
			if( $(this).val() != ''){
				$(this).removeClass('error');
				$(this).closest('div').find('.error').removeClass('active').hide();		
				//$("#videoVIP-tab").removeClass('rch');	
				//$("#mediaV-tab").removeClass('rch');	
			      if($('ul#pubblTab.nav.nav-tabs>li').find('a.nav-link.rch').length == 0){
			        	$('#labelValid').hide();   
			        } 	
			}else{
				//$("#mediaV-tab").addClass('rch');
				//$("#videoVIP-tab").addClass('rch');					
				$(this).addClass('error');
				$(this).closest('div').find('.error').addClass('active').show();
				validform = false;
			}	
		});
		
		$('#videoAggVIPbox').find('input[name$="].link"]').each(function() {
			if( $(this).val() != ''){
				$(this).removeClass('error');
				$(this).closest('div').find('.error').removeClass('active').hide();			
				//$("#videoVIP-tab").removeClass('rch');	
			//	$("#mediaV-tab").removeClass('rch');	
			      if($('ul#pubblTab.nav.nav-tabs>li').find('a.nav-link.rch').length == 0){
			        	$('#labelValid').hide();   
			        } 	
			}else{
				$("#mediaV-tab").addClass('rch');
				//$("#videoVIP-tab").addClass('rch');	
				$(this).addClass('error');
				$(this).closest('div').find('.error').addClass('active').show();
				validform = false;
			}	
		});
		
		
		if(validform == false || validform2 == false || validform3 == false){
			$("#labelValid").show();
			window.scrollTo(0,0);
			return;
		}else{
			$("#mediaV-tab").removeClass('rch');
			$("#videoVIP-tab").removeClass('rch');
			$("#prodottoV-tab").removeClass('rch');
			$("#datigeneraliV-tab").removeClass('rch');	
		      if($('ul#pubblTab.nav.nav-tabs>li').find('a.nav-link.rch').length == 0){
		        	$('#labelValid').hide();   
		        } 
		}	
		
	
	}
	
	
	
	

	if(statoPubblicazione == "BOZZA" && $('#salvaVIP').is(":visible")) {
		
		if(validform2 == false){
			$("#labelValid").show();
			$(".note-editable").each(function( index ) {
				$(this).keyup();
			});
			window.scrollTo(0,0);
			return;
		}else{
			$("#mediaV-tab").removeClass('rch');
			$("#videoVIP-tab").removeClass('rch');
			$("#prodottoV-tab").removeClass('rch');
			$("#datigeneraliV-tab").addClass('rch');	
		      if($('ul#pubblTab.nav.nav-tabs>li').find('a.nav-link.rch').length == 0){
		        	$('#labelValid').hide();   
		        } 
		}
			
		
		
		
	//		if($('#pubblTab .nav-item.active').next().is(":hidden") || $('#pubblTabContent .nav-item.active').next().length == 0) {
				window.tabsave2 = $('#pubblTab .nav-item.active').attr('id');
	//		} else {
	//			window.tabsave2 = $('#pubblTab .nav-item.active').next().attr('id');
	//		}
	} else {
		window.tabsave2 = "datigeneraliV";
	}
	

	
	enableForm();
	var metadata = $('#formSchedaVIP').serializeFormJSON();
	
	var arrayMIBACT = $('#tipologieMIBACT').selectize()[0].selectize.getValue();
	var mapOption = {};
	for(var i=0; i<arrayMIBACT.length; i++ ) {
		var option = $('#tipologieMIBACT').selectize()[0].selectize.options[arrayMIBACT[i]];
		mapOption[option.idMIBACT] = option.tipologiaMIBACT;
	}
	
	metadata['tipologieMIBACT'] = mapOption;
	$('#overlay').show();
	
	$.ajax({
        type: "POST",
        url: context+"/saveVipScheda/" + $("#eventoId").text() + "?noteAggiuntive=" + encodeURIComponent($('#notaValidazione').val()) + "&statoPubblicazione=" + statoPubblicazione,
        data: JSON.stringify(metadata),
		contentType: 'application/json',
        success: function(response) {       	
        	
        	$.ajax({
                type: "GET",
                url: context+"/vipScheda/" + $("#eventoId").text() + "?statoNucleo=" + $("#statoNucleo").text(),
                success: function(response2) {            
                	
                	document.getElementById("divSchedaAccessoria").style.display = 'none';
                	$("#divSchedaAccessoria").empty();
                	$("#divSchedaAccessoria").html(response2);
                    document.getElementById("divSchedaAccessoria").style.display = 'block';
                    var disableInput = caricaContenutoScheda();
                    
                    enableSummerNote();
                    
                	if(disableInput){
                		disableForm();
                	}                	 
                	 
            		$('#overlay').hide();
                    if (statoPubblicazione == "BOZZA"){
                    		opendmstoastsuccess('Operazione conclusa con successo!');  
                     	
                    }else if (statoPubblicazione == "REDATTO"){
                      	alertify.alert("Azione confermata", "<img src=\""+context+"/assets/images/SUCCESSO.svg\"><br/><br/><br/>I dati della scheda sono stati salvati e la richiesta &egrave; stata inviata con successo!").set('onok', function(){
                    		$('#overlay').show();
                    		location.reload();
                    		} ).set('label', 'Chiudi'); 
                    
                    }else if (statoPubblicazione == "PUBBLICATO"){
                      	alertify.alert("Azione confermata", "<img src=\""+context+"/assets/images/SUCCESSO.svg\"><br/><br/><br/>Evento pubblicato!").set('onok', function(){
                    		$('#overlay').show();
                    		location.reload();
                    		} ).set('label', 'Chiudi'); 
                      	
                      	
                    }else if (statoPubblicazione == "RIFIUTATO"){
                      	alertify.alert("Azione confermata", "<img src=\""+context+"/assets/images/SUCCESSO.svg\"><br/><br/><br/>Evento respinto!").set('onok', function(){
                    		$('#overlay').show();
                    		location.reload();
                    		} ).set('label', 'Chiudi'); 
 
                    }else{                   	
                    	
                    	alertify.alert("Successo", "<img src=\""+context+"/assets/images/SUCCESSO.svg\"><br/><br/><br/>La procedura &eacute; avvenuta con successo!").set('onok', function(){
                    		$('#overlay').show();
                    		location.reload();
                    		} ).set('label', 'Chiudi'); 
                    }
  
                    if(window.tabsave2 != null && window.tabsave2 != ""){
                		$('#'+window.tabsave2+'-tab').click();
                	}
                	$(".note-editable").each(function( index ) {
            			$(this).keyup();
            		});	
                },
                error: function(error){                	
                  	alertify.alert("Abbiamo riscontrato un problema", "<img src=\""+context+"/assets/images/ERRORE.svg\"><br/><br/><br/>Al momento non &egrave; possibile concludere l'operazione. Ci scusiamo e ti invitiamo a riprovare in un altro momento.").set('label', 'Chiudi'); 
                	$('#overlay').hide();            		
            	}
            });
        },
        error: function(error){        	
          	alertify.alert("Abbiamo riscontrato un problema", "<img src=\""+context+"/assets/images/ERRORE.svg\"><br/><br/><br/>Al momento non &egrave; possibile concludere l'operazione. Ci scusiamo e ti invitiamo a riprovare in un altro momento.").set('label', 'Chiudi'); 
        	$('#overlay').hide();		
		}
	});
		
	setTimeout(function(){ 	
		$(".note-editable").each(function( index ) {
			$(this).keyup();
		});
	}, 1000);

}

function riportaUltimaPubblicazione() {
	alertify.confirm("<i class=\"icon-errore\" style=\"font-size:64px; color:#a66300;\"></i><br><br>"+window.mexJS['js.button.sicuro']).set({
		title:window.mexJS['js.tipologia.attenzione']}).set('labels', {ok:'Si', cancel:'No'}).set('onok', function(){
						
					enableForm();
					var metadata = $('#formSchedaVIP').serializeFormJSON();
					$.ajax({
				        type: "PUT",
				        url: context+"/recoverVipScheda/" + $("#eventoId").text(),
				        data: JSON.stringify(metadata),
						contentType: 'application/json',
						beforeSend: function(){
							$('#overlay').show();
						},
						complete: function(){
							$('#overlay').hide();
						},
				        success: function(response) {
				        	/*$.ajax({
				                type: "GET",
				                url: context + "/vipScheda/" + $("#eventoId").text(),
				                success: function(response2) {
				                	document.getElementById("divSchedaAccessoria").style.display = 'none';
				                	$("#divSchedaAccessoria").empty();
				                	$("#divSchedaAccessoria").html(response2);
				                    document.getElementById("divSchedaAccessoria").style.display = 'block';
				                    var disableInput = caricaContenutoScheda();
				                	if(disableInput){
				                		disableForm();
				                	}
				            		enableSummerNote();		  */          		
				            		
			        		alertify.alert("Azione confermata", "<img src=\""+context+"/assets/images/SUCCESSO.svg\"><br/><br/><br/>Ultima pubblicazione ripristinata!").set('onok', function(){
			            		$('#overlay').show();
			            		location.reload();
			        		});
				              
				               
				           /*     },
				                error: function(error){
				                  	alertify.alert("Abbiamo riscontrato un problema", "<img src=\""+context+"/assets/images/ERRORE.svg\"><br/><br/><br/>Al momento non &egrave; possibile concludere l'operazione. Ci scusiamo e ti invitiamo a riprovare in un altro momento.").set('label', 'Chiudi'); 
				                		        		
				        		}
				            });*/
				        },
				        error: function(error){
		                  	alertify.alert("Abbiamo riscontrato un problema", "<img src=\""+context+"/assets/images/ERRORE.svg\"><br/><br/><br/>Al momento non &egrave; possibile concludere l'operazione. Ci scusiamo e ti invitiamo a riprovare in un altro momento.").set('label', 'Chiudi'); 
		                		        		
		        		}
					});	
		    
		}).set('oncancel', function(){
//			alertify.error(window.mexJS['js.nota.annulla']);
			return;
		});
	
}

function revocaUltimaPubblicazione() {
	alertify.confirm("<i class=\"icon-errore\" style=\"font-size:64px; color:#a66300;\"></i><br><br>"+window.mexJS['js.button.sicuro']).set({
		title:window.mexJS['js.tipologia.attenzione']}).set('labels', {ok:'Si', cancel:'No'}).set('onok', function(){
			
			enableForm();
			var metadata = $('#formSchedaVIP').serializeFormJSON();
			$.ajax({
		        type: "DELETE",
		        url: context + "/deleteVipScheda/" + $("#eventoId").text(),
		        data: JSON.stringify(metadata),
				contentType: 'application/json',
				beforeSend: function(){
					$('#overlay').show();
				},
				complete: function(){
					$('#overlay').hide();
				},
		        success: function(response) {
		        	/*$.ajax({
		                type: "GET",
		                url: context + "/vipScheda/" + $("#eventoId").text(),
		                success: function(response2) {
		                	document.getElementById("divSchedaAccessoria").style.display = 'none';
		                	$("#divSchedaAccessoria").empty();
		                	$("#divSchedaAccessoria").html(response2);
		                    document.getElementById("divSchedaAccessoria").style.display = 'block';
		                    var disableInput = caricaContenutoScheda();
		                	if(disableInput){
		                		disableForm();
		                	}
		            		enableSummerNote();
		            		*/
	        		alertify.alert("Azione confermata", "<img src=\""+context+"/assets/images/SUCCESSO.svg\"><br/><br/><br/>Adesso l'evento &egrave; offline per la tua redazione!").set('onok', function(){
	            		$('#overlay').show();
	            		location.reload();
	        		});
		            		
		            		
		               /* },
		                error: function(error){		                	
		                  	alertify.alert("Abbiamo riscontrato un problema", "<img src=\""+context+"/assets/images/ERRORE.svg\"><br/><br/><br/>Al momento non &egrave; possibile concludere l'operazione. Ci scusiamo e ti invitiamo a riprovare in un altro momento.").set('label', 'Chiudi'); 
		                	
		        		}
		            });*/
		        },
		        error: function(){
					alertify.alert("Abbiamo riscontrato un problema", "<img src=\""+context+"/assets/images/ERRORE.svg\"><br/><br/><br/>Al momento non &egrave; possibile concludere l'operazione. Ci scusiamo e ti invitiamo a riprovare in un altro momento.").set('label', 'Chiudi');
				}
			});
			
		}).set('oncancel', function(){
//			alertify.error(window.mexJS['js.nota.annulla']);
			return;
		});
}

function caricaContenutoScheda(){	

	var disableInput = false;
	$.ajax({
        type: "GET",
        async: false,
        url: context + "/getVipScheda/" + $("#eventoId").text(),
        dataType : "json",
        success: function(response) {
        	$('#statoPubblicazione').text(response.statoPubblicazione);        	
        	$('#ideventomodal').val(response.pubblicazioneId);
        	//$('#statoPubblicazione').text("Stato corrente scheda di pubblicazione: " + response.statoPubblicazione);
        	
        	// GESTIONE TIPOLOGIA NUCLEO
        	var tipoLogiaNucleo = response.eventoModel.tipo; 
           
            if(tipoLogiaNucleo == "EVENTO"){
				tipoLogiaNucleo = "SINGOLO";
			}
			
		
			
        	$('#tipologiaNucleo').text(tipoLogiaNucleo);
        	
        	// GESTIONE STATO PUBBLICAZIONE
        	var statoOnline = "Non disponibile";
        	if(response.fgPubblicato == true){
				statoOnline = "Online";
			} else if (response.fgPubblicato == false){
				statoOnline = "Offline";
			}
			$('#statoOnline').text(statoOnline);
          		
          	// GESTIONE DATE PUBBLICAZIONE
        	if(response.dataPubblicazione) {
        		var data = response.dataPubblicazione.substr(0,10);
        		data = convertDataEngToIta(data) + " alle ore" +  response.dataPubblicazione.substr(10);
        		$('#idLablePubblicazione').text('Ultima pubblicazione effettuata in data ' + data + ' a cura di ' + response.nomeOwner + " " + response.cognomeOwner);
        		$('#pubblicatoPubblicazione').text(data);
    		} else {
    			$('#pubblicatoPubblicazione').text("Non disponibile");	
    		}
			
    		if(response.dataPrimaPubblicazione){
        		var data1 = response.dataPrimaPubblicazione.substr(0,10);
        		data1 = convertDataEngToIta(data1) + " alle ore" +  response.dataPrimaPubblicazione.substr(10);
        		$('#primaPubbblicazione').text(data1);
    		} else {
    			$('#primaPubbblicazione').text("Non disponibile");
			}
			
			if(response.nomeOwner && response.cognomeOwner){
				$('#autorePubblicazione').text(response.nomeOwner + " " + response.cognomeOwner);
			}else{
				$('#autorePubblicazione').text("Non disponibile");
			}
			
			
        	//To do visualizzazione box bandi INIZIO
        	/*
        	var bandoId = "";
        	var progettoId = "";
        	var titoloBando = "";
        	var titoloProgetto = "";
        	var nomeOrganizzazione = "";	
        	
        	if(typeof response.eventoModel.bando !== 'undefined'){
        		if(typeof response.eventoModel.bando.bandoId !== 'undefined'){ bandoId = response.eventoModel.bando.bandoId; }        	
        		if(typeof response.eventoModel.bando.titoloBando !== 'undefined'){ titoloBando = response.eventoModel.bando.titoloBando; }
        	}
        	
        	if(typeof response.eventoModel.progetto !== 'undefined'){
        		if(typeof response.eventoModel.progetto.progettoId !== 'undefined'){ progettoId = response.eventoModel.progetto.progettoId; }        	
        		if(typeof response.eventoModel.progetto.titoloProgetto !== 'undefined'){ titoloProgetto = response.eventoModel.progetto.titoloProgetto; }
        		if(typeof response.eventoModel.progetto.progettoId !== 'undefined'){ nomeOrganizzazione = response.eventoModel.progetto.nomeOrganizzazione; }
        	}
        	
        	if(bandoId){
	        	$('#boxbando').find('input[name="bando.bandoId"]').val(bandoId).prop("disabled", false);
	        	
	        	if(titoloBando){
	        		$('#labelbando').show();
	        		$('#boxbando').find('input[name="bando.titoloBando"]').prop("disabled", false).val(titoloBando); $("#bandoutente").html(titoloBando);
	        	}else{
	        		$('#labelbando').hide();
	        	}
	        	
	        	if(titoloProgetto){
	        		$('#labelprogetto').show();
	        		$('#boxbando').find('input[name="progetto.progettoId"]').prop("disabled", false).val(progettoId);
	        		$('#boxbando').find('input[name="progetto.titoloProgetto"]').prop("disabled", false).val(titoloProgetto);  
	        		$("#progettoutente").html(titoloProgetto);
	        	}else{
	        		$('#labelprogetto').hide();
	        		$('#boxbando').find('input[name="progetto.progettoId"]').prop("disabled", true).val('');
	        		$('#boxbando').find('input[name="progetto.titoloProgetto"]').prop("disabled", true).val(''); 
	        	}		
	        	
	           	if(nomeOrganizzazione){
	        		$('#labelorganizzazione').show();
	        		$('#boxbando').find('input[name="progetto.nomeOrganizzazione"]').prop("disabled", false).val(nomeOrganizzazione); 
	        		$("#organizzazioneutente").html(nomeOrganizzazione);
	        	}else{
	        		$('#labelorganizzazione').hide();
	        		$('#boxbando').find('input[name="progetto.nomeOrganizzazione"]').prop("disabled", true).val(''); 
	        	} 	        		        		
	        	$('#boxbando').show();
        	}
        	*/
        	//To do visualizzazione box bandi FINE	
    			
    			var statoNucleo =$("#statoNucleo").text().toUpperCase(); 
    		
			if(statoNucleo !='VALIDATO' &&statoNucleo !='RIVALIDATO')
			{
				disableInput=true;
				$('#salvaVIP').attr('hidden', true);
        		$('#pubblicaVIP').attr('hidden', true);
        		$('#compilaVIP').attr('hidden', true);
        		$('#bozzaVIP').attr('hidden', true);
        		$('#riportaVIP').attr('hidden', true);
        		$('#rifiutaVIP').attr('hidden', true);  
        		$('#creaBozzaVIP').attr('hidden', true);
			}
				
    			
        	else if(!response.pubblicazioneId) {
        		
        		// GESTIONE CAMPI
				disableInput = true;

				// PULSANTIERA
        		$('#salvaVIP').attr('hidden', true);
        		$('#pubblicaVIP').attr('hidden', true);
        		$('#compilaVIP').attr('hidden', true);
        		$('#bozzaVIP').attr('hidden', true);
        		$('#riportaVIP').attr('hidden', true);
        		$('#rifiutaVIP').attr('hidden', true);  
			
				// INTESTAZIONE
        		//$('#autorePubblicazione').text("Non disponibile");
        		$('#statoPubblicazione').text("IN ATTESA DI REDAZIONE");

        	} else if (response.statoPubblicazione == 'BOZZA') {
        		
        		// PULSANTIERA
        		$('#bozzaVIP').attr('hidden', true);
        		$('#creaBozzaVIP').attr('hidden', true); 
        		$('#riportaVIP').attr('hidden', true);
        		
        		if(!window.flagPrm["pubblicazioneVIP"]){ // GESTIONE PULSANTI - PERMESSI
            		$('#pubblicaVIP').attr('hidden', true);
            	} else {
            		$('#compilaVIP').attr('hidden', true);
            		if(response.dataPubblicazione) {
    					$('#riportaVIP').attr('hidden', false);
        			}
            	}
            	
				if(response.fgPubblicato){
        			$('#rifiutaVIP').attr('hidden', true);
        		}

        		// INTESTAZIONE
        		//$('#autorePubblicazione').text("Non disponibile");
        		$('#statoPubblicazione').text("IN REDAZIONE");

        	} else if(response.statoPubblicazione == 'PUBBLICATO'){
        		
        		// GESTIONE CAMPI
        		disableInput = true;
        		
        		// PULSANTIERA
        		$('#salvaVIP').attr('hidden', true);
        		$('#pubblicaVIP').attr('hidden', true);
        		$('#compilaVIP').attr('hidden', true);
        		$('#riportaVIP').attr('hidden', true);
        		$('#creaBozzaVIP').attr('hidden', true);
        		$('#rifiutaVIP').attr('hidden', true);
        		
        		if(!window.flagPrm["inredazioneVIP"]){ // GESTIONE PULSANTI - PERMESSI
        			$('#bozzaVIP').attr('hidden', true);
        		}

				// INTESTAZIONE
        		$('#idLablePubblicazione').removeAttr('hidden');
        		//$('#autorePubblicazione').text(response.nomeOwner + " " + response.cognomeOwner);
        		
        	} else if(response.statoPubblicazione == 'REDATTO') {
	
        		$('#salvaVIP').attr('hidden', true);
        		$('#compilaVIP').attr('hidden', true);
        		$('#creaBozzaVIP').attr('hidden', true);
        		$('#riportaVIP').attr('hidden', true);
        		
        		if(!window.flagPrm["pubblicazioneVIP"]){
        			$('#pubblicaVIP').attr('hidden', true);
        			disableInput = true; // GESTIONE CAMPI - PERMESSI
        			
        			if(response.fgPubblicato){ // Se pubblicato il redattore non può vedere il pulsante
						$('#bozzaVIP').attr('hidden', true);
					}
        		} else {
        			if(response.dataPubblicazione) {

    					$('#riportaVIP').attr('hidden', false);
        			}
        		}
        		
        		if(response.fgPubblicato){
        			$('#rifiutaVIP').attr('hidden', true);
        		}

        		//$('#autorePubblicazione').text("Non disponibile");
        		$('#statoPubblicazione').text("REDATTO");
        		
        	} else if(response.statoPubblicazione == 'RIFIUTATO') {
          		
          		$('#riportaVIP').attr('hidden', true);
          		
          		if(response.dataPubblicazione) {
    				$('#riportaVIP').attr('hidden', false);
        		}

          		// GESTIONE CAMPI
          		disableInput = true;
				
				// PULSANTIERA
          		$('#salvaVIP').attr('hidden', true);
          		$('#compilaVIP').attr('hidden', true);
          		$('#creaBozzaVIP').attr('hidden', true);
          		$('#rifiutaVIP').attr('hidden', true); 
          		
          		$('#pubblicaVIP').attr('hidden', true);
          		
          		if(!window.flagPrm["pubblicazioneVIP"]){
					$('#bozzaVIP').attr('hidden', true);									
				}
          						
				// INTESTAZIONE
        		if(response.noteAggiuntive){
					$('#noteRifiuto').text(response.noteAggiuntive); 
					
				}else{
					$('#noteRifiuto').text("Non disponibile"); 
				}
				
          		$('#notePubblicazione').show(); 
          		
          		//$('#autorePubblicazione').text("Non disponibile");
          		$('#statoPubblicazione').text("RESPINTO");
           		$('#modalValidazione').modal('hide');	
          	}
          	
        	if (response.pubblicazioneId) {
        		$('#idPubblicazione').val(response.pubblicazioneId);
        		$('#statoPubblicazione').removeAttr('hidden');
        		$('[data-name="aggiungId"]').attr('id','labelEvent');
        	}
        	
        	if (!response.fgPubblicato) {
        		$('#revocaVIP').attr('hidden', true);
        	}

			$('#formSchedaVIP').deserializeJSONForm(response.genericMetadata,true);

			inizilizeSwitchMantieni(response.genericMetadata);

        	if(response.genericMetadata.tipologieMIBACT){
        		var arrayMIBACT = Object.keys(response.genericMetadata.tipologieMIBACT);
        		//console.log(arrayMIBACT);
        		$('#tipologieMIBACT').selectize()[0].selectize.setValue(arrayMIBACT, false);
        	}
        },
        error: function(error){        	
          	alertify.alert("Abbiamo riscontrato un problema", "<img src=\""+context+"/assets/images/ERRORE.svg\"><br/><br/><br/>Al momento non &egrave; possibile concludere l'operazione. Ci scusiamo e ti invitiamo a riprovare in un altro momento.").set('label', 'Chiudi'); 
        	$('#overlay').hide();		
		}
	});
	return disableInput;
}

function inizilizeSwitchMantieni(genericMetadata){

	if(genericMetadata.relazioneSet){
		$(genericMetadata.relazioneSet).each(function(index, relazione){
			if(!relazione.schedePubblicazione || (relazione.schedePubblicazione && relazione.schedePubblicazione.indexOf("VIP") === -1)){
				$('[name="relazioneSet[' + index + '].mantieni"]').attr("disabled", true);
				$('[name="relazioneSet[' + index + '].mantieni"]').parent('label').addClass('disabled');			
				
			}
			if(relazione.statoEventoAssociato && relazione.statoEventoAssociato !== "VALIDATO"){
				$('[data-template="relazioneSet[' + index + ']"]').hide();
			}
		});
	}
	
	if(genericMetadata.relazioneSetAggiunto){
		$(genericMetadata.relazioneSetAggiunto).each(function(index, relazione){
			if(relazione.statoEventoAssociato && relazione.statoEventoAssociato !== "VALIDATO"){
				$('[data-template="relazioneSetAggiunto[' + index + ']"]').hide();
			}
		});
	}
}

function fileImmaginiFeed(inputName, counter){
	
	flastorder();
	
	$('#overlay').show();
	$('[data-name="multilinguaDiv"]').removeAttr('hidden');
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
	formData.append("immagine", file, filename, lastorder);
	
    $.ajax({
        url : context+'/submitFile/immagine/' + idEvento + '?idPubblicazione=' + idPubblicazione + "&ordine=" + lastorder, // + '&w=' + 750 + '&h=' + 400,
        data : formData,
        type : 'POST',
        processData: false,
        contentType: false,
        success : function(data){
        	var json = JSON.parse(data);        	
       
        	$('[name="immagineSetAggiunto[' + counter + '].immagineId"]').val(json.allegatoId);
        	$('[name="immagineSetAggiunto[' + counter + '].estensione"]').val(json.estensione);
        	$('[name="immagineSetAggiunto[' + counter + '].dimensione"]').val(json.dimensione);
        	$('[name="immagineSetAggiunto[' + counter + '].nomeOriginale"]').val(json.nomeOriginale);
        	$('[name="immagineSetAggiunto[' + counter + '].nomeOriginale"]').after(json.nomeOriginale);
        	$('[name="immagineSetAggiunto[' + counter + '].ordine"]').val(lastorder);
    		$('[name="immagineSetAggiuntoLoad"]').val("");
    		$('#immagini-tab').removeClass('rch');
    		$('#media-tab').removeClass('rch');
        	$('#overlay').hide();
        	opendmstoastsuccess('Immagine inserita con successo!');
        },
    	error: function(error){
    		if (error.status == 400){
    			alertify.alert(window.mexJS['js.error.errore'], "<img src=\""+context+"/assets/images/ERRORE.svg\"><br/><br/><br/>"+window.mexJS['js.error.estensione']).set('label', 'Chiudi'); 
    		} else if (error.status == 406) {
    			alertify.alert(window.mexJS['js.error.errore'], "<img src=\""+context+"/assets/images/ERRORE.svg\"><br/><br/><br/>"+window.mexJS['js.error.dimensioni']).set('label', 'Chiudi'); 
    		} else if (error.status == 413) {
    			alertify.alert(window.mexJS['js.error.errore'], "<img src=\""+context+"/assets/images/ERRORE.svg\"><br/><br/><br/>"+window.mexJS['js.error.peso']).set('label', 'Chiudi'); 
    		}
    		 else if (error.status == 415) {
    			alertify.alert(window.mexJS['js.error.errore'], "<img src=\""+context+"/assets/images/ERRORE.svg\"><br/><br/><br/>"+window.mexJS['js.error.file.valido']).set('label', 'Chiudi'); 
    		}
    		$('[data-deletebutton="immagineSetAggiunto[' + counter + ']"]').click();
    		$('[name="immagineSetLoad"]').val("");
    		$('#overlay').hide();
    	}
    });
}

function initVisualizzaAgg(inputName, event) {
	$('[data-name="'+ inputName +'"]').click(function (event) {
        event.preventDefault();
    }).one(event, function(evento) {
    	var counter = inputName.substr(inputName.indexOf("[") + 1);
    	counter = counter.substr(0, counter.indexOf("]"));
		var immagineId = $('[name="immagineSetAggiunto[' + counter + '].immagineId"]').val();
    	var estensione = $('[name="immagineSetAggiunto[' + counter + '].estensione"]').val();
    	var eventoId = $('#eventoId').text();
    	var hrefVal = $('[data-name="'+ inputName +'"]').attr("href") + "/getFile/" + $("#eventoId").text() + "/immagine/" + immagineId + "." + estensione + '?idPubblicazione=' + $('#idPubblicazione').val();
    	$('[data-name="'+ inputName +'"]').attr("href", hrefVal);
    	$('[data-name="'+ inputName +'"]').magnificPopup({
    		type: 'image',
    		overflowY: 'scroll',
    		fixedContentPos: false,
    		image: {
    			tError: '<a href="%url%">Il tuo browser non supporta il formato di questa immagine e pertanto non riesce a visualizzarla. Clicca qui per scaricarla in locale.'
    		}
    	});
    	$('[data-name="'+ inputName +'"]').click();
	});
}

function initVisualizza(inputName, event) {
	$('[data-name="'+ inputName +'"]').click(function (event) {
        event.preventDefault();
    }).one(event, function(evento) {
    	var counter = inputName.substr(inputName.indexOf("[") + 1);
    	counter = counter.substr(0, counter.indexOf("]"));
		var immagineId = $('[name="immagineSet[' + counter + '].immagineId"]').val();
    	var estensione = $('[name="immagineSet[' + counter + '].estensione"]').val();
    	var eventoId = $('#eventoId').text();
    	var hrefVal = $('[data-name="'+ inputName +'"]').attr("href") + "/getFile/" + $("#eventoId").text() + "/immagine/" + immagineId + "." + estensione;
    	$('[data-name="'+ inputName +'"]').attr("href", hrefVal);
    	$('[data-name="'+ inputName +'"]').magnificPopup({
    		type: 'image',
    		overflowY: 'scroll',
    		fixedContentPos: false,
    		image: {
    			tError: '<a href="%url%">Il tuo browser non supporta il formato di questa immagine e pertanto non riesce a visualizzarla. Clicca qui per scaricarla in locale.'
    		}
    	});
    	$('[data-name="'+ inputName +'"]').click();
	});
}

function fileDocumentiFeed(inputName, counter){
	
	flastorderallegati();
	
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
	formData.append("documento", file, filename, lastorderallegati);
    $.ajax({
        url : context+'/submitFile/documento/' + idEvento+ '?idPubblicazione=' + idPubblicazione + '&ordine=' + lastorderallegati,
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
        	$('[name="documentoSetAggiunto[' + counter + '].nomeOriginale"]').after(json.nomeOriginale);
        	$('[name="documentoSetAggiunto[' + counter + '].ordineNumerico"]').val(lastorderallegati);
        	$('[name="documentoSetAggiuntoLoad"]').val("");
        	$('#overlay').hide();
        	opendmstoastsuccess('Documento inserito con successo!');
        },
    	error: function(error){
    		if (error.status == 400){
    			alertify.alert(window.mexJS['js.error.errore'], "<img src=\""+context+"/assets/images/ERRORE.svg\"><br/><br/><br/>"+window.mexJS['js.error.estensione']).set('label', 'Chiudi'); 
    		} else if (error.status == 406) {
    			alertify.alert(window.mexJS['js.error.errore'], "<img src=\""+context+"/assets/images/ERRORE.svg\"><br/><br/><br/>"+window.mexJS['js.error.dimensioni']).set('label', 'Chiudi'); 
    		} else if (error.status == 413) {
    			alertify.alert(window.mexJS['js.error.errore'], "<img src=\""+context+"/assets/images/ERRORE.svg\"><br/><br/><br/>"+window.mexJS['js.error.peso']).set('label', 'Chiudi'); 
    		} else if (error.status == 415) {
    			alertify.alert(window.mexJS['js.error.errore'], "<img src=\""+context+"/assets/images/ERRORE.svg\"><br/><br/><br/>"+window.mexJS['js.error.file.valido']).set('label', 'Chiudi'); 
    		}
    		$('[data-deletebutton="documentoSetAggiunto[' + counter + ']"]').click();
    		$('[name="documentoSetLoad"]').val("");
    		$('#overlay').hide();
    	}
    });
    
}

function initVisualizzaDocAgg(inputName, event){
	$('[data-name="'+ inputName +'"]').click(function (event) {
        event.preventDefault();
    	var counter = inputName.substr(inputName.indexOf("[") + 1);
    	counter = counter.substr(0, counter.indexOf("]"));
		var documentoId = $('[name="documentoSetAggiunto[' + counter + '].documentoId"]').val();
    	var estensione = $('[name="documentoSetAggiunto[' + counter + '].estensione"]').val();
    	var eventoId = $('#eventoId').text();
    	var hrefVal = $('[data-name="'+ inputName +'"]').attr("href") + "/getFile/" + eventoId + "/documento/" + documentoId + "." + estensione+ '?idPubblicazione=' + $('#idPubblicazione').val();
    	window.open(hrefVal, '_blank');
	});
}

function initVisualizzaDoc(inputName, event){
	$('[data-name="'+ inputName +'"]').click(function (event) {
        event.preventDefault();
    	var counter = inputName.substr(inputName.indexOf("[") + 1);
    	counter = counter.substr(0, counter.indexOf("]"));
		var documentoId = $('[name="documentoSet[' + counter + '].documentoId"]').val();
    	var estensione = $('[name="documentoSet[' + counter + '].estensione"]').val();
    	var eventoId = $('#idEventoPrincipale').val();
    	var hrefVal = $('[data-name="'+ inputName +'"]').attr("href") + "/getFile/" + $("#eventoId").text() + "/documento/" + documentoId + "." + estensione;
    	window.open(hrefVal, '_blank');
	});
}

function initDownload(inputName, event){
	$('[data-name="'+ inputName +'"]').click(function (event) {
        event.preventDefault();
    }).one(event, function(evento) {
    	var counter = inputName.substr(inputName.indexOf("[") + 1);
    	counter = counter.substr(0, counter.indexOf("]"));
		var immagineId = $('[name="immagineSet[' + counter + '].immagineId"]').val();
    	var estensione = $('[name="immagineSet[' + counter + '].estensione"]').val();
    	var eventoId = $('#eventoId').text();
    	var hrefVal = $('[data-name="'+ inputName +'"]').attr("href") + "/getFile/" + $("#eventoId").text() + "/immagine/" + immagineId + "." + estensione;
    	var link = document.createElement('a');
    	link.href = hrefVal;
    	link.download = immagineId+'.'+estensione;
    	document.body.appendChild(link);
    	link.click();
    	document.body.removeChild(link);
    });
}

function initDownloadDoc(inputName, event){
	$('[data-name="'+ inputName +'"]').click(function (event) {
        event.preventDefault();
    	var counter = inputName.substr(inputName.indexOf("[") + 1);
    	counter = counter.substr(0, counter.indexOf("]"));
		var documentoId = $('[name="documentoSet[' + counter + '].documentoId"]').val();
    	var estensione = $('[name="documentoSet[' + counter + '].estensione"]').val();
    	var eventoId = $('#idEventoPrincipale').val();
    	var hrefVal = $('[data-name="'+ inputName +'"]').attr("href") + "/getFile/" + $("#eventoId").text() + "/documento/" + documentoId + "." + estensione;
    	var link = document.createElement('a');
    	link.href = hrefVal;
    	link.download = documentoId+'.'+estensione;
    	document.body.appendChild(link);
    	link.click();
    	document.body.removeChild(link);
	});
}

function initDownloadAgg(inputName, event){
	$('[data-name="'+ inputName +'"]').click(function (event) {
        event.preventDefault();
    }).one(event, function(evento) {
    	var counter = inputName.substr(inputName.indexOf("[") + 1);
    	counter = counter.substr(0, counter.indexOf("]"));
		var immagineId = $('[name="immagineSetAggiunto[' + counter + '].immagineId"]').val();
    	var estensione = $('[name="immagineSetAggiunto[' + counter + '].estensione"]').val();
    	var eventoId = $('#eventoId').text();
    	var hrefVal = $('[data-name="'+ inputName +'"]').attr("href") + "/getFile/" + $("#eventoId").text() + "/immagine/" + immagineId + "." + estensione + '?idPubblicazione=' + $('#idPubblicazione').val();
    	var link = document.createElement('a');
    	link.href = hrefVal;
    	link.download = immagineId+'.'+estensione;
    	document.body.appendChild(link);
    	link.click();
    	document.body.removeChild(link);
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
    	link.href = hrefVal;
    	link.download = documentoId+'.'+estensione;
    	document.body.appendChild(link);
    	link.click();
    	document.body.removeChild(link);
	});
}

function selectizeRelazioniAgg(inputName){
	var selectizeRelazioniObj = $('[name="'+ inputName +'"]').selectize({
	    valueField: 'titolo',
	    labelField: 'titolo',
	    searchField: 'titolo',
	    maxItems: '1',
	    placeholder: window.mexJS['js.placeholder.relazioni'],
	    render: {
	    	option: function(item, escape) {
				var tipoEventoNucleo = item.tipo;
				if(tipoEventoNucleo == "EVENTO"){
					tipoEventoNucleo = "SINGOLO";
				}
	    		var selectOption = item.titolo+' ('+ tipoEventoNucleo +') creato il ' + convertDataEngToIta(item.dataIns.substr(0,10)) + ' alle ore ' + item.dataIns.substr(10);
	        	
	        	if(item.comune){
					selectOption = selectOption +', Comune '+ item.comune;
				}
				
	    		return '<option value="'+selectOption+'">' + selectOption + '</option>';

	        }
	    },
	    load: function(query, callback) {
	        if (query.length<3) {
				selectizeRelazioniObj.selectize()[0].selectize.clearOptions(false);
				return callback();
			}
	        var tipoRelazioneEvento = "EVENTO";
	        if ($("[value='EVENTO']").is(':checked')) {
	        	tipoRelazioneEvento = "RASSEGNA";
	        }
	        $.ajax({
	            url: window.context + '/titoliEventi/'+$('#eventoId').text()+'?tipoEvento='+tipoRelazioneEvento+'&q=' + query.toUpperCase(),
	            type: 'GET',
	            success: function(res) {
	            	var arrayUtilizzati = [];
	            	$('[name$="eventoRelazionatoId"]').not('[name*="_PH_"], [name="relazioneSet.eventoRelazionatoId"]').each(function() {
	            		arrayUtilizzati.push(Number($(this).val()));
	            	});
	            	var cleanUpArray = [];
	            	for(var i = 0 ; i < res.length; i++){
	            		if(!arrayUtilizzati.includes(res[i].eventoId)){
	            			cleanUpArray.push(res[i]);
	            		}
	            	}
	            	callback(cleanUpArray);
	            },
	            error: function() {
					selectizeRelazioniObj.selectize()[0].selectize.clearOptions(false);
					return callback();
	            }
	        });
	    },
	    onType: function(str){
			if(!str || str.length < 3){
				selectizeRelazioniObj.selectize()[0].selectize.clearOptions(false);
			}
		},
		onFocus: function(){
			selectizeRelazioniObj.selectize()[0].selectize.clearOptions(false);
		},
	    onChange: function(value) {
	    	
	    	if (!value.length) return;
	    	
	    	var data = this.options[value];
	    	
	    	$('[name="'+ inputName +'.eventoRelazionatoId"]').attr('value', data.eventoId);
	    	$('[name="'+ inputName +'.tipoEventoAssociato"]').attr('value', data.tipo);    	
	    	$('[name="'+ inputName +'.periodo"]').attr('value', data.periodo);
	    	$('[name="'+ inputName +'.comune"]').attr('value', data.comune);
	    }
	});
}

function selectizeRelazioniAggFeed(inputName, counter){
	var valueTitolo = $('[name="'+ inputName +'"]').val();
	var valueId = $('[name="'+ inputName +'.eventoRelazionatoId"]').val();
	var valueTipo = $('[name="'+ inputName +'.tipoEventoAssociato"]').val();
	var valuePeriodo = $('[name="'+ inputName +'.periodo"]').val();
	var valueComune = $('[name="'+ inputName +'.comune"]').val();
	var nameTitolo = 'relazioneSetAggiunto[' + counter + '].titolo';
	var nameId = 'relazioneSetAggiunto[' + counter + '].eventoRelazionatoId';
	var nameTipoRelazione = 'relazioneSetAggiunto[' + counter + '].tipoRelazione';
	var nameTipoEvAss = 'relazioneSetAggiunto[' + counter + '].tipoEventoAssociato';
	var namePeriodo = 'relazioneSetAggiunto[' + counter + '].periodo';
	var nameComune = 'relazioneSetAggiunto[' + counter + '].comune';
	$('[name="'+ nameTitolo +'"]').val(valueTitolo);
	$('[name="'+ nameId +'"]').val(valueId);
	$('[name="'+ nameTipoEvAss +'"]').val(valueTipo);
	$('[name="'+ namePeriodo +'"]').val(valuePeriodo);
	$('[name="'+ nameComune +'"]').val(valueComune);
	var tipoRelazione = "CONTIENE";
    if ($("[value='EVENTO']").is(':checked')) {
    	tipoRelazione = "CONTENUTO";
    } else {
    	if(valueTipo == 'EVENTO'){
    		$('[data-name="relazioneSetAggiunto[' + counter + '].eventoContenuto' +'"]').removeAttr('hidden');
    	} else {
    		$('[data-name="relazioneSetAggiunto[' + counter + '].rassegnaContenuta' +'"]').removeAttr('hidden');
    	}
		$('[data-name="relazioneSetAggiunto[' + counter + '].rassegnaAssociata' +'"]').attr('hidden','true');
    }
	$('[name="'+ nameTipoRelazione +'"]').val(tipoRelazione);
	$('[name="'+ inputName +'"]')[0].selectize.clear();
	$('[name="'+ inputName +'"]')[0].selectize.clearOptions();
	$('[name="'+ inputName +'"]')[0].selectize.clearCache();	
	
	if(valueTipo == 'EVENTO'){
		  $('[name="'+ nameTipoEvAss +'"]').after('SINGOLO');
	}else{
		  $('[name="'+ nameTipoEvAss +'"]').after(valueTipo);	
	}
	
	$('[name="'+ namePeriodo +'"]').after(valuePeriodo);
	$('[name="'+ nameComune +'"]').after(valueComune);	
	$('[name="'+ nameTitolo +'"]').after(valueTitolo);			
}

function selectizeAttrattori(inputName){
	var selectizeAttrattoriObj = $('[name="'+ inputName +'"]').selectize({
	    valueField: 'etichetta',
	    labelField: 'etichetta',
	    searchField: 'etichetta',
	    maxItems: '1',
	    placeholder: window.mexJS['js.placeholder.location.attrattori'],
	    render: {
	    	option: function(item, escape) {
	    		return '<option value="'+item.etichetta+'">'+item.etichetta+'</option>';
	        }
	    },
	    load: function(query, callback) {  	        
	        if (query.length < 3){			
	        	selectizeAttrattoriObj.selectize()[0].selectize.clearOptions(false);
					return callback();
				} 	        
	        $.ajax({
	            url: window.context + '/attrattori?s=' + query.toUpperCase(),
	            type: 'GET',
	            success: function(res) {  	            	
	               	var arrayUtilizzati = [];
	            	$('[name$="attrattoreId"]').not('[name*="_PH_"], [name="attrattoriSet.attrattoreId"]').each(function() {            		
	            		arrayUtilizzati.push(Number($(this).val()));
	            	});
	            	var cleanUpArray = [];
	            	for(var i = 0 ; i < res.length; i++){
	            		if(!arrayUtilizzati.includes(res[i].attrattoreId)){
	            			cleanUpArray.push(res[i]);
	            		}
	            	}            	
	            	callback(cleanUpArray);
	            },
	            error: function() {
	            	selectizeAttrattoriObj.selectize()[0].selectize.clearOptions(false);
    				return callback();
	            }
	        });
	    },
	    onType: function(str){
			if(!str || str.length < 3){
				selectizeAttrattoriObj.selectize()[0].selectize.clearOptions(false);
			}
		},
		onFocus: function(){
			selectizeAttrattoriObj.selectize()[0].selectize.clearOptions(false);
		},
	    onChange: function(value) {
	    	if (!value.length) return;
	    	var data = this.options[value];
	    	$('[name="'+ inputName +'.attrattoreId"]').attr('value', data.attrattoreId);
	    }
	});
	$("#attrattoriSelect").find('.selectize-control.form-control.Input-text').removeClass('multi');
}

function selectizeAttrattoriFeed(inputName, counter){
	var valueEtichetta = $('[name="'+ inputName +'"]').val();
	var valueId = $('[name="'+ inputName +'.attrattoreId"]').val();
	var suffix = inputName.substr(0,inputName.indexOf(".")+1);
	var nameEtichetta = suffix + 'attrattoriSet[' + counter + '].etichetta';
	var nameId = suffix + 'attrattoriSet[' + counter + '].attrattoreId';
	$('[name="'+ nameEtichetta +'"]').val(valueEtichetta);
	$('[name="'+ nameId +'"]').val(valueId);
	$('[name="'+ inputName +'"]')[0].selectize.clear();
}

function disableForm(){
	disableInputScheda = true;
	
	$("#formSchedaVIP :input").not('[id="riportaVIP"], [id="revocaVIP"], [id*="medAgg"], [id*="relAgg"]').prop('disabled', true);	
	$("#immaginiAggVIPbox").find("#mybutton").each(function() {		
		$(this).addClass("newdisabled");
	});
	
	$("#documentiAggVIPbox").find("#mybutton").each(function() {		
		$(this).addClass("newdisabled");
	});
	
	$('[name*="Multi"]').not('[name*="didascalia"], input[name*="titolo"]').each(function() {
		$(this).summernote('disable');
	});
	
	for (i=0; i < $("#formSchedaVIP .selectized").length; i++) {
		$("#formSchedaVIP .selectized").eq(i).selectize()[0].selectize.disable();
	}
	
	$("#myTabCEContent,	#boxmobileFilter, #boxmobilemyTabFE, #boxmobileMenu, #boxmobileAllegato, #boxmobileFilter2, #boxmobileAllegatoVip,#boxmobileLingua, #boxmobileLinguaG, #boxmobileLinguaVid, #boxmobileLinguaImg, #boxmobileLinguaDoc, #boxmobileLinguaVIPG, #boxmobileLinguaImgVip, #boxmobileLinguaDocVip, #boxmobileLinguaVidVip").find('select').prop('disabled', false);
	
}

function enableForm(){	
	disableInputScheda = false;	
	$("#formSchedaVIP :input").prop('disabled', false);	
	$('[name*="Multi"]').not('[name*="didascalia"], input[name*="titolo"]').each(function() {		
		$(this).summernote('enable');
	});
	
	for (i=0; i < $("#formSchedaVIP .selectized").length; i++) {
		$("#formSchedaVIP .selectized").eq(i).selectize()[0].selectize.enable();
	}
}

function validationSummerNote(){	
    var labelerrore = '<label id="campo-error" class="error" for="errore campo">* &Egrave; stato raggiunto il limite di caratteri complessivo</label>';    
	    $('#boxDatigenerali').find('.note-editor').each(function( index ) {		    	
	    	if($(this).hasClass( "errorsummernote" )){
		    	$(this).removeClass('errorsummernote');	    	
		    	$(this).addClass('error');	    	
			    $(this).closest('.boxInput').find('#campo-error').remove();
			    $(this).after(labelerrore);     
			    $('#datigeneraliV-tab').addClass('rch');
	    	}
	    });
	    
	    var itcontaerrori = $("#itattVIPbox").find("div.note-editor.note-frame.error").length;
	    var encontaerrori = $("#enattVIPbox").find("div.note-editor.note-frame.error").length;
	    var spcontaerrori = $("#spattVIPbox").find("div.note-editor.note-frame.error").length;
	    var frcontaerrori = $("#frattVIPbox").find("div.note-editor.note-frame.error").length;
	    var decontaerrori = $("#deattVIPbox").find("div.note-editor.note-frame.error").length;
	    var rucontaerrori = $("#ruattVIPbox").find("div.note-editor.note-frame.error").length;
	    
	    if(itcontaerrori>0){ $('#itattVIP-tab').addClass('rch'); }else{ $('#itattVIP-tab').removeClass('rch'); }
	    if(encontaerrori>0){ $('#enattVIP-tab').addClass('rch'); }else{ $('#enattVIP-tab').removeClass('rch'); }
	    if(spcontaerrori>0){ $('#spattVIP-tab').addClass('rch'); }else{ $('#spattVIP-tab').removeClass('rch'); }
	    if(frcontaerrori>0){ $('#frattVIP-tab').addClass('rch'); }else{ $('#frattVIP-tab').removeClass('rch'); }
	    if(decontaerrori>0){ $('#deattVIP-tab').addClass('rch'); }else{ $('#deattVIP-tab').removeClass('rch'); } 
	    if(rucontaerrori>0){ $('#ruattVIP-tab').addClass('rch'); }else{ $('#ruattVIP-tab').removeClass('rch'); }
}



function enableSummerNoteDesc(){
	
	$('[name^="descrizioneMulti"]').summernote({
		height: 150,
		lang: 'it-IT',
		toolbar: [
		    ['style', ['bold', 'italic', 'underline']], 
		    ['para', ['ul', 'ol', 'paragraph']],
		    ['insert', ['link', 'hr']]
		  ],
		  placeholder: true, 
		  callbacks: {
		    onInit: function(e) {
		      this.placeholder 
		        ? e.editingArea.find(".note-placeholder").html(this.placeholder)
		        : e.editingArea.remove(".note-placeholder") 
		        
		        if($(this).summernote('isEmpty')){
		        	$(this).next('.note-editor').find('.note-editable').html(''); 
				 }
		        
		    //    $(this).next('.note-editor').find('.note-resizebar').hide(); 

		    },
		    onFocus: function (contents) {
			    if($(this).summernote('isEmpty')){
			      $(this).html(''); 
			    }
			  }
	
		  },
		  
		  
	}).on('summernote.keyup', function(e) {
	
	    var text = $(this).next('.note-editor').find('.note-editable').html();	    
	    var maxlength = $(this).closest('div.boxInput').find('textarea:first-child').attr('data-maxlength'); 
	    var maxlengthtml = $(this).closest('div.boxInput').find('textarea:first-child').attr('data-maxlengthtml');
	    var lengthhtml = text.length;
	    var num = maxlength - length;  	    
	    
        //var body2 =  $(this).html();
        var temp2 = document.createElement("div");
        temp2.innerHTML = text;
        var sanitized2 = temp2.textContent || temp2.innerText;
        sanitized2 = ConvertCharacter(sanitized2);
        var length = sanitized2.length;	     	    
	    
    if (length > maxlength || lengthhtml > maxlengthtml ) {	
	    	
	    	$(this).next('.note-editor').addClass('errorsummernote');	     
	       // var labelerrore = '<label id="campo-error" class="error" for="errore campo">* non superare il numero limite di caratteri richiesto</label>';
	       // $(this).closest('.boxInput').find('#campo-error').remove();
	      //  $(this).next('.note-editor').after(labelerrore);
	    } else{
	    	
	    	$(this).next('.note-editor').removeClass('errorsummernote');
	    	$(this).next('.note-editor').removeClass('error');
	    	
	    	$(this).closest('.boxInput').find('#campo-error').remove();
	    	$(this).closest('.boxInput').find('.error').hide();
	    	
	    } 
    
    if (length > 0 || lengthhtml > 0 ) {	
    	$(this).next('.note-editor').removeClass('errorV');
    	$(this).closest('.boxInput').find('.errorV').hide();
    }
    
    
    
    
	    
    var itcontaerrori = $("#itattVIPbox").find("div.note-editor.note-frame.error").length;
    var encontaerrori = $("#enattVIPbox").find("div.note-editor.note-frame.error").length;
    var spcontaerrori = $("#spattVIPbox").find("div.note-editor.note-frame.error").length;
    var frcontaerrori = $("#frattVIPbox").find("div.note-editor.note-frame.error").length;
    var decontaerrori = $("#deattVIPbox").find("div.note-editor.note-frame.error").length;
    var rucontaerrori = $("#ruattVIPbox").find("div.note-editor.note-frame.error").length;
	    
	    if(itcontaerrori>0){ $('#itattVIP-tab').addClass('rch'); }else{ $('#itattVIP-tab').removeClass('rch'); }
	    if(encontaerrori>0){ $('#enattVIP-tab').addClass('rch'); }else{ $('#enattVIP-tab').removeClass('rch'); }
	    if(spcontaerrori>0){ $('#spattVIP-tab').addClass('rch'); }else{ $('#spattVIP-tab').removeClass('rch'); }
	    if(frcontaerrori>0){ $('#frattVIP-tab').addClass('rch'); }else{ $('#frattVIP-tab').removeClass('rch'); }
	    if(decontaerrori>0){ $('#deattVIP-tab').addClass('rch'); }else{ $('#deattVIP-tab').removeClass('rch'); } 
	    if(rucontaerrori>0){ $('#ruattVIP-tab').addClass('rch'); }else{ $('#ruattVIP-tab').removeClass('rch'); }
	    
	    var contaerroritotali = $("#boxDatigenerali").find("div.note-editor.note-frame.errorV").length;
	    if(contaerroritotali==0){
	        $('#datigeneraliV-tab').removeClass('rch');
	        if($('ul#pubblTab.nav.nav-tabs>li').find('a.nav-link.rch').length == 0){
	        	$('#labelValid').hide();   
	        }	    
	     }
	});
}









function enableSummerNote(){
	$('[name*="Multi"]').not('[name*="didascalia"], input[name*="titolo"], [name^="descrizioneMulti"]').summernote({
		height: 75,
		lang: 'it-IT',
		toolbar: [
		    ['style', ['bold', 'italic', 'underline']], 
		    ['para', ['ul', 'ol', 'paragraph']],
		    ['insert', ['link', 'hr']]
		  ],
		  placeholder: true, 
		  callbacks: {
		    onInit: function(e) {
		      this.placeholder 
		        ? e.editingArea.find(".note-placeholder").html(this.placeholder)
		        : e.editingArea.remove(".note-placeholder")		        
		        
		        if($(this).summernote('isEmpty')){
		        	$(this).next('.note-editor').find('.note-editable').html(''); 
				 }
		        
		        //  $(this).next('.note-editor').find('.note-resizebar').hide(); 
		        	   		        
		    },
		    onFocus: function (contents) {
			    if($(this).summernote('isEmpty')){
			      $(this).html(''); 
			    }
			  }

		  }
	}).on('summernote.keyup', function(e) {	
		
		
	    var text = $(this).next('.note-editor').find('.note-editable').html();	    
	    var maxlength = $(this).closest('div.boxInput').find('textarea:first-child').attr('data-maxlength'); 
	    var maxlengthtml = $(this).closest('div.boxInput').find('textarea:first-child').attr('data-maxlengthtml');
	    var lengthhtml = text.length;
	    var num = maxlength - length;  	    
	    
        //var body2 =  $(this).html();
        var temp2 = document.createElement("div");
        temp2.innerHTML = text;
        var sanitized2 = temp2.textContent || temp2.innerText;
        
        sanitized2 = ConvertCharacter(sanitized2);
        var length = sanitized2.length;
        

	     	    
	    
    if (length > maxlength || lengthhtml > maxlengthtml ) {		    	
	    	$(this).next('.note-editor').addClass('errorsummernote');   	
	     
	    //    var labelerrore = '<label id="campo-error" class="error" for="errore campo">* non superare il numero limite di caratteri richiesto</label>';
	     //   $(this).closest('.boxInput').find('#campo-error').remove();
	   //     $(this).next('.note-editor').after(labelerrore);       
	       //$(this).summernote("code", text);
	    } else{	 
	    	$(this).next('.note-editor').removeClass('error');
	 
	    	$(this).next('.note-editor').removeClass('errorsummernote');
	    	$(this).closest('.boxInput').find('#campo-error').remove();
	    	$(this).closest('.boxInput').find('.error').hide();
	    }
    
    if (length > 0 || lengthhtml > 0 ) {	
    	$(this).next('.note-editor').removeClass('errorV');
    	$(this).closest('.boxInput').find('.errorV').hide();
    } 
    
    var itcontaerrori = $("#itattVIPbox").find("div.note-editor.note-frame.error").length;
    var encontaerrori = $("#enattVIPbox").find("div.note-editor.note-frame.error").length;
    var spcontaerrori = $("#spattVIPbox").find("div.note-editor.note-frame.error").length;
    var frcontaerrori = $("#frattVIPbox").find("div.note-editor.note-frame.error").length;
    var decontaerrori = $("#deattVIPbox").find("div.note-editor.note-frame.error").length;
    var rucontaerrori = $("#ruattVIPbox").find("div.note-editor.note-frame.error").length;
    
    if(itcontaerrori>0){ $('#itattVIP-tab').addClass('rch'); }else{ $('#itattVIP-tab').removeClass('rch'); }
    if(encontaerrori>0){ $('#enattVIP-tab').addClass('rch'); }else{ $('#enattVIP-tab').removeClass('rch'); }
    if(spcontaerrori>0){ $('#spattVIP-tab').addClass('rch'); }else{ $('#spattVIP-tab').removeClass('rch'); }
    if(frcontaerrori>0){ $('#frattVIP-tab').addClass('rch'); }else{ $('#frattVIP-tab').removeClass('rch'); }
    if(decontaerrori>0){ $('#deattVIP-tab').addClass('rch'); }else{ $('#deattVIP-tab').removeClass('rch'); } 
    if(rucontaerrori>0){ $('#ruattVIP-tab').addClass('rch'); }else{ $('#ruattVIP-tab').removeClass('rch'); }
    
    var contaerroritotali = $("#boxDatigenerali").find("div.note-editor.note-frame.errorV").length;
    if(contaerroritotali==0){
        $('#datigeneraliV-tab').removeClass('rch');
        if($('ul#pubblTab.nav.nav-tabs>li').find('a.nav-link.rch').length == 0){
        	$('#labelValid').hide();   
        }     
     }    

	});
	
	
	$('[name^="descrizioneMulti"]').summernote({
		height: 150,
		lang: 'it-IT',
		toolbar: [
		    ['style', ['bold', 'italic', 'underline']], 
		    ['para', ['ul', 'ol', 'paragraph']],
		    ['insert', ['link', 'hr']]
		  ],
		  placeholder: true, 
		  callbacks: {
		    onInit: function(e) {
		      this.placeholder 
		        ? e.editingArea.find(".note-placeholder").html(this.placeholder)
		        : e.editingArea.remove(".note-placeholder") 
		        
		        if($(this).summernote('isEmpty')){
		        	$(this).next('.note-editor').find('.note-editable').html(''); 
				 }
		        
		    //    $(this).next('.note-editor').find('.note-resizebar').hide(); 

		    },
		    onFocus: function (contents) {
			    if($(this).summernote('isEmpty')){
			      $(this).html(''); 
			    }
			  }
	
		  },
		  
		  
	}).on('summernote.keyup', function(e) {
	
	    var text = $(this).next('.note-editor').find('.note-editable').html();	    
	    var maxlength = $(this).closest('div.boxInput').find('textarea:first-child').attr('data-maxlength'); 
	    var maxlengthtml = $(this).closest('div.boxInput').find('textarea:first-child').attr('data-maxlengthtml');
	    var lengthhtml = text.length;
	    var num = maxlength - length;  	    
	    
        //var body2 =  $(this).html();
        var temp2 = document.createElement("div");
        temp2.innerHTML = text;
        var sanitized2 = temp2.textContent || temp2.innerText;
        sanitized2 = ConvertCharacter(sanitized2);
        var length = sanitized2.length;	     	    
	    
    if (length > maxlength || lengthhtml > maxlengthtml ) {	
	    	
	    	$(this).next('.note-editor').addClass('errorsummernote');	     
	       // var labelerrore = '<label id="campo-error" class="error" for="errore campo">* non superare il numero limite di caratteri richiesto</label>';
	       // $(this).closest('.boxInput').find('#campo-error').remove();
	      //  $(this).next('.note-editor').after(labelerrore);
	    } else{
	    	
	    	$(this).next('.note-editor').removeClass('errorsummernote');
	    	$(this).next('.note-editor').removeClass('error');
	    	
	    	$(this).closest('.boxInput').find('#campo-error').remove();
	    	$(this).closest('.boxInput').find('.error').hide();
	    	
	    } 
    
    if (length > 0 || lengthhtml > 0 ) {	
    	$(this).next('.note-editor').removeClass('errorV');
    	$(this).closest('.boxInput').find('.errorV').hide();
    }
    
    
    
    
	    
    var itcontaerrori = $("#itattVIPbox").find("div.note-editor.note-frame.error").length;
    var encontaerrori = $("#enattVIPbox").find("div.note-editor.note-frame.error").length;
    var spcontaerrori = $("#spattVIPbox").find("div.note-editor.note-frame.error").length;
    var frcontaerrori = $("#frattVIPbox").find("div.note-editor.note-frame.error").length;
    var decontaerrori = $("#deattVIPbox").find("div.note-editor.note-frame.error").length;
    var rucontaerrori = $("#ruattVIPbox").find("div.note-editor.note-frame.error").length;
	    
	    if(itcontaerrori>0){ $('#itattVIP-tab').addClass('rch'); }else{ $('#itattVIP-tab').removeClass('rch'); }
	    if(encontaerrori>0){ $('#enattVIP-tab').addClass('rch'); }else{ $('#enattVIP-tab').removeClass('rch'); }
	    if(spcontaerrori>0){ $('#spattVIP-tab').addClass('rch'); }else{ $('#spattVIP-tab').removeClass('rch'); }
	    if(frcontaerrori>0){ $('#frattVIP-tab').addClass('rch'); }else{ $('#frattVIP-tab').removeClass('rch'); }
	    if(decontaerrori>0){ $('#deattVIP-tab').addClass('rch'); }else{ $('#deattVIP-tab').removeClass('rch'); } 
	    if(rucontaerrori>0){ $('#ruattVIP-tab').addClass('rch'); }else{ $('#ruattVIP-tab').removeClass('rch'); }
	    
	    var contaerroritotali = $("#boxDatigenerali").find("div.note-editor.note-frame.errorV").length;
	    if(contaerroritotali==0){
	        $('#datigeneraliV-tab').removeClass('rch');
	        if($('ul#pubblTab.nav.nav-tabs>li').find('a.nav-link.rch').length == 0){
	        	$('#labelValid').hide();   
	        }	    
	     }
	});
			
	
	$('div[class="note-editor note-frame panel panel-default"]').removeClass('panel').removeClass('panel-default');
}

$(document).on('click', '.note-btn', function() {
	$(".note-editable").each(function( index ) {
		$(this).keyup();
	});
});


//$(document).on('click', 'input[type="button"]', function() {	
//	setTimeout(function(){ 		
//		$(".note-editable").each(function( index ) {
//			$(this).keyup();
//		});	
//	}, 500);	
//});








function switchPaginaAgg(){
	$('#nucleoMedia').attr('hidden', true);
	$('#AggMedia').removeAttr('hidden');
}

function switchPaginaNucleo(){
	$('#nucleoMedia').removeAttr('hidden');
	$('#AggMedia').attr('hidden', true);
}

function switchPaginaAggR(){
	$('#nucleoRel').attr('hidden', true);
	$('#AggRel').removeAttr('hidden');
}

function switchPaginaNucleoR(){
	$('#nucleoRel').removeAttr('hidden');
	$('#AggRel').attr('hidden', true);
}


$(document).ready(function() {

	if (window.enterInRed){	
	
		
		if(caricaContenutoScheda()){
			enableSummerNote();
			disableForm();
		}else{
			enableSummerNote();
		}		
		
		window.enterInRed = false;
	}
	
	$('#formSchedaVIP :input').not('[type="checkbox"], [type="radio"]').mouseenter(function(){
		$(this).attr('title', $(this).val());
	});


	$('#overlay').hide();
});


var contaimmagini = 1;
var contadocumenti = 1;
var contalink = 1;

var contaimmaginivip = 1;
var contadocumentivip = 1;
var contalinkvip = 1;

var contaimmaginivipagg = 1;
var contadocumentivipagg = 1;
var contalinkvipagg = 1;

function listalingue(){
	
	contaimmagini = $("div.contaimmagini > div.row").length;
	contadocumenti = $("div.contadocumenti > div.row").length;
	contalink = $("div.contalink > div.row").length;
	
	contaimmaginivip = $("div.contaimmaginivip > div.row").length;
	contadocumentivip = $("div.contadocumentivip > div.row").length;
	contalinkvip = $("div.contalinkvip > div.row").length;
	
	contaimmaginivipagg = $("div.contaimmaginivipagg > div.row").length;
	contadocumentivipagg = $("div.contadocumentivipagg > div.row").length;
	contalinkvipagg = $("div.contalinkvipagg > div.row").length;
	
	if(contaimmagini == 1){	$("#myTab7").hide(); }else{	$("#myTab7").show(); }	
	if(contadocumenti == 1){ $("#myTab9").hide(); }else{ $("#myTab9").show(); }	
	if(contalink == 1){	$("#vidTab").hide(); }else{	$("#vidTab").show(); }
	
	if(contaimmaginivip == 1 && contaimmaginivipagg == 1){	$("#VIPimmtab").hide(); }else{	$("#VIPimmtab").show(); }	
	if(contadocumentivip == 1 && contadocumentivipagg == 1){ $("#myVIPdc").hide(); }else{ $("#myVIPdc").show(); }	
	if(contalinkvip == 1 && contalinkvipagg == 1){	$("#vidVIPTab").hide(); }else{	$("#vidVIPTab").show(); }
	
}

function fileClick(but){	
	$('button#'+but).click();	
}

function fileClickImgV(but){		
	if( (contaimmaginivip + contadocumentivipagg)< 17){
		$('button#'+but).click();
	}else{
		alertify.alert("Errore", "<img src=\""+context+"/assets/images/ERRORE.svg\"><br/><br/><br/>Hai raggiunto il numero massimo per l'inserimento di immagini.").set('label', 'Chiudi'); 
	}	
}

function fileClickDocV(but){		
	if( (contadocumentivip + contadocumentivipagg) < 17){
		$('button#'+but).click();
	}else{
		alertify.alert("Errore", "<img src=\""+context+"/assets/images/ERRORE.svg\"><br/><br/><br/>Hai raggiunto il numero massimo per l'inserimento di allegati.").set('label', 'Chiudi'); 
	}
}

$(document).on('change', 'input[name$=".banner"]', function() {
	if($(this).prop( "checked") == true){		
		$('#boxMedia').find('input[name$=".banner"]').prop( "checked", false );
		$(this).prop( "checked", true);
	}
});

$(document).on('click', 'a#mediaV-tab', function() {
	
	$('ul#docVIPTab > li').each(function( index ) {
		  $(this).removeClass('active');
	});	
	$('ul#docVIPTab > li > div').each(function( index ) {
		  $(this).removeClass('active');
	});		
	$('ul#docVIPTab > li:first-child div').click();
	$('ul#docVIPTab > li:first-child').click();	
	
	
	//	per le lingue
	$('ul#VIPimmtab > li').each(function( index ) {
		  $(this).removeClass('active');
	});	
	$('ul#VIPimmtab > li > a').each(function( index ) {
		  $(this).removeClass('active');
	});	
	$('ul#VIPimmtab > li:first-child a').click();
	
	
	
	$('ul#myVIPdc > li').each(function( index ) {
		  $(this).removeClass('active');
	});	
	$('ul#myVIPdc > li > a').each(function( index ) {
		  $(this).removeClass('active');
	});	
	$('ul#myVIPdc > li:first-child a').click();
	
	
	
	$('ul#vidVIPTab > li').each(function( index ) {
		  $(this).removeClass('active');
	});	
	$('ul#vidVIPTab > li > a').each(function( index ) {
		  $(this).removeClass('active');
	});	
	$('ul#vidVIPTab > li:first-child a').click();
		
});

$(document).on('click', '#rifiutaVIP', function() {
	$('#modalValidazione').modal('show');	
});


$(document).on( "click", ".dropdownbox", function() { 
		    $(this).next("ul.nav.nav-tabs").toggleClass("showMenu");
		    $(this).next("ul.nav.nav-tabs > li > a").click(function(){
	        //$(".dropdownbox > p").text($(this).text());
			$(this).next("ul.nav.nav-tabs").removeClass("showMenu");
	      });
	  });

$(document).on( "click", "ul#VIPimmtab li", function() {	  
	  var ckid = $(this).find('a').html();		  
	  if(ckid != 'IT'){		  
		  $('.contaimmaginivip').find('input[name$=".ordine"]').each(function( index ) {			  
			  $(this).attr('readonly', true);
		  });	
		  $('.contaimmaginivip').find('input[name$=".banner"]').each(function( index ) {			  
			  $(this).attr('disabled', true);
		  });
		  $('.contaimmaginivip').find('input[name$=".daPubblicare"]').each(function( index ) {			  
			  $(this).attr('disabled', true);
		  });
	  }else{
		  $('.contaimmaginivip').find('input[name$=".ordine"]').each(function( index ) {			  
			  $(this).attr('readonly', disableInputScheda);
		  });	
		  $('.contaimmaginivip').find('input[name$=".banner"]').each(function( index ) {			  
			  $(this).attr('disabled', disableInputScheda);
		  });
		  $('.contaimmaginivip').find('input[name$=".daPubblicare"]').each(function( index ) {			  
			  $(this).attr('disabled', disableInputScheda);
		  });
	  }		
	  if(ckid != 'IT'){		  
		  $('.contaimmaginivipagg').find('textarea[name$=".credits"]').each(function( index ) {			  
			  $(this).attr('readonly', true);
		  });	
		  $('.contaimmaginivipagg').find('input[name$=".ordine"]').each(function( index ) {			  
			  $(this).attr('readonly', true);
		  });
		  $('.contaimmaginivipagg').find('input[name$=".banner"]').each(function( index ) {			  
			  $(this).attr('disabled', true);
		  });
	  }else{
		  $('.contaimmaginivipagg').find('textarea[name$=".credits"]').each(function( index ) {			  
			  $(this).attr('readonly', disableInputScheda);
		  });
		  $('.contaimmaginivipagg').find('input[name$=".ordine"]').each(function( index ) {			  
			  $(this).attr('readonly', disableInputScheda);
		  });
		  $('.contaimmaginivipagg').find('input[name$=".banner"]').each(function( index ) {			  
			  $(this).attr('disabled', disableInputScheda);
		  });
	  }	
});
//mobile
$(document).on( "change", "select#mobiletabLinguaImgVip", function() {  
	  var ckid = $(this).val();		  
	  if(ckid != 'IT'){	
			  
		  $('.contaimmaginivip').find('input[name$=".ordine"]').each(function( index ) {			  
			  $(this).attr('readonly', true);
		  });	
		  $('.contaimmaginivip').find('input[name$=".banner"]').each(function( index ) {			  
			  $(this).attr('disabled', true);
		  });
		  $('.contaimmaginivip').find('input[name$=".daPubblicare"]').each(function( index ) {			  
			  $(this).attr('disabled', true);
		  });
	  }else{
		  $('.contaimmaginivip').find('input[name$=".ordine"]').each(function( index ) {			  
			  $(this).attr('readonly', disableInputScheda);
		  });	
		  $('.contaimmaginivip').find('input[name$=".banner"]').each(function( index ) {			  
			  $(this).attr('disabled', disableInputScheda);
		  });
		  $('.contaimmaginivip').find('input[name$=".daPubblicare"]').each(function( index ) {			  
			  $(this).attr('disabled', disableInputScheda);
		  });
	  }		
	  if(ckid != 'IT'){		  
		  $('.contaimmaginivipagg').find('textarea[name$=".credits"]').each(function( index ) {			  
			  $(this).attr('readonly', true);
		  });	
		  $('.contaimmaginivipagg').find('input[name$=".ordine"]').each(function( index ) {			  
			  $(this).attr('readonly', true);
		  });
		  $('.contaimmaginivipagg').find('input[name$=".banner"]').each(function( index ) {			  
			  $(this).attr('disabled', true);
		  });
	  }else{
		  $('.contaimmaginivipagg').find('textarea[name$=".credits"]').each(function( index ) {			  
			  $(this).attr('readonly', disableInputScheda);
		  });
		  $('.contaimmaginivipagg').find('input[name$=".ordine"]').each(function( index ) {			  
			  $(this).attr('readonly', disableInputScheda);
		  });
		  $('.contaimmaginivipagg').find('input[name$=".banner"]').each(function( index ) {			  
			  $(this).attr('disabled', disableInputScheda);
		  });
	  }	
});


$(document).on( "click", "ul#myVIPdc li", function() {	  
	  var ckid = $(this).find('a').html();		  
	  if(ckid != 'IT'){		  
		  $('.contadocumentivip').find('input[name$=".ordineNumerico"]').each(function( index ) {			  
			  $(this).attr('readonly', true);
		  });	
		  $('.contadocumentivip').find('input[name$=".daPubblicare"]').each(function( index ) {			  
			  $(this).attr('disabled', true);
		  });
	  }else{
		  $('.contadocumentivip').find('input[name$=".ordineNumerico"]').each(function( index ) {			  
			  $(this).attr('readonly', disableInputScheda);
		  });	

		  $('.contadocumentivip').find('input[name$=".daPubblicare"]').each(function( index ) {			  
			  $(this).attr('disabled', disableInputScheda);
		  });
	  }		
	  if(ckid != 'IT'){
		  $('.contadocumentivipagg').find('input[name$=".ordineNumerico"]').each(function( index ) {			  
			  $(this).attr('readonly', true);
		  });
		  $('.contadocumentivipagg').find('input[name$=".daPubblicare"]').each(function( index ) {			  
			  $(this).attr('disabled', true);
		  });
	  }else{
		  $('.contadocumentivipagg').find('input[name$=".ordineNumerico"]').each(function( index ) {			  
			  $(this).attr('readonly', disableInputScheda);
		  });
		  $('.contadocumentivipagg').find('input[name$=".daPubblicare"]').each(function( index ) {			  
			  $(this).attr('disabled', disableInputScheda);
		  });
	  }	
});
//mobile
$(document).on( "change", "select#mobiletabLinguaDocVip", function() {    
	  var ckid = $(this).val();		  
	  if(ckid != 'IT'){		  
		  $('.contadocumentivip').find('input[name$=".ordineNumerico"]').each(function( index ) {			  
			  $(this).attr('readonly', true);
		  });	
		  $('.contadocumentivip').find('input[name$=".daPubblicare"]').each(function( index ) {			  
			  $(this).attr('disabled', true);
		  });
	  }else{
		  $('.contadocumentivip').find('input[name$=".ordineNumerico"]').each(function( index ) {			  
			  $(this).attr('readonly', disableInputScheda);
		  });	

		  $('.contadocumentivip').find('input[name$=".daPubblicare"]').each(function( index ) {			  
			  $(this).attr('disabled', disableInputScheda);
		  });
	  }		
	  if(ckid != 'IT'){
		  $('.contadocumentivipagg').find('input[name$=".ordineNumerico"]').each(function( index ) {			  
			  $(this).attr('readonly', true);
		  });
		  $('.contadocumentivipagg').find('input[name$=".daPubblicare"]').each(function( index ) {			  
			  $(this).attr('disabled', true);
		  });
	  }else{
		  $('.contadocumentivipagg').find('input[name$=".ordineNumerico"]').each(function( index ) {			  
			  $(this).attr('readonly', disableInputScheda);
		  });
		  $('.contadocumentivipagg').find('input[name$=".daPubblicare"]').each(function( index ) {			  
			  $(this).attr('disabled', disableInputScheda);
		  });
	  }	
});

$(document).on( "click", "ul#vidVIPTab li", function() {	  
	  var ckid = $(this).find('a').html();		  
	  if(ckid != 'IT'){
		  $('.contalinkvip').find('input[name$=".ordineNumerico"]').each(function( index ) {			  
			  $(this).attr('readonly', true);
		  });
		  $('.contalinkvip').find('input[name$=".daPubblicare"]').each(function( index ) {			  
			  $(this).attr('disabled', true);
		  });
	  }else{
		  $('.contalinkvip').find('input[name$=".ordineNumerico"]').each(function( index ) {			  
			  $(this).attr('readonly', disableInputScheda);
		  });
		  $('.contalinkvip').find('input[name$=".daPubblicare"]').each(function( index ) {			  
			  $(this).attr('disabled', disableInputScheda);
		  });
	  }	
	  if(ckid != 'IT'){	
		  $('.contalinkvipagg').find('input[name$=".ordineNumerico"]').each(function( index ) {			  
			  $(this).attr('readonly', true);
		  });
		  $('.contalinkvipagg').find('input[name$=".daPubblicare"]').each(function( index ) {			  
			  $(this).attr('disabled', true);
		  });
		  $('.contalinkvipagg').find('textarea[name$=".credits"]').each(function( index ) {			  
			  $(this).attr('readonly', true);
		  });
		  $('.contalinkvipagg').find('input[name$=".link"]').each(function( index ) {				  
			  $(this).attr('readonly', true);
		  });
	  }else{
		  $('.contalinkvipagg').find('input[name$=".ordineNumerico"]').each(function( index ) {			  
			  $(this).attr('readonly', disableInputScheda);
		  });
		  $('.contalinkvipagg').find('input[name$=".daPubblicare"]').each(function( index ) {			  
			  $(this).attr('disabled', disableInputScheda);
		  });
		  $('.contalinkvipagg').find('textarea[name$=".credits"]').each(function( index ) {			  
			  $(this).attr('readonly', disableInputScheda);
		  });
		  $('.contalinkvipagg').find('input[name$=".link"]').each(function( index ) {				  
			  $(this).attr('readonly', disableInputScheda);
		  });
	  }	
});
//mobile 
$(document).on( "change", "select#mobiletabLinguaVidVip", function() {    
	  var ckid = $(this).val();		  
	  if(ckid != 'IT'){
		  $('.contalinkvip').find('input[name$=".ordineNumerico"]').each(function( index ) {			  
			  $(this).attr('readonly', true);
		  });
		  $('.contalinkvip').find('input[name$=".daPubblicare"]').each(function( index ) {			  
			  $(this).attr('disabled', true);
		  });
	  }else{
		  $('.contalinkvip').find('input[name$=".ordineNumerico"]').each(function( index ) {
			  $(this).attr('readonly', disableInputScheda);
		  });
		  $('.contalinkvip').find('input[name$=".daPubblicare"]').each(function( index ) {			  
			  $(this).attr('disabled', disableInputScheda);
		  });
	  }	
	  if(ckid != 'IT'){	
		  $('.contalinkvipagg').find('input[name$=".ordineNumerico"]').each(function( index ) {			  
			  $(this).attr('readonly', true);
		  });
		  $('.contalinkvipagg').find('input[name$=".daPubblicare"]').each(function( index ) {			  
			  $(this).attr('disabled', true);
		  });
		  $('.contalinkvipagg').find('textarea[name$=".credits"]').each(function( index ) {			  
			  $(this).attr('readonly', true);
		  });
		  $('.contalinkvipagg').find('input[name$=".link"]').each(function( index ) {				  
			  $(this).attr('readonly', true);
		  });
	  }else{
		  $('.contalinkvipagg').find('input[name$=".ordineNumerico"]').each(function( index ) {			  
			  $(this).attr('readonly', disableInputScheda);
		  });
		  $('.contalinkvipagg').find('input[name$=".daPubblicare"]').each(function( index ) {			  
			  $(this).attr('disabled', disableInputScheda);
		  });
		  $('.contalinkvipagg').find('textarea[name$=".credits"]').each(function( index ) {			  
			  $(this).attr('readonly', disableInputScheda);
		  });
		  $('.contalinkvipagg').find('input[name$=".link"]').each(function( index ) {				  
			  $(this).attr('readonly', disableInputScheda);
		  });
	  }	
});


$('#mobiletabMenu, #mobiletabLinguaVip, #mobiletabLinguaTic, #mobiletabLinguaImgVip, #mobiletabLinguaDocVip, #mobiletabLinguaVidVip, #mobiletabAllegatoVip').on('change', function (e) {
	  var $optionSelected = $("option:selected", this);
	  $optionSelected.tab('show')
	});


$(document).ready(function() { 
	  $(".contarelazioni, .contarelazioniagg").find('input.inputtotext.toevento').each(function( index ) {			  
		  if($(this).val() == 'EVENTO'){
			  $(this).after('SINGOLO');
		  }else{
			  $(this).after($(this).val());
		  }
	  });			  
});

$(document).on( "click", "#chiudievento", function() {
	if(disableInputScheda == false){	
		alertify.confirm("<i class=\"icon-errore\" style=\"font-size:64px; color:#a66300;\"></i><br><br>Sei sicuro di voler procedere?<br/>Se torni indietro senza salvare, perderai i dati immessi.").set({
		title:window.mexJS['js.tipologia.attenzione']}).set('labels', {ok:'Si', cancel:'No'}).set('onok', function(){
			$('#overlay').show();
			location.reload();
		}).set('oncancel', function(){
		});	
	}else{
		$('#overlay').show();
		location.reload();
	}
  });


//remove rch validazione
$(document).on( "change", "#boxProdotto input, #boxProdotto textarea", function() {
	if(	$('#boxProdotto').find('label.error:visible').length == 0){			
		$('#prodottoV-tab').removeClass('rch');
	}
	if($('ul#pubblTab.nav.nav-tabs>li').find('a.nav-link.rch').length == 0){
		$('#labelValid').hide();
	}
});

//$(document).on( "change", "#videoAggVIPbox input, #videoAggVIPbox textarea", function() {
//	if(	$('#videoAggVIPbox').find('label.error.active').length == 0){			
//		$('#videoVIP-tab').removeClass('rch');
//		$('#mediaV-tab').removeClass('rch');
//	}
//	if($('ul#pubblTab.nav.nav-tabs>li').find('a.nav-link.rch').length == 0){
//		$('#labelValid').hide();
//	}
//});

function strip(html)
{
   var tmp = document.createElement("DIV");
   tmp.innerHTML = html;
   return tmp.textContent||tmp.innerText;
}

var lastorder=0;

function flastorder(){
	let listorder = [0];		
	
	$('.contaimmaginivip').find('input[name$=".ordine"]').each(function( index ) {			  
		 var ordine = $(this).val();		 
		 if(ordine !=''){
			 listorder.push(ordine);
		 }
	 });	
	$('.contaimmaginivipagg').find('input[name$=".ordine"]').each(function( index ) {			  
		 var ordine = $(this).val();		 
		 if(ordine !=''){
			 listorder.push(ordine);
		 }
	 });	
	lastorder = Math.max.apply(Math, listorder);
	lastorder = lastorder+1;
}

var lastorderallegati = 0;
function flastorderallegati(){
	let listorderallegati = [0];
	$('.contadocumentivip').find('input[name$=".ordineNumerico"]').each(function( index ) {			  
		 var ordine = $(this).val();		 
		 if(ordine !=''){
			 listorderallegati.push(ordine);
		 }
	 });
	$('.contadocumentivipagg').find('input[name$=".ordineNumerico"]').each(function( index ) {			  
		 var ordine = $(this).val();		 
		 if(ordine !=''){
			 listorderallegati.push(ordine);
		 }
	 });
	lastorderallegati = Math.max.apply(Math, listorderallegati);
	lastorderallegati = lastorderallegati+1;
}

var lastorderaltro = 0;
function flastorderaltro(){
	let listorderaltro = [0];
	$('.contalinkvip').find('input[name$=".ordineNumerico"]').each(function( index ) {			  
		 var ordine = $(this).val();		 
		 if(ordine !=''){
			 listorderaltro.push(ordine);
		 }
	 });
	$('.contalinkvipagg').find('input[name$=".ordineNumerico"]').each(function( index ) {			  
		 var ordine = $(this).val();		 
		 if(ordine !=''){
			 listorderaltro.push(ordine);
		 }
	 });
	lastorderaltro = Math.max.apply(Math, listorderaltro);
	lastorderaltro = lastorderaltro+1;
}


$(document).ready(function() {		
	flastorder();
	flastorderallegati();
	flastorderaltro();
	$(".note-editable").each(function( index ) {
		$(this).keyup();
	});
});

