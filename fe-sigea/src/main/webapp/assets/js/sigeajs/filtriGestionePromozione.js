function icontabelle(){		
	var w = screen.width;	
	if(w > 480){	
		$('div.DTFC_LeftBodyLiner > table#t_eventi > tbody > tr > td:first-child').each(function( index ) {        		
			$(this).find('button').each(function( index ) {
				$(this).attr('data-toggle', 'tooltip').tooltip({ 'trigger': 'hover', container: "body"});		
			});
			$(this).find("a").attr('data-toggle', 'tooltip').tooltip({ 'trigger': 'hover', container: "body"});	
		});	
		$('div.DTFC_LeftBodyLiner > table#t_eventi > tbody > tr > td:nth-child(2)').each(function( index ) {
			$(this).find("div").attr('data-toggle', 'tooltip').tooltip({ 'trigger': 'hover', container: "body"});	
		});
		$('[data-toggle="tooltip"]').tooltip({ 'trigger': 'hover', container: "body"});	
	}else{		
		$('div.dataTables_scrollBody > table.tabpromozione> tbody > tr > td:first-child').each(function( index ) {        		
			$(this).find('button').each(function( index ) {
				$(this).tooltip('destroy').removeAttr('data-toggle');		
			});
			$(this).find("a").tooltip('destroy').removeAttr('data-toggle');	
		});	
		$('div.dataTables_scrollBody> table.tabpromozione > tbody > tr > td:nth-child(2)').each(function( index ) {
			$(this).find("div").attr('data-toggle', 'tooltip').tooltip({ 'trigger': 'hover', container: "body"});	
		});
		$('[data-toggle="tooltip"]').tooltip({ 'trigger': 'hover', container: "body"});			
	}
}

function icontabellesegnalazioni(){		
	var w = screen.width;
	if(w > 480){	
		$('.tabpromozione > tbody > tr > td:first-child').each(function( index ) {        		
			$(this).find('button').each(function( index ) {
				$(this).tooltip({ 'trigger': 'hover', container: "body"});		
			});
		
		});	
		$('[data-toggle="tooltips"]').tooltip({ 'trigger': 'hover', container: "body"});	
	}else{		
		$('div.dataTables_scrollBody > table.tabsegnalazione> tbody > tr > td:first-child').each(function( index ) {        		
			$(this).find('button').each(function( index ) {
				$(this).tooltip('destroy').removeAttr('data-toggle');		
			});
			$(this).find("a").tooltip('destroy').removeAttr('data-toggle');	
		});	
		$('div.dataTables_scrollBody> table.tabsegnalazione > tbody > tr > td:nth-child(2)').each(function( index ) {
			$(this).find("div").attr('data-toggle', 'tooltips').tooltip({ 'trigger': 'hover', container: "body"});	
		});
		$('[data-toggle="tooltips"]').tooltip({ 'trigger': 'hover', container: "body"});			
	}
}

function recoveryfilter(filter){	
    $("#idevento").val(filter.eventoId);
    $("#tipologia")[0].selectize.setValue(filter.tipologia, false);    
    $("#titolo").val(filter.titolo); 
    if(filter.dataDa){  $("#dataDal").datepicker("setDate", moment(filter.dataDa).format('DD/MM/YYYY'));  }
    if(filter.dataA){  $("#dataAl").datepicker("setDate", moment(filter.dataA).format('DD/MM/YYYY'));  }  
    $("#stato")[0].selectize.setValue(filter.stato, false);    
    $("#filtroMIBACT")[0].selectize.setValue(filter.filtroMIBACT, false); 
    $("#nazione")[0].selectize.setValue(filter.codNazione, false);
    $("#areaterr")[0].selectize.setValue(filter.codArea, false);
    $("#regione")[0].selectize.setValue(filter.codRegione, false);
    $("#provincia")[0].selectize.setValue(filter.codProvinciaSet, false); 
    $("#comun")[0].selectize.setValue(filter.codComuneSet, false); 
    $("#estero").val(filter.comuneEstero);    
}

function recoveryfilter2(filter){	   
    $("#titoloS").val(filter.titolo);    
    if(filter.dataDa){  $("#dataDalS").datepicker("setDate", moment(filter.dataDa).format('DD/MM/YYYY'));  }
    if(filter.dataA){  $("#dataAlS").datepicker("setDate", moment(filter.dataA).format('DD/MM/YYYY'));  }  
    $("#statoS")[0].selectize.setValue(filter.status, false);     
    $("#comunS")[0].selectize.setValue(filter.codIstat, false);    
    $("#estero").val(filter.comuneEstero);    
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



$(document).ready(function() {	
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
		//$("#gestsegnalazioni").removeClass('hide');
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

	globalSelectizedProvincia = $('#provincia').selectize({
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
		
	$('#statoS').selectize({
		valueField: 'status',
	    labelField: 'label',
	    placeholder: window.mexJS['js.placeholder.stato'],
	    options: [{"status":"Da valutare", "label":"Da valutare"},{"status":"Gestita", "label":"Gestito"},{"status":"Ignorata", "label":"Ignorato"}],
	    render: {
	        option: function(item, escape) {
	        	return '<div class="option">'+escape(item.label)+'</div>';
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
	/*

	if (window.flagPrm["filtroGestisci"]==true){
		//recuperoTuttiProgetti();
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
	    	
	     	setTimeout(function(){ 
        		icontabelle();        	
        		$('.dataTables_scrollBody:has(#t_eventi)').append('<div id="overlaytable1" class="overlaytable" style="display:none;"><div class="spinner"></div></div>');
        	    $('.popup-with-form').magnificPopup({
	        		type: 'ajax',
	        		closeOnBgClick: false
	        	});        	
        	}, 1300); 
	    	
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
             	
            	if(jsondatatable != null && pageidmenu == 'filtriGestionePromozione.jsp'){
            		filter = jsondatatable;
            	}else{            	
            		filter = $('#formEventFilter').serializeFormJSON();
            	}
            	
				filter.serviceCode = "PROM";
            	//if(window.flagPrm["filtroEventoSeeAll"] != true) {
        		if (window.attivita.attivitaId){
            		filter.idAttivita = window.attivita.attivitaId;
            	} else if(window.attivitaRichiesta.richiestaAttivitaId){
            		filter.idRichiestaAttivita = window.attivitaRichiesta.richiestaAttivitaId;
            	}
            	//}
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
        	{ name: "eventoId", data: "eventoId" },                  
            { name: "tipo", data: "tipo" },           
            { name: "dataDa", data: "dataDa" },
            { name: "dataA", data: "dataA" }, 
            { name: "comune", data: "comune" },
        	{ name: "stato", data: "stato" },
            { name: "dataIns", data: "dataIns" },
            { name: "dataMod", data: "dataMod" },
            { name: "finanziato", data: "finanziato", "defaultContent": "No"}   

        ],
        columnDefs: [
        	{ "title": "Azioni", "targets": 0, "width": 94 },
        	{ "title": "Titolo", "targets": 1, "width": 197 },
          	{ "title": "ID", "targets": 2, "width": 50 },        	
        	{ "title": "Tipo evento", "targets": 3, "width":90  },          	
        	{ "title": "Data da", "targets": 4, "width": 70 },
        	{ "title": "Data a", "targets": 5, "width": 70 },
        	{ "title": "Comune", "targets": 6, "width": 145 }, 
        	{ "title": "Stato", "targets": 7, "width": 77 },  
            { "title": "Creato il", "targets": 8, "width": 70 },
            { "title": "Data modifica", "targets": 9, "width": 110 },          
        	{ "title": "Finanziamento", "targets": 10, "width": 110 },
    
        	{ targets: [2, 7, 10], searchable: false, orderable: true, visible: true, render:$.fn.dataTable.render.text()},
        	
        	{ targets: 0, searchable: false, orderable: false, visible: true, render: function(data, type, full, meta) { 
          		
                var actionColumn = '';
                var btnVisualizza = '<a class="btn btn-primary databutton popup-with-form" style="letter-spacing: normal" href="'+context+'/getRiassunto/'+data.eventoId+'" data-toggle="tooltip" data-container="body" title="Visualizza" data-html="true"><i class="dms-wk-icon-show-details" aria-hidden="true"></i></a>';
                var btnGestisci = '<button onclick="visualizzaEvento('+data.eventoId+',\''+data.stato+'\')" class="btn btn-primary databutton" type="button" data-toggle="tooltip" data-container="body" title="Gestisci" data-html="true"><i class="dms-wk-icon-edit" aria-hidden="true"></i></button>';
                var btnDuplica= '<button onclick="duplicaEvento('+data.eventoId+')" class="btn btn-primary databutton" type="button" data-toggle="tooltip" data-container="body" title="Duplica" data-html="true"><i class="dms-wk-icon-copy" aria-hidden="true"></i></button>';
                var btnElimina= '<button class="btn btn-primary databutton" onclick="eliminaEvento('+data.eventoId+')" type="button" data-toggle="tooltip" data-container="body" title="Cancella" data-html="true"><i class="dms-wk-icon-delete" aria-hidden="true"></i></button>';
                
                if(full.stato != 'Bozza') {
        			actionColumn = '<div class="iconstable">' + btnVisualizza + btnGestisci + btnDuplica +'</div>';
        		} else {
        			actionColumn = '<div class="iconstable">' + btnVisualizza + btnGestisci + btnDuplica + btnElimina +'</div>';
                }
                
        		return actionColumn;
              }},
              
            { targets: 1, searchable: true, orderable: true, visible: true, render: function(data, type, full, meta) {
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
             { targets: 3, searchable: false, orderable: true, visible: true, render: function(data, type, full, meta) { 
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
              { targets: 4, searchable: false, type: 'date-euro', orderable: true, visible: true, render: function(data, type, full, meta) { 
            	if(full.dataDa != ' '){
 					var data =  moment(full.dataDa, 'DD/MM/YYYY').format('DD/MM/YY');
              	}else{
              		var data =  '';
              	}
 				return data;
               }},
               
               { targets: 5, searchable: false, type: 'date-euro', orderable: true, visible: true, render: function(data, type, full, meta) { 
           		if(full.dataA != ' '){
  					var data =  moment(full.dataA, 'DD/MM/YYYY').format('DD/MM/YY');
               	}else{
             	  var data =  '';
               	}
  				return data;
                }},
                

                
                { targets: 6, searchable: true, orderable: true, visible: true, render: function(data, type, full, meta) {
                	var lugtitolot = full.comune.length;
                	var terminale = '';
                  	if(lugtitolot > 35){      
                  		terminale ='...';
                  	}                  	
                  	var comune = full.comune.slice(0,30);               	  
                	var data =  comune+terminale;
        
                  	return data;              		
                 }}, 
                 
                 { targets: 8, searchable: false, type: 'date-euro', orderable: true, visible: true, render: function(data, type, full, meta) { 
                		if(full.dataIns != ' '){
        					var data =  moment(full.dataIns, 'DD/MM/YYYY').format('DD/MM/YY');
                     	}else{
                   	  var data =  '';
                     	}
        				return data;
                      }},
                      
                 { targets: 9, searchable: false, type: 'date-euro', orderable: true, visible: true, render: function(data, type, full, meta) { 
     	                if(full.dataMod){
     	        			var data =  moment(full.dataMod, 'DD/MM/YYYY').format('DD/MM/YY');
     	                }else{
     	                	var data =  '';
     	                }
             			return data;
                      }},
                

        	
        ],
        order: [[ 9, "desc" ]],
        initComplete: function(settings, json) {
        	this.find('.dataTables_scrollBody').on('scroll', function() {
        		            this.find('.dataTables_scrollHeadInner').scrollLeft($(this).scrollLeft());
        		        }); 
        
        	//$('.dataTables_scrollBody:has(#t_eventi)').append('<div id="overlaytable1" class="overlaytable"><div class="spinner"></div></div>');
        	setTimeout(function(){ 
        		icontabelle();        	
        		$('.dataTables_scrollBody:has(#t_eventi)').append('<div id="overlaytable1" class="overlaytable" style="display:none;"><div class="spinner"></div></div>');
        	    $('.popup-with-form').magnificPopup({
	        		type: 'ajax',
	        		closeOnBgClick: false
	        	}); 
        	    $('#t_eventi').DataTable().columns.adjust();
        	}, 1300);  
        	recoveryfilter(filter);
        },
        drawCallback: function(){
        	setTimeout(function(){ 
        		icontabelle();
        		$('.popup-with-form').magnificPopup({
            		type: 'ajax',
            		closeOnBgClick: false
            	});    	
            	
            	}, 1300);
        	$(".tooltip").tooltip("hide");
        	$('[data-toggle="popover"]').popover({ placement : 'right', trigger : 'hover', container: "body"});  
        	$('[data-toggle="popoverclick"]').popover({  placement : 'top', container: "body", trigger : 'click', delay: {'show' : 0, 'hide' : 300} });
        	//$('[data-toggle="popover"]').popover({ 'trigger': 'hover'});
       	 	//$('[data-toggle="tooltip"]').tooltip({ 'trigger': 'hover'});
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
	
	$(document).on('click', 'div.denomTooltip' , function(){
		$(".tooltip").tooltip("hide");
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
	
	$('#t_eventi').on('page.dt', function() { $('#overlaytable1').show(); $('.dataTables_scrollBody').scrollTop(0); });
	
	$("#excel").click(function(){
		
		var mypar = JSON.parse(window.param);
		mypar.detail.length=-1;
		var par = JSON.stringify(mypar);
		$("#excel").attr("disabled", true);
		$("#excel").find("span").text("Elaborazione in corso...");
		$("#excel").find("i").show();
		//$("#imageDownloadXLS").hide();
		$.ajax({

			url: context + "/exportEventi/start?ruolo=PROMOTORE",
			contentType: 'application/json',
			type: "POST",
			data:par,
			error: function (xhr, error, thrown) {
				if (xhr.status == 403) {
					$('#overlay').hide();
					alertify.alert(window.mexJS['js.error.errore'], "<img src=\""+context+"/assets/images/ERRORE.svg\"><br/><br/><br/>"+window.mexJS['js.sessionescaduta']).set('onok', function(closeEvent){window.location.href = window.areaRiservata;}).set('label', 'Chiudi'); 
					
				}
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
	var filter2 = "";
	var dataTabS = $('#t_segnalazioni').DataTable({
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
        	url: context+"/segnPageList",
            contentType: 'application/json',
            type: "POST",
            data: function(d) {
            	
              	var jsondatatables = '';
            	if(sessionStorage.getItem("jsondatatables")){
            		jsondatatables = JSON.parse(sessionStorage.getItem("jsondatatables"));
            		window.sessionStorage.removeItem("jsondatatables");
            	}
            	
              	var pageidmenus = '';
            	if(sessionStorage.getItem("pageidmenus")){
            		pageidmenus = sessionStorage.getItem("pageidmenus");
            		window.sessionStorage.removeItem("pageidmenus");
            	}
            	
            	if(jsondatatables && pageidmenus == 'filtriGestionePromozione.jsp'){
            		filter2 = jsondatatables;
            	}else{            	
            		filter2 = $('#formSegnFilter').serializeFormJSON();
            	}
            	
            	return convertDatatableWrapper(d, dataTabS.settings().init().columns, filter2);
            },
            error: function (xhr, error, thrown) {
            	if (xhr.status == 403) {
		        	$('#overlay').hide();
		    		alertify.alert(window.mexJS['js.errore.errore'], "<img src=\""+context+"/assets/images/ERRORE.svg\"><br/><br/><br/>"+window.mexJS['js.sessionescaduta']).set('onok', function(closeEvent){window.location.href = window.areaRiservata;}).set('label', 'Chiudi'); 
            		}
            }
        },
        columns: [        	
        
        	{ name: "strumenti", data: null },
            { name: "nome", data: "nome" },
            { name: "dataDa", data: "dataDa" },
            { name: "dataA", data: "dataA" },
            { name: "comune", data: "comune" },            
            { name: "riferimento", data: "riferimento" }, // da modificare
            { name: "riferimento", data: "riferimento" },
            { name: "dataIns", data: "dataIns" },
            { name: "status", data: "status" }           
        ],
        columnDefs: [        	
        
        	{ targets: 0, "width": 94, searchable: false, orderable: false, visible: true, render: function(data, type, full, meta){
        		if(data.status != 'Da valutare') {
        			data = '<div class="iconstable"><button class="btn btn-primary databutton" type="button" disabled data-toggle="tooltips" data-container="body" title="Gestisci" data-html="true"><i class="dms-wk-icon-agenda" aria-hidden="true"></i></button><button class="btn btn-primary databutton" type="button" disabled data-toggle="tooltips" data-container="body" title="Ignora" data-html="true"><i class="dms-wk-icon-delete" aria-hidden="true"></i></button></div>';
        		} else {
        			data = '<div class="iconstable"><button onclick="gestisciSegnalazione('+data.segnalazioneId+')" class="btn btn-primary databutton" type="button" data-toggle="tooltips" data-container="body" title="Gestisci" data-html="true"><i class="dms-wk-icon-agenda" aria-hidden="true"></i></button><button onclick="ignoraSegnalazione('+data.segnalazioneId+')" class="btn btn-primary databutton" type="button"  data-container="body" title="Ignora" data-html="true"><i class="dms-wk-icon-delete" aria-hidden="true"></i></button></div>';
        		}
                 return data;
              }},
              
				//             { targets: 2, "width": 197, searchable: true, orderable: true, visible: true, render: function(data, type, full, meta) {
				//              	
				//            	var titoloevento = full.nome;            	 
				//            	var lugtitolot = titoloevento.length;            	
				//              	var terminale = '';
				//                	if(lugtitolot > 35){      
				//                		terminale ='...';
				//                	}                	
				//                var titolo = full.nome.slice(0,32);               	  
				//              	var info = "<img src=\""+context+"/assets/svg/icon-informazioni.svg\"  style=\"height:24px;\"data-toggle=\"tooltip\" data-container=\"body\" title=\""+full.nome+"\" data-html=\"true\">";        			
				//              	var data =  info+" "+titolo+terminale;
				//      
				//                	return data;              		
				//               }},               
               
              { targets: 1, "width": 197, searchable: true, orderable: true, visible: true, render: function(data, type, full, meta) {
            	  
               	var lugtitolot = full.nome.length;
               	var terminale = '';
                 	if(lugtitolot > 40){      
                 		terminale ='...';
                 		var titolo = full.nome.slice(0,37);  
                   	var data = "<div class=\"denomTooltipover\" data-toggle=\"tooltip\" data-container=\"body\" title=\""+full.nome+"\" data-html=\"true\">"+titolo+terminale+"</div>";            	
                   }else{
                   	var data = full.nome;
                   }              	
                 
                 	return data;              		
                }}, 
                            
              { targets: 2, "width": 75, searchable: true, type: 'date-euro', orderable: true, visible: true, render: function(data, type, full, meta) { 

               	  
            		if(full.dataDa != ' '){
    					var data =  moment(full.dataDa, 'DD/MM/YYYY').format('DD/MM/YY');
                 	}else{
                 		var data =  '';
                 	}
            		//var data =  tooltips+" "+info+" "+full.tipo;
    				return data;
                  }},
                  
               { targets: 3, "width": 75, searchable: true, type: 'date-euro', orderable: true, visible: true, render: function(data, type, full, meta) { 
             		if(full.dataA != ' '){
     					var data =  moment(full.dataA, 'DD/MM/YYYY').format('DD/MM/YY');
                  	}else{
                	  var data =  '';
                  	}
             		//var data =  tooltips+" "+info+" "+full.tipo;
     				return data;
                   }},   
              
        	{ targets: 4,  "width": 150, searchable: true, orderable: true, visible: true, render:$.fn.dataTable.render.text() },
        	
        	{ targets: 5,  "width": 200, searchable: true, orderable: true, visible: true, render: function(data, type, full, meta) {
    			
    			
    		    var info = "";
    		    var name = ""; 
    		    var fullowner = full.nomeUtente;             		    
    		    var fullemailOwner = full.emailUtente;    		    
   
    			var info = "<div id=\"dcreatoda\"><span id=\"tcreatoda\">"+fullowner+"</span></div>";     			 
		  		        		
    			if(fullemailOwner){
    				if(fullemailOwner.trim()){
    					name = '<a href="mailto:'+fullemailOwner+'"  data-toggle=\"tooltip\" data-container=\"body\" title=\"'+fullemailOwner+'\" data-html=\"true\" id=\"acreatoda\"><i class=\"dms-wk-icon-email\" aria-hidden=\"true\"></i></a> ';
    				}
    			}
    			
    			return "<div class=\"linetd\">"+ name + info+"</div>";
    		}
    	},
        	
        	
        	
        	
        	{ targets: 6, "width": 150, searchable: true, orderable: true, visible: true, render: function(data, type, full, meta) {
                 	                   	
                   	var lugtitolot = full.riferimento.length;
                   	var terminale = '';
                     	if(lugtitolot > 35){      
                     		terminale ='...';
                     		var riferimento = full.riferimento.slice(0,32);  
                       	var data = "<div class=\"denomTooltipover\" data-toggle=\"tooltip\" data-container=\"body\" title=\""+full.riferimento+"\" data-html=\"true\">"+riferimento+terminale+"</div>";            	
                       }else{
                       	var data = full.riferimento;
                       }              	
                     
                     	return data;                   	
              }}, 
        	               
            { targets: 7, "width": 75, searchable: true, type: 'date-euro', orderable: true, visible: true, render: function(data, type, full, meta) {
              		if(full.dataIns != ' '){
      					var data =  moment(full.dataIns, 'DD/MM/YYYY').format('DD/MM/YY');
                   	}else{
                 	  var data =  '';
                   	}
              		//var data =  tooltips+" "+info+" "+full.tipo;
      				return data;
                }
            },       	
       
        	{ targets: 8, "width": 90, searchable: true, orderable: true, visible: true, render:  function(data, type, full, meta) {
              		if(data == 'Ignorata'){
      					data = "Ignorato";
                   	}else if (data == 'Gestita'){
                 		data =  'Gestito';
                   	}
      				return data;
                } 
            }
        	
        ],
        order: [[ 7, "desc" ]],
        initComplete: function(settings, json) {
        		
        	setTimeout(function(){ 
        		icontabellesegnalazioni();     
        		$('.dataTables_scrollBody:has(#t_segnalazioni)').append('<div id="overlaytable2" class="overlaytable" style="display:none;"><div class="spinner"></div></div>');
        		$('#t_segnalazioni').DataTable().columns.adjust();
        	}, 1300);     	 	
        	
        	recoveryfilter2(filter2);   
        },
        preDrawCallback: function(settings) {
            var api = new $.fn.dataTable.Api(settings);
            var pagination = $(this).closest('.dataTables_wrapper').find('.dataTables_paginate');
            pagination.toggle(api.page.info().pages > 1);
        },
		drawCallback:  function( settings ) {
			
			
			
			setTimeout(function(){ 
				icontabellesegnalazioni(); 
            	}, 1300);
			
			
			
			$(".tooltip").tooltip("hide");
			$('[data-toggle="popover"]').popover({ 'trigger': 'hover'});
			//$('[data-toggle="tooltip"]').tooltip({ 'trigger': 'hover'});
			tsegnalazionisearch();			
			$('#overlay').hide();
			$('#overlaytable2').hide();
		}
	});
	
	$('#t_segnalazioni').on('page.dt', function() { $('#overlaytable2').show(); });	
	//$("#nazione")[0].selectize.setValue(window.italia.codNazione, false);
	//$("#regione")[0].selectize.setValue(window.puglia.codRegione, false);	
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
	$('#t_segnalazioni').DataTable().columns.adjust();	
});

$("#eventi-tab").click(function(){
	$("#gesteventi").addClass("active in");
	$("#eventi-tab").addClass("active in");
	$("#eventibox").addClass("active in");
	$("#gestsegnalazioni").removeClass("active in");
	$("#segnalazioni-tab").removeClass("active in");
	$("#segnalazionibox").removeClass("active in");
	$("#resetevento").click();
	$('#t_eventi').DataTable().columns.adjust();	
 	setTimeout(function(){      	
		  $('.popup-with-form').magnificPopup({
		type: 'ajax',
		closeOnBgClick: false
	});
	}, 1300);
});

$('.end-d').datepicker({format: 'dd/mm/yyyy', language: 'it', autoclose: true,  constrainInput: false});
$('.start-d').datepicker({format: 'dd/mm/yyyy', language: 'it', autoclose: true,  constrainInput: false}).on('change', function () {
	var minDate = $(this).val();	
	if(minDate ==''){
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
	window.sessionStorage.removeItem("jsondatatable");
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
	$("#stato")[0].selectize.clear();
	$("#comun")[0].selectize.clear();
	$("#comun")[0].selectize.enable();
	$('.start-d').datepicker('setEndDate', null);
	$('.end-d').datepicker('setStartDate', null);
	$('.startS-d').datepicker('setEndDate', null);
	$('.endS-d').datepicker('setStartDate', null);
	$('#estero').prop('disabled', true);
	$('#regione').prop('disabled', false);
	$("#regione")[0].selectize.clear();
	$("#regione")[0].selectize.enable();
    $('#tipologia')[0].selectize.clear();
    $('#filtroMIBACT')[0].selectize.clear();
    $("#nazione")[0].selectize.clear();
    $("#regione")[0].selectize.clear();
    //$("#nazione")[0].selectize.setValue(window.italia.codNazione, false);
    //$("#regione")[0].selectize.setValue(window.puglia.codRegione, false);	
});


$("#cercasegnalazione").click(function(){
	window.sessionStorage.removeItem("jsondatatables");
	$('#overlay').show();
	var jsonDTO = $('#formSegnFilter').serializeFormJSON();
	$('#t_segnalazioni').DataTable().draw();
	$(".tooltip").tooltip("hide");
	$('[data-toggle="popover"]').popover({ 'trigger': 'hover'});
	$('[data-toggle="popoverclick"]').popover({  placement : 'top', container: "body", trigger : 'click', delay: {'show' : 0, 'hide' : 300} });
	$('[data-toggle="tooltip"]').tooltip({ 'trigger': 'hover'});  
});

$("#resetsegnalazione").click(function(){
	window.sessionStorage.removeItem("jsondatatables");
	//$('#overlay').show();	
	$('#formSegnFilter').find('input').val('');
	$("#statoS")[0].selectize.clear();
	$("#comunS")[0].selectize.clear();
	$("#comunS")[0].selectize.enable();
	$('.start-d').datepicker('setEndDate', null);
	$('.end-d').datepicker('setStartDate', null);
	
//    $('#t_segnalazioni_filter').find('input').val('');
//    $('#t_segnalazioni').DataTable().draw();
//	setTimeout(function(){ 
//		icontabelle();        	
//		$('.dataTables_scrollBody:has(#t_segnalazioni)').append('<div id="overlaytable1" class="overlaytable" style="display:none;"><div class="spinner"></div></div>');
//	    $('.popup-with-form').magnificPopup({
//    		type: 'ajax',
//    		closeOnBgClick: false
//    	});        	
//	}, 1300); 
	
	
});

$("[name='creaevento']").click(function(){
	creaEventoForm();
});



function exportFetch(uuid){	
	var xhr = new XMLHttpRequest();  
    var xhr = new XMLHttpRequest();
    xhr.open('GET', context+'/exportEventi/fetch?uuid='+ uuid  +'&ruolo=PROMOTORE' , true);
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
        }else if(this.status==500)
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
		        	////$('#t_eventi').DataTable().draw();
		        	$('#overlay').hide();
		        	alertify.alert(window.mexJS['js.esito.operazione'], "<i class=\"icon-successo\" style=\"font-size:64px; color:#58CB7D;\"></i><br/><br/><br/>"+window.mexJS['js.esitodettaglio.evento.duplica']).set('onok', function(){ $('#t_eventi').DataTable().columns.adjust().draw();
		        	
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
	        		//alertify.alert(window.mexJS['js.error.errore'], "<i class=\"icon-errore\" style=\"font-size:64px; color:#58CB7D;\"></i><br/><br/><br/>"+window.mexJS['js.esitodettaglio.evento.duplica.error']).set('onok', function(){$('#').DataTable().draw();} ).set('label', 'Chiudi');
		        
	        		alertify.alert("Abbiamo riscontrato un problema", "<img src=\""+context+"/assets/images/ERRORE.svg\"><br/><br/><br/>"+window.mexJS['js.esitodettaglio.evento.duplica.error']).set('label', 'Chiudi'); 
	        		
		        
		        
		        }
		    });
			}).set('oncancel', function(){
				//$('#t_eventi').DataTable().draw();
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
		        	//('#t_eventi').DataTable().draw();
		        	$('#overlay').hide();
		        	alertify.alert(window.mexJS['js.esito.operazione'], "<i class=\"icon-successo\" style=\"font-size:64px; color:#58CB7D;\"></i><br/><br/><br/>"+window.mexJS['js.esitodettaglio.evento.elimina']).set('onok', function(){ $('#t_eventi').DataTable().columns.adjust().draw();
		        	
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
				//$('#t_eventi').DataTable().draw();
	     	  	setTimeout(function(){ 
	           	  	$('.popup-with-form').magnificPopup({
	            		type: 'ajax',
	            		closeOnBgClick: false
	            	});    	
	            	
	            	}, 1300);
			});
}

function gestisciSegnalazione(idSegnalazione) {
	window.sessionStorage.setItem("jsondatatables",JSON.stringify($('#formSegnFilter').serializeFormJSON()));
	window.sessionStorage.setItem("pageidmenus",idmenu);
	$('#overlay').show();
	$.ajax({
        type: "GET",
        url: context+"/getSegnalazione?segnalazioneId="+idSegnalazione,
        dataType: "json",
        success: function(response) {

        	window.segnalazioneScelta = response;
       
        	$("[name='creaevento']").click();
        }
    });
}

function ignoraSegnalazione(idSegnalazione) {
	
    alertify.confirm("<i class=\"icon-errore\" style=\"font-size:64px; color:#a66300;\"></i><br><br>" + window.mexJS['js.conferma.segnalazione.ignora']).set({
		title:window.mexJS['js.tipologia.attenzione']}).set('labels', {ok:'Si', cancel:'No'}).set('onok', function(){
		
		$.ajax({
	        url: context + "/cambiaStato?segnalazioneId=" + idSegnalazione,
	        type: "PUT",
	        contentType: 'application/json',
	        data: JSON.stringify({"stato":"Ignorata"}),
	        beforeSend: function(){
				$('#overlay').show();
			},
			complete: function(){
				$('#overlay').hide();
			},
	        success: function() {
	        	$('#t_segnalazioni').DataTable().draw();
	        	alertify.alert(window.mexJS['js.esito.operazione'], "<i class=\"icon-successo\" style=\"font-size:64px; color:#58CB7D;\"></i><br/><br/><br/>" + window.mexJS['js.esitodettaglio.segnalazione.ignora']).set('onok', function(){
					//$('#t_segnalazioni').DataTable().draw();
				}).set('label', 'Chiudi'); 
	        }
	    });
	}).set('oncancel', function(){});
}

$(".accordionF").on("click", ".accordionF-header", function() {
	$(this).toggleClass("active").next().slideToggle();
});
$(".accordionA1").on("click", ".accordionA1-header", function() {
	$(this).toggleClass("active").next().slideToggle();
});

$(document).ready(function(){
	   $("#idevento").bind("keypress", function (e) {
	          var keyCode = e.which ? e.which : e.keyCode
	               
	          if (!(keyCode >= 48 && keyCode <= 57)) {
	            $(".error").css("display", "inline");
	            return false;
	          }else{
	            $(".error").css("display", "none");
	          }
	      });	
	
	  $(".dropdownbox").click(function(){
		    $(this).next("ul.nav.nav-tabs").toggleClass("showMenu");
		    $(this).next("ul.nav.nav-tabs > li > a").click(function(){
	        //$(".dropdownbox > p").text($(this).text());
			$(this).next("ul.nav.nav-tabs").removeClass("showMenu");
	      });
	  });	  
});

$('#mobiletabFilter, #mobiletabFilter2').on('change', function (e) {
	  var $optionSelected = $("option:selected", this);
	  $optionSelected.tab('show')
	});

$('#mobilemyTabFE').on('change', function (e) {

	var valore = $(this).val();
	
	if(valore =="segnalazioni-tab"){
		$("#gesteventi").removeClass("active in");
		$("#eventi-tab").removeClass("active in");
		$("#eventibox").removeClass("active in");
		$("#gestsegnalazioni").addClass("active in");
		$("#segnalazioni-tab").addClass("active in");
		$("#segnalazionibox").addClass("active in");
		$("#resetsegnalazione").click();
		$('#t_segnalazioni').DataTable().columns.adjust();
		
	 	setTimeout(function(){      	
			  $('.popup-with-form').magnificPopup({
			type: 'ajax',
			closeOnBgClick: false
		});
		}, 1300);	
		
		icontabelle(); 
		icontabellesegnalazioni(); 
		
		$(".tooltip").tooltip("hide");
		$('[data-toggle="popover"]').popover({ 'trigger': 'hover'});
		$('[data-toggle="popoverclick"]').popover({  placement : 'top', container: "body", trigger : 'click', delay: {'show' : 0, 'hide' : 300} });
		$('[data-toggle="tooltip"]').tooltip({ 'trigger': 'hover'});  
	}
	if(valore =="eventi-tab"){
	
		$("#gesteventi").addClass("active in");
		$("#eventi-tab").addClass("active in");
		$("#eventibox").addClass("active in");
		$("#gestsegnalazioni").removeClass("active in");
		$("#segnalazioni-tab").removeClass("active in");
		$("#segnalazionibox").removeClass("active in");
		$("#resetevento").click();
		$('#t_eventi').DataTable().columns.adjust();
		
	 	setTimeout(function(){      	
			  $('.popup-with-form').magnificPopup({
			type: 'ajax',
			closeOnBgClick: false
		});
		}, 1300);	
		
		icontabelle();
		icontabellesegnalazioni(); 
		$(".tooltip").tooltip("hide");
		$('[data-toggle="popover"]').popover({ 'trigger': 'hover'});
		$('[data-toggle="popoverclick"]').popover({  placement : 'top', container: "body", trigger : 'click', delay: {'show' : 0, 'hide' : 300} });
		$('[data-toggle="tooltip"]').tooltip({ 'trigger': 'hover'});  
	}

});


function teventisearch() {	
var dtable = $('#t_eventi').DataTable();

$("#t_eventi_filter input")
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

function tsegnalazionisearch() {	
	var dtable = $('#t_segnalazioni').DataTable();

	$("#t_segnalazioni_filter input")
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
	
	$(document).ready(function() {			
		if (statoStruttura=="IN VALIDAZIONE"){
			$("#strutturainattesa").show();
		}else{
			$("#strutturainattesa").hide();
		}
	});
