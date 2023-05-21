<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<div class="row" style="margin-top: 20px; margin-left: 0px; margin-right: 0px">
	<div class="col-md-6 col-sm-12 Input multipl" id="tipologiaEvento">
		<div class="boxInput">
			<select class="form-control Input-text multi" name="tipologieEventiSet[]" id="tipologiaEventoVIP" multiple="multiple"></select>
			<div class="scegliere">
				<spring:message code="label.tipologiaevento" />
			</div>
		</div>
	</div>
	<div class="col-md-6 col-sm-12 Input multipl" id="tipologiaAttivita" hidden="true">
		<div class="boxInput">
			<select class="form-control Input-text multi" name="tipologieAttivitaSet[]" id="tipologiaVIP" multiple="multiple"></select>
			<div class="scegliere">
				<spring:message code="label.tipologiaO" />
			</div>
		</div>
	</div>
</div>