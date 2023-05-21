function icontabelle(){	
//console.log("Entrato icon tabelle"+ new Date());
	
	var w = screen.width;	
	if(w > 480){	
		$('div.DTFC_LeftBodyLiner > table > tbody > tr > td:first-child').each(function( index ) {        		
			$(this).find('button').each(function( index ) {
				$(this).attr('data-toggle', 'tooltip').tooltip({ 'trigger': 'hover', container: "body"});		
			});
			$(this).find("a").attr('data-toggle', 'tooltip').tooltip({ 'trigger': 'hover', container: "body"});	
		});	
		$('div.DTFC_LeftBodyLiner > table > tbody > tr > td:nth-child(2)').each(function( index ) {
			$(this).find("div").attr('data-toggle', 'tooltip').tooltip({ 'trigger': 'hover', container: "body"});	
		});
		$('[data-toggle="tooltip"]').tooltip({ 'trigger': 'hover', container: "body"});	
	}else{		
		$('div.dataTables_scrollBody > table > tbody > tr > td:first-child').each(function( index ) {        		
			$(this).find('button').each(function( index ) {
				$(this).tooltip('destroy').removeAttr('data-toggle');		
			});
			$(this).find("a").tooltip('destroy').removeAttr('data-toggle');	
		});	
		$('div.dataTables_scrollBody> table > tbody > tr > td:nth-child(2)').each(function( index ) {
			$(this).find("div").attr('data-toggle', 'tooltip').tooltip({ 'trigger': 'hover', container: "body"});	
		});
		$('[data-toggle="tooltip"]').tooltip({ 'trigger': 'hover', container: "body"});			
	}
}

function recoveryfilter(filter){	
    $("#idevento").val(filter.eventoId);
    $("#tipologia")[0].selectize.setValue(filter.tipologia, false);    
    $("#titolo").val(filter.titolo);    
    if(filter.dataInsDa){  $("#dataInsDa").datepicker("setDate", moment(filter.dataInsDa).format('DD/MM/YYYY'));  }    
    if(filter.dataInsA){  $("#dataInsA").datepicker("setDate", moment(filter.dataInsA).format('DD/MM/YYYY'));  }
    if(filter.dataAttivoDa){  $("#dataAttivoDa").datepicker("setDate", moment(filter.dataAttivoDa).format('DD/MM/YYYY'));  }    
    if(filter.dataAttivoA){  $("#dataAttivoA").datepicker("setDate", moment(filter.dataAttivoA).format('DD/MM/YYYY'));  }
    if(filter.dataDa){  $("#dataDal").datepicker("setDate", moment(filter.dataDa).format('DD/MM/YYYY'));  }
    if(filter.dataA){  $("#dataAl").datepicker("setDate", moment(filter.dataA).format('DD/MM/YYYY'));  }    		
  //  $("#finanziato")[0].selectize.setValue(filter.finanziato, false);
    $("#stato")[0].selectize.setValue(filter.stato, false);
  //  $("#pubblicato")[0].selectize.setValue(filter.pubblicato, false);
	$("input[name='pubblicato'][value='"+filter.pubblicato+"']").prop('checked', true);
    $("#smart")[0].selectize.setValue(filter.smart, false);
    $("#nazione")[0].selectize.setValue(filter.codNazione, false);
    $("#areaterr")[0].selectize.setValue(filter.codArea, false);
    $("#regione")[0].selectize.setValue(filter.codRegione, false);
    $("#filtroMIBACT")[0].selectize.setValue(filter.filtroMIBACT, false); 
    $("#redazioniGenerali")[0].selectize.setValue(filter.redazioniGenerali, false); 
    $("#provincia")[0].selectize.setValue(filter.codProvinciaSet, false); 
    $("#comun")[0].selectize.setValue(filter.codComuneSet, false); 
    $("#estero").val(filter.comuneEstero);
    $("#cognome").val(filter.cognomeOwner);
    $("#email").val(filter.emailOwner);
    $("#attivita").val(filter.denominazioneAttivita);
}

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

var statEventoNew = [];

$(document).ready(function() {	
	   $("#idevento").bind("keypress", function (e) {
	          var keyCode = e.which ? e.which : e.keyCode
	               
	          if (!(keyCode >= 48 && keyCode <= 57)) {
	            $(".error").css("display", "inline");
	            return false;
	          }else{
	            $(".error").css("display", "none");
	          }
	      });
	
	if(window.flagPrm["filtroPromuovi"] == true){
		$("#gestioneAcc").attr('hidden',true);
		$("#promozioneAcc").removeAttr('hidden');
		$("#datahid").attr('hidden',true);
		
		//	$("#promozioneAcc > h4 > span").append(window.attivita.denominazione ? window.attivita.denominazione : window.attivitaRichiesta.denominazione);
				
		if(window.attivita.attivitaId){
			
			$("#areadefault").hide();				
			$("#areaattivita").find("#goHomeUrl").attr("href",  linkdms+"gestioneattivitaprofilo?idAttivita="+window.attivita.attivitaId );			
			$("#areaattivita").find("#goHomeTitolo").attr("href",  linkdms+"gestioneattivitaprofilo?idAttivita="+window.attivita.attivitaId );			
			$("#areaattivita").find("#goHomeTitolo").html(window.attivita.denominazione);
			$("#areaattivita").show();			
		}else{
			$("#areadefault").hide();				
			$("#areaattivita").find("#goHomeUrl").attr("href",  linkdms+"gestioneattivitatitolaredavalidare?idRegistrazione="+window.attivitaRichiesta.richiestaAttivitaId );			
			$("#areaattivita").find("#goHomeTitolo").attr("href",  linkdms+"gestioneattivitatitolaredavalidare?idRegistrazione="+window.attivitaRichiesta.richiestaAttivitaId );			
			$("#areaattivita").find("#goHomeTitolo").html(window.attivitaRichiesta.denominazione);
			$("#areaattivita").show();				
		}
				
		$("#nomestruttura").html(window.attivita.denominazione ? window.attivita.denominazione : window.attivitaRichiesta.denominazione);
		
		$("#myTabFE").attr('hidden',true);
		$("#myTabFEContent").css('width','100%');
	}
			
	if( window.flagPrm["filtroGestisci"] == true) {
		 	$("#gestioneAcc").removeAttr('hidden');
			$("#areadefault").hide();				
			$("#areaattivita").find("#goHomeUrl").attr("href",  linkdms+"ente?idEntita="+window.attivita.attivitaId );			
			$("#areaattivita").find("#goHomeTitolo").attr("href",  linkdms+"ente?idEntita="+window.attivita.attivitaId );			
			$("#areaattivita").find("#goHomeTitolo").html(window.attivita.denominazione);
			$("#areaattivita").show();		 
	}
		
	if(window.flagPrm["filtroGestisci"] == true){
		$("#smartDiv").removeAttr('hidden');
		$("#promoDiv").removeAttr('hidden');
	//	$("#gestsegnalazioni").removeClass('hide');
		$("#promozioneAcc").attr('hidden',true);
		$("#gestioneAcc").removeAttr('hidden');
		$("#gestioneAcc > h4 > span").append(window.titoloUtente);
	}
	
	if (window.flagPrm["creaEvento"] != true) {
		$("[name='creaevento']").attr("hidden", true);
	}
		

//	statEventoNew = window.statiEvento;
//	var rimosso = statEventoNew.splice(1,1);
//	statEventoNew = statEventoNew.filter(function(el) { return el; });

	
	$('#stato').selectize({
		valueField: 'statoId',
	    labelField: 'descrizione',
	    placeholder: window.mexJS['js.placeholder.stato'],
	    //options: window.statiEvento,
	    options: 	[{"statoId":"Nessuna scelta", "descrizione": "Nessuna scelta"},
	    			{"statoId": "ANNULLATO", "descrizione": "Annullato"},
					
	    			{"statoId": "VALUTAZIONE", "descrizione": "In attesa di valutazione"},
					{"statoId": "MODIFICA", "descrizione": "In modifica"},
	    			{"statoId": "RIVALUTAZIONE", "descrizione": "In nuova valutazione"},
					{"statoId": "LAVORAZIONE", "descrizione": "In valutazione"},
					{"statoId": "RESPINTO", "descrizione": "Rifiutato"},
					{"statoId": "VALIDATO", "descrizione": "Validato"},
					{"statoId": "RIVALIDATO", "descrizione": "Rivalidato"}], 
	    render: {
	        option: function (item, escape) {	
	        	return '<div class="option">'+escape(item.descrizione)+'</div>';
	        }
	    },
	    onInitialize: function() {       
            this.setValue("VALUTAZIONE", false);
        },
	    onChange: function(value) {
	    	if (value == "Nessuna scelta") {
	    		$('#stato').selectize()[0].selectize.clear();
	    	}
	    }
	});
	
	globalSelectizedEditor = $('#filtroMIBACT').selectize({
		valueField: 'idMIBACT',
	    labelField: 'tipologiaMIBACT',
	    searchField: 'tipologiaMIBACT',
	    plugins: ['remove_button'],
	    placeholder: 'Seleziona',
	    options: window.tipologieMIBACT,
	    render: {
	        option: function(item, escape) {
	        	return '<div class="option">'+escape(item.tipologiaMIBACT)+'</div>';
	        }
	    },
        onItemAdd: function(value) {
        	globalSelectizedEditor[0].selectize.close();
        },
        onItemRemove: function () {
            globalSelectizedEditor[0].selectize.close();

        }
	});

	globalSelectizedComune = $('#comun').selectize({
		valueField: 'codComune',
	    labelField: 'comune',
	    searchField: 'comune',
	    plugins: ['remove_button'],
	    onType: function(input) {
	        if (input.length < 3 && input.length > 0) {
	        	$('#comun').siblings('.selectize-control').find('.selectize-dropdown.multi.form-control.Input-text').css('display','none');
	        } else {
	        	$('#comun').siblings('.selectize-control').find('.selectize-dropdown.multi.form-control.Input-text').css('display','');
	        }
	    },
	    maxOptions: 8000,
	    placeholder: window.mexJS['js.placeholder.comune'],
	    options: window.comuniList,
	    render: {
	        option: function(item, escape) {
	        	return '<div class="option">'+escape(item.comune)+'</div>';
	        }
	    },
	    onChange: function(value) {
	    	
	    	var nomenazione = "";
	    	if($('[name="codNazione"]').selectize()[0].selectize.items[0])  {
	    		var nomenazione = $('[name="codNazione"]').selectize()[0].selectize.options[$('[name="codNazione"]').selectize()[0].selectize.items[0]].nazione;
	    	}
	    	
	    	if (!value.length && nomenazione != 'ITALIA' && !$('[name="codRegione"]').selectize()[0].selectize.items[0] && !$('[name="codProvinciaSet[]"]').selectize()[0].selectize.items[0] && !$('[name="codArea"]').selectize()[0].selectize.items[0]){
	    		$('#estero').prop('disabled', false);
	    	} else {
	    		$('#estero').prop('disabled', true);
	    		$('#estero').val('');
	    	}
	    },
        onItemAdd: function(value) {
        	globalSelectizedComune[0].selectize.close();
        },
        onItemRemove: function () {
        	globalSelectizedComune[0].selectize.close();

        }
	});

	$('#regione').selectize({
		valueField: 'codRegione',
	    labelField: 'regione',
	    searchField: 'regione',
	    onType: function(input) {
	        if (input.length < 3 && input.length > 0) {
	        	$('#regione').siblings('.selectize-control').find('.selectize-dropdown.single.form-control.Input-text').css('display','none');
	        } else {
	        	$('#regione').siblings('.selectize-control').find('.selectize-dropdown.single.form-control.Input-text').css('display','');
	        }
	    },
	    placeholder: window.mexJS['js.placeholder.regione'],
	    options: window.regioniList,
	    render: {
	        option: function(item, escape) {
	        	return '<div class="option">'+escape(item.regione)+'</div>';
	        }
	    },
	    onChange: function(value) {
	    	$('#estero').prop('disabled', true);
	    	$('#estero').val('');
	    	var provinceSelecitze = $('[name="codProvinciaSet[]"]').selectize()[0].selectize;
    		provinceSelecitze.clear();
    		provinceSelecitze.clearOptions();
	    	var comuniSelecitze = $('[name="codComuneSet[]"]').selectize()[0].selectize;
    		comuniSelecitze.clear();
    		comuniSelecitze.clearOptions();
    		var areeSelecitze = $('[name="codArea"]').selectize()[0].selectize;
    		
	    	if (!value.length){
	    		if ($('[name="codNazione"]').selectize()[0].selectize.items[0] != window.italia.codNazione) {
	    			$('#estero').prop('disabled', false);
	    		}
	    		areeSelecitze.enable();
	    		provinceSelecitze.load(function(callback) {
	    		    callback(provinceList);
	    		});
	    		comuniSelecitze.load(function(callback) {
	    		    callback(comuniList);
	    		});
	    	}else{
		    	var data = this.options[value];
	    		provinceSelecitze.load(function(callback) {
	    		    callback(provincePerRegione[data.codRegione]);
	    		});
	    		comuniSelecitze.load(function(callback) {
	    		    callback(comuniPerRegione[data.codRegione]);
	    		});
	    		if(data.regione.toUpperCase()=='PUGLIA'){
	    			areeSelecitze.enable();
	    		}else{
	    			  areeSelecitze.disable();
	    			  areeSelecitze.clear();
	    			}
	    	}
	    }
	});

	globalSelectizedProvincia =	$('#provincia').selectize({
		valueField: 'codProvincia',
	    labelField: 'provincia',
	    searchField: 'provincia',
	    plugins: ['remove_button'],
	    onType: function(input) {
	        if (input.length < 3 && input.length > 0) {
	        	$('#provincia').siblings('.selectize-control').find('.selectize-dropdown.multi.form-control.Input-text').css('display','none');
	        } else {
	        	$('#provincia').siblings('.selectize-control').find('.selectize-dropdown.multi.form-control.Input-text').css('display','');
	        }
	    },
	    placeholder: window.mexJS['js.placeholder.provincia'],
	    options: window.provinceList,
	    render: {
	        option: function(item, escape) {
	        	return '<div class="option">'+escape(item.provincia)+'</div>';
	        }
	    },
	    onChange: function(value) {
	    	$('#estero').prop('disabled', true);
	    	$('#estero').val('');
	    	var comuniSelecitze = $('[name="codComuneSet[]"]').selectize()[0].selectize;
    		comuniSelecitze.clear();
    		comuniSelecitze.clearOptions();
    		if (!value.length){
    			if ($('[name="codNazione"]').selectize()[0].selectize.items[0] != window.italia.codNazione) {
    				$('#estero').prop('disabled', false);
	    		}
    			comuniSelecitze.load(function(callback) {
    				if($('#areaterr').val() == false){
    					callback(comuniList);
    				} else {
    					callback(comuniPerArea[$('#areaterr').val()]);
    				}
	    		});
    		}else{
    			for (var i=0; i<value.length; i++){
    				var data = this.options[value[i]];
        			comuniSelecitze.load(function(callback) {
        				if($('#areaterr').val() == false){
        					callback(comuniPerProvincia[data.codProvincia]);
        				} else {
        					var comuniPerProvinciaPerArea = [];
        					for (var i=0; i<comuniPerProvincia[data.codProvincia].length; i++) {
        						for (var j=0; j<comuniPerArea[$('#areaterr').val()].length; j++) {
        							if (comuniPerProvincia[data.codProvincia][i].codComune == comuniPerArea[$('#areaterr').val()][j].codComune) {
        								comuniPerProvinciaPerArea.push(comuniPerProvincia[data.codProvincia][i]);
        							}
        						}
        					}
        					callback(comuniPerProvinciaPerArea);
        				}
    	    		});
    			}
    		}
	    },
        onItemAdd: function(value) {
        	globalSelectizedProvincia[0].selectize.close();
        },
        onItemRemove: function () {
        	globalSelectizedProvincia[0].selectize.close();

        }
	});

	$('#areaterr').selectize({
		valueField: 'codArea',
	    labelField: 'areaTerritoriale',
	    placeholder: window.mexJS['js.placeholder.area'],
	    options: areeList,
	    render: {
	        option: function(item, escape) {
	        	return '<div class="option">'+escape(item.areaTerritoriale)+'</div>';
	        }
	    },
	    onChange: function(value) {
	    	var provinceSelecitze = $('[name="codProvinciaSet[]"]').selectize()[0].selectize;
	    	provinceSelecitze.clear();
	    	provinceSelecitze.clearOptions();
    		var comuniSelecitze = $('[name="codComuneSet[]"]').selectize()[0].selectize;
    		comuniSelecitze.clear();
    		comuniSelecitze.clearOptions();
    		$('#estero').prop('disabled', true);
    		$('#estero').val('');
    		if (!value.length){
    			if ($('[name="codNazione"]').selectize()[0].selectize.items[0] != window.italia.codNazione) {
    				$('#estero').prop('disabled', false);
	    		}
    			var regioniSelecitze = $('[name="codRegione"]').selectize()[0].selectize;
	    		
	    		var regioneVal = regioniSelecitze.getValue();
	    		
	    		if(regioneVal){
					provinceSelecitze.load(function(callback) {
		    		    callback(provincePerRegione[regioneVal]);
		    		});
		    		comuniSelecitze.load(function(callback) {
		    		    callback(comuniPerRegione[regioneVal]);
		    		});
				} else {
					provinceSelecitze.load(function(callback) {
		    		    callback(provinceList);
		    		});
	    			comuniSelecitze.load(function(callback) {
		    		    callback(comuniList);
		    		});
				}
    		}else{
				var data = this.options[value];
				provinceSelecitze.load(function(callback) {
	    		    callback(provincePerArea[data.codArea]);
	    		});
    			comuniSelecitze.load(function(callback) {
	    		    callback(comuniPerArea[data.codArea]);
	    		});
    		}
	    }
	});

	$('#nazione').selectize({
		valueField: 'codNazione',
	    labelField: 'nazione',
	    searchField: 'nazione',
	    onType: function(input) {
	        if (input.length < 3 && input.length > 0) {
	        	$('#nazione').siblings('.selectize-control').find('.selectize-dropdown.single.form-control.Input-text').css('display','none');
	        } else {
	        	$('#nazione').siblings('.selectize-control').find('.selectize-dropdown.single.form-control.Input-text').css('display','');
	        }
	    },
	    placeholder: window.mexJS['js.placeholder.nazione'],
	    options: window.nazioniList,
	    render: {
	        option: function(item, escape) {
	        	return '<div class="option">'+escape(item.nazione)+'</div>';
	        }
	    },
	    onChange: function(value) {
	    	var regioniSelecitze = $('[name="codRegione"]').selectize()[0].selectize;
	    	regioniSelecitze.disable();
	    	regioniSelecitze.clear();
    		regioniSelecitze.clearOptions();
    		var provinceSelecitze = $('[name="codProvinciaSet[]"]').selectize()[0].selectize;
	    	provinceSelecitze.disable();
    		provinceSelecitze.clear();
    		provinceSelecitze.clearOptions();
	    	var comuniSelecitze = $('[name="codComuneSet[]"]').selectize()[0].selectize;
    		comuniSelecitze.disable();
    		comuniSelecitze.clear();
    		comuniSelecitze.clearOptions();
    		var areaSelecitze = $('[name="codArea"]').selectize()[0].selectize;
    		areaSelecitze.disable();
    		areaSelecitze.clear();
    		areaSelecitze.clearOptions();
	    	if (!value.length){
	    		regioniSelecitze.enable();
	    		provinceSelecitze.enable();
	    		comuniSelecitze.enable();
	    		areaSelecitze.enable();
	    		$('#estero').prop('disabled', false);
	    		regioniSelecitze.load(function(callback) {
	    		    callback(regioniList);
	    		});
	    		provinceSelecitze.load(function(callback) {
	    		    callback(provinceList);
	    		});
	    		comuniSelecitze.load(function(callback) {
	    		    callback(comuniList);
	    		});
	    		areaSelecitze.load(function(callback) {
	    		    callback(areeList);
	    		});
	    	}else{
	    		var data = this.options[value];
		    	if(data.codNazione == window.italia.codNazione){
		    		regioniSelecitze.load(function(callback) {
		    			regioniSelecitze.enable();
		    		    callback(regioniList);
		    		});
		    		provinceSelecitze.load(function(callback) {
		    			provinceSelecitze.enable();
		    		    callback(provinceList);
		    		});
		    		comuniSelecitze.load(function(callback) {
		    		comuniSelecitze.enable();
		    		    callback(comuniList);
		    		});
		    		areaSelecitze.load(function(callback) {
			    		areaSelecitze.enable();
			    		    callback(areeList);
			    		});
		    		$('#estero').prop('disabled', true);
		    		$('#estero').val('');
		    	}else{
		    		$('#estero').prop('disabled', false);
		    	}
	    	}
	    }
	});

	/*$('#pubblicato').selectize({
		valueField: 'pubblicato',
	    labelField: 'pubblicato',
	    placeholder: window.mexJS['js.placeholder.pubblicato'],
	    options: [{"pubblicato":"Nessuna scelta"},{"pubblicato":"Online"},{"pubblicato":"Offline"}],
	    render: {
	        option: function(item, escape) {
	        	return '<div class="option">'+escape(item.pubblicato)+'</div>';
	        }
	    },
	    onChange: function(value) {
	    	if (value == "Nessuna scelta") {
	    		$('#pubblicato').selectize()[0].selectize.clear();
	    		$('#redazioniGenerali').selectize()[0].selectize.clear();
	    		$('#redazioniGenerali').selectize()[0].selectize.disable();
	    	} else if (value == "Online" ) {
	    		$('#redazioniGenerali').selectize()[0].selectize.enable();
	    	} else if (value == "Offline" ) {
	    		$('#redazioniGenerali').selectize()[0].selectize.clear();
	    		$('#redazioniGenerali').selectize()[0].selectize.disable();
	    	}
	    	
	    },
	    onDelete: function(value) {
	    	$('#redazioniGenerali').selectize()[0].selectize.clear();
    		$('#redazioniGenerali').selectize()[0].selectize.disable();
	    }
	});*/
	
//	$('#finanziato').selectize({
//		valueField: 'finanziato',
//	    labelField: 'finanziato',
//	    placeholder: window.mexJS['js.placeholder.finanziato'],
//	    options: [{"finanziato":"Nessuna scelta"},{"finanziato":"Sì"},{"finanziato":"No"}],
//	    render: {
//	        option: function(item, escape) {
//	        	return '<div class="option">'+escape(item.finanziato)+'</div>';
//	        }
//	    },
//	    onChange: function(value) {
//	    	if (value.length > 3) {
//	    		$('#finanziato').selectize()[0].selectize.clear();
//	    	}
//	    	if (value == 'No') {
//	    		$('[name="titoloProgetto"]').selectize()[0].selectize.clear();
//	    		$('[name="titoloProgetto"]').selectize()[0].selectize.disable();
//	    	} else {
//	    		$('[name="titoloProgetto"]').selectize()[0].selectize.enable();
//	    	}
//	    }
//	});
	
	$('#tipologia').selectize({
		valueField: 'tipologia',
	    labelField: 'label',
	    placeholder: window.mexJS['js.placeholder.tipologia'],
	    options: [{"tipologia":"EVENTO", label:"SINGOLO"},{"tipologia":"RASSEGNA", label:"RASSEGNA"}],
	    render: {
	        option: function(item, escape) {
	        	return '<div class="option">'+escape(item.label)+'</div>';
	        }
	    }
	});
	
	$('#smart').selectize({
		plugins: ['no_results'],
	    valueField: 'codComune',
	    labelField: 'comune',
	    searchField: 'comune',
	    onType: function(input) {
	        if (input.length < 3 && input.length > 0) {
	        	$('#smart').siblings('.selectize-control').find('.selectize-dropdown.multi.form-control.Input-text').css('display','none');
	        } else {
	        	$('#smart').siblings('.selectize-control').find('.selectize-dropdown.multi.form-control.Input-text').css('display','');
	        }
	    },
	    placeholder: window.mexJS['js.placeholder.smart'],
	    preload: true,
	    render: {
	    	option: function(item, escape) {
	    		return '<option>'+item.comune+' ha '+item.numEventi+' eventi attivi</option>';
	        }
	    },
	    load: function(query, callback) {
	        $.ajax({
	            url: window.context + '/raggruppamentopercomune',
	            type: 'GET',
	            success: function(res) {
	            	return callback(res);
	            },
	            error: function() {
	                return callback();
	            },
	        });
	    },
	    onChange: function(value) {			    	
	    	if (!value.length) return;
	    	var data = this.options[value];	    	
	    	$("#nazione")[0].selectize.setValue(data.codNazione, false);
	    	$("#regione")[0].selectize.setValue(data.codRegione, false);
	    	$("#provincia")[0].selectize.setValue(data.codProvincia, false);
	    	$("#comun")[0].selectize.setValue(data.codComune, false);
	    }
	});
	
	$('#statoS').selectize({
		valueField: 'status',
	    labelField: 'status',
	    placeholder: window.mexJS['js.placeholder.stato'],
	    options: [{"status":"Da valutare"},{"status":"Gestita"},{"status":"Ignorata"}],
	    render: {
	        option: function(item, escape) {
	        	return '<div class="option">'+escape(item.status)+'</div>';
	        }
	    }
	});

	$('#comunS').selectize({
	    valueField: 'codComune',
	    labelField: 'comune',
	    searchField: 'comune',
	    onType: function(input) {
	        if (input.length < 3 && input.length > 0) {
	        	$('#comunS').siblings('.selectize-control').find('.selectize-dropdown.multi.form-control.Input-text').css('display','none');
	        } else {
	        	$('#comunS').siblings('.selectize-control').find('.selectize-dropdown.multi.form-control.Input-text').css('display','');
	        }
	    },
	    maxOptions: 8000,
	    placeholder: "Seleziona",
	    options: window.comuniList,
	    render: {
	    	option: function(item, escape) {
	    		return '<option value="'+item.comune+'">'+item.comune+'</option>';
	        }
	    },
	    onChange: function(value) {
	    	if (!value.length) {
	    		$("#comunEstS").prop('disabled', false);
	    		return;
	    	}
	    	var data = this.options[value];
	    	$("#comunEstS").val('');
	    	$("#comunEstS").prop('disabled', true);
	    }
	});
	
	$('#comunEstS').on('input',function() {
		if (this.value) {
			$('#comunS')[0].selectize.clear();
			$('#comunS')[0].selectize.disable();
		} else {
			$('#comunS')[0].selectize.enable();
		}
	});
	
	redazioneEventoNew = window.redazioniList;
	var rimossolist = redazioneEventoNew.splice(3,1);
	redazioneEventoNew = redazioneEventoNew.filter(function(el) { return el; });
	
	$('#redazioniGenerali').selectize({
		valueField: 'nome',
	    labelField: 'nome',
	    placeholder: window.mexJS['js.placeholder.redazioni'],
	    options: redazioneEventoNew,
	    render: {
	        option: function(item, escape) {
	        	return '<div class="option">'+escape(item.nome)+'</div>';
	        }
	    }
	});
	
	
	$('#redazioniGenerali').selectize()[0].selectize.disable();

	
	
	$(document).on( 'change', 'input[name="pubblicato"]', function() {
		  var value = $('input[name="pubblicato"]:checked').val();
		  
		 
		  
		  if (value == "") {
			  	$('#redazioniGenerali').selectize()[0].selectize.clear();
	    		$('#redazioniGenerali').selectize()[0].selectize.disable();
	    	} else if (value == "Online" ) {
	    		$('#redazioniGenerali').selectize()[0].selectize.enable();
	    	} else if (value == "Offline" ) {
	    		$('#redazioniGenerali').selectize()[0].selectize.clear();
	    		$('#redazioniGenerali').selectize()[0].selectize.disable();
	    	}		  
	});
	
	
//	$('[name="titoloProgetto"]').selectize({
//		//plugins: ['no_results'],
//		valueField: 'titoloProgetto',
//	    labelField: 'titoloProgetto',
//	    searchField: 'titoloProgetto',
//	    placeholder: window.mexJS['js.placeholder.progetto'],
//	    render: {
//	        option: function (item, escape) {
//	        	return '<div class="option">' +
//	                   	'<div class="text">' +
//	                   		'<span class="name">' + escape(item.titoloProgetto) + '</span>' +
//	                   	'</div>' +
//	                   '</div>';
//	        }
//	    },
//	    onChange: function(value) {
//	    	if (!value.length) return;
//	    	var data = this.options[value];
//	    	$('[name="progettoId"]').attr('value', data.progettoId);
//	    }});
	/*
	if (window.flagPrm["filtroGestisci"]==true){
		recuperoTuttiProgetti();
	}else{
	    recuperoProgetti({});
	}
	*/
	if (window.flagPrm["filtroRedattore"] != true) {
		$("#schedaRedHid").attr("hidden", true);
	} 

	
	$('#t_eventi').on('processing.dt', function(e, settings, processing) {
		if (processing) {
			$('#overlaytable1').show();
	    } else {
	    	$('#overlaytable1').hide();
	     }
	});
	var filter = "";
	
	var dataTab = $('#t_eventi').DataTable({
		language: { "url": context+"/assets/json/Italian.json" },
		scrollY: "396px",
	    scrollX: true,
	    paging:  true,
		pageLength: 10,
		lengthMenu: [[10,25, 50, 100, 250], [10,25, 50, 100, 250]],
		mark: false,
        serverSide: true,
        deferRender: true,
        autoWidth: false,
        scrollCollapse: true,
        fixedColumns:   {
            leftColumns: 2
        },
        data: '',
        ajax: {
        	url: context+"/eventPageList",
            contentType: 'application/json',
            type: "POST",
            data: function(d) {
            	            	
            	var jsondatatable = '';
            	if(sessionStorage.getItem("jsondatatable")){
            		jsondatatable = JSON.parse(sessionStorage.getItem("jsondatatable"));
            		window.sessionStorage.removeItem("jsondatatable");
            	}
            	
            	var pageidmenu = '';
            	if(sessionStorage.getItem("pageidmenu")){
            		pageidmenu = sessionStorage.getItem("pageidmenu");
            		window.sessionStorage.removeItem("pageidmenu");
            	}
            	
            	if(jsondatatable && pageidmenu == 'filtriGestioneValidazione.jsp'){
            		 filter = jsondatatable;
            	}else{            	
            		 filter = $('#formEventFilter').serializeFormJSON();
            	}    
            	            	
				filter.serviceCode = "VAL";
			
            	if(window.flagPrm["filtroEventoSeeAll"] != true) {
            		if (window.attivita.attivitaId){
                		filter.idAttivita = window.attivita.attivitaId;
                	} else if(window.attivitaRichiesta.richiestaAttivitaId){
                		filter.idRichiestaAttivita = window.attivitaRichiesta.richiestaAttivitaId;
                	}
            	}
            	if (window.flagPrm["filtroRedattore"] == true && !filter.statoPubblicazione) {
            		filter.redazioni = [];
            	}
//            	if (filter.statoPubblicazione || filter.pubblicato) {
//            		filter.stato = 'VALIDATO';
//            	}
            	window.param = convertDatatableWrapper(d, dataTab.settings().init().columns, filter);
            	return window.param;
            },
            error: function (xhr, error, thrown) {
            	if (xhr.status == 403) {
		        	$('#overlay').hide();
		    		alertify.alert(window.mexJS['js.error.errore'], "<img src=\""+context+"/assets/images/ERRORE.svg\"><br/><br/><br/>"+window.mexJS['js.sessionescaduta']).set('onok', function(closeEvent){window.location.href = window.areaRiservata;}).set('label', 'Chiudi'); 
		    		
            	}
            }
        },
        columns: [
        	{ name: "strumenti", data: null},
        	{ name: "titolo", data: "titolo" },
            { name: "stato", data: "stato" },            
            { name: "dataDa", data: "dataDa" },
            { name: "dataA", data: "dataA" },
            { name: "dataIns", data: "dataIns" },
            { name: "dataMod", data: "dataMod" },
            { name: "comune", data: "comune" }, 
            { name: "owner", data: "owner" },   
            { name: "redazioni", data: "redazioni" },
            { name: "tipo", data: "tipo" },
            { name: "eventoId", data: "eventoId" },
            { name: "finanziato", data: "finanziato", "defaultContent": "No"}    
        ],
        columnDefs: [
        	{ "title": "Azioni", "targets": 0, "width": 94 },
        	{ "title": "Titolo", "targets": 1, "width": 197 },
        	{ "title": "Stato", "targets": 2, "width": 122 },        	
        	{ "title": "Data da", "targets": 3, "width": 70 },
        	{ "title": "Data a", "targets": 4, "width": 70 },
        	{ "title": "Creato il", "targets": 5, "width": 70 },
        	{ "title": "Aperto il", "targets": 6, "width": 70 },
        	{ "title": "Comune", "targets": 7, "width": 200 },
        	{ "title": "Creato da", "targets": 8, "width": 250 }, 
        	{ "title": "Pubblicato", "targets": 9, "width": 85},
        	{ "title": "Tipo evento", "targets": 10, "width": 115 },
        	{ "title": "ID", "targets": 11, "width": 50 }, 
        	{ "title": "Finanziamento", "targets": 12, "width": 110 },
        	
          	{ targets: 0, searchable: false, orderable: false, visible: true, render: function(data, type, full, meta) {  
          		

				var actionColumn = '';
        		var btnVisualizza = '<a class="btn btn-primary databutton popup-with-form" style="letter-spacing: normal" href="'+context+'/getRiassunto/'+data.eventoId+'" data-container="body" title="Visualizza" data-html="true"><i class="dms-wk-icon-show-details" aria-hidden="true"></i></a>';
        		var btnGestisci = '<button onclick="visualizzaEvento('+data.eventoId+',\''+data.stato+'\')" class="btn btn-primary databutton" type="button" data-container="body" title="Gestisci" data-html="true"><i class="dms-wk-icon-edit" aria-hidden="true"></i></button>';
        		var btnDuplica= '<button onclick="duplicaEvento('+data.eventoId+')" class="btn btn-primary databutton" type="button" data-container="body" title="Duplica" data-html="true"><i class="dms-wk-icon-copy" aria-hidden="true"></i></button>';

				actionColumn = '<div class="iconstable">' + btnVisualizza + btnGestisci + btnDuplica +'</div>';

          		return actionColumn;
            }},
            
            { targets: 1, "width": 197, searchable: true, orderable: true, visible: true, render: function(data, type, full, meta) {
            	var lugtitolot = full.titolo.length;
            	var terminale = '';
              	if(lugtitolot > 40){      
              		terminale ='...';
              		var titolo = full.titolo.slice(0,37);  
              		var fulltitolo = specialcharacters(full.titolo);
                	var data = "<div class=\"denomTooltipover\" data-toggle=\"tooltip\" data-container=\"body\" title=\""+fulltitolo+"\" data-html=\"true\">"+titolo+terminale+"</div>";            	
                }else{
                	var data = full.titolo;
                }              	
              
              	return data;              		
             }},  
        	        	
             { targets: 2, searchable: false, orderable: true, visible:  true, render: function(data, type, full, meta) {    				
            	 var info ='';
            		if(full.ownerLock && full.ownerLock.trim() != ''){          			
            			var ownerLock= full.ownerLock;        			
            			ownerLock = ownerLock.replace(',','<br/>');        			
            			info = "<img src=\""+context+"/assets/svg/icon-informazioni.svg\"  style=\"width:26px;\" data-toggle=\"tooltip\" data-container=\"body\" title=\""+ownerLock+"\" data-html=\"true\">";        			
            		}
            		var data =  info+" "+full.stato.toUpperCase();
            		
    				return data;    				
    			}
        	},
        	
            { targets: 3, "width": 70, searchable: false, type: 'date-euro', orderable: true, visible: true, render: function(data, type, full, meta) { 
           		if(full.dataDa != ' '){
   					var data =  moment(full.dataDa, 'DD/MM/YYYY').format('DD/MM/YY');
                	}else{
                		var data =  '';
                	}
           		//var data =  tooltips+" "+info+" "+full.tipo;
   				return data;
             }},
              
           { targets: 4, "width": 70, searchable: false, type: 'date-euro', orderable: true, visible: true, render: function(data, type, full, meta) { 
            		if(full.dataA != ' '){
    					var data =  moment(full.dataA, 'DD/MM/YYYY').format('DD/MM/YY');
                 	}else{
               	  var data =  '';
                 	}
            		//var data =  tooltips+" "+info+" "+full.tipo;
    				return data;
            }},
            
            { targets: 5, "width": 70, searchable: false, type: 'date-euro', orderable: true, visible: true, render: function(data, type, full, meta) { 
        	  	if(full.dataIns != ' '){
   					var data =  moment(full.dataIns, 'DD/MM/YYYY').format('DD/MM/YY');
                	}else{
                		var data =  '';
                	}
           		//var data =  tooltips+" "+info+" "+full.tipo;
   				return data;
            }},
            { targets: 6, "width": 70, searchable: false, type: 'date-euro', orderable: true, visible: true, render: function(data, type, full, meta) { 
        	  	if(full.dataMod){
   					var data =  moment(full.dataMod, 'DD/MM/YYYY').format('DD/MM/YY');
                	}else{
                		var data =  '';
                	}
           		//var data =  tooltips+" "+info+" "+full.tipo;
   				return data;
            }}, 
            
            { targets: 7, searchable: true, orderable: true, visible: true, render: function(data, type, full, meta) {
            	var lugtitolot = full.comune.length;
            	var terminale = '';
              	if(lugtitolot > 35){      
              		terminale ='...';
              	}                  	
              	var comune = full.comune.slice(0,30);               	  
            	var data =  comune+terminale;
    
              	return data;              		
             }}, 
                            
        	{ targets: 8, searchable: true, orderable: true, visible: true, render: function(data, type, full, meta) {
        			
        		    var info = "";
        		    var name = ""; 
        		    var icostato = "";             		    
        		    var stato = full.statoImpresa;
        		    
        		    if(stato == 'IN VALIDAZIONE'){
        		    	icostato = "<div id=\"iconapericololeg\" data-toggle=\"tooltip\" data-container=\"body\" title=\"Stato impresa: in attesa di validazione\" data-html=\"true\"><img src=\""+context+"/assets/svg/new_svg/icon-attenzione.svg\" style=\"width: 26px;\"></div>";
        		    }
        		     
    		  		if(full.entitaOwner){
    		  			if(full.entitaOwner.trim()){    		  			
    		  				info = "<div id=\"dcreatoda\"><span id=\"tcreatoda\" style=\"cursor: pointer;\" data-toggle=\"tooltip\" data-container=\"body\" title=\""+full.entitaOwner+"\" data-html=\"true\">"+full.owner+"</span></div>";  
    		  			}else{
    		  				info = "<div id=\"dcreatoda\"><span id=\"tcreatoda\">"+full.owner+"</span></div>"; 
    		  			}    		  			
    		  		}else{
	    				info = "<div id=\"dcreatoda\"><span id=\"tcreatoda\">"+full.owner+"</span></div>"; 
	    			}   
    		  		        		
        			if(full.emailOwner){
        				if(full.emailOwner.trim()){
        					name = '<a href="mailto:'+full.emailOwner+'"  data-toggle=\"tooltip\" data-container=\"body\" title=\"'+full.emailOwner+'\" data-html=\"true\" id=\"acreatoda\"><i class=\"dms-wk-icon-email\" aria-hidden=\"true\"></i></a> ';
        				}
        			}
        			
        			return "<div class=\"linetd\">"+icostato + name + info+"</div>";
        		}
        	},
        	
        	{ targets: 9, searchable: false, orderable: true, visible:  true, render: function(data, type, full, meta) {
        			var redazioni = full.redazioni;        	
	        		var data = "<img src=\""+context+"/assets/svg/ok.svg\" style=\"height:16px; left: 40%; position: relative;\" data-toggle=\"tooltip\" data-container=\"body\" title=\""+redazioni+"\" data-html=\"true\">";	
        							
					if(redazioni === " " || redazioni == 'null' || redazioni == null){
						data = "<img src=\""+context+"/assets/svg/ko.svg\" style=\"height:16px; left: 40%; position: relative;\"  style=\"width:26px;\"data-toggle=\"tooltip\" data-container=\"body\" title=\"NO\" data-html=\"true\">";
					}
					return data;
    			}
        	},        	
        	
            { targets: 10, searchable: false, orderable: true, visible: true, render: function(data, type, full, meta) { 
            	var tipoLabel = "";
               	if(full.tipo == "EVENTO"){
					tipoLabel = "SINGOLO";
				} else {
					tipoLabel = full.tipo;
				}
               
                if(full.relazioni && full.relazioni.trim() != ''){          			
        			var relazioni= full.relazioni;        			
        			relazioni= relazioni.replace(/|/g,'<br/>');         			
        			relazioninew = specialcharacters(relazioni);        			
        			let tooltips = "<div class=\"denomTooltip\" " +
    				"data-toggle=\"popoverclick\" " +
    				"data-html=\"true\" " +
    				"title=\"Associazioni\" " +
    				"data-content=\"<div id='message'>" + relazioninew + "</div>\">" + tipoLabel +"</div>";        		
    				var data =  tooltips;        		
        		}else{        			
        			var data = tipoLabel;
        		} 
				return data;
             }},  
        	
        	//{ targets: 5, searchable: false, orderable: false, visible: false},
        	{ targets: [6,12], searchable: false, orderable: true, visible: true, render:$.fn.dataTable.render.text()}
        	
      
        ],
        order: [[ 6, "desc" ]],
        initComplete: function(settings, json) {
        	this.find('.dataTables_scrollBody').on('scroll', function() {
        		            this.find('.dataTables_scrollHeadInner').scrollLeft($(this).scrollLeft());
        		        }); 
        	//$('.dataTables_scrollBody:has(#t_eventi)').append('<div id="overlaytable1" class="overlaytable"><div class="spinner"></div></div>');
        	//$('th').css('min-width', '75px');
        	$('#t_eventi').DataTable().columns.adjust();
           	setTimeout(function(){ 
        		icontabelle(); 
        		$('.dataTables_scrollBody:has(#t_eventi)').append('<div id="overlaytable1" class="overlaytable" style="display:none;"><div class="spinner"></div></div>'); 
        		
            	$('.popup-with-form').magnificPopup({
            		type: 'ajax',
            		closeOnBgClick: false
            	});        	
        	}, 1300);  
        	recoveryfilter(filter);
        },
        drawCallback: function(){
        	//console.log("Draw callback"+new Date());
        	setTimeout(function(){ 
           	  	$('.popup-with-form').magnificPopup({
            		type: 'ajax',
            		closeOnBgClick: false
            	});    	
           	 icontabelle();
            	}, 1300);
            	
            	
         	$(".tooltip").tooltip("hide");
        	$('[data-toggle="popover"]').popover({ 'trigger': 'hover'});
        	$('[data-toggle="popoverclick"]').popover({  placement : 'top', container: "body", trigger : 'click', delay: {'show' : 0, 'hide' : 300} });
       	 //	$('[data-toggle="tooltip"]').tooltip({ 'trigger': 'hover'});
       	 	
     
       	    teventisearch();
       	    $('#overlay').hide();
       	    $('#overlaytable1').hide();
        },
        preDrawCallback: function(settings) {
            var api = new $.fn.dataTable.Api(settings);
            var pagination = $(this).closest('.dataTables_wrapper').find('.dataTables_paginate');
            pagination.toggle(api.page.info().pages > 1);
        },
        createdRow: function(row, data, dataIndex){
          //  $('td', row).css('min-width', '75px');
        }  
	});
	//console.log("DOPO:"+new Date());

	$('#t_eventi').on( 'page.dt', function () {
	    var info = dataTab.page.info();
	    $('#overlaytable1').show();
	    setTimeout(function(){ icontabelle(); }, 1300);  
	} );

	
	
	$(document).on('click', 'div.denomTooltip' , function(){	
		$('.popover').remove();
		$(this).popover({  placement : 'top', container: "body", trigger : 'click', delay: {'show' : 0, 'hide' : 300} });
		$(this).popover('show');
	});
	
	
	$(window).scroll(function(){
		  $('[data-toggle="popoverclick"]').each(function () {
		            $(this).popover('hide');		  
		    });
		  $(".tooltip").tooltip("hide");
	});

	$(document).on('scroll', 'div' , function(){	
		$("div").scroll(function(){
			$('[data-toggle="popoverclick"]').each(function () {
	            $(this).popover('hide');  
			});
			$(".tooltip").tooltip("hide");
		 });
		
	});
	
	$('html').on('click', function(e) {
		  if (typeof $(e.target).data('original-title') == 'undefined' &&
		     !$(e.target).parents().is('.popover.in')) {
		    $('.popover').popover('hide');
		  }
	});

	
	$('#t_eventi').on('page.dt', function() { $('.dataTables_scrollBody').scrollTop(0); });
	
	$("#excel").click(function(){
		var mypar = JSON.parse(window.param);
		mypar.detail.length=-1;
		var par = JSON.stringify(mypar);
		$("#excel").attr("disabled", true);
		$("#excel").find("span").text("Elaborazione in corso...");
		$("#excel").find("i").show();
		$.ajax({

			url: context + "/exportEventi/start?ruolo=VALIDATORE",
			contentType: 'application/json',
			type: "POST",
			data:par,
			error: function (xhr, error, thrown) {
				if (xhr.status == 403) {
					$('#overlay').hide();
					alertify.alert(window.mexJS['js.error.errore'], "<img src=\""+context+"/assets/images/ERRORE.svg\"><br/><br/><br/>"+window.mexJS['js.sessionescaduta']).set('onok', function(closeEvent){window.location.href = window.areaRiservata;}).set('label', 'Chiudi'); 

				}
				  $("#excel").prop('disabled',false);		
			   		$("#excel").find("span").text("Scarica eventi");
			   		$("#excel").find("i").hide();
			},
			success: function(uuid) {
				fetch = setInterval(function(){ exportFetch(uuid); }, 5000);
			}
			
		});	});
	

	//$("#nazione")[0].selectize.setValue(window.italia.codNazione, false);
	//$("#regione")[0].selectize.setValue(window.puglia.codRegione, false);
	
	$('#overlay').hide();	
	
});

//$("#eventi-tab").click(function(){
//	$("#gesteventi").addClass("active in");
//	$("#eventi-tab").addClass("active in");
//	$("#eventibox").addClass("active in");
//	$("#gestsegnalazioni").removeClass("active in");
//	$("#segnalazioni-tab").removeClass("active in");
//	$("#segnalazionibox").removeClass("active in");
//	$("#resetevento").click();
//	$('#t_eventi').DataTable().columns.adjust().draw();
//	  	setTimeout(function(){ 
//       	  	$('.popup-with-form').magnificPopup({
//        		type: 'ajax',
//        		closeOnBgClick: false
//        	});    	
//        	
//        	}, 1300);
//});

$('.end-d').datepicker({format: 'dd/mm/yyyy', language: 'it', autoclose: true,  constrainInput: false});
$('.start-d').datepicker({format: 'dd/mm/yyyy', language: 'it', autoclose: true,  constrainInput: false}).on('change', function () {
	var minDate = $(this).val();	
	if(minDate == ''){
		minDate = null;
	}else{
		minDate = minDate;
	}
	$('.end-d').val('').datepicker('destroy').datepicker({format: 'dd/mm/yyyy', language: 'it', autoclose: true,  constrainInput: false, startDate: minDate});
});

$('.endS-d').datepicker({format: 'dd/mm/yyyy', language: 'it', autoclose: true,  constrainInput: false});
$('.startS-d').datepicker({format: 'dd/mm/yyyy', language: 'it', autoclose: true,  constrainInput: false}).on('change', function () {
	var minDate = $(this).val();	
	if(minDate ==''){
		minDate = null;
	}else{
		minDate = minDate;
	}
	$('.endS-d').val('').datepicker('destroy').datepicker({format: 'dd/mm/yyyy', language: 'it', autoclose: true,  constrainInput: false, startDate: minDate});
});

$("#cercaevento").click(function(){
	window.sessionStorage.setItem("jsondatatable","");
	$('#overlay').show();
	//var jsonDTO = $('#formEventFilter').serializeFormJSON();
	$('#t_eventi').DataTable().draw();
	
 	setTimeout(function(){      	
		  $('.popup-with-form').magnificPopup({
		type: 'ajax',
		closeOnBgClick: false
	});
	}, 1300);	
	
	icontabelle(); 
 	$(".tooltip").tooltip("hide");
	$('[data-toggle="popover"]').popover({ 'trigger': 'hover'});
	$('[data-toggle="popoverclick"]').popover({  placement : 'top', container: "body", trigger : 'click', delay: {'show' : 0, 'hide' : 300} });
	$('[data-toggle="tooltip"]').tooltip({ 'trigger': 'hover'});
});

$("#resetevento").click(function(){
	window.sessionStorage.removeItem("jsondatatable");
	//$('#overlay').show();
	$('#formEventFilter').find('input').val('');
	$("#areaterr")[0].selectize.clear(true);
	$("#areaterr")[0].selectize.enable();
	$("#provincia")[0].selectize.clear(true);
	$("#provincia")[0].selectize.enable();
	//$("#pubblicato")[0].selectize.clear();
	//$("#finanziato")[0].selectize.clear();
	
	
		$("input[name='pubblicato'][value='']").prop('checked', true);
	
	$("#stato")[0].selectize.clear();
	$("#stato")[0].selectize.enable();
	$("#stato")[0].selectize.setValue("VALUTAZIONE", false);
	$("#comun")[0].selectize.clear();
	$("#comun")[0].selectize.enable();
	$("#smart")[0].selectize.clear();
	$('.start-d').datepicker('setEndDate', null);
	$('.end-d').datepicker('setStartDate', null);
	$('.startS-d').datepicker('setEndDate', null);
	$('.endS-d').datepicker('setStartDate', null);
	$('#estero').prop('disabled', true);
    $('#tipologia')[0].selectize.clear();
	$('#filtroMIBACT')[0].selectize.clear();
    $('#redazioniGenerali')[0].selectize.clear();
    $('#redazioniGenerali')[0].selectize.disable();
    $("#nazione")[0].selectize.clear();
    $("#regione")[0].selectize.clear();
    
   // $('#redazioni')[0].selectize.clear();
   // $('#redazioni')[0].selectize.disable();
    //$("#nazione")[0].selectize.setValue(window.italia.codNazione, false);
    //$("#regione")[0].selectize.setValue(window.puglia.codRegione, false);
   
});

$("[name='creaevento']").click(function(){
	creaEventoForm();
});

function exportFetch(uuid){
	var xhr = new XMLHttpRequest();  
    var xhr = new XMLHttpRequest();
    xhr.open('GET', context+'/exportEventi/fetch?uuid='+ uuid  +'&ruolo=VALIDATORE' , true);
    xhr.responseType = 'arraybuffer';
    xhr.onload = function() {
        if(this.status == '200') {
        	clearInterval(fetch);	
        	   var filename = '';
	           var disposition = xhr.getResponseHeader('Content-Disposition');
	           if (disposition && disposition.indexOf('attachment') !== -1) {
	               var filenameRegex = /filename[^;=\n]*=((['"]).*?\2|[^;\n]*)/;
	               var matches = filenameRegex.exec(disposition);
	               if (matches !== null && matches[1]) {
	            	   filename = matches[1].replace(/['"]/g, '');
	               }
	           }
	           var type = xhr.getResponseHeader('Content-Type');
	           var blob = new Blob([this.response], {type: type});
	           if(typeof window.navigator.msSaveBlob != 'undefined') {
	               window.navigator.msSaveBlob(blob, filename);
	           }
	           else {
	               var URL = window.URL || window.webkitURL;
	               var download_URL = URL.createObjectURL(blob);
	               if(filename) {
	                   var a = document.createElement('a');
	                   if(typeof a.download == 'undefined') {
	                       window.location = download_URL;
	                   } else {
	                       a.href = download_URL;
	                       a.download = filename;
	                       document.body.appendChild(a);
	                       a.click();
	                   }
	               } else {
	                   window.location = download_URL;
	               }
	               setTimeout(function() {URL.revokeObjectURL(download_URL);}, 10000);
	           }
	           
	           $("#excel").prop('disabled',false);		
		   		$("#excel").find("span").text("Scarica eventi");
		   		$("#excel").find("i").hide();
        }
        else if(this.status==500)
    	{
    	alertify.alert("Abbiamo riscontrato un problema", "<img src=\""+context+"/assets/images/ERRORE.svg\"><br/><br/><br/>"+window.mexJS['js.esitodettaglio.evento.duplica.error']).set('label', 'Chiudi'); 
		
    	clearInterval(fetch);
    	   $("#excel").prop('disabled',false);		
	   		$("#excel").find("span").text("Scarica eventi");
	   		$("#excel").find("i").hide();
    	}
    }; 
    xhr.setRequestHeader('Content-type', 'application/json; charset=UTF-8');
    xhr.send();
//    uuid=null;
    $('#overlay').hide();

    


};

function duplicaEvento(idEvento) {
	
	 var data = JSON.stringify({"attivitaId":attivita.attivitaId , "denominazione" : attivita.denominazione});
	
	alertify.confirm("<i class=\"icon-errore\" style=\"font-size:64px; color:#a66300;\"></i><br><br>"+window.mexJS['js.conferma.evento.duplica']).set({
		title:window.mexJS['js.tipologia.attenzione']}).set('labels', {ok:'Si', cancel:'No'}).set('onok', function(){
			$('#overlay').show();
			$.ajax({
		        type: "POST",
		        url: context+"/duplica?idEvento="+idEvento,
		        data : data,
		        contentType: 'application/json',
		        success: function(response) {
		        	//$('#t_eventi').DataTable().columns.adjust().draw();
		        	$('#overlay').hide();
		        	alertify.alert(window.mexJS['js.esito.operazione'], "<i class=\"icon-successo\" style=\"font-size:64px; color:#58CB7D;\"></i><br/><br/><br/>"+window.mexJS['js.esitodettaglio.evento.duplica']).set('onok', function(){$('#t_eventi').DataTable().columns.adjust().draw();
		        	
		         	setTimeout(function(){      	
		      		  $('.popup-with-form').magnificPopup({
		      		type: 'ajax',
		      		closeOnBgClick: false
		      	});
		      	}, 1300);	
		        	
		        	} ).set('label', 'Chiudi'); 
		        },
		        error: function(error){
		        	$('#overlay').hide();
	        	
		        	alertify.alert("Abbiamo riscontrato un problema", "<img src=\""+context+"/assets/images/ERRORE.svg\"><br/><br/><br/>"+window.mexJS['js.esitodettaglio.evento.duplica.error']).set('label', 'Chiudi'); 
		        }
		    });
			}).set('oncancel', function(){
				//$('#t_eventi').DataTable().columns.adjust().draw();
			 	setTimeout(function(){      	
					  $('.popup-with-form').magnificPopup({
					type: 'ajax',
					closeOnBgClick: false
				});
				}, 1300);	
			});
}

function eliminaEvento(idEvento) {
	alertify.confirm("<i class=\"icon-errore\" style=\"font-size:64px; color:#a66300;\"></i><br><br>"+window.mexJS['js.conferma.evento.elimina']).set({
		title:window.mexJS['js.tipologia.attenzione']}).set('labels', {ok:'Si', cancel:'No'}).set('onok', function(){
			$('#overlay').show();
			$.ajax({
		        type: "DELETE",
		        url: context+"/deleteEvento/"+idEvento,
		        success: function(response) {
		        	//('#t_eventi').DataTable().columns.adjust().draw();
		        	$('#overlay').hide();
		        	alertify.alert(window.mexJS['js.esito.operazione'], "<i class=\"icon-successo\" style=\"font-size:64px; color:#58CB7D;\"></i><br/><br/><br/>"+window.mexJS['js.esitodettaglio.evento.elimina']).set('onok', function(){$('#t_eventi').DataTable().columns.adjust().draw();
		        	
		         	setTimeout(function(){      	
		      		  $('.popup-with-form').magnificPopup({
		      		type: 'ajax',
		      		closeOnBgClick: false
		      	});
		      	}, 1300);	
		        	} ).set('label', 'Chiudi'); 
		        },
		        error: function(error){
            		if (error.status == 409){
            			$('#overlay').hide();
            			alertify.alert(window.mexJS['js.tipologia.attenzione'], "<img src=\""+context+"/assets/images/PERICOLO.svg\"><br/><br/><br/>"+window.mexJS['js.risorsaoccupata']).set('label', 'Chiudi'); 
            		}
            	}
		    });
			}).set('oncancel', function(){
				//$('#t_eventi').DataTable().columns.adjust().draw();
			 	setTimeout(function(){      	
					  $('.popup-with-form').magnificPopup({
					type: 'ajax',
					closeOnBgClick: false
				});
				}, 1300);	
			});
}

//function gestisciSegnalazione(idSegnalazione) {
//	$('#overlay').show();
//	$.ajax({
//        type: "GET",
//        url: context+"/getSegnalazione?segnalazioneId="+idSegnalazione,
//        dataType: "json",
//        success: function(response) {
//        	window.segnalazioneScelta = response;
//        	$("[name='creaevento']").click();
//        }
//    });
//}

$(".accordionF").on("click", ".accordionF-header", function() {
	$(this).toggleClass("active").next().slideToggle();
});
$(".accordionA1").on("click", ".accordionA1-header", function() {
	$(this).toggleClass("active").next().slideToggle();
});

$(document).ready(function(){
	  $(".dropdownbox").click(function(){
		    $(this).next("ul.nav.nav-tabs").toggleClass("showMenu");
		    $(this).next("ul.nav.nav-tabs > li > a").click(function(){
	        //$(".dropdownbox > p").text($(this).text());
			$(this).next("ul.nav.nav-tabs").removeClass("showMenu");
	      });
	  });
});

$('#mobiletabFilter').on('change', function (e) {
	  var $optionSelected = $("option:selected", this);
	  $optionSelected.tab('show')
});



	$(document).on( 'change', 'input[name="pubblicato"]', function() {
		  var value = $('input[name="pubblicato"]:checked').val();
		  
		 
		  
		  if (value == "") {
			  	$('#redazioniGenerali').selectize()[0].selectize.clear();
	    		$('#redazioniGenerali').selectize()[0].selectize.disable();
	    	} else if (value == "Online" ) {
	    		$('#redazioniGenerali').selectize()[0].selectize.enable();
	    	} else if (value == "Offline" ) {
	    		$('#redazioniGenerali').selectize()[0].selectize.clear();
	    		$('#redazioniGenerali').selectize()[0].selectize.disable();
	    	}		  
	});

function teventisearch() {	
	var dtable = $("#t_eventi").DataTable();

	$(".dataTables_filter input")
	 .unbind() // Unbind previous default bindings
	 .bind("input", function(e) { // Bind our desired behavior
		 
	     if(this.value.length >= 3 || e.keyCode == 13) {
	         // Call the API search function
	         dtable.search(this.value).draw();
	     }

	     if(this.value == "") {
	         dtable.search("").draw();
	     }
	     return;
	 });
	};
