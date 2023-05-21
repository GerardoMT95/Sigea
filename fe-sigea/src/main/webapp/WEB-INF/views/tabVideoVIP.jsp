<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<div class="row" style="margin-bottom: 15px">
	<div class="col-md-12">
		<spring:message code="label.schedavip.mediaVid.accordion" />
	</div>
</div>
<div class="row">
	<div class="col-md-12 Input" id="boxmobileLinguaVidVip" style="display: none; padding-bottom: 15px; margin-left: 15px; margin-right: 15px;">
		<div class="mobile-input">
			<select id="mobiletabLinguaVidVip" class="form-control Input-text">
				<option value="IT" data-target="[data-tab='itVIPvideo']">IT</option>
				<option value="EN" data-target="[data-tab='enVIPvideo']">EN</option>
				<option value="SP" data-target="[data-tab='spVIPvideo']">SP</option>
				<option value="FR" data-target="[data-tab='frVIPvideo']">FR</option>
				<option value="DE" data-target="[data-tab='deVIPvideo']">DE</option>
				<option value="RU" data-target="[data-tab='ruVIPvideo']">RU</option>
			</select>
			<div class="scegliere">Cambia lingua</div>
		</div>
	</div>
	<ul class="nav nav-tabs" id="vidVIPTab" role="tablist" style="margin-right: 15px; margin-left: 15px; margin-bottom: 15px">
		<li class="nav-item active" style="cursor: pointer" data-tablink="itVIPv"><a data-target="[data-tab='itVIPvideo']" data-toggle="tab"
			class="nav-link active" style="cursor: pointer">IT</a></li>
		<li class="nav-item" style="cursor: pointer" data-tablink="enVIPv"><a data-target="[data-tab='enVIPvideo']" data-toggle="tab"
			class="nav-link active" style="cursor: pointer">EN</a></li>
		<li class="nav-item" style="cursor: pointer" data-tablink="spVIPv"><a data-target="[data-tab='spVIPvideo']" data-toggle="tab"
			class="nav-link active" style="cursor: pointer">SP</a></li>
		<li class="nav-item" style="cursor: pointer" data-tablink="frVIPv"><a data-target="[data-tab='frVIPvideo']" data-toggle="tab"
			class="nav-link active" style="cursor: pointer">FR</a></li>
		<li class="nav-item" style="cursor: pointer" data-tablink="deVIPv"><a data-target="[data-tab='deVIPvideo']" data-toggle="tab"
			class="nav-link active" style="cursor: pointer">DE</a></li>
		<li class="nav-item" style="cursor: pointer" data-tablink="ruVIPv"><a data-target="[data-tab='ruVIPvideo']" data-toggle="tab"
			class="nav-link active" style="cursor: pointer">RU</a></li>
	</ul>
	<div class="row" hidden="true" style="margin-left: 15px; padding-top: 15px; margin-bottom: 15px; margin-right: 15px;">
		<div class="col-md-12 Input" style="padding-top: 8px; text-align: right;">
			<button type="button" class="btn btn-primary" data-bindingonload data-addbutton="linkSet" data-counter="-1" data-ph="_PH_"></button>
		</div>
	</div>
	<div class="bloccotab">
		<div class="row">
			<div class="col-md-12" style="padding: 15px 40px 15px 40px">
				<span style="color: #003a54; font-size: 16px; font-weight: 600;">LINK ESTERNI DEL NUCLEO</span>
			</div>
		</div>
		<div data-dynamic="linkSet" style="padding: 0px 9px;" class="contalinkvip">
			<div class="row" hidden="true" data-template="linkSet_PH_"
				style="border-top: 1px solid #efefef; box-shadow: 0px 4px 10px rgba(0, 0, 0, 0.2); margin: 0 15px 30px 15px; background: #F2F2F2;">
				<div class="tab-content" id="myVidVIPContent">
					<div class="tab-pane fade active in blockaccordium" data-tab="itVIPvideo">
						<div class="col-lg-6 col-md-6 col-sm-12 Input" style="margin-top: 34px;">
							<spring:message code="placeholder.titolo" var="phDidascalia" />
							<div class="boxInput" style="margin: 0 15px 40px 15px;">
								<input type="text" class="form-control Input-text" name="linkSet_PH_.titoloMulti.IT" maxlength="300" placeholder="Aggiungi un testo con massimo 300 caratteri"
									readonly="readonly">
								<div class="scegliere">
									<spring:message code="label.titoloevento" />
								</div>
							</div>
							<spring:message code="placeholder.media.didascalia" var="phDidascalia" />
							<div class="boxInput" style="margin: 0 15px 20px 15px;">
								<textarea class="form-control Input-text resize" style="min-height: 75px; max-height: 300px" readonly="readonly"
									name="linkSet_PH_.didascaliaMulti.IT" maxlength="300" placeholder="${phDidascalia}"></textarea>
								<div class="scegliere" style="bottom: 110% !important;">
									<spring:message code="label.media.didascalia" />
								</div>
							</div>
						</div>
					</div>
					<div class="tab-pane fade blockaccordium" data-tab="enVIPvideo">
						<div class="col-lg-6 col-md-6 col-sm-12 Input" style="margin-top: 34px;">
							<spring:message code="placeholder.titolo" var="phDidascalia" />
							<div class="boxInput" style="margin: 0 15px 40px 15px;">
								<input type="text" class="form-control Input-text" name="linkSet_PH_.titoloMulti.EN" maxlength="300" placeholder="Aggiungi un testo con massimo 300 caratteri"
									readonly="readonly">
								<div class="scegliere">
									<spring:message code="label.titoloevento" />
								</div>
							</div>
							<spring:message code="placeholder.media.didascalia" var="phDidascalia" />
							<div class="boxInput" style="margin: 0 15px 20px 15px;">
								<textarea class="form-control Input-text resize" style="min-height: 75px; max-height: 300px" readonly="readonly"
									name="linkSet_PH_.didascaliaMulti.EN" maxlength="300" placeholder="${phDidascalia}"></textarea>
								<div class="scegliere" style="bottom: 110% !important;">
									<spring:message code="label.media.didascalia" />
								</div>
							</div>
						</div>
					</div>
					<div class="tab-pane fade blockaccordium" data-tab="spVIPvideo">
						<div class="col-lg-6 col-md-6 col-sm-12 Input" style="margin-top: 34px;">
							<spring:message code="placeholder.titolo" var="phDidascalia" />
							<div class="boxInput" style="margin: 0 15px 40px 15px;">
								<input type="text" class="form-control Input-text" name="linkSet_PH_.titoloMulti.SP" maxlength="300" placeholder="Aggiungi un testo con massimo 300 caratteri"
									readonly="readonly">
								<div class="scegliere">
									<spring:message code="label.titoloevento" />
								</div>
							</div>
							<spring:message code="placeholder.media.didascalia" var="phDidascalia" />
							<div class="boxInput" style="margin: 0 15px 20px 15px;">
								<textarea class="form-control Input-text resize" style="min-height: 75px; max-height: 300px" readonly="readonly"
									name="linkSet_PH_.didascaliaMulti.SP" maxlength="300" placeholder="${phDidascalia}"></textarea>
								<div class="scegliere" style="bottom: 110% !important;">
									<spring:message code="label.media.didascalia" />
								</div>
							</div>
						</div>
					</div>
					<div class="tab-pane fade blockaccordium" data-tab="frVIPvideo">
						<div class="col-lg-6 col-md-6 col-sm-12 Input" style="margin-top: 34px;">
							<spring:message code="placeholder.titolo" var="phDidascalia" />
							<div class="boxInput" style="margin: 0 15px 40px 15px;">
								<input type="text" class="form-control Input-text" name="linkSet_PH_.titoloMulti.FR" maxlength="300" placeholder="Aggiungi un testo con massimo 300 caratteri"
									readonly="readonly">
								<div class="scegliere">
									<spring:message code="label.titoloevento" />
								</div>
							</div>
							<spring:message code="placeholder.media.didascalia" var="phDidascalia" />
							<div class="boxInput" style="margin: 0 15px 20px 15px;">
								<textarea class="form-control Input-text resize" style="min-height: 75px; max-height: 300px" readonly="readonly"
									name="linkSet_PH_.didascaliaMulti.FR" maxlength="300" placeholder="${phDidascalia}"></textarea>
								<div class="scegliere" style="bottom: 110% !important;">
									<spring:message code="label.media.didascalia" />
								</div>
							</div>
						</div>
					</div>
					<div class="tab-pane fade blockaccordium" data-tab="deVIPvideo">
						<div class="col-lg-6 col-md-6 col-sm-12 Input" style="margin-top: 34px;">
							<spring:message code="placeholder.titolo" var="phDidascalia" />
							<div class="boxInput" style="margin: 0 15px 40px 15px;">
								<input type="text" class="form-control Input-text" name="linkSet_PH_.titoloMulti.DE" maxlength="300" placeholder="Aggiungi un testo con massimo 300 caratteri"
									readonly="readonly">
								<div class="scegliere">
									<spring:message code="label.titoloevento" />
								</div>
							</div>
							<spring:message code="placeholder.media.didascalia" var="phDidascalia" />
							<div class="boxInput" style="margin: 0 15px 20px 15px;">
								<textarea class="form-control Input-text resize" style="min-height: 75px; max-height: 300px" readonly="readonly"
									name="linkSet_PH_.didascaliaMulti.DE" maxlength="300" placeholder="${phDidascalia}"></textarea>
								<div class="scegliere" style="bottom: 110% !important;">
									<spring:message code="label.media.didascalia" />
								</div>
							</div>
						</div>
					</div>
					<div class="tab-pane fade blockaccordium" data-tab="ruVIPvideo">
						<div class="col-lg-6 col-md-6 col-sm-12 Input" style="margin-top: 34px;">
							<spring:message code="placeholder.titolo" var="phDidascalia" />
							<div class="boxInput" style="margin: 0 15px 40px 15px;">
								<input type="text" class="form-control Input-text" name="linkSet_PH_.titoloMulti.RU" maxlength="300" placeholder="Aggiungi un testo con massimo 300 caratteri"
									readonly="readonly">
								<div class="scegliere">
									<spring:message code="label.titoloevento" />
								</div>
							</div>
							<spring:message code="placeholder.media.didascalia" var="phDidascalia" />
							<div class="boxInput" style="margin: 0 15px 20px 15px;">
								<textarea class="form-control Input-text resize" style="min-height: 75px; max-height: 300px" readonly="readonly"
									name="linkSet_PH_.didascaliaMulti.RU" maxlength="300" placeholder="${phDidascalia}"></textarea>
								<div class="scegliere" style="bottom: 110% !important;">
									<spring:message code="label.media.didascalia" />
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="col-lg-6 col-md-6 col-sm-6 Input blockaccordiumx" style="margin-top: 34px">
					<spring:message code="placeholder.video.link" var="phLink" />
					<div class="boxInput" style="margin: 0 15px 40px 15px;">
						<input hidden="true" type="text" name="linkSet_PH_.linkId"> <input type="text" class="form-control Input-text" name="linkSet_PH_.link"
							maxlength="300" placeholder="${phLink}" readonly="readonly">
						<div class="scegliere">
							<spring:message code="label.video.linkvip" />
						</div>
					</div>
					<spring:message code="placeholder.media.credits" var="phCredits" />
					<div class="boxInput" style="margin: 0 15px 20px 15px;">
						<textarea class="form-control Input-text resize" style="min-height: 75px; max-height: 300px" readonly="readonly" name="linkSet_PH_.credits"
							maxlength="300" placeholder="${phCredits}"></textarea>
						<div class="scegliere" style="bottom: 110% !important;">
							<spring:message code="label.media.credits" />
						</div>
					</div>
				</div>
				<div class="col-lg-6 col-md-6 col-sm-6 Input blockaccordiumx" style="margin-top: 15px">
					<spring:message code="placeholder.documenti.ordine" var="phOrdine" />
					<div class="boxInput" style="margin: 0 15px 20px 15px;">
						<input type="number" class="form-control Input-text itready order" name="linkSet_PH_.ordineNumerico"
							oninput="this.value = this.value.replace(/[^0-9]/g, '').replace(/(\..*)\./g, '$1');" maxlength="10" placeholder="${phOrdine}">
							<label class="error" style="display:none;">* campo obbligatorio</label>
						<div class="scegliere">
							<spring:message code="label.documento.ordine" />
						</div>
					</div>
				</div>
				<div class="col-lg-6 col-md-6 Input blockaccordiumx" style="margin-top: 15px;">
					<div class="boxInput" style="margin: 0 15px 20px 15px;">
						<label class="switch" style="float: left;"> <input type="checkbox" class="custom-control-input itreadys switch"
							style="position: inherit" name="linkSet_PH_.daPubblicare" value="true" checked> <span class="slider round"></span>
						</label> <label class="labelswith" style="left: 15px; top: 5px; font-weight: 600; font-size: 16px;"><spring:message
								code="label.immagini.dapubblicare" /></label>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>