<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<div class="row" style="margin: 0px;">
	<div class="col-md-4 Input" style="height: auto; padding-right: 15px;">
		<div data-dynamic="telefonoSet">
			<div class="col-md-12 Input" style="margin-bottom: 0px; padding-left: 0px; text-align: right;">
				<button type="button" class="btn btn-primary invia"
					style="float: left; margin-left: 0px; margin-right: 0px; margin-bottom: 10px; margin-top: 10px;" data-bindingonload data-addbutton="telefonoSet"
					data-counter="-1" data-ph="_PH_">
					<i class="dms-wk-icon-add" aria-hidden="true"></i>
					<spring:message code="label.contatti.aggiungitelefono" />
				</button>
			</div>
			<div hidden="true" data-template="telefonoSet_PH_">
				<div class="col-md-12 Input" style="margin-top: 40px; padding-top: 12px;">
					<spring:message code="placeholder.contatti.telefono" var="phTelefono" />
					<div class="boxInput" style="margin: 0px; padding: 0px; height: 64px;">
						<input hidden="true" type="number" name="telefonoSet_PH_.contattoId"> <input type="text" class="form-control Input-text"
							name="telefonoSet_PH_.contatto" placeholder="${phTelefono}"
							oninput="this.value = this.value.replace(/[^0-9. ]/g, '').replace(/(\..*)\./g, '$1'); bothVerified(this);" maxlength="15">
						<div class="scegliere">
							<spring:message code="label.contatti.telefono" />
						</div>
					</div>
				</div>
				<div class="col-md-12 Input" style="position: relative; height: 40px; padding-left: 0px; text-align: right;">
					<div style="top: 40px; width: 100%; position: absolute; border-top: 1px solid #BDBDBD;"></div>
					<button type="button" data-deletebutton="telefonoSet_PH_" class="removeButton" style="margin-right: 0px; padding-right: 0px !important;">
						<i class="dms-wk-icon-delete" aria-hidden="true"></i>
						<spring:message code="label.media.elimina" />
					</button>
				</div>
			</div>
		</div>
	</div>
	<div class="col-md-4 Input" style="height: auto; padding-right: 15px;">
		<div data-dynamic="emailSet">
			<div class="col-md-12 Input" style="margin-bottom: 0px; padding-left: 0px; text-align: right;">
				<button type="button" class="btn btn-primary invia"
					style="float: left; margin-left: 0px; margin-right: 0px; margin-bottom: 10px; margin-top: 10px;" data-bindingonload data-addbutton="emailSet"
					data-counter="-1" data-ph="_PH_">
					<i class="dms-wk-icon-add" aria-hidden="true"></i>
					<spring:message code="label.contatti.aggiungimail" />
				</button>
			</div>
			<div hidden="true" data-template="emailSet_PH_">
				<div class="col-md-12 Input" style="margin-top: 40px; padding-top: 12px;">
					<spring:message code="placeholder.contatti.mail" var="phMail" />
					<div class="boxInput" style="margin: 0px; padding: 0px; height: 64px;">
						<input hidden="true" type="number" name="emailSet_PH_.contattoId"> <input type="text" class="form-control Input-text"
							name="emailSet_PH_.contatto" placeholder="${phMail}" oninput="bothVerified(this);" data-dynabindingonload="validatoreMail_PH_" data-event=""
							maxlength="300">
						<div class="scegliere">
							<spring:message code="label.contatti.mail" />
						</div>
					</div>
				</div>
				<div class="col-md-12 Input" style="position: relative; height: 40px; padding-left: 0px; text-align: right;">
					<div style="top: 40px; width: 100%; position: absolute; border-top: 1px solid #BDBDBD;"></div>
					<button type="button" data-deletebutton="emailSet_PH_" class="removeButton" style="margin-right: 0px; padding-right: 0px !important;">
						<i class="dms-wk-icon-delete" aria-hidden="true"></i>
						<spring:message code="label.media.elimina" />
					</button>
				</div>
			</div>
		</div>
	</div>
	<div class="col-md-4 Input" style="height: auto">
		<div data-dynamic="sitoSet">
			<div class="col-md-12 Input" style="margin-bottom: 0px; padding-left: 0px; text-align: right;">
				<button type="button" class="btn btn-primary invia"
					style="float: left; margin-left: 0px; margin-right: 0px; margin-bottom: 10px; margin-top: 10px;" data-bindingonload data-addbutton="sitoSet"
					data-counter="-1" data-ph="_PH_">
					<i class="dms-wk-icon-add" aria-hidden="true"></i>
					<spring:message code="label.contatti.aggiungisito" />
				</button>
			</div>
			<div hidden="true" data-template="sitoSet_PH_">
				<div class="col-md-12 Input" style="margin-top: 40px; padding-top: 12px;">
					<spring:message code="placeholder.contatti.sito" var="phSito" />
					<div class="boxInput" style="margin: 0px; padding: 0px; height: 64px;">
						<input hidden="true" type="number" name="sitoSet_PH_.contattoId"> <input type="text" class="form-control Input-text"
							name="sitoSet_PH_.contatto" maxlength="300" placeholder="${phSito}">
						<div class="scegliere">
							<spring:message code="label.contatti.sito" />
						</div>
					</div>
				</div>
				<div class="col-md-12 Input" style="position: relative; height: 40px; padding-left: 0px; text-align: right;">
					<div style="top: 40px; width: 100%; position: absolute; border-top: 1px solid #BDBDBD;"></div>
					<button type="button" data-deletebutton="sitoSet_PH_" class="removeButton" style="margin-right: 0px; padding-right: 0px !important;">
						<i class="dms-wk-icon-delete" aria-hidden="true"></i>
						<spring:message code="label.media.elimina" />
					</button>
				</div>
			</div>
		</div>
	</div>
</div>