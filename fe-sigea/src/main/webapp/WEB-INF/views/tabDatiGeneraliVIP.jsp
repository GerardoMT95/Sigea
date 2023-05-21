<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<div class="row" style="padding-left: 15px; padding-right: 15px; padding-top: 5px">
	<div class="row" style="margin-left: 0px; margin-bottom: 45px">
		<div class="col-md-12 Input">
			<div class="scegliere">
				<spring:message code="label.evatt" />
			</div>
			<div class="col-md-2 col-sm-2 Input validvalue">
				<input type="radio" name="tipoScheda" class="radio-ruolo option-input inlist radio" value="evento" checked
					data-bindingonload="clickRadioTipologia" data-event="change">
				<spring:message code="label.tipologia.evento" />
			</div>
			<div class="col-md-2 col-sm-2 Input validvalue">
				<input type="radio" name="tipoScheda" class="radio-ruolo option-input inlist radio" value="attivita" data-bindingonload="clickRadioTipologia"
					data-event="change">
				<spring:message code="label.tipologia.attivita" />
			</div>
		</div>
	</div>
	<div class="row" style="margin-left: 0px">
		<div class="col-md-12 Input">
			<div class="boxInput" style="margin-bottom: 40px; margin-right: 15px !important;">
				<select class="form-control Input-text" name="tipologieMIBACT" id="tipologieMIBACT" multiple="multiple" data-ignorefield></select>
				<div class="scegliere">
					<spring:message code="label.mibact.required" />
				</div>
				<label id="tipologieMIBACT-error" class="error" for="tipologieMIBACT" style="display:none;">* campo obbligatorio</label>
			</div>
		</div>
		<div class="col-md-12 Input multipl" id="tipologiaAttivita" style="display: none; margin-right: 15px !important;">
			<div class="boxInput">
				<select class="form-control Input-text multi" name="tipologieAttivitaSet[]" id="tipologiaVIP" multiple="multiple"></select>
				<div class="scegliere">
					<spring:message code="label.tipologiaO" />
				</div>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="Input" id="boxmobileLinguaVIPG" style="display: none; padding-bottom: 15px; margin-left: 15px; margin-right: 15px;">
			<div class="mobile-input">
				<select id="mobiletabLinguaVIP" class="form-control Input-text">
					<option data-target="#itattVIPbox">IT</option>
					<option data-target="#enattVIPbox">EN</option>
					<option data-target="#spattVIPbox">SP</option>
					<option data-target="#frattVIPbox">FR</option>
					<option data-target="#deattVIPbox">DE</option>
					<option data-target="#ruattVIPbox">RU</option>
				</select>
				<div class="scegliere">Cambia lingua</div>
			</div>
		</div>
	</div>
	<ul class="nav nav-tabs" id="VIPattDG" role="tablist" style="margin-right: 5px; margin-left: 5px; margin-bottom: 15px">
		<li class="nav-item active" style="cursor: pointer" id="itattVIP"><a href="#itattVIPbox" role="tab" data-toggle="tab" class="nav-link"
			id="itattVIP-tab" style="cursor: pointer">IT</a></li>
		<li class="nav-item" style="cursor: pointer" id="enattVIP"><a href="#enattVIPbox" role="tab" data-toggle="tab" class="nav-link"
			id="enattVIP-tab" style="cursor: pointer">EN</a></li>
		<li class="nav-item" style="cursor: pointer" id="spattVIP"><a href="#spattVIPbox" role="tab" data-toggle="tab" class="nav-link"
			id="spattVIP-tab" style="cursor: pointer">SP</a></li>
		<li class="nav-item" style="cursor: pointer" id="frattVIP"><a href="#frattVIPbox" role="tab" data-toggle="tab" class="nav-link"
			id="frattVIP-tab" style="cursor: pointer">FR</a></li>
		<li class="nav-item" style="cursor: pointer" id="deattVIP"><a href="#deattVIPbox" role="tab" data-toggle="tab" class="nav-link"
			id="deattVIP-tab" style="cursor: pointer">DE</a></li>
		<li class="nav-item" style="cursor: pointer" id="ruattVIP"><a href="#ruattVIPbox" role="tab" data-toggle="tab" class="nav-link"
			id="ruattVIP-tab" style="cursor: pointer">RU</a></li>
	</ul>
	<div class="tab-content" id="VIPattDGContent" style="margin: 0px -15px;">
		<div class="tab-pane fade active in" id="itattVIPbox">
			<div class="col-md-12" style="margin-bottom: 20px; padding: 0px;">
				<div class="boxInput" style="margin: 15px 15px 0px 15px; min-height:156px;">
					<textarea class="form-control Input-text resize" name="titoloMulti.IT" id="itattVIP_titolo"  data-maxlengthtml="500" data-maxlength="350" placeholder="Aggiungi un testo con massimo 350 caratteri"></textarea>
					<div class="scegliere" style="top: -20px">
						<spring:message code="label.datigenerali.titolo" />
					</div>
					<label id="itattVIP_titolo-error" class="errorV" for="itattVIP_titolo" style="display:none;">* campo obbligatorio</label>
				</div>
			</div>
			<div class="col-md-12" style="margin-bottom: 20px; padding: 0px;">
				<div class="boxInput" style="margin: 15px 15px 0px 15px; min-height:156px;">
				<textarea class="form-control Input-text resize" name="abstractDescrMulti.IT" id="itattVIP_abstract"  data-maxlengthtml="1500" data-maxlength="350" placeholder="Aggiungi un testo con massimo 350 caratteri"></textarea>
					<div class="scegliere" style="top: -20px">
						<spring:message code="label.datigenerali.abstract" />
					</div>
					<label id="itattVIP_abstract-error" class="errorV" for="itattVIP_abstract" style="display:none;">* campo obbligatorio</label>
				</div>
			</div>
			<div class="col-md-12" style="margin-bottom: 20px; padding: 0px;">
				<div class="boxInput" style="margin: 15px 15px 0px 15px; min-height:156px;">
					<spring:message code="placeholder.datigenerali.snippets" var="phSnippets" />
					<textarea class="form-control Input-text resize" name="snippetMulti.IT" id="it_snippets"  data-maxlengthtml="4000" data-maxlength="350" placeholder="Aggiungi un testo con massimo 350 caratteri"></textarea>
					<div class="scegliere" style="top: -20px">
						<spring:message code="label.datigenerali.snippets" />
					</div>	
				</div>
			</div>
			<div class="col-md-12" style="margin-bottom: 20px; padding: 0px;">
				<div class="boxInput" style="margin: 15px 15px 0px 15px; min-height:230px;">
					<spring:message code="placeholder.datigenerali.descrizione" var="phDescrizioneDG" />
					<textarea class="form-control Input-text resize" name="descrizioneMulti.IT" id="itattVIP_descrizione"  data-maxlengthtml="8000" data-maxlength="4000" placeholder="Aggiungi un testo con massimo 4000 caratteri"></textarea>
					<div class="scegliere" style="top: -20px">
						<spring:message code="label.datigenerali.descrizione" />
					</div>
					<label id="itattVIP_descrizione-error" class="errorV" for="itattVIP_descrizione" style="display:none;">* campo obbligatorio</label>		
				</div>
			</div>
		</div>
		<div class="tab-pane fade" id="enattVIPbox">
			<div class="col-md-12" style="margin-bottom: 20px; padding: 0px;">
				<div class="boxInput" style="margin: 15px 15px 0px 15px; min-height:156px;">
					<textarea class="form-control Input-text resize" name="titoloMulti.EN" id="enattVIP_titolo"  data-maxlengthtml="500" data-maxlength="350" placeholder="Aggiungi un testo con massimo 350 caratteri"></textarea>
					<div class="scegliere" style="top: -20px">
						<spring:message code="label.titoloevento" />
					</div>
				</div>
			</div>
			<div class="col-md-12" style="margin-bottom: 20px; padding: 0px;">
				<div class="boxInput" style="margin: 15px 15px 0px 15px; min-height:156px;">
					<textarea class="form-control Input-text resize" name="abstractDescrMulti.EN" id="enattVIP_abstract"  data-maxlengthtml="1500" data-maxlength="350" placeholder="Aggiungi un testo con massimo 350 caratteri"></textarea>
					<div class="scegliere" style="top: -20px">
						<spring:message code="label.abstract" />
					</div>
				</div>
			</div>
			<div class="col-md-12" style="margin-bottom: 20px; padding: 0px;">
				<div class="boxInput" style="margin: 15px 15px 0px 15px; min-height:156px;">
					<textarea class="form-control Input-text resize" name="snippetMulti.EN" id="enattVIP_snippets"  data-maxlengthtml="4000" data-maxlength="350" placeholder="Aggiungi un testo con massimo 350 caratteri"></textarea>
					<div class="scegliere" style="top: -20px">
						<spring:message code="label.datigenerali.snippets" />
					</div>
				</div>
			</div>
			<div class="col-md-12" style="margin-bottom: 20px; padding: 0px;">
				<div class="boxInput" style="margin: 15px 15px 0px 15px; min-height:230px;">
					<textarea class="form-control Input-text resize" name="descrizioneMulti.EN" id="enattVIP_descrizione"  data-maxlengthtml="8000" data-maxlength="4000" placeholder="Aggiungi un testo con massimo 4000 caratteri"></textarea>
					<div class="scegliere" style="top: -20px">
						<spring:message code="label.descrizione" />
					</div>
				</div>
			</div>
		</div>
		<div class="tab-pane fade" id="spattVIPbox">
			<div class="col-md-12" style="margin-bottom: 20px; padding: 0px;">
				<div class="boxInput" style="margin: 15px 15px 0px 15px; min-height:156px;">
					<textarea class="form-control Input-text resize" name="titoloMulti.SP" id="spattVIP_titolo"  data-maxlengthtml="500" data-maxlength="350" placeholder="Aggiungi un testo con massimo 350 caratteri"></textarea>
					<div class="scegliere" style="top: -20px">
						<spring:message code="label.titoloevento" />
					</div>
				</div>
			</div>
			<div class="col-md-12" style="margin-bottom: 20px; padding: 0px;">
				<div class="boxInput" style="margin: 15px 15px 0px 15px; min-height:156px;">
					<textarea name="abstractDescrMulti.SP" id="spattVIP_abstract" data-maxlengthtml="1500" data-maxlength="350" placeholder="Aggiungi un testo con massimo 350 caratteri"></textarea>
					<div class="scegliere" style="top: -20px">
						<spring:message code="label.abstract" />
					</div>
				</div>
			</div>
			<div class="col-md-12" style="margin-bottom: 20px; padding: 0px;">
				<div class="boxInput" style="margin: 15px 15px 0px 15px; min-height:156px;">
					<textarea class="form-control Input-text resize" name="snippetMulti.SP" id="spattVIP_snippets" data-maxlengthtml="4000" data-maxlength="350" placeholder="Aggiungi un testo con massimo 350 caratteri"></textarea>
					<div class="scegliere" style="top: -20px">
						<spring:message code="label.datigenerali.snippets" />
					</div>
				</div>
			</div>
			<div class="col-md-12" style="margin-bottom: 20px; padding: 0px;">
				<div class="boxInput" style="margin: 15px 15px 0px 15px; min-height:230px;">
					<textarea class="form-control Input-text resize" name="descrizioneMulti.SP" id="spattVIP_descrizione" data-maxlengthtml="8000" data-maxlength="4000" placeholder="Aggiungi un testo con massimo 4000 caratteri"></textarea>
					<div class="scegliere" style="top: -20px">
						<spring:message code="label.descrizione" />
					</div>
				</div>
			</div>
		</div>
		<div class="tab-pane fade" id="frattVIPbox">
			<div class="col-md-12" style="margin-bottom: 20px; padding: 0px;">
				<div class="boxInput" style="margin: 15px 15px 0px 15px; min-height:156px;">
					<textarea class="form-control Input-text resize" name="titoloMulti.FR" id="frattVIP_titolo" data-maxlengthtml="500" data-maxlength="350" placeholder="Aggiungi un testo con massimo 350 caratteri"></textarea>
					<div class="scegliere" style="top: -20px">
						<spring:message code="label.titoloevento" />
					</div>
				</div>
			</div>
			<div class="col-md-12" style="margin-bottom: 20px; padding: 0px;">
				<div class="boxInput" style="margin: 15px 15px 0px 15px; min-height:156px;">
					<textarea class="form-control Input-text resize" name="abstractDescrMulti.FR" id="frattVIP_abstract" data-maxlengthtml="1500" data-maxlength="350" placeholder="Aggiungi un testo con massimo 350 caratteri"></textarea>
					<div class="scegliere" style="top: -20px">
						<spring:message code="label.abstract" />
					</div>
				</div>
			</div>
			<div class="col-md-12" style="margin-bottom: 20px; padding: 0px;">
				<div class="boxInput" style="margin: 15px 15px 0px 15px; min-height:156px;">
					<textarea class="form-control Input-text resize" name="snippetMulti.FR" id="frattVIP_snippets" data-maxlengthtml="4000" data-maxlength="350" placeholder="Aggiungi un testo con massimo 350 caratteri"></textarea>
					<div class="scegliere" style="top: -20px">
						<spring:message code="label.datigenerali.snippets" />
					</div>
				</div>
			</div>
			<div class="col-md-12" style="margin-bottom: 20px; padding: 0px;">
				<div class="boxInput" style="margin: 15px 15px 0px 15px; min-height:230px;">
					<textarea class="form-control Input-text resize" name="descrizioneMulti.FR" id="frattVIP_descrizione"  data-maxlengthtml="8000" data-maxlength="4000"  placeholder="Aggiungi un testo con massimo 4000 caratteri"></textarea>
					<div class="scegliere" style="top: -20px">
						<spring:message code="label.descrizione" />
					</div>
				</div>
			</div>
		</div>

		<div class="tab-pane fade" id="deattVIPbox">
			<div class="col-md-12" style="margin-bottom: 20px; padding: 0px;">
				<div class="boxInput" style="margin: 15px 15px 0px 15px; min-height:156px;">
					<textarea class="form-control Input-text resize" name="titoloMulti.DE" id="deattVIP_titolo"  data-maxlengthtml="500" data-maxlength="350" placeholder="Aggiungi un testo con massimo 350 caratteri"></textarea>
					<div class="scegliere" style="top: -20px">
						<spring:message code="label.titoloevento" />
					</div>
				</div>
			</div>
			<div class="col-md-12" style="margin-bottom: 20px; padding: 0px;">
				<div class="boxInput" style="margin: 15px 15px 0px 15px; min-height:156px;">
					<textarea name="abstractDescrMulti.DE" id="deattVIP_abstract" data-maxlengthtml="1500" data-maxlength="350" placeholder="Aggiungi un testo con massimo 350 caratteri"></textarea>
					<div class="scegliere" style="top: -20px">
						<spring:message code="label.abstract" />
					</div>
				</div>
			</div>
			<div class="col-md-12" style="margin-bottom: 20px; padding: 0px;">
				<div class="boxInput" style="margin: 15px 15px 0px 15px; min-height:156px;">
					<textarea class="form-control Input-text resize" name="snippetMulti.DE" data-maxlengthtml="4000" data-maxlength="350" id="deattVIP_snippets" placeholder="Aggiungi un testo con massimo 350 caratteri"></textarea>
					<div class="scegliere" style="top: -20px">
						<spring:message code="label.datigenerali.snippets" />
					</div>
				</div>
			</div>
			<div class="col-md-12" style="margin-bottom: 20px; padding: 0px;">
				<div class="boxInput" style="margin: 15px 15px 0px 15px; min-height:230px;">
					<textarea class="form-control Input-text resize" name="descrizioneMulti.DE" id="deattVIP_descrizione" data-maxlengthtml="8000" data-maxlength="4000" placeholder="Aggiungi un testo con massimo 4000 caratteri"></textarea>
					<div class="scegliere" style="top: -20px">
						<spring:message code="label.descrizione" />
					</div>
				</div>
			</div>
		</div>
		<div class="tab-pane fade" id="ruattVIPbox">
			<div class="col-md-12" style="margin-bottom: 20px; padding: 0px;">
				<div class="boxInput" style="margin: 15px 15px 0px 15px; min-height:156px;">
					<textarea name="titoloMulti.RU" id="ruattVIP_titolo"  data-maxlengthtml="500" data-maxlength="350" placeholder="Aggiungi un testo con massimo 350 caratteri"></textarea>
					<div class="scegliere" style="top: -20px">
						<spring:message code="label.titoloevento" />
					</div>
				</div>
			</div>
			<div class="col-md-12" style="margin-bottom: 20px; padding: 0px;">
				<div class="boxInput" style="margin: 15px 15px 0px 15px; min-height:156px;">
					<textarea class="form-control Input-text resize" name="abstractDescrMulti.RU" id="ruattVIP_abstract" data-maxlengthtml="1500" data-maxlength="350" placeholder="Aggiungi un testo con massimo 350 caratteri"></textarea>
					<div class="scegliere" style="top: -20px">
						<spring:message code="label.abstract" />
					</div>
				</div>
			</div>
			<div class="col-md-12" style="margin-bottom: 20px; padding: 0px;">
				<div class="boxInput" style="margin: 15px 15px 0px 15px; min-height:156px;">
					<textarea class="form-control Input-text resize" name="snippetMulti.RU"  data-maxlengthtml="4000" data-maxlength="350" id="ruattVIP_snippets" placeholder="Aggiungi un testo con massimo 350 caratteri" ></textarea>
					<div class="scegliere" style="top: -20px">
						<spring:message code="label.datigenerali.snippets" />
					</div>
				</div>
			</div>
			<div class="col-md-12" style="margin-bottom: 20px; padding: 0px;">
				<div class="boxInput" style="margin: 15px 15px 0px 15px; min-height:230px;">
					<textarea class="form-control Input-text resize" name="descrizioneMulti.RU" id="ruattVIP_descrizione" data-maxlengthtml="8000" data-maxlength="4000" placeholder="Aggiungi un testo con massimo 4000 caratteri"></textarea>
					<div class="scegliere" style="top: -20px">
						<spring:message code="label.descrizione" />
					</div>
				</div>
			</div>
		</div>
	</div>
</div>