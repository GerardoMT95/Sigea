

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
		$("#gestsegnalazioni").removeClass('hide');
		$("#promozioneAcc").attr('hidden',true);
		$("#gestioneAcc").removeAttr('hidden');
		$("#gestioneAcc > h4 > span").append(window.titoloUtente);
	}
	
	if (window.flagPrm["creaEvento"] != true) {
		$("[name='creaevento']").attr("hidden", true);
	}
	
	$('#stato').selectize({
		valueField: 'statoId',
	    labelField: 'descrizione',
	    placeholder: window.mexJS['js.placeholder.stato'],
	    options: window.statiEvento,
	    render: {
	        option: function (item, escape) {
	        	return '<div class="option">'+escape(item.descrizione)+'</div>';
	        }
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

	$('#pubblicato').selectize({
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
	    	} else if (value == "Sì" || value == "No") {
	    		$('#redazioniGenerali').selectize()[0].selectize.enable();
	    	}
	    	
	    },
	    onDelete: function(value) {
	    	$('#redazioniGenerali').selectize()[0].selectize.clear();
    		$('#redazioniGenerali').selectize()[0].selectize.disable();
	    }
	});
	
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
	    		return '<option><td>'+item.comune+' ha '+item.numEventi+' eventi attivi</option>';
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
	    	var data = this.options[value];
	    	$("#comun")[0].selectize.setValue(value, false);
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
	
	$('#statoPubb').selectize({
		valueField: 'stato',
	    labelField: 'stato',
	    placeholder: window.mexJS['js.placeholder.stato'],
	    options: [{"stato":"Nessuna scelta"},{"stato":"Non pubblicato"},{"stato":"In compilazione"},{"stato":"Redatto"},{"stato":"Pubblicato"},{"stato":"Respinto"}],
	    render: {
	        option: function(item, escape) {
	        	return '<div class="option">'+escape(item.stato)+'</div>';
	        }
	    },
	    onChange: function(value) {
	    	if (value == "Nessuna scelta") {
	    		$('#statoPubb').selectize()[0].selectize.clear();
	    		$('#redazioni').selectize()[0].selectize.clear();
	    		$('#redazioni').selectize()[0].selectize.disable();
	    	} else if (value == "Non pubblicato" || value == "In compilazione" || value == "Redatto" || value == "Pubblicato" || value == "Respinto") {
	    		$('#redazioni').selectize()[0].selectize.enable();
	    	}
	    },
	    onDelete: function(value) {
	    	$('#redazioni').selectize()[0].selectize.clear();
    		$('#redazioni').selectize()[0].selectize.disable();
	    }
	});
	
	globalSelectizedRedazioni = $('#redazioni').selectize({
		valueField: 'nome',
	    labelField: 'nome',
	    plugins: ['remove_button'],
	    placeholder: window.mexJS['js.placeholder.redazioni'],
	    options: window.redazioniListUtenteCorrente,
	    render: {
	        option: function(item, escape) {
	        	return '<div class="option">'+escape(item.nome)+'</div>';
	        }
	    },
        onItemAdd: function(value) {
        	globalSelectizedRedazioni[0].selectize.close();
        },
        onItemRemove: function () {
        	globalSelectizedRedazioni[0].selectize.close();
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
//	else {
//		if(window.redazioniListUtenteCorrente.length == 1){
//			$("#redazioni")[0].selectize.addItem(window.redazioniListUtenteCorrente[0].nome, false);
//			$("#redazioni").parent().parent().attr("hidden", true);
//		}
//	}
	
	$('#t_eventi').on('processing.dt', function(e, settings, processing) {
		if (processing) {
			$('#overlaytable1').show();
	    } else {
	    	$('#overlaytable1').hide();
	     }
	});
	
	var dataTab = $('#t_eventi').DataTable({
		language: { "url": "assets/json/Italian.json" },
		scrollY: "415px",
	    scrollX: true,
	    paging:  true,
		pageLength: 10,
		lengthMenu: [[10, 25, 50, 100, 250], [10, 25, 50, 100, 250]],
		mark: true,
        serverSide: true,
        deferRender: true,
        autoWidth: false,
        data: '',
        ajax: {
        	url: "eventPageList",
            contentType: 'application/json',
            type: "POST",
            data: function(d) {
            	var filter = $('#formEventFilter').serializeFormJSON();
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
            { name: "eventoId", data: "eventoId" },
            { name: "tipo", data: "tipo" },
            { name: "titolo", data: "titolo" },
            { name: "owner", data: "owner" },
            { name: "emailOwner", data: "emailOwner" },
            { name: "dataDa", data: "dataDa" },
            { name: "dataA", data: "dataA" },
            { name: "comune", data: "comune" },
            { name: "stato", data: "stato" },
            { name: "dataIns", data: "dataIns" },
            //{ name: "finanziato", data: "finanziato" },
            //{ name: "titoloProgetto", data: "titoloProgetto" },
            { name: "redazioni", data: "redazioni" }
           
        ],
        columnDefs: [
        	{ "title": "Azioni", "targets": 0, "width": 90 },
        	{ "title": "ID", "targets": 1 },
        	{ "title": "Tipologia", "targets": 2 },
        	{ "title": "Titolo", "targets": 3 },
        	{ "title": "Creato da", "targets": 4 },
        	{ "title": "Email", "targets": 5 },
        	{ "title": "Data da", "targets": 6 },
        	{ "title": "Data a", "targets": 7 },
        	{ "title": "Comune", "targets": 8 },
        	{ "title": "Stato", "targets": 9 },
        	{ "title": "Creato il", "targets": 10 },
        	//{ "title": "Finanziato", "targets": 11 },
        	//{ "title": "Progetto", "targets": 12 },
        	{ "title": "Pubblicato da", "targets": 11 },
        	
        	{ targets: 4, searchable: true, orderable: true, visible: window.flagPrm["filtroGestisci"] ? true : false, render: function(data, type, full, meta) {
        			if(full.emailOwner){
        				return '<a href="mailto:'+full.emailOwner+'">'+full.owner+'</a>';
        			}
        			return data;
        		}
        	},
        	{ targets: 5, searchable: false, orderable: false, visible: false},
        	{ targets: [1, 2, 3, 6, 7, 8, 9, 10, 11], searchable: true, orderable: true, visible: true, render:$.fn.dataTable.render.text()},
        	{ targets: 0, searchable: false, orderable: false, visible: true, render: function(data, type, full, meta) {
        		
        		
        		
        		
        		if(full.stato != 'Bozza') {
        			data = '<div class="iconstable"><a class="btn btn-primary databutton popup-with-form" style="letter-spacing: normal" href="'+context+'/getRiassunto/'+data.eventoId+'" data-toggle="tooltip" data-container="body" title="Visualizza" data-html="true"><i class="dms-wk-icon-show-details" aria-hidden="true"></i></a><!-- <button onclick="visualizzaEvento('+data.eventoId+',\''+data.stato+'\')" class="btn btn-primary databutton" type="button" data-toggle="tooltip" data-container="body" title="Visualizza" data-html="true"><img src="assets/images/VIEW.svg" /></button>--><button onclick="duplicaEvento('+data.eventoId+')" class="btn btn-primary databutton" type="button" data-toggle="tooltip" data-container="body" title="Duplica" data-html="true"><i class="dms-wk-icon-copy" aria-hidden="true"></i></button></div>';
        		} else {
        			data = '<div class="iconstable"><a class="btn btn-primary databutton popup-with-form" style="letter-spacing: normal" href="'+context+'/getRiassunto/'+data.eventoId+'" data-toggle="tooltip" data-container="body" title="Visualizza" data-html="true"><i class="dms-wk-icon-show-details" aria-hidden="true"></i></a><!-- <button onclick="visualizzaEvento('+data.eventoId+',\''+data.stato+'\')" class="btn btn-primary databutton" type="button" data-toggle="tooltip" data-container="body" title="Visualizza" data-html="true"><img src="assets/images/VIEW.svg" /></button>--><button onclick="duplicaEvento('+data.eventoId+')" class="btn btn-primary databutton" type="button" data-toggle="tooltip" data-container="body" title="Duplica" data-html="true"><i class="dms-wk-icon-copy" aria-hidden="true"></i></button><button class="btn btn-primary databutton" onclick="eliminaEvento('+data.eventoId+')" type="button" data-toggle="tooltip" data-container="body" title="Cancella" data-html="true"><i class="dms-wk-icon-delete" aria-hidden="true"></i></button></div>';
                }
        		return data;
              }}
        ],
        order: [[ 10, "desc" ]],
        initComplete: function(settings, json) {
        	this.find('.dataTables_scrollBody').on('scroll', function() {
        		            this.find('.dataTables_scrollHeadInner').scrollLeft($(this).scrollLeft());
        		        }); 
        	$('.dataTables_scrollBody:has(#t_eventi)').append('<div id="overlaytable1" class="overlaytable"><div class="spinner"></div></div>');
        	$('th').css('min-width', '75px');
        	$('#t_eventi').DataTable().columns.adjust().draw();
        },
        drawCallback: function(){
        	$('.popup-with-form').magnificPopup({
        		type: 'ajax',
        		closeOnBgClick: false
        	});
        	
        	$('[data-toggle="popover"]').popover({ 'trigger': 'hover'});
       	 	$('[data-toggle="tooltip"]').tooltip({ 'trigger': 'hover'});
       	    $('#overlay').hide();
       	    $('#overlaytable1').hide();
        },
        preDrawCallback: function(settings) {
            var api = new $.fn.dataTable.Api(settings);
            var pagination = $(this).closest('.dataTables_wrapper').find('.dataTables_paginate');
            pagination.toggle(api.page.info().pages > 1);
        },
        createdRow: function(row, data, dataIndex){
            $('td', row).css('min-width', '75px');
        }
	});
	
	$('#t_eventi').on('page.dt', function() { $('#overlaytable1').show(); $('.dataTables_scrollBody').scrollTop(0); });
	
	$("#excel").click(function(){
		
		var mypar = JSON.parse(window.param);
		mypar.detail.length=-1;
		var par = JSON.stringify(mypar);
		$("#excel").attr("disabled", true);
		$("#excel").find("span").text("Elaborazione in corso...");
		$("#excel").find("i").show();
		$.ajax({

			url: context + "/exportEventi/start?ruolo=REDATTORE",
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
			
		});
	});
	
	$('#t_segnalazioni').on('processing.dt', function(e, settings, processing) {
		if (processing) {
			$('#overlaytable2').show();
	    } else {
	    	$('#overlaytable2').hide();
	     }
	});
	
	var dataTabS = $('#t_segnalazioni').DataTable({
		language: { "url": "assets/json/Italian.json" },
		scrollY: "415px",
	    scrollX: true,
	    paging:  true,
		pageLength: 10,
		lengthMenu: [[10, 25, 50, 100, 250], [10, 25, 50, 100, 250]],
		mark: true,
        serverSide: true,
        deferRender: true,
        autoWidth: false,
        data: '',
        ajax: {
        	url: "segnPageList",
            contentType: 'application/json',
            type: "POST",
            data: function(d) {
            	var filter = $('#formSegnFilter').serializeFormJSON();
            	return convertDatatableWrapper(d, dataTabS.settings().init().columns, filter);
            },
            error: function (xhr, error, thrown) {
            	if (xhr.status == 403) {
		        	$('#overlay').hide();
		    		alertify.alert(window.mexJS['js.errore.errore'], "<img src=\""+context+"/assets/images/ERRORE.svg\"><br/><br/><br/>"+window.mexJS['js.sessionescaduta']).set('onok', function(closeEvent){window.location.href = window.areaRiservata;}).set('label', 'Chiudi'); 
		    		 	
            	}
            }
        },
        columns: [
        	
        	{ name: "segnalazioneId", data: "segnalazioneId" },
        	{ name: "strumenti", data: null},
            { name: "nome", data: "nome" },
            { name: "dataDa", data: "dataDa" },
            { name: "dataA", data: "dataA" },
            { name: "comune", data: "comune" },
            { name: "riferimento", data: "riferimento" },
            { name: "dataIns", data: "dataIns" },
            { name: "status", data: "status" }
           
        ],
        columnDefs: [
//        	{ "title": "ID", "targets": 0 },
//        	{ "title": "Titolo", "targets": 1 },
//        	{ "title": "Data da", "targets": 2 },
//        	{ "title": "Data a", "targets": 3 },
//        	{ "title": "Comune", "targets": 4 },
//        	{ "title": "Riferimento", "targets": 5 },
//        	{ "title": "Creato il", "targets": 6 },
//        	{ "title": "Stato", "targets": 7 },
//        	{ "title": "Strumenti", "targets": 8 },
        	{ targets: 0, visible: false },
        	{ targets: 1, "width": 50, searchable: false, orderable: false, visible: true, render: function(data, type, full, meta){
        		if(data.status != 'Da valutare') {
        			data = '<div class="iconstable"><button class="btn btn-primary databutton" type="button" disabled data-toggle="tooltip" data-container="body" title="Gestisci" data-html="true"><img src="'+context+'/assets/images/icon-creazione_agenda.svg" style="height: 28px;"></button><button class="btn btn-primary databutton" type="button" disabled data-toggle="tooltip" data-container="body" title="Ignora" data-html="true"><i class="dms-wk-icon-delete" aria-hidden="true"></i></button></div>';
        		} else {
        			data = '<div class="iconstable"><button onclick="gestisciSegnalazione('+data.segnalazioneId+')" class="btn btn-primary databutton" type="button" data-toggle="tooltip" data-container="body" title="Gestisci" data-html="true"><img src="'+context+'/assets/images/icon-creazione_agenda.svg" style="height: 28px;"></button><button onclick="ignoraSegnalazione('+data.segnalazioneId+')" class="btn btn-primary databutton" type="button" data-toggle="tooltip" data-container="body" title="Ignora" data-html="true"><i class="dms-wk-icon-delete" aria-hidden="true"></i></button></div>';
        		}
                 return data;
              }},
        	{ targets: [2, 3, 4, 5, 6, 7, 8], searchable: true, orderable: true, visible: true, render:$.fn.dataTable.render.text() }
        	
        ],
        order: [[ 7, "desc" ]],
        initComplete: function(settings, json) {
        	$('.dataTables_scrollBody:has(#t_segnalazioni)').append('<div id="overlaytable2" class="overlaytable"><div class="spinner"></div></div>');
//        	$('th').css('min-width', '60px');
        	$('#t_segnalazioni').DataTable().columns.adjust().draw();
        },
        preDrawCallback: function(settings) {
            var api = new $.fn.dataTable.Api(settings);
            var pagination = $(this).closest('.dataTables_wrapper').find('.dataTables_paginate');
            pagination.toggle(api.page.info().pages > 1);
        },        
        
		drawCallback:  function( settings ) {

			$('[data-toggle="popover"]').popover({ 'trigger': 'hover'});
			$('[data-toggle="tooltip"]').tooltip({ 'trigger': 'hover'});
			$('#overlay').hide();

		}        

	});
	$("#nazione")[0].selectize.setValue(window.italia.codNazione, false);
	$("#regione")[0].selectize.setValue(window.puglia.codRegione, false);	
	$('#overlay').hide();	
	
});

$("#segnalazioni-tab").click(function(){
	$("#gesteventi").removeClass("active in");
	$("#eventi-tab").removeClass("active in");
	$("#eventibox").removeClass("active in");
	$("#gestsegnalazioni").addClass("active in");
	$("#segnalazioni-tab").addClass("active in");
	$("#segnalazionibox").addClass("active in");
	$("#resetsegnalazione").click();
	$('#t_segnalazioni').DataTable().columns.adjust().draw();
});

$("#eventi-tab").click(function(){
	$("#gesteventi").addClass("active in");
	$("#eventi-tab").addClass("active in");
	$("#eventibox").addClass("active in");
	$("#gestsegnalazioni").removeClass("active in");
	$("#segnalazioni-tab").removeClass("active in");
	$("#segnalazionibox").removeClass("active in");
	$("#resetevento").click();
	$('#t_eventi').DataTable().columns.adjust().draw();
});

$('.start-d').datepicker({format: 'dd/mm/yyyy', language: 'it', autoclose: true,  constrainInput: false}).on('change', function () {
var minDate = $(this).val();

if(minDate ==''){
	minDate = '';
}else{
	minDate = minDate;
}

$('.end-d').val('').datepicker('destroy').datepicker({format: 'dd/mm/yyyy', language: 'it', autoclose: true,  constrainInput: false, startDate: minDate});
});

$('.end-d').datepicker({format: 'dd/mm/yyyy', language: 'it', autoclose: true,  constrainInput: false, startDate: ''});


$("#cercaevento").click(function(){
	$('#overlay').show();
	var jsonDTO = $('#formEventFilter').serializeFormJSON();
	$('#t_eventi').DataTable().columns.adjust().draw();
	icontabelle(); 
	$('[data-toggle="popover"]').popover({ 'trigger': 'hover'});
	$('[data-toggle="popoverclick"]').popover({  placement : 'top', container: "body", trigger : 'click', delay: {'show' : 0, 'hide' : 300} });
	$('[data-toggle="tooltip"]').tooltip({ 'trigger': 'hover'});
});

$("#cercasegnalazione").click(function(){
	$('#overlay').show();
	var jsonDTO = $('#formSegnFilter').serializeFormJSON();
	$('#t_segnalazioni').DataTable().columns.adjust().draw();
	icontabelle(); 
	$('[data-toggle="popover"]').popover({ 'trigger': 'hover'});
	$('[data-toggle="popoverclick"]').popover({  placement : 'top', container: "body", trigger : 'click', delay: {'show' : 0, 'hide' : 300} });
	$('[data-toggle="tooltip"]').tooltip({ 'trigger': 'hover'});
});

$("#resetevento").click(function(){
	$('#formEventFilter').find('input').val('');
	$("#areaterr")[0].selectize.clear(true);
	$("#areaterr")[0].selectize.enable();
	$("#provincia")[0].selectize.clear(true);
	$("#provincia")[0].selectize.enable();
	$("#pubblicato")[0].selectize.clear();
	//$("#finanziato")[0].selectize.clear();
	$("#stato")[0].selectize.clear();
	$("#comun")[0].selectize.clear();
	$("#comun")[0].selectize.enable();
	$("#smart")[0].selectize.clear();
	$('.start-d').datepicker('setEndDate', null);
	$('.end-d').datepicker('setStartDate', null);
	$('.startS-d').datepicker('setEndDate', null);
	$('.endS-d').datepicker('setStartDate', null);
	$('#estero').prop('disabled', true);
    $('#tipologia')[0].selectize.clear();
	$("#stato")[0].selectize.clear();

    $('#statoPubb')[0].selectize.clear();
    $('#redazioniGenerali')[0].selectize.clear();
    $('#redazioniGenerali')[0].selectize.disable();
    $('#redazioni')[0].selectize.clear();
    $('#redazioni')[0].selectize.disable();
    $("#nazione")[0].selectize.setValue(window.italia.codNazione, true);
    var regioniSelecitze = $('#regione').selectize()[0].selectize;

	regioniSelecitze.clear();
	regioniSelecitze.clearOptions();
	var provinceSelecitze = $('#provincia').selectize()[0].selectize;

	provinceSelecitze.clear();
	provinceSelecitze.clearOptions();
	var comuniSelecitze = $('#comun').selectize()[0].selectize;

	comuniSelecitze.clear();
	comuniSelecitze.clearOptions();
	var areaSelecitze = $('#areaterr').selectize()[0].selectize;

	areaSelecitze.clear();
	areaSelecitze.clearOptions();
  
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
    $("#regione")[0].selectize.setValue(window.puglia.codRegione, true);
});

$("#resetsegnalazione").click(function(){
	$('#formSegnFilter').find('input').val('');
	$("#statoS")[0].selectize.clear();
	$("#comunS")[0].selectize.clear();
	$("#comunS")[0].selectize.enable();
	$('.start-d').datepicker('setEndDate', null);
	$('.end-d').datepicker('setStartDate', null);
});

$("[name='creaevento']").click(function(){
	creaEventoForm();
});


function exportFetch(uuid){
	
	
	var xhr = new XMLHttpRequest();  
    var xhr = new XMLHttpRequest();
    xhr.open('GET', context+'/exportEventi/fetch?uuid='+ uuid  +'&ruolo=REDATTORE' , true);
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
		        url: "duplica?idEvento="+idEvento,
		        data : data,
		        contentType: 'application/json',
		        success: function(response) {
		        	//$('#t_eventi').DataTable().columns.adjust().draw();
		        	$('#overlay').hide();
		        	alertify.alert(window.mexJS['js.esito.operazione'], "<i class=\"icon-successo\" style=\"font-size:64px; color:#58CB7D;\"></i><br/><br/><br/>"+window.mexJS['js.esitodettaglio.evento.duplica']).set('onok', function(){$('#t_eventi').DataTable().columns.adjust().draw();} ).set('label', 'Chiudi'); 
		        },
		        error: function(error){
		        	$('#overlay').hide();
	        		//alertify.alert(window.mexJS['js.error.errore'], "<i class=\"icon-errore\" style=\"font-size:64px; color:#58CB7D;\"></i><br/><br/><br/>"+window.mexJS['js.esitodettaglio.evento.duplica.error']).set('onok', function(){$('#t_eventi').DataTable().columns.adjust().draw();} ).set('label', 'Chiudi');
		        	
		        	alertify.alert("Abbiamo riscontrato un problema", "<img src=\""+context+"/assets/images/ERRORE.svg\"><br/><br/><br/>"+window.mexJS['js.esitodettaglio.evento.duplica.error']).set('label', 'Chiudi'); 		        
		        }
		    });
			}).set('oncancel', function(){
				$('#t_eventi').DataTable().columns.adjust().draw();
			});
}

function eliminaEvento(idEvento) {
	alertify.confirm("<i class=\"icon-errore\" style=\"font-size:64px; color:#a66300;\"></i><br><br>"+window.mexJS['js.conferma.evento.elimina']).set({
		title:window.mexJS['js.tipologia.attenzione']}).set('labels', {ok:'Si', cancel:'No'}).set('onok', function(){
			$('#overlay').show();
			$.ajax({
		        type: "DELETE",
		        url: "deleteEvento/"+idEvento,
		        success: function(response) {
		        	//('#t_eventi').DataTable().columns.adjust().draw();
		        	$('#overlay').hide();
		        	alertify.alert(window.mexJS['js.esito.operazione'], "<i class=\"icon-successo\" style=\"font-size:64px; color:#58CB7D;\"></i><br/><br/><br/>"+window.mexJS['js.esitodettaglio.evento.elimina']).set('onok', function(){$('#t_eventi').DataTable().columns.adjust().draw();} ).set('label', 'Chiudi'); 
		        },
		        error: function(error){
            		if (error.status == 409){
            			$('#overlay').hide();
            			alertify.alert(window.mexJS['js.tipologia.attenzione'], "<img src=\""+context+"/assets/images/PERICOLO.svg\"><br/><br/><br/>"+window.mexJS['js.risorsaoccupata']).set('label', 'Chiudi'); 
            		}
            	}
		    });
			}).set('oncancel', function(){
				$('#t_eventi').DataTable().columns.adjust().draw();
			});
}

function gestisciSegnalazione(idSegnalazione) {
	$('#overlay').show();
	$.ajax({
        type: "GET",
        url: "getSegnalazione?segnalazioneId="+idSegnalazione,
        dataType: "json",
        success: function(response) {
        	window.segnalazioneScelta = response;
        	$("[name='creaevento']").click();
        }
    });
}

//$(".box-title").on("click", function() {
// 	$(this).toggleClass("active").next().slideToggle();
// });

$(".accordionF").on("click", ".accordionF-header", function() {
	$(this).toggleClass("active").next().slideToggle();
});
$(".accordionA1").on("click", ".accordionA1-header", function() {
	$(this).toggleClass("active").next().slideToggle();
});

$('#mobilemyTabFE').on('change', function (e) {
	  var $optionSelected = $("option:selected", this);
	  $optionSelected.tab('show')
});

