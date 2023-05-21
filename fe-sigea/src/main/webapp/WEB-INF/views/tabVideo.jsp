<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<div class="row" style="margin-bottom: 15px">
	<div class="col-md-12">
		<spring:message code="label.media.bloccovideo" />
		<br />
		<br />
		<button class="btn btn-primary invia" type="button" data-bindingonload data-addbutton="linkSet" data-counter="-1" data-ph="_PH_">
			<i class="dms-wk-icon-add"></i>
			<spring:message code="label.media.aggiungivideo" />
		</button>
	</div>
</div>
<div class="row" style="margin: 0 0px;">
	<div class="col-md-12 Input">
		<div class="mobile-input" id="boxmobileLinguaVid" style="display: none; padding-bottom: 15px;">
			<select id="mobiletabLinguaVid" class="form-control Input-text">
				<option data-target="[data-tab='itvideo']">IT</option>
				<option data-target="[data-tab='envideo']">EN</option>
				<option data-target="[data-tab='spvideo']">SP</option>
				<option data-target="[data-tab='frvideo']">FR</option>
				<option data-target="[data-tab='devideo']">DE</option>
				<option data-target="[data-tab='ruvideo']">RU</option>
			</select>
			<div class="scegliere">Cambia lingua</div>
		</div>
		<ul class="nav nav-tabs" id="vidTab" role="tablist" style="margin-bottom: 15px">
			<li class="nav-item active" style="cursor: pointer" data-tablink="itv"><a data-target="[data-tab='itvideo']" data-toggle="tab"
				class="nav-link active" style="cursor: pointer">IT</a></li>
			<li class="nav-item" style="cursor: pointer" data-tablink="env"><a data-target="[data-tab='envideo']" data-toggle="tab"
				class="nav-link active" style="cursor: pointer">EN</a></li>
			<li class="nav-item" style="cursor: pointer" data-tablink="spv"><a data-target="[data-tab='spvideo']" data-toggle="tab"
				class="nav-link active" style="cursor: pointer">SP</a></li>
			<li class="nav-item" style="cursor: pointer" data-tablink="frv"><a data-target="[data-tab='frvideo']" data-toggle="tab"
				class="nav-link active" style="cursor: pointer">FR</a></li>
			<li class="nav-item" style="cursor: pointer" data-tablink="dev"><a data-target="[data-tab='devideo']" data-toggle="tab"
				class="nav-link active" style="cursor: pointer">DE</a></li>
			<li class="nav-item" style="cursor: pointer" data-tablink="ruv"><a data-target="[data-tab='ruvideo']" data-toggle="tab"
				class="nav-link active" style="cursor: pointer">RU</a></li>
		</ul>
	</div>
</div>
<div data-dynamic="linkSet" style="padding: 0px 9px;" class="contalink">
	<div class="row" hidden="true" data-template="linkSet_PH_"
		style="border-bottom: 1px solid #c2c2c2; padding-bottom: 15px; padding-top: 15px; padding-left: 15px;">
		<input type="hidden" name="linkSet_PH_.daPubblicare" value="true">
		
		<div class="tab-content" id="myVContent">		
			<div class="tab-pane fade active in blockaccordiumx" data-tab="itvideo">
				<div class="col-md-6 Input" style="margin-top: 15px">
					<spring:message code="placeholder.titolo" var="phDidascalia" />
					<div class="boxInput">
						<input type="text" class="form-control Input-text" name="linkSet_PH_.titoloMulti.IT" maxlength="300"
							placeholder="Aggiungi un testo con massimo 300 caratteri">
						<div class="scegliere">
							<spring:message code="label.titoloevento.required" />
						</div>
					</div>
					<spring:message code="placeholder.media.didascalia" var="phDidascalia" />
					<div class="boxInput">
						<textarea class="form-control Input-text resize" style="min-height: 75px; max-height: 300px" name="linkSet_PH_.didascaliaMulti.IT"
							maxlength="300" placeholder="Aggiungi un testo con massimo 300 caratteri"></textarea>
						<div class="scegliere" style="bottom: 110% !important;">
							<spring:message code="label.media.didascalia" />
						</div>
					</div>
				</div>
			</div>
			<div class="tab-pane fade blockaccordium" data-tab="envideo">
				<div class="col-md-6 Input" style="margin-top: 15px">
					<spring:message code="placeholder.titolo" var="phDidascalia" />
					<div class="boxInput">
						<input type="text" class="form-control Input-text" name="linkSet_PH_.titoloMulti.EN" maxlength="300"
							placeholder="Aggiungi un testo con massimo 300 caratteri">
						<div class="scegliere">
							<spring:message code="label.titoloevento" />
						</div>
					</div>
					<spring:message code="placeholder.media.didascalia" var="phDidascalia" />
					<div class="boxInput">
						<textarea class="form-control Input-text resize" style="min-height: 75px; max-height: 300px" name="linkSet_PH_.didascaliaMulti.EN"
							maxlength="300" placeholder="Aggiungi un testo con massimo 300 caratteri"></textarea>
						<div class="scegliere" style="bottom: 110% !important;">
							<spring:message code="label.media.didascalia" />
						</div>
					</div>
				</div>
			</div>
			<div class="tab-pane fade blockaccordium" data-tab="spvideo">
				<div class="col-md-6 Input" style="margin-top: 15px">
					<spring:message code="placeholder.titolo" var="phDidascalia" />
					<div class="boxInput">
						<input type="text" class="form-control Input-text" name="linkSet_PH_.titoloMulti.SP" maxlength="300"
							placeholder="Aggiungi un testo con massimo 300 caratteri">
						<div class="scegliere">
							<spring:message code="label.titoloevento" />
						</div>
					</div>
					<spring:message code="placeholder.media.didascalia" var="phDidascalia" />
					<div class="boxInput">
						<textarea class="form-control Input-text resize" style="min-height: 75px; max-height: 300px" name="linkSet_PH_.didascaliaMulti.SP"
							maxlength="300" placeholder="Aggiungi un testo con massimo 300 caratteri"></textarea>
						<div class="scegliere" style="bottom: 110% !important;">
							<spring:message code="label.media.didascalia" />
						</div>
					</div>
				</div>
			</div>
			<div class="tab-pane fade blockaccordium" data-tab="frvideo">
				<div class="col-md-6 Input" style="margin-top: 15px">
					<spring:message code="placeholder.titolo" var="phDidascalia" />
					<div class="boxInput">
						<input type="text" class="form-control Input-text" name="linkSet_PH_.titoloMulti.FR" maxlength="300"
							placeholder="Aggiungi un testo con massimo 300 caratteri">
						<div class="scegliere">
							<spring:message code="label.titoloevento" />
						</div>
					</div>
					<spring:message code="placeholder.media.didascalia" var="phDidascalia" />
					<div class="boxInput">
						<textarea class="form-control Input-text resize" style="min-height: 75px; max-height: 300px" name="linkSet_PH_.didascaliaMulti.FR"
							maxlength="300" placeholder="Aggiungi un testo con massimo 300 caratteri"></textarea>
						<div class="scegliere" style="bottom: 110% !important;">
							<spring:message code="label.media.didascalia" />
						</div>
					</div>
				</div>
			</div>
			<div class="tab-pane fade blockaccordium" data-tab="devideo">
				<div class="col-md-6 Input" style="margin-top: 15px">
					<spring:message code="placeholder.titolo" var="phDidascalia" />
					<div class="boxInput">
						<input type="text" class="form-control Input-text" name="linkSet_PH_.titoloMulti.DE" maxlength="300"
							placeholder="Aggiungi un testo con massimo 300 caratteri">
						<div class="scegliere">
							<spring:message code="label.titoloevento" />
						</div>
					</div>
					<spring:message code="placeholder.media.didascalia" var="phDidascalia" />
					<div class="boxInput">
						<textarea class="form-control Input-text resize" style="min-height: 75px; max-height: 300px" name="linkSet_PH_.didascaliaMulti.DE"
							maxlength="300" placeholder="Aggiungi un testo con massimo 300 caratteri"></textarea>
						<div class="scegliere" style="bottom: 110% !important;">
							<spring:message code="label.media.didascalia" />
						</div>
					</div>
				</div>
			</div>
			<div class="tab-pane fade blockaccordium" data-tab="ruvideo">
				<div class="col-md-6 Input" style="margin-top: 15px">
					<spring:message code="placeholder.titolo" var="phDidascalia" />
					<div class="boxInput">
						<input type="text" class="form-control Input-text" name="linkSet_PH_.titoloMulti.RU" maxlength="300"
							placeholder="Aggiungi un testo con massimo 300 caratteri">
						<div class="scegliere">
							<spring:message code="label.titoloevento" />
						</div>
					</div>
					<spring:message code="placeholder.media.didascalia" var="phDidascalia" />
					<div class="boxInput">
						<textarea class="form-control Input-text resize" style="min-height: 75px; max-height: 300px" name="linkSet_PH_.didascaliaMulti.RU"
							maxlength="300" placeholder="Aggiungi un testo con massimo 300 caratteri"></textarea>
						<div class="scegliere" style="bottom: 110% !important;">
							<spring:message code="label.media.didascalia" />
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="col-md-6 Input" style="margin-top: 15px">
			<spring:message code="placeholder.video.link" var="phLink" />
			<div class="boxInput">
				<input hidden="true" type="text" name="linkSet_PH_.linkId"> <input type="text" class="form-control Input-text" name="linkSet_PH_.link"
					maxlength="300" placeholder="${phLink}">
				<div class="scegliere">
					<spring:message code="label.video.link" />
				</div>
			</div>
			<spring:message code="placeholder.media.credits" var="phCredits" />
			<div class="boxInput">
				<textarea class="form-control Input-text resize" style="min-height: 75px; max-height: 300px" name="linkSet_PH_.credits" maxlength="300"
					placeholder="Aggiungi un testo con massimo 300 caratteri"></textarea>
				<div class="scegliere">
					<spring:message code="label.media.credits" />
				</div>
			</div>
			</div>
			<div class="col-lg-6 col-md-6 Input blockaccordiumx" style="margin-top: 15px">
			
				<spring:message code="placeholder.documenti.ordine" var="phOrdine" />
				<div class="boxInput">
					<input type="number" class="form-control Input-text itready order" name="linkSet_PH_.ordine" min="1" maxlength="10" placeholder="${phOrdine}" />
						<div class="scegliere">
							<spring:message code="label.documento.ordine.required" />
						</div>
					</div>
		</div>
		
	
		
		<div class="col-md-12 Input blockaccordiumx" style="text-align: right; height: 60px; background: #F2F2F2;">
			<div class="boxInput" style="margin: 0 15px 0px 15px;">
				<button type="button" data-deletebutton="linkSet_PH_" class="removeButton">
					<i class="dms-wk-icon-delete" aria-hidden="true"></i>
					<spring:message code="label.media.elimina" />
				</button>
			</div>
		</div>
	</div>
</div>
