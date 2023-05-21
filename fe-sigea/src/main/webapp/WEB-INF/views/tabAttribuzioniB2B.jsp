<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<div class="row" style="margin-top: 20px; margin-left: 0px; margin-right:0px">
 	<div class="row" style="margin-left: 0px;">
		<div class="col-md-6 col-sm-6 Input multipl" style="margin-bottom: 10px; height: auto">
			<div class="boxInput">
				<select class="form-control Input-text multi" name="sottotipologieSet[]" id="sottotipologieSet" multiple="multiple"></select>
				<div class="scegliere"><spring:message code="label.sottotipologie.en"/></div>
			</div>
		</div>

		<div class="col-md-6 col-sm-6 Input"style="margin-bottom: 10px; height: auto" >
				<div class="boxInput">
					<select class="form-control Input-text multi" name="modalitaPartecipazioneSet[]" id="modalitaPartecipazione" multiple="multiple"></select>
					<div class="scegliere"><spring:message code="label.modalitapartecipazione.en"/></div>
				</div>
		</div>
	</div> 
	<div class="row" style="margin-left: 0px;">
		<div class="col-md-6 col-sm-6 Input" style="margin-bottom: 10px; height: auto">
			<spring:message code="placeholder.nomePartecipante.en" var="phNomePartecipante"/>
			<div class="boxInput">
				<input type="text" class="form-control Input-text" name="nomePartecipante" maxlength="200" placeholder="${phNomePartecipante}">
				<div class="scegliere"><spring:message code="label.nomePartecipante.en"/></div>
			</div>
		</div>
		<div class="col-md-6 col-sm-6 Input" style="margin-bottom: 10px; height: auto">
			<spring:message code="placeholder.cognomePartecipante.en" var="phCognomePartecipante"/>
			<div class="boxInput">
				<input type="text" class="form-control Input-text" name="cognomePartecipante" maxlength="200" placeholder="${phCognomePartecipante}">
				<div class="scegliere"><spring:message code="label.cognomePartecipante.en"/></div>
			</div>
		</div>
	</div>
	<div class="row" style="margin-left: 0px;">
		<div class="col-md-6 col-sm-6 Input" style="margin-bottom: 10px; height: auto">
			<spring:message code="placeholder.telefonoPartecipante.en" var="phTelefonoPartecipante"/>
			<div class="boxInput">
				<input type="text" class="form-control Input-text" name="telefonoPartecipante"  maxlength="200" placeholder="${phTelefonoPartecipante}">
				<div class="scegliere"><spring:message code="label.telefonoPartecipante.en"/></div>
			</div>
		</div>
		<div class="col-md-6 col-sm-6 Input" style="margin-bottom: 10px; height: auto">
			<spring:message code="placeholder.mailPartecipante.en" var="phMailPartecipante"/>
			<div class="boxInput">
				<input type="text" class="form-control Input-text" name="mailPartecipante"  maxlength="200" placeholder="${phMailPartecipante}">
				<div class="scegliere"><spring:message code="label.mailPartecipante.en"/></div>
			</div>
		</div>
	</div>
	<div class="row" style="margin-left: 0px; margin-bottom: 20px; height: auto">
		<div class="col-md-4 Input">
			<div class="scegliere" style="top: -20px;">Attributions</div>
			<label class="checkbox">
				<input type="checkbox" style="position: inherit" name="accreditamentoSeller" value="true">
				<spring:message code="label.accreditamentoSeller.en"/>
			</label>
			<label class="checkbox">
				<input type="checkbox" style="position: inherit" name="accreditamentoBuyer" value="true">
				<spring:message code="label.accreditamentoBuyer.en"/>
			</label>
		</div>
		<div class="col-md-4 Input">
			<label class="checkbox">
				<input type="checkbox" style="position: inherit" name="agenda" value="true">
				<spring:message code="label.agenda.en"/>
			</label>
			<label class="checkbox">
				<input type="checkbox" style="position: inherit" name="pagamento" value="true">
				<spring:message code="label.pagamento.en"/>
			</label>
		</div>
	</div>
 	<div class="row" style="margin-left: 0px; margin-bottom: 45px">
		<div class="col-md-6 col-sm-6 Input inputicondata2" style="margin-bottom: 10px; height: auto">
			<spring:message code="placeholder.dataDaAccreditamento.en" var="phDataDaAccreditamento"/>
			<div class="boxInput">
				<input type="text" class="form-control-date Input-text startS-d data" name="dataDaAccreditamento" readonly placeholder="dd/mm/yyyy" >
				<div class="scegliere"><spring:message code="label.dataDaAccreditamento.en"/></div>
			</div>
		</div>
		<div class="col-md-6 col-sm-6 Input inputicondata2" style="margin-bottom: 10px; height: auto">
			<spring:message code="placeholder.dataAAccreditamento.en" var="phDataAAccreditamento"/>
			<div class="boxInput">
				<input type="text" class="form-control-date Input-text startS-d data" name="dataAAccreditamento" readonly placeholder="dd/mm/yyyy">
				<div class="scegliere"><spring:message code="label.dataAAccreditamento.en"/></div>
			</div>
		</div>
	</div>
</div>