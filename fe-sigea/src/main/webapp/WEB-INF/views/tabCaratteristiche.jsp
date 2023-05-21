<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<div class="row" style="padding-left: 20px; padding-top: 5px">
	<div class="col-md-2 Input" style="height: auto">
		<label class="checkbox checkbox-padre"> <input type="checkbox" style="position: inherit" name="teatro" value="true"
			data-bindingonload="attivazioneCheckbox" data-event="change"> <spring:message code="label.caratteristiche.teatro" />
		</label> <label class="checkbox" hidden="true"> <input type="checkbox" style="position: inherit" name="festivalt" value="true"> <spring:message
				code="label.caratteristiche.festival" />
		</label> <label class="checkbox" hidden="true"> <input type="checkbox" style="position: inherit" name="spettacolo" value="true"> <spring:message
				code="label.caratteristiche.spettacolo" />
		</label>
	</div>
	<div class="col-md-2 Input" style="height: auto">
		<label class="checkbox checkbox-padre"> <input type="checkbox" style="position: inherit" name="business" value="true"
			data-bindingonload="attivazioneCheckbox" data-event="change"> <spring:message code="label.caratteristiche.business" />
		</label> <label class="checkbox" hidden="true"> <input type="checkbox" style="position: inherit" name="conferenza" value="true"> <spring:message
				code="label.caratteristiche.conferenza" />
		</label> <label class="checkbox" hidden="true"> <input type="checkbox" style="position: inherit" name="fiera" value="true"> <spring:message
				code="label.caratteristiche.fiera" />
		</label>
	</div>
	<div class="col-md-2 Input" style="height: auto">
		<label class="checkbox checkbox-padre"> <input type="checkbox" style="position: inherit" name="intrattenimento" value="true"
			data-bindingonload="attivazioneCheckbox" data-event="change"> <spring:message code="label.caratteristiche.intrattenimento" />
		</label> <label class="checkbox" hidden="true"> <input type="checkbox" style="position: inherit" name="festa" value="true"> <spring:message
				code="label.caratteristiche.festa" />
		</label> <label class="checkbox" hidden="true"> <input type="checkbox" style="position: inherit" name="gioco" value="true"> <spring:message
				code="label.caratteristiche.gioco" />
		</label> <label class="checkbox" hidden="true"> <input type="checkbox" style="position: inherit" name="serata" value="true"> <spring:message
				code="label.caratteristiche.serata" />
		</label>
	</div>
	<div class="col-md-2 Input" style="height: auto">
		<label class="checkbox checkbox-padre"> <input type="checkbox" style="position: inherit" name="enogastronomia" value="true"
			data-bindingonload="attivazioneCheckbox" data-event="change"> <spring:message code="label.caratteristiche.enogastronomia" />
		</label> <label class="checkbox" hidden="true"> <input type="checkbox" style="position: inherit" name="degustazione" value="true"> <spring:message
				code="label.caratteristiche.degustazione" />
		</label> <label class="checkbox" hidden="true"> <input type="checkbox" style="position: inherit" name="festivalE" value="true"> <spring:message
				code="label.caratteristiche.festival" />
		</label> <label class="checkbox" hidden="true"> <input type="checkbox" style="position: inherit" name="sagra" value="true"> <spring:message
				code="label.caratteristiche.sagra" />
		</label>
	</div>
	<div class="col-md-2 Input" style="height: auto">
		<label class="checkbox checkbox-padre"> <input type="checkbox" style="position: inherit" name="cinema" value="true"
			data-bindingonload="attivazioneCheckbox" data-event="change"> <spring:message code="label.caratteristiche.cinema" />
		</label> <label class="checkbox" hidden="true"> <input type="checkbox" style="position: inherit" name="festivalC" value="true"> <spring:message
				code="label.caratteristiche.festival" />
		</label> <label class="checkbox" hidden="true"> <input type="checkbox" style="position: inherit" name="proiezione" value="true"> <spring:message
				code="label.caratteristiche.proiezione" />
		</label> <label class="checkbox" hidden="true"> <input type="checkbox" style="position: inherit" name="rassegna" value="true"> <spring:message
				code="label.caratteristiche.rassegna" />
		</label>
	</div>
	<div class="col-md-2 Input" style="height: auto">
		<label class="checkbox checkbox-padre"> <input type="checkbox" style="position: inherit" name="musica" value="true"
			data-bindingonload="attivazioneCheckbox" data-event="change"> <spring:message code="label.caratteristiche.musica" />
		</label> <label class="checkbox" hidden="true"> <input type="checkbox" style="position: inherit" name="concerto" value="true"> <spring:message
				code="label.caratteristiche.concerto" />
		</label> <label class="checkbox" hidden="true"> <input type="checkbox" style="position: inherit" name="festivalM" value="true"> <spring:message
				code="label.caratteristiche.festival" />
		</label> <label class="checkbox" hidden="true"> <input type="checkbox" style="position: inherit" name="spettacoloM" value="true"> <spring:message
				code="label.caratteristiche.spettacolo" />
		</label>
	</div>
</div>
<div class="row" style="padding-left: 20px">
	<div class="col-md-2 Input" style="height: auto">
		<label class="checkbox checkbox-padre"> <input type="checkbox" style="position: inherit" name="sport" value="true"
			data-bindingonload="attivazioneCheckbox" data-event="change"> <spring:message code="label.caratteristiche.sport" />
		</label> <label class="checkbox" hidden="true"> <input type="checkbox" style="position: inherit" name="gara" value="true"> <spring:message
				code="label.caratteristiche.gara" />
		</label> <label class="checkbox" hidden="true"> <input type="checkbox" style="position: inherit" name="maratona" value="true"> <spring:message
				code="label.caratteristiche.maratona" />
		</label> <label class="checkbox" hidden="true"> <input type="checkbox" style="position: inherit" name="partita" value="true"> <spring:message
				code="label.caratteristiche.partita" />
		</label> <label class="checkbox" hidden="true"> <input type="checkbox" style="position: inherit" name="torneo" value="true"> <spring:message
				code="label.caratteristiche.torneo" />
		</label>
	</div>
	<div class="col-md-2 Input" style="height: auto">
		<label class="checkbox checkbox-padre"> <input type="checkbox" style="position: inherit" name="tradizione" value="true"
			data-bindingonload="attivazioneCheckbox" data-event="change"> <spring:message code="label.caratteristiche.tradizione" />
		</label> <label class="checkbox" hidden="true"> <input type="checkbox" style="position: inherit" name="corteo" value="true"> <spring:message
				code="label.caratteristiche.corteo" />
		</label> <label class="checkbox" hidden="true"> <input type="checkbox" style="position: inherit" name="palio" value="true"> <spring:message
				code="label.caratteristiche.palio" />
		</label> <label class="checkbox" hidden="true"> <input type="checkbox" style="position: inherit" name="pellegrinaggio" value="true"> <spring:message
				code="label.caratteristiche.pellegrinaggio" />
		</label> <label class="checkbox" hidden="true"> <input type="checkbox" style="position: inherit" name="processione" value="true"> <spring:message
				code="label.caratteristiche.processione" />
		</label> <label class="checkbox" hidden="true"> <input type="checkbox" style="position: inherit" name="sfilata" value="true"> <spring:message
				code="label.caratteristiche.sfilata" />
		</label>
	</div>
	<div class="col-md-2 Input" style="height: auto">
		<label class="checkbox checkbox-padre"> <input type="checkbox" style="position: inherit" name="arte" value="true"
			data-bindingonload="attivazioneCheckbox" data-event="change"> <spring:message code="label.caratteristiche.arte" />
		</label> <label class="checkbox" hidden="true"> <input type="checkbox" style="position: inherit" name="festivalA" value="true"> <spring:message
				code="label.caratteristiche.festival" />
		</label> <label class="checkbox" hidden="true"> <input type="checkbox" style="position: inherit" name="mostra" value="true"> <spring:message
				code="label.caratteristiche.mostra" />
		</label> <label class="checkbox" hidden="true"> <input type="checkbox" style="position: inherit" name="presentazione" value="true"> <spring:message
				code="label.caratteristiche.presentazione" />
		</label>
	</div>
	<div class="col-md-2 Input" style="height: auto">
		<label class="checkbox checkbox-padre"> <input type="checkbox" style="position: inherit" name="mare" value="true"> <spring:message
				code="label.caratteristiche.mare" />
		</label>
	</div>
</div>