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
			
	if (window.flagPrm["filtroRedattore"] != true) {
		$("#schedaRedHid").attr("hidden", true);
	} 
	
	$('#t_eventifinanziatiPro').on('processing.dt', function(e, settings, processing) {
		if (processing) {
			$('#overlaytable1').show();
	    } else {
	    	$('#overlaytable1').hide();
	     }
	});
	var filter = "";
	//console.log(new Date());
	var dataTab = $('#t_eventifinanziatiPro').DataTable({
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
            	            	
           	
            	filter = $('#formEventFilter').serializeFormJSON();            	 
            	            	
				filter.serviceCode = "FIN";
			
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
        	{ name: "titoloBando", data: "titoloBando" },
        	{ name: "titoloProgetto", data: "titoloProgetto" },
            { name: "dataDa", data: "dataDa" },
            { name: "dataA", data: "dataA" },    
            { name: "comune", data: "comune" },
            { name: "eventoId", data: "eventoId" },
        ],
        columnDefs: [
        	{ "title": "Azioni", "targets": 0, "width": 140 },
        	{ "title": "Titolo", "targets": 1, "width": 197 },
        	{ "title": "Bando", "targets": 2, "width": 197 },  
        	{ "title": "Progetto", "targets": 3, "width": 197 },  
        	{ "title": "Data da", "targets": 4, "width": 70 },
        	{ "title": "Data a", "targets": 5, "width": 70 },
        	{ "title": "Comune", "targets": 6, "width": 200 },
        	{ "title": "ID", "targets": 7, "width": 50 }, 
        	
          	{ targets: 0, searchable: false, orderable: false, visible: true, render: function(data, type, full, meta) { 

				var actionColumn = '';
        		var btnVisualizza = '<a class="btn btn-primary databutton popup-with-form" style="letter-spacing: normal" href="'+context+'/getRiassunto/'+data.eventoId+'" data-container="body" title="Visualizza" data-html="true"><i class="dms-wk-icon-show-details" aria-hidden="true"></i></a>';
        		//var btnGestisci = '<button onclick="visualizzaRevisioni('+data.eventoId+',\''+data.stato+'\')" class="btn btn-primary databutton" data-container="body" title="Visualizza revisioni" data-html="true"><i class="dms-wk-icon-concorsi" aria-hidden="true"></i></button>';
        		var btnRicevutaBase= '<button id="buno" onclick="ricevuta(\''+data.eventoId+'\',\'base\')" class="btn btn-primary databutton" type="button" data-container="body" title="Scarica prima ricevuta" data-html="true"><i class="dms-wk-icon-download" aria-hidden="true"></i><div class="cerchiouno">1</div></button>';
        		var btnRicevutaFinale= '<button id="bdue" onclick="ricevuta(\''+data.eventoId+'\',\'runtime\')" class="btn btn-primary databutton" type="button" data-container="body" title="Scarica ricevuta aggiornata" data-html="true"><i class="dms-wk-icon-download" aria-hidden="true"></i><div class="cerchiodue">2</div></button>';
        
        		if(full.stato == 'Bozza'){
            		//	btnGestisci =    '<button disabled="disabled" class="btn btn-primary databutton" data-container="body" title="Visualizza revisioni" data-html="true"><i class="dms-wk-icon-concorsi" aria-hidden="true"></i></button>';
                		btnRicevutaBase= '<button disabled="disabled" class="btn btn-primary databutton" type="button" data-container="body" title="Ricevuta(1)" data-html="true"><i class="dms-wk-icon-download" aria-hidden="true"></i><div class="cerchiouno">1</div></button>';
                		btnRicevutaFinale= '<button disabled="disabled" class="btn btn-primary databutton" type="button" data-container="body" title="Ricevuta(2)" data-html="true"><i class="dms-wk-icon-download" aria-hidden="true"></i><div class="cerchiodue">2</div></button>';
            		}        		
            		
				actionColumn = '<div class="iconstablericevute">'+ btnVisualizza /*+ btnGestisci*/ + btnRicevutaBase + btnRicevutaFinale +'</div>';

          		return actionColumn;
            }},
            
            { targets: 1, "width": 197, searchable: true, orderable: true, visible: true, render: function(data, type, full, meta) {
            	var lugtitolot = full.titolo.length;
            	var terminale = '';
              	if(lugtitolot > 40){      
              		terminale ='...';
              		var titolo = full.titolo.slice(0,37);  
              		var fulltitolo = specialcharacters(full.titolo);              		
              		info = "<img src=\""+context+"/assets/svg/icon-informazioni.svg\"  style=\"width:26px;\" data-toggle=\"tooltip\" data-container=\"body\" title=\""+fulltitolo+"\" data-html=\"true\">";        			
            		var data =  info+" "+titolo;
              	}else{                		
                	var data = full.titolo;
                }              	
              
              	return data;              		
             }},  
             
             { targets: 2, "width": 197, searchable: true, orderable: true, visible: true, render: function(data, type, full, meta) {
              	var lugtitolot = full.titoloBando.length;
              	var terminale = '';
                	if(lugtitolot > 40){      
                		terminale ='...';
                		var titoloBando = full.titoloBando.slice(0,37);  
                		var fulltitolo = specialcharacters(full.titoloBando);              		
                		info = "<img src=\""+context+"/assets/svg/icon-informazioni.svg\"  style=\"width:26px;\" data-toggle=\"tooltip\" data-container=\"body\" title=\""+fulltitolo+"\" data-html=\"true\">";        			
              		var data =  info+" "+titoloBando;
                	}else{                		
                  	var data = full.titoloBando;
                  }              	
                
                	return data;              		
               }},  
               
               
               { targets: 3, "width": 197, searchable: true, orderable: true, visible: true, render: function(data, type, full, meta) {
            	   if(full.titoloProgetto != null){
     	       			var lugtitolot = full.titoloProgetto.length;
   	              	var terminale = '';
   	                	if(lugtitolot > 40){      
   	                		terminale ='...';
   	                		var titoloProgetto = full.titoloProgetto.slice(0,37);  
   	                		var fulltitolo = specialcharacters(full.titoloProgetto);              		
   	                		info = "<img src=\""+context+"/assets/svg/icon-informazioni.svg\"  style=\"width:26px;\" data-toggle=\"tooltip\" data-container=\"body\" title=\""+fulltitolo+"\" data-html=\"true\">";        			
   	              		var data =  info+" "+titoloProgetto;
   	                	}else{                		
   	                  	var data = full.titoloProgetto;
   	                  }
               	}else{
               		var data =  '';
               	}
   	                
   	                	return data;              		
                  }},  
        	        	

	        	
	        { targets: 4, "width": 70, searchable: false, type: 'date-euro', orderable: true, visible: true, render: function(data, type, full, meta) { 
	       		if(full.dataDa != ' '){
						var data =  moment(full.dataDa, 'DD/MM/YYYY').format('DD/MM/YY');
	            	}else{
	            		var data =  '';
	            	}
	       		//var data =  tooltips+" "+info+" "+full.tipo;
					return data;
	         }},
        	
              
           { targets: 5, "width": 70, searchable: false, type: 'date-euro', orderable: true, visible: true, render: function(data, type, full, meta) { 
            		if(full.dataA != ' '){
    					var data =  moment(full.dataA, 'DD/MM/YYYY').format('DD/MM/YY');
                 	}else{
               	  var data =  '';
                 	}
            		//var data =  tooltips+" "+info+" "+full.tipo;
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
             }}
        	
      
        ],
        order: [[ 7, "desc" ]],
        initComplete: function(settings, json) {
        	this.find('.dataTables_scrollBody').on('scroll', function() {
        		            this.find('.dataTables_scrollHeadInner').scrollLeft($(this).scrollLeft());
        		        }); 
        	//$('.dataTables_scrollBody:has(#t_eventifinanziati)').append('<div id="overlaytable1" class="overlaytable"><div class="spinner"></div></div>');
        	//$('th').css('min-width', '75px');
        	$('#t_eventifinanziatiPro').DataTable().columns.adjust();
           	setTimeout(function(){ 
        		icontabelle(); 
        		$('.dataTables_scrollBody:has(#t_eventifinanziatiPro)').append('<div id="overlaytable1" class="overlaytable" style="display:none;"><div class="spinner"></div></div>'); 
        		
            	$('.popup-with-form').magnificPopup({
            		type: 'ajax',
            		closeOnBgClick: false
            	});        	
        	}, 1300);  
  
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

	$('#t_eventifinanziatiPro').on( 'page.dt', function () {
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

	
	$('#t_eventifinanziatiPro').on('page.dt', function() { $('.dataTables_scrollBody').scrollTop(0); });
	
	$("#excel").click(function(){
		var mypar = JSON.parse(window.param);
		mypar.detail.length=-1;
		var par = JSON.stringify(mypar);
		$("#excel").attr("disabled", true);
		$("#excel").find("span").text("Elaborazione in corso...");
		$("#excel").find("i").show();
		$.ajax({

			url: context + "/exportEventiFinanziati/start?ruolo=VALIDATORE",
			contentType: 'application/json',
			type: "POST",
			data:par,
			error: function (xhr, error, thrown) {
				if (xhr.status == 403) {
					$('#overlay').hide();
					alertify.alert(window.mexJS['js.error.errore'], "<img src=\""+context+"/assets/images/ERRORE.svg\"><br/><br/><br/>"+window.mexJS['js.sessionescaduta']).set('onok', function(closeEvent){window.location.href = window.areaRiservata;}).set('label', 'Chiudi'); 

				}
				  $("#excel").prop('disabled',false);		
			   		$("#excel").find("span").text("Scarica eventi finanziati");
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




$("#cercaevento").click(function(){

	$('#overlay').show();
	//var jsonDTO = $('#formEventFilter').serializeFormJSON();
	$('#t_eventifinanziatiPro').DataTable().draw();
	
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
	$('#formEventFilter').find('input').val('');   
	$("#progettoId")[0].selectize.clear();
	$('#progettoId').prop('disabled', true);
	$("#bandoId")[0].selectize.clear();
	$('#bandoId').prop('disabled', false);
});



function exportFetch(uuid){
	var xhr = new XMLHttpRequest();  
    xhr.open('GET', context+'/exportEventiFinanziati/fetch?uuid='+ uuid  +'&ruolo=VALIDATORE' , true);
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
		   		$("#excel").find("span").text("Scarica eventi finanziati");
		   		$("#excel").find("i").hide();
        }
        else if(this.status==500)
    	{
    	alertify.alert("Abbiamo riscontrato un problema", "<img src=\""+context+"/assets/images/ERRORE.svg\"><br/><br/><br/>"+window.mexJS['js.esitodettaglio.evento.duplica.error']).set('label', 'Chiudi'); 
		
    	clearInterval(fetch);
    	   $("#excel").prop('disabled',false);		
	   		$("#excel").find("span").text("Scarica eventi finanziati");
	   		$("#excel").find("i").hide();
    	}
    }; 
    xhr.setRequestHeader('Content-type', 'application/json; charset=UTF-8');
    xhr.send();
//    uuid=null;
    $('#overlay').hide();
};


$(".accordionF").on("click", ".accordionF-header", function() {
	$(this).toggleClass("active").next().slideToggle();
});
$(".accordionA1").on("click", ".accordionA1-header", function() {
	$(this).toggleClass("active").next().slideToggle();
});


var contaList = 0;

const newFirstElement = {bandoId: '', titoloBando: 'Tutti i bandi', progettoId: '', titoloProgetto: 'Tutti i progetti'};
const bandiList = [newFirstElement].concat(window.bandiList);

const newFirstElement2 = {progettoId: '', titoloProgetto: 'Tutti i progetti', bandoId: ''};
const progettiList = [newFirstElement2].concat(window.progettiList);

$(document).ready(function(){
	  $(".dropdownbox").click(function(){
		    $(this).next("ul.nav.nav-tabs").toggleClass("showMenu");
		    $(this).next("ul.nav.nav-tabs > li > a").click(function(){
	        //$(".dropdownbox > p").text($(this).text());
			$(this).next("ul.nav.nav-tabs").removeClass("showMenu");
	      });
	  });
	  
		$('#bandoId').selectize({
			valueField: 'bandoId',
		    labelField: 'titoloBando',
		    searchField: 'titoloBando',
		    placeholder: 'Inserisci',
		    create: false,
		    options:bandiList,
		    render: {
		        option: function(item, escape) {
		        	return '<div class="option">'+escape(item.titoloBando)+'</div>';
		        }
		    },
		    onChange: function(value) {		    	
		    	var progettoSelecitze = $('[name="progettoId"]').selectize()[0].selectize;
		    	progettoSelecitze.clear();
		    	progettoSelecitze.clearOptions();
    		
		    	if (!value.length){
		    		$('#progettoId').prop('disabled', true);
		    		progettoSelecitze.disable();
		           	progettoSelecitze.clear();
			    	progettoSelecitze.clearOptions();		    		
		    	}else{		
		        	var data = this.options[value];	
		        	var progettoid ="";
		        	var row = "";
		        	contaList = 0;
		        	progettoSelecitze.destroy();
		    		$('#progettoId').empty();
		    		$('#progettoId').prop('disabled', false);	
		    				      
		    		for (var i=0 ; i <progettiList.length ; i++)
			        {	
		    			if(progettiList[i]['progettoId']){
		    				progettoId = progettiList[i]['progettoId'];
		    			}else{
		    				progettoId = "";
		    			}	 
		    				if(progettiList[i]['progettoId']==''){
								 row = "<option value=''>"+progettiList[i]['titoloProgetto']+"</button>";			                
						    	$("#progettoId").append(row);
							}else{
		    			            if (progettiList[i]['bandoId'] == data.bandoId && progettoId != '') {			         
						            	 row = "<option value=\""+progettiList[i]['progettoId']+"\" >"+progettiList[i]['titoloProgetto']+"</button>";			                
						            	$("#progettoId").append(row);
						                contaList++;			                
						            }
							}	            
			        }
		            if(contaList>0){
		            	$('#progettoId').selectize({allowEmptyOption: true,  placeholder: 'Inserisci'});
		            }else{
		            	$('#progettoId').empty();		            	
		            	$('#progettoId').prop('disabled', true);
			    		progettoSelecitze.disable();
			           	progettoSelecitze.clear();
				    	progettoSelecitze.clearOptions();
				    	$('#progettoId').selectize({ allowEmptyOption: true, placeholder: 'Inserisci'});
		            }
		    	}
		    }
		}); 
		
		globalSelectizedProgetto = $('#progettoId').selectize({
			valueField: 'progettoId',
		    labelField: 'titoloProgetto',
		    searchField: 'titoloProgetto',
		    allowEmptyOption: true, 
		    placeholder: 'Inserisci'
		});
	    
});

$('#mobiletabFilter').on('change', function (e) {
	  var $optionSelected = $("option:selected", this);
	  $optionSelected.tab('show')
});

function teventisearch() {	
	var dtable = $("#t_eventifinanziatiPro").DataTable();

	$("#t_eventifinanziatiPro_filter input")
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
			

function visualizzaRevisioni(idEvento,stato) {			
		$('#overlay').show();		
		$('#t_revisionimodal').DataTable().destroy();
		$('#modalRevisioni').find("#idevento").val(idEvento);
		var logSet ='';

		var urlGetEvento = context + "/getEvento?idEvento=" + idEvento;		

				$.ajax({// Caricamento json evento
					async: false,
			        type: "GET",
			        url: urlGetEvento,
			        success: function(response) { 
			        	
			        	logSet = response.logSet;
			        	
			        			$("#strutturarevisione").html(response.datiGenerali.titoloMulti.IT);
			        	      					
					        },
					        error: function(error){
								$('#overlay').hide();
					    		if (error.status == 409){
					    			alertify.alert(window.mexJS['js.tipologia.attenzione'], "<img src=\"" + context + "/assets/images/PERICOLO.svg\"><br/><br/><br/>"+window.mexJS['js.risorsaoccupata']).set('label', 'Chiudi'); 
					    		}
					    	}
					    });
				
				
				var myTable = $('#t_revisionimodal').DataTable({
	        		language: { "url": context+"/assets/json/Italian.json" },
	        		scrollY: "396px",
	        	    scrollX: false,
	        	    paging:  true,
	        		pageLength: 5,
	        		lengthMenu: [[5, 10, 25, 50, 100, 250], [5, 10, 25, 50, 100, 250]],
	        		mark: true,
	        	    deferRender: true,
	        	    autoWidth: true,
	        	    scrollCollapse: true,
	        	    data: logSet,
	        	    columns: [
	        	    	{ name: "id", data: "logId" },
	        	        { name: "tipo", data: "tipoOperazione" },
	        	        { name: "data", data: "dataModifica"},
	        	        { name: "operazioni", data: "operazioni" },
	        	        { name: "utente", data: "nomeUtente" },
	        	        { name: "stato", data: "descrizioneStato" }
	        	        
	        	    ],	        	    
	        	    createdRow: function(row, data, dataIndex) {
	        	        if (data.toString().indexOf('Total') > -1) {
	        	          myTable.rows($(row)).remove().draw();
	        	        }
	        	    },
	        	    
	        	    columnDefs: [
	        	    	{ targets: [0], "width": 250, searchable: false, orderable: true, visible: false },
	        	    	{ targets: [1], "width": 250, "className":"operazione", searchable: true, orderable: true, visible: false, render:$.fn.dataTable.render.text() },    	
	        	    	{ targets: [2], "width": 250, type: 'date-euro', searchable: true, orderable: true, visible: true, render: function(data, type, full, meta) {   
	        	    		var datamodifica = convertDataEngToIta(data.substr(0,10)) + data.substr(10);
	        	    		var data =  moment(datamodifica, 'DD/MM/YYYY HH:mm:ss').format('DD/MM/YYYY HH:mm:ss');
	        	    	    return data;
	        	        } },
	        	    	{ targets: 3, "width": 250, searchable: true, orderable: true, visible: true, render:$.fn.dataTable.render.text() }, 
	        	    	{ targets: 4, "width": 250, searchable: true, orderable: true, visible: true, render: function(data, type, full, meta) {
	        	    		
	        	    		if(full.denominazioneAttivita){
	        	      			data = "<div class=\"denomTooltipover\" data-toggle=\"tooltip\" data-container=\"body\" title=\""+full.denominazioneAttivita+"\" data-html=\"true\">"+data+"</div>";     
	        	    		}                     	
	        	              return data;              		
	        	          }
	        	    	},
	        	    	{ targets: 5, "width": 169, searchable: true, orderable: true, visible: true, render:$.fn.dataTable.render.text() }, 
	        	    ],
	        	    order: [[ 2, "desc" ]],
	        	    initComplete: function(settings, json) {       	    	
	        	    
	        	    	$("[name='t_revisionimodal_length']").attr("data-ignorefield","");
	        	    	$('#t_revisionimodal').DataTable().draw();
	        	    
	        	    },
	        	    drawCallback: function(){
	        	    	$('[data-toggle="popover"]').popover({ 'trigger': 'hover'});
	        	    	$('[data-toggle="popoverclick"]').popover({  placement : 'top', container: "body", trigger : 'click', delay: {'show' : 0, 'hide' : 300} });
	        	    	$('[data-toggle="tooltip"]').tooltip({ 'trigger': 'hover'});
	        	    
	        	    }
	        	});
				
				
				//$('#t_revisionimodal').DataTable().draw();
	    		$('#modalRevisioni').modal('show');	
	    		
	    		
	    		setTimeout(function(){ 
	        	$('#overlay').hide();
				}, 1500);
			       
	}
	

	$("#excel").click(function(){
		
		var mypar = JSON.parse(window.param);
		mypar.detail.length=-1;
		var par = JSON.stringify(mypar);
		$("#excel").attr("disabled", true);
		$("#excel").find("span").text("Elaborazione in corso...");
		$("#excel").find("i").show();
		//$("#imageDownloadXLS").hide();
		$.ajax({

			url: context + "/exportEventi/start?ruolo=FINANZIATO",
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
	
	function exportFetch(uuid){
		var xhr = new XMLHttpRequest();  
	    xhr.open('GET', context+'/exportEventi/fetch?uuid='+ uuid  +'&ruolo=FINANZIATO' , true);
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
//	    uuid=null;
	    $('#overlay').hide();

	};
	

// Crea ricevuta
function ricevuta(id, stato) {
		

				
		
		var xhr = new XMLHttpRequest();  
    xhr.open('GET', context+'/ricevute/'+id+'?stato='+stato, true);
    xhr.responseType = 'arraybuffer';
    xhr.onload = function() {
        if(this.status == '200') {
        	
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
	         
        }
        else if(this.status==500)
    	{
    	alertify.alert("Abbiamo riscontrato un problema", "<img src=\""+context+"/assets/images/ERRORE.svg\"><br/><br/><br/>"+window.mexJS['js.esitodettaglio.evento.duplica.error']).set('label', 'Chiudi'); 
		

    	
    	}
    }; 
    xhr.setRequestHeader('Content-type', 'application/json; charset=UTF-8');
    xhr.send();
//  uuid=null;
    $('#overlay').hide();	
}
	

	//Scarica elenco revisioni
	$("#revisioni").click(function(){	
		var idEvento = $('#modalRevisioni').find("#idevento").val();	
		
		$("#revisioni").attr("disabled", true);
		$("#revisioni").find("span").text("Elaborazione in corso...");
		$("#revisioni").find("i").show();
		
		var xhr = new XMLHttpRequest();  
	    xhr.open('GET', context+'/exportRevisioni?idEvento='+ idEvento, true);
	    xhr.responseType = 'arraybuffer';
	    xhr.onload = function() {
	        if(this.status == '200') {
	        	
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
		           
		           $("#revisioni").prop('disabled',false);		
			   	   $("#revisioni").find("span").text("Scarica elenco revisioni");
			   	   $("#revisioni").find("i").hide();
	        }
	        else if(this.status==500)
	    	{
	    	alertify.alert("Abbiamo riscontrato un problema", "<img src=\""+context+"/assets/images/ERRORE.svg\"><br/><br/><br/>"+window.mexJS['js.esitodettaglio.evento.duplica.error']).set('label', 'Chiudi'); 
			

	    	   $("#revisioni").prop('disabled',false);		
		   		$("#revisioni").find("span").text("Scarica elenco revisioni");
		   		$("#revisioni").find("i").hide();
	    	}
	    }; 
	    xhr.setRequestHeader('Content-type', 'application/json; charset=UTF-8');
	    xhr.send();
	//  uuid=null;
	    $('#overlay').hide();	
		
	});
	