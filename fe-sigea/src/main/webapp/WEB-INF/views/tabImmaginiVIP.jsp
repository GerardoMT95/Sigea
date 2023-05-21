<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<div class="row" style="margin-bottom: 15px">
	<div class="col-md-12">
		<spring:message code="label.schedavip.mediaImg.accordion" />
	</div>
</div>
<div class="row">
	<div class="col-md-12 Input" id="boxmobileLinguaImgVip" style="display: none; padding-bottom: 15px; margin-left: 15px; margin-right: 15px;">
		<div class="mobile-input">
			<select id="mobiletabLinguaImgVip" class="form-control Input-text">
				<option value="IT" data-target="[data-tab='itVIPmedia']">IT</option>
				<option value="EN" data-target="[data-tab='enVIPmedia']">EN</option>
				<option value="SP" data-target="[data-tab='spVIPmedia']">SP</option>
				<option value="FR" data-target="[data-tab='frVIPmedia']">FR</option>
				<option value="DE" data-target="[data-tab='deVIPmedia']">DE</option>
				<option value="RU" data-target="[data-tab='ruVIPmedia']">RU</option>
			</select>
			<div class="scegliere">Cambia lingua</div>
		</div>
	</div>
	<ul class="nav nav-tabs" id="VIPimmtab" role="tablist" style="margin-right: 15px; margin-left: 15px; margin-bottom: 15px">
		<li class="nav-item active" style="cursor: pointer" data-tablink="itVIPm"><a data-target="[data-tab='itVIPmedia']" data-toggle="tab"
			class="nav-link active" style="cursor: pointer">IT</a></li>
		<li class="nav-item" style="cursor: pointer" data-tablink="enVIPm"><a data-target="[data-tab='enVIPmedia']" data-toggle="tab"
			class="nav-link active" style="cursor: pointer">EN</a></li>
		<li class="nav-item" style="cursor: pointer" data-tablink="spVIPm"><a data-target="[data-tab='spVIPmedia']" data-toggle="tab"
			class="nav-link active" style="cursor: pointer">SP</a></li>
		<li class="nav-item" style="cursor: pointer" data-tablink="frVIPm"><a data-target="[data-tab='frVIPmedia']" data-toggle="tab"
			class="nav-link active" style="cursor: pointer">FR</a></li>
		<li class="nav-item" style="cursor: pointer" data-tablink="deVIPm"><a data-target="[data-tab='deVIPmedia']" data-toggle="tab"
			class="nav-link active" style="cursor: pointer">DE</a></li>
		<li class="nav-item" style="cursor: pointer" data-tablink="ruVIPm"><a data-target="[data-tab='ruVIPmedia']" data-toggle="tab"
			class="nav-link active" style="cursor: pointer">RU</a></li>
	</ul>
	<div class="row" hidden="true">
		<div class="col-md-2 Input">
			<button type="button" class="btn btn-primary" data-bindingonload data-addbutton="immagineSet" data-counter="-1" data-ph="_PH_"
				style="margin-left: 20px; float: right;"></button>
		</div>
	</div>
	<div class="bloccotab">
		<div class="row">
			<div class="col-md-12" style="padding: 15px 40px 15px 40px">
				<span style="color: #003a54; font-size: 16px; font-weight: 600;">IMMAGINI DEL NUCLEO</span>
			</div>
		</div>
		<div data-dynamic="immagineSet" style="padding: 0px 9px;" class="contaimmaginivip">
			<div class="row" hidden="true" data-template="immagineSet_PH_"
				style="border-top: 1px solid #efefef; box-shadow: 0px 4px 10px rgba(0, 0, 0, 0.2); margin: 10px 15px 30px 15px; background: #F2F2F2;">
				<input type="hidden" name="immagineSet_PH_.immagineId" readonly> <input type="hidden" name="immagineSet_PH_.estensione" readonly>
				<input type="hidden" name="immagineSet_PH_.dimensione" readonly>
				<div class="tab-content">
					<div class="col-md-12 Input accordionlight" style="padding: 15px; background: #ffffff; cursor: pointer;">
						<div class="boxInput" id="nomefilebox" style="margin-bottom: 0px;">
							<img src="${contextPath}/assets/svg/doc_nero.svg" style="margin-right: 20px;"> <span class="titoloinput"> <input type="hidden"
								class="inputtotext totext" name="immagineSet_PH_.nomeOriginale" readonly />
							</span>

							<div style="float: right; padding-top: 16px;">
								<img src="${contextPath}/assets/images/FRECCIA_SU.svg" class="frecciafiltro" id="frecciaopen" style="width: 18px;">
							</div>
						</div>
					</div>
					<div class="col-md-12 Input blockaccordiumx" style="text-align: right; height: 60px; margin-top: 15px; background: #F2F2F2;">
						<div class="boxInput" style="margin: 0 15px 20px 15px;">
							<a href="${contextPath}" data-name="immagineSet_PH_Visualizza" data-dynabindingonload="initVisualizza_PH_" data-event="click"
								style="margin-left: 15px; font-size: 16px; font-weight: 600;">
								<i class="dms-wk-icon-show-details" aria-hidden="true"></i>
								<%-- <img src="${contextPath}/assets/images/VIEW.svg" style="margin-right: 10px;"> --%>
							<spring:message code="label.media.visualizza" /></a> <a href="${contextPath}" data-name="immagineSet_PH_Download"
								data-dynabindingonload="initDownload_PH_" data-event="click" style="margin-left: 15px; font-size: 16px; font-weight: 600;">
								<i class="dms-wk-icon-download" aria-hidden="true"></i>				
								<%-- <img
								src="${contextPath}/assets/images/download.svg" style="margin-right: 10px;"> --%>
							<spring:message code="label.media.download" /></a>
						</div>
					</div>
					<div class="tab-pane fade active in blockaccordium" data-tab="itVIPmedia">
						<div class="col-lg-6 col-md-6 Input" style="margin-top: 15px">
							<spring:message code="placeholder.media.didascalia" var="phDidascalia" />
							<div class="boxInput" style="margin: 0 15px 20px 15px;">
								<textarea class="form-control Input-text resize" style="min-height: 75px; max-height: 300px" name="immagineSet_PH_.didascaliaMulti.IT"
									maxlength="300" placeholder="${phDidascalia}" readonly="readonly"></textarea>
								<div class="scegliere" style="bottom: 110% !important;">
									<spring:message code="label.media.didascalia" />
								</div>
							</div>
						</div>
					</div>
					<div class="tab-pane fade blockaccordium" data-tab="enVIPmedia">
						<div class="col-lg-6 col-md-6 Input" style="margin-top: 15px">
							<spring:message code="placeholder.media.didascalia" var="phDidascalia" />
							<div class="boxInput" style="margin: 0 15px 20px 15px;">
								<textarea class="form-control Input-text resize" style="min-height: 75px; max-height: 300px" name="immagineSet_PH_.didascaliaMulti.EN"
									maxlength="300" placeholder="${phDidascalia}" readonly="readonly"></textarea>
								<div class="scegliere" style="bottom: 110% !important;">
									<spring:message code="label.media.didascalia" />
								</div>
							</div>
						</div>
					</div>
					<div class="tab-pane fade blockaccordium" data-tab="spVIPmedia">
						<div class="col-lg-6 col-md-6 Input" style="margin-top: 15px">
							<spring:message code="placeholder.media.didascalia" var="phDidascalia" />
							<div class="boxInput" style="margin: 0 15px 20px 15px;">
								<textarea class="form-control Input-text resize" style="min-height: 75px; max-height: 300px" name="immagineSet_PH_.didascaliaMulti.SP"
									maxlength="300" placeholder="${phDidascalia}" readonly="readonly"></textarea>
								<div class="scegliere" style="bottom: 110% !important;">
									<spring:message code="label.media.didascalia" />
								</div>
							</div>
						</div>
					</div>
					<div class="tab-pane fade blockaccordium" data-tab="frVIPmedia">
						<div class="col-lg-6 col-md-6 Input" style="margin-top: 15px">
							<spring:message code="placeholder.media.didascalia" var="phDidascalia" />
							<div class="boxInput" style="margin: 0 15px 20px 15px;">
								<textarea class="form-control Input-text resize" style="min-height: 75px; max-height: 300px" name="immagineSet_PH_.didascaliaMulti.FR"
									maxlength="300" placeholder="${phDidascalia}" readonly="readonly"></textarea>
								<div class="scegliere" style="bottom: 110% !important;">
									<spring:message code="label.media.didascalia" />
								</div>
							</div>
						</div>
					</div>
					<div class="tab-pane fade blockaccordium" data-tab="deVIPmedia">
						<div class="col-lg-6 col-md-6 Input" style="margin-top: 15px">
							<spring:message code="placeholder.media.didascalia" var="phDidascalia" />
							<div class="boxInput" style="margin: 0 15px 20px 15px;">
								<textarea class="form-control Input-text resize" style="min-height: 75px; max-height: 300px" name="immagineSet_PH_.didascaliaMulti.DE"
									maxlength="300" placeholder="${phDidascalia}" readonly="readonly"></textarea>
								<div class="scegliere" style="bottom: 110% !important;">
									<spring:message code="label.media.didascalia" />
								</div>
							</div>
						</div>
					</div>
					<div class="tab-pane fade blockaccordium" data-tab="ruVIPmedia">
						<div class="col-lg-6 col-md-6 Input" style="margin-top: 15px">
							<spring:message code="placeholder.media.didascalia" var="phDidascalia" />
							<div class="boxInput" style="margin: 0 15px 20px 15px;">
								<textarea class="form-control Input-text resize" style="min-height: 75px; max-height: 300px" name="immagineSet_PH_.didascaliaMulti.RU"
									maxlength="300" placeholder="${phDidascalia}" readonly="readonly"></textarea>
								<div class="scegliere" style="bottom: 110% !important;">
									<spring:message code="label.media.didascalia" />
								</div>
							</div>
						</div>
					</div>
					<div class="col-lg-6 col-md-6 Input blockaccordiumx" style="margin-top: 15px">
						<spring:message code="placeholder.media.credits" var="phCredits" />
						<div class="boxInput" style="margin: 0 15px 20px 15px;">
							<textarea class="form-control Input-text resize" style="min-height: 75px; max-height: 200px" name="immagineSet_PH_.credits" maxlength="300"
								placeholder="${phCredits}" readonly="readonly"></textarea>
							<div class="scegliere" style="bottom: 110% !important;">
								<spring:message code="label.media.credits" />
							</div>
						</div>
					</div>
					<div class="col-lg-6 col-md-6  Input blockaccordiumx" style="margin-top: 15px">
						<spring:message code="placeholder.documenti.ordine" var="phOrdine" />
						<div class="boxInput" style="margin: 0 15px 20px 15px;">
							<input type="number" class="form-control Input-text itready" name="immagineSet_PH_.ordine"
								min="1" maxlength="10" placeholder="${phOrdine}" />
								<label class="error" style="display:none;">* campo obbligatorio</label>
							<div class="scegliere">
								<spring:message code="label.documento.ordine.required" />
							</div>
						</div>
					</div>
					<div class="col-lg-3 col-md-3  Input blockaccordiumx" style="margin-top: 15px;">
						<div class="boxInput" style="margin: 0 15px 20px 15px;">
							<label class="switch" style="float: left;"> <input type="checkbox" class="custom-control-input switch itreadys"
								style="position: inherit" name="immagineSet_PH_.banner" value="true"> <span class="slider round"></span>
							</label> <label class="labelswith" style="left: 15px; top: 5px; font-weight: 600; font-size: 16px;"><spring:message
									code="label.immagini.banner" /></label>
						</div>
					</div>
					<div class="col-lg-3 col-md-3  Input blockaccordiumx" style="margin-top: 15px;">
						<div class="boxInput" style="margin: 0 15px 20px 15px;">
							<label class="switch" style="float: left;"> <input type="checkbox" class="custom-control-input switch" style="position: inherit"
								name="immagineSet_PH_.daPubblicare" value="true" checked> <span class="slider round"></span>
							</label> <label class="labelswith" style="left: 15px; top: 5px; font-weight: 600; font-size: 16px;"><spring:message
									code="label.immagini.dapubblicare" /></label>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>