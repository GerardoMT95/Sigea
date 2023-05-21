<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<div class="contarelazioni" data-dynamic="relazioneSet">
	<div class="row" style="margin-top: 10px; margin-right: 0px; margin-left: 0px">
		<div class="col-md-12 col-sm-12 Input">
			<div class="boxInput" id="relazioneSelect" style="margin-right:0px;">
				<input type="hidden" name="relazioneSet.eventoRelazionatoId" data-ignorefield readonly> 
				<input type="hidden"
					name="relazioneSet.tipoEventoAssociato" data-ignorefield readonly> 
					<input type="hidden" name="relazioneSet.periodo" data-ignorefield
					readonly> 
					<input type="hidden" name="relazioneSet.comune" data-ignorefield readonly> <input type="hidden"
					name="relazioneSet.titolofull" data-ignorefield readonly> 
					<select class="form-control Input-text" name="relazioneSet" data-ignorefield
					data-feed="selectizeRelazioniFeed" data-bindingonload="selectizeRelazioni"></select>
				<div class="scegliere">
					<spring:message code="label.relazioni.associazioni" />
				</div>
			</div>
		</div>
		<div class="col-md-12 col-sm-12 Input" style="text-align: right;">
			<button class="btn btn-primary invia" type="button" data-bindingonload data-addbutton="relazioneSet"
				data-counter="-1" data-ph="_PH_" data-feeder="relazioneSet">
				<i class="dms-wk-icon-add"></i>
				<spring:message code="label.relazioni.aggiungi" />
			</button>
		</div>
	</div>
	<div hidden="true" data-template="relazioneSet_PH_" class="row"
		style="margin-top: 15px; margin-right: 0px; margin-left: 0px; border-bottom: 1px solid #efefef; padding-top: 15px; padding-bottom: 15px;">
		<div class="col-md-12 col-sm-12" style="padding-bottom: 15px;">
			<i class="dms-wk-icon-evento" aria-hidden="true" style="font-size: 24px;"></i> <input type="hidden" name="relazioneSet_PH_.relazioneId" readonly>
			<input type="hidden" name="relazioneSet_PH_.eventoRelazionatoId" readonly> <input type="hidden" name="relazioneSet_PH_.schedePubblicazione"
				readonly> <input type="hidden" name="relazioneSet_PH_.statoEventoAssociato" readonly> <input type="hidden"
				name="relazioneSet_PH_.tipoRelazione" readonly> <input type="hidden" name="relazioneSet_PH_.redazioneId" readonly> <span
				class="titoloinput"> <input type="hidden" class="inputtotext totext" name="relazioneSet_PH_.titolo" readonly
				style="width: 80%; background: transparent !important; position: relative; margin-left: 10px; top: -5px; height: 25px;" />
			</span>
		</div>
		<div class="col-md-6" style="padding-bottom: 20px;">
			<b>Tipologia:</b> <input type="hidden" class="inputtotext toevento" name="relazioneSet_PH_.tipoEventoAssociato" readonly
				style="width: 80%; background: transparent !important; font-weight: 400; margin-left: 10px;">
		</div>
		<div class="col-md-6" style="padding-bottom: 20px;">
			<b>Data:</b> <input type="hidden" class="inputtotext totext" name="relazioneSet_PH_.periodo" readonly
				style="width: 80%; background: transparent !important; font-weight: 400; margin-left: 10px;">
		</div>
		<div class="col-md-12">
			<b>Comune:</b> <input type="hidden" name="relazioneSet_PH_.comune" readonly class="inputtotext totext"
				style="width: 80%; background: transparent !important; font-weight: 400; margin-left: 10px;">
		</div>
		<div class="col-md-12 col-sm-12 Input" style="padding-right: 10px">
			<button type="button" style="float: right; padding: 0px 5px 0px 5px !important;" data-deletebutton="relazioneSet_PH_" class="removeButton">
				<i class="dms-wk-icon-delete" aria-hidden="true"></i>
				<spring:message code="label.relazioni.rimuovi" />
			</button>
		</div>
	</div>
</div>