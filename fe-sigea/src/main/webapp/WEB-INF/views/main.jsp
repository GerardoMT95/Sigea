<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="false"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<html>
<head>
<%@ include file="base/head.jsp"%>
</head>
<script type="text/javascript"> 
		window.idEntita = "${idEntita}";
		window.flagPrm = JSON.parse('${flagPrm}'.replace(/=/g, ":"));
		window.idUtenteCorrente = '${idUtenteCorrente}';
		window.titoloUtente = '${titoloUtente}';
		window.tipoUtente = '${tipoUtente}';
	    var jsonAtt='${ fn:replace(fn:replace(attivita, "\'", "&rsquo;"), "\"", "\\\"")}';
	    	//'${fn:replace(attivita, '\'', '&rsquo;')}';		
		//window.attivita = JSON.parse('${attivita}'); 
	    window.attivita = JSON.parse(jsonAtt);		
		window.attivitaRichiesta = JSON.parse('${attivitaRichiesta}'); 
		window.mexJS = JSON.parse('${mexMap}'.replace(/=/g, ":"));
		window.context = '${pageContext.request.contextPath}';
		window.statiEvento = JSON.parse('${statiEvento}');
		window.statiEvento.sort((a, b) => (a.descrizione > b.descrizione) ? 1 : -1);
		window.mezzi = JSON.parse('${mezzi}');
		window.mezzi.sort((a, b) => (a.mezzo > b.mezzo) ? 1 : -1);
		window.prodotti = JSON.parse('${prodotti}');
		window.prodotti.sort((a, b) => (a.prodotto > b.prodotto) ? 1 : -1);
		window.tipologieMIBACT = JSON.parse('${tipologieMIBACT}');
		window.tipologieMIBACT.sort((a, b) => (a.tipologiaMIBACT > b.tipologiaMIBACT) ? 1 : -1);
		window.tipologieAttivita = JSON.parse('${tipologieAttivita}');
		window.tipologieAttivita.sort((a, b) => (a.tipologia > b.tipologia) ? 1 : -1);
		window.valoriAttrattivita = JSON.parse('${valoriAttrattivita}');
		window.infopoint = JSON.parse('${infopoint}').map(function(val) {return { infopointId : val.infopointId, infopoint : val.title }});
		window.italia = JSON.parse('${italia}');
		window.puglia = JSON.parse('${puglia}');
		window.nazioniList = JSON.parse('${nazioniList}');
		window.regioniList = JSON.parse('${regioniList}');
		window.areeList = JSON.parse('${areeList}');
		window.areeList.sort((a, b) => (a.areaTerritoriale > b.areaTerritoriale) ? 1 : -1);
		window.provinceList = JSON.parse('${provinceList}');
		window.comuniList = JSON.parse('${comuniList}');
		window.provincePerRegione = JSON.parse('${provincePerRegione}');
		window.provincePerArea = JSON.parse('${provincePerArea}');
		window.comuniPerRegione = JSON.parse('${comuniPerRegione}');
		window.comuniPerArea = JSON.parse('${comuniPerArea}');
		window.comuniPerProvincia = JSON.parse('${comuniPerProvincia}');
		window.redazioniList = JSON.parse('${redazioni}');
		window.redazioniListUtenteCorrente = JSON.parse('${redazioniUtente}');
		window.segnalazioneScelta = null;
		window.pollInterval = null;
		window.pollInterval2 = null;
		window.tabsave = null;
		window.tabsave2 = null;
		window.tipoRedazioneAttiva = '${tipoRedazioneAttiva}';
		window.areaRiservata = '${areaRiservata}';
		window.enterInRed = true;
		
	    <c:if test = "${not empty bandiList}">
	    	window.bandiList = JSON.parse('${bandiList}');
	    </c:if>
     	<c:if test = "${empty bandiList}">
 		 	window.bandiList = '';
		</c:if>     
     	<c:if test = "${not empty progettiList}">
	    	window.progettiList = JSON.parse('${progettiList}');
  		</c:if>
     	<c:if test = "${empty progettiList}">
    		window.progettiList = '';
		</c:if>
		
		var linkdms = '${linkDms}';
		var idmenu = '${myVariable}';
		var entityType = '${entityType}';
		
		var dataserver = '${datadioggi}';
		var codicefiscale =false;	
		var cfPiva = '';
		var cfTitolare ='';
		var validator =false;
		
		<c:choose>
			<c:when
				test="${not empty cfPiva}">
				codicefiscale = true;
				cfPiva = '${cfPiva}';
			</c:when>
			</c:choose>		
			<c:choose>	
			<c:when
				test="${not empty cfTitolare}">
				codicefiscale = true;
				cfTitolare = '${cfTitolare}';
			</c:when>		
		</c:choose>
		
		$.fn.dataTable.ext.errMode = 'throw';
		$.ajaxSetup({
			complete: function(x) {
		        if (x.status == 403) {
		        	$('#overlay').hide();
		    		alertify.alert("Errore","<img src=\"" + window.contextPath + "/assets/images/ERRORE.svg\"><br/><br/><br/>".window.mexJS['js.sessionescaduta']).set('onok', function(closeEvent){window.location.href = window.areaRiservata;}).set('label', 'Chiudi'); 
		        }
		    }
		});
	</script>
	
<script type="text/javascript" src="${contextPath}/assets/js/sigeajs/main.js"></script>
<body class="internobg">
	<div>
		<c:choose>
			<c:when
				test="${(myVariable == 'home.jsp') || 
				(myVariable == 'redazioneHome.jsp') || 
				(myVariable == 'filtriGestionePromozione.jsp') || 
				(myVariable == 'filtriGestioneValidazione.jsp') || 
				(myVariable == 'filtriGestioneRedazione.jsp') || 
				(myVariable == 'ricevute.jsp')				
				}">
				<%@ include file="base/headerSubMenu.jsp"%>
			</c:when>
			<c:when
				test="${(myVariable == 'filtriPromozione.jsp') || 
				(myVariable == 'ricevutePro.jsp')				
				}">
				<%@ include file="base/headerSubMenuPro.jsp"%>
			</c:when>
			
			<c:otherwise>
				<%@ include file="base/header.jsp"%>
			</c:otherwise>
		</c:choose>
		<div class="container">

			<div id="divFiltro" class="col-md-12 col-sm-12 general-section" style="min-height: 500px">
			<c:if
					test="${(myVariable == 'segnalazione.jsp') 
					|| (myVariable == 'home.jsp') || (myVariable == 'redazioneHome.jsp') 
					|| (myVariable == 'filtriGestionePromozione.jsp') 
					|| (myVariable == 'filtriGestioneValidazione.jsp') 
					|| (myVariable == 'filtriGestioneRedazione.jsp') 
					|| (myVariable == 'filtriPromozione.jsp')
					|| (myVariable == 'ricevute.jsp')
					|| (myVariable == 'ricevutePro.jsp')
					
					}">
					<jsp:include page="${myVariable}" flush="false" />
				</c:if>
			</div>
			<div id="divCreazione" class="col-md-12 col-sm-12 general-section" /></div>
			<div id="divSchedaAccessoria" class="col-md-12 col-sm-12 general-section" /></div>
			<div id="preload" class="col-md-12 col-sm-12 general-section" style="display: none; text-align: center; padding: 150px 15px 150px 15px !important;">
				<img src="${contextPath}/assets/images/intro.svg" style="width: 250px">
			</div>
		</div>
	</div>
	<%@ include file="base/footer.jsp"%>
	<%@ include file="base/spinner.jsp"%>
	<%@ include file="base/toast.jsp"%>

	<script type="text/javascript" src="${contextPath}/assets/js/accordionlight.js"></script>

	<c:if
		test="${
		(myVariable != 'segnalazione.jsp') && 
		(myVariable != 'home.jsp') && 
		(myVariable != 'redazioneHome.jsp') && 
		(myVariable != 'filtriGestionePromozione.jsp') && 
		(myVariable != 'filtriGestioneValidazione.jsp') && 
		(myVariable != 'filtriGestioneRedazione.jsp') && 
		(myVariable != 'filtriPromozione.jsp') && 
		(myVariable != 'ricevute.jsp') && 
		(myVariable != 'ricevutePro.jsp')
		
		}">
		<script>
				var idEvento = '${myVariable}';
				if (idEvento == "-1"){
					creaEventoForm();
				}else{
					visualizzaEvento('${myVariable}','');
				}
			</script>
	</c:if>
	
		<sec:authorize access="hasRole('SIGEA_VALIDAZIONE')">
				<script type="text/javascript">
				var validator = true;
				</script>
		</sec:authorize>

	<script type="text/javascript">
			$(document).ready(function() {				
				
				if(idmenu == 'home.jsp' ){
					$('#submenuMobile').find('#home').addClass('active');
				}else if(idmenu == 'filtriGestionePromozione.jsp'){
					$('#submenuMobile').find('#promozione').addClass('active');
				}else if(idmenu == 'filtriGestioneValidazione.jsp' ){
					$('#submenuMobile').find('#validazione').addClass('active');
				}else if(idmenu == 'filtriGestioneRedazione.jsp'){
					$('#submenuMobile').find('#redazione').addClass('active');
				}else if(idmenu == 'ricevute.jsp'){
					$('#submenuMobile').find('#ricevute').addClass('active');
				}else if(idmenu == 'ricevutePro.jsp'){
					$('#submenuMobile').find('#ricevutePro').addClass('active');	
				}else if(idmenu == 'ricevutePro.jsp'){
					$('#submenuMobile').find('#ricevutePro').addClass('active');	
				}else if(idmenu == 'filtriPromozione.jsp'){
					$('#submenuMobile').find('#promozionePro').addClass('active');					
				}else if(idmenu == 'redazioneHome.jsp'){
					$('#submenuMobile').find('#redazione').addClass('active');
				}
				
				if(idmenu == "filtriGestioneRedazione.jsp"){
					$("#labelbreadcrumb").empty().html('Redazione eventi');
				}else if(idmenu == "filtriGestionePromozione.jsp"){
					$("#labelbreadcrumb").empty().html('Promozione eventi');
				}else if(idmenu == "filtriGestioneValidazione.jsp"){
					$("#labelbreadcrumb").empty().html('Validazione eventi');
				}else if(idmenu == "ricevute.jsp"){
					$("#labelbreadcrumb").empty().html('Ricevute eventi finanziati');	
				}else if(idmenu == "filtriPromozione.jsp"){
					//$("#labelbreadcrumb").empty().html('Promozione eventi');
					$("#gestioneEvento").attr('hidden', true);
					$("#gestioneEventoPro").show();					
				}else if(idmenu == "ricevutePro.jsp"){
					//$("#labelbreadcrumb").empty().html('Promozione eventi');
					$("#gestioneEvento").attr('hidden', true);
					$("#gestioneEventoPro").show();					
				}
			});		
		</script>
</body>
</html>