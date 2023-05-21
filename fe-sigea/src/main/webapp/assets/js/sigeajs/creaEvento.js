$(document).ready(function(){
	if(window.segnalazioneScelta != null){
	
		$("#labelSegn").show();
		$("#it_titolo").val(window.segnalazioneScelta.nome);
		$("#titoloSegn").html(window.segnalazioneScelta.nome);
		
		if(window.segnalazioneScelta.descrizione){
			$("#it_descrizione").val(window.segnalazioneScelta.descrizione);
			$("#descrizioneSegn").html("<strong>Descrizione: </strong><br/>" +window.segnalazioneScelta.descrizione);
		}
		
		if(window.segnalazioneScelta.dataDa == window.segnalazioneScelta.dataA){
			var data = moment(window.segnalazioneScelta.dataDa).format('DD/MM/YYYY');			
			$("input[name$='periodoSet[0].dataA']").val(data);
			$("input[name$='periodoSet[0].dataDa']").val(data);			
			$("#dataDaSegn").html("<strong>Data: </strong> " + data);
		} else {
			var dataDaSegn = moment(window.segnalazioneScelta.dataDa).format('DD/MM/YYYY');
			var dataASegn = moment(window.segnalazioneScelta.dataA).format('DD/MM/YYYY');			
			$("input[name$='periodoSet[0].dataSecca'][value='true']").prop("checked", false);
			$("input[name$='periodoSet[0].dataSecca'][value='false']").prop("checked", true);
			$("input[name$='periodoSet[0].dataA']").val(dataASegn);
			$("input[name$='periodoSet[0].dataDa']").val(dataDaSegn);			
			$("#dataDaSegn").html("<strong>Data da: </strong> " + dataDaSegn);
			$("#dataASegn").html("<strong>Data a: </strong> " + dataASegn);
		}
		
		var nazioneSelecitze = $('[name="locationSet[0].nazione"]').selectize()[0].selectize;
		var regioniSelecitze = $('[name="locationSet[0].regione"]').selectize()[0].selectize;
		var provinceSelecitze = $('[name="locationSet[0].provincia"]').selectize()[0].selectize;
    	var comuniSelecitze = $('[name="locationSet[0].comune"]').selectize()[0].selectize;
	    	
		if(window.segnalazioneScelta.codIstat){
			
			var keysComuniPerProvincia = Object.keys(comuniPerProvincia);
			var keysComuniPerRegione = Object.keys(comuniPerRegione);
			var keysComuniPerArea = Object.keys(comuniPerArea);
			
			var keyProvincia = "";
			var keyRegione = "";
			var keyArea = "";

			// Provincia
			for(var key of keysComuniPerProvincia){
				var listaComuni = comuniPerProvincia[key];
				for(var comune of listaComuni){
					if(comune.codComune == window.segnalazioneScelta.codIstat){
						keyProvincia = key;
						break;
					}
				}
			}
			
			// Regione
			for(var key of keysComuniPerRegione){
				var listaComuni = comuniPerRegione[key];
				for(var comune of listaComuni){
					if(comune.codComune == window.segnalazioneScelta.codIstat){
						keyRegione = key;
						break;
					}
				}
			}
			
			// Area
			for(var key of keysComuniPerArea){
				var listaComuni = comuniPerArea[key];
				for(var comune of listaComuni){
					if(comune.codComune == window.segnalazioneScelta.codIstat){
						keyArea = key;
						break;
					}
				}
			}
			
			if(keyRegione && keyRegione != window.puglia){
				$("input[name$='locationSet[0].puglia'][value='true']").prop("checked", false);
				$("input[name$='locationSet[0].puglia'][value='false']").prop("checked", true);
				
				for(var regione of regioniList){
					if(regione.codRegione == keyRegione){
	
						regioniSelecitze.setValue(regione.regione, false);
						$("input[name$='locationSet[0].codRegione']").val(regione.codRegione);
						break;
					}
				}
			}
			
			if(keyProvincia){
				for(var provincia of provinceList){
					if(provincia.codProvincia == keyProvincia){
	
						provinceSelecitze.setValue(provincia.provincia, false);
						$("input[name$='locationSet[0].codProvincia']").val(provincia.codProvincia);
						break;
					}
				}
			}
			
			if(keyArea){
				for(var area of areeList){
					if(area.codArea == keyArea){
	
						$("input[name$='locationSet[0].area']").val(area.areaTerritoriale);
						$("input[name$='locationSet[0].codArea']").val(area.codArea);
						break;
					}
				}
			}
			
			comuniSelecitze.setValue(window.segnalazioneScelta.comune, false);
			$("input[name$='locationSet[0].codComune']").val(window.segnalazioneScelta.codIstat);
			
			$("#comuneSegn").html("<strong>Comune: </strong> " + window.segnalazioneScelta.comune);
			
		} else {
			
			$("input[name$='locationSet[0].puglia'][value='true']").prop("checked", false);
			$("input[name$='locationSet[0].puglia'][value='false']").prop("checked", true);
			
			nazioneSelecitze.setValue("", false);
			$("input[name$='locationSet[0].codNazione']").val("");
			
			regioniSelecitze.setValue("", false);
			$("input[name$='locationSet[0].codRegione']").val("");
			
			$("input[name$='locationSet[0].comuneEstero']").val(window.segnalazioneScelta.comune);
			
			$("#comuneSegn").html("<strong>Comune: </strong> " + window.segnalazioneScelta.comune);
		}
		
		if(window.segnalazioneScelta.indirizzo){
			
			$("#indirizzoSegn").html("<strong>Indirizzo: </strong> " +window.segnalazioneScelta.indirizzo);
			
			$("input[name$='locationSet[0].indirizzo']").val(window.segnalazioneScelta.indirizzo);
		}
		
		if(window.segnalazioneScelta.location){
			
			$("#locationSegn").html("<strong>Location: </strong> " +window.segnalazioneScelta.location);
			
			$("input[name$='locationSet[0].nomeLocation']").val(window.segnalazioneScelta.location);
		}

		$("#riferimentoSegn").html("<strong>Riferimento: </strong> " +window.segnalazioneScelta.riferimento+"<br/><strong>Cognome e Nome: </strong> " +window.segnalazioneScelta.nomeUtente+"<br/><strong>E-mail: </strong> " +window.segnalazioneScelta.emailUtente+"<br/>");
		
	}
});



$('.popup-with-form').magnificPopup({
	type: 'ajax',
	closeOnBgClick: false,
	fixedContentPos: true
});

//GESTIONE LOCK EVENTO 
(function() {
	poll = function() {
		var eventoid = $('#idEventoPrincipale').val();
		if (eventoid) {
			$.ajax({
				type: 'POST',
				url: context+'/lockEvento?idEvento='+eventoid,
				success: function(response) {
					//console.log('Lock rinnovato su evento: '+eventoid);
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

(function() {
	poll2 = function() {
		var eventoid = $('#idEventoPrincipale').val();
		if (eventoid) {
			//alertify.success(window.mexJS['js.salva.avviso']);
			$('.ajs-button ajs-ok').click();
			alertify.alert(window.mexJS["js.tipologia.attenzione"], "<i class=\"icon-errore\" style=\"font-size:64px; color:#a66300;\"></i><br><br>"+window.mexJS["js.salva.avviso"]).set('label', 'Chiudi'); 
		}
	}
	if (window.pollInterval2 != null) {
		clearInterval(window.pollInterval2);
		window.pollInterval2 = null;
	}
	pollInterval2 = setInterval(function() { poll2(); }, 600000);
	poll2();
})();


function chiudiEvento() {
	$('#overlay').show();
	location.reload();
}

function annullaEvento() {
	alertify.confirm("<i class=\"icon-errore\" style=\"font-size:64px; color:#a66300;\"></i><br><br>"+window.mexJS['js.button.uscir']).set({
		title:window.mexJS['js.tipologia.attenzione']}).set('labels', {ok:'Si', cancel:'No'}).set('onok', function(){
			chiudiEvento();
		}).set('oncancel', function(){
//			alertify.error(window.mexJS['js.nota.annulla']);
			return;
		});
}

function apriSchedaAccessoria(nomeScheda,stato){

	window.sessionStorage.setItem("jsondatatable",JSON.stringify($('#formEventFilter').serializeFormJSON()));
	window.sessionStorage.setItem("pageidmenu",idmenu);
	
	$('#overlay').show();
	var idEvento = $("#idEventoPrincipale").val();
	$.ajax({
        type: "GET",
        url: context+"/"+nomeScheda + "/" + idEvento,
        success: function(response) {        	        	
        	
        	$('#overlay').hide();
        	$("#divSchedaAccessoria").html(response);
            document.getElementById("divSchedaAccessoria").style.display = 'block';
            document.getElementById("divCreazione").style.display = 'none';
            $("#divCreazione").empty();
            $('html, body').scrollTop(0);                        
            
        },
        error: function(error){
        	        	
          	alertify.alert("Abbiamo riscontrato un problema", "<img src=\""+context+"/assets/images/ERRORE.svg\"><br/><br/><br/>Al momento non &egrave; possibile concludere l'operazione. Ci scusiamo e ti invitiamo a riprovare in un altro momento.").set('label', 'Chiudi'); 
        	$('#overlay').hide();
		}
    });
}

function apriSchedaAccessoriaEsterna(url){
	$('#overlay').show();
	var idEvento = $("#idEventoPrincipale").val();
	var paramEntita;
	var entita;
	if(window.attivita.denominazione){
		paramEntita = "idEntita";
		entita = window.attivita.attivitaId;
	}else{
		paramEntita = "idRichiesta";
		entita = window.attivitaRichiesta.richiestaAttivitaId;
	}
	window.location = url + '?codiceAccesso=' + window.tipoUtente + '&' + paramEntita + '=' + entita + '&idEvento=' + idEvento;
}

function salvaEventoCondizionato(stato){
	
	$("a#media-tab div.alert").remove();
	$(".errorimmaginitab").removeClass("errorimmaginitab");
	
	var valuenote = '';
	var message  = "Inserisci qui i motivi per cui l&rsquo;evento richiede una modifica. La nota verr&agrave; inviata al promotore che ha creato la scheda.<br />Sei sicuro di voler procedere?";
	
	if(stato == 'MODIFICA'){
		
			if(window.idEntitaEventoCorrente != window.idEntita){		
				$("#modificaevento").validate(); 
				$("#notaModificaCreazione").rules("add", "required");
				$("#richiesta_nota").html("<br />"+message);
			}
			
		$("#modalModificaCreazione").modal('show');	
	} else if(stato == 'RESPINTO') {
		alertify.prompt("Attenzione", 
				"<i class=\"icon-errore\" style=\"font-size:64px; color:#a66300;\"></i><br><br><div style=\"text-align:left\"><strong>Stai rifiutando l'evento!</strong><br>Inserisci qui i motivi per cui l'evento &egrave; da rifiutare.<br />La	nota inserita sar&agrave; inviata via email al promotore.</div><br><div class=\"tolabel\">"+window.mexJS['js.nota.aggiuntiva']+"</div>", '', function(evt, value) {
	} , function() { }).set('labels', {ok:'Si', cancel:'No'}).set('onok', function(evt, value){
	
		valuenote = value;
		alerta(stato,valuenote);
		return false;		
		
		}).set({onfocus:function(){ $('.alertify').find('textarea.ajs-input').attr('placeholder', 'Inserisci'); }}); 
		
	} else {
		alertify.confirm("<i class=\"icon-errore\" style=\"font-size:64px; color:#a66300;\"></i><br><br>"+window.mexJS['js.button.sicuro']).set({
			title:window.mexJS['js.tipologia.attenzione']}).set('labels', {ok:'Si', cancel:'No'}).set('onok', function(){
				salvaEvento(stato);					
			}).set('oncancel', function(){
//				alertify.error(window.mexJS['js.nota.annulla']);
			});		
	}	
}

$("#modificaeventocreato").unbind("click").click(function() {
	if($('#modificaevento').valid()){
		var camponota ='';
		if($("#notaModificaCreazione").val()){camponota = $("#notaModificaCreazione").val();}	
		$("#modalModificaCreazione").modal('hide');
		$("#notaModificaCreazione").val('');	
		$("#ultimeNote").val(camponota);
		salvaEvento('MODIFICA');		
	}
	return true;
});

function alerta(stato,valuenote){
	$( "button.ajs-button.ajs-ok" ).unbind('click').click(function() {	
		$("#ultimeNote").val($("textarea.ajs-input").val())
		alertify.prompt().close();
		salvaEvento(stato);			
	});	
}

function controlloImmagine(stato){
	var countImmagine = $('[name$="immagineId"]').not("[name*='_PH_']").length;
	if(countImmagine == 0 && stato != "BOZZA"){	
		$('#immagini-tab').addClass('rch');		
		$('#media-tab').addClass('rch');
		$('#labelValid').show();
		 $('html, body').scrollTop(0);
		//$('#mediaValidazione').text(window.mexJS['js.error.insImg']);
		//$('html, body').animate({ scrollTop: $('#labelValid').offset().top }, 600);
		return false;
	} else {
		$('#media-tab').removeClass('rch');
		$('#immagini-tab').removeClass('rch');
		$('#mediaValidazione').text('');
		$('#labelValid').hide();
		return true;
	}
}

function immagineObbgligatoria(imageValid){	
	if(imageValid == false){	
		$('#immagini-tab').addClass('rch');		
		$('#media-tab').addClass('rch');
		$('#labelValid').show();
		 $('html, body').scrollTop(0);
		return false;
	} else {
		$('#media-tab').removeClass('rch');
		$('#immagini-tab').removeClass('rch');
		$('#labelValid').hide();
		return true;
	}	
}

function salvaEvento(stato) {
//	$('.ajs-visible').click();
	$('a.rch').removeClass('rch');
	$('#labelValid').hide();
	//$('.selectized').siblings('div.scegliere').attr('style', 'color: #0075BF !important');
	$('.selectized').siblings('div.selectize-control.form-control.Input-text').attr('style', 'border: 1px solid #0075BF !important');
	gestValidatore(stato);
	$('html, body').scrollTop(0);
	var titolo = $('#it_titolo').val();
	if (titolo == 'Inserisci'){
		$('#it_titolo').val('');
	}
	if(!titolo){
		$("#formEventCreating").validate().form();
		$('html, body').scrollTop(0);
	//	$('html, body').animate({ scrollTop: $('#labelValid').offset().top }, 600);
		return;
	} else if(stato != "AGGIORNA" && stato!="MODIFICA") {
		var formValid = $("#formEventCreating").validate().form();
		var imageValid = controlloImmagine(stato);		
		if (!formValid || !imageValid){
			$('#labelValid').show();
			$('html, body').scrollTop(0);
			return;
		}
		$('html, body').scrollTop(0);
	}
			
	enableAll();

	var evento = $('#formEventCreating').serializeFormJSON();

	if (stato == "BOZZA") {
		if(window.attivita.denominazione != null && window.attivita.denominazione != ""){
			evento.attivita = window.attivita;
		} else if (window.attivitaRichiesta.denominazione != null && window.attivitaRichiesta.denominazione != "") {
			evento.richiestaAttivita = window.attivitaRichiesta;
		}
	}
	
	if(stato == "AGGIORNA" ) {
		window.tabsave = $('#myTabCE .nav-item.active').attr('id');
	} else {
		window.tabsave = "datigenerali";
	}

	$('#overlay').show();
	$.ajax({
        type: "PUT",
        url: context+"/saveEvento/" + stato,
        data: JSON.stringify(evento),
		contentType: 'application/json',
        success: function(response1) {        	
        	if(stato == "BOZZA" && window.segnalazioneScelta != null){
        		$.ajax({
        	        type: "PUT",
        	        url: context+"/cambiaStato?segnalazioneId="+window.segnalazioneScelta.segnalazioneId,
        	        contentType: 'application/json',
        	        data: JSON.stringify({"stato":"Gestita"}),
        	        success: function(response) {
        	        	if(stato != "BOZZA") {
        	        		window.segnalazioneScelta = null;
        	        	}
        	        
        	        },
                    error: function(error){
                    	
                      	alertify.alert("Abbiamo riscontrato un problema", "<img src=\""+context+"/assets/images/ERRORE.svg\"><br/><br/><br/>Al momento non &egrave; possibile concludere l'operazione. Ci scusiamo e ti invitiamo a riprovare in un altro momento.").set('label', 'Chiudi'); 
                    	$('#overlay').hide();    		
            		}
        	    });
        	}
        	document.getElementById("divCreazione").style.display = 'none';
        	$("#preload").show();
            $("#divCreazione").empty();
            $.ajax({
                type: "GET",
                url: context+"/creazioneEvento",
                success: function(response2) {                	
                	inizializzazioneSchermataEvento(response2);
                	$.ajax({
                		async: false,
                        type: "GET",
                        url: context + "/getEvento?idEvento=" + response1,
                        success: function(response3) {
                        	
                        	if(response3.locationSet) {
                        		var locationOrdinate = [];
                            	var j = 1;
                            	for(i=0; i<response3.locationSet.length; i++){
                            		if(response3.locationSet[i].fgPrincipale){
                            			locationOrdinate[0] = response3.locationSet[i];
                            		} else {
                            			locationOrdinate[j] = response3.locationSet[i];
                            			j++;
                            		}
                            	}
                            	response3.locationSet = locationOrdinate;
                        	}
                        	//recuperoProgetti(response3);                       	
                        	
                        	inizializzaValidatore();
                        	
                        	$('#formEventCreating').deserializeJSONForm(response3,true);
                        	
							if(response3.relazioneSet){
								$(response3.relazioneSet).each(function(index, relazione){
									if(relazione.redazioneId){
										$('[data-template="relazioneSet[' + index + ']"]').hide();
									}
								});
							}							
                        	
                        	$("#formEventCreating").validate();                        	
                        	$('input[name$="contatto"]').not("[name*='_PH_'], [name*='sitoSet'], [name*='telefonoSet']").each(function() {
                        		$(this).rules("add", { email: true, normalizer: function(value) { return $.trim(value); }});
                        	});
                        	
                        	var entitaIdOwner = null;
                        	
				        	if(response3.attivita){
								entitaIdOwner = response3.attivita.attivitaId;
							}else if(response3.richiestaAttivita){
								entitaIdOwner = response3.richiestaAttivita.richiestaAttivitaId;
							}
				        				
				        	
				           	window.idEntitaEventoCorrente = "" + entitaIdOwner;
				           	window.idUtenteOwnerEventoCorrente =response3.ownerId + "";
                        	gestisciPermessiInterfaccia(response3.statoId, response3.ownerId, response3.ownerIdUltimaModifica, window.idUtenteCorrente, response3.tipo, response3.fgValidazionePregressa, window.idEntita, entitaIdOwner);
                        	$("#statoCorrente").html("<strong>Stato corrente:</strong> "+response3.descrizioneStato);
                        	
                        	if(response3.statoId == "LAVORAZIONE") {
                        		var riferimentoOwnerLavorazione;
                            	if (response3.nomeOwnerUltimaModifica && response3.cognomeOwnerUltimaModifica){
                            		riferimentoOwnerLavorazione = response3.nomeOwnerUltimaModifica + " " + response3.cognomeOwnerUltimaModifica;
                            	}else{
                            		riferimentoOwnerLavorazione = response3.usernameOwnerUltimaModifica;
                            	}
                            	$("#utenteLavorazione").html("(da parte di "+riferimentoOwnerLavorazione+")");
                        	}
                        	
                        	if(response3.ultimeNote && response3.ultimeNote != null && response3.ultimeNote != ""){                        		
                        		$("#noteMod").css('display','contents');
                        		$("#preload").hide();
		                		$("#noteModV").show();
		                	}
                        	
                        	$("#noteMod").html("<strong>Motivo:</strong> "+response3.ultimeNote);
                        	$("#idEvent").html("<strong>ID:</strong> " + response3.eventoId);                        	
                          	$("#titoloEvent").html(response3.datiGenerali.titoloMulti.IT);
                          	
                        	if(response3.immagineSet && response3.immagineSet.length > 0 ){                	
                        		var imageUrl = window.context+"/getFile/"+response3.eventoId+"/immagine/"+response3.immagineSet[0].immagineId+"."+response3.immagineSet[0].estensione;                	               	               	
                        		$(".immagineventoblocco").css('background', 'url('+imageUrl+')');
                        	}
                          	
                        	$('#riassuntoButton').attr('href', context+'/getRiassunto/'+ response3.eventoId);
                        	var data = response3.dataIns.substr(0,10);
                        	data = convertDataEngToIta(data);
                        	var riferimentoOwner;
                        	if (response3.nomeOwner && response3.cognomeOwner){
                        		riferimentoOwner = response3.nomeOwner + " " + response3.cognomeOwner;
                        	}else{
                        		riferimentoOwner = response3.usernameOwner;
                        	}                        	
                        	if(response3.attivita && response3.attivita.denominazione != null && response3.attivita.denominazione != "") {
                        		
                        		
                        		//todo
                        		$("#utenteOwner").html("<strong>Creato da:</strong> " + riferimentoOwner + " (" + response3.attivita.denominazione + ") ");
                        		                        		
                        		$("#utenteOwnerData").html("<strong>Creato in data:</strong> " + data);
                        	} else if (response3.richiestaAttivita && response3.richiestaAttivita.denominazione != null && response3.richiestaAttivita.denominazione != "") {
	
                        		var statoi = " - Stato impresa: in attesa di validazione";                        		
                        		$("#utenteOwner").html("<strong>Creato da:</strong> " + riferimentoOwner + " (" + response3.richiestaAttivita.denominazione + ")"+ statoi);
                        		$("#utenteOwnerData").html("<strong>Creato in data:</strong> " + data);
                        	} else {
                        		$("#utenteOwner").html("<strong>Creato da:</strong> " + riferimentoOwner);
                        		$("#utenteOwnerData").html("<strong>Creato in data:</strong> "+ data);
                        	}
                        	
				        	//To do visualizzazione box bandi INIZIO
    			        	
                        	var bandoId = "";
                        	var progettoId = "";
                        	var titoloBando = "";
                        	var titoloProgetto = "";
                        	var nomeOrganizzazione = "";	
                        	
                        	if(typeof response3.bando !== 'undefined'){
                        		if(typeof response3.bando.bandoId !== 'undefined'){ bandoId = response3.bando.bandoId; }                        	
                        		if(typeof response3.bando.titoloBando !== 'undefined'){ titoloBando = response3.bando.titoloBando; }
                        	}
                        	
                        	if(typeof response3.progetto !== 'undefined'){
                        		if(typeof response3.progetto.progettoId !== 'undefined'){ progettoId = response3.progetto.progettoId; }
                        		if(typeof response3.progetto.titoloProgetto !== 'undefined'){ titoloProgetto = response3.progetto.titoloProgetto; }
                        		if(typeof response3.progetto.progettoId !== 'undefined'){ nomeOrganizzazione = response3.progetto.nomeOrganizzazione; }
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
				        	
				        	if(response3.statoId != 'BOZZA' && response3.statoId !='MODIFICA'){				        		
				        		$("#azionibandi").hide();
				        		$("#boxbandoaggiungi").hide();
				        	}
				        	
				        	//To do visualizzazione box bandi FINE
                        	
                        	$('#t_revisioni').DataTable().rows.add(response3.logSet).draw();
                        	
                        	$("#gestioneAccF").hide();
                        	if(idmenu == "filtriPromozione.jsp" || idmenu == "filtriGestionePromozione.jsp"){
            					//$("#labelbreadcrumb").empty().html('Promozione eventi');
            					$("#gestioneEvento").attr('hidden', true);
            					$("#gestioneEventoPro").show();					
            				}else if(idmenu == "filtriGestioneValidazione.jsp"){
            					//$("#labelbreadcrumb").empty().html('Promozione eventi');
            					$("#gestioneEventoPro").attr('hidden', true);
            					$("#gestioneEvento").show();					
            				}
                        	
                        	if(window.tabsave != null && window.tabsave != ""){
                        		$('#'+window.tabsave+'-tab').click();
                        	}

                        },
                        error: function(error){
                 
                    		if (error.status == 409){
                    			annullaEvento();
                    			alertify.alert(window.mexJS['js.tipologia.attenzione'], "<img src=\""+context+"/assets/images/PERICOLO.svg\"><br/><br/><br/>"+window.mexJS['js.risorsaoccupata']).set('onok', function(){$('#t_eventi').DataTable().columns.adjust().draw();} ).set('label', 'Chiudi'); 
                    			$('#overlay').hide();
                    		}
                    	else{
		alertify.alert("Abbiamo riscontrato un problema", "<img src=\""+context+"/assets/images/ERRORE.svg\"><br/><br/><br/>Al momento non &egrave; possibile concludere l'operazione. Ci scusiamo e ti invitiamo a riprovare in un altro momento.").set('label', 'Chiudi'); 
                	$('#overlay').hide();
}
                    	}
                    });
                	//$('html, body').scrollTop($('#divCreazione').offset().top);
                	
                	$("#preload").hide();
                    $('#overlay').hide();
                                        
                    if(stato == 'AGGIORNA'){ 
                    	opendmstoastsuccess('Operazione conclusa con successo!');   
                    }else if(stato == 'BOZZA'){                     	
                    	opendmstoastsuccess('Bozza creata con successo!');
                    }else if(stato == 'LAVORAZIONE'){                     	
                    	opendmstoastsuccess('I campi sono abilitati!');   
                    }else if(stato == 'VALUTAZIONE'){   	
                      	alertify.alert("Azione confermata", "<img src=\""+context+"/assets/images/SUCCESSO.svg\"><br/><br/><br/>I dati dell'evento sono stati salvati e la richiesta &egrave; stata inviata con successo!").set('onok', function(){
                    		$('#overlay').show();
                    		location.reload();
                    	} ).set('label', 'Chiudi');                       	
                    }else if(stato == 'VALIDATO' || stato == 'RIVALIDATO'){   	
                      	alertify.alert("Azione confermata", "<img src=\""+context+"/assets/images/SUCCESSO.svg\"><br/><br/><br/>Evento validato!").set('onok', function(){
                    		$('#overlay').show();
                    		location.reload();
                    	} ).set('label', 'Chiudi');                      	
                    }else if(stato == 'RESPINTO'){   	
                      	alertify.alert("Azione confermata", "<img src=\""+context+"/assets/images/SUCCESSO.svg\"><br/><br/><br/>Evento rifiutato!").set('onok', function(){
                    		$('#overlay').show();
                    		location.reload();
                    	} ).set('label', 'Chiudi');                        	
                    }else if(stato == 'MODIFICA'){     
                    	
                    	alertify.alert("Azione confermata", "<img src=\""+context+"/assets/images/SUCCESSO.svg\"><br/><br/><br/>Operazione conclusa con successo!").set('onok', function(){
                    		
                      		if(window.idEntitaEventoCorrente  == window.idEntita)		
                      		{
                      			$("#gestioneEvento").attr('hidden', true);
            					$("#gestioneEventoPro").show();					
                      		}
                      		else{
                      		$('#overlay').show();
                    		location.reload();
                      		}
                    	} ).set('label', 'Chiudi');    	
                    }else if(stato == 'ANNULLATO'){                     	
                      	alertify.alert("Azione confermata", "<img src=\""+context+"/assets/images/SUCCESSO.svg\"><br/><br/><br/>Evento annullato!").set('onok', function(){
                    		$('#overlay').show();
                    		location.reload();
                    	} ).set('label', 'Chiudi');    
                    }else if(stato == 'RIVALUTAZIONE'){                     	
                      	alertify.alert("Azione confermata", "<img src=\""+context+"/assets/images/SUCCESSO.svg\"><br/><br/><br/>I dati dell'evento sono stati salvati e la richiesta &egrave; stata inviata con successo.").set('onok', function(){
                    		$('#overlay').show();
                    		location.reload();
                    	} ).set('label', 'Chiudi');                       	
                    }else{ 
                    	alertify.alert("Successo", "<img src=\""+context+"/assets/images/SUCCESSO.svg\"><br/><br/><br/>Operazione avvenuta con successo!").set('onok', function(){
                    		$('#overlay').show();
                    		location.reload();
                    	} ).set('label', 'Chiudi'); 
                    }  
                },
                error: function(error){
                	
                  	alertify.alert("Abbiamo riscontrato un problema", "<img src=\""+context+"/assets/images/ERRORE.svg\"><br/><br/><br/>Al momento non &egrave; possibile concludere l'operazione. Ci scusiamo e ti invitiamo a riprovare in un altro momento.").set('label', 'Chiudi'); 
                	$('#overlay').hide();
        		
        		}
            });
        }, error: function(error){
                	
                  	alertify.alert("Abbiamo riscontrato un problema", "<img src=\""+context+"/assets/images/ERRORE.svg\"><br/><br/><br/>Al momento non &egrave; possibile concludere l'operazione. Ci scusiamo e ti invitiamo a riprovare in un altro momento.").set('label', 'Chiudi'); 
                	$('#overlay').hide();
        		}
    });
}

//Gestione TAB

$("#datigenerali-tab").click(function(){
	//$("#formEventCreating").find(".active").removeClass("active in");
	//$("#datigenerali").addClass("active in");
	//$("#datigeneralibox").addClass("active in");
	//$("#datigenerali-tab").addClass("active in");
	
	$("#myTabDG").find('li.nav-item').removeClass("active in");
	$("#myTabDG").find('a.nav-link').removeClass("active in");	
	$("#myTabDGContent").find('tab-pane fade').removeClass("active in");
	
	$("#it").addClass("active in");
	$("#it-tab").addClass("active in");
	$("#itbox").addClass("active in");	
});

//$("#revisioni-tab").click(function(){
//	//$("#formEventCreating").find(".active").removeClass("active in");
//	//$("#revisioni").addClass("active in");
//	//$("#revisionibox").addClass("active in");
//	//$("#revisioni-tab").addClass("active in");
//	$('#t_revisioni').DataTable().columns.adjust().draw();
//});


$('#revisioni-tab').on('shown.bs.tab', function(e){
	$($.fn.dataTable.tables(true)).DataTable()
	.columns.adjust().draw();
	//.responsive.recalc();
	});

$("#ticket-tab").click(function(){
	//$("#formEventCreating").find(".active").removeClass("active in");
	//$("#ticket").addClass("active in");
	//$("#ticketbox").addClass("active in");
	//$("#ticket-tab").addClass("active in");
	$("#itn").addClass("active in");
	$("#itn-tab").addClass("active in");
	$("#itnota").addClass("active in");
});

$("#media-tab").click(function(){
	//$("#formEventCreating").find(".active").removeClass("active in");
	//$("#media").addClass("active in");
	//$("#mediabox").addClass("active in");
	//$("#media-tab").addClass("active in");
	$("#imm").addClass("active in");
	$("#immaginibox").addClass("active in");
	$("#immagini-tab").addClass("active in");
	$("[data-tablink*='itm']").addClass("active in");
	$("[data-tab*='itmedia']").addClass("active in");
	$("[data-tablink*='itd']").addClass("active in");
	$("[data-tablink*='itv']").addClass("active in");
});

$("#documenti-tab").click(function(){
	//$("[data-tablink$='d'].active");
	var lin = $.trim($("[data-tablink$='d'].active").text()).toLowerCase();
	$("[data-tab*='"+lin+"documento']").addClass("active in");
});

$("#video-tab").click(function(){
	//$("[data-tablink$='v'].active");
	var lin = $.trim($("[data-tablink$='v'].active").text()).toLowerCase();
	$("[data-tab*='"+lin+"video']").addClass("active in");
});

$("ul#myTabCE li").click(function(){
	var dataTarget = $(this).find("a").attr("href");
	$("#myTabCEContent").find(".tab-pane").not(dataTarget).removeClass("active in");
	$("#immaginibox").addClass("active in");
	$("#itbox").addClass("active in");
	$("#itnota").addClass("active in");
	$("[data-tab='itdocumenti']").addClass("active in");
	$("[data-tab='itmedia']").addClass("active in");
});

$("ul#myTab7 li").click(function(){
	var dataTarget = $(this).find("a").attr("data-target").substr(11,7);
	var cosa = $("[data-dynamic='immagineSet']").find(".tab-pane").not("[data-tab='"+dataTarget+"']").removeClass("active in");
});

$("ul#myTab9 li").click(function(){
	var dataTarget = $(this).find("a").attr("data-target").substr(11,11);
	$("[data-dynamic='documentoSet']").find(".tab-pane").not("[data-tab='"+dataTarget+"']").removeClass("active in");
});

$("ul#vidTab li").click(function(){
	var dataTarget = $(this).find("a").attr("data-target").substr(11,7);
	$("[data-dynamic='linkSet']").find(".tab-pane").not("[data-tab='"+dataTarget+"']").removeClass("active in");
});
	
$("[name='tipo']").on('mousedown', function(){
	if($(this).val() == 'EVENTO' && $(this). prop("checked") == false){
		alertify.confirm("<i class=\"icon-errore\" style=\"font-size:64px; color:#a66300;\"></i><br><br>"+window.mexJS['js.tipologia.evento']).set({
			title:window.mexJS['js.tipologia.attenzione']}).set('labels', {ok:'Si', cancel:'No'}).set('onok', function(){
					$("[value='EVENTO']").prop('checked', true);
					$("[value='RASSEGNA']").prop('checked', false);
					$("[data-deletebutton^='locationSet']").not("[data-deletebutton*='.']").click();
					$("[data-deletebutton^='relazioneSet']").click();
					$("[data-addbutton='locationSet']").attr("hidden",true);
					$("#myTabContent4").find('hr').attr("hidden",true);
					// Reload della select
					let inputName = 'relazioneSet';
					$('[name="'+ inputName +'"]').selectize()[0].selectize.destroy();
					selectizeRelazioni(inputName);
					
				}).set('oncancel', function(){
					$("[value='EVENTO']").prop('checked', false);
					$("[value='RASSEGNA']").prop('checked', true);
				});
	}else if ($(this).val() == 'RASSEGNA' && $(this). prop("checked") == false) {
		alertify.confirm("<i class=\"icon-errore\" style=\"font-size:64px; color:#a66300;\"></i><br><br>"+window.mexJS['js.tipologia.rassegna']).set({
			title:window.mexJS['js.tipologia.attenzione']}).set('labels', {ok:'Si', cancel:'No'}).set('onok', function(){
				$("[value='EVENTO']").prop('checked', false);
				$("[value='RASSEGNA']").prop('checked', true);
				$("[data-deletebutton^='relazioneSet']").click();
				$("[data-addbutton='locationSet']").removeAttr("hidden");
				$("#myTabContent4").find('hr').removeAttr("hidden");
				// Reload della select
				let inputName = 'relazioneSet';
				$('[name="'+ inputName +'"]').selectize()[0].selectize.destroy();
				selectizeRelazioni(inputName);
					
			}).set('oncancel', function(){
				$("[value='EVENTO']").prop('checked', true);
				$("[value='RASSEGNA']").prop('checked', false);
			});
	}
});

//Gestione eventi dinamici
function clickRadioPagamento(nomeRadio, evento){
	$('[name="'+ nomeRadio +'"]').on( evento, function() {
		if($(this).prop("checked")){
			if($(this).val() == 'pagamento'){
				//$('#pag').removeAttr('hidden');
				$("#boxticket").show();
				$("#inputlink").show();
				 $("#inputticket").show();
				 $("#accig").show();
				$("#accir").show();
			}else if($(this).val() == 'gratuito'){
				$("#boxticket").show();
				$("#inputlink").show();
				 $("#inputticket").hide();
				 $("#accig").hide();
				$("#accir").hide();				
			}else {
				$("#boxticket").hide();
				$("#inputlink").hide();
				 $("#inputticket").hide();
				 $("#accig").hide();
				$("#accir").hide();
			}	
		}
	});
}

//var dataoggi = moment(serverdate, 'YYYY-MM-DD').startOf('day');
//var dataoggiuk = moment(serverdate, 'YYYY-MM-DD').toDate();
var dataoggiit = moment(dataserver, 'YYYY-MM-DD').format('DD/MM/YYYY');

function clickRadioDatasecca(nomeRadio, evento){
	$('[name="'+ nomeRadio +'"]').on( evento, function() {
		if($(this).prop("checked")){
			var suffix = nomeRadio.substr(0,nomeRadio.indexOf(".")+1);
			if($(this).val() == 'true'){
				
				$('[data-name="'+ suffix + 'dataSeccaLable' +'"]').removeAttr('hidden');
				 $('[data-name="'+ suffix + 'dataPeriodoLable' +'"]').attr('hidden','true');
				 $('[data-name="'+ suffix + 'sezPeriodo' +'"]').attr('hidden','true');
				 $('[data-name="'+ suffix + 'cadenzaSuPeriodo' +'"]').attr('hidden','true');
				 var dataDaName = nomeRadio.replace(new RegExp('dataSecca', 'g'), "dataDa");
				 //var dataAName = nomeRadio.replace(new RegExp('dataSecca', 'g'), "dataA");
				 $('[name="'+ dataDaName +'"]').val('');
				 if(validator == true){
					 $('[name="'+ dataDaName +'"]').datepicker('setStartDate', null);
				 }else{
					 $('[name="'+ dataDaName +'"]').datepicker('setStartDate', dataoggiit); 
				 }
				 $('[name="'+ dataDaName +'"]').datepicker('setEndDate', null);
				 $('[name="'+ dataDaName +'"]').datepicker('autoclose', true);
//				 $('[name="'+ dataAName +'"]').datepicker('setStartDate', new Date());
				 $('[name="'+ dataAName +'"]').val('');
		    }else {		    	
		    	
				 $('[data-name="'+ suffix + 'dataSeccaLable' +'"]').attr('hidden','true');
				 $('[data-name="'+ suffix + 'dataPeriodoLable' +'"]').removeAttr('hidden');
				 $('[data-name="'+ suffix + 'sezPeriodo' +'"]').removeAttr('hidden');
				 $('[data-name="'+ suffix + 'cadenzaSuPeriodo' +'"]').removeAttr('hidden');
				 var dataDaName = nomeRadio.replace(new RegExp('dataSecca', 'g'), "dataDa");
				 var dataAName = nomeRadio.replace(new RegExp('dataSecca', 'g'), "dataA");
				 $('[name="'+ dataDaName +'"]').val('');
				 //$('[name="'+ dataDaName +'"]').datepicker('setStartDate', null);	
				 $('[name="'+ dataDaName +'"]').datepicker('autoclose', true);
				 $('[name="'+ dataDaName +'"]').datepicker('setStartDate', null);

				 
				// if(validator == true){
				//	 $('[name="'+ dataAName +'"]').datepicker('setStartDate', null);
				// }else{
				 	 $('[name="'+ dataAName +'"]').datepicker('destroy').val('');
				 	$('[name="'+ dataAName +'"]').datepicker({ format: 'dd/mm/yyyy', autoclose: true, constrainInput: false, language: "it"});
					 $('[name="'+ dataAName +'"]').datepicker('setStartDate', dataoggiit);
					 $('[name="'+ dataAName +'"]').datepicker('autoclose', true);
				// }
					 
					 
					
				
				 
					 
			}
		}
	});
}

function clickRadioContinuato(nomeRadio, evento){
	$('[name="'+ nomeRadio +'"]').on( evento, function() {
		if($(this).prop("checked")){
			var suffix = nomeRadio.substr(0,nomeRadio.indexOf(".")+1);
			if($(this).val() == 'true'){
				 $('[data-name="'+ suffix + 'dataOrarioContinuato' +'"]').removeAttr('hidden');
				 $('[data-name="'+ suffix + 'dataOrarioNonContinuato' +'"]').attr('hidden','true');
				 $('[data-name="'+ suffix + 'dataOrarioNonContinuato' +'"]').find('input').val('');
		    }else {
				 $('[data-name="'+ suffix + 'dataOrarioContinuato' +'"]').attr('hidden','true');
				 $('[data-name="'+ suffix + 'dataOrarioContinuato' +'"]').find('input').val('');
				 $('[data-name="'+ suffix + 'dataOrarioNonContinuato' +'"]').removeAttr('hidden');
			}
		}
	});
}

function validatoreMail(inputName, evento){
	if(inputName != 'emailSet[0].contatto') {
		$('[name="'+inputName+'"]').each(function() {
			$(this).rules("add", { email: true, normalizer: function(value) { return $.trim(value); }});
		});
	}
}

function selectizeCadenza(inputName){
	$('[name="'+ inputName +'"]').selectize({
		valueField: 'cadenza',
	    labelField: 'cadenza',
	    options: [{"cadenza":"Nessuna"}],
	    render: {
	        option: function (item, escape) {
	        	return '<div class="option">' +
	                   	'<div class="text">' +
	                   		'<span class="name">' + escape(item.cadenza) + '</span>' +
	                   	'</div>' +
	                   '</div>';
	        }
	    },
	    onChange: function(value) {
	    	var suffix = inputName.substr(0,inputName.indexOf(".")+1);
	    	if (value == 'Settimanale'){
	    		$('[data-name="'+ suffix + 'cadenzaSettimanale' +'"]').removeAttr('hidden');
				$('[data-name="'+ suffix + 'cadenzaMensile' +'"]').attr('hidden','true');
				$('[data-name="'+ suffix + 'chiusoNeiGiorni' +'"]').attr('hidden','true');
	    	}else if (value == 'Mensile'){
	    		$('[data-name="'+ suffix + 'cadenzaMensile' +'"]').removeAttr('hidden');
				$('[data-name="'+ suffix + 'cadenzaSettimanale' +'"]').attr('hidden','true');
				$('[data-name="'+ suffix + 'chiusoNeiGiorni' +'"]').attr('hidden','true');
	    	}else{
	    		$('[data-name="'+ suffix + 'cadenzaSettimanale' +'"]').attr('hidden','true');
				$('[data-name="'+ suffix + 'cadenzaMensile' +'"]').attr('hidden','true');
				$('[data-name="'+ suffix + 'chiusoNeiGiorni' +'"]').removeAttr('hidden');
	    	}
	    }
	});
	var $select = $('[name="'+ inputName +'"]').selectize();
	var selectize = $select[0].selectize;
	selectize.setValue("Nessuna", false);
}

function selectizeCadenzaMensile(inputName){
	$('[name="'+ inputName +'"]').selectize({
		valueField: 'num',
	    labelField: 'num',
	    placeholder: 'Seleziona',
	    options: [{"num":"1"},{"num":"2"},{"num":"3"},{"num":"4"},{"num":"5"},{"num":"6"},{"num":"7"},{"num":"8"},{"num":"9"},{"num":"10"},{"num":"11"},{"num":"12"},{"num":"13"},{"num":"14"},{"num":"15"},{"num":"16"},{"num":"17"},{"num":"18"},{"num":"19"},{"num":"20"},{"num":"21"},{"num":"22"},{"num":"23"},{"num":"24"},{"num":"25"},{"num":"26"},{"num":"27"},{"num":"28"},{"num":"29"},{"num":"30"},{"num":"31"}],
	    render: {
	        option: function (item, escape) {
	        	return '<div class="option">' +
	                   	'<div class="text">' +
	                   		'<span class="name">' + escape(item.num) + '</span>' +
	                   	'</div>' +
	                   '</div>';
	        }
	    }
	});
}

$('[name^="ticket.conv"]').not('[name*="ttive"]').selectize({
	valueField: 'conv',
    labelField: 'conv',
    options: [{"conv":"Accompagnatori disabili"},{"conv":"Anziani"},{"conv":"Bambini"},{"conv":"Convenzioni attive"},{"conv":"Disabili"}],
    render: {
        option: function (item, escape) {
        	return '<div class="option">' +
                   	'<div class="text">' +
                   		'<span class="name">' + escape(item.conv) + '</span>' +
                   	'</div>' +
                   '</div>';
        }
    }
});

function inizializeDataDa(inputName){
	$('[name="'+ inputName +'"]').datepicker({ format: 'dd/mm/yyyy', autoclose: true, constrainInput: false, language: "it" /*, startDate: new Date()*/ });
}

function inizializeDataA(inputName){
	$('[name="'+ inputName +'"]').datepicker({ format: 'dd/mm/yyyy', autoclose: true, constrainInput: false, language: "it"/*, startDate: new Date()*/ });
}

function inizializeOrario(inputName, evento){
	$('[name="'+ inputName +'"]').on(evento, function(selected) {
		if (!$('[name="'+ inputName +'"]').hasClass('hasWickedpicker')){
			// $('[name="'+ inputName +'"]').wickedpicker({twentyFour:true, now : '00:00', minutesInterval: 5});
		}
	});
}



function changeDataDa(inputName, evento){
	var dataAName = inputName.replace(new RegExp('dataDa', 'g'), "dataA");
	var selectizeName = inputName.replace(new RegExp('dataDa', 'g'), "cadenza");
	var selectize = $('[name="'+ selectizeName +'"]').selectize()[0].selectize;
	$('[name="'+ inputName +'"]').on(evento, function() {
		//		var minDate = null;
		//		try{
		//			minDate = new Date(selected.date.valueOf());
		//		}catch(e){}
		//		if (minDate < new Date()){ minDate = new Date(); }
		
		var minDate = $(this).val();
			
		var datainput = moment(minDate, 'DD/MM/YYYY').format('YYYY-MM-DD');
		//datainput = moment(datainput, 'YYYY-MM-DD').format('YYYY-MM-DD');		
		
		if(datainput < dataserver && validator != true){
			minDate = dataoggiit;
		}else if(datainput < dataserver && validator == true){
			minDate = minDate;
		}else{
			minDate = minDate;
		}		
	
		
		//console.log(minDate);		
	
		
		$('[name="'+ dataAName +'"]').val('').datepicker('destroy').datepicker({format: 'dd/mm/yyyy', language: 'it', autoclose: true,  constrainInput: false, startDate: minDate, defaultViewDate: minDate});
		
		//$('[name="'+ dataAName +'"]').val('').datepicker('setStartDate', minDate);
		//$('[name="'+ dataAName +'"]').val('').datepicker('mindate', minDate);
		//$('[name="'+ dataAName +'"]').datepicker('autoclose', true);
		
		//$('[name="'+ dataAName +'"]').val('').datepicker('destroy').datepicker({format: 'dd/mm/yyyy', language: 'it', autoclose: true,  constrainInput: false, startDate: minDate});
		
		
		
		var dataA = $('[name="'+ dataAName +'"]').val();
		if (dataA != null && dataA != ''){
			selectize.setValue('', true);
			selectize.clearOptions();
			var diff = calculateDaysBetweenItalianDates($(this).val(), dataA);

			selectize.addOption({"cadenza":"Nessuna"});
			if (diff >13){
				selectize.addOption({"cadenza":"Settimanale"});
			}
			if (diff >59){
				selectize.addOption({"cadenza":"Mensile"});
			}

			selectize.setValue('Nessuna', false);
		}
	});
}

function changeDataA(inputName, evento){
	var dataDaName = inputName.replace(new RegExp('dataA', 'g'), "dataDa");
	var hiddenDiv = inputName.replace(new RegExp('dataA', 'g'), "sezPeriodo");
	var selectizeName = inputName.replace(new RegExp('dataA', 'g'), "cadenza");
	var selectize = $('[name="'+ selectizeName +'"]').selectize()[0].selectize;
	$('[name="'+ inputName +'"]').on(evento, function() {
		if (!$('[data-name="'+ hiddenDiv +'"]').prop("hidden")){
			
			
			//			var minDate = null;
			//			try{
			//				minDate = new Date(selected.date.valueOf());
			//			}catch(e){}
			
			var minDate = $(this).val();
			//if (minDate < new Date()) minDate = new Date();
				
			var datainput = moment(minDate, 'DD/MM/YYYY').format('YYYY-MM-DD');
			datainput = moment(datainput, 'YYYY-MM-DD').toDate();
			
			if(datainput < dataserver && validator != true){
				minDate = dataoggiit;
			}else if(datainput < dataserver && validator == true){
				minDate = minDate;
			}else{
				minDate = minDate;
			}		
			
			//$('[name="'+ dataDaName +'"]').datepicker('setEndDate', minDate);
			//$('[name="'+ dataDaName +'"]').datepicker('autoclose', true);
			var dataDa = $('[name="'+ dataDaName +'"]').val();
			if (dataDa != null && dataDa != ''){
				selectize.setValue('', true);
				selectize.clearOptions();
				var diff = calculateDaysBetweenItalianDates(dataDa, $(this).val());
				
				selectize.addOption({"cadenza":"Nessuna"});
				if (diff >13){
					selectize.addOption({"cadenza":"Settimanale"});
				}
				if (diff >59){
					selectize.addOption({"cadenza":"Mensile"});
				}

				selectize.setValue('Nessuna', false);
			}
		}
	});
}

function clickRadioPuglia(inputName, evento){
	$('[name="'+ inputName +'"]').on( evento, function() {
		if($(this).prop("checked")){
			var nazioniSelecitze = $('[name="'+ inputName.replace(new RegExp('puglia', 'g'), "nazione") +'"]').selectize()[0].selectize;
			var regioniSelecitze = $('[name="'+ inputName.replace(new RegExp('puglia', 'g'), "regione") +'"]').selectize()[0].selectize;
			var suffix = inputName.substr(0,inputName.indexOf(".")+1);
			if($(this).val() == 'true'){
				nazioniSelecitze.setValue(window.italia.nazione, false);
				regioniSelecitze.setValue(window.puglia.regione, false);
				$('[data-name="'+ suffix + 'campiPuglia' +'"]').removeAttr('hidden');
			}else {
				if($('[name="'+ inputName.replace(new RegExp('puglia', 'g'), "regione") +'"]').val() == window.puglia.regione){
					regioniSelecitze.clear();
				}
				$('[data-name="'+ suffix + 'campiPuglia' +'"]').attr('hidden','true');
				$('[name="'+ inputName.replace(new RegExp('puglia', 'g'), "provincia") +'"]').selectize()[0].selectize.disable();
			}	
		}
	});
}

function selectizeNazione(inputName){
	$('[name="'+ inputName +'"]').selectize({
		valueField: 'nazione',
	    labelField: 'nazione',
	    searchField: 'nazione',
	    onType: function(input) {
	        if (input.length < 3 && input.length > 0) {
	        	$('[name="'+ inputName +'"]').siblings('.selectize-control').find('.selectize-dropdown.single.form-control.Input-text').css('display','none');
	        } else {
	        	$('[name="'+ inputName +'"]').siblings('.selectize-control').find('.selectize-dropdown.single.form-control.Input-text').css('display','');
	        }
	    },
	    placeholder: "Seleziona",
	    options: window.nazioniList,
	    render: {
	        option: function (item, escape) {
	        	return '<div class="option">' +
	                   	'<div class="text">' +
	                   		'<span class="name">' + escape(item.nazione) + '</span>' +
	                   	'</div>' +
	                   '</div>';
	        }
	    },
	    onBlur: function() {
	    	$('[for="'+ inputName +'"]').css("display","");
	    },
	    onChange: function(value) {
	    	if (!this.disabled){
	    		$('[for="'+ inputName +'"]').css("display","none");
	    	//	$('[name="'+ inputName +'"]').siblings('div.scegliere').attr('style', 'color: #0075BF !important');
	    		$('[name="'+ inputName +'"]').siblings('div.selectize-control.form-control.Input-text').attr('style', 'border: 1px solid #0075BF !important');
	    	}
	    	var regioniSelecitze = $('[name="'+ inputName.replace(new RegExp('nazione', 'g'), "regione") +'"]').selectize()[0].selectize;
	    	regioniSelecitze.disable();
	    	regioniSelecitze.clear();
    		regioniSelecitze.clearOptions();
    		var provinceSelecitze = $('[name="'+ inputName.replace(new RegExp('nazione', 'g'), "provincia") +'"]').selectize()[0].selectize;
	    	provinceSelecitze.disable();
    		provinceSelecitze.clear();
    		provinceSelecitze.clearOptions();
	    	var comuniSelecitze = $('[name="'+ inputName.replace(new RegExp('nazione', 'g'), "comune") +'"]').selectize()[0].selectize;
	    	
    		comuniSelecitze.disable();
    		comuniSelecitze.clear();
    		comuniSelecitze.clearOptions();
    		
    		$('[name="'+ inputName.replace(new RegExp('nazione', 'g'), "comune") +'"]').removeClass('error')
    		$('[name="'+ inputName.replace(new RegExp('nazione', 'g'), "comune") +'"]').closest('div').find('label.error').remove();
	    	$('[name="'+ inputName.replace(new RegExp('nazione', 'g'), "comune") +'"]').closest('div').find('div.selectize-control.form-control.Input-text').attr('style', 'border: 1px solid #0075BF !important');
    		    		
	    	if (!value.length){
	    		regioniSelecitze.load(function(callback) {
	    		    callback(regioniList);
	    		});
	    		provinceSelecitze.load(function(callback) {
	    		    callback(provinceList);
	    		});
	    		comuniSelecitze.load(function(callback) {
	    		    callback(comuniList);
	    		});
	    		
	    		// Se comuneEstero valorizzato modifco l'interfaccia
	    		if($("input[name$='locationSet[0].comuneEstero']").val()){
					$('[name="'+ inputName.replace(new RegExp('nazione', 'g'), "puglia") +'"][value="true"]').prop("checked",false);
		    		$('[name="'+ inputName.replace(new RegExp('nazione', 'g'), "puglia") +'"][value="false"]').prop("checked",true);
		    		$('[name="'+ inputName.replace(new RegExp('nazione', 'g'), "comuneEstero") +'"]').parent().parent().removeAttr('hidden');
		    		$('[name="'+ inputName.replace(new RegExp('nazione', 'g'), "regione") +'"]').parent().parent().attr('hidden', true);
		    		$('[name="'+ inputName.replace(new RegExp('nazione', 'g'), "provincia") +'"]').parent().parent().attr('hidden', true);
		    		$('[name="'+ inputName.replace(new RegExp('nazione', 'g'), "comune") +'"]').parent().parent().attr('hidden', true);
		    		$('[name="'+ inputName.replace(new RegExp('nazione', 'g'), "cap") +'"]').parent().parent().attr('hidden', true);
		    		$('[data-name="'+ inputName.replace(new RegExp('nazione', 'g'), "campiPuglia") +'"]').attr('hidden','true');
				}
	    		
	    	}else{
	    		var data = this.options[value];
		    	$('[name="'+ inputName.replace(new RegExp('nazione', 'g'), "codNazione") +'"]').attr('value', data.codNazione);
		    	if(data.codNazione == window.italia.codNazione){
		    		regioniSelecitze.load(function(callback) {
		    			if($('[name="'+ inputName +'"]').is(':enabled')){
		    				regioniSelecitze.enable();
		    			}
		    		    callback(regioniList);
		    		});
		    		provinceSelecitze.load(function(callback) {
		    		    callback(provinceList);
		    		});
		    		comuniSelecitze.load(function(callback) {
		    		    callback(comuniList);
		    		});
		    		$('[name="'+ inputName.replace(new RegExp('nazione', 'g'), "comuneEstero") +'"]').val('');
		    		$('[name="'+ inputName.replace(new RegExp('nazione', 'g'), "comuneEstero") +'"]').parent().parent().attr('hidden', true);
		    		$('[name="'+ inputName.replace(new RegExp('nazione', 'g'), "regione") +'"]').parent().parent().removeAttr('hidden');
		    		$('[name="'+ inputName.replace(new RegExp('nazione', 'g'), "provincia") +'"]').parent().parent().removeAttr('hidden');
		    		$('[name="'+ inputName.replace(new RegExp('nazione', 'g'), "comune") +'"]').parent().parent().removeAttr('hidden');
		    		$('[name="'+ inputName.replace(new RegExp('nazione', 'g'), "cap") +'"]').parent().parent().removeAttr('hidden');
		    	}else{
		    		$('[name="'+ inputName.replace(new RegExp('nazione', 'g'), "puglia") +'"][value="true"]').prop("checked",false);
		    		$('[name="'+ inputName.replace(new RegExp('nazione', 'g'), "puglia") +'"][value="false"]').prop("checked",true);
		    		$('[name="'+ inputName.replace(new RegExp('nazione', 'g'), "comuneEstero") +'"]').parent().parent().removeAttr('hidden');
		    		$('[name="'+ inputName.replace(new RegExp('nazione', 'g'), "regione") +'"]').parent().parent().attr('hidden', true);
		    		$('[name="'+ inputName.replace(new RegExp('nazione', 'g'), "provincia") +'"]').parent().parent().attr('hidden', true);
		    		$('[name="'+ inputName.replace(new RegExp('nazione', 'g'), "comune") +'"]').parent().parent().attr('hidden', true);
		    		$('[name="'+ inputName.replace(new RegExp('nazione', 'g'), "cap") +'"]').parent().parent().attr('hidden', true);
		    		$('[data-name="'+ inputName.replace(new RegExp('nazione', 'g'), "campiPuglia") +'"]').attr('hidden','true');
		    	}
	    	}
	    }
	});
}

function selectizeRegione(inputName){
	$('[name="'+ inputName +'"]').selectize({
		valueField: 'regione',
	    labelField: 'regione',
	    searchField: 'regione',
	    onType: function(input) {
	        if (input.length < 3 && input.length > 0) {
	        	$('[name="'+ inputName +'"]').siblings('.selectize-control').find('.selectize-dropdown.single.form-control.Input-text').css('display','none');
	        } else {
	        	$('[name="'+ inputName +'"]').siblings('.selectize-control').find('.selectize-dropdown.single.form-control.Input-text').css('display','');
	        }
	    },
	    placeholder: "Seleziona",
	    options: window.regioniList,
	    render: {
	        option: function (item, escape) {
	        	return '<div class="option">' +
	                   	'<div class="text">' +
	                   		'<span class="name">' + escape(item.regione) + '</span>' +
	                   	'</div>' +
	                   '</div>';
	        }
	    },
	    onBlur: function() {
	    	$('[for="'+ inputName +'"]').css("display","");
	    },
	    onChange: function(value) {
	    	if (!this.disabled){
	    		$('[for="'+ inputName +'"]').css("display","none");
	    //		$('[name="'+ inputName +'"]').siblings('div.scegliere').attr('style', 'color: #0075BF !important');
	    		$('[name="'+ inputName +'"]').siblings('div.selectize-control.form-control.Input-text').attr('style', 'border: 1px solid #0075BF !important');
	    	}
	    	var provinceSelecitze = $('[name="'+ inputName.replace(new RegExp('regione', 'g'), "provincia") +'"]').selectize()[0].selectize;
	    	provinceSelecitze.disable();
    		provinceSelecitze.clear();
    		provinceSelecitze.clearOptions();
	    	var comuniSelecitze = $('[name="'+ inputName.replace(new RegExp('regione', 'g'), "comune") +'"]').selectize()[0].selectize;
    		comuniSelecitze.disable();
    		comuniSelecitze.clear();
    		comuniSelecitze.clearOptions();
	    	if (!value.length){
	    		provinceSelecitze.load(function(callback) {
	    			if($('[name="'+ inputName +'"]').is(':enabled')){
	    				provinceSelecitze.enable();
	    			}
	    		    callback(provinceList);
	    		});
	    		comuniSelecitze.load(function(callback) {
	    		    callback(comuniList);
	    		});
	    	}else{
		    	var data = this.options[value];
		    	$('[name="'+ inputName.replace(new RegExp('regione', 'g'), "codRegione") +'"]').attr('value', data.codRegione);
	    		provinceSelecitze.load(function(callback) {
	    			if($('[name="'+ inputName +'"]').is(':enabled')){
	    				provinceSelecitze.enable();
	    			}
	    		    callback(provincePerRegione[data.codRegione]);
	    		});
	    		comuniSelecitze.load(function(callback) {
	    		    callback(comuniPerRegione[data.codRegione]);
	    		});
	    		if (data.codRegione == window.puglia.codRegione){
	    			$('[name="'+ inputName.replace(new RegExp('regione', 'g'), "puglia") +'"][value="true"]').prop("checked",true);
		    		$('[name="'+ inputName.replace(new RegExp('regione', 'g'), "puglia") +'"][value="false"]').prop("checked",false);
		    		$('[data-name="'+ inputName.replace(new RegExp('regione', 'g'), "campiPuglia") +'"]').removeAttr('hidden');
	    		}else{
	    			$('[name="'+ inputName.replace(new RegExp('regione', 'g'), "puglia") +'"][value="true"]').prop("checked",false);
		    		$('[name="'+ inputName.replace(new RegExp('regione', 'g'), "puglia") +'"][value="false"]').prop("checked",true);
		    		$('[data-name="'+ inputName.replace(new RegExp('regione', 'g'), "campiPuglia") +'"]').attr('hidden','true');
	    		}
	    	}
	    }
	});
	$('[name="'+ inputName +'"]').selectize()[0].selectize.disable();
}

function selectizeProvincia(inputName){
	$('[name="'+ inputName +'"]').selectize({
		valueField: 'provincia',
	    labelField: 'provincia',
	    searchField: 'provincia',
	    onType: function(input) {
	        if (input.length < 3 && input.length > 0) {
	        	$('[name="'+ inputName +'"]').siblings('.selectize-control').find('.selectize-dropdown.single.form-control.Input-text').css('display','none');
	        } else {
	        	$('[name="'+ inputName +'"]').siblings('.selectize-control').find('.selectize-dropdown.single.form-control.Input-text').css('display','');
	        }
	    },
	    placeholder: "Seleziona",
	    options: window.provinceList,
	    render: {
	        option: function (item, escape) {
	        	return '<div class="option">' +
	                   	'<div class="text">' +
	                   		'<span class="name">' + escape(item.provincia) + '</span>' +
	                   	'</div>' +
	                   '</div>';
	        }
	    },
	    onBlur: function() {
	    	if (!this.items[0]){
	    		$('[for="'+ inputName +'"]').css("display","");
	    	}
	    },
	    onChange: function(value) {
	    	if (!this.disabled){
	    		$('[for="'+ inputName +'"]').css("display","none");
	    	//	$('[name="'+ inputName +'"]').siblings('div.scegliere').attr('style', 'color: #0075BF !important');
	    		$('[name="'+ inputName +'"]').siblings('div.selectize-control.form-control.Input-text').attr('style', 'border: 1px solid #0075BF !important');
	    	}
	    	var comuniSelecitze = $('[name="'+ inputName.replace(new RegExp('provincia', 'g'), "comune") +'"]').selectize()[0].selectize;
	    	comuniSelecitze.disable();
    		comuniSelecitze.clear();
    		comuniSelecitze.clearOptions();
    		if (!value.length){
    			comuniSelecitze.load(function(callback) {
    				if($('[name="'+ inputName +'"]').is(':enabled')){
    					comuniSelecitze.enable();
    				}
	    		    callback(comuniList);
	    		});
    		}else{
    			var data = this.options[value];
    	    	$('[name="'+ inputName.replace(new RegExp('provincia', 'g'), "codProvincia") +'"]').attr('value', data.codProvincia);
    			comuniSelecitze.load(function(callback) {
    				if($('[name="'+ inputName +'"]').is(':enabled')){
    					comuniSelecitze.enable();
    				}
	    		    callback(comuniPerProvincia[data.codProvincia]);
	    		});
    		}
	    }
	});
	$('[name="'+ inputName +'"]').selectize()[0].selectize.disable();
}

function selectizeComune(inputName){
	$('[name="'+ inputName +'"]').selectize({
		valueField: 'comune',
	    labelField: 'comune',
	    searchField: 'comune',
	    onType: function(input) {
	        if (input.length < 3 && input.length > 0) {
	        	$('[name="'+ inputName +'"]').siblings('.selectize-control').find('.selectize-dropdown.single.form-control.Input-text').css('display','none');
	        } else {
	        	$('[name="'+ inputName +'"]').siblings('.selectize-control').find('.selectize-dropdown.single.form-control.Input-text').css('display','');
	        }
	    },
	    placeholder: "Seleziona",
	    options: window.comuniList,
	    render: {
	        option: function (item, escape) {
	        	return '<div class="option">' +
	                   	'<div class="text">' +
	                   		'<span class="name">' + escape(item.comune) + '</span>' +
	                   	'</div>' +
	                   '</div>';
	        }
	    },
	    onBlur: function() {
	    	if (!this.items[0]){
	    		$('[for="'+ inputName +'"]').css("display","");
	    	}
	    },
	    onChange: function(value) {
	    	if (!this.disabled){
	    		$('[for="'+ inputName +'"]').css("display","none");
	    	//	$('[name="'+ inputName +'"]').siblings('div.scegliere').attr('style', 'color: #0075BF !important');
	    		$('[name="'+ inputName +'"]').siblings('div.selectize-control.form-control.Input-text').attr('style', 'border: 1px solid #0075BF !important');
	    	}
	    	if (!value.length) return;
	    	var data = this.options[value];
	    	$('[name="'+ inputName.replace(new RegExp('comune', 'g'), "codComune") +'"]').attr('value', data.codComune);
	    }
	});
	$('[name="'+ inputName +'"]').selectize()[0].selectize.disable();
}

function selectizeAttrattori(inputName){
	$('[name="'+ inputName +'"]').selectize({
	    valueField: 'etichetta',
	    labelField: 'etichetta',
	    searchField: 'etichetta',
	    placeholder: window.mexJS['js.placeholder.location.attrattori'],
	    render: {
	    	option: function(item, escape) {
	    		return '<option value="'+item.etichetta+'">'+item.etichetta+'</option>';
	        }
	    },
	    load: function(query, callback) {
	        if (query.length<3) return callback();
	        $.ajax({
	            url: window.context + '/attrattori?s=' + query.toUpperCase(),
	            type: 'GET',
	            error: function() {
	                callback();
	            },
	            success: function(res) {
	            	callback(res);
	            }
	        });
	    },
	    onChange: function(value) {
	    	if (!value.length) return;
	    	var data = this.options[value];
	    	$('[name="'+ inputName +'.attrattoreId"]').attr('value', data.attrattoreId);
	    }
	});
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

function selectizeRelazioni(inputName){
	
	if(window.flagPrm["filtroPromuovi"] == true){
		var ph = window.mexJS['js.placeholder.relazioniP'];
	} else {
		var ph = window.mexJS['js.placeholder.relazioni'];
	}
	var selectizeRelazioniObj = $('[name="'+ inputName +'"]').selectize({
	    valueField: 'titolo',
	    labelField: 'titolo',
	    searchField: 'titolo',
	    maxItems: '1',
	    placeholder: ph,
	    render: {
	    	option: function(item, escape) {
				var tipoEventoNucleo = item.tipo;
				if(tipoEventoNucleo == "EVENTO"){
					tipoEventoNucleo = "SINGOLO";
				}
	    		var selectOption = item.titolo + ' ('+ tipoEventoNucleo +') creato il ' + convertDataEngToIta(item.dataIns.substr(0,10)) + ' alle ore ' + item.dataIns.substr(10);
	    		
	    		if(item.comune){
					selectOption = selectOption +', Comune '+ item.comune;
				}
				
	    		return '<option value="'+selectOption+'">' + selectOption + '</option>';
	        }
	    },
	    load: function(query, callback) {
	        if (query.length < 3){
			//	console.log(query.length);
				selectizeRelazioniObj.selectize()[0].selectize.clearOptions(false);
				return callback();
			} 
			
	        var tipoRelazioneEvento = "EVENTO";
	        if ($("[value='EVENTO']").is(':checked')) {
	        	tipoRelazioneEvento = "RASSEGNA";
	        }
	        $.ajax({
	            url: window.context + '/titoliEventi/'+$('#idEventoPrincipale').val()+'?tipoEvento='+tipoRelazioneEvento+'&q=' + query.toUpperCase(),
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
	    	
	    	var selectOption = data.titolo+' ('+data.tipo+') creato il '+convertDataEngToIta(data.dataIns.substr(0,10))+' alle ore '+data.dataIns.substr(10);
    		
    		if(data.comune){
				selectOption = selectOption +', Comune '+ data.comune;
			} 	

	    	$('[name="'+ inputName +'.titolofull"]').attr('value', selectOption);
	    }
	});
	$("#relazioneSelect").find('.selectize-control.form-control.Input-text').removeClass('multi');
}

function selectizeRelazioniFeed(inputName, counter){
	var valueTitolo = $('[name="'+ inputName +'"]').val();
	var valueId = $('[name="'+ inputName +'.eventoRelazionatoId"]').val();
	var valueTipo = $('[name="'+ inputName +'.tipoEventoAssociato"]').val();	
	var valuePeriodo = $('[name="'+ inputName +'.periodo"]').val();
	var valueComune = $('[name="'+ inputName +'.comune"]').val();	
	var valueTitolofull = $('[name="'+ inputName +'.titolofull"]').val();
	
	var nameTitolo = 'relazioneSet[' + counter + '].titolo';
	var nameId = 'relazioneSet[' + counter + '].eventoRelazionatoId';
	var nameTipoRelazione = 'relazioneSet[' + counter + '].tipoRelazione';
	var nameTipoEvAss = 'relazioneSet[' + counter + '].tipoEventoAssociato';	
	var namePeriodo = 'relazioneSet[' + counter + '].periodo';
	var nameComune = 'relazioneSet[' + counter + '].comune';
	var nameTitolofull = 'relazioneSet[' + counter + '].titolofull';
	
	$('[name="'+ nameTitolo +'"]').val(valueTitolo);
	$('[name="'+ nameId +'"]').val(valueId);
	$('[name="'+ nameTipoEvAss +'"]').val(valueTipo);	
	$('[name="'+ namePeriodo +'"]').val(valuePeriodo);
	$('[name="'+ nameComune +'"]').val(valueComune);	
	$('[name="'+ nameTitolofull +'"]').val(valueTitolofull);

	
	var tipoRelazione = "CONTIENE";
    if ($("[value='EVENTO']").is(':checked')) {
    	tipoRelazione = "CONTENUTO";
    } else {
    	if(valueTipo == 'EVENTO'){
    		$('[data-name="relazioneSet[' + counter + '].eventoContenuto' +'"]').removeAttr('hidden');
    	} else {
    		$('[data-name="relazioneSet[' + counter + '].rassegnaContenuta' +'"]').removeAttr('hidden');
    	}
		$('[data-name="relazioneSet[' + counter + '].rassegnaAssociata' +'"]').attr('hidden','true');
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

function fileImmaginiFeed(inputName, counter){
	
	flastorder();
	
	$('#overlay').show();	
	$('[data-name="multilinguaDiv"]').removeAttr('hidden');
	var idEvento = $('#idEventoPrincipale').val();
	if(!idEvento){
		return;
	}
	var file = $('[name="'+ inputName +'"]')[0].files[0];
	var filename = file.name;
	if (file.name.lastIndexOf("\\")>0){
		filename = file.name.substr(file.name.lastIndexOf("\\")+1);
	}
	
	var idimmagine = contaimmagini;
	var formData = new FormData();	
	formData.append("immagine", file, filename, lastorder);
	
    $.ajax({
        url : context+'/submitFile/immagine/' + idEvento + "?ordine=" + lastorder,
        data : formData,
        type : 'POST',
        processData: false,
        contentType: false,
        success : function(data){
        	var json = JSON.parse(data);
        	$('[name="immagineSet[' + counter + '].immagineId"]').val(json.immagineId);
        	$('[name="immagineSet[' + counter + '].estensione"]').val(json.estensione);
        	$('[name="immagineSet[' + counter + '].dimensione"]').val(json.dimensione);
        	$('[name="immagineSet[' + counter + '].nomeOriginale"]').val(json.nomeOriginale);
        	$('[name="immagineSet[' + counter + '].nomeOriginale"]').after(json.nomeOriginale);
        	$('[name="immagineSet[' + counter + '].ordine"]').val(json.ordine);
    		$('[name="immagineSetLoad"]').val("");
    		$('#immagini-tab').removeClass('rch');
    		$('#media-tab').removeClass('rch');
    		
    		if($('ul#myTabCE.nav.nav-tabs>li').find('a.nav-link.rch').length == 0){
    			$('#labelValid').hide();
    		}	
    		
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
    		} else if (error.status == 415) {
    			alertify.alert(window.mexJS['js.error.errore'], "<img src=\""+context+"/assets/images/ERRORE.svg\"><br/><br/><br/>"+window.mexJS['js.error.file.valido']).set('label', 'Chiudi'); 
    		}
    		$('[data-deletebutton="immagineSet[' + counter + ']"]').click();
    		$('[name="immagineSetLoad"]').val("");
    		$('#overlay').hide();
    	}
    });
}


function isFileImage(file) {
    const acceptedImageTypes = ['image/gif', 'image/jpeg', 'image/png'];
    var isFile =  file && $.inArray(file['type'], acceptedImageTypes);
    
    return isFile;
}


function initDownload(inputName, event){
	$('[data-name="'+ inputName +'"]').click(function (event) {
        event.preventDefault();
    }).one(event, function(evento) {
    	var counter = inputName.substr(inputName.indexOf("[") + 1);
    	counter = counter.substr(0, counter.indexOf("]"));
		var immagineId = $('[name="immagineSet[' + counter + '].immagineId"]').val();
    	var estensione = $('[name="immagineSet[' + counter + '].estensione"]').val();
    	var eventoId = $('#idEventoPrincipale').val();
    	var hrefVal = $('[data-name="'+ inputName +'"]').attr("href") + "/getFile/" + eventoId + "/immagine/" + immagineId + "." + estensione;
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
    	var hrefVal = $('[data-name="'+ inputName +'"]').attr("href") + "/getFile/" + eventoId + "/documento/" + documentoId + "." + estensione;
    	$.ajax({
			url: hrefVal,
			method: "GET",
			xhrFields: {
		        responseType: 'blob'
		    },
		    success: function(data) {
				var a = document.createElement('a');
			    var url = window.URL.createObjectURL(data);
			    a.href = url;
			    a.download = documentoId + '.' + estensione;
			    document.body.append(a);
			    a.click();
			    a.remove();
			    window.URL.revokeObjectURL(url);
			}
		});
	});
}

function initVisualizza(inputName, event){
	$('[data-name="'+ inputName +'"]').click(function (event) {
        event.preventDefault();
    }).one(event, function(evento) {
    	var counter = inputName.substr(inputName.indexOf("[") + 1);
    	counter = counter.substr(0, counter.indexOf("]"));
		var immagineId = $('[name="immagineSet[' + counter + '].immagineId"]').val();
    	var estensione = $('[name="immagineSet[' + counter + '].estensione"]').val();
    	var eventoId = $('#idEventoPrincipale').val();
    	var hrefVal = $('[data-name="'+ inputName +'"]').attr("href") + "/getFile/" + eventoId + "/immagine/" + immagineId + "." + estensione;
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
	var idEvento = $('#idEventoPrincipale').val();
	if(!idEvento){
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
        url : context+'/submitFile/documento/' + idEvento  + "?ordine=" + lastorderallegati,
        data : formData,
        type : 'POST',
        processData: false,
        contentType: false,
        success : function(data){
        	var json = JSON.parse(data);
        	$('[name="documentoSet[' + counter + '].documentoId"]').val(json.documentoId);
        	$('[name="documentoSet[' + counter + '].estensione"]').val(json.estensione);
        	$('[name="documentoSet[' + counter + '].dimensione"]').val(json.dimensione);
        	$('[name="documentoSet[' + counter + '].nomeOriginale"]').val(json.nomeOriginale);
        	$('[name="documentoSet[' + counter + '].nomeOriginale"]').after(json.nomeOriginale);
        	$('[name="documentoSet[' + counter + '].ordine"]').val(lastorderallegati);
        	$('[name="documentoSetLoad"]').val("");
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
    		$('[data-deletebutton="documentoSet[' + counter + ']"]').click();
    		$('[name="documentoSetLoad"]').val("");
    		$('#overlay').hide();
    	}
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
    	var hrefVal = $('[data-name="'+ inputName +'"]').attr("href") + "/getFile/" + eventoId + "/documento/" + documentoId + "." + estensione;
    	window.open(hrefVal, '_blank');
	});
}

//function selectizeProgetto(inputName){
//	$('[name="'+ inputName +'"]').selectize({
//	//plugins: ['no_results'],
//	valueField: 'titoloProgetto',
//    labelField: 'titoloProgetto',
//    searchField: 'titoloProgetto',
//    placeholder: window.mexJS['js.placeholder.progetto'],
//    render: {
//        option: function (item, escape) {
//        	return '<div class="option">' +
//                   	'<div class="text">' +
//                   		'<span class="name">' + escape(item.titoloProgetto) + '</span>' +
//                   	'</div>' +
//                   '</div>';
//        }
//    },
//    onChange: function(value) {
//    	if (!value.length) return;
//    	var data = this.options[value];
//    	$('[name="progettoId"]').attr('value', data.progettoId);
//    }});
//}
//$.fn.dataTable.moment('DD/MM/YYYY');

jQuery.extend( jQuery.fn.dataTableExt.oSort, {
    "date-euro-pre": function ( a ) {
        var x;
 
        if ( a.trim() !== '' ) {
            var frDatea = a.trim().split(' ');
            var frTimea = (undefined != frDatea[1]) ? frDatea[1].split(':') : [00,00,00];
            var frDatea2 = frDatea[0].split('/');
            x = (frDatea2[2] + frDatea2[1] + frDatea2[0] + frTimea[0] + frTimea[1] + ((undefined != frTimea[2]) ? frTimea[2] : 0)) * 1;
        }
        else {
            x = Infinity;
        }
 
        return x;
    },
 
    "date-euro-asc": function ( a, b ) {
        return a - b;
    },
 
    "date-euro-desc": function ( a, b ) {
        return b - a;
    }
} );



$('#t_revisioni').DataTable({
	language: { "url": context+"/assets/json/Italian.json" },
	scrollY: "396px",
    scrollX: true,
    paging:  true,
	pageLength: 10,
	lengthMenu: [[10,25, 50, 100, 250], [10,25, 50, 100, 250]],
	mark: true,
    deferRender: true,
    autoWidth: false,
    scrollCollapse: true,
    data: '',
    columns: [
    	{ name: "id", data: "logId" },
        { name: "tipo", data: "tipoOperazione" },
        { name: "data", data: "dataModifica"},
        { name: "operazioni", data: "operazioni" },
        { name: "utente", data: "nomeUtente" },
        { name: "stato", data: "descrizioneStato" }
        
    ],
    columnDefs: [
    	{ targets: [0], "width": 190, searchable: false, orderable: true, visible: false },
    	{ targets: [1], "width": 190, "className":"operazione", searchable: true, orderable: true, visible: false, render:$.fn.dataTable.render.text() },    	
    	{ targets: [2], "width": 190, type: 'date-euro', searchable: true, orderable: true, visible: true, render: function(data, type, full, meta) {   
    		var datamodifica = convertDataEngToIta(data.substr(0,10)) + data.substr(10);
    		var data =  moment(datamodifica, 'DD/MM/YYYY HH:mm:ss').format('DD/MM/YYYY HH:mm:ss');
    	    return data;
        } },
    	{ targets: 3, "width": 190, searchable: true, orderable: true, visible: true, render:$.fn.dataTable.render.text() }, 
    	{ targets: 4, "width": 190, searchable: true, orderable: true, visible: true, render: function(data, type, full, meta) {
    		
    		if(full.denominazioneAttivita){
      			data = "<div class=\"denomTooltipover\" data-toggle=\"tooltip\" data-container=\"body\" title=\""+full.denominazioneAttivita+"\" data-html=\"true\">"+data+"</div>";     
    		}                     	
              return data;              		
          }
    	},
    	{ targets: 5, "width": 190, searchable: true, orderable: true, visible: true, render:$.fn.dataTable.render.text() }, 
    ],
    order: [[ 2, "desc" ]],
    initComplete: function(settings, json) {
    	$("[name='t_revisioni_length']").attr("data-ignorefield","");
    },
    drawCallback: function(){
    	$('[data-toggle="popover"]').popover({ 'trigger': 'hover'});
    	$('[data-toggle="popoverclick"]').popover({  placement : 'top', container: "body", trigger : 'click', delay: {'show' : 0, 'hide' : 300} });
    	$('[data-toggle="tooltip"]').tooltip({ 'trigger': 'hover'});
    }
});


function tConvert (time) {
	  // Check correct time format and split into components
	  time = time.toString ().match (/^([01]\d|2[0-3])(:)([0-5]\d)(:[0-5]\d)?$/) || [time];

	  if (time.length > 1) { // If time format correct
	    time = time.slice (1);  // Remove full string match value
	    time[5] = +time[0] < 12 ? 'am' : 'pm'; // Set AM/PM
	    time[0] = +time[0] % 12 || 12; // Adjust hours
	  }
	  return time.join (''); // return adjusted time or original string
}

$('#tipologiaMIBACT').selectize({
	valueField: 'tipologiaMIBACT',
    labelField: 'tipologiaMIBACT',
    searchField: 'tipologiaMIBACT',
    placeholder: 'Seleziona',
    options: window.tipologieMIBACT,
    render: {
        option: function(item, escape) {
        	return '<div class="option">'+escape(item.tipologiaMIBACT)+'</div>';
        }
    },
    onChange: function(value) {
    	if (!value.length) return;
    	var data = this.options[value];
    	$('[name="idMIBACT"]').attr('value', data.idMIBACT);
    }
});


$(document).on('change', 'input[name="ticket.flagGratisConvenzioni"]' , function(){	
	if ($('input[name="ticket.flagGratisConvenzioni"]').is(":checked"))
	{
		$("#formEventCreating").validate().resetForm();
		$('input[name="ticket.convenzioniAttiveG"]').attr("required","required");
	    $("#convenzioniAttiveG").show();	  
	}else{
		$("#formEventCreating").validate().resetForm();
		$('input[name="ticket.convenzioniAttiveG"]').val('').removeAttr("required");
		$("#convenzioniAttiveG").hide();
		
	}		
});

$(document).on('change', 'input[name="ticket.flagRidottoConvenzioni"]' , function(){	
	if ($('input[name="ticket.flagRidottoConvenzioni"]').is(":checked"))
	{
		$("#formEventCreating").validate().resetForm();
		$('input[name="ticket.convenzioniAttiveR"]').attr("required","required");
	    $("#convenzioniAttiveR").show();	  
	}else{
		$("#formEventCreating").validate().resetForm();
		$('input[name="ticket.convenzioniAttiveR"]').val('').removeAttr("required");
		$("#convenzioniAttiveR").hide();		
	}		
});


$(document).on('change', 'input[name="ticket.tipoTicket"]' , function(){	
	if ($('input[name="ticket.tipoTicket"]:checked').val() == 'indefinito' || $('input[name="ticket.tipoTicket"]:checked').val() == 'gratuito'){
		$('input[name="ticket.convenzioniAttiveG"]').val('').removeAttr("required");
		$('input[name="ticket.convenzioniAttiveR"]').val('').removeAttr("required");		
	}
	$('input[name="ticket.flagGratisConvenzioni"]').prop('checked', false);
	$('input[name="ticket.flagRidottoConvenzioni"]').prop('checked', false);		
});

function labeldataOrario(){	
	$( "div[data-name$='.dataOrarioContinuato']" ).each(function( index ) {		
		var timeA = $(this).find('input[name$=".orarioApertura"]').val();	
		var timeB = $(this).find('input[name$=".orarioChiusura"]').val();		
		if(timeA !='' && timeB !=''){  
			var splitted1 = timeA.split(":");
			var splitted2 = timeB.split(":");
			var time1 = new Date();
			var time2 = new Date();													
			//time1.setHours(splitted1[0], splitted1[1], 0, 0);
			//time2.setHours(splitted2[0], splitted2[1], 0, 0);	
			
			time1.setHours(splitted1[0], splitted1[1], 0, 0);
			var time3 = splitted2[0]+":"+splitted2[1];
			
			if(time3 == '00:00'){
				time2 = time2.setHours("23", "45", 0, 0);
				time2 = new Date(time2);
			}else{
				time2.setHours(splitted2[0], splitted2[1], 0, 0);	
				time2 = new Date(time2.getTime() - 15*60000);
			}
	
	
			if (time2 < time1) {		
				$(this).find('#labelperiodo').show();
			}else{
				$(this).find('#labelperiodo').hide();
			}		
		}		
	});
	
	$( "div[data-name$='.dataOrarioNonContinuato']" ).each(function( index ) {
		var timeA = $(this).find('input[name$=".orarioAperturaPomeriggio"]').val();
		var timeB = $(this).find('input[name$=".orarioChiusuraPomeriggio"]').val();	
		//var timeC = $(this).find('input[name$=".orarioAperturaMattina"]').val();	
		//var timeD = $(this).find('input[name$=".orarioChiusuraMattina"]').val();		
		if(timeA !='' && timeB !=''){
			var splitted1 = timeA.split(":");
			var splitted2 = timeB.split(":");
			var time1 = new Date();
			var time2 = new Date();													
			
			//time1.setHours(splitted1[0], splitted1[1], 0, 0);
			//time2.setHours(splitted2[0], splitted2[1], 0, 0);	
			
			
			time1.setHours(splitted1[0], splitted1[1], 0, 0);
			var time3 = splitted2[0]+":"+splitted2[1];
			
			if(time3 == '00:00'){
				time2 = time2.setHours("23", "45", 0, 0);
				time2 = new Date(time2);
			}else{
				time2.setHours(splitted2[0], splitted2[1], 0, 0);	
				time2 = new Date(time2.getTime() - 15*60000);
			}
	

			if (time2 < time1 ) {		
				$(this).find('#labelperiodo').show();
			}else{
				$(this).find('#labelperiodo').hide();
			}
		}			
	});		
}	 

$(document).on( "change", "#tipologiaMIBACT", function() {
	
	
	if( $(this).val() != ''){
		$(this).removeClass('error');
		 $(this).closest('div').find('div.selectize-control.form-control.Input-text.single').removeAttr('style');
		
		 $(this).closest('div').find('label#tipologiaMIBACT-error').remove();
	}
	
});


$(document).on( "change", "input.form-control-date.Input-text.endS-d.data, input.form-control-date.Input-text.startS-d.data", function() {
		
	if( $(this).val() != ''){
		$(this).removeClass('error');		
		$(this).closest('div').find('label.error').remove();
	}
	
});



$(document).on( "click", ".campiora", function() {
	$('.ui-timepicker-viewport').scrollTop(0);
});


	
function reloadtime(){		
	$( 'input[name$=".orarioApertura"]' ).each(function( index ) {			
			$(this).timepicker({
				timeFormat: 'HH:mm',
			    interval: 15,
			    minTime: '00:15',
			    maxTime: '23:45',
			    startTime: '00:15',
			    dynamic: false,
			    dropdown: true,
			    scrollbar: true,
			    change: function(time) {		    	
			    	var element = $(this), text;			 
			    	var timepicker = element.timepicker();			
			    	var orario = timepicker.format(time);
					if(orario !=''){
						orario = moment(orario,'HH:mm').add(15, 'minutes').format('HH:mm');										
						$(this).closest('div[data-name$=".dataOrarioContinuato"]').find('input[name$=".orarioChiusura"]').val('').timepicker('destroy');
						$(this).closest('div[data-name$=".dataOrarioContinuato"]').find('input[name$=".orarioChiusura"]').timepicker({
							timeFormat: 'HH:mm',
							interval: 15,  
							minTime: '00:00',
							//maxTime: '00:00',
							//defaultTime: orario,
							startTime: orario, 
							dynamic: false, 
							dropdown: true, 
							scrollbar: true,
						    change: function(time) {
								var element = $(this), text;						    	
						    	var timepicker = element.timepicker();
						    	var orariox = timepicker.format(time);
						    	//orariox = moment(orariox,'HH:mm').add(15, 'minutes').format('HH:mm');				
								
								var timeA = $(this).closest('div[data-name$=".dataOrarioContinuato"]').find('input[name$=".orarioApertura"]').val();
								var timeB = orariox;

									if(timeA !='' && timeB !=''){ 
										var splitted1 = timeA.split(":");
										var splitted2 = timeB.split(":");
										var time1 = new Date();
										var time2 = new Date();													
										//time1.setHours(splitted1[0], splitted1[1], 0, 0);
										//time2.setHours(splitted2[0], splitted2[1], 0, 0);		
										
										time1.setHours(splitted1[0], splitted1[1], 0, 0);
										var time3 = splitted2[0]+":"+splitted2[1];
										
										if(time3 == '00:00'){
											time2 = time2.setHours("23", "45", 0, 0);
											time2 = new Date(time2);
										}else{
											time2.setHours(splitted2[0], splitted2[1], 0, 0);	
											time2 = new Date(time2.getTime() - 15*60000);
										}	
										
										if (time2 < time1) {		
											$(this).closest('div[data-name$=".dataOrarioContinuato"]').find('#labelperiodo').show();
										}else{
											$(this).closest('div[data-name$=".dataOrarioContinuato"]').find('#labelperiodo').hide();
										}
									}	
							}
						});
						$(this).closest('div[data-name$=".dataOrarioContinuato"]').find('input[name$=".orarioChiusura"]').addClass("campiora");
					}else{
						$(this).closest('div[data-name$=".dataOrarioContinuato"]').find('input[name$=".orarioChiusura"]').val('');
						$(this).closest('div[data-name$=".dataOrarioContinuato"]').find('input[name$=".orarioChiusura"]').removeClass("campiora");
					}
					//$(this).closest('div[data-name$=".dataOrarioContinuato"]').find('input[name$=".orarioChiusura"]').val('');
				}
			
			});
	});
	

	
	$( 'input[name$=".orarioChiusura"]' ).each(function( index ) {
		if($(this).val() !=''){			
			var atime = $(this).closest('div[data-name$=".dataOrarioContinuato"]').find('input[name$=".orarioApertura"]').val();
			if( atime ==''){	var mtime = '00:15'; }else{ var mtime = atime; 			
			mtime = moment(mtime,'HH:mm').add(15, 'minutes').format('HH:mm');
			}
			
			$(this).addClass("campiora");
			$(this).timepicker({	    
				timeFormat: 'HH:mm',
			    interval: 15,
			    minTime: '00:00',
			    //maxTime: '23:45',
			    startTime: mtime,
			    dynamic: false,
			    dropdown: true,
			    scrollbar: true,
			    change: function(time) {	
			    	var element = $(this), text;
			    	var timepicker = element.timepicker();
			    	var orario = timepicker.format(time);
			    	//orario = moment(orario,'HH:mm').add(15, 'minutes').format('HH:mm');				
					
					var timeA = $(this).closest('div[data-name$=".dataOrarioContinuato"]').find('input[name$=".orarioApertura"]').val();
					var timeB = orario;

					if(timeA !='' && timeB !=''){ 
						var splitted1 = timeA.split(":");
						var splitted2 = timeB.split(":");
						var time1 = new Date();
						var time2 = new Date();													
						//time1.setHours(splitted1[0], splitted1[1], 0, 0);
						//time2.setHours(splitted2[0], splitted2[1], 0, 0);		
						
						time1.setHours(splitted1[0], splitted1[1], 0, 0);
						var time3 = splitted2[0]+":"+splitted2[1];
						
						if(time3 == '00:00'){
							time2 = time2.setHours("23", "45", 0, 0);
							time2 = new Date(time2);
						}else{
							time2.setHours(splitted2[0], splitted2[1], 0, 0);	
							time2 = new Date(time2.getTime() - 15*60000);
						}	
						
						if (time2 < time1) {		
							$(this).closest('div[data-name$=".dataOrarioContinuato"]').find('#labelperiodo').show();
						}else{
							$(this).closest('div[data-name$=".dataOrarioContinuato"]').find('#labelperiodo').hide();
						}
					}
					
					
				}			    
			});
		}else{
			$(this).removeClass("campiora");
		}
	});
		
	$( 'input[name$=".orarioAperturaMattina"]' ).each(function( index ) {		
		$(this).timepicker({ 	    
			timeFormat: 'HH:mm',
		    interval: 15,
		    minTime: '00:15',
		    maxTime: '23:15',
		    startTime: '00:15',
		    dynamic: false,
		    dropdown: true,
		    scrollbar: true, 
		    change: function(time) {		    	
		    	var element = $(this), text;
		    	var timepicker = element.timepicker();
		    	var orario = timepicker.format(time);
		    	orario = moment(orario,'HH:mm').add(15, 'minutes').format('HH:mm');	
				if(orario !=''){
					$(this).closest('div[data-name$=".dataOrarioNonContinuato"]').find('input[name$=".orarioChiusuraMattina"]').val('');
					$(this).closest('div[data-name$=".dataOrarioNonContinuato"]').find('input[name$=".orarioChiusuraMattina"]').timepicker('destroy').timepicker({
						timeFormat: 'HH:mm',
						interval: 15,  
						minTime: orario,
						maxTime: '23:30',
						//defaultTime: orario,
						startTime: orario, 
						dynamic: false, 
						dropdown: true, 
						scrollbar: true,
					    change: function(time) {		    	
					    	var element = $(this), text;
					    	var timepicker = element.timepicker();
					    	var orario = timepicker.format(time);
					    	orario = moment(orario,'HH:mm').add(15, 'minutes').format('HH:mm');						    	
							if(orario !=''){
								$(this).closest('div[data-name$=".dataOrarioNonContinuato"]').find('input[name$=".orarioAperturaPomeriggio"]').val('');
								$(this).closest('div[data-name$=".dataOrarioNonContinuato"]').find('input[name$=".orarioAperturaPomeriggio"]').timepicker('destroy').timepicker({
									timeFormat: 'HH:mm',
									interval: 15,  
									minTime: orario,
									maxTime: '23:45',
									//defaultTime: orario,
									startTime: orario, 
									dynamic: false, 
									dropdown: true, 
									scrollbar: true,
								    change: function(time) {		    	
								    	var element = $(this), text;
								    	var timepicker = element.timepicker();
								    	var orario = timepicker.format(time);
								    	orario = moment(orario,'HH:mm').add(15, 'minutes').format('HH:mm');								    	
										if(orario !=''){					
											$(this).closest('div[data-name$=".dataOrarioNonContinuato"]').find('input[name$=".orarioChiusuraPomeriggio"]').val('');
											$(this).closest('div[data-name$=".dataOrarioNonContinuato"]').find('input[name$=".orarioChiusuraPomeriggio"]').timepicker('destroy').timepicker({
												timeFormat: 'HH:mm',
												interval: 15,  
												//minTime: orario,
												//maxTime: '00:15',
												//defaultTime: orario,
												startTime: orario, 
												dynamic: false, 
												dropdown: true, 
												scrollbar: true,
											    change: function(time) {											    	
											    	var element = $(this), text;
											    	var timepicker = element.timepicker();
													var timeA = $(this).closest('div[data-name$=".dataOrarioNonContinuato"]').find('input[name$=".orarioAperturaPomeriggio"]').val();
													var timeB = timepicker.format(time);														
														if(timeA !='' && timeB !=''){ 
															var splitted1 = timeA.split(":");
															var splitted2 = timeB.split(":");
															var time1 = new Date();
															var time2 = new Date();													
															//time1.setHours(splitted1[0], splitted1[1], 0, 0);
															//time2.setHours(splitted2[0], splitted2[1], 0, 0);																
															time1.setHours(splitted1[0], splitted1[1], 0, 0);
															var time3 = splitted2[0]+":"+splitted2[1];
															
															if(time3 == '00:00'){
																time2 = time2.setHours("23", "45", 0, 0);
																time2 = new Date(time2);
															}else{													
																
																time2.setHours(splitted2[0], splitted2[1], 0, 0);	
																time2 = new Date(time2.getTime() - 15*60000);
															}
															
															if (time2 < time1) {		
																$(this).closest('div[data-name$=".dataOrarioNonContinuato"]').find('#labelperiodo').show();
															}else{
																$(this).closest('div[data-name$=".dataOrarioNonContinuato"]').find('#labelperiodo').hide();
															}
														}								
											    	
											    }
											});
											//$(this).closest('div[data-name$=".dataOrarioNonContinuato"]').find('input[name$=".orarioChiusuraPomeriggio"]').val('');
											$(this).closest('div[data-name$=".dataOrarioNonContinuato"]').find('input[name$=".orarioChiusuraPomeriggio"]').addClass("campiora");
														
										}else{
											$(this).closest('div[data-name$=".dataOrarioNonContinuato"]').find('input[name$=".orarioChiusuraPomeriggio"]').removeClass("campiora");
										}	
									 } 	     	    
								});	
								//$(this).closest('div[data-name$=".dataOrarioNonContinuato"]').find('input[name$=".orarioAperturaPomeriggio"]').val('');
								$(this).closest('div[data-name$=".dataOrarioNonContinuato"]').find('input[name$=".orarioAperturaPomeriggio"]').addClass("campiora");
							}else{
								$(this).closest('div[data-name$=".dataOrarioNonContinuato"]').find('input[name$=".orarioAperturaPomeriggio"]').removeClass("campiora");
							}	
						}
					});	
					//$(this).closest('div[data-name$=".dataOrarioNonContinuato"]').find('input[name$=".orarioChiusuraMattina"]').val('');
					$(this).closest('div[data-name$=".dataOrarioNonContinuato"]').find('input[name$=".orarioChiusuraMattina"]').addClass("campiora");
				}else{
					$(this).closest('div[data-name$=".dataOrarioNonContinuato"]').find('input[name$=".orarioChiusuraMattina"]').removeClass("campiora");
				}
			}		
		});		
	});
	
	$( 'input[name$=".orarioChiusuraMattina"]' ).each(function( index ) {		
		var atime = $(this).closest('div[data-name$=".dataOrarioNonContinuato"]').find('input[name$=".orarioAperturaMattina"]').val();
		if( atime ==''){	var mtime = '00:15'; }else{ var mtime = atime; mtime = moment(mtime,'HH:mm').add(15, 'minutes').format('HH:mm'); }
		
		if($(this).val() !=''){
		$(this).addClass("campiora");
		$(this).timepicker('destroy').timepicker({ 	    
			timeFormat: 'HH:mm',
		    interval: 15,
		    minTime: mtime,
		    maxTime: '23:30',
		    startTime: mtime,
		    dynamic: false,
		    dropdown: true,
		    scrollbar: true,
		    change: function(time) {		    	
		    	var element = $(this), text;
		    	var timepicker = element.timepicker();
		    	var orario = timepicker.format(time);
		    	orario = moment(orario,'HH:mm').add(15, 'minutes').format('HH:mm');	
				if(orario !=''){
					$(this).closest('div[data-name$=".dataOrarioNonContinuato"]').find('input[name$=".orarioAperturaPomeriggio"]').val('').timepicker('destroy');
					$(this).closest('div[data-name$=".dataOrarioNonContinuato"]').find('input[name$=".orarioAperturaPomeriggio"]').timepicker({
						timeFormat: 'HH:mm',
						interval: 15, 
						minTime: orario,
						maxTime: '23:45',
						//defaultTime: orario,
						startTime: orario,
						dynamic: false, 
						dropdown: true, 
						scrollbar: true,
					    change: function(time) {		    	
					    	var element = $(this), text;
					    	var timepicker = element.timepicker();
					    	var orario = timepicker.format(time);
					    	orario = moment(orario,'HH:mm').add(15, 'minutes').format('HH:mm');			    
							if(orario !=''){					
								$(this).closest('div[data-name$=".dataOrarioNonContinuato"]').find('input[name$=".orarioChiusuraPomeriggio"]').timepicker('destroy').val('');
								$(this).closest('div[data-name$=".dataOrarioNonContinuato"]').find('input[name$=".orarioChiusuraPomeriggio"]').timepicker({
									timeFormat: 'HH:mm',
									interval: 15,  
									//minTime: orario,
									//maxTime: '00:15',
									//defaultTime: orario,
									startTime: orario, 
									dynamic: false, 
									dropdown: true, 
									scrollbar: true,
									change: function(time) { 
										
								    	var element = $(this), text;
								    	var timepicker2 = element.timepicker();
								    	
											var timeA = $(this).closest('div[data-name$=".dataOrarioNonContinuato"]').find('input[name$=".orarioAperturaPomeriggio"]').val();
											var timeB = timepicker2.format(time);
												
												if(timeA !='' && timeB !=''){ 
													var splitted1 = timeA.split(":");
													var splitted2 = timeB.split(":");
													var time1 = new Date();
													var time2 = new Date();													
													//time1.setHours(splitted1[0], splitted1[1], 0, 0);
													//time2.setHours(splitted2[0], splitted2[1], 0, 0);			
													
													time1.setHours(splitted1[0], splitted1[1], 0, 0);
													var time3 = splitted2[0]+":"+splitted2[1];
													
													if(time3 == '00:00'){
														time2 = time2.setHours("23", "45", 0, 0);
														time2 = new Date(time2);
													}else{
														time2.setHours(splitted2[0], splitted2[1], 0, 0);	
														time2 = new Date(time2.getTime() - 15*60000);
													}
											
													if (time2 < time1) {		
														$(this).closest('div[data-name$=".dataOrarioNonContinuato"]').find('#labelperiodo').show();
													}else{
														$(this).closest('div[data-name$=".dataOrarioNonContinuato"]').find('#labelperiodo').hide();
													}
												}											
								
									}
								   });
								$(this).closest('div[data-name$=".dataOrarioNonContinuato"]').find('input[name$=".orarioChiusuraPomeriggio"]').addClass("campiora");		
							}else{
								$(this).closest('div[data-name$=".dataOrarioNonContinuato"]').find('input[name$=".orarioChiusuraPomeriggio"]').removeClass("campiora");
							}
							//$(this).closest('div[data-name$=".dataOrarioNonContinuato"]').find('input[name$=".orarioChiusuraPomeriggio"]').val('');
							
						 } 
					});	
					
					$(this).closest('div[data-name$=".dataOrarioNonContinuato"]').find('input[name$=".orarioAperturaPomeriggio"]').addClass("campiora");
				}else{
					$(this).closest('div[data-name$=".dataOrarioNonContinuato"]').find('input[name$=".orarioAperturaPomeriggio"]').removeClass("campiora");
				}
				//$(this).closest('div[data-name$=".dataOrarioNonContinuato"]').find('input[name$=".orarioAperturaPomeriggio"]').val('');
			}
		
		});
		}else{ 
			$(this).removeClass("campiora");		
		}
	});
	
	$( 'input[name$=".orarioAperturaPomeriggio"]' ).each(function( index ) {
		
		var atime = $(this).closest('div[data-name$=".dataOrarioNonContinuato"]').find('input[name$=".orarioChiusuraMattina"]').val();
		if( atime ==''){	var mtime = '00:15'; }else{ var mtime = atime; mtime = moment(mtime,'HH:mm').add(15, 'minutes').format('HH:mm');}
		
		if($(this).val() !=''){
			$(this).addClass("campiora");
		$(this).timepicker({ 	    
			timeFormat: 'HH:mm',
		    interval: 15,
		    minTime: mitime,
		    maxTime: '23:45',
		    startTime: mitime,
		    dynamic: false,
		    dropdown: true,
		    scrollbar: true,
		    change: function(time) {		    	
		    	var element = $(this), text;
		    	var timepicker = element.timepicker();
		    	var orario = timepicker.format(time);		    	
		    	orario = moment(orario,'HH:mm').add(15, 'minutes').format('HH:mm');			    
						if(orario !=''){					
							$(this).closest('div[data-name$=".dataOrarioNonContinuato"]').find('input[name$=".orarioChiusuraPomeriggio"]').timepicker('destroy').val('');
							$(this).closest('div[data-name$=".dataOrarioNonContinuato"]').find('input[name$=".orarioChiusuraPomeriggio"]').timepicker({
								timeFormat: 'HH:mm',
								interval: 15,  
								//minTime: orario,
								//maxTime: '23:45',
								//defaultTime: orario,
								startTime: orario, 
								dynamic: false, 
								dropdown: true, 
								scrollbar: true,
								change: function(time) {
							    	
									var element = $(this), text;
							    	var timepicker = element.timepicker();
							    	
									var timeA = $(this).closest('div[data-name$=".dataOrarioNonContinuato"]').find('input[name$=".orarioAperturaPomeriggio"]').val();
									var timeB = timepicker.format(time);
										
										if(timeA !='' && timeB !=''){ 
											var splitted1 = timeA.split(":");
											var splitted2 = timeB.split(":");
											var time1 = new Date();
											var time2 = new Date();													
											//time1.setHours(splitted1[0], splitted1[1], 0, 0);
											//time2.setHours(splitted2[0], splitted2[1], 0, 0);		
											
											time1.setHours(splitted1[0], splitted1[1], 0, 0);
											var time3 = splitted2[0]+":"+splitted2[1];
											
											if(time3 == '00:00'){
												time2 = time2.setHours("23", "45", 0, 0);
												time2 = new Date(time2);
											}else{
												time2.setHours(splitted2[0], splitted2[1], 0, 0);	
												time2 = new Date(time2.getTime() - 15*60000);
											}												
										
											
											if (time2 < time1) {		
												$(this).closest('div[data-name$=".dataOrarioNonContinuato"]').find('#labelperiodo').show();
											}else{
												$(this).closest('div[data-name$=".dataOrarioNonContinuato"]').find('#labelperiodo').hide();
											}
										}
								}
							    });
							$(this).closest('div[data-name$=".dataOrarioNonContinuato"]').find('input[name$=".orarioChiusuraPomeriggio"]').addClass("campiora");										
						}else{
							$(this).closest('div[data-name$=".dataOrarioNonContinuato"]').find('input[name$=".orarioChiusuraPomeriggio"]').removeClass("campiora");
						}					
						//$(this).closest('div[data-name$=".dataOrarioNonContinuato"]').find('input[name$=".orarioChiusuraPomeriggio"]').val('');
			 }
		});
		}else{
			$(this).removeClass("campiora");
			}	
	});
	
	$( 'input[name$=".orarioChiusuraPomeriggio"]' ).each(function( index ) {
		
		var atime = $(this).closest('div[data-name$=".dataOrarioNonContinuato"]').find('input[name$=".orarioAperturaPomeriggio"]').val();
		if( atime ==''){	var mtime = '00:15'; }else{ var mtime = atime; mtime = moment(mtime,'HH:mm').add(15, 'minutes').format('HH:mm');}
		
		if($(this).val() !=''){
		$(this).addClass("campiora");
				$(this).timepicker({
					timeFormat: 'HH:mm',
				    interval: 15,
				    //minTime: mtime,
				   // maxTime: '00:00',
				    startTime: mtime,
				    dynamic: false,
				    dropdown: true,
				    scrollbar: true,
				    change: function(time) {
				    	
				    	var element = $(this), text;
				    	var timepicker = element.timepicker();
				    	
						var timeA = $(this).closest('div[data-name$=".dataOrarioNonContinuato"]').find('input[name$=".orarioAperturaPomeriggio"]').val();
						var timeB = timepicker.format(time);
							
							if(timeA !='' && timeB !=''){ 
								var splitted1 = timeA.split(":");
								var splitted2 = timeB.split(":");
								var time1 = new Date();
								var time2 = new Date();													
								//time1.setHours(splitted1[0], splitted1[1], 0, 0);
								//time2.setHours(splitted2[0], splitted2[1], 0, 0);		
								
								time1.setHours(splitted1[0], splitted1[1], 0, 0);
								var time3 = splitted2[0]+":"+splitted2[1];
								
								if(time3 == '00:00'){
									time2 = time2.setHours("23", "45", 0, 0);
									time2 = new Date(time2);
								}else{
									time2.setHours(splitted2[0], splitted2[1], 0, 0);	
									time2 = new Date(time2.getTime() - 15*60000);
								}	
								
								if (time2 < time1) {		
									$(this).closest('div[data-name$=".dataOrarioNonContinuato"]').find('#labelperiodo').show();
								}else{
									$(this).closest('div[data-name$=".dataOrarioNonContinuato"]').find('#labelperiodo').hide();
								}
							}								
				    }				    
				    
				});
		}else{
			$(this).removeClass("campiora");
			}
	});	

};



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
	
	if(contaimmagini == 1 ){ $("#myTab7").hide(); }else{ $("#myTab7").show(); }	
	if(contadocumenti == 1){ $("#myTab9").hide(); }else{ $("#myTab9").show(); }	
	if(contalink == 1){	     $("#vidTab").hide(); }else{ $("#vidTab").show(); }
	
	if(contaimmaginivip == 1 && contaimmaginivipagg == 1){	$("#VIPimmtab").hide(); }else{	$("#VIPimmtab").show(); }	
	if(contadocumentivip == 1 && contadocumentivipagg == 1){ $("#myVIPdc").hide(); }else{ $("#myVIPdc").show(); }	
	if(contalinkvip == 1 && contalinkvipagg == 1){	$("#vidVIPTab").hide(); }else{	$("#vidVIPTab").show(); }
	
}

function fileClick(but){
	$('button#'+but).click();
}

function fileClickImg(but){		
	if(contaimmagini < 17){
		$('button#'+but).click();
	}else{
		alertify.alert("Errore", "<img src=\""+context+"/assets/images/ERRORE.svg\"><br/><br/><br/>Hai raggiunto il numero massimo per l'inserimento di immagini.").set('label', 'Chiudi'); 
	}	
}

function fileClickDoc(but){		
	if(contadocumenti < 17){
		$('button#'+but).click();
	}else{
		alertify.alert("Errore", "<img src=\""+context+"/assets/images/ERRORE.svg\"><br/><br/><br/>Hai raggiunto il numero massimo per l'inserimento di allegati.").set('label', 'Chiudi'); 
	}
}

$(document).ready(function() {
	
	if(window.flagPrm["filtroPromuovi"] == true){
		$("#gestioneAccF").attr('hidden',true);
		$("#promozioneAccF").removeAttr('hidden');
	}
	
	if(window.flagPrm["filtroGestisci"] == true){
		$("#promozioneAccF").attr('hidden',true);
		//$("#gestioneEvento").removeAttr('hidden');
	}
		
	if ($('input[name="ticket.flagGratisConvenzioni"]').is(":checked"))
	{
		$('input[name="ticket.convenzioniAttiveG"]').attr("required","required");
	    $("#convenzioniAttiveG").show();	    
	}	
	
	if ($('input[name="ticket.flagRidottoConvenzioni"]').is(":checked"))
	{
		$('input[name="ticket.convenzioniAttiveR"]').attr("required","required");
	    $("#convenzioniAttiveR").show();
	}
	
	labeldataOrario();
	reloadtime();	
	listalingue();
	
});

$(document).on('change', 'input[name$=".banner"]', function() {
	if($(this).prop( "checked") == true){		
		$('#boxMedia').find('input[name$=".banner"]').prop( "checked", false );
		$(this).prop( "checked", true);
	}
});

$(document).on('click', 'a#media-tab', function() {
	
	$('ul#docTab > li').each(function( index ) {
		  $(this).removeClass('active');
	});	
	$('ul#docTab > li > a').each(function( index ) {
		  $(this).removeClass('active');
	});
	
	$('ul#docTab > li:first-child a').click();
	$('ul#docTab > li:first-child').click();	
	
	$('ul#myTab7 > li').each(function( index ) {
		  $(this).removeClass('active');
	});	
	$('ul#myTab7 > li > a').each(function( index ) {
		  $(this).removeClass('active');
	});		
	$('ul#myTab7 > li:first-child a').click();
	
	
	$('ul#myTab9 > li').each(function( index ) {
		  $(this).removeClass('active');
	});	
	$('ul#myTab9 > li > a').each(function( index ) {
		  $(this).removeClass('active');
	});	
	$('ul#myTab9 > li:first-child a').click();
	
	
	$('ul#vidTab > li').each(function( index ) {
		  $(this).removeClass('active');
	});	
	$('ul#vidTab > li > a').each(function( index ) {
		  $(this).removeClass('active');
	});	
	$('ul#vidTab > li:first-child a').click();
	
});

$(document).on( "click", "#resetline, #linereset, .resetline", function() {
	
	$(this).closest('div.hblock').find('.form-control-date').each(function( index ) {		
		$(this).val('');	
	});		
	reloadtime();
	$(this).closest('div.row').find('#labelperiodo').hide();
	
});

	 
	$(document).on( "click", ".dropdownbox", function() { 
		  $(this).next("ul.nav.nav-tabs").toggleClass("showMenu");
		  $(this).next("ul.nav.nav-tabs > li > a").click(function(){
	        //$(".dropdownbox > p").text($(this).text());
			  $(this).next("ul.nav.nav-tabs").removeClass("showMenu");
	      });
	  });
	  
	 	
	  $(document).on( "click", "ul#myTab7 li", function() {
		  var ckid = $(this).find('a').html();		  
		  if(ckid != 'IT'){		  
			  $('.contaimmagini').find('textarea[name$=".credits"]').each(function( index ) {			  
				  $(this).attr('readonly', true);
			  });			  
		  }else{
			  $('.contaimmagini').find('textarea[name$=".credits"]').each(function( index ) {				  
				  $(this).attr('readonly', false);
			  });
		  }	
		  if(ckid != 'IT'){		  
			  $('.contaimmagini').find('input[name$=".ordine"]').each(function( index ) {			  
				  $(this).attr('readonly', true);
			  });			  
		  }else{
			  $('.contaimmagini').find('input[name$=".ordine"]').each(function( index ) {				  
				  $(this).attr('readonly', false);
			  });
		  }	
		  
		  
		  
	  }); 
	  $(document).on( "change", "select#mobiletabLinguaImg", function() {	  
		  var ckid = $(this).val();		  
		  if(ckid != 'IT'){		  
			  $('.contaimmagini').find('textarea[name$=".credits"]').each(function( index ) {			  
				  $(this).attr('readonly', true);
			  });			  
		  }else{
			  $('.contaimmagini').find('textarea[name$=".credits"]').each(function( index ) {				  
				  $(this).attr('readonly', false);
			  });
		  }	
		  if(ckid != 'IT'){		  
			  $('.contaimmagini').find('input[name$=".ordine"]').each(function( index ) {			  
				  $(this).attr('readonly', true);
			  });			  
		  }else{
			  $('.contaimmagini').find('input[name$=".ordine"]').each(function( index ) {				  
				  $(this).attr('readonly', false);
			  });
		  }	
	  });
	  	   
	  $(document).on( "click", "ul#vidTab li", function() {	  
		  var ckid = $(this).find('a').html();		  
		  if(ckid != 'IT'){		  
			  $('.contalink').find('textarea[name$=".credits"]').each(function( index ) {			  
				  $(this).attr('readonly', true);
			  });	
			  $('.contalink').find('input[name$=".link"]').each(function( index ) {			  
				  $(this).attr('readonly', true);
			  });
		  }else{
			  $('.contalink').find('textarea[name$=".credits"]').each(function( index ) {			  
				  $(this).attr('readonly', false);
			  });
			  $('.contalink').find('input[name$=".link"]').each(function( index ) {				  
				  $(this).attr('readonly', false);
			  });
		  }	
	  });
	  
	  $(document).on( "change", "select#mobiletabLinguaVid", function() {		  
		  var ckid = $(this).val();		  
		  if(ckid != 'IT'){		  
			  $('.contalink').find('textarea[name$=".credits"]').each(function( index ) {			  
				  $(this).attr('readonly', true);
			  });	
			  $('.contalink').find('input[name$=".link"]').each(function( index ) {			  
				  $(this).attr('readonly', true);
			  });
		  }else{
			  $('.contalink').find('textarea[name$=".credits"]').each(function( index ) {			  
				  $(this).attr('readonly', false);
			  });
			  $('.contalink').find('input[name$=".link"]').each(function( index ) {				  
				  $(this).attr('readonly', false);
			  });
		  }			
	  });
	  
	  $('#mobiletabMenu, #mobiletabLingua, #mobiletabLinguaTic, #mobiletabAllegato, #mobiletabLinguaImg, #mobiletabLinguaDoc, #mobiletabLinguaVid').on('change', function (e) {
		  var $optionSelected = $("option:selected", this);
		  $optionSelected.tab('show')
		});  	  

	    	  
	  $(function() {  
		    $("textarea[maxlength]").bind('input propertychange', function() {  
		        var maxLength = $(this).attr('maxlength');  
		        if ($(this).val().length > maxLength) {  
		            $(this).val($(this).val().substring(0, maxLength));  
		        }  
		    })  
		});	  
	  
	  
	  $(document).ready(function() { 
	  $(".contarelazioni").find('input.inputtotext.toevento').each(function( index ) {			  
		  if($(this).val() == 'EVENTO'){
			  $(this).after('SINGOLO');
		  }else{
			  $(this).after($(this).val());
		  }
	  });	
	  
	  });
	  
	  
	  $(document).on( "click", "#chiudievento", function() {		  
		  if(readOnly == false){
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
$(document).on("change", "#itbox input, #itbox textarea", function() {

	if(	$('#itbox').find('label.error:visible').length == 0){			
		$('#it-tab').removeClass('rch');
	}
	
	if(	$('ul#myTabDG.nav.nav-tabs>li').find('a.nav-link.rch').length == 0 && $('#datigeneralibox').find('label.error:visible').length == 0){			
		$('#datigenerali-tab').removeClass('rch');		
	}
	
	if($('ul#myTabCE.nav.nav-tabs>li').find('a.nav-link.rch').length == 0){
		$('#labelValid').hide();
	}
});


$(document).on("change", "#rubox input, #rubox textarea", function() {
	
	if(	$('#rubox').find('label.error:visible').length == 0){			
		$('#ru-tab').removeClass('rch');
	}	
	
	if(	$('ul#myTabDG.nav.nav-tabs>li').find('a.nav-link.rch').length == 0 && $('#datigeneralibox').find('label.error:visible').length == 0){			
		$('#datigenerali-tab').removeClass('rch');		
	}
	
	if($('ul#myTabCE.nav.nav-tabs>li').find('a.nav-link.rch').length == 0){
		$('#labelValid').hide();
	}
});

$(document).on("change", "#tipologiaMIBACT", function() {
	if(	$('ul#myTabDG.nav.nav-tabs>li').find('a.nav-link.rch').length == 0 && $('#datigeneralibox').find('label.error:visible').length == 0){			
		$('#datigenerali-tab').removeClass('rch');		
	}
//	if(	$('#itbox').find('label.error:visible').length == 0){			
	//	$('#it-tab').removeClass('rch');
	//}	
	if($('ul#myTabCE.nav.nav-tabs>li').find('a.nav-link.rch').length == 0 ){
		$('#labelValid').hide();
		
	}
});

$(document).on("change", "#locationbox select", function() {
	if(	$('#locationbox').find('label.error:visible').length == 0){			
		$('#location-tab').removeClass('rch');
	}
	if($('ul#myTabCE.nav.nav-tabs>li').find('a.nav-link.rch').length == 0){
		$('#labelValid').hide();
	}
});

$(document).on( "change", "#itbox select", function() {
	if(	$('#datigeneralibox').find('label.error:visible').length == 0){			
		$('#datigenerali-tab').removeClass('rch');
	}
	if($('ul#myTabCE.nav.nav-tabs>li').find('a.nav-link.rch').length == 0){
		$('#labelValid').hide();
	}
});

$(document).on( "change", "#periodobox input, #periodobox textarea", function() {
	if(	$('#periodobox').find('label.error:visible').length == 0){			
		$('#periodo-tab').removeClass('rch');
	}
	if($('ul#myTabCE.nav.nav-tabs>li').find('a.nav-link.rch').length == 0){
		$('#labelValid').hide();
	}	
});

$(document).on( "change", "#contattibox input, #contattibox textarea", function() {
	if(	$('#contattibox').find('label.error:visible').length == 0){			
		$('#contatti-tab').removeClass('rch');
	}
	if($('ul#myTabCE.nav.nav-tabs>li').find('a.nav-link.rch').length == 0){
		$('#labelValid').hide();
	}
});

$(document).on( "click", ".removeButton", function() {
	if(	$('#contattibox').find('label.error:visible').length == 0){			
		$('#contatti-tab').removeClass('rch');
	}
	if($('ul#myTabCE.nav.nav-tabs>li').find('a.nav-link.rch').length == 0){
		$('#labelValid').hide();
	}
});

$(document).on( "change", "#locationbox input, #locationbox textarea", function() {
	if(	$('#locationbox').find('label.error:visible').length == 0){			
		$('#location-tab').removeClass('rch');
	}
	if($('ul#myTabCE.nav.nav-tabs>li').find('a.nav-link.rch').length == 0){
		$('#labelValid').hide();
	}	
});

/*
$(document).on( "change", "#videobox input, #videobox textarea", function() {
	if(	$('#videobox').find('label.error:visible').length == 0){			
		$('#video-tab').removeClass('rch');
	}
	if($('#videobox').find('label.error:visible').length  == 0 && $('#documentibox').find('label.error:visible').length == 0 && $('#immaginibox').find('label.error:visible').length  == 0){
		$('#media-tab').removeClass('rch');
	}
	if($('ul#myTabCE.nav.nav-tabs>li').find('a.nav-link.rch').length == 0){
		$('#labelValid').hide();
	}
});

$(document).on( "change", "#documentibox input, #documentibox textarea", function() {
	if(	$('#documentibox').find('label.error').length == 0){			
		$('#documenti-tab').removeClass('rch');
	}
	if($('#documentibox').find('label.error).length  == 0 && $('#videobox').find('label.error:visible').length == 0 && $('#immaginibox').find('label.error:visible').length  == 0){
		$('#media-tab').removeClass('rch');
	}
	if($('ul#myTabCE.nav.nav-tabs>li').find('a.nav-link.rch').length == 0){
		$('#labelValid').hide();
	}
});
*/







//gestione bandi
function listabandipersonali(){	
	
	if(codicefiscale){	
	var url = context+"/progetti?";
	
	if(cfPiva){
		url+="cfPiva="+cfPiva;
	}
	
	if(cfTitolare)
	{
		if(cfPiva){
			url+="&";
		}		
		url+= "cfTitolare=" + cfTitolare;			
	}	
	
	// azione per il caricamento dei bando/progetti propri
	$.ajax({
		url :url,
		async:false, 	    	
		success: function(data){			
			var data = data.items;		

								
			 data = unique(
data ,   (a, b) => (a.id_bando === b.id_bando) & (a.id_progetto === b.id_progetto)
);		

$(".listaboxbandipersonali").empty();
			var contarisultati = 0;			
			for(i=0; i<data.length; i++){	
			
				var body = "<div class=\"boxbandipersonali\"><div style=\"display:table; width: 100%;\">" +
						   "<div style=\"display:table-cell; vertical-align:middle;padding:20px;\" class=\"bandichk\">" +
						   "<input type=\"radio\" class=\"radio-ruolo option-input inlist radio radioprogetto listabandi\" name=\"bandoselezione\" value=\""+data[i].id_bando+"\" style=\"top: 0px;\">" +
						   "<input type=\"hidden\" name=\"bando.bandoId\" disabled=\"disabled\" value=\""+data[i].id_bando+"\">" +
						   "</div><div style=\"display:table-cell; vertical-align:middle;\"><strong>Progetto:</strong> " +
						   "<input type=\"hidden\" name=\"progetto.progettoId\" disabled=\"disabled\" value=\""+data[i].id_progetto+"\">" +
						   "<input type=\"hidden\" name=\"progetto.titoloProgetto\" disabled=\"disabled\" value=\""+data[i].titolo_progetto+"\"> <span id=\"progettoutente\">"+data[i].titolo_progetto+"</span><br/>" +
						   "<strong>Bando:</strong> <input type=\"hidden\" name=\"bando.titoloBando\" disabled=\"disabled\" value=\""+data[i].nome_bando+"\"> <span id=\"bandoutente\">"+data[i].nome_bando+"</span><br>" +
						   "<strong>Organizzazione:</strong> <input type=\"hidden\" name=\"progetto.nomeOrganizzazione\" disabled=\"disabled\" value=\""+data[i].denominazione+"\"> <span id=\"organizzazioneutente\">"+data[i].denominazione+"</span>" +
						   "</div></div></div>";
				contarisultati++;
				$(".listaboxbandipersonali").append(body);					
			}
			
			$("#numerorisultati").empty().html(contarisultati);
			
			if(contarisultati == 0){
				$("#numerorisultati").empty().html('0');
				$("#messaggiozerorisultati").show();
			}	
			$('#overlay').hide();
			$('.boxFinanziamento').show();	
		},
    	error: function(error){
    		alertify.alert("Abbiamo riscontrato un problema", "<img src=\""+context+"/assets/images/ERRORE.svg\"><br/><br/><br/>Al momento non &egrave; possibile concludere l'operazione. Ci scusiamo e ti invitiamo a riprovare in un altro momento.").set('label', 'Chiudi'); 
        	$("input[name='radiofinanziato'][value='No']").prop("checked", true);
    		$('.boxFinanziamento').hide();
    		$('#BOZZA').prop("disabled", false);
    		$('#inseriscibando').prop("disabled", false);	
    		$(".listabandi").find(".boxbandipersonali").removeClass("active");
    		$(".listabandi").find("input[type='hidden']").prop("disabled", true);	
    		$(".listabandi").find("input[type='radio']").prop('checked', false);		
    		$("#accbandi").prop('checked', false);		
    		$('.divacc').hide();
    		$(".listaboxbandicomuni").empty();	
    		$('#overlay').hide();
    	}
	});
	
	}else{
		$('#overlay').hide();
		$("#numerorisultati").empty().html('0');
		$("#messaggiozerorisultati").show();	
		$('.boxFinanziamento').show();	
	}
	
	
	
}


function unique(a, fn) {
  if (a.length === 0 || a.length === 1) {
    return a;
  }
  if (!fn) {
    return a;
  }

  for (let i = 0; i < a.length; i++) {
    for (let j = i + 1; j < a.length; j++) {
      if (fn(a[i], a[j])) {
        a.splice(i, 1);
      }
    }
  }
  return a;
}





function listaboxbandicomuni(){	
	
	$.ajax({
		url : context+"/bandi",
		async:false, 	    	
		success: function(data){	
			
			var data = data.items;				
			 data = unique(
data ,   (a, b) => (a.id_bando === b.id_bando) & (a.nome_bando === b.nome_bando)
);
			$(".listaboxbandicomuni").empty();
			
			for(i=0; i<data.length; i++){
				var body = "<div class=\"boxbandipersonali\"><div style=\"display:table; width: 100%;\">" +
						   "<div style=\"display:table-cell; vertical-align:middle;padding:0px 20px;width: 70px;\" class=\"bandichk\">" +
						   "<input type=\"radio\" class=\"radio-ruolo option-input inlist radio radioprogetto altribandi\" name=\"bandoselezione\" value=\""+data[i].id_bando+"\" style=\"top: 0px;\">" +
						   "<input type=\"hidden\" disabled=\"disabled\" name=\"bando.bandoId\" disabled=\"disabled\" value=\""+data[i].id_bando+"\">" +
						   "</div><div style=\"display:table-cell; vertical-align:middle;\"><strong>Bando:</strong> " +
						   "<input type=\"hidden\" disabled=\"disabled\" name=\"bando.titoloBando\" disabled=\"disabled\" value=\""+data[i].nome_bando+"\"> <span id=\"bandoutente\">"+data[i].nome_bando+"</span></div>" +
						   "</div></div>";	
				$(".listaboxbandicomuni").append(body);					
			}
			
			$('.listaboxbandicomuni').show();	
			$('#overlay').hide();
			$('.divacc').show();
		},
    	error: function(error){
    		alertify.alert("Abbiamo riscontrato un problema", "<img src=\""+context+"/assets/images/ERRORE.svg\"><br/><br/><br/>Al momento non &egrave; possibile concludere l'operazione. Ci scusiamo e ti invitiamo a riprovare in un altro momento.").set('label', 'Chiudi'); 
    		$('.listaboxbandicomuni').empty();	
    		$('#overlay').hide();
    	}
	
	
	});	
}



$(document).on( "click", ".radiofinanziato", function() {	
	if( $('input[name="radiofinanziato"]:checked').val() == 'Si') { 	
		$('#BOZZA').prop("disabled", true);			
		if(codicefiscale !=''){	
			if( $(".listaboxbandipersonali").is(':empty') ){		
				$('#overlay').show();
				setTimeout(function(){ listabandipersonali(); }, 500);			
			}else{
				$('.boxFinanziamento').show();
			}	
		}else{
		//	$('#overlay').show();
			$('#messaggiozerorisultati').show();
			$('.boxFinanziamento').show();
			$(".listaboxbandicomuni").empty();
			//setTimeout(function(){ listaboxbandicomuni(); }, 500);
		}
		
	}else{
		$('.boxFinanziamento').hide();
		$('#BOZZA').prop("disabled", false);
		$('#inseriscibando').prop("disabled", false);	
		$(".listabandi").find(".boxbandipersonali").removeClass("active");
		$(".listabandi").find("input[type='hidden']").prop("disabled", true);	
		$(".listabandi").find("input[type='radio']").prop('checked', false);		
		$("#accbandi").prop('checked', false);		
		$('.divacc').hide();
		$(".listaboxbandicomuni").empty();		
	}
});


$(document).on( "click", ".radioprogetto", function() {	
	if($(this).is(':checked')) {
		$(".listabandi").find(".boxbandipersonali").removeClass("active");
		$(".listabandi").find("input[type='hidden']").prop("disabled", true);			
		$(this).closest(".boxbandipersonali").addClass("active");	
		$(this).closest(".boxbandipersonali").find("input[type='hidden']").prop("disabled", false);
		$('#BOZZA').prop("disabled", false);	
		$('#inseriscibando').prop("disabled", false);	
			
		if($(this).hasClass("altribandi")) { 
			if($('#accbandi').is(':checked') == true && $(this).is(':checked') == true) {
				$('#BOZZA').prop("disabled", false);	
				$('#inseriscibando').prop("disabled", false);	
			}else{
				$('#BOZZA').prop("disabled", true);
				$("#accbandi").prop('checked', false).prop("disabled", false);
				$('#inseriscibando').prop("disabled", true);	
			}		
		}else{
			$("#accbandi").prop('checked', false).prop("disabled", true);	
			$("#accbandimodel").prop('checked', false);
		}		
	}
});





$(document).on( "click", "#accbandi, .altribandi", function() {	
	
	if($('#accbandi').is(':checked') == true && $('.altribandi').is(':checked') == true) {
		$('#BOZZA').prop("disabled", false);	
		$('#inseriscibando').prop("disabled", false);	
	}else{
		$('#BOZZA').prop("disabled", true);
		$('#inseriscibando').prop("disabled", true);	
	}		
	if($('#accbandi').is(':checked') == true && $('.listabandi').is(':checked') == true) {
		$('#BOZZA').prop("disabled", false);	
		$('#inseriscibando').prop("disabled", false);	
	}else if($('#accbandi').is(':checked') == false && $('.listabandi').is(':checked') == true) {
		$('#BOZZA').prop("disabled", false);	
		$('#inseriscibando').prop("disabled", false);	
	}	
});


$(document).on( "click", "#altribandi", function() {
	if($(".listaboxbandicomuni").find('.boxbandipersonali').length == 0){	
		$('#overlay').show();
		$(".listaboxbandicomuni").empty();
		setTimeout(function(){ listaboxbandicomuni(); }, 500);
	}
});

$(document).on( "click", "#espandibando", function() {		
	$("#chiudibando").show();
	$("#boxbandoaggiungilabel").show();	
	$(this).hide();
});

$(document).on( "click", "#chiudibando", function() {			
	$("#boxbandoaggiungilabel").hide();	
	$(this).hide();
	$("#espandibando").show();
});

$(document).on( "click", "#eliminabando", function() {
	
	alertify.confirm("<i class=\"icon-errore\" style=\"font-size:64px; color:#a66300;\"></i><br><br>"+window.mexJS['js.button.sicuro']).set({
		title:window.mexJS['js.tipologia.attenzione']}).set('labels', {ok:'Si', cancel:'No'}).set('onok', function(){			
		
			$("#boxbando").find("input[type='hidden']").prop("disabled", true);	
			$("#boxbando").find("input[type='hidden']").val("");		
			$("#progettoutente").empty();
			$("#bandoutente").empty();
			$("#organizzazioneutente").empty();	
			$("#boxbando").hide();		
			$("#boxbandoaggiungilabel").hide();	
			$("#chiudibando").hide();
			$("#espandibando").show();	
			$("#boxbandoaggiungi").show();
			opendmstoastsuccess('Operazione conclusa con successo!'); 
	
		}).set('oncancel', function(){
			return;
		});	
});

$(document).off("click", "#modificabando, #aggiungibando").on( "click", "#modificabando, #aggiungibando", function() {	
	
//modalAggiungibando	
	$("#boxFinanziamento").remove();
		
		var idbutton = $(this).attr('id');	
		if(idbutton == 'aggiungibando'){		
			$('#modalAggiungibando').find('.modal-title').empty().html('Associa un bando o un progetto');		
			$('#modalAggiungibando').find('#inseriscibando').empty().html('Associa');
		}else if(idbutton == 'modificabando'){
			$('#modalAggiungibando').find('.modal-title').empty().html('Modifica i dati del bando');		
			$('#modalAggiungibando').find('#inseriscibando').empty().html('Modifica');		
		}
	
		if(codicefiscale != '' || codicefiscale != false){	
			if( $('#modalAggiungibando').find(".listaboxbandipersonali").is(':empty') ){
				$('#overlay').show();
				setTimeout(function(){ listabandipersonali(); }, 500);
			}		
			$('#modalAggiungibando').find(".listaboxbandicomuni").empty();	
			$('#modalAggiungibando').find(".listaboxbandicomuni").hide();	
		}else{
			
			//$('#overlay').show();
			$('#modalAggiungibando').find("#numerorisultati").empty().html('0');
			$('#modalAggiungibando').find('#messaggiozerorisultati').show();	
			$('#modalAggiungibando').find(".listaboxbandicomuni").empty();
		//	setTimeout(function(){ listaboxbandicomuni(); }, 500);
		}		
		$('#modalAggiungibando').find(".listabandi").find(".boxbandipersonali").removeClass("active");
		$('#modalAggiungibando').find('#inseriscibando').prop("disabled", true);			
		
		$('#modalAggiungibando').find(".radioprogetto").prop('checked', false);		
		$('#modalAggiungibando').find("#accbandi").prop('checked', false);		
		$('#modalAggiungibando').find('.divacc').hide();
		$("#modalAggiungibando").modal('show');		
});

function nuovobando(){		
	
	alertify.confirm("<i class=\"icon-errore\" style=\"font-size:64px; color:#a66300;\"></i><br><br>"+window.mexJS['js.button.sicuro']).set({
		title:window.mexJS['js.tipologia.attenzione']}).set('labels', {ok:'Si', cancel:'No'}).set('onok', function(){
		
				var bandoId = $('.boxbandipersonali.active').find('input[name="bando.bandoId"]').val();
				var progettoId = $('.boxbandipersonali.active').find('input[name="progetto.progettoId"]').val();
				var titoloBando = $('.boxbandipersonali.active').find('input[name="bando.titoloBando"]').val();
				var titoloProgetto = $('.boxbandipersonali.active').find('input[name="progetto.titoloProgetto"]').val();
				var nomeOrganizzazione = $('.boxbandipersonali.active').find('input[name="progetto.nomeOrganizzazione"]').val();	
				$('#boxbando').find('input[name="bando.bandoId"]').val(bandoId).prop("disabled", false);
				
				if(titoloBando){
					$('#labelbando').show();
					$('#boxbando').find('input[name="bando.titoloBando"]').prop("disabled", false).val(titoloBando); $("#bandoutente").html(titoloBando);
				}else{
					$('#labelbando').hide();
				}
				
				if(titoloProgetto){
					$('#labelprogetto').show();
					$('#boxbando').find('input[name="progetto.progettoId"]').val(progettoId).prop("disabled", false);
					$('#boxbando').find('input[name="progetto.titoloProgetto"]').prop("disabled", false).val(titoloProgetto);  $("#progettoutente").html(titoloProgetto);
				}else{
					$('#boxbando').find('input[name="progetto.progettoId"]').val('').prop("disabled", true); $("#progettoutente").html('');
					$('#boxbando').find('input[name="progetto.titoloProgetto"]').prop("disabled", true).val('');
					$('#labelprogetto').hide();
				}	
				
				if(nomeOrganizzazione){
					$('#labelorganizzazione').show();
					$('#boxbando').find('input[name="progetto.nomeOrganizzazione"]').prop("disabled", false).val(nomeOrganizzazione); $("#organizzazioneutente").html(nomeOrganizzazione);
				}else{
					$('#boxbando').find('input[name="progetto.nomeOrganizzazione"]').prop("disabled", true).val(''); $("#organizzazioneutente").html('');
					$('#labelorganizzazione').hide();
				}
				
				$('#boxbando').show();		
				$('#boxbandoaggiungi').hide();	
				$('.listaboxbandipersonali').empty();
				$('.listaboxbandicomuni').empty();
				$('.divacc').hide();
				$("#accbandi").prop('checked', false);	
				$('#inseriscibando').prop("disabled", true);
				$('#modalAggiungibando').modal('hide');	
				opendmstoastsuccess('Operazione conclusa con successo!'); 
	
		}).set('oncancel', function(){
			return;
		});
}

/* Funzione rimozione tag html da campi TextAreavi*/
function strip(html)
{
   var tmp = document.createElement("DIV");
   
   tmp.innerHTML = html;
   
   return tmp.textContent||tmp.innerText;
}

$(document).ready(function(){
	$(document).on("change, click, focusout", "textarea", function() {
			$(this).val(function(index, old){
		
				old = old.replace(/‘/g,"'");
				old = old.replace(/’/g,"'");
				old = old.replace(/“/g,"\"");
				old = old.replace(/”/g,"\""); 			
				old = old.replace(/–/g,"-");
				old = old.replace(/—/g,"-"); 				
				old = strip(old);
				
				//old = old.replace(/([\u2700-\u27BF]|[\uE000-\uF8FF]|\uD83C[\uDC00-\uDFFF]|\uD83D[\uDC00-\uDFFF]|[\u2011-\u26FF]|\uD83E[\uDD10-\uDDFF])/g, '');
				old = cleanString(old);
		
				return old;
		});
	});
});



$(document).ready(function(){
	$(document).on("change, click, focusout", "input[name^='datiGenerali.titoloMulti']", function() {
		$(this).val(function(index, old){
			
			old = old.replace(/‘/g,"'");
			old = old.replace(/’/g,"'");
			old = old.replace(/“/g,"\"");
			old = old.replace(/”/g,"\""); 			
			old = old.replace(/–/g,"-");
			old = old.replace(/—/g,"-"); 	
			
			old = strip(old);
						
			//old = old.replace(/([\u2700-\u27BF]|[\uE000-\uF8FF]|\uD83C[\uDC00-\uDFFF]|\uD83D[\uDC00-\uDFFF]|[\u2011-\u26FF]|\uD83E[\uDD10-\uDDFF])/g, '');			
			old = cleanString(old);
			
			return old;
		});
	});

	if(window.idEntitaEventoCorrente != window.idEntita){
		$("#MODIFICA").empty().html("Richiedi modifica");
	}
});


$(document).on("change", '.contaimmagini input[name$=".ordine"]', function() {	  		
	  $(".contaimmagini").find('input[name$=".ordine"]').each(function( index ) {			  
		  $(this).closest('div').find("label.error").remove();
		  $(this).removeClass("error");
	  });		  
	  
});

$(document).on("change", '.contadocumenti input[name$=".ordine"]', function() {	  		
	  $(".contadocumenti").find('input[name$=".ordine"]').each(function( index ) {			  
		  $(this).closest('div').find("label.error").remove();
		  $(this).removeClass("error");
	  });	  
});


$(document).on("change", '.contadocumenti input[name$=".titoloMulti.IT"]', function() {	  		
	  $(".contadocumenti").find('input[name$=".titoloMulti.IT"]').each(function( index ) {			  
		  $(this).closest('div').find("label.error").remove();
		  $(this).removeClass("error");
	  });	  
});


$(document).on("change", '.contalink input[name$=".ordine"]', function() {	  		
	  $(".contalink").find('input[name$=".ordine"]').each(function( index ) {			  
		  $(this).closest('div').find("label.error").remove();
		  $(this).removeClass("error");
	  });	  
});

$(document).on("change", '.contalink input[name$=".link"]', function() {	  		
	  $(".contalink").find('input[name$=".link"]').each(function( index ) {			  
		  $(this).closest('div').find("label.error").remove();
		  $(this).removeClass("error");
	  });	  
});

$(document).on("change", '.contalink input[name$=".titoloMulti.IT"]', function() {	  		
	  $(".contalink").find('input[name$=".titoloMulti.IT"]').each(function( index ) {			  
		  $(this).closest('div').find("label.error").remove();
		  $(this).removeClass("error");
	  });	  
});
	
	
var lastorder=0;
function flastorder(){
	let listorder = [0];
	$('.contaimmagini').find('input[name$=".ordine"]').each(function( index ) {			  
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
	$('.contadocumenti').find('input[name$=".ordine"]').each(function( index ) {			  
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
	$('.contalink').find('input[name$=".ordine"]').each(function( index ) {			  
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
});
	

$(document).on("change", "#mediabox input", function() {	
	
	if(	$('#immaginibox').find('label.error').length == 0){			
		$('#immagini-tab').removeClass('rch');	
	}
	if(	$('#documentibox').find('label.error').length == 0){			
		$('#documenti-tab').removeClass('rch');		
	}
	
	if(	$('#videobox').find('label.error').length == 0){			
		$('#video-tab').removeClass('rch');
	}
	
	if($('#immaginibox').find('label.error').length  == 0 && $('#videobox').find('label.error').length == 
		0 && $('#documentibox').find('label.error').length  == 0){
		$('#media-tab').removeClass('rch');
	}
	if($('ul#myTabCE.nav.nav-tabs>li').find('a.nav-link.rch').length == 0){
		$('#labelValid').hide();
	}
});

$(document).ready(function() {	
	
	$('#periodobox').find('input').each(function(){
 	   if ($(this).hasClass("radio-ruolo")){	    		   
 		   if($(this).is(":checked")){
 				   
 			   if($(this).val() == 'false' ){	    			   
 			     
 			   
 			    var valoredataDa = $(this).closest('.contaperiodo').find('input[name$=".dataDa"]').val();
 			     			   		    			   
 				var dateParts = valoredataDa.split('/');
         				            		            		
         		var dataoggiit = moment(dataserver, 'YYYY-MM-DD').format('DD/MM/YYYY');
         		
         		
         		 
         		 if(validator != true){
         			 
         		    			$(this).closest('.contaperiodo').find('input[name$=".dataA"]').datepicker('setStartDate', dataoggiit);			            	
		            		}
 				  			   
 			   
 			   //$(this).closest('.contaperiodo').find('input[name$=".dataA"]').datepicker('setStartDate', null);  
 			    		   
 			   }
 }
 	   } 
    }); 
	
});





















