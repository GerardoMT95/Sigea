<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<div class="row" style="margin-bottom: 15px">
	<div class="col-md-12">
		<spring:message code="label.media.bloccoimmagini" />
		<br />
		<br />
		<div id="mybutton" class="btn btn-primary invia">
			<input id="file-00" type="file" name="immagineSetLoad" data-feed="fileImmaginiFeed" onchange="fileClickImg('filebut00');" />
			<button id="filebut00" type="button" class="btn btn-primary" data-bindingonload data-addbutton="immagineSet" data-counter="-1" data-ph="_PH_"
				data-feeder="immagineSetLoad" style="display: none;">
				<spring:message code="label.media.carica" />
			</button>
			<i class="dms-wk-icon-upload"></i> Carica immagine
		</div>
	</div>
</div>
<div class="row" style="margin: 0 0px;">
	<div class="col-md-12 Input">
		<div class="mobile-input" id="boxmobileLinguaImg" style="display: none; padding-bottom: 15px;">
			<select id="mobiletabLinguaImg" class="form-control Input-text">
				<option value="IT" data-target="[data-tab='itmedia']">IT</option>
				<option value="EN" data-target="[data-tab='enmedia']">EN</option>
				<option value="SP" data-target="[data-tab='spmedia']">SP</option>
				<option value="FR" data-target="[data-tab='frmedia']">FR</option>
				<option value="DE" data-target="[data-tab='demedia']">DE</option>
				<option value="RU" data-target="[data-tab='rumedia']">RU</option>
			</select>
			<div class="scegliere">Cambia lingua</div>
		</div>
		<ul class="nav nav-tabs" id="myTab7" role="tablist" style="margin-bottom: 15px">
			<li class="nav-item active" style="cursor: pointer" data-tablink="itm" id="itacredits"><a data-target="[data-tab='itmedia']" data-toggle="tab"
				class="nav-link active" style="cursor: pointer">IT</a></li>
			<li class="nav-item" style="cursor: pointer" data-tablink="enm"><a data-target="[data-tab='enmedia']" data-toggle="tab"
				class="nav-link active" style="cursor: pointer">EN</a></li>
			<li class="nav-item" style="cursor: pointer" data-tablink="spm"><a data-target="[data-tab='spmedia']" data-toggle="tab"
				class="nav-link active" style="cursor: pointer">SP</a></li>
			<li class="nav-item" style="cursor: pointer" data-tablink="frm"><a data-target="[data-tab='frmedia']" data-toggle="tab"
				class="nav-link active" style="cursor: pointer">FR</a></li>
			<li class="nav-item" style="cursor: pointer" data-tablink="dem"><a data-target="[data-tab='demedia']" data-toggle="tab"
				class="nav-link active" style="cursor: pointer">DE</a></li>
			<li class="nav-item" style="cursor: pointer" data-tablink="rum"><a data-target="[data-tab='rumedia']" data-toggle="tab"
				class="nav-link active" style="cursor: pointer">RU</a></li>
		</ul>
	</div>
</div>
<div data-dynamic="immagineSet" class="contaimmagini">
	<div class="row" hidden="true" data-template="immagineSet_PH_"
		style="border-bottom: 1px solid #c2c2c2; padding-bottom: 15px; padding-top: 15px; padding-left: 15px;">
		<input type="hidden" name="immagineSet_PH_.immagineId" readonly> <input type="hidden" name="immagineSet_PH_.estensione" readonly> <input
			type="hidden" name="immagineSet_PH_.dimensione" readonly> <input type="hidden" name="immagineSet_PH_.daPubblicare" value="true">
		<div class="tab-content">
			<div class="col-md-6 Input blockaccordiumx">
				<div class="boxInput" id="nomefilebox" style="margin-bottom: 0px;">
					<img src="${contextPath}/assets/svg/doc_nero.svg" style="margin-right: 20px;"> <span class="titoloinput"> <input type="hidden"
						class="inputtotext totext" name="immagineSet_PH_.nomeOriginale" readonly />
					</span>
				</div>
			</div>
			<div class="col-md-6 Input blockaccordiumx" style="text-align: right; background: #F2F2F2;" id="rimuovi">
				<div class="boxInput" style="margin: 0 15px 20px 15px; font-weight: 600;">
					<a style="letter-spacing: normal" href="${contextPath}" data-name="immagineSet_PH_Visualizza" data-dynabindingonload="initVisualizza_PH_"
						data-event="click" style="margin-right: 20px; font-size:16px; font-weight:600;">
						
							<i class="dms-wk-icon-show-details" aria-hidden="true"></i>
				<%-- 		<img src="${contextPath}/assets/images/VIEW.svg"
						style="margin-right: 10px;"> --%>
						
						
					<spring:message code="label.media.visualizza" /></a> <a style="letter-spacing: normal" href="${contextPath}" data-name="immagineSet_PH_Download"
						data-dynabindingonload="initDownload_PH_" data-event="click" style="margin-right: 20px; font-size:16px; font-weight:600;">
						<i class="dms-wk-icon-download" aria-hidden="true"></i>
					<%-- 	<img
						src="${contextPath}/assets/images/download.svg" style="margin-right: 10px;"> --%>
					<spring:message code="label.media.download" /></a>
					<button type="button" class="removeButton" data-deletebutton="immagineSet_PH_">
						<i class="dms-wk-icon-delete" aria-hidden="true"></i>
						<spring:message code="label.media.elimina" />
					</button>
				</div>
			</div>
			<div class="tab-pane fade active in blockaccordium" data-tab="itmedia">
				<div class="col-lg-6 col-md-6 Input" style="margin-top: 15px">
					<spring:message code="placeholder.media.didascalia" var="phDidascalia" />
					<div class="boxInput">
						<textarea class="form-control Input-text resize" style="min-height: 75px; max-height: 300px" name="immagineSet_PH_.didascaliaMulti.IT"
							maxlength="300" placeholder="Aggiungi un testo con massimo 300 caratteri"></textarea>
						<div class="scegliere" style="bottom: 110% !important;">
							<spring:message code="label.media.didascalia" />
						</div>
					</div>
				</div>
			</div>

			<div class="tab-pane fade blockaccordium" data-tab="enmedia">
				<div class="col-lg-6 col-md-6 Input" style="margin-top: 15px">
					<spring:message code="placeholder.media.didascalia" var="phDidascalia" />
					<div class="boxInput">
						<textarea class="form-control Input-text resize" style="min-height: 75px; max-height: 300px" name="immagineSet_PH_.didascaliaMulti.EN"
							maxlength="300" placeholder="Aggiungi un testo con massimo 300 caratteri"></textarea>
						<div class="scegliere" style="bottom: 110% !important;">
							<spring:message code="label.media.didascalia" />
						</div>
					</div>
				</div>
			</div>

			<div class="tab-pane fade blockaccordium" data-tab="spmedia">
				<div class="col-lg-6 col-md-6 Input" style="margin-top: 15px">
					<spring:message code="placeholder.media.didascalia" var="phDidascalia" />
					<div class="boxInput">
						<textarea class="form-control Input-text resize" style="min-height: 75px; max-height: 300px" name="immagineSet_PH_.didascaliaMulti.SP"
							maxlength="300" placeholder="Aggiungi un testo con massimo 300 caratteri"></textarea>
						<div class="scegliere" style="bottom: 110% !important;">
							<spring:message code="label.media.didascalia" />
						</div>
					</div>
				</div>
			</div>
			<div class="tab-pane fade blockaccordium" data-tab="frmedia">
				<div class="col-lg-6 col-md-6 Input" style="margin-top: 15px">
					<spring:message code="placeholder.media.didascalia" var="phDidascalia" />
					<div class="boxInput">
						<textarea class="form-control Input-text resize" style="min-height: 75px; max-height: 300px" name="immagineSet_PH_.didascaliaMulti.FR"
							maxlength="300" placeholder="Aggiungi un testo con massimo 300 caratteri"></textarea>
						<div class="scegliere" style="bottom: 110% !important;">
							<spring:message code="label.media.didascalia" />
						</div>
					</div>
				</div>
			</div>
			<div class="tab-pane fade blockaccordium" data-tab="demedia">
				<div class="col-lg-6 col-md-6 Input" style="margin-top: 15px">
					<spring:message code="placeholder.media.didascalia" var="phDidascalia" />
					<div class="boxInput">
						<textarea class="form-control Input-text resize" style="min-height: 75px; max-height: 300px" name="immagineSet_PH_.didascaliaMulti.DE"
							maxlength="300" placeholder="Aggiungi un testo con massimo 300 caratteri"></textarea>
						<div class="scegliere" style="bottom: 110% !important;">
							<spring:message code="label.media.didascalia" />
						</div>
					</div>
				</div>
			</div>
			<div class="tab-pane fade blockaccordium" data-tab="rumedia">
				<div class="col-lg-6 col-md-6 Input" style="margin-top: 15px">
					<spring:message code="placeholder.media.didascalia" var="phDidascalia" />
					<div class="boxInput">
						<textarea class="form-control Input-text resize" style="min-height: 75px; max-height: 300px" name="immagineSet_PH_.didascaliaMulti.RU"
							maxlength="300" placeholder="Aggiungi un testo con massimo 300 caratteri"></textarea>
						<div class="scegliere">
							<spring:message code="label.media.didascalia" />
						</div>
					</div>
				</div>
			</div>
			<div class="col-md-6 Input blockaccordiumx" style="margin-top: 15px">
				<spring:message code="placeholder.media.credits" var="phCredits" />
				<div class="boxInput">
					<textarea class="form-control Input-text resize" style="min-height: 75px; max-height: 300px" name="immagineSet_PH_.credits" maxlength="300"
						placeholder="Aggiungi un testo con massimo 300 caratteri"></textarea>
					<div class="scegliere" style="bottom: 110% !important;">
						<spring:message code="label.media.credits" />
					</div>
				</div>
			</div>
			<div class="col-lg-6 col-md-6 Input blockaccordiumx" style="margin-top: 15px">
				<spring:message code="placeholder.documenti.ordine" var="phOrdine" />
				<div class="boxInput">
					<input type="number" class="form-control Input-text itready" name="immagineSet_PH_.ordine" min="1" maxlength="10" placeholder="${phOrdine}" />
						<div class="scegliere">
							<spring:message code="label.documento.ordine.required" />
						</div>
					</div>
				</div>
		    </div>
	</div>
</div>

<script>
$(document).ready(function(){
    $('.accordionA37-header').on('click', function(event){
    	var link = $(this).find(".frecciafiltro");    	
    	var classblocco = $(this).attr("class");    	
    	var faq = $(this);
		if (classblocco == 'accordionA37-header collapsed') {
            link.attr("src","${contextPath}/assets/images/FRECCIA_SU.svg");
        } else {
        	link.attr("src","${contextPath}/assets/images/FRECCIA_GIU.svg");
        }		
    });  
});
</script>