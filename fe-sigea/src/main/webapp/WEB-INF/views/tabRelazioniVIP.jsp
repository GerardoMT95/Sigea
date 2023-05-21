<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<div class="bloccotab">
	<div class="row">
		<div class="col-md-12" style="padding: 5px 40px 5px 40px">
			<span style="color: #003a54; font-size: 16px; font-weight: 600;">ASSOCIAZIONI DEL NUCLEO</span>
		</div>
	</div>
	<div class="contarelazioni" data-dynamic="relazioneSet">
		<div hidden="true" data-template="relazioneSet_PH_" class="row"
			style="margin-top: 15px; margin-right: 20px; margin-left: 20px; border-top: 1px solid #efefef; padding-top: 15px;">
			<div class="col-md-12 col-sm-12" style="padding-bottom: 15px;">
				<i class="dms-wk-icon-evento" aria-hidden="true" style="font-size: 24px;"></i> 
				<input type="hidden" name="relazioneSet_PH_.relazioneId" readonly>
				<input type="hidden" name="relazioneSet_PH_.eventoRelazionatoId" readonly> 
				<input type="hidden" name="relazioneSet_PH_.tipoRelazione" readonly> 
				<input type="hidden" name="relazioneSet_PH_.schedePubblicazione" readonly> 
				<input type="hidden" name="relazioneSet_PH_.statoEventoAssociato" readonly> 
				<span class="titoloinput"> 
				<input type="hidden" name="relazioneSet_PH_.titolo" class="inputtotext totext" readonly
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
			<div class="col-md-6" style="padding: 15px 15px;">
				<b>Comune:</b> <input type="hidden" name="relazioneSet_PH_.comune" readonly class="inputtotext totext"
					style="width: 80%; background: transparent !important; font-weight: 400; margin-left: 10px;">
			</div>
			<div class="col-md-6">
				<label class="switch"> <input type="checkbox" class="custom-control-input switch" style="position: inherit"
					name="relazioneSet_PH_.mantieni" value="true"> <span class="slider round"></span>
				</label> <label class="labelswith"><spring:message code="label.mantieni" /></label>
			</div>
		</div>
	</div>
	<div class="row" hidden="true" style="margin-left: 15px; padding-top: 20px; padding-right: 15px">
		<div class="col-md-12 Input" style="margin-bottom: 5px; padding-top: 5px; text-align: right;">
			<button type="button" class="btn btn-primary" data-bindingonload data-addbutton="relazioneSet" data-counter="-1" data-ph="_PH_"></button>
		</div>
	</div>
</div>