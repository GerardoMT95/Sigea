<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<div class="bloccotab">
	<div class="row">
		<div class="col-md-12" style="padding: 5px 40px 5px 40px">
			<span style="color: #003a54; font-size: 16px; font-weight: 600;">ASSOCIAZIONI AGGIUNTIVE REDAZIONE</span>
		</div>
	</div>
	<div class="contarelazioniagg" data-dynamic="relazioneSetAggiunto">
		<div class="row" style="margin: 15px 20px; border-top: 1px solid #efefef; padding-top: 40px;">
			<div class="col-md-12 col-sm-12 Input">
				<div class="boxInput">
					<input type="hidden" name="relazioneSetAggiunto.eventoRelazionatoId" data-ignorefield readonly> <input type="hidden"
						name="relazioneSetAggiunto.tipoEventoAssociato" data-ignorefield readonly> <input type="hidden" name="relazioneSetAggiunto.periodo"
						data-ignorefield readonly> <input type="hidden" name="relazioneSetAggiunto.comune" data-ignorefield readonly> <input
						type="hidden" name="relazioneSetAggiunto.titolofull" data-ignorefield readonly> <select class="form-control Input-text"
						name="relazioneSetAggiunto" data-ignorefield data-bindingonload="selectizeRelazioniAgg" data-feed="selectizeRelazioniAggFeed"></select>
					<div class="scegliere">
						<spring:message code="label.relazioni.associazioni" />
					</div>
				</div>
			</div>
			<div class="col-md-12 col-sm-12 Input" style="margin-bottom: 30px; text-align: right;">
				<button class="btn btn-primary invia" type="button" data-bindingonload data-addbutton="relazioneSetAggiunto" data-counter="-1"
					data-ph="_PH_" data-feeder="relazioneSetAggiunto" style="margin: 0 15px;">
					<i class="dms-wk-icon-add"></i>
					<spring:message code="label.relazioni.aggiungi" />
				</button>
			</div>
		</div>
		<div hidden="true" data-template="relazioneSetAggiunto_PH_" class="row"
			style="margin-top: 15px; margin-right: 15px; margin-left: 15px; border-bottom: 1px solid #efefef; padding-top: 15px; padding-bottom: 15px;">
			<div class="col-md-12 col-sm-12" style="padding-bottom: 15px;">
				<i class="dms-wk-icon-evento" aria-hidden="true" style="font-size: 24px;"></i> <input type="hidden" name="relazioneSetAggiunto_PH_.relazioneId"
					readonly> <input type="hidden" name="relazioneSetAggiunto_PH_.eventoRelazionatoId" readonly> <input type="hidden"
					name="relazioneSetAggiunto_PH_.tipoRelazione" readonly> <input type="hidden" name="relazioneSetAggiunto_PH_.schedePubblicazione" readonly>
				<input type="hidden" name="relazioneSetAggiunto_PH_.statoEventoAssociato" readonly> <input type="hidden"
					name="relazioneSetAggiunto_PH_.redazioneId" readonly> <span class="titoloinput"><input type="hidden"
					name="relazioneSetAggiunto_PH_.titolo" readonly class="inputtotext totext"
					style="width: 80%; background: transparent !important; position: relative; margin-left: 10px; top: -5px; height: 25px;" /></span>
			</div>
			<div class="col-md-6" style="padding-bottom: 15px;">
				<b>Tipologia:</b> <input type="hidden" class="inputtotext toevento" name="relazioneSetAggiunto_PH_.tipoEventoAssociato" readonly
					style="width: 80%; background: transparent !important; font-weight: 400; margin-left: 10px;">
			</div>
			<div class="col-md-6" style="padding-bottom: 15px;">
				<b>Data:</b> <input type="hidden" class="inputtotext totext" name="relazioneSetAggiunto_PH_.periodo" readonly
					style="width: 80%; background: transparent !important; font-weight: 400; margin-left: 10px;">
			</div>
			<div class="col-md-12">
				<b>Comune:</b> <input type="hidden" name="relazioneSetAggiunto_PH_.comune" readonly class="inputtotext totext"
					style="width: 80%; background: transparent !important; font-weight: 400; margin-left: 10px;">
			</div>
			<div class="col-md-12 col-sm-12 Input" style="padding-right: 10px">
				<button type="button" style="float: right; padding-right: 15px !important;" data-deletebutton="relazioneSetAggiunto_PH_" class="removeButton">
					<i class="dms-wk-icon-delete" aria-hidden="true"></i>
					<spring:message code="label.relazioni.rimuovi" />
				</button>
			</div>
		</div>
	</div>
</div>
