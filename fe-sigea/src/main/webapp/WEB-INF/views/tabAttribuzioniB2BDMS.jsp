<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<div class="row" style="margin-top: 20px; margin-left: 0px; margin-right:0px">
 	<div class="row" style="margin-left: 0px;">
		<div class="col-md-6 col-sm-6 Input multipl" style="margin-bottom: 10px; height: auto">
			<div class="boxInput">
				<select class="form-control Input-text multi" name="sottotipologieSet[]" id="sottotipologieSet" multiple="multiple"></select>
				<div class="scegliere"><spring:message code="label.sottotipologie"/></div>
			</div>
		</div>

			<div class="col-md-6 col-sm-6 Input" style="margin-bottom: 10px; height: auto">
				<div class="boxInput">
					<select class="form-control Input-text multi" name="modalitaPartecipazioneSet[]" id="modalitaPartecipazione" multiple="multiple"></select>
					<div class="scegliere"><spring:message code="label.modalitapartecipazione"/></div>
				</div>
			</div>	
	</div> 
	<div class="row" style="margin-left: 0px;">
		<div class="col-md-6 col-sm-6 Input" style="margin-bottom: 10px; height: auto">
			<spring:message code="placeholder.nomePartecipante" var="phNomePartecipante"/>
			<div class="boxInput">
				<input type="text" class="form-control Input-text" id="nomePartecipante" name="nomePartecipante" maxlength="200" placeholder="${phNomePartecipante}">
				<div class="scegliere"><spring:message code="label.nomePartecipante"/></div>
			</div>
		</div>
		<div class="col-md-6 col-sm-6 Input" style="margin-bottom: 10px; height: auto">
			<spring:message code="placeholder.cognomePartecipante" var="phCognomePartecipante"/>
			<div class="boxInput">
				<input type="text" class="form-control Input-text" id="cognomePartecipante" name="cognomePartecipante" maxlength="200" placeholder="${phCognomePartecipante}">
				<div class="scegliere"><spring:message code="label.cognomePartecipante"/></div>
			</div>
		</div>
	</div>
	<div class="row" style="margin-left: 0px;">
		<div class="col-md-6 col-sm-6 Input" style="margin-bottom: 10px; height: auto">
			<spring:message code="placeholder.telefonoPartecipante" var="phTelefonoPartecipante"/>
			<div class="boxInput">
				<input type="text" class="form-control Input-text" id="telefonoPartecipante" name="telefonoPartecipante"  maxlength="200" placeholder="${phTelefonoPartecipante}">
				<div class="scegliere"><spring:message code="label.telefonoPartecipante"/></div>
			</div>
		</div>
		<div class="col-md-6 col-sm-6 Input" style="margin-bottom: 10px; height: auto">
			<spring:message code="placeholder.mailPartecipante" var="phMailPartecipante"/>
			<div class="boxInput">
				<input type="text" class="form-control Input-text" id="mailPartecipante" name="mailPartecipante"  maxlength="200" placeholder="${phMailPartecipante}">
				<div class="scegliere"><spring:message code="label.mailPartecipante"/></div>
			</div>
		</div>
	</div>

	<div class="row" style="margin-left: 0px; margin-bottom: 20px; height: auto">
		<div class="col-md-4 Input">
			<div class="scegliere" style="top: -20px;">Attribuzioni</div>	
			<label class="checkbox">
				<input type="checkbox" style="position: inherit" name="accreditamentoSeller" value="true">
				<spring:message code="label.accreditamentoSeller"/>
			</label>
			<label class="checkbox">
				<input type="checkbox" style="position: inherit" name="accreditamentoBuyer" value="true">
				<spring:message code="label.accreditamentoBuyer"/>
			</label>
		</div>		
		<div class="col-md-4 Input">
			<label class="checkbox">
				<input type="checkbox" style="position: inherit" name="agenda" value="true">
				<spring:message code="label.agenda"/>
			</label>
			<label class="checkbox">
				<input type="checkbox" style="position: inherit" name="pagamento" value="true">
				<spring:message code="label.pagamento"/>
			</label>
		</div>
	</div>
 	<div class="row" style="margin-left: 0px; margin-bottom: 45px">
		<div class="col-md-6 col-sm-6 Input inputicondata2" style="margin-bottom: 10px; height: auto">
			<spring:message code="placeholder.dataDaAccreditamento" var="phDataDaAccreditamento"/>
			<div class="boxInput">
				<input type="text" class="form-control-date Input-text startS-d data" name="dataDaAccreditamento" readonly placeholder="gg/mm/aaaa" >
				<div class="scegliere"><spring:message code="label.dataDaAccreditamento"/></div>
			</div>
		</div>
		<div class="col-md-6 col-sm-6 Input inputicondata2" style="margin-bottom: 10px; height: auto">
			<spring:message code="placeholder.dataAAccreditamento" var="phDataAAccreditamento"/>
			<div class="boxInput">
				<input type="text" class="form-control-date Input-text startS-d data" name="dataAAccreditamento" readonly placeholder="gg/mm/aaaa">
				<div class="scegliere"><spring:message code="label.dataAAccreditamento"/></div>
			</div>
		</div>
	</div>
</div>