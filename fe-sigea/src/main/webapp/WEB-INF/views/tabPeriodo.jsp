<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<div class="tab-content" id="myTabContent5" style="padding-top: 5px">
	<div style="margin-left: 0px;">
		<div class="col-md-12 Input" style="padding-top: 0px; text-align: right;">
			<button type="button" class="btn btn-primary invia" data-bindingonload data-addbutton="periodoSet" data-counter="-1" data-ph="_PH_"
				onClick="reloadtime();" style="float: left; margin-left: 0px; margin-right: 0px; margin-bottom: 40px; margin-top: 0px;">
				<i class="dms-wk-icon-add" aria-hidden="true"></i>
				<spring:message code="label.periodo.aggiungiperiodo" />
			</button>
		</div>
	</div>
	<div class="contaperiodo" data-dynamic="periodoSet">
		<div hidden="true" data-template="periodoSet_PH_" style="padding-top: 30px; padding-bottom: 45px; border-bottom: 1px solid #cecece;">
			<div class="row" style="margin-left: 0px; margin-bottom: 20px;">
				<div class="col-md-4 Input" style="padding-top: 5px">
					<div class="col-md-6 col-sm-6 col-6 Input validvalue">
						<input type="radio" class="radio-ruolo option-input inlist radio" name="periodoSet_PH_.dataSecca" value="true" checked
							data-dynabindingonload="clickRadioDatasecca_PH_" data-event="change">
						<spring:message code="label.periodo.datasecca" />
						<div class="scegliere">Data evento</div>
					</div>
					<div class="col-md-6 col-6 Input validvalue">
						<input type="radio" class="radio-ruolo option-input inlist radio" name="periodoSet_PH_.dataSecca" value="false"
							data-dynabindingonload="clickRadioDatasecca_PH_" data-event="change">
						<spring:message code="label.periodo.periodo" />
					</div>
				</div>
			</div>
			<div class="row" style="margin-left: 0px; margin-right: 0px; margin-bottom: 20px;">
				<div class="col-md-12" id="labelperiodorosso">
					<spring:message code="label.periodorosso" />
				</div>
				<input type="hidden" name="periodoSet_PH_.periodoId" readonly>
				<div class="col-md-6 Input inputicondata2" style="margin-bottom: 18px">
					<spring:message code="placeholder.data" var="phData" />
					<div class="boxInput">
						<input type="search" autocomplete="off" class="form-control-date Input-text startS-d data" name="periodoSet_PH_.dataDa" placeholder="${phData}"
							data-initialize="inizializeDataDa_PH_" data-dynabindingonload="changeDataDa_PH_" data-event="change">
						<div class="scegliere" data-name="periodoSet_PH_.dataSeccaLable">
							<spring:message code="label.periodo.data" />
						</div>
						<div class="scegliere due" data-name="periodoSet_PH_.dataPeriodoLable" hidden="true">
							<spring:message code="label.datada" />
						</div>
					</div>
				</div>
				<div class="col-md-6 Input inputicondata2" style="margin-bottom: 10px" data-name="periodoSet_PH_.sezPeriodo" hidden="true">
					<spring:message code="placeholder.dataa" var="phData" />
					<div class="boxInput">
						<input type="search" autocomplete="off" class="form-control-date Input-text endS-d data" name="periodoSet_PH_.dataA" placeholder="${phData}"
							data-initialize="inizializeDataA_PH_" data-dynabindingonload="changeDataA_PH_" data-event="change">
						<div class="scegliere">
							<spring:message code="label.dataa" />
						</div>
					</div>
				</div>
			</div>
			<div class="row" style="margin-top: 18px; margin-left: 0px; margin-right: 0px;">
				<div class="col-md-12 Input">
					<div class="col-lg-2 col-md-3 col-sm-3 col-6 Input validvalue">
						<input type="radio" class="radio-ruolo option-input inlist radio" name="periodoSet_PH_.fgContinuato" value="true" checked
							data-dynabindingonload="clickRadioContinuato_PH_" data-event="change">
						<spring:message code="label.periodo.continuato" />
						<div class="scegliere">Orario</div>
					</div>
					<div class="col-lg-2 col-md-3 col-sm-4 col-6 Input validvalue">
						<input type="radio" class="radio-ruolo option-input inlist radio" name="periodoSet_PH_.fgContinuato" value="false"
							data-dynabindingonload="clickRadioContinuato_PH_" data-event="change">
						<spring:message code="label.periodo.noncontinuato" />
					</div>
				</div>
			</div>
			<div class="row" style="margin-top: 18px; margin-left: 0px; margin-right: 0px;">
				<div class="hblock" data-name="periodoSet_PH_.dataOrarioContinuato">
					<div class="col-md-12" id="labelperiodo">
						<spring:message code="label.periodosuccessivo" />
					</div>
					<div class="col-md-3 Input">
						<spring:message code="placeholder.ora" var="phOra" />
						<div class="boxInput appendtime">
							<input type="text" class="form-control-date Input-text startS-d campiora" readonly="readonly" name="periodoSet_PH_.orarioApertura"
								data-dynabindingonload="inizializeOrario_PH_" data-event="click" placeholder="${phOra}">
							<div class="scegliere">
								<spring:message code="label.periodo.orarioapertura" />
							</div>
						</div>
					</div>
					<div class="col-md-3 Input">
						<spring:message code="placeholder.ora" var="phOra" />
						<div class="boxInput appendtime">
							<input type="text" readonly="readonly" class="form-control-date Input-text endS-d toreset" readonly="readonly" name="periodoSet_PH_.orarioChiusura"
								data-dynabindingonload="inizializeOrario_PH_" data-event="click" placeholder="${phOra}">
							<div class="scegliere">
								<spring:message code="label.periodo.orariochiusura" />
							</div>
						</div>
					</div>
					<div class="col-md-3 Input" style="padding-top: 10px;">
						<button type="button" class="btn btn-primary cancella resetline" style="float: left;">Azzera</button>
					</div>
				</div>
				<div class="hblock" data-name="periodoSet_PH_.dataOrarioNonContinuato" hidden="true">
					<div class="col-md-12" id="labelperiodo">
						<spring:message code="label.periodosuccessivo" />
					</div>
					<div class="col-md-3 Input">
						<spring:message code="placeholder.ora" var="phOra" />
						<div class="boxInput appendtime">
							<input type="text" class="form-control-date Input-text startS-d campiora" readonly="readonly" name="periodoSet_PH_.orarioAperturaMattina"
								data-dynabindingonload="inizializeOrario_PH_" data-event="click" placeholder="${phOra}">
							<div class="scegliere">
								<spring:message code="label.periodo.orarioaperturamattina" />
							</div>
						</div>
					</div>

					<div class="col-md-3 Input">
						<spring:message code="placeholder.ora" var="phOra" />
						<div class="boxInput appendtime">
							<input type="text" readonly="readonly" class="form-control-date Input-text endS-d toreset" readonly="readonly"
								name="periodoSet_PH_.orarioChiusuraMattina" data-dynabindingonload="inizializeOrario_PH_" data-event="click" placeholder="${phOra}">
							<!-- onfocus="(this.type='time')" onblur="(this.type='text')" -->
							<div class="scegliere">
								<spring:message code="label.periodo.orariochiusuramattina" />
							</div>
						</div>
					</div>
					<div class="col-md-3 Input">
						<spring:message code="placeholder.ora" var="phOra" />
						<div class="boxInput appendtime">
							<input type="text" class="form-control-date Input-text startS-d toreset" readonly="readonly" name="periodoSet_PH_.orarioAperturaPomeriggio"
								data-dynabindingonload="inizializeOrario_PH_" data-event="click" placeholder="${phOra}">
							<div class="scegliere">
								<spring:message code="label.periodo.orarioaperturapomeriggio" />
							</div>
						</div>
					</div>
					<div class="col-md-3 Input">
						<spring:message code="placeholder.ora" var="phOra" />
						<div class="boxInput appendtime">
							<input type="text" readonly="readonly" class="form-control-date Input-text endS-d toreset" readonly="readonly"
								name="periodoSet_PH_.orarioChiusuraPomeriggio" data-dynabindingonload="inizializeOrario_PH_" data-event="click" placeholder="${phOra}">
							<div class="scegliere">
								<spring:message code="label.periodo.orariochiusurapomeriggio" />
							</div>
						</div>
					</div>
					<div class="col-md-12 Input">
						<div class="boxInput" style="margin-top: 0px;">
							<button type="button" class="btn btn-primary cancella resetline" style="float: right;">Azzera</button>
						</div>
					</div>
				</div>
			</div>
			<div data-name="periodoSet_PH_.cadenzaSuPeriodo" hidden="true">
			
				<div class="row" style="margin-top: 18px; margin-left: 0px; margin-right: 0px;">
					<div class="col-md-6 Input">
						<div class="boxInput">
							<select class="form-control Input-text" name="periodoSet_PH_.cadenza" data-initialize="_PH_selectizeCadenza"></select>
							<div class="scegliere">
								<spring:message code="label.periodo.cadenza" />
							</div>
						</div>
					</div>
				
				<div data-name="periodoSet_PH_.cadenzaMensile" hidden="true">
						<div class="col-md-6 Input multipl">
							<div class="boxInput">
								<select class="form-control Input-text multi" name="periodoSet_PH_.cadenzaMensile[]" multiple="multiple"
									data-initialize="_PH_selectizeCadenzaMensile"></select>
								<div class="scegliere">
									<spring:message code="label.periodo.neigiornimese" />
								</div>
							</div>
						</div>					
				</div>
				</div>
	
				
				
				<div data-name="periodoSet_PH_.cadenzaSettimanale" hidden="true">
					<div class="row">
						<div class="col-md-12 Input" style="height: auto; margin-top: -10px; margin-left: 5px">
							<fieldset style="border: none; padding-bottom: 5px">
								<legend style="border-bottom: none; font-size: 20px; margin-bottom: 0px; padding-top: 5px">
									<spring:message code="label.periodo.neigiorni" />
								</legend>
								<label class="checkbox" style="display: inline-block; margin-right: 10px; width: 105px; margin-bottom: 0px;"> <input type="checkbox"
									style="position: inherit" name="periodoSet_PH_.cadenzaLun" value="true"> <spring:message code="label.periodo.lunedi" />
								</label> <label class="checkbox" style="display: inline-block; margin-right: 10px; width: 105px; margin-bottom: 0px;"> <input type="checkbox"
									style="position: inherit" name="periodoSet_PH_.cadenzaMar" value="true"> <spring:message code="label.periodo.martedi" />
								</label> <label class="checkbox" style="display: inline-block; margin-right: 10px; width: 105px; margin-bottom: 0px;"> <input type="checkbox"
									style="position: inherit" name="periodoSet_PH_.cadenzaMer" value="true"> <spring:message code="label.periodo.mercoledi" />
								</label> <label class="checkbox" style="display: inline-block; margin-right: 10px; width: 105px; margin-bottom: 0px;"> <input type="checkbox"
									style="position: inherit" name="periodoSet_PH_.cadenzaGio" value="true"> <spring:message code="label.periodo.giovedi" />
								</label> <label class="checkbox" style="display: inline-block; margin-right: 10px; width: 105px; margin-bottom: 0px;"> <input type="checkbox"
									style="position: inherit" name="periodoSet_PH_.cadenzaVen" value="true"> <spring:message code="label.periodo.venerdi" />
								</label> <label class="checkbox" style="display: inline-block; margin-right: 10px; width: 105px; margin-bottom: 0px;"> <input type="checkbox"
									style="position: inherit" name="periodoSet_PH_.cadenzaSab" value="true"> <spring:message code="label.periodo.sabato" />
								</label> <label class="checkbox" style="display: inline-block; margin-right: 10px; width: 105px; margin-bottom: 0px;"> <input type="checkbox"
									style="position: inherit" name="periodoSet_PH_.cadenzaDom" value="true"> <spring:message code="label.periodo.domenica" />
								</label>
							</fieldset>
						</div>
					</div>
				</div>				
				
				<div class="row">
					<div class="col-md-12 Input" data-name="periodoSet_PH_.chiusoNeiGiorni" style="height: auto; margin-top: -10px; margin-left: 5px">
						<fieldset style="border: none; padding-bottom: 5px">
							<legend style="border-bottom: none; font-size: 20px; margin-bottom: 0px; padding-top: 5px">
								<spring:message code="label.periodo.chiusuraneigiorni" />
							</legend>
							<label class="checkbox" style="display: inline-block; margin-right: 10px; width: 105px; margin-bottom: 0px;"> <input type="checkbox"
								style="position: inherit" name="periodoSet_PH_.chiusuraLun" value="true"> <spring:message code="label.periodo.lunedi" />
							</label> <label class="checkbox" style="display: inline-block; margin-right: 10px; width: 105px; margin-bottom: 0px;"> <input type="checkbox"
								style="position: inherit" name="periodoSet_PH_.chiusuraMar" value="true"> <spring:message code="label.periodo.martedi" />
							</label> <label class="checkbox" style="display: inline-block; margin-right: 10px; width: 105px; margin-bottom: 0px;"> <input type="checkbox"
								style="position: inherit" name="periodoSet_PH_.chiusuraMer" value="true"> <spring:message code="label.periodo.mercoledi" />
							</label> <label class="checkbox" style="display: inline-block; margin-right: 10px; width: 105px; margin-bottom: 0px;"> <input type="checkbox"
								style="position: inherit" name="periodoSet_PH_.chiusuraGio" value="true"> <spring:message code="label.periodo.giovedi" />
							</label> <label class="checkbox" style="display: inline-block; margin-right: 10px; width: 105px; margin-bottom: 0px;"> <input type="checkbox"
								style="position: inherit" name="periodoSet_PH_.chiusuraVen" value="true"> <spring:message code="label.periodo.venerdi" />
							</label> <label class="checkbox" style="display: inline-block; margin-right: 10px; width: 105px; margin-bottom: 0px;"> <input type="checkbox"
								style="position: inherit" name="periodoSet_PH_.chiusuraSab" value="true"> <spring:message code="label.periodo.sabato" />
							</label> <label class="checkbox" style="display: inline-block; margin-right: 10px; width: 105px; margin-bottom: 0px;"> <input type="checkbox"
								style="position: inherit" name="periodoSet_PH_.chiusuraDom" value="true"> <spring:message code="label.periodo.domenica" />
							</label>
						</fieldset>
					</div>
				</div>

			
			</div>
			<div class="row" style="margin-left: 0px">
				<div class="col-md-12 Input" style="margin-top: 15px;">
					<button type="button" style="float: right; padding-right: 15px !important;" data-deletebutton="periodoSet_PH_" class="removeButton">
						<i class="dms-wk-icon-delete" aria-hidden="true"></i>
						<spring:message code="label.media.elimina" />
					</button>
				</div>
			</div>
		</div>
	</div>
</div>