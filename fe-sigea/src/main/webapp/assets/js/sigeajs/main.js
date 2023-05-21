alertify.defaults.maintainFocus = false;

Selectize.define( 'no_results', function( options ) {
    var self = this;

    options = $.extend({
        message: 'Nessun risultato disponibile',

        html: function(data) {
            return (
                '<div class="selectize-dropdown ' + data.classNames + ' dropdown-empty-message">' +
                    '<div class="selectize-dropdown-content">' + data.message + '</div>' +
                '</div>'
            );
        }
    }, options );

    self.displayEmptyResultsMessage = function () {
        this.$empty_results_container.css( 'top', this.$control.outerHeight() );
        this.$empty_results_container.show();
    };

    self.refreshOptions = (function () {
        var original = self.refreshOptions;

        return function () {
            original.apply( self, arguments );
            this.hasOptions ? this.$empty_results_container.hide() :
                this.displayEmptyResultsMessage();
        }
    })();

    self.onKeyDown = (function () {
        var original = self.onKeyDown;

        return function ( e ) {
            original.apply( self, arguments );
            if ( e.keyCode === 27 ) {
                this.$empty_results_container.hide();
            }
        }
    })();

    self.onBlur = (function () {
        var original = self.onBlur;

        return function () {
            original.apply( self, arguments );
            this.$empty_results_container.hide();
        };
    })();

    self.setup = (function() {
        var original = self.setup;
        return function() {
            original.apply(self, arguments);
            self.$empty_results_container = $( options.html( $.extend( {
                classNames: self.$input.attr( 'class' ) }, options ) ) );
            self.$empty_results_container.insertBefore( self.$dropdown );
            self.$empty_results_container.hide();
        };
    })();
});

function creaEventoForm(){
	$('#overlay').show();
	$.ajax({
        type: "GET",
        url: context+"/creazioneEvento",
        success: function(response) {
        	inizializzazioneSchermataEvento(response);
        	gestisciPermessiInterfaccia(response.statoId, response.ownerId, response.ownerIdUltimaModifica, window.idUtenteCorrente, 'EVENTO', response.fgValidazionePregressa, window.idEntita, null);
        	//$('html, body').scrollTop($('#divCreazione').offset().top);
        	//recuperoProgetti(response);
            $('#overlay').hide();
        }
    });
}

function inizializzaValidatore() {
	
	$('#formEventCreating').validate({
		ignore: [],
		onkeydown: true,
		errorPlacement: function(error, element) { error.appendTo(element.parent('div')); },
		invalidHandler: function() {
			setTimeout(function() {
				$('a.rch').removeClass('rch');
			//	$('.selectized.error').siblings('div.scegliere').attr('style', 'color: #c10512 !important');
				$('.selectized.error').siblings('div.selectize-control.form-control.Input-text').attr('style', 'border: 1px solid #c10512 !important');
				var validatePane = $('input.error, textarea.error, select.error').parents('.tab-pane').each(function() {
					var id = $(this).attr('id');
					if(id) {
						var ch = $('[href^="#' + id + '"]').text().trim();
						if (id != "itbox" && id != "immaginibox" && id != "documentibox" && id != "videobox"){
							$('#labelValid').show();
							 $('html, body').scrollTop(0);
							//$('#labelValidazioni').removeAttr('hidden');
							//$('#labelValidazioni').append("<br>"+ch);
							window.scrollTo(0,0);
							//	alertify.error('Devi compilare i campi obbligatori di "'+ ch +'" per proseguire.');
						}
						$('[href^="#' + id + '"]').addClass('rch');	
						
						
						if(	$('#immaginibox').find('label.error').length != 0){			
							 $('#immagini-tab').addClass('rch');
							
							//console.log($('#immaginibox').find('label.error').length+"---immagini");
						}
						if(	$('#documentibox').find('label.error').length != 0){			
							$('#documenti-tab').addClass('rch');
							//console.log($('#documentibox').find('label.error').length+"---documenti");
						}
						
						if(	$('#videobox').find('label.error').length != 0){			
							$('#video-tab').addClass('rch');
							//console.log($('#videobox').find('label.error').length+"---video");
						}
						
						
						
						
						var countImmagine = $('[name$="immagineId"]').not("[name*='_PH_']").length;
							if(countImmagine == 0 || $('#documentibox').find('label.error').length != 0 ||
									$('#documentibox').find('label.error').length != 0 || $('#videobox').find('label.error').length != 0){
								//$('#immagini-tab').addClass('rch');		
								$('#media-tab').addClass('rch');
								$('#labelValid').show();	
								$('html, body').scrollTop(0);
							} 					
						
							
							
					} else {
						//TODO
					}
				});
			});
		}
	});
	$("#formEventCreating").validate();

}
/*
function recuperoProgetti(response){
}

function recuperoTuttiProgetti(){
}
*/
function visualizzaEvento(idEvento,stato) {
	
	window.sessionStorage.setItem("jsondatatable",JSON.stringify($('#formEventFilter').serializeFormJSON()));
	window.sessionStorage.setItem("pageidmenu",idmenu);
	
	$(".tooltip").tooltip("hide");
	$('#overlay').show();
	
	var urlCreaEvento = context + "/creazioneEvento";

	var urlGetEvento = context + "/getEvento?idEvento=" + idEvento;
	
	var urlLockEvento = context + '/lockEvento?idEvento=' + idEvento;
	
	var isErrorInLavorazione=false;
	//console.log("Effettuando callback false lockEvento");
	$.ajax({ // Gestione lock
		type: 'POST',
		url: urlLockEvento,
		success: function() {
			if (stato=='In attesa di valutazione' || stato=='In nuova valutazione'){
				$.ajax({
					async: false,
			        type: "PUT",
			        url: context+"/saveEventoInLavorazione?idEvento=" + idEvento,
					contentType: 'application/json',
			        success: function(response1) {   
			        	//console.log("Salvato Evento " +idEvento+ " da Valutazione in Lavorazione");
			        },
			        error: function(error){
						$('#overlay').hide();
						if (error.status == 409){
			    			alertify.alert(window.mexJS['js.tipologia.attenzione'], "<img src=\"" + context + "/assets/images/PERICOLO.svg\"><br/><br/><br/>"+window.mexJS['js.risorsaoccupata']).set('label', 'Chiudi'); 
			    		}else{
			    			alertify.alert("Abbiamo riscontrato un problema", "<img src=\""+context+"/assets/images/ERRORE.svg\"><br/><br/><br/>Al momento non &egrave; possibile concludere l'operazione. Ci scusiamo e ti invitiamo a riprovare in un altro momento.").set('label', 'Chiudi'); 
			    		}
						isErrorInLavorazione=true;
			    	}
			    });
			}			
		
		if (!isErrorInLavorazione){
			$.ajax({// Caricamento json evento
				async: false,
		        type: "GET",
		        url: urlGetEvento,
		        success: function(response) {        	
		        	
					$.ajax({// Caricamento pagina crea evento
				        type: "GET",
				        url: urlCreaEvento,
				        success: function(page) {
				        	
				        	inizializzazioneSchermataEvento(page);
		
				        	if(response.locationSet) {
				        		var locationOrdinate = [];
				            	var j = 1;
				            	for(i=0; i<response.locationSet.length; i++){
					
									// Evito inizializzazione ad italia
									if(response.locationSet[i].comuneEstero){
										if(!response.locationSet[i].codNazione){
											response.locationSet[i].codNazione="";
											response.locationSet[i].nazione="";
										}
									}
									
				            		if(response.locationSet[i].fgPrincipale){
				            			locationOrdinate[0] = response.locationSet[i];
				            		} else {
				            			locationOrdinate[j] = response.locationSet[i];
				            			j++;
				            		}
				            	}
				            	response.locationSet = locationOrdinate;
				        	}
				        	//recuperoProgetti(response);				        	
				        	
				        	inizializzaValidatore();
				        	$('#formEventCreating').deserializeJSONForm(response, true);                	
				        	
				        	if(response.relazioneSet){
								$(response.relazioneSet).each(function(index, relazione){
									if(relazione.redazioneId){
										$('[data-template="relazioneSet[' + index + ']"]').hide();
									}
								});
							}
									
				        	$("#formEventCreating").validate();
				        	
				        	$('input[name$="contatto"]').not("[name*='_PH_'], [name*='sitoSet'], [name*='telefonoSet']").each(function() {
				        		$(this).rules("add", { email: true, normalizer: function(value) { return $.trim(value); }});
				        	});
				        	
				        	var data = response.dataIns.substr(0, 10);
				        	data = convertDataEngToIta(data);
				        	
				        	var entitaIdOwner = null;
				        	if(response.attivita){
								entitaIdOwner = response.attivita.attivitaId;
							}else if(response.richiestaAttivita){
								entitaIdOwner = response.richiestaAttivita.richiestaAttivitaId;
							}
							
				        	gestisciPermessiInterfaccia(response.statoId, response.ownerId, response.ownerIdUltimaModifica, window.idUtenteCorrente, response.tipo, response.fgValidazionePregressa, window.idEntita, entitaIdOwner);
				        	$("#statoCorrente").html("<strong>Stato corrente:</strong> " + response.descrizioneStato);
				        					         	
				        	window.idEntitaEventoCorrente = "" + entitaIdOwner
				        	window.idUtenteOwnerEventoCorrente =response.ownerId + "";
				        					        	
				        	if(response.statoId == "LAVORAZIONE") {
				        		var riferimentoOwnerLavorazione;
				            	if (response.nomeOwnerUltimaModifica && response.cognomeOwnerUltimaModifica){
				            		riferimentoOwnerLavorazione = response.nomeOwnerUltimaModifica + " " + response.cognomeOwnerUltimaModifica;
				            	}else{
				            		riferimentoOwnerLavorazione = response.usernameOwnerUltimaModifica;
				            	}
				            	$("#utenteLavorazione").text("(da parte di "+riferimentoOwnerLavorazione+")");
				        	}
				        	
				        	if(response.ultimeNote && response.ultimeNote != null && response.ultimeNote != ""){
				        	                		
				        		$("#noteMod").css('display','contents');
				        		$("#noteModV").show();
				        	}
				        	$("#noteMod").html("<strong>Motivo: </strong>"+response.ultimeNote);
				        	$("#idEvent").html("<strong>ID:</strong> " + response.eventoId);
				        	
				        	$("#titoloEvent").html(response.datiGenerali.titoloMulti.IT);
				        	
				        	if(response.immagineSet && response.immagineSet.length > 0 ){                	
				        		var imageUrl = window.context+"/getFile/"+response.eventoId+"/immagine/"+response.immagineSet[0].immagineId+"."+response.immagineSet[0].estensione;                	               	               	
				        		$(".immagineventoblocco").css('background', 'url('+imageUrl+')');
				        	}	        	
				        	
				        	$('#riassuntoButton').attr('href', context+'/getRiassunto/'+ response.eventoId);
				        	
				        	var riferimentoOwner;
				        	if (response.nomeOwner && response.cognomeOwner){
				        		riferimentoOwner = response.nomeOwner + " " + response.cognomeOwner;
				        	}else{
				        		riferimentoOwner = response.usernameOwner;
				        	}
				        	
				        	if(response.emailOldOwner!== undefined && response.emailOldOwner!=''){
				        		$("#utenteOldOwner").html("<strong>Utente PugliaEvents:</strong> " + response.emailOldOwner);
				        		$("#divOldOwner").show();
				        	}
				        	
				        	var invalidazione = "";
				        	
				        	if(response.attivita && response.attivita.denominazione != null && response.attivita.denominazione != "") {
				        		var verifica = 'ok';
				        	}else{
				        		var verifica = 'ko';
				        	}
				        	
				        	if(response.richiestaAttivita && response.richiestaAttivita.denominazione != null  && verifica == 'ko'){
				        		invalidazione = " - In attesa di validazione";
				        	}
				        	
				        	if(response.attivita && response.attivita.denominazione != null && response.attivita.denominazione != "") {
				        		$("#utenteOwner").html("<strong>Creato da:</strong> " + riferimentoOwner + " (" + response.attivita.denominazione +")");
				        		$("#utenteOwnerData").html("<strong>Creato in data:</strong> " + data);
				        	} else if (response.richiestaAttivita && response.richiestaAttivita.denominazione != null && response.richiestaAttivita.denominazione != "") {
				        		$("#utenteOwner").html("<strong>Creato da:</strong> " + riferimentoOwner + " (" + response.richiestaAttivita.denominazione + invalidazione +")");
				        		$("#utenteOwnerData").html("<strong>Creato in data:</strong> " + data);
				        	} else {
				        		$("#utenteOwner").html("<strong>Creato da:</strong> " + riferimentoOwner);
				        		$("#utenteOwnerData").html("<strong>Creato in data:</strong> " + data);
				        	}			        	
				        	
				        	//To do visualizzazione box bandi INIZIO				        	
				 				        					        			        	
                        	var bandoId = "";
                        	var progettoId = "";
                        	var titoloBando = "";
                        	var titoloProgetto = "";
                        	var nomeOrganizzazione = "";
                        	
                        	if(typeof response.bando !== 'undefined'){ 
                        		if(typeof response.bando.bandoId !== 'undefined'){ bandoId = response.bando.bandoId; }
                        		if(typeof response.bando.titoloBando !== 'undefined'){ titoloBando = response.bando.titoloBando; }  
                        	}
                        	if(typeof response.progetto !== 'undefined'){
                        		if(typeof response.progetto.progettoId !== 'undefined'){ progettoId = response.progetto.progettoId; }                       
                        		if(typeof response.progetto.titoloProgetto !== 'undefined'){ titoloProgetto = response.progetto.titoloProgetto; }
                        		if(typeof response.progetto.progettoId !== 'undefined'){ nomeOrganizzazione = response.progetto.nomeOrganizzazione; }
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
					        						        	
					        	$('#boxbando').find("input[type='hidden']").prop("disabled", false);		
					        	
					        	
					        	$("#boxbandoaggiungi").hide();
					        	$('#boxbando').show();
				        	}else{
				        		$('#boxbando').hide();
				        		$("#boxbandoaggiungi").show();
				        	}
				        	
				        	if(response.statoId != 'BOZZA' && response.statoId !='MODIFICA'){				        		
				        		$("#azionibandi").hide();
				        		$("#boxbandoaggiungi").hide();
				        	}
				        	
				        	//To do visualizzazione box bandi FINE
				        	
				        	$('#t_revisioni').DataTable().rows.add(response.logSet).draw();
				        					        	
							$("#gestioneAccF").remove();
							$("#gestioneEvento").removeAttr('hidden');
				        								
							var logSet1=response.logSet;
		                	var validatoPrecedente=false;
		                	
		                	for (let log of logSet1)
		                	  {
		                	    if(log.descrizioneStato==='Validato')
		                	      {
		                	       validatoPrecedente = true;
		                	      }
		                	  }
		                	
		                	$('#RESPINTO').attr('disabled' ,validatoPrecedente);
							if(idmenu == "filtriGestioneRedazione.jsp"){
		    					$("#labelbreadcrumb").empty().html('Redazione eventi');
		    				}else if(idmenu == "filtriGestionePromozione.jsp"){
		    					$("#labelbreadcrumb").empty().html('Promozione eventi');
		    				}else if(idmenu == "filtriPromozione.jsp"){
		    					//$("#labelbreadcrumb").empty().html('Promozione eventi');
		    					
		    					$("#gestioneEvento").attr('hidden', true);
		    					$("#gestioneEventoPro").show();
		    					
		    					
		    				}else if(idmenu == "filtriGestioneValidazione.jsp"){
		    					$("#labelbreadcrumb").empty().html('Validazione eventi');
		    				}
				    				
				        	$.ajax({
				                type: "GET",
				                url: context+"/getPubblicazioni/" + idEvento,
				                success: function(response) {
				                	var listPubbl;
				                	for (i=0; i < response.length; i++) {
				                		var data = response[i].dataPubblicazione.substr(0,10);
				                		data = convertDataEngToIta(data) + " alle ore" +  response[i].dataPubblicazione.substr(10);
				                		var pubblicazione = response[i].nomeRedazione+" in data "+data; 
				                		//console.log(response[i]);
				                		if(listPubbl){
				                			listPubbl = listPubbl+", "+pubblicazione;
				                		} else {
				                			listPubbl = pubblicazione;
				                		}
				                		
				                	}
				                	if(listPubbl){
				                		$('#divPubblEff').removeAttr('hidden').find('.labelpubbdate').html(listPubbl+"<br/>");
				                	}
				                	
				                	$('#overlay').hide();
				                }
				            });
				        }
				    });
		        },
		        error: function(error){
					$('#overlay').hide();
		    		if (error.status == 409){
		    			alertify.alert(window.mexJS['js.tipologia.attenzione'], "<img src=\"" + context + "/assets/images/PERICOLO.svg\"><br/><br/><br/>"+window.mexJS['js.risorsaoccupata']).set('label', 'Chiudi'); 
		    		}
		    	}
		    });	
		 }
		},
		error: function(error){
			$('#overlay').hide();
    		if (error.status == 409){
    			alertify.alert(window.mexJS['js.tipologia.attenzione'], "<img src=\"" + context + "/assets/images/PERICOLO.svg\"><br/><br/><br/>"+window.mexJS['js.risorsaoccupata']).set('label', 'Chiudi'); 
    		}
    	}
	});
}

function inizializzazioneSchermataEvento(response) {
    $("#divCreazione").html(response);
    document.getElementById("divCreazione").style.display = 'block';
    document.getElementById("divFiltro").style.display = 'none';
    $("#divFiltro").empty();
    document.getElementById("divSchedaAccessoria").style.display = 'none';
    $("#divSchedaAccessoria").empty();
    $('#formEventCreating').initializeBindingOnForm();
    $("[data-addbutton='locationSet']").click();
    $("[data-addbutton='periodoSet']").click();
    $("[data-addbutton='telefonoSet']").click();
    $("[data-addbutton='emailSet']").click();
    $("[data-addbutton='sitoSet']").click();
    $("[data-deletebutton='locationSet[0]']").parent('div').remove();
    $("[data-deletebutton='periodoSet[0]']").parent('div').remove();
    $("[data-deletebutton='telefonoSet[0]']").parent('div').remove();
    $("[data-deletebutton='emailSet[0]']").parent('div').remove();
    $("[data-deletebutton='sitoSet[0]']").parent('div').remove();
    $("[name='locationSet[0].fgPrincipale']").val("true");    
}

var readOnly = false;

function gestisciPermessiInterfaccia(statoCorrente, idOwner, idWorker, idUtenteCorrente, tipologia, fgValidazionePregressa, idEntitaCorrente, idEntitaOwner) {
	
	if(fgValidazionePregressa){
		$("#RESPINTO").hide();
	}
	
	if(statoCorrente == 'VALIDATO' && window.redazioniListUtenteCorrente && window.redazioniListUtenteCorrente.length > 0){
		$('#boxPubblicazione').removeAttr('hidden');
		for (var i = 0; i < window.redazioniList.length; i++) {
			for (var j = 0; j < window.redazioniListUtenteCorrente.length; j++) {
				if(window.redazioniList[i].redazioneId == window.redazioniListUtenteCorrente[j].redazioneId){
					$('#'+window.redazioniList[i].redazioneId+'scheda').removeAttr('hidden');
					break;
				}
			}
			
		}
	}
	if (tipologia == 'EVENTO') {
		$("[data-addbutton='locationSet']").attr("hidden", true);
		$("#myTabContent4").find('hr').attr("hidden", true);
	} else {
		$("[data-addbutton='locationSet']").removeAttr("hidden");
		$("#myTabContent4").find('hr').removeAttr("hidden");
	}
	
	if(statoCorrente == null && idOwner == null){ 
		$("#salva").attr('hidden',true);
		$("#labelEvent").attr('hidden',true);
		$("#BOZZA").removeAttr('hidden');
		$("#revisioni").addClass('hide');
		$("#location").addClass('hide');
		$("#periodo").addClass('hide');
		$("#contatti").addClass('hide');
		$("#ticket").addClass('hide');
		$("#accessibilita").addClass('hide');
		$("#media").addClass('hide');
		$("#relazioni").addClass('hide');	
		$("#titoloitalia").find('#it_titolo').attr("name","it_test");		
		$("#myTabCE").addClass('hide');
		$("#myTabCEContent").addClass('hide');
		$("#boxusersigea").hide();
		$("#inseriscievento").show();		
		
		$("#modalAggiungibando").remove();
		
		$("#chiudievento").css("margin-left", "0");
		$("#gestioneEvento").attr('hidden', true);		
		$("#labeldettaglio").hide();		
		var abst = $('#it_abstract').siblings('.scegliere').text().slice(0,-4);
		var desc = $('#it_descrizione').siblings('.scegliere').text().slice(0,-4);
		$('#it_abstract').siblings('.scegliere').text(abst);
		$('#it_descrizione').siblings('.scegliere').text(desc);
	}else{
		$("#promozioneAccF").hide();
		$("#promozioneAccF").hide();
		$("#gestioneEvento").removeAttr('hidden');		
		$('#inseriscievento').remove();
	}
	
	if(window.flagPrm["hideTabRevisioni"]) {
		$("#revisioni").addClass('hide');
	}

	if(statoCorrente){
		var configurazione = null;
		for(var i=0; i<window.statiEvento.length; i++) {
			if(window.statiEvento[i].statoId == statoCorrente) {
				configurazione = window.statiEvento[i].configurazioneStato;
			}
		}
		
		
		var visibleButton = [];
		for(var i=0; i<configurazione.statiRaggiungibili.length; i++) {
			var removeButton = false;
			if(configurazione.statiRaggiungibili[i].permessi["autorizzato"] && !window.flagPrm["cambioStato" + configurazione.statiRaggiungibili[i].statoId]){
				removeButton = true;
			}
	
			if(configurazione.statiRaggiungibili[i].permessi["mustBeOwner"] && idEntitaOwner != idEntitaCorrente){
				removeButton = true;
			}
			if(configurazione.statiRaggiungibili[i].permessi["mustNotBeOwner"] && idEntitaOwner == idEntitaCorrente){
				removeButton = true;
			}
			
			if(configurazione.statiRaggiungibili[i].permessi["mustBeWorker"] && idWorker != idUtenteCorrente){
				removeButton = true;
			}
			if(configurazione.statiRaggiungibili[i].permessi["mustNotBeWorker"] && idWorker == idUtenteCorrente){
				removeButton = true;
			}
			
			if(!removeButton){
				$("#"+configurazione.statiRaggiungibili[i].statoId).removeAttr('hidden');
				visibleButton.push(configurazione.statiRaggiungibili[i]);
			}
		}	
		
		var overiddenButtons = [];
		for(var i = 0; i<visibleButton.length; i++) {
			if(visibleButton[i].statiSovrascritti){
				for(var j = 0; j<visibleButton[i].statiSovrascritti.length; j++) {
					overiddenButtons.push(visibleButton[i].statiSovrascritti[j]);
				}
			}
		}
		
		for(var i = 0; i<overiddenButtons.length; i++) {
			$("#"+overiddenButtons[i]).attr("hidden", true);
			//console.log("#"+overiddenButtons[i]);
		}

		readOnly = false;
		if(statoCorrente != null) {
			if(!(window.flagPrm["writableNucleo" + statoCorrente]) && idEntitaOwner != idEntitaCorrente){
				readOnly = true;
			}else if(idEntitaOwner == idEntitaCorrente && !configurazione.isEditableByOwner){
				readOnly = true;
			}else if(configurazione.isWorkerExclusive && idWorker != idUtenteCorrente){
				readOnly = true;
				//AGGIUNTO IF PER CAMBIO LABEL BOTTONE. Per ripristinare isWorkerExclusive, cambiare a true il valore della chiave omonima where stato_id='LAVORAZIONE' e nient'altro
			}else if(statoCorrente=='LAVORAZIONE' && idWorker != idUtenteCorrente){
				$("#salva").text(window.mexJS['js.button.prendiincarico']);
				$("#myTabCEContent :input").not('#permettiButton :input').prop('disabled', true);
				
				for (i=0; i < $("#myTabCEContent .selectized").length; i++) {
					$("#myTabCEContent .selectized").eq(i).selectize()[0].selectize.disable();
				}
				$("div#mybutton").addClass('disabled');	
				$("#myTabCEContent,	#boxmobileFilter, #boxmobilemyTabFE, #boxmobileMenu, #boxmobileAllegato, #boxmobileFilter2, #boxmobileAllegatoVip,#boxmobileLingua, #boxmobileLinguaG, #boxmobileLinguaVid, #boxmobileLinguaImg, #boxmobileLinguaDoc, #boxmobileLinguaVIPG, #boxmobileLinguaImgVip, #boxmobileLinguaDocVip, #boxmobileLinguaVidVip").find('select').prop('disabled', false);

				$("#aggiungibando").hide();
				$("#modificabando").hide();
				$("#eliminabando").hide();

			}
		}
		
		if(readOnly){
			$("#myTabCEContent :input").not('#permettiButton :input').prop('disabled', true);
			
			for (i=0; i < $("#myTabCEContent .selectized").length; i++) {
				$("#myTabCEContent .selectized").eq(i).selectize()[0].selectize.disable();
			}
			
			$("div#mybutton").addClass('disabled');			
			$("#salva").attr("hidden", true);
			
			$("#myTabCEContent,	#boxmobileFilter, #boxmobilemyTabFE, #boxmobileMenu, #boxmobileAllegato, #boxmobileFilter2, #boxmobileAllegatoVip,#boxmobileLingua, #boxmobileLinguaG, #boxmobileLinguaVid, #boxmobileLinguaImg, #boxmobileLinguaDoc, #boxmobileLinguaVIPG, #boxmobileLinguaImgVip, #boxmobileLinguaDocVip, #boxmobileLinguaVidVip").find('select').prop('disabled', false);
						
			$("#aggiungibando").hide();
			$("#modificabando").hide();
			$("#eliminabando").hide();
			
		}
	}
}

function bothVerified(element)
{
	var name;
	if(element.name.includes("telefonoSet")){
		name= element.name.replace("telefono", "email");
		}else if(element.name.includes("emailSet")){
			name= element.name.replace("email", "telefono");
		}
	
	$('[name="'+name+'"]').rules("add", { required: false, normalizer: function(value) { return $.trim(value); }});			
 	$('[name="'+name+'"]').removeClass('error');
	$('label[for="'+name+'"]').hide();

}


jQuery.validator
.addMethod(
		"lengthRU300",
		function(value, element) {		
			
			var text = ConvertCharacter(value);
		
			if (text.length > 300) {
				return false;
			} else {
				return true;
			}
		},  "* superato limite consentito di 300 caratteri");


jQuery.validator
.addMethod(
		"lengthRU4000",
		function(value, element) {		
			
			var text = ConvertCharacter(value);
		
			if (text.length > 4000) {
				return false;
			} else {
				return true;
			}
		},  "* superato limite consentito di 4000 caratteri");




function gestValidatore(stato) {
	$('#labelValidazioni').text(window.mexJS['js.error.insCampi']);
	$('#labelValid').attr('hidden', true);
	$('#labelValidazioni').attr('hidden', true);	
	$('html, body').scrollTop(0);

	$("#formEventCreating").validate();
	$("#it_titolo").rules("add", { required: true, normalizer: function(value) { return $.trim(value); }});
	
	if((stato && stato == "VALIDATO")||(stato && stato == "RIVALIDATO") ||(stato && stato == "VALUTAZIONE")||(stato && stato == "RIVALUTAZIONE")) {
		$("#it_abstract").rules("add", { required: true});
		$("#it_descrizione").rules("add", { required: true, normalizer: function(value) { return $.trim(value); }});
		
		// Validazione russa
		$("#ru_titolo").rules("add", { lengthRU300: true, normalizer: function(value) { return $.trim(value); }});
		$("#ru_abstract").rules("add", { lengthRU300: true, normalizer: function(value) { return $.trim(value); }});
		$("#ru_descrizione").rules("add", { lengthRU4000: true, normalizer: function(value) { return $.trim(value); }});
				
		var emailC = $('[data-addbutton="emailSet"]').attr('data-counter');
		var telefonoC = $('[data-addbutton="telefonoSet"]').attr('data-counter');
		var texC = emailC > telefonoC ? emailC: telefonoC;
		var indiceC = parseInt(texC, 10);
		for(var i=0; i<=indiceC; i++) {
			if( ($('[name="emailSet['+i+'].contatto"]').val() == "" || $('[name="emailSet['+i+'].contatto"]').val() == undefined )
					&& ($('[name="telefonoSet['+i+'].contatto"]').val() == "" || $('[name="telefonoSet['+i+'].contatto"]').val() == undefined)) {
				$('[name="emailSet['+i+'].contatto"]').rules("add", { required: true, normalizer: function(value) { return $.trim(value); }});
				$('[name="telefonoSet['+i+'].contatto"]').rules("add", { required: true});			
				} 
		}
		
		var documentD = $('[data-addbutton="documentoSet"]').attr('data-counter');
		var indiceD = parseInt(documentD, 10);
		for(var i=0; i<=indiceD; i++) {
			$('[name="documentoSet['+i+'].titoloMulti.IT"]').rules("add", { required: true, normalizer: function(value) { return $.trim(value); }});	
			$('[name="documentoSet['+i+'].ordine"]').rules("add", { required: true, orderValidDocumenti: true, normalizer: function(value) { return $.trim(value); }});
		}
		
		var documentO = $('[data-addbutton="immagineSet"]').attr('data-counter');
		var indiceO = parseInt(documentO, 10);
		for(var i=0; i<=indiceO; i++) {
			$('[name="immagineSet['+i+'].ordine"]').rules("add", { required: true, orderValid: true, normalizer: function(value) { return $.trim(value); }});				
		}
		
		$('input[name$="link"]').not("[name*='_PH_'], [name*='location']").each(function() {
			$(this).rules("add", { required: true, normalizer: function(value) { return $.trim(value); }});
		});
		
		var linkL = $('[data-addbutton="linkSet"]').attr('data-counter');
		var indiceLinkL = parseInt(linkL, 10);
		for(var i=0; i<=indiceLinkL; i++) {
			$('[name="linkSet['+i+'].titoloMulti.IT"]').rules("add", { required: true, normalizer: function(value) { return $.trim(value); }});	
			$('[name="linkSet['+i+'].ordine"]').rules("add", { required: true, orderValidLink: true, normalizer: function(value) { return $.trim(value); }});
		}
		
		$('input[name$="indirizzo"]').not("[name*='_PH_']").each(function() {
			$(this).rules("add", { required: true, normalizer: function(value) { return $.trim(value); }});
		});
		$('input[name$="cap"]').not("[name*='_PH_']").each(function() {
			$(this).rules("add", { minlength: 5, normalizer: function(value) { return $.trim(value); }});
		});
		$('[name$="tipologiaMIBACT"]').rules("add", { required: true, normalizer: function(value) { return $.trim(value); }});
				
		var texL = $('[data-addbutton="locationSet"]').attr('data-counter');
		var indiceL = parseInt(texL, 10);
		for(var i=0; i<=indiceL; i++) {
			if($('[name="locationSet['+i+'].nazione"]').val() == window.italia.nazione) {
				$('input[name="locationSet['+i+'].comuneEstero"]').rules("add", { required: false, normalizer: function(value) { return $.trim(value); }});
				$('[name="locationSet['+i+'].regione"]').rules("add", { required: true, normalizer: function(value) { return $.trim(value); }});
				$('[name="locationSet['+i+'].provincia"]').rules("add", { required: true, normalizer: function(value) { return $.trim(value); }});
				$('[name="locationSet['+i+'].comune"]').rules("add", { required: true, normalizer: function(value) { return $.trim(value); }});
			} else {
				if(!$('[name="locationSet['+i+'].nazione"]').val()){
					$('[name="locationSet['+i+'].nazione"]').rules("add", { required: true, normalizer: function(value) { return $.trim(value); }});
				}
				$('input[name="locationSet['+i+'].comuneEstero"]').rules("add", { required: true, normalizer: function(value) { return $.trim(value); }});
				$('[name="locationSet['+i+'].regione"]').rules("add", { required: false, normalizer: function(value) { return $.trim(value); }});
				$('[name="locationSet['+i+'].provincia"]').rules("add", { required: false, normalizer: function(value) { return $.trim(value); }});			
				$('[name="locationSet['+i+'].comune"]').rules("add", { required: false, normalizer: function(value) { return $.trim(value); }});
			}
		}
		
		var texP = $('[data-addbutton="periodoSet"]').attr('data-counter');
		var indiceP = parseInt(texP, 10);
		for(var i=0; i<=indiceP; i++) {
			$('input[name="periodoSet['+i+'].dataDa"]').rules("add", { required: true, normalizer: function(value) { return $.trim(value); }});
			
			if($('input[name="periodoSet['+i+'].dataSecca"][value="true"]').prop("checked")){
				$('input[name="periodoSet['+i+'].dataA"]').rules("add", { required: false, normalizer: function(value) { return $.trim(value); }});
			} else {
				$('input[name="periodoSet['+i+'].dataA"]').rules("add", { required: true, normalizer: function(value) { return $.trim(value); }});
			}
		}
	}	
}


function controlloVIP(){	
			  	  
			  
			  if(orderValidVip() == false){	
				  var listorderImgD = GetDuplicates(listorderImg);
				  $('#immaginiVIPbox').find('input[name$="].ordine"]').each(function( index ) {
					  if( listorderImgD.indexOf($(this).val()) != -1 ){					  
						  $(this).addClass('error');
						  $(this).next('label').empty().html('* inserire valori non duplicati').addClass('active').show();	
					  }						  
				  });					  
				  $('#immaginiAggVIPbox').find('input[name$="].ordine"]').each(function( index ) {	
					  if( listorderImgD.indexOf($(this).val()) != -1 ){
						  $(this).addClass('error');
						  $(this).next('label').empty().html('* inserire valori non duplicati').addClass('active').show();		
					  }
				  });	
			  }else{		
				  
				  $('#immaginiAggVIPbox').find('input[name$="].ordine"]').each(function( index ) {	
					  $(this).removeClass('error');
					  $(this).next('label').empty().removeClass('active').hide();			
				  });	
				  
				  $('#immaginiAggVIPbox').find('input[name$="].ordine"]').each(function( index ) {	
					  $(this).removeClass('error');
					  $(this).next('label').empty().removeClass('active').hide();			
				  });	
				  
				  
				  
				  $('#immaginiVIPbox').find('input[name$="].ordine"]').each(function( index ) {			  
					  if($(this).val() == '' || $(this).val() == 0){
						  $(this).addClass('error');
						  $(this).next('label').empty().html('* campo obbligatorio. Deve essere maggiore di 0.').addClass('active').show();
					  }else{
						  $(this).removeClass('error');
						  $(this).next('label').removeClass('active').hide();
					  }
				  });	
				  
				  $('#immaginiAggVIPbox').find('input[name$="].ordine"]').each(function( index ) {			  
					  if($(this).val() == '' || $(this).val() == 0){
						  $(this).addClass('error');
						  $(this).next('label').empty().html('* campo obbligatorio. Deve essere maggiore di 0.').addClass('active').show();
					  }else{				  
						  $(this).removeClass('error');
						  $(this).next('label').removeClass('active').hide();					  
					  }
				  });
			  }	
			  
			  
			  if(orderValidDocumentiVip() == false){		
				  var listorderDocD = GetDuplicates(listorderDoc);
				  $('#documentiVIPbox').find('input[name$="].ordineNumerico"]').each(function( index ) {	
					  
					  if( listorderDocD.indexOf($(this).val()) != -1 ){	
						  $(this).addClass('error');
						  $(this).next('label').empty().html('* inserire valori non duplicati').addClass('active').show();	
					  }
				  });					  
				  $('#documentiAggVIPbox').find('input[name$="].ordineNumerico"]').each(function( index ) {	
					  					  
					  if( listorderDocD.indexOf($(this).val()) != -1 ){	
						  $(this).addClass('error');
						  $(this).next('label').empty().html('* inserire valori non duplicati').addClass('active').show();	
					  }
				  });	
			  }else{	
				  
				  $('#documentiVIPbox').find('input[name$="].ordineNumerico"]').each(function( index ) {	
					  $(this).removeClass('error');
					  $(this).next('label').empty().removeClass('active').hide();			
				  });	
				  
				  $('#documentiAggVIPbox').find('input[name$="].ordineNumerico"]').each(function( index ) {	
					  $(this).removeClass('error');
					  $(this).next('label').empty().removeClass('active').hide();			
				  });	
				  
				  
				  
				  $('#documentiVIPbox').find('input[name$="].ordineNumerico"]').each(function( index ) {			  
					  if($(this).val() == '' || $(this).val() == 0){
						  $(this).addClass('error');
						  $(this).next('label').empty().html('* campo obbligatorio. Deve essere maggiore di 0.').addClass('active').show();
					  }else{			
						  $(this).removeClass('error');
						  $(this).next('label').removeClass('active').hide();
					  }
				  });	
				  
				  $('#documentiAggVIPbox').find('input[name$="].ordineNumerico"]').each(function( index ) {			  
					  if($(this).val() == '' || $(this).val() == 0){
						  $(this).addClass('error');
						  $(this).next('label').empty().html('* campo obbligatorio. Deve essere maggiore di 0.').addClass('active').show();
					  }else{
						  $(this).removeClass('error');
						  $(this).next('label').removeClass('active').hide();
					  }
				  });	
			  }	
		
			  if(orderValidLinkVip() == false){	
				  var listorderLinkD = GetDuplicates(listorderLink);
				  $('#videoVIPbox').find('input[name$="].ordineNumerico"]').each(function( index ) {	
					  if( listorderLinkD.indexOf($(this).val()) != -1 ){	
						  $(this).addClass('error');
						  $(this).next('label').empty().html('* inserire valori non duplicati').addClass('active').show();		
					  }
				  });					  
				  $('#videoAggVIPbox').find('input[name$="].ordineNumerico"]').each(function( index ) {	
					  if( listorderLinkD.indexOf($(this).val()) != -1 ){	
						  $(this).addClass('error');
						  $(this).next('label').empty().html('* inserire valori non duplicati').addClass('active').show();	
					  }
				  });	
			  }else{				  
				  
				  $('#videoVIPbox').find('input[name$="].ordineNumerico"]').each(function( index ) {	
					  $(this).removeClass('error');
					  $(this).next('label').empty().removeClass('active').hide();			
				  });	
				  
				  $('#videoAggVIPbox').find('input[name$="].ordineNumerico"]').each(function( index ) {	
					  $(this).removeClass('error');
					  $(this).next('label').empty().removeClass('active').hide();			
				  });	
				  		
				  $('#videoVIPbox').find('input[name$="].ordineNumerico"]').each(function( index ) {			  
					  if($(this).val() == '' || $(this).val() == 0){
						  $(this).addClass('error');
						  $(this).next('label').empty().html('* campo obbligatorio. Deve essere maggiore di 0.').addClass('active').show();
					  }else{
						  $(this).removeClass('error');
						  $(this).next('label').removeClass('active').hide();
					  }
				  });	
				  
				  $('#videoAggVIPbox').find('input[name$="].ordineNumerico"]').each(function( index ) {			  
					  if($(this).val() == '' || $(this).val() == 0){
						  $(this).addClass('error');
						  $(this).next('label').empty().html('* campo obbligatorio. Deve essere maggiore di 0.').addClass('active').show();
					  }else{
						  $(this).removeClass('error');
						  $(this).next('label').removeClass('active').hide();
					  }
				  });	 
				   			
			  }		
			  
				$('#videoAggVIPbox').find('input[name$="].titoloMulti.IT"]').each(function() {
					if( $(this).val() != ''){
						$(this).removeClass('error');
						$(this).closest('div').find('.error').removeClass('active').hide();						
				
					}else{							
						$(this).addClass('error');
						$(this).closest('div').find('.error').addClass('active').show();					
					}	
				});
				
				$('#videoAggVIPbox').find('input[name$="].link"]').each(function() {
					if( $(this).val() != ''){
						$(this).removeClass('error');
						$(this).closest('div').find('.error').removeClass('active').hide();							
					}else{
						$("#mediaV-tab").addClass('rch');
						//$("#videoVIP-tab").addClass('rch');	
						$(this).addClass('error');
						$(this).closest('div').find('.error').addClass('active').show();
				
					}	
				});	 
			  
			
			  var contaerrori = 0;
				$('.contaimmaginivip').find('label.error.active').each(function( index ) {			  
					contaerrori++;
				 });
			    
				$('.contaimmaginivipagg').find('label.error.active').each(function( index ) {			  
					contaerrori++;
				 });
				
			    var contaerrori2 = 0;   
				$('.contadocumentivip').find('label.error.active').each(function( index ) {			  
					contaerrori2++;
				 });
			    
				$('.contadocumentivipagg').find('label.error.active').each(function( index ) {			  
					contaerrori2++;
				 });
				
			    var contaerrori3 = 0; 
				$('.contalinkvip').find('label.error.active').each(function( index ) {			  
					contaerrori3++;
				 });
			    
				$('.contalinkvipagg').find('label.error.active').each(function( index ) {			  
					contaerrori3++;
				 });
			    
			    if(contaerrori >0){
			    	//$("#mediaV-tab").addClass('rch');	
			    	$("#immaginiVIP-tab").addClass('rch');	
			    }else{
			     	//$("#mediaV-tab").removeClass('rch');	
			    	$("#immaginiVIP-tab").removeClass('rch');	
			    }   
			    if(contaerrori2 >0){
			    	//$("#mediaV-tab").addClass('rch');	
			    	$("#documentiVIP-tab").addClass('rch');	
			    }else{
			     	//$("#mediaV-tab").removeClass('rch');	
			    	$("#documentiVIP-tab").removeClass('rch');	
			    } 
			    
			    if(contaerrori3 >0){
			    	//$("#mediaV-tab").addClass('rch');	
			    	$("#videoVIP-tab").addClass('rch');	
			    }else{
			     	//$("#mediaV-tab").removeClass('rch');	
			    	$("#videoVIP-tab").removeClass('rch');	
			    } 
			    
				if(contaerrori > 0 || contaerrori2 > 0 || contaerrori3 > 0){
					$("#mediaV-tab").addClass('rch');
				}
				
				if(contaerrori == 0 && contaerrori2 == 0 && contaerrori3 == 0){
					$("#mediaV-tab").removeClass('rch');
				}
				
				if($('ul#pubblTab.nav.nav-tabs>li').find('a.nav-link.rch').length == 0){
				    $('#labelValid').hide(); 
				}	
}

function gestValidatoreVip(stato) {
	if( (stato && stato == "PUBBLICATO")||(stato && stato == "REDATTO")) {						
		var open = controlloVIP();
	}	
}

function enableAll(){
	$("#myTabCEContent :input").prop('disabled', false);
	for (i=0; i < $("#myTabCEContent .selectized").length; i++) {
		$("#myTabCEContent .selectized").eq(i).selectize()[0].selectize.enable();
	}
}

$(document).ready(function(){
    $('.accordionF-header').on('click', function(event){
    	var link = $(this).find(".frecciafiltro");    	
    	var classblocco = $(this).attr("class");    	
    	var faq = $(this);    	
        if (classblocco == 'accordionF-header active') {
            link.attr("src", context+"/assets/images/FRECCIA_GIU.svg");
        }else{
        	  link.attr("src", context+"/assets/images/FRECCIA_SU.svg");
        }
    });     
});

/*
function replaceAll(string, search, replace) {
	  return string.split(search).join(replace);
	}
*/

function specialcharacters(text){	
	text = text.replace(/‘/g,'&rsquo;');
	text = text.replace(/’/g,'&rsquo;');
	text = text.replace(/\'/g,'&rsquo;');
	text = text.replace(/\'/g,'&apos;');
	text = text.replace(/"/g,'&quot;');	
	text = text.replace(/“/g,'&quot;');
	text = text.replace(/”/g,'&quot;'); 
	return text;
}


function specialcharacterstitolo(text){	
	text = text.replace(/‘/g, '\'');
	text = text.replace(/’/g, '\'');
	text = text.replace(/“/g, '"');
	text = text.replace(/”/g, '"'); 
	return text;
}

$(document).ready(function() {	
	if(idmenu == 'home.jsp' ){
		$('#submenuMobile').find('#home').addClass('active');
	}else if(idmenu == 'filtriGestionePromozione.jsp'){
		$('#submenuMobile').find('#promozione').addClass('active');
	}else if(idmenu == 'filtriGestioneValidazione.jsp' ){
		$('#submenuMobile').find('#validazione').addClass('active');
	}else if(idmenu == 'filtriGestioneRedazione.jsp'){
		$('#submenuMobile').find('#redazione').addClass('active');
	}else if(idmenu == 'redazioneHome.jsp'){
		$('#submenuMobile').find('#redazione').addClass('active');
	}else if(idmenu == 'ricevute.jsp'){
		$('#submenuMobile').find('#ricevute').addClass('active');
	}
	
	
	if(idmenu == "filtriGestioneRedazione.jsp"){
		$("#labelbreadcrumb").empty().html('Redazione eventi');
	}else if(idmenu == "filtriGestionePromozione.jsp"){
		$("#labelbreadcrumb").empty().html('Promozione eventi');
	}else if(idmenu == "filtriGestioneValidazione.jsp"){
		$("#labelbreadcrumb").empty().html('Validazione eventi');
	}		
});

//Disable Enter Key On Forms
$("form").keypress(function(e) {
	  //Enter key
	  if (e.which == 13) {
	    return false;
	  }
});

/* funzione che si occupa di accettare tutti i caratteri utf8 fino al 255esimo e dal 1024 al 1327 */
// https://cloford.com/resources/charcodes/utf-8_latin-a.htm
function cleanString(input) {
	var output = "";
	for (var i=0; i<input.length; i++) {
		if (input.charCodeAt(i) <= 255 || (input.charCodeAt(i) >=1024 && input.charCodeAt(i) <= 1327) ) {
		output += input.charAt(i);
		}
	}
	return output;
}

function ConvertCharacter(input){
	var output = "";
	input = input.replace(/Ѐ/g,"&#1024;");
	input = input.replace(/Ё/g,"&#1025;");
	input = input.replace(/Ђ/g,"&#1026;");
	input = input.replace(/Ѓ/g,"&#1027;");
	input = input.replace(/Є/g,"&#1028;");
	input = input.replace(/Ѕ/g,"&#1029;");
	input = input.replace(/І/g,"&#1030;");
	input = input.replace(/Ї/g,"&#1031;");
	input = input.replace(/Ј/g,"&#1032;");
	input = input.replace(/Љ/g,"&#1033;");
	input = input.replace(/Њ/g,"&#1034;");
	input = input.replace(/Ћ/g,"&#1035;");
	input = input.replace(/Ќ/g,"&#1036;");
	input = input.replace(/Ѝ/g,"&#1037;");
	input = input.replace(/Ў/g,"&#1038;");
	input = input.replace(/Џ/g,"&#1039;");
	input = input.replace(/А/g,"&#1040;");
	input = input.replace(/Б/g,"&#1041;");
	input = input.replace(/В/g,"&#1042;");
	input = input.replace(/Г/g,"&#1043;");
	input = input.replace(/Д/g,"&#1044;");
	input = input.replace(/Е/g,"&#1045;");
	input = input.replace(/Ж/g,"&#1046;");
	input = input.replace(/З/g,"&#1047;");
	input = input.replace(/И/g,"&#1048;");
	input = input.replace(/Й/g,"&#1049;");
	input = input.replace(/К/g,"&#1050;");
	input = input.replace(/Л/g,"&#1051;");
	input = input.replace(/М/g,"&#1052;");
	input = input.replace(/Н/g,"&#1053;");
	input = input.replace(/О/g,"&#1054;");
	input = input.replace(/П/g,"&#1055;");
	input = input.replace(/Р/g,"&#1056;");
	input = input.replace(/С/g,"&#1057;");
	input = input.replace(/Т/g,"&#1058;");
	input = input.replace(/У/g,"&#1059;");
	input = input.replace(/Ф/g,"&#1060;");
	input = input.replace(/Х/g,"&#1061;");
	input = input.replace(/Ц/g,"&#1062;");
	input = input.replace(/Ч/g,"&#1063;");
	input = input.replace(/Ш/g,"&#1064;");
	input = input.replace(/Щ/g,"&#1065;");
	input = input.replace(/Ъ/g,"&#1066;");
	input = input.replace(/Ы/g,"&#1067;");
	input = input.replace(/Ь/g,"&#1068;");
	input = input.replace(/Э/g,"&#1069;");
	input = input.replace(/Ю/g,"&#1070;");
	input = input.replace(/Я/g,"&#1071;");
	input = input.replace(/а/g,"&#1072;");
	input = input.replace(/б/g,"&#1073;");
	input = input.replace(/в/g,"&#1074;");
	input = input.replace(/г/g,"&#1075;");
	input = input.replace(/д/g,"&#1076;");
	input = input.replace(/е/g,"&#1077;");
	input = input.replace(/ж/g,"&#1078;");
	input = input.replace(/з/g,"&#1079;");
	input = input.replace(/и/g,"&#1080;");
	input = input.replace(/й/g,"&#1081;");
	input = input.replace(/к/g,"&#1082;");
	input = input.replace(/л/g,"&#1083;");
	input = input.replace(/м/g,"&#1084;");
	input = input.replace(/н/g,"&#1085;");
	input = input.replace(/о/g,"&#1086;");
	input = input.replace(/п/g,"&#1087;");
	input = input.replace(/р/g,"&#1088;");
	input = input.replace(/с/g,"&#1089;");
	input = input.replace(/т/g,"&#1090;");
	input = input.replace(/у/g,"&#1091;");
	input = input.replace(/ф/g,"&#1092;");
	input = input.replace(/х/g,"&#1093;");
	input = input.replace(/ц/g,"&#1094;");
	input = input.replace(/ч/g,"&#1095;");
	input = input.replace(/ш/g,"&#1096;");
	input = input.replace(/щ/g,"&#1097;");
	input = input.replace(/ъ/g,"&#1098;");
	input = input.replace(/ы/g,"&#1099;");
	input = input.replace(/ь/g,"&#1100;");
	input = input.replace(/э/g,"&#1101;");
	input = input.replace(/ю/g,"&#1102;");
	input = input.replace(/я/g,"&#1103;");
	input = input.replace(/ѐ/g,"&#1104;");
	input = input.replace(/ё/g,"&#1105;");
	input = input.replace(/ђ/g,"&#1106;");
	input = input.replace(/ѓ/g,"&#1107;");
	input = input.replace(/є/g,"&#1108;");
	input = input.replace(/ѕ/g,"&#1109;");
	input = input.replace(/і/g,"&#1110;");
	input = input.replace(/ї/g,"&#1111;");
	input = input.replace(/ј/g,"&#1112;");
	input = input.replace(/љ/g,"&#1113;");
	input = input.replace(/њ/g,"&#1114;");
	input = input.replace(/ћ/g,"&#1115;");
	input = input.replace(/ќ/g,"&#1116;");
	input = input.replace(/ѝ/g,"&#1117;");
	input = input.replace(/ў/g,"&#1118;");
	input = input.replace(/џ/g,"&#1119;");
	input = input.replace(/Ѡ/g,"&#1120;");
	input = input.replace(/ѡ/g,"&#1121;");
	input = input.replace(/Ѣ/g,"&#1122;");
	input = input.replace(/ѣ/g,"&#1123;");
	input = input.replace(/Ѥ/g,"&#1124;");
	input = input.replace(/ѥ/g,"&#1125;");
	input = input.replace(/Ѧ/g,"&#1126;");
	input = input.replace(/ѧ/g,"&#1127;");
	input = input.replace(/Ѩ/g,"&#1128;");
	input = input.replace(/ѩ/g,"&#1129;");
	input = input.replace(/Ѫ/g,"&#1130;");
	input = input.replace(/ѫ/g,"&#1131;");
	input = input.replace(/Ѭ/g,"&#1132;");
	input = input.replace(/ѭ/g,"&#1133;");
	input = input.replace(/Ѯ/g,"&#1134;");
	input = input.replace(/ѯ/g,"&#1135;");
	input = input.replace(/Ѱ/g,"&#1136;");
	input = input.replace(/ѱ/g,"&#1137;");
	input = input.replace(/Ѳ/g,"&#1138;");
	input = input.replace(/ѳ/g,"&#1139;");
	input = input.replace(/Ѵ/g,"&#1140;");
	input = input.replace(/ѵ/g,"&#1141;");
	input = input.replace(/Ѷ/g,"&#1142;");
	input = input.replace(/ѷ/g,"&#1143;");
	input = input.replace(/Ѹ/g,"&#1144;");
	input = input.replace(/ѹ/g,"&#1145;");
	input = input.replace(/Ѻ/g,"&#1146;");
	input = input.replace(/ѻ/g,"&#1147;");
	input = input.replace(/Ѽ/g,"&#1148;");
	input = input.replace(/ѽ/g,"&#1149;");
	input = input.replace(/Ѿ/g,"&#1150;");
	input = input.replace(/ѿ/g,"&#1151;");
	input = input.replace(/Ҁ/g,"&#1152;");
	input = input.replace(/ҁ/g,"&#1153;");
	input = input.replace(/҂/g,"&#1154;");
	input = input.replace(/҈/g,"&#1160;");
	input = input.replace(/҉/g,"&#1161;");
	input = input.replace(/Ҋ/g,"&#1162;");
	input = input.replace(/ҋ/g,"&#1163;");
	input = input.replace(/Ҍ/g,"&#1164;");
	input = input.replace(/ҍ/g,"&#1165;");
	input = input.replace(/Ҏ/g,"&#1166;");
	input = input.replace(/ҏ/g,"&#1167;");
	input = input.replace(/Ґ/g,"&#1168;");
	input = input.replace(/ґ/g,"&#1169;");
	input = input.replace(/Ғ/g,"&#1170;");
	input = input.replace(/ғ/g,"&#1171;");
	input = input.replace(/Ҕ/g,"&#1172;");
	input = input.replace(/ҕ/g,"&#1173;");
	input = input.replace(/Җ/g,"&#1174;");
	input = input.replace(/җ/g,"&#1175;");
	input = input.replace(/Ҙ/g,"&#1176;");
	input = input.replace(/ҙ/g,"&#1177;");
	input = input.replace(/Қ/g,"&#1178;");
	input = input.replace(/қ/g,"&#1179;");
	input = input.replace(/Ҝ/g,"&#1180;");
	input = input.replace(/ҝ/g,"&#1181;");
	input = input.replace(/Ҟ/g,"&#1182;");
	input = input.replace(/ҟ/g,"&#1183;");
	input = input.replace(/Ҡ/g,"&#1184;");
	input = input.replace(/ҡ/g,"&#1185;");
	input = input.replace(/Ң/g,"&#1186;");
	input = input.replace(/ң/g,"&#1187;");
	input = input.replace(/Ҥ/g,"&#1188;");
	input = input.replace(/ҥ/g,"&#1189;");
	input = input.replace(/Ҧ/g,"&#1190;");
	input = input.replace(/ҧ/g,"&#1191;");
	input = input.replace(/Ҩ/g,"&#1192;");
	input = input.replace(/ҩ/g,"&#1193;");
	input = input.replace(/Ҫ/g,"&#1194;");
	input = input.replace(/ҫ/g,"&#1195;");
	input = input.replace(/Ҭ/g,"&#1196;");
	input = input.replace(/ҭ/g,"&#1197;");
	input = input.replace(/Ү/g,"&#1198;");
	input = input.replace(/ү/g,"&#1199;");
	input = input.replace(/Ұ/g,"&#1200;");
	input = input.replace(/ұ/g,"&#1201;");
	input = input.replace(/Ҳ/g,"&#1202;");
	input = input.replace(/ҳ/g,"&#1203;");
	input = input.replace(/Ҵ/g,"&#1204;");
	input = input.replace(/ҵ/g,"&#1205;");
	input = input.replace(/Ҷ/g,"&#1206;");
	input = input.replace(/ҷ/g,"&#1207;");
	input = input.replace(/Ҹ/g,"&#1208;");
	input = input.replace(/ҹ/g,"&#1209;");
	input = input.replace(/Һ/g,"&#1210;");
	input = input.replace(/һ/g,"&#1211;");
	input = input.replace(/Ҽ/g,"&#1212;");
	input = input.replace(/ҽ/g,"&#1213;");
	input = input.replace(/Ҿ/g,"&#1214;");
	input = input.replace(/ҿ/g,"&#1215;");
	input = input.replace(/Ӏ/g,"&#1216;");
	input = input.replace(/Ӂ/g,"&#1217;");
	input = input.replace(/ӂ/g,"&#1218;");
	input = input.replace(/Ӄ/g,"&#1219;");
	input = input.replace(/ӄ/g,"&#1220;");
	input = input.replace(/Ӆ/g,"&#1221;");
	input = input.replace(/ӆ/g,"&#1222;");
	input = input.replace(/Ӈ/g,"&#1223;");
	input = input.replace(/ӈ/g,"&#1224;");
	input = input.replace(/Ӊ/g,"&#1225;");
	input = input.replace(/ӊ/g,"&#1226;");
	input = input.replace(/Ӌ/g,"&#1227;");
	input = input.replace(/ӌ/g,"&#1228;");
	input = input.replace(/Ӎ/g,"&#1229;");
	input = input.replace(/ӎ/g,"&#1230;");
	input = input.replace(/ӏ/g,"&#1231;");
	input = input.replace(/Ӑ/g,"&#1232;");
	input = input.replace(/ӑ/g,"&#1233;");
	input = input.replace(/Ӓ/g,"&#1234;");
	input = input.replace(/ӓ/g,"&#1235;");
	input = input.replace(/Ӕ/g,"&#1236;");
	input = input.replace(/ӕ/g,"&#1237;");
	input = input.replace(/Ӗ/g,"&#1238;");
	input = input.replace(/ӗ/g,"&#1239;");
	input = input.replace(/Ә/g,"&#1240;");
	input = input.replace(/ә/g,"&#1241;");
	input = input.replace(/Ӛ/g,"&#1242;");
	input = input.replace(/ӛ/g,"&#1243;");
	input = input.replace(/Ӝ/g,"&#1244;");
	input = input.replace(/ӝ/g,"&#1245;");
	input = input.replace(/Ӟ/g,"&#1246;");
	input = input.replace(/ӟ/g,"&#1247;");
	input = input.replace(/Ӡ/g,"&#1248;");
	input = input.replace(/ӡ/g,"&#1249;");
	input = input.replace(/Ӣ/g,"&#1250;");
	input = input.replace(/ӣ/g,"&#1251;");
	input = input.replace(/Ӥ/g,"&#1252;");
	input = input.replace(/ӥ/g,"&#1253;");
	input = input.replace(/Ӧ/g,"&#1254;");
	input = input.replace(/ӧ/g,"&#1255;");
	input = input.replace(/Ө/g,"&#1256;");
	input = input.replace(/ө/g,"&#1257;");
	input = input.replace(/Ӫ/g,"&#1258;");
	input = input.replace(/ӫ/g,"&#1259;");
	input = input.replace(/Ӭ/g,"&#1260;");
	input = input.replace(/ӭ/g,"&#1261;");
	input = input.replace(/Ӯ/g,"&#1262;");
	input = input.replace(/ӯ/g,"&#1263;");
	input = input.replace(/Ӱ/g,"&#1264;");
	input = input.replace(/ӱ/g,"&#1265;");
	input = input.replace(/Ӳ/g,"&#1266;");
	input = input.replace(/ӳ/g,"&#1267;");
	input = input.replace(/Ӵ/g,"&#1268;");
	input = input.replace(/ӵ/g,"&#1269;");
	input = input.replace(/Ӷ/g,"&#1270;");
	input = input.replace(/ӷ/g,"&#1271;");
	input = input.replace(/Ӹ/g,"&#1272;");
	input = input.replace(/ӹ/g,"&#1273;");
	input = input.replace(/Ӻ/g,"&#1274;");
	input = input.replace(/ӻ/g,"&#1275;");
	input = input.replace(/Ӽ/g,"&#1276;");
	input = input.replace(/ӽ/g,"&#1277;");
	input = input.replace(/Ӿ/g,"&#1278;");
	input = input.replace(/ӿ/g,"&#1279;");
	input = input.replace(/Ԁ/g,"&#1280;");
	input = input.replace(/ԁ/g,"&#1281;");
	input = input.replace(/Ԃ/g,"&#1282;");
	input = input.replace(/ԃ/g,"&#1283;");
	input = input.replace(/Ԅ/g,"&#1284;");
	input = input.replace(/ԅ/g,"&#1285;");
	input = input.replace(/Ԇ/g,"&#1286;");
	input = input.replace(/ԇ/g,"&#1287;");
	input = input.replace(/Ԉ/g,"&#1288;");
	input = input.replace(/ԉ/g,"&#1289;");
	input = input.replace(/Ԋ/g,"&#1290;");
	input = input.replace(/ԋ/g,"&#1291;");
	input = input.replace(/Ԍ/g,"&#1292;");
	input = input.replace(/ԍ/g,"&#1293;");
	input = input.replace(/Ԏ/g,"&#1294;");
	input = input.replace(/ԏ/g,"&#1295;");
	input = input.replace(/Ԕ/g,"&#1300;");
	input = input.replace(/ԕ/g,"&#1301;");
	input = input.replace(/Ԗ/g,"&#1302;");
	input = input.replace(/ԗ/g,"&#1303;");
	input = input.replace(/Ԙ/g,"&#1304;");
	input = input.replace(/ԙ/g,"&#1305;");
	input = input.replace(/Ԛ/g,"&#1306;");
	input = input.replace(/ԛ/g,"&#1307;");
	input = input.replace(/Ԝ/g,"&#1308;");
	input = input.replace(/ԝ/g,"&#1309;");
	input = input.replace(/Ԟ/g,"&#1310;");
	input = input.replace(/ԟ/g,"&#1311;");
	input = input.replace(/Ԡ/g,"&#1312;");
	input = input.replace(/ԡ/g,"&#1313;");
	input = input.replace(/Ԣ/g,"&#1314;");
	input = input.replace(/ԣ/g,"&#1315;");
	input = input.replace(/Ԥ/g,"&#1316;");
	input = input.replace(/ԥ/g,"&#1317;");
	input = input.replace(/Ԧ/g,"&#1318;");
	input = input.replace(/ԧ/g,"&#1319;");
	input = input.replace(/Ԩ/g,"&#1320;");
	input = input.replace(/ԩ/g,"&#1321;");
	input = input.replace(/Ԫ/g,"&#1322;");
	input = input.replace(/ԫ/g,"&#1323;");
	input = input.replace(/Ԭ/g,"&#1324;");
	input = input.replace(/ԭ/g,"&#1325;");
	input = input.replace(/Ԯ/g,"&#1326;");
	input = input.replace(/ԯ/g,"&#1327;");
	
	output = input;
	
	return output;	
}





