<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<div class="tab-content bloccoaccessibile" id="myTabContent3" style="padding-top: 0px">
	<input type="hidden" name="accessibilita.eventoId" readonly>
	<div class="row" style="margin-top: 10px; margin-right: 0px; margin-left: 0px">
		<div class="col-md-12 Input" style="margin-bottom: 10px; height: auto">
			<div class="accordionA6">
				<div class="accordionA6-header" style="text-align: left; color: #003A54;" data-toggle="collapse" data-target="#collapseA6" aria-expanded="false"
					aria-controls="collapseA6">
					<img src="${contextPath}/assets/images/blind.svg" style="margin-right: 20px;">
					<spring:message code="label.accessibilita.esigenze" />
					<div style="position: absolute; float: right; padding-top: 16px; right: 20px; top: 20px;">
						<img src="${contextPath}/assets/images/FRECCIA_GIU.svg" class="frecciafiltro" style="width: 18px;">
					</div>
				</div>
				<div class="accordionA6-content" id="collapseA6" aria-labelledby="headingA6" data-parent="#accordionExample" style="background: #F2F2F2;">
					<label class="checkbox" style="border-bottom: 1px solid #ddd; padding-bottom: 15px;"> <input type="checkbox" style="position: inherit"
						name="seldes6" value="x" id="seldes6"> <spring:message code="label.ticket.seldes" />
					</label> <label class="checkbox"> <input type="checkbox" style="position: inherit" name="accessibilita.flagAnziani" value="true"> <spring:message
							code="Accessibilita.flagAnziani" />
					</label> <label class="checkbox"> <input type="checkbox" style="position: inherit" name="accessibilita.flagDisabilitaFisica" value="true">
						<spring:message code="Accessibilita.flagDisabilitaFisica" />
					</label> <label class="checkbox"> <input type="checkbox" style="position: inherit" name="accessibilita.flagDisabilitaUditiva" value="true">
						<spring:message code="Accessibilita.flagDisabilitaUditiva" />
					</label> <label class="checkbox"> <input type="checkbox" style="position: inherit" name="accessibilita.flagDisabilitaVisiva" value="true">
						<spring:message code="Accessibilita.flagDisabilitaVisiva" />
					</label> <label class="checkbox"> <input type="checkbox" style="position: inherit" name="accessibilita.flagGravidanza" value="true"> <spring:message
							code="Accessibilita.flagGravidanza" />
					</label> <label class="checkbox"> <input type="checkbox" style="position: inherit" name="accessibilita.flagFamiglieBambini" value="true">
						<spring:message code="Accessibilita.flagFamiglieBambini" />
					</label> <!--  <label class="checkbox"> <input type="checkbox" style="position: inherit" name="accessibilita.flagPercorsi" value="true"> <spring:message
							code="Accessibilita.flagPercorsi" /> 
					</label> -->
					<input type="hidden" style="position: inherit" name="accessibilita.flagPercorsi" value="false"> <label class="checkbox"> <input type="checkbox" style="position: inherit" name="accessibilita.flagPersoneAnimali" value="true">
						<spring:message code="Accessibilita.flagPersoneAnimali" />
					</label>
				</div>
			</div>
		</div>
		<div class="col-md-12 Input" style="margin-bottom: 10px; height: auto">
			<div class="accordionA1">
				<div class="accordionA1-header" style="text-align: left; color: #003A54;" data-toggle="collapse" data-target="#collapseA1" aria-expanded="false"
					aria-controls="collapseOne">
					<img src="${contextPath}/assets/images/Group.svg" style="margin-right: 20px;">
					<spring:message code="label.accessibilita.servizi" />
					<div style="position: absolute; float: right; padding-top: 16px; right: 20px; top: 20px;">
						<img src="${contextPath}/assets/images/FRECCIA_GIU.svg" class="frecciafiltro" style="width: 18px;">
					</div>
				</div>
				<div class="accordionA1-content" id="collapseA1" aria-labelledby="headingA1" data-parent="#accordionExample" style="background: #F2F2F2;">
					<label class="checkbox" style="border-bottom: 1px solid #ddd; padding-bottom: 15px;"> <input type="checkbox" style="position: inherit"
						name="seldes1" value="x" id="seldes1"> <spring:message code="label.ticket.seldes" />
					</label>
					<div class="row">
						<div class="col-md-6">
							<label class="checkbox"> <input type="checkbox" style="position: inherit" name="accessibilita.flagInfopoint" value="true"> <spring:message
									code="Accessibilita.flagInfopoint" />
							</label> <label class="checkbox"> <input type="checkbox" style="position: inherit" name="accessibilita.flagServiziIgienici" value="true">
								<spring:message code="Accessibilita.flagServiziIgienici" />
							</label> <label class="checkbox"> <input type="checkbox" style="position: inherit" name="accessibilita.flagParcheggioDisabili" value="true">
								<spring:message code="Accessibilita.flagParcheggioDisabili" />
							</label> <label class="checkbox"> <input type="checkbox" style="position: inherit" name="accessibilita.flagIngressi" value="true"> <spring:message
									code="Accessibilita.flagIngressi" />
							</label> <label class="checkbox"> <input type="checkbox" style="position: inherit" name="accessibilita.flagPercorsiTattili" value="true">
								<spring:message code="Accessibilita.flagPercorsiTattili" />
							</label> <label class="checkbox"> <input type="checkbox" style="position: inherit" name="accessibilita.flagGuideBraille" value="true">
								<spring:message code="Accessibilita.flagGuideBraille" />
							</label> <label class="checkbox"> <input type="checkbox" style="position: inherit" name="accessibilita.flagSegnaleticaBraille" value="true">
								<spring:message code="Accessibilita.flagSegnaleticaBraille" />
							</label> <label class="checkbox"> <input type="checkbox" style="position: inherit" name="accessibilita.flagSegnaleticaLeggibile" value="true">
								<spring:message code="Accessibilita.flagSegnaleticaLeggibile" />
							</label>
						</div>
						<div class="col-md-6">
							<label class="checkbox"> <input type="checkbox" style="position: inherit" name="accessibilita.flagMaterialeLeggibile" value="true">
								<spring:message code="Accessibilita.flagMaterialeLeggibile" />
							</label> <label class="checkbox"> <input type="checkbox" style="position: inherit" name="accessibilita.flagPostazioniAudio" value="true">
								<spring:message code="Accessibilita.flagPostazioniAudio" />
							</label> <label class="checkbox"> <input type="checkbox" style="position: inherit" name="accessibilita.flagAudioguide" value="true"> <spring:message
									code="Accessibilita.flagAudioguide" />
							</label> <label class="checkbox"> <input type="checkbox" style="position: inherit" name="accessibilita.flagAudioguidePercorsi" value="true">
								<spring:message code="Accessibilita.flagAudioguidePercorsi" />
							</label> <label class="checkbox"> <input type="checkbox" style="position: inherit" name="accessibilita.flagMaterialeSottotitolato" value="true">
								<spring:message code="Accessibilita.flagMaterialeSottotitolato" />
							</label> <label class="checkbox"> <input type="checkbox" style="position: inherit" name="accessibilita.flagRiproduzioneTattili" value="true">
								<spring:message code="Accessibilita.flagRiproduzioneTattili" />
							</label> <label class="checkbox"> <input type="checkbox" style="position: inherit" name="accessibilita.flagComputer" value="true"> <spring:message
									code="Accessibilita.flagComputer" />
							</label> <label class="checkbox"> <input type="checkbox" style="position: inherit" name="accessibilita.flagLis" value="true"> <spring:message
									code="Accessibilita.flagLis" />
							</label>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="col-md-12 Input" style="margin-bottom: 10px; height: auto">
			<div class="accordionA4">
				<div class="accordionA4-header" style="text-align: left; color: #003A54;" data-toggle="collapse" data-target="#collapseA4" aria-expanded="false"
					aria-controls="collapse4A">
					<img src="${contextPath}/assets/images/family.svg" style="margin-right: 20px;">
					<spring:message code="label.accessibilita.famiglieBambini" />
					<div style="position: absolute; float: right; padding-top: 16px; right: 20px; top: 20px;">
						<img src="${contextPath}/assets/images/FRECCIA_GIU.svg" class="frecciafiltro" style="width: 18px;">
					</div>
				</div>
				<div class="accordionA4-content" id="collapseA4" aria-labelledby="headingA4" data-parent="#accordionExample" style="background: #F2F2F2;">
					<label class="checkbox" style="border-bottom: 1px solid #ddd; padding-bottom: 15px;"> <input type="checkbox" style="position: inherit"
						name="seldes3" value="x" id="seldes3"> <spring:message code="label.ticket.seldes" />
					</label> <label class="checkbox"> <input type="checkbox" style="position: inherit" name="accessibilita.flagBiblioteca" value="true"> <spring:message
							code="Accessibilita.flagBiblioteca" />
					</label> <label class="checkbox"> <input type="checkbox" style="position: inherit" name="accessibilita.flagLudoteca" value="true"> <spring:message
							code="Accessibilita.flagLudoteca" />
					</label> <label class="checkbox"> <input type="checkbox" style="position: inherit" name="accessibilita.flagNursey" value="true"> <spring:message
							code="Accessibilita.flagNursey" />
					</label> <label class="checkbox"> <input type="checkbox" style="position: inherit" name="accessibilita.flagParcogiochi" value="true"> <spring:message
							code="Accessibilita.flagParcogiochi" />
					</label> <label class="checkbox"> <input type="checkbox" style="position: inherit" name="accessibilita.flagGiardini" value="true"> <spring:message
							code="Accessibilita.flagGiardini" />
					</label>
				</div>
			</div>
		</div>
		<div class="col-md-12 Input" style="margin-bottom: 10px; height: auto">
			<div class="accordionA2">
				<div class="accordionA2-header" style="text-align: left; color: #003A54;" data-toggle="collapse" data-target="#collapseA2" aria-expanded="false"
					aria-controls="collapse2A">
					<img src="${contextPath}/assets/images/paw.svg" style="margin-right: 20px;">
					<spring:message code="label.accessibilita.animali" />
					<div style="position: absolute; float: right; padding-top: 16px; right: 20px; top: 20px;">
						<img src="${contextPath}/assets/images/FRECCIA_GIU.svg" class="frecciafiltro" style="width: 18px;">
					</div>
				</div>
				<div class="accordionA2-content" id="collapseA2" aria-labelledby="headingA2" data-parent="#accordionExample" style="background: #F2F2F2;">
					<label class="checkbox" style="border-bottom: 1px solid #ddd; padding-bottom: 15px;"> <input type="checkbox" style="position: inherit"
						name="seldes2" value="x" id="seldes2"> <spring:message code="label.ticket.seldes" />
					</label> <label class="checkbox"> <input type="checkbox" style="position: inherit" name="accessibilita.flagCaniPiccoli" value="true"> <spring:message
							code="Accessibilita.flagCaniPiccoli" />
					</label> <label class="checkbox"> <input type="checkbox" style="position: inherit" name="accessibilita.flagCaniMedi" value="true"> <spring:message
							code="Accessibilita.flagCaniMedi" />
					</label> <label class="checkbox"> <input type="checkbox" style="position: inherit" name="accessibilita.flagCani" value="true"> <spring:message
							code="Accessibilita.flagCani" />
					</label>
				</div>
			</div>
		</div>
	</div>
</div>

<script>

$(document).ready(function(){
    $('.accordionA1-header, .accordionA2-header, .accordionA3-header, .accordionA4-header, .accordionA5-header, .accordionA6-header').on('click', function(event){
    	var link = $(this).find(".frecciafiltro");    	
    	var classblocco = $(this).attr("aria-expanded");    	
    	var faq = $(this);   	
    	
        if (classblocco == 'true') {
            link.attr("src", "${contextPath}/assets/images/FRECCIA_GIU.svg");

        } else {
        	  link.attr("src", "${contextPath}/assets/images/FRECCIA_SU.svg");
        }
    });
    
    $('#seldes1, #seldes2, #seldes3, #seldes4, #seldes5, #seldes6').click(function(){
    	
    	if ($(this).is(":checked")){
    		$(this).parent().parent().find("input").prop('checked', true);
    	}else{
    		$(this).parent().parent().find("input").prop('checked', false);
    	}   	
    	
    });     
});

</script>