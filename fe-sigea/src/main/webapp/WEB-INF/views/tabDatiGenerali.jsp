<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<div class="row" style="margin-top: 10px; margin-right: 0px; margin-left: 0px">
	<div class="tab-content" id="myTabContent1" style="padding-top: 5px">
		<input type="hidden" name="eventoId" id="idEventoPrincipale" readonly> <input type="hidden" id="attivitaId" data-ignorefield readonly>
		<input type="hidden" id="richiestaAttivitaId" data-ignorefield readonly> <input type="hidden" id="ultimeNote" name="ultimeNote" readonly>
		<div class="row">
			<div class="col-md-4 col-sm-5 Input" style="margin-bottom: 20px">
				<div class="scegliere" style="left: 10px;">Tipo di evento *</div>
				<div class="col-md-6 col-6 Input validvalue" style="margin-bottom: 10px; height: auto">
					<input type="radio" name="tipo" value="EVENTO" class="radio-ruolo option-input inlist radio" checked>
					<spring:message code="label.tipologia.eventosingolo" />
				</div>
				<div class="col-md-6 col-6 Input validvalue" style="margin-bottom: 10px; height: auto">
					<input type="radio" name="tipo" value="RASSEGNA" class="radio-ruolo option-input inlist radio">
					<spring:message code="label.tipologia.rassegna" />
				</div>
			</div>
			<div class="col-md-4 col-sm-5 Input" style="margin-bottom: 20px">
				<input type="hidden" name="idMIBACT" readonly>
				<div class="boxInput">
					<select class="form-control Input-text" name="tipologiaMIBACT" id="tipologiaMIBACT"></select>
					<div class="scegliere">
						<spring:message code="label.mibact.required" />
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-md-2 col-sm-3 Input" style="margin-bottom: 10px; height: auto; display: none">
				<label class="checkbox"> <input type="checkbox" style="position: inherit" name="grandeEvento" value="true"> <spring:message
						code="label.grandeevento" />
				</label>
			</div>
		</div>
	</div>
</div>
<div class="row">
	<div class="Input" id="boxmobileLinguaG" style="display: none; padding-bottom: 15px;">
		<div class="mobile-input">
			<select id="mobiletabLingua" class="form-control Input-text">
				<option data-target="#itbox">IT</option>
				<option data-target="#enbox">EN</option>
				<option data-target="#spbox">SP</option>
				<option data-target="#frbox">FR</option>
				<option data-target="#debox">DE</option>
				<option data-target="#rubox">RU</option>
			</select>
			<div class="scegliere">Cambia lingua</div>
		</div>
	</div>
</div>
<ul class="nav nav-tabs" id="myTabDG" role="tablist" style="margin-right: 0px; margin-left: 0px; margin-bottom: 15px">
	<li class="nav-item active" style="cursor: pointer" id="it"><a href="#itbox" role="tab" data-toggle="tab" class="nav-link active" id="it-tab"
		style="cursor: pointer">IT</a></li>
	<li class="nav-item" style="cursor: pointer" id="en"><a href="#enbox" role="tab" data-toggle="tab" class="nav-link" id="en-tab"
		style="cursor: pointer">EN</a></li>
	<li class="nav-item" style="cursor: pointer" id="sp"><a href="#spbox" role="tab" data-toggle="tab" class="nav-link" id="sp-tab"
		style="cursor: pointer">SP</a></li>
	<li class="nav-item" style="cursor: pointer" id="fr"><a href="#frbox" role="tab" data-toggle="tab" class="nav-link" id="fr-tab"
		style="cursor: pointer">FR</a></li>
	<li class="nav-item" style="cursor: pointer" id="de"><a href="#debox" role="tab" data-toggle="tab" class="nav-link" id="de-tab"
		style="cursor: pointer">DE</a></li>
	<li class="nav-item" style="cursor: pointer" id="ru"><a href="#rubox" role="tab" data-toggle="tab" class="nav-link" id="ru-tab"
		style="cursor: pointer">RU</a></li>
</ul>
<input type="hidden" name="datiGenerali.eventoId" readonly>
<div class="row">
	<div class="tab-content" id="myTabDGContent" style="margin: 0px -15px;">
		<div class="tab-pane fade active in" id="itbox">
			<div class="col-md-12 Input" style="margin-bottom: 10px">
				<spring:message code="placeholder.datigenerali.titolo" var="phTitolo" />
				<div class="boxInput" style="margin: 15px 15px 20px 15px;" id="titoloitalia">
					<input type="text" class="form-control Input-text" name="datiGenerali.titoloMulti.IT" id="it_titolo" maxlength="300" placeholder="Aggiungi un testo con massimo 300 caratteri" />
					<div class="scegliere">
						<spring:message code="label.datigenerali.titolo" />
					</div>	
				</div>				
			</div>
			<div class="col-md-12 Input" style="margin-bottom: 10px;">
				<spring:message code="placeholder.datigenerali.abstract" var="phAbstract" />
				<div class="boxInput" style="margin: 15px 15px 20px 15px;">	
					<textarea class="form-control Input-text resize" style="min-height: 75px; max-height: 200px" name="datiGenerali.abstractDescrMulti.IT"
						id="it_abstract" style="height: 75px" maxlength="300" placeholder="${phAbstract}"></textarea>
					<div class="scegliere" style="bottom: 110% !important;">
						<spring:message code="label.datigenerali.abstract" />
					</div>
				</div>
			</div>
			<div class="col-md-12 Input" style="margin-bottom: 10px; display: none">
				<spring:message code="placeholder.datigenerali.snippets" var="phSnippets" />
				<div class="boxInput" style="margin: 15px 15px 20px 15px;">
					<textarea class="form-control Input-text resize" style="min-height: 75px; max-height: 200px" name="datiGenerali.snippetMulti.IT" id="it_snippets"
						maxlength="350" placeholder="${phSnippets}"></textarea>
					<div class="scegliere" style="bottom: 110% !important;">
						<spring:message code="label.datigenerali.snippets" />
					</div>
				</div>
			</div>
			<div class="col-md-12 Input" style="margin-bottom: 10px;">
				<spring:message code="placeholder.datigenerali.descrizione" var="phDescrizioneDG" />
				<div class="boxInput" style="margin: 15px 15px 20px 15px;">
					<textarea class="form-control Input-text resize" style="min-height: 150px; max-height: 200px; height: 120px" name="datiGenerali.descrizioneMulti.IT"
						id="it_descrizione" maxlength="4000" placeholder="${phDescrizioneDG}"></textarea>
					<div class="scegliere" style="bottom: 110% !important;">
						<spring:message code="label.datigenerali.descrizione" />
					</div>
				</div>
			</div>
		</div>
		<div class="tab-pane fade" id="enbox">
			<div class="col-md-12 Input" style="margin-bottom: 10px">
				<spring:message code="placeholder.datigenerali.titolo" var="phTitolo" />
				<div class="boxInput" style="margin: 15px 15px 20px 15px;">
					<input type="text" class="form-control Input-text" name="datiGenerali.titoloMulti.EN" id="en_titolo" maxlength="300" placeholder="Aggiungi un testo con massimo 300 caratteri" />
					<div class="scegliere">
						<spring:message code="datatable.evento" />
					</div>
				</div>
			</div>
			<div class="col-md-12 Input" style="margin-bottom: 10px;">
				<spring:message code="placeholder.datigenerali.abstract" var="phAbstract" />
				<div class="boxInput" style="margin: 15px 15px 20px 15px;">
					<textarea class="form-control Input-text resize" style="min-height: 75px; max-height: 200px; height: 75px" name="datiGenerali.abstractDescrMulti.EN"
						id="en_abstract" maxlength="300" placeholder="${phAbstract}"></textarea>
					<div class="scegliere" style="bottom: 110% !important;">
						<spring:message code="label.abstract" />
					</div>
				</div>
			</div>
			<div class="col-md-12 Input" style="margin-bottom: 10px; display: none">
				<spring:message code="placeholder.datigenerali.titolo" var="phSnippets" />
				<div class="boxInput" style="margin: 15px 15px 20px 15px;">
					<textarea class="form-control Input-text resize" style="min-height: 75px; max-height: 200px" name="datiGenerali.snippetMulti.EN" id="en_snippets"
						maxlength="350" placeholder="${phSnippets}"></textarea>
					<div class="scegliere">
						<spring:message code="label.datigenerali.snippets" />
					</div>
				</div>
			</div>
			<div class="col-md-12 Input" style="margin-bottom: 10px;">
				<spring:message code="placeholder.datigenerali.descrizione" var="phDescrizioneDG" />
				<div class="boxInput" style="margin: 15px 15px 20px 15px;">
					<textarea class="form-control Input-text resize" style="min-height: 150px; max-height: 200px; height: 120px" name="datiGenerali.descrizioneMulti.EN"
						id="en_descrizione" maxlength="4000" placeholder="${phDescrizioneDG}"></textarea>
					<div class="scegliere">
						<spring:message code="label.descrizione" />
					</div>
				</div>
			</div>
		</div>
		<div class="tab-pane fade" id="spbox">
			<div class="col-md-12 Input" style="margin-bottom: 10px">
				<spring:message code="placeholder.datigenerali.titolo" var="phTitolo" />
				<div class="boxInput" style="margin: 15px 15px 20px 15px;">
					<input type="text" class="form-control Input-text resize" name="datiGenerali.titoloMulti.SP" id="sp_titolo" maxlength="300" placeholder="Aggiungi un testo con massimo 300 caratteri" />
					<div class="scegliere">
						<spring:message code="datatable.evento" />
					</div>
				</div>
			</div>
			<div class="col-md-12 Input" style="margin-bottom: 10px;">
				<spring:message code="placeholder.datigenerali.abstract" var="phAbstract" />
				<div class="boxInput" style="margin: 15px 15px 20px 15px;">
					<textarea class="form-control Input-text resize" style="min-height: 75px; max-height: 200px; height: 75px" name="datiGenerali.abstractDescrMulti.SP"
						id="sp_abstract"  maxlength="300" placeholder="${phAbstract}"></textarea>
					<div class="scegliere">
						<spring:message code="label.abstract" />
					</div>
				</div>
			</div>
			<div class="col-md-12 Input" style="margin-bottom: 10px; display: none">
				<spring:message code="placeholder.datigenerali.titolo" var="phSnippets" />
				<div class="boxInput" style="margin: 15px 15px 20px 15px;">
					<textarea class="form-control Input-text resize" style="min-height: 75px; max-height: 200px" name="datiGenerali.snippetMulti.SP" id="sp_snippets"
						maxlength="350" placeholder="${phSnippets}"></textarea>
					<div class="scegliere">
						<spring:message code="label.datigenerali.snippets" />
					</div>
				</div>
			</div>
			<div class="col-md-12 Input" style="margin-bottom: 10px;">
				<spring:message code="placeholder.datigenerali.descrizione" var="phDescrizioneDG" />
				<div class="boxInput" style="margin: 15px 15px 20px 15px;">
					<textarea class="form-control Input-text resize" style="min-height: 150px; max-height: 200px; height: 120px" name="datiGenerali.descrizioneMulti.SP"
						id="sp_descrizione" maxlength="4000" placeholder="${phDescrizioneDG}"></textarea>
					<div class="scegliere">
						<spring:message code="label.descrizione" />
					</div>
				</div>
			</div>
		</div>
		<div class="tab-pane fade" id="frbox">
			<div class="col-md-12 Input" style="margin-bottom: 10px">
				<spring:message code="placeholder.datigenerali.titolo" var="phTitolo" />
				<div class="boxInput" style="margin: 15px 15px 20px 15px;">
					<input type="text" class="form-control Input-text resize" name="datiGenerali.titoloMulti.FR" id="fr_titolo" maxlength="300"	placeholder="Aggiungi un testo con massimo 300 caratteri" />
					<div class="scegliere">
						<spring:message code="datatable.evento" />
					</div>
				</div>
			</div>
			<div class="col-md-12 Input" style="margin-bottom: 10px;">
				<spring:message code="placeholder.datigenerali.abstract" var="phAbstract" />
				<div class="boxInput" style="margin: 15px 15px 20px 15px;">
					<textarea class="form-control Input-text resize" style="min-height: 75px; max-height: 200px;height: 75px" name="datiGenerali.abstractDescrMulti.FR"
						id="fr_abstract" maxlength="300" placeholder="${phAbstract}"></textarea>
					<div class="scegliere">
						<spring:message code="label.abstract" />
					</div>
				</div>
			</div>
			<div class="col-md-12 Input" style="margin-bottom: 10px; display: none">
				<spring:message code="placeholder.datigenerali.titolo" var="phSnippets" />
				<div class="boxInput" style="margin: 15px 15px 20px 15px;">
					<textarea class="form-control Input-text resize" style="min-height: 75px; max-height: 200px" name="datiGenerali.snippetMulti.FR" id="fr_snippets"
						maxlength="350" placeholder="${phSnippets}"></textarea>
					<div class="scegliere">
						<spring:message code="label.datigenerali.snippets" />
					</div>
				</div>
			</div>
			<div class="col-md-12 Input" style="margin-bottom: 10px;">
				<spring:message code="placeholder.datigenerali.descrizione" var="phDescrizioneDG" />
				<div class="boxInput" style="margin: 15px 15px 20px 15px;">
					<textarea class="form-control Input-text resize" style="min-height: 150px; max-height: 200px;height: 120px" name="datiGenerali.descrizioneMulti.FR"
						id="fr_descrizione" maxlength="4000" placeholder="${phDescrizioneDG}"></textarea>
					<div class="scegliere">
						<spring:message code="label.descrizione" />
					</div>
				</div>
			</div>
		</div>
		<div class="tab-pane fade" id="debox">
			<div class="col-md-12 Input" style="margin-bottom: 10px;">
				<spring:message code="placeholder.datigenerali.titolo" var="phTitolo" />
				<div class="boxInput" style="margin: 15px 15px 20px 15px;">
					<input type="text" class="form-control Input-text" name="datiGenerali.titoloMulti.DE" id="de_titolo" maxlength="300" placeholder="Aggiungi un testo con massimo 300 caratteri" />
					<div class="scegliere">
						<spring:message code="datatable.evento" />
					</div>
				</div>
			</div>
			<div class="col-md-12 Input" style="margin-bottom: 10px;">
				<spring:message code="placeholder.datigenerali.abstract" var="phAbstract" />
				<div class="boxInput" style="margin: 15px 15px 20px 15px;">
					<textarea class="form-control Input-text resize" style="min-height: 75px; max-height: 200px;height: 75px" name="datiGenerali.abstractDescrMulti.DE"
						id="de_abstract" maxlength="300" placeholder="${phAbstract}"></textarea>
					<div class="scegliere">
						<spring:message code="label.abstract" />
					</div>
				</div>
			</div>
			<div class="col-md-12 Input" style="margin-bottom: 10px; display: none">
				<spring:message code="placeholder.datigenerali.titolo" var="phSnippets" />
				<div class="boxInput" style="margin: 15px 15px 20px 15px;">
					<textarea class="form-control Input-text resize" style="min-height: 75px; max-height: 200px" name="datiGenerali.snippetMulti.DE" id="de_snippets"
						maxlength="350" placeholder="${phSnippets}"></textarea>
					<div class="scegliere">
						<spring:message code="label.datigenerali.snippets" />
					</div>
				</div>
			</div>
			<div class="col-md-12 Input" style="margin-bottom: 10px;">
				<spring:message code="placeholder.datigenerali.descrizione" var="phDescrizioneDG" />
				<div class="boxInput" style="margin: 15px 15px 20px 15px;">
					<textarea class="form-control Input-text resize" style="min-height: 150px; max-height: 200px; height: 120px" name="datiGenerali.descrizioneMulti.DE"
						id="de_descrizione" maxlength="4000" placeholder="${phDescrizioneDG}"></textarea>
					<div class="scegliere">
						<spring:message code="label.descrizione" />
					</div>
				</div>
			</div>
		</div>
		<div class="tab-pane fade" id="rubox">
			<div class="col-md-12 Input" style="margin-bottom: 10px">
				<spring:message code="placeholder.datigenerali.titolo" var="phTitolo" />
				<div class="boxInput" style="margin: 15px 15px 20px 15px;">
					<input type="text" class="form-control Input-text" name="datiGenerali.titoloMulti.RU" id="ru_titolo" maxlength="300" placeholder="Aggiungi un testo con massimo 300 caratteri" />
					<div class="scegliere">
						<spring:message code="datatable.evento" />
					</div>
				</div>
			</div>
			<div class="col-md-12 Input" style="margin-bottom: 10px">
				<spring:message code="placeholder.datigenerali.abstract" var="phAbstract" />
				<div class="boxInput" style="margin: 15px 15px 20px 15px;">
					<textarea class="form-control Input-text resize" style="min-height: 75px; max-height: 200px;height: 75px" name="datiGenerali.abstractDescrMulti.RU"
						id="ru_abstract" maxlength="300" placeholder="${phAbstract}"></textarea>
					<div class="scegliere">
						<spring:message code="label.abstract" />
					</div>
				</div>
			</div>
			<div class="col-md-12 Input" style="margin-bottom: 10px; display: none">
				<spring:message code="placeholder.datigenerali.titolo" var="phSnippets" />
				<div class="boxInput" style="margin: 15px 15px 20px 15px;">
					<textarea class="form-control Input-text resize" style="min-height: 75px; max-height: 200px" name="datiGenerali.snippetMulti.RU" id="ru_snippets"
						maxlength="350" placeholder="${phSnippets}"></textarea>
					<div class="scegliere">
						<spring:message code="label.datigenerali.snippets" />
					</div>
				</div>
			</div>
			<div class="col-md-12 Input" style="margin-bottom: 10px">
				<spring:message code="placeholder.datigenerali.descrizione" var="phDescrizioneDG" />
				<div class="boxInput" style="margin: 15px 15px 20px 15px;">
					<textarea class="form-control Input-text resize" style="min-height: 150px; max-height: 200px;height: 120px" name="datiGenerali.descrizioneMulti.RU"
						id="ru_descrizione" maxlength="4000" placeholder="${phDescrizioneDG}"></textarea>
					<div class="scegliere">
						<spring:message code="label.descrizione" />
					</div>
				</div>
			</div>
		</div>
	</div>
</div>