<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<button type="button"
	style="color:#000; font-weight: 600; font-size: 16px;  opacity: 1; background-image: url('${pageContext.request.contextPath}/assets/svg/ko.svg');  
background-repeat: no-repeat;  background-position: center;"
	class="mfp-close"></button>

<c:if test="${empty evento}">
	<br />
	<br />
	<br />
	<br />
	<div style="text-align: center; align: center; font-size: 20px;">
		<img src="${contextPath}/assets/images/PERICOLO.svg" />
		<p>
			La risorsa &egrave; al momento utilizzata da un altro operatore. <br> Riprova più tardi.
		</p>
	</div>
	<br />
	<br />
	<br />
</c:if>

<div class="bloccomodaleasy">
	<c:if test="${not empty evento}">
		<div class="modaleasy">
			<b>${evento.datiGenerali.titoloMulti['IT']}</b>
		</div>

		<c:if test="${RedazioneVIP == 'VIP'}">
			<div id="boxlistriassunto">
				<ul class="nav nav-tabs" id="pubblTab" role="tablist">
					<li class="nav-item active" style="cursor: pointer;" id="nucleoli"><a href="#nucleo" role="tab" data-toggle="tab" class="nav-link"
						id="nucleo-tab" style="cursor: pointer; padding: 10px 11px">NUCLEO</a></li>
					<li class="nav-item" style="cursor: pointer" id="schedaredazionaleli"><a href="#schedaredazionale" role="tab" data-toggle="tab"
						class="nav-link" id="schedaredazionale-tab" style="cursor: pointer; padding: 10px 11px">SCHEDA REDAZIONALE</a></li>
				</ul>
			</div>
		</c:if>

		<div class="tab-content" style="padding-top: 10px">
			<div class="bloccomodaleasycontenuto tab-pane fade active in" id="nucleo">
				<div class="row">
					<c:if test="${not empty evento.immagineSet.toArray()[0].immagineId}">
						<div class="col-md-2">
							<div class="imgmodale"
								style="background-image:url('${pageContext.request.contextPath}/getFile/${evento.eventoId}/immagine/${evento.immagineSet.toArray()[0].immagineId}.${evento.immagineSet.toArray()[0].estensione}')">
							</div>
						</div>
					</c:if>
					<div class="col-md-5">
						<c:choose>
							<c:when test="${not empty evento.nomeOwner && not empty evento.cognomeOwner}">
								<p>
									<b>Creato da:</b> ${evento.nomeOwner} ${evento.cognomeOwner}
								</p>
							</c:when>
							<c:otherwise>
								<p>
									<b>Creato da:</b> ${evento.usernameOwner}
								</p>
							</c:otherwise>
						</c:choose>
						<c:choose>
							<c:when test="${not empty evento.attivita}">
								<p>
									<b>Impresa/Locazione/Ente:</b> ${evento.attivita.denominazione}
								</p>
							</c:when>
							<c:otherwise>
								<p>
									<b>Impresa/Locazione/Ente:</b> ${evento.richiestaAttivita.denominazione}
								</p>
							</c:otherwise>
						</c:choose>

						<c:if test="${not empty evento.emailOwner}">
							<p>
								<b>Email organizzatore:</b> <a href="mailto:${evento.emailOwner}">${evento.emailOwner}</a>
							</p>
						</c:if>
						<c:if test="${not empty evento.telOwner}">
							<p>
								<b>Telefono organizzatore:</b> ${evento.telOwner}
							</p>
						</c:if>
						<c:if test="${not empty evento.celOwner}">
							<p>
								<b>Cellulare organizzatore:</b> ${evento.celOwner}
							</p>
						</c:if>
					</div>

					<div class="col-md-5">
						<p>
							<b>Stato del nucleo:</b> ${evento.statoId}
						</p>
						<p>
							<b>ID:</b> ${evento.eventoId}
						</p>
						<c:if test="${not empty dataPrimaRichiestaValidazione}">
							<p>
								<b>Data di richiesta valutazione:</b> ${dataPrimaRichiestaValidazione}
							</p>
						</c:if>
					</div>
				</div>


				<!-- BLOCCO BANNER INIZIO -->
				<c:if test="${not empty evento.bando.bandoId}">
					<div class="box user" id="boxbando">
						<div class="boxbanner" style="padding: 10px;">
							<div class="row">
								<div class="col-md-12" style="vertical-align: middle; border: 0px; margin: 0px;" id="testobando">
									<strong style="font-weight: 600; font-size: 16px; color: #003A54">INFORMAZIONI BANDO</strong><br />
									<c:if test="${not empty evento.bando.titoloBando}">
										<div id="labelbando" style="padding: 0px;">
											<strong>Bando:</strong> <span id="progettoutente">${evento.bando.titoloBando}</span>
										</div>
									</c:if>
									<c:if test="${not empty evento.progetto.titoloProgetto}">
										<div id="labelprogetto" style="padding: 0px;">
											<strong>Progetto:</strong> <span id="bandoutente">${evento.progetto.titoloProgetto}</span>
										</div>
									</c:if>
									<c:if test="${not empty evento.progetto.nomeOrganizzazione}">
										<div id="labelorganizzazione" style="padding: 0px;">
											<strong>Organizzazione:</strong> <span id="organizzazioneutente">${evento.progetto.nomeOrganizzazione}</span>
										</div>
									</c:if>
								</div>
							</div>
						</div>
					</div>
				</c:if>

				<!-- BLOCCO BANNER FINE -->
				<div class="titolomodal">DATI GENERALI</div>
				<div class="row">
					<div class="col-md-6">
						<b>Titolo:</b> ${evento.datiGenerali.titoloMulti['IT']}
					</div>
					<c:choose>
						<c:when test="${evento.tipo eq 'EVENTO'}">
							<div class="col-md-6">
								<b>Tipo evento:</b> <span class="uppercell">SINGOLO</span>
							</div>
						</c:when>
						<c:otherwise>
							<div class="col-md-6">
								<b>Tipo evento:</b> <span class="uppercell">${evento.tipo}</span>
							</div>
						</c:otherwise>
					</c:choose>
					<div class="col-md-6">
						<b>Categorie:</b> ${evento.tipologiaMIBACT}
					</div>

					<c:if test="${not empty evento.datiGenerali.abstractDescrMulti['IT']}">
						<div class="col-md-12">
							<b>Abstract:</b> ${evento.datiGenerali.abstractDescrMulti['IT']}
						</div>
					</c:if>
					<c:if test="${not empty evento.datiGenerali.descrizioneMulti['IT']}">
						<div class="col-md-12">
							<b>Descrizione:</b> ${evento.datiGenerali.descrizioneMulti['IT']}
						</div>
					</c:if>
				</div>
				<c:if test="${not empty evento.locationSet}">
					<div class="titolomodal">Location</div>
				</c:if>
				<c:forEach items="${evento.locationSet}" var="location" varStatus="loop">
					<div class="row" style="<c:if test="${!loop.last}">border-bottom: 1px solid #C4C4C4;</c:if>  padding-bottom: 10px; margin-bottom: 10px;">
						<c:if test="${not empty location.nazione}">
							<div class="col-md-6">
								<b>Nazione:</b> ${location.nazione}
							</div>
						</c:if>
						<c:if test="${not empty location.regione}">
							<div class="col-md-6">
								<b>Regione:</b> ${location.regione}
							</div>
						</c:if>
						<c:if test="${not empty location.comune}">
							<div class="col-md-6">
								<b>Comune: </b> ${location.comune}
							</div>
						</c:if>
						<c:if test="${not empty location.provincia}">
							<div class="col-md-6">
								<b>Provincia: </b>${location.provincia}</div>
						</c:if>
						<c:if test="${not empty location.comuneEstero}">
							<div class="col-md-6">
								<b>Comune:</b> ${location.comuneEstero}
							</div>
						</c:if>
						<c:if test="${not empty location.nomeLocation}">
							<div class="col-md-6">
								<b>Località:</b> ${location.nomeLocation}
							</div>
						</c:if>
						<c:if test="${not empty location.indirizzo}">
							<div class="col-md-6">
								<b>Indirizzo:</b> ${location.indirizzo}
							</div>
						</c:if>
						<c:if test="${not empty location.cap}">
							<div class="col-md-6">
								<b>CAP:</b> ${location.cap}
							</div>
						</c:if>
						<c:if test="${not empty location.latitudine}">
							<div class="col-md-6">
								<b>Latitudine:</b> ${location.latitudine}
							</div>
						</c:if>
						<c:if test="${not empty location.longitudine}">
							<div class="col-md-6">
								<b>Longitudine:</b> ${location.longitudine}
							</div>
						</c:if>
						<c:if test="${not empty location.link}">
							<div class="col-md-6">
								<b>Streaming su:</b> ${location.link}
							</div>
						</c:if>
					</div>
				</c:forEach>
				<c:if test="${not empty evento.periodoSet}">
					<div class="titolomodal">Periodi</div>
				</c:if>
				<c:forEach items="${evento.periodoSet}" var="periodo" varStatus="loop">
					<div class="row" style="<c:if test="${!loop.last}">border-bottom: 1px solid #C4C4C4;</c:if> padding-bottom: 10px; margin-bottom: 10px;">
						<c:if test="${periodo.dataSecca == true}">
							<div class="col-md-6">
								<b>Data:</b>
								<fmt:formatDate pattern="dd/MM/yyyy" value="${periodo.dataDa}" />
							</div>
						</c:if>
						<c:if test="${periodo.dataSecca == false}">
							<div class="col-md-6">
								<b>Data:</b>
								<fmt:formatDate pattern="dd/MM/yyyy" value="${periodo.dataDa}" />
								-
								<fmt:formatDate pattern="dd/MM/yyyy" value="${periodo.dataA}" />
							</div>
						</c:if>
						<%-- <div class="col-md-6">
						<b>Orario:</b>
						<c:if test="${periodo.fgContinuato == false}">non continuato</c:if>
						<c:if test="${periodo.fgContinuato == true}">continuato</c:if>
					</div> --%>
						<c:if test="${not empty periodo.orarioApertura}">
							<div class="col-md-6">
								<b>Orari</b>: ${fn:replace(periodo.orarioApertura,' ', '')}<c:if test="${not empty periodo.orarioChiusura}"> - ${fn:replace(periodo.orarioChiusura,' ', '')}</c:if>
								
							</div>
						</c:if>
						<c:if
							test="${not empty periodo.orarioAperturaMattina}">
							<div class="col-md-6">
								<b>Orari</b>: ${fn:replace(periodo.orarioAperturaMattina,' ', '')}
								<c:if
							test="${not empty periodo.orarioChiusuraMattina}">
								-${fn:replace(periodo.orarioChiusuraMattina,' ', '')} ,</c:if>
								<c:if
							test="${
							not empty periodo.orarioAperturaPomeriggio }">
								${fn:replace(periodo.orarioAperturaPomeriggio,' ', '')}</c:if>
								<c:if
							test="${not empty periodo.orarioChiusuraPomeriggio}">
								-${fn:replace(periodo.orarioChiusuraPomeriggio,' ', '')}</c:if>									
							</div>
						</c:if>
						<c:if
							test="${periodo.chiusuraLun == true || periodo.chiusuraMar == true || periodo.chiusuraMer == true || periodo.chiusuraGio == true || periodo.chiusuraVen == true || periodo.chiusuraSab == true || periodo.chiusuraDom == true}">
							<div class="col-md-6">
								<b>Giorni chiusura: </b><span class="removecomma"> <c:if test="${periodo.chiusuraLun == true}">
							 Luned&igrave;,
					</c:if> <c:if test="${periodo.chiusuraMar == true}">
							 Marted&igrave;,
					</c:if> <c:if test="${periodo.chiusuraMer == true}">
							 Mercoled&igrave;,
					</c:if> <c:if test="${periodo.chiusuraGio == true}">
							 Gioved&igrave;,
					</c:if> <c:if test="${periodo.chiusuraVen == true}">
							 Venerd&igrave;,
					</c:if> <c:if test="${periodo.chiusuraSab == true}">
							 Sabato,
					</c:if> <c:if test="${periodo.chiusuraDom == true}">
							 Domenica
					</c:if>
								</span>
							</div>
						</c:if>
						<c:if
							test="${periodo.cadenzaLun == true || periodo.cadenzaMar == true || periodo.cadenzaMer == true || periodo.cadenzaGio == true || periodo.cadenzaVen == true || periodo.cadenzaSab == true || periodo.cadenzaDom == true}">
							<div class="col-md-6">
								<b>Cadenza settimanale nei giorni: </b><span class="removecomma"> <c:if test="${periodo.cadenzaLun == true}">
							 Luned&igrave;,
					</c:if> <c:if test="${periodo.cadenzaMar == true}">
							 Marted&igrave;,
					</c:if> <c:if test="${periodo.cadenzaMer == true}">
							 Mercoled&igrave;,
					</c:if> <c:if test="${periodo.cadenzaGio == true}">
							 Gioved&igrave;,
					</c:if> <c:if test="${periodo.cadenzaVen == true}">
							 Venerd&igrave;,
					</c:if> <c:if test="${periodo.cadenzaSab == true}">
							 Sabato,
					</c:if> <c:if test="${periodo.cadenzaDom == true}">
							 Domenica
					</c:if>
								</span>
							</div>
						</c:if>
						<c:if test="${periodo.cadenzaMensile.size() > 0}">
							<div class="col-md-6">
								<b>Cadenza mensile nei giorni:</b><span class="removecomma">${String.join(",", periodo.cadenzaMensile)}</span>
							</div>
						</c:if>
					</div>
				</c:forEach>
				<c:if test="${not empty evento.telefonoSet || not empty evento.emailSet || not empty evento.sitoSet}">
					<div class="titolomodal">CONTATTI</div>
					<div class="row">
						<c:if test="${not empty evento.telefonoSet}">
							<div class="col-md-6">
								<b>Telefono:</b> <span class="removecomma"><c:forEach items="${evento.telefonoSet}" var="telefono"> ${telefono.contatto},</c:forEach></span>
							</div>
						</c:if>
						<c:if test="${not empty evento.emailSet}">
							<div class="col-md-6"><b>Email:</b> <span class="removecomma"><c:forEach items="${evento.emailSet}" var="email"> <a href="mailto:${email.contatto}">${email.contatto}</a>,</c:forEach></span></div>
						</c:if>
						<c:if test="${not empty evento.sitoSet}">
							<div class="col-md-6">
								<b>Sito web/Social network:</b> <span class="removecomma"><c:forEach items="${evento.sitoSet}" var="sito"> ${sito.contatto},</c:forEach></span>
							</div>
						</c:if>
					</div>
				</c:if>

				<c:if
					test="${evento.accessibilita.flagPercorsi == true || evento.accessibilita.flagDisabilitaUditiva == true || evento.accessibilita.flagFamiglieBambini == true || evento.accessibilita.flagPersoneAnimali == true || evento.accessibilita.flagDisabilitaVisiva == true || evento.accessibilita.flagAnziani == true || evento.accessibilita.flagDisabilitaFisica == true || evento.accessibilita.flagGravidanza == true}">

					<div class="titolomodal">Accessibilità</div>
					<div class="row">
						<div class="col-md-12">
							<b> Persone con esigenze specifiche:</b> <span class="removecomma"> 							
								<c:if test="${evento.accessibilita.flagAnziani == true}">
									 Anziani,
								</c:if>
								<c:if test="${evento.accessibilita.flagDisabilitaFisica == true}">
									 Persone con disabilità fisica,
								</c:if>
								<c:if test="${evento.accessibilita.flagDisabilitaUditiva == true}">
									 Persone con disabilità uditiva,
								</c:if>
								<c:if test="${evento.accessibilita.flagDisabilitaVisiva == true}">
									 Persone con disabilità visiva,
								</c:if>
								<c:if test="${evento.accessibilita.flagGravidanza == true}">
									 Donne in gravidanza,
								</c:if>							
								<c:if test="${evento.accessibilita.flagFamiglieBambini == true}">
									 Famiglie con bambini,
								</c:if> 
								<c:if test="${evento.accessibilita.flagPersoneAnimali == true}">
									 Persone con animali
								</c:if>      <!--<c:if test="${evento.accessibilita.flagPercorsi == true}">
						 			Percorsi accessibili
								</c:if> -->
							</span>
						</div>
					</div>
				</c:if>
				<c:if
					test="${evento.accessibilita.flagInfopoint == true || evento.accessibilita.flagServiziIgienici == true || evento.accessibilita.flagParcheggioDisabili == true || evento.accessibilita.flagIngressi == true || evento.accessibilita.flagPercorsiTattili == true || evento.accessibilita.flagGuideBraille == true || evento.accessibilita.flagSegnaleticaBraille == true || evento.accessibilita.flagSegnaleticaLeggibile == true || evento.accessibilita.flagMaterialeLeggibile == true || evento.accessibilita.flagPostazioniAudio == true || evento.accessibilita.flagAudioguide == true || evento.accessibilita.flagAudioguidePercorsi == true || evento.accessibilita.flagMaterialeSottotitolato == true || evento.accessibilita.flagRiproduzioneTattili == true || evento.accessibilita.flagComputer == true || evento.accessibilita.flagLis == true}">
					<div class="row">
						<div class="col-md-12">
							<b>Servizi accessibili:</b> <span class="removecomma"> <c:if test="${evento.accessibilita.flagInfopoint == true}">
								Punto informazioni/accoglienza accessibile,
							</c:if> <c:if test="${evento.accessibilita.flagServiziIgienici == true}">
								 Servizi igienici accessibili,
							</c:if> <c:if test="${evento.accessibilita.flagParcheggioDisabili == true}">
								 Parcheggio riservato alle persone con disabilità,
							</c:if> <c:if test="${evento.accessibilita.flagIngressi == true}">
								 Ingresso principale o secondario accessibile (pedana, ascensore, altro),
							</c:if> <c:if test="${evento.accessibilita.flagPercorsiTattili == true}">
								 Percorsi tattili,
							</c:if> <c:if test="${evento.accessibilita.flagGuideBraille == true}">
								 Guide cartacee in braille,
							</c:if> <c:if test="${evento.accessibilita.flagSegnaleticaBraille == true}">
								Segnaletica in braille,
							</c:if> <c:if test="${evento.accessibilita.flagSegnaleticaLeggibile == true}">
								 Segnaletica ad altro contrasto/alta leggibilità,
							</c:if> <c:if test="${evento.accessibilita.flagMaterialeLeggibile == true}">
								 Materiale informativo ad altro contrasto/alta leggibilità,
							</c:if> <c:if test="${evento.accessibilita.flagPostazioniAudio == true}">
								 Postazioni audio-descrittive della struttura e del percorso,
							</c:if> <c:if test="${evento.accessibilita.flagAudioguide == true}">
								 Audioguide per persone ipovedenti o non vedenti,
							</c:if> <c:if test="${evento.accessibilita.flagAudioguidePercorsi == true}">
								 Audioguide con percorsi tattili per persone ipovedenti o non vedenti,
							</c:if> <c:if test="${evento.accessibilita.flagMaterialeSottotitolato == true}">
								 Materiale audiovisivo sottotitolato,
							</c:if> <c:if test="${evento.accessibilita.flagRiproduzioneTattili == true}">
								 Riproduzione tattili,
							</c:if> <c:if test="${evento.accessibilita.flagComputer == true}">
								 Computer con descrizione vocali,
							</c:if> <c:if test="${evento.accessibilita.flagLis == true}">
								 Personale che conosce la Lingua dei Segni Italiana (LIS)
							</c:if>
							</span>
						</div>
					</div>
				</c:if>
				<c:if
					test="${evento.accessibilita.flagBiblioteca == true || evento.accessibilita.flagGiardini == true || evento.accessibilita.flagLudoteca == true || evento.accessibilita.flagNursey == true || evento.accessibilita.flagParcogiochi == true }">
					<div class="row">
						<div class="col-md-12">
							<b> Servizi famiglie con bambini:</b> <span class="removecomma"> <c:if test="${evento.accessibilita.flagBiblioteca == true}">
								 Biblioteca per bambini,
							</c:if> <c:if test="${evento.accessibilita.flagGiardini == true}">
								 Spazi esterni attrezzati (giardini),
							</c:if> <c:if test="${evento.accessibilita.flagLudoteca == true}">
								 Ludoteca,
							</c:if> <c:if test="${evento.accessibilita.flagNursey == true}">
								 Nursey,
							</c:if> <c:if test="${evento.accessibilita.flagParcogiochi == true}">
								 Parco giochi
							</c:if>
							</span>
						</div>
					</div>
				</c:if>
				<c:if test="${evento.accessibilita.flagCaniMedi == true || evento.accessibilita.flagCaniPiccoli == true || evento.accessibilita.flagCani == true}">
					<div class="row">
						<div class="col-md-12">
							<b>Animali ammessi:</b> <span class="removecomma"> <c:if test="${evento.accessibilita.flagCaniMedi == true}">
								 Cani taglia media,
							</c:if> <c:if test="${evento.accessibilita.flagCaniPiccoli == true}">
								 Cani taglia piccola,
							</c:if> <c:if test="${evento.accessibilita.flagCani == true}">
								Tutti i cani
							</c:if>
							</span>
						</div>
					</div>
				</c:if>
				<c:if test="${evento.immagineSet.size() > 0}">
					<div class="titolomodal">MEDIA - IMMAGINI</div>
					<div class="row">
						<c:forEach items="${evento.immagineSet}" var="immagine" varStatus="loop">
							<div class="col-md-12" style="<c:if test="${!loop.last}">border-bottom: 1px solid #C4C4C4;</c:if> padding-bottom: 10px; margin-bottom: 10px;">
								<b>Allegato:</b> ${immagine.nomeOriginale}<br />
								<c:if test="${not empty immagine.didascaliaMulti['IT']}">
									<b>Didascalia:</b> ${immagine.didascaliaMulti['IT']}<br />
								</c:if>
								<c:if test="${not empty immagine.credits}">
									<b>Credits:</b> ${immagine.credits}
				</c:if>
							</div>
						</c:forEach>
					</div>
				</c:if>
				<c:if test="${evento.documentoSet.size() > 0}">
					<div class="titolomodal">MEDIA - ALTRI ALLEGATI</div>
					<div class="row">
						<c:forEach items="${evento.documentoSet}" var="documento" varStatus="loop">
							<div class="col-md-12" style="<c:if test="${!loop.last}">border-bottom: 1px solid #C4C4C4;</c:if> padding-bottom: 10px; margin-bottom: 10px;">
								<b>Allegato:</b> ${documento.nomeOriginale}<br />
								<c:if test="${not empty documento.titoloMulti['IT']}">
									<b>Titolo:</b> ${documento.titoloMulti['IT']}
							</c:if>
							</div>
						</c:forEach>
					</div>
				</c:if>
				<c:if test="${evento.linkSet.size() > 0}">
					<div class="titolomodal">MEDIA - LINK</div>
					<div class="row">
						<c:forEach items="${evento.linkSet}" var="link" varStatus="loop">
							<div class="col-md-12" style="<c:if test="${!loop.last}">border-bottom: 1px solid #C4C4C4;</c:if> padding-bottom: 10px; margin-bottom: 10px;">
								<b>Url:</b> <a href="${link.link}" target="_blank">${link.link}</a><br />
								<c:if test="${not empty link.titoloMulti['IT']}">
									<b>Titolo:</b> ${link.titoloMulti['IT']}<br />
								</c:if>
								<c:if test="${not empty link.didascaliaMulti['IT']}">
									<b>Didascalia:</b> ${link.didascaliaMulti['IT']}<br />
								</c:if>
								<c:if test="${not empty link.credits}">
									<b>Credits:</b> ${link.credits}
							</c:if>
							</div>
						</c:forEach>
					</div>
				</c:if>
				<c:if test="${evento.relazioneSet.size() > 0}">
					<c:if test="${evento.tipo == 'EVENTO'}">
						<div class="titolomodal">ASSOCIAZIONE</div>
						<div class="row">
							<c:forEach items="${evento.relazioneSet}" var="relazione" varStatus="loop">
								<c:if test="${relazione.redazioneId eq null}">
									<c:if test="${relazione.tipoRelazione == 'CONTENUTO'}">
										<div class="col-md-12"
											style="<c:if test="${!loop.last}">border-bottom: 1px solid #C4C4C4;</c:if> padding-bottom: 10px; margin-bottom: 10px;">
											<b> ${relazione.titolo}</b>
										</div>
									</c:if>
								</c:if>
							</c:forEach>
						</div>
					</c:if>
					<c:if test="${evento.tipo == 'RASSEGNA'}">
						<div class="titolomodal">ASSOCIAZIONI</div>
						<div class="row">
							<c:forEach items="${evento.relazioneSet}" var="relazione" varStatus="loop">
								<c:if test="${relazione.redazioneId eq null}">
									<c:if test="${relazione.tipoRelazione == 'CONTIENE'}">
										<div class="col-md-12"
											style="<c:if test="${!loop.last}">border-bottom: 1px solid #C4C4C4;</c:if> padding-bottom: 10px; margin-bottom: 10px;">
											<b> ${relazione.titolo}</b>
										</div>
									</c:if>
								</c:if>
							</c:forEach>
						</div>
					</c:if>
				</c:if>
		
				<div class="titolomodal">TICKET</div>
				<div class="row">				
					<div class="col-md-6">
						<b>Ticket:</b> ${evento.ticket.tipoTicket}
					</div>
					<c:if test="${not empty evento.ticket.ticketIntero}">
						<div class="col-md-6">
							<b>Ticket Intero:</b> ${evento.ticket.ticketIntero}&euro;
						</div>
					</c:if>
					<c:if test="${not empty evento.ticket.linkPrenotazioni}">
						<div class="col-md-6">
							<b>Link di prenotazione:</b> ${evento.ticket.linkPrenotazioni}
						</div>
					</c:if>
					<c:if
						test="${evento.ticket.flagGratisAnziani == true || evento.ticket.flagGratisBambini == true || evento.ticket.flagGratisDisabili == true || evento.ticket.flagGratisAccompagnatori == true || evento.ticket.flagGratisConvenzioni == true}">
						<div class="col-md-6">
							<b>Ingresso gratuito per:</b> <span class="removecomma"> <c:if test="${evento.ticket.flagGratisAnziani == true}">
								Anziani,
							</c:if> <c:if test="${evento.ticket.flagGratisBambini == true}">
								Bambini,
							</c:if> <c:if test="${evento.ticket.flagGratisDisabili == true}">
								Disabili,
							</c:if> <c:if test="${evento.ticket.flagGratisAccompagnatori == true}">
								Accompagnatori disabili,
							</c:if> <c:if test="${evento.ticket.flagGratisConvenzioni == true}">
								Convenzioni
							</c:if>
							</span>
						</div>
					</c:if>
					<c:if test="${not empty evento.ticket.convenzioniAttiveG}">
						<div class="col-md-12">
							<b>Specifica convenzioni attive per ingresso gratuito:</b> ${evento.ticket.convenzioniAttiveG}
						</div>
					</c:if>

					<c:if test="${not empty evento.ticket.ticketRidotto}">
						<div class="col-md-6">
							<b>Ticket ridotto:</b> ${evento.ticket.ticketRidotto}&euro;
						</div>
					</c:if>
					<c:if
						test="${evento.ticket.flagRidottoAnziani == true || evento.ticket.flagRidottoBambini == true || evento.ticket.flagRidottoDisabili == true || evento.ticket.flagRidottoAccompagnatori == true || evento.ticket.flagRidottoConvenzioni == true}">
						<div class="col-md-6">
							<b>Ingresso ridotto per:</b> <span class="removecomma"> <c:if test="${evento.ticket.flagRidottoAnziani == true}">
								Anziani,
							</c:if> <c:if test="${evento.ticket.flagRidottoBambini == true}">
								Bambini,
							</c:if> <c:if test="${evento.ticket.flagRidottoDisabili == true}">
								Disabili,
							</c:if> <c:if test="${evento.ticket.flagRidottoAccompagnatori == true}">
								Accompagnatori disabili,
							</c:if> <c:if test="${evento.ticket.flagRidottoConvenzioni == true}">
								Convenzioni
							</c:if>
							</span>
						</div>
					</c:if>
					<c:if test="${not empty evento.ticket.convenzioniAttiveR}">
						<div class="col-md-12">
							<b>Specifica convenzioni attive per ingresso ridotto:</b> ${evento.ticket.convenzioniAttiveR}
						</div>
					</c:if>

					<c:if test="${not empty evento.ticket.ticketGruppiRidotto}">
						<div class="col-md-6">
							<b>Ticket ingresso ridotto per gruppi:</b> ${evento.ticket.ticketGruppiRidotto}&euro;
						</div>
						<c:if test="${not empty evento.ticket.ticketGruppiNumero}">
							<div class="col-md-6">
								<b>Di almeno:</b> ${evento.ticket.ticketGruppiNumero}
							</div>
						</c:if>
					</c:if>
					<c:if test="${not empty evento.ticket.notaMulti['IT']}">
						<div class="col-md-12">
							<b>Note:</b> ${evento.ticket.notaMulti['IT']}
						</div>
					</c:if>
				</div>
						<c:if test="${pubblicazioni.size() > 0 && evento.statoId == 'VALIDATO'}">
					<div class="titolomodal">PUBBLICAZIONI</div>
					<div class="row">
						<c:forEach items="${pubblicazioni}" var="pubblicazione" varStatus="loop">
							<div class="col-md-12" style="<c:if test="${!loop.last}">border-bottom: 1px solid #C4C4C4;</c:if> padding-bottom: 10px; margin-bottom: 10px;">
								<b>Redazione:</b> ${pubblicazione.nomeRedazione},
								<fmt:formatDate pattern="dd/MM/yyyy" value="${pubblicazione.dataPubblicazione}" />
								alle ore
								<fmt:formatDate pattern="HH:mm" value="${pubblicazione.dataPubblicazione}" />
							</div>
						</c:forEach>
					</div>
				</c:if>
				
				
			</div>
			<c:if test="${RedazioneVIP == 'VIP'}">
				<div class="bloccomodaleasycontenuto tab-pane fade" id="schedaredazionale">


	<c:if test="${not empty statoPubblicazione}">


					<div class="row" style="margin-bottom: 0px;">
						<div class="col-md-2" style="text-align: center;">
							<img src="${contextPath}/assets/svg/redazione.svg" style="height: 120px;">
						</div>
						<div class="col-md-10">
							<c:if test="${not empty statoPubblicazione}">
								<b>Stato scheda:</b>
								<span style="text-transform: uppercase;">${statoPubblicazione} </span>
								<br />
							</c:if>
							<b>Pubblicato:</b>
							<c:if test="${pubblicazioneVip.fgPubblicato == 'false'}">Offline</c:if>
							<c:if test="${pubblicazioneVip.fgPubblicato == 'true'}">Online</c:if>
							<br />

							<c:if test="${not empty pubblicazioneVip.cognomeOwner || not empty pubblicazioneVip.nomeOwner}">
								<b>Pubblicato da:</b> ${pubblicazioneVip.nomeOwner}	 ${pubblicazioneVip.cognomeOwner}	
							</c:if>
						</div>
					</div>

					<div class="titolomodal">DATI GENERALI</div>
					<div class="row">
						<div class="col-md-12" style="padding-bottom: 10px; margin-bottom: 10px;">
							<div id="bsrprodotti" style="padding: 0px;">
								<b>Categorie:</b> <span id="srprodotti"></span>
							</div>
							<div id="bsrtipologiaprodotti" style="padding: 0px;">
								<b>Tipologia attività:</b> <span id="srtipologiaprodotti"></span>
							</div>
							<div id="bsrtipologia" style="padding: 0px;">
								<b>Tipologia scheda:</b> <span id="srtipologia"></span>
							</div>
							<div id="bsrtitolo" style="padding: 0px;">
								<b>Titolo dell’evento:</b> <span id="srtitolo">  ${titoloRedazione}</span>
							</div>
							<div id="bsrabstract" style="padding: 0px;">
								<b>Abstract:</b> <span id="srabstract"></span>
							</div>
							<div id="bsrsnippet" style="padding: 0px;">
								<b>Snippet:</b> <span id="srsnippet"></span>
							</div>
							<div id="bsrdescrizione" style="padding: 0px;">
								<b>Descrizione:</b> <span id="srdescrizione"></span>
							</div>
						</div>
					</div>

					<div id="bsrcategorie" class="titolomodal">CATEGORIE</div>
					<div id="bsrprodottiset" class="row">
						<div class="col-md-12">
							<b>Prodotti:</b> <span id="srprodottiset"></span>
						</div>
					</div>
					<div id="bsrmezzi" class="row">
						<div class="col-md-12">
							<b>Elenco mezzi:</b> <span id="srmezzi"></span>
						</div>
					</div>
					<c:if test="${not empty evento.locationSet}">
						<div class="titolomodal">Luogo</div>
					</c:if>
					<c:forEach items="${evento.locationSet}" var="location" varStatus="loop">
						<div class="row" style="<c:if test="${!loop.last}">border-bottom: 1px solid #C4C4C4;</c:if>  padding-bottom: 10px; margin-bottom: 10px;">
							<c:if test="${not empty location.nazione}">
								<div class="col-md-6">
									<b>Nazione:</b> ${location.nazione}
								</div>
							</c:if>
							<c:if test="${not empty location.regione}">
								<div class="col-md-6">
									<b>Regione:</b> ${location.regione}
								</div>
							</c:if>
							<c:if test="${not empty location.comune}">
								<div class="col-md-6">
									<b>Comune: </b> ${location.comune}
								</div>
							</c:if>
							<c:if test="${not empty location.provincia}">
								<div class="col-md-6">
									<b>Provincia: </b>${location.provincia}</div>
							</c:if>
							<c:if test="${not empty location.comuneEstero}">
								<div class="col-md-6">
									<b>Comune:</b> ${location.comuneEstero}
								</div>
							</c:if>
							<c:if test="${not empty location.nomeLocation}">
								<div class="col-md-6">
									<b>Località:</b> ${location.nomeLocation}
								</div>
							</c:if>
							<c:if test="${not empty location.indirizzo}">
								<div class="col-md-6">
									<b>Indirizzo:</b> ${location.indirizzo}
								</div>
							</c:if>
							<c:if test="${not empty location.cap}">
								<div class="col-md-6">
									<b>CAP:</b> ${location.cap}
								</div>
							</c:if>
							<c:if test="${not empty location.latitudine}">
								<div class="col-md-6">
									<b>Latitudine:</b> ${location.latitudine}
								</div>
							</c:if>
							<c:if test="${not empty location.longitudine}">
								<div class="col-md-6">
									<b>Longitudine:</b> ${location.longitudine}
								</div>
							</c:if>
							<c:if test="${not empty location.link}">
								<div class="col-md-6">
									<b>Streaming su:</b> ${location.link}
								</div>
							</c:if>
						</div>
					</c:forEach>
					<c:if test="${not empty evento.periodoSet}">
						<div class="titolomodal">Periodi</div>
					</c:if>
					<c:forEach items="${evento.periodoSet}" var="periodo" varStatus="loop">
						<div class="row" style="<c:if test="${!loop.last}">border-bottom: 1px solid #C4C4C4;</c:if> padding-bottom: 10px; margin-bottom: 10px;">
							<c:if test="${periodo.dataSecca == true}">
								<div class="col-md-6">
									<b>Data:</b>
									<fmt:formatDate pattern="dd/MM/yyyy" value="${periodo.dataDa}" />
								</div>
							</c:if>
							<c:if test="${periodo.dataSecca == false}">
								<div class="col-md-6">
									<b>Data:</b>
									<fmt:formatDate pattern="dd/MM/yyyy" value="${periodo.dataDa}" />
									-
									<fmt:formatDate pattern="dd/MM/yyyy" value="${periodo.dataA}" />
								</div>
							</c:if>
							<%-- 					<div class="col-md-6">
						<b>Orario:</b>
						<c:if test="${periodo.fgContinuato == false}">non continuato</c:if>
						<c:if test="${periodo.fgContinuato == true}">continuato</c:if>
					</div> --%>
							<c:if test="${not empty periodo.orarioApertura && not empty periodo.orarioChiusura}">
								<div class="col-md-6">
									<b>Orari</b>: ${fn:replace(periodo.orarioApertura,' ', '')}-${fn:replace(periodo.orarioChiusura,' ', '')}
								</div>
							</c:if>
							<c:if
								test="${not empty periodo.orarioAperturaMattina && not empty periodo.orarioChiusuraMattina && not empty periodo.orarioAperturaPomeriggio && not empty periodo.orarioChiusuraPomeriggio}">
								<div class="col-md-6">
									<b>Orari</b>: ${fn:replace(periodo.orarioAperturaMattina,' ', '')}-${fn:replace(periodo.orarioChiusuraMattina,' ', '')} ,
									${fn:replace(periodo.orarioAperturaPomeriggio,' ', '')}-${fn:replace(periodo.orarioChiusuraPomeriggio,' ', '')}
								</div>
							</c:if>
							<c:if
								test="${periodo.chiusuraLun == true || periodo.chiusuraMar == true || periodo.chiusuraMer == true || periodo.chiusuraGio == true || periodo.chiusuraVen == true || periodo.chiusuraSab == true || periodo.chiusuraDom == true}">
								<div class="col-md-6">
									<b>Giorni chiusura: </b><span class="removecomma"> <c:if test="${periodo.chiusuraLun == true}">
							 Luned&igrave;, 
					</c:if> <c:if test="${periodo.chiusuraMar == true}">
							 Marted&igrave;, 
					</c:if> <c:if test="${periodo.chiusuraMer == true}">
							 Mercoled&igrave;, 
					</c:if> <c:if test="${periodo.chiusuraGio == true}">
							 Gioved&igrave;, 
					</c:if> <c:if test="${periodo.chiusuraVen == true}">
							 Venerd&igrave;, 
					</c:if> <c:if test="${periodo.chiusuraSab == true}">
							 Sabato, 
					</c:if> <c:if test="${periodo.chiusuraDom == true}">
							 Domenica
					</c:if>
									</span>
								</div>
							</c:if>
							<c:if
								test="${periodo.cadenzaLun == true || periodo.cadenzaMar == true || periodo.cadenzaMer == true || periodo.cadenzaGio == true || periodo.cadenzaVen == true || periodo.cadenzaSab == true || periodo.cadenzaDom == true}">
								<div class="col-md-6">
									<b>Cadenza settimanale nei giorni: </b><span class="removecomma"> <c:if test="${periodo.cadenzaLun == true}">
							 Luned&igrave;, 
					</c:if> <c:if test="${periodo.cadenzaMar == true}">
							 Marted&igrave;, 
					</c:if> <c:if test="${periodo.cadenzaMer == true}">
							 Mercoled&igrave;, 
					</c:if> <c:if test="${periodo.cadenzaGio == true}">
							 Gioved&igrave;, 
					</c:if> <c:if test="${periodo.cadenzaVen == true}">
							 Venerd&igrave;,
					</c:if> <c:if test="${periodo.cadenzaSab == true}">
							 Sabato, 
					</c:if> <c:if test="${periodo.cadenzaDom == true}">
							 Domenica
					</c:if>
									</span>
								</div>
							</c:if>
							<c:if test="${periodo.cadenzaMensile.size() > 0}">
								<div class="col-md-6">
									<b>Cadenza mensile nei giorni:</b><span class="removecomma">${String.join(", ", periodo.cadenzaMensile)}</span>
								</div>
							</c:if>
						</div>
					</c:forEach>
					<c:if test="${not empty evento.telefonoSet || not empty evento.emailSet || not empty evento.sitoSet}">
						<div class="titolomodal">CONTATTI</div>
						<div class="row">
							<c:if test="${not empty evento.telefonoSet}">
								<div class="col-md-6">
									<b>Telefono:</b> <span class="removecomma"> <c:forEach items="${evento.telefonoSet}" var="telefono">${telefono.contatto}, </c:forEach></span></div>
							</c:if>
							<c:if test="${not empty evento.emailSet}">
								<div class="col-md-6">
									<b>Email:</b> <span class="removecomma"> <c:forEach items="${evento.emailSet}" var="email"><a href="mailto:${email.contatto}">${email.contatto}</a>, </c:forEach></span></div>
							</c:if>
							<c:if test="${not empty evento.sitoSet}">
								<div class="col-md-6">
									<b>Sito web/Social network:</b> <span class="removecomma"> <c:forEach items="${evento.sitoSet}" var="sito">${sito.contatto}, </c:forEach></span></div>
							</c:if>
						</div>
					</c:if>

					<div id="tbsrimmagini" class="titolomodal">MEDIA - IMMAGINI</div>
			
					<b id="srimmagininucleotext" style="color:#0075bf !important; display:none;">Nucleo:</b>
					<div id="srimmagininucleo" style="padding: 0px 6px;"></div>
					<b id="srimmaginitext" style="color:#0075bf !important; display:none;">Redazione:</b>
					<div id="srimmagini" style="padding: 0px  6px;"></div>

					<div id="tbsrallegati" class="titolomodal">MEDIA - ALTRI ALLEGATI</div>
					<b id="srallegatinucleotext" style="color:#0075bf !important; display:none;">Nucleo:</b>
					<div id="srallegatinucleo" style="padding: 0px  6px;"></div>
					<b id="srallegatitext" style="color:#0075bf !important; display:none;">Redazione:</b>
					<div id="srallegati" style="padding: 0px  6px;"></div>

					<div id="tbsrlink" class="titolomodal">MEDIA - LINK</div>
					<b id="srlinknucleotext" style="color:#0075bf !important; display:none;">Nucleo:</b>
					<div id="srlinknucleo" style="padding: 0px  6px;"></div>
					<b id="srlinktext" style="color:#0075bf !important; display:none;">Redazione:</b>
					<div id="srlink" style="padding: 0px  6px;"></div>				


					<div id="tsrassociazioni" class="titolomodal">ASSOCIAZIONI</div>
					<div id="bsrassociazioninucleo" class="row">
						<div class="col-md-12">
								<b id="srassociazioninucleotext" style="color:#0075bf !important; display:none;">Nucleo:</b> <div class="removecomma" id="srassociazioninucleo" style="padding:0px;">							
							<c:if test="${evento.tipo == 'EVENTO'}">
									<c:forEach items="${evento.relazioneSet}" var="relazione" varStatus="loop">
										<c:if test="${relazione.redazioneId eq null}">
											<c:if test="${relazione.tipoRelazione == 'CONTENUTO'}">${relazione.titolo}, </c:if>
										</c:if>
									</c:forEach>
								</c:if>								
									<c:if test="${evento.tipo == 'RASSEGNA'}">
									<c:forEach items="${evento.relazioneSet}" var="relazione" varStatus="loop">
										<c:if test="${relazione.redazioneId eq null}">
											<c:if test="${relazione.tipoRelazione == 'CONTIENE'}">${relazione.titolo}, </c:if>
										</c:if>
									</c:forEach>
								</c:if>
							</div>
						</div>
					</div>

					<div id="bsrassociazioni" class="row">
						<div class="col-md-12">
							<b id="srassociazionitext" style="color:#0075bf !important; display:none;">Redazione:</b><div class="removecomma" id="srassociazioni" style="padding:0px;"></div>
						</div>
					</div>
					
					
		

					<div id="tbsrattribuzioni" class="titolomodal">ATTRIBUZIONI</div>
					
					<div class="row" id="bsrline1">
						<div id="bsropzioni" class="col-md-6">
							<b>Opzioni di posizionamento: </b> <span class="removecomma" id="sropzioni"></span>
						</div>
						<div id="bsrranking" class="col-md-6">
							<b>Ranking:</b> <span id="srranking"></span>
						</div>
					</div>
					
					
					<div id="bsrranking2" class="row">
						<div class="col-md-12">
							<b>Valore d'attrattività turistica: </b> <span id="srranking2"></span>
						</div>
					</div>
					
					
					
					<div id="bsrattrattore" class="row">
						<div class="col-md-12">
							<b>Attrattore: </b> <span id="srattrattore"></span>
						</div>
					</div>

					<div class="titolomodal" id="tbsrturista" style="display: none">CONSIGLIATO AL TURISTA</div>
					<div id="bsrturista" class="row">
						<div class="col-md-12" style="padding-bottom: 10px; margin-bottom: 10px;">
							<b>Consigliato a chi viene in Puglia per: </b> <span id="srturista"></span><br />
						</div>
					</div>
					
					<div class="titolomodal">TICKET</div>
					<div class="row">
						<div class="col-md-6">
							<b>Ticket:</b> ${evento.ticket.tipoTicket}
						</div>
						<c:if test="${not empty evento.ticket.ticketIntero}">
							<div class="col-md-6">
								<b>Ticket Intero:</b> ${evento.ticket.ticketIntero}&euro;
							</div>
						</c:if>
						<c:if test="${not empty evento.ticket.linkPrenotazioni}">
							<div class="col-md-6">
								<b>Link di prenotazione:</b> ${evento.ticket.linkPrenotazioni}
							</div>
						</c:if>
						<c:if
							test="${evento.ticket.flagGratisAnziani == true || evento.ticket.flagGratisBambini == true || evento.ticket.flagGratisDisabili == true || evento.ticket.flagGratisAccompagnatori == true || evento.ticket.flagGratisConvenzioni == true}">
							<div class="col-md-6">
								<b>Ingresso gratuito per:</b> <span class="removecomma"> <c:if test="${evento.ticket.flagGratisAnziani == true}">
								Anziani,
							</c:if> <c:if test="${evento.ticket.flagGratisBambini == true}">
								Bambini,
							</c:if> <c:if test="${evento.ticket.flagGratisDisabili == true}">
								Disabili,
							</c:if> <c:if test="${evento.ticket.flagGratisAccompagnatori == true}">
								Accompagnatori disabili,
							</c:if> <c:if test="${evento.ticket.flagGratisConvenzioni == true}">
								Convenzioni
							</c:if>
								</span>
							</div>
						</c:if>
						<c:if test="${not empty evento.ticket.convenzioniAttiveG}">
							<div class="col-md-12">
								<b>Specifica convenzioni attive per ingresso gratuito:</b> ${evento.ticket.convenzioniAttiveG}
							</div>
						</c:if>

						<c:if test="${not empty evento.ticket.ticketRidotto}">
							<div class="col-md-6">
								<b>Ticket ridotto:</b> ${evento.ticket.ticketRidotto}&euro;
							</div>
						</c:if>
						<c:if
							test="${evento.ticket.flagRidottoAnziani == true || evento.ticket.flagRidottoBambini == true || evento.ticket.flagRidottoDisabili == true || evento.ticket.flagRidottoAccompagnatori == true || evento.ticket.flagRidottoConvenzioni == true}">
							<div class="col-md-6">
								<b>Ingresso ridotto per:</b> <span class="removecomma"> <c:if test="${evento.ticket.flagRidottoAnziani == true}">
								Anziani,
							</c:if> <c:if test="${evento.ticket.flagRidottoBambini == true}">
								Bambini,
							</c:if> <c:if test="${evento.ticket.flagRidottoDisabili == true}">
								Disabili,
							</c:if> <c:if test="${evento.ticket.flagRidottoAccompagnatori == true}">
								Accompagnatori disabili,
							</c:if> <c:if test="${evento.ticket.flagRidottoConvenzioni == true}">
								Convenzioni
							</c:if>
								</span>
							</div>
						</c:if>
						<c:if test="${not empty evento.ticket.convenzioniAttiveR}">
							<div class="col-md-12">
								<b>Specifica convenzioni attive per ingresso ridotto:</b> ${evento.ticket.convenzioniAttiveR}
							</div>
						</c:if>

						<c:if test="${not empty evento.ticket.ticketGruppiRidotto}">
							<div class="col-md-6">
								<b>Ticket ingresso ridotto per gruppi:</b> ${evento.ticket.ticketGruppiRidotto}&euro;
							</div>
							<c:if test="${not empty evento.ticket.ticketGruppiNumero}">
								<div class="col-md-6">
									<b>Di almeno:</b> ${evento.ticket.ticketGruppiNumero}
								</div>
							</c:if>
						</c:if>
						<c:if test="${not empty evento.ticket.notaMulti['IT']}">
							<div class="col-md-12">
								<b>Note:</b> ${evento.ticket.notaMulti['IT']}
							</div>
						</c:if>
					</div>	

					<c:if
						test="${evento.accessibilita.flagPercorsi == true || evento.accessibilita.flagDisabilitaUditiva == true || evento.accessibilita.flagFamiglieBambini == true || evento.accessibilita.flagPersoneAnimali == true || evento.accessibilita.flagDisabilitaVisiva == true || evento.accessibilita.flagAnziani == true || evento.accessibilita.flagDisabilitaFisica == true || evento.accessibilita.flagGravidanza == true}">

						<div class="titolomodal">Accessibilità</div>
						<div class="row">
							<div class="col-md-12">
								<b> Persone con esigenze specifiche:</b> <span class="removecomma"> 
								<c:if test="${evento.accessibilita.flagAnziani == true}">
									 Anziani,
								</c:if>
								<c:if test="${evento.accessibilita.flagDisabilitaFisica == true}">
									 Persone con disabilità fisica,
								</c:if>
								<c:if test="${evento.accessibilita.flagDisabilitaUditiva == true}">
									 Persone con disabilità uditiva,
								</c:if>
								<c:if test="${evento.accessibilita.flagDisabilitaVisiva == true}">
									 Persone con disabilità visiva,
								</c:if>
								<c:if test="${evento.accessibilita.flagGravidanza == true}">
									 Donne in gravidanza,
								</c:if>							
								<c:if test="${evento.accessibilita.flagFamiglieBambini == true}">
									 Famiglie con bambini,
								</c:if> 
								<c:if test="${evento.accessibilita.flagPersoneAnimali == true}">
									 Persone con animali
								</c:if>      <!--<c:if test="${evento.accessibilita.flagPercorsi == true}">
						 			Percorsi accessibili
								</c:if> -->
								</span>
							</div>
						</div>
					</c:if>
					<c:if
						test="${evento.accessibilita.flagInfopoint == true || evento.accessibilita.flagServiziIgienici == true || evento.accessibilita.flagParcheggioDisabili == true || evento.accessibilita.flagIngressi == true || evento.accessibilita.flagPercorsiTattili == true || evento.accessibilita.flagGuideBraille == true || evento.accessibilita.flagSegnaleticaBraille == true || evento.accessibilita.flagSegnaleticaLeggibile == true || evento.accessibilita.flagMaterialeLeggibile == true || evento.accessibilita.flagPostazioniAudio == true || evento.accessibilita.flagAudioguide == true || evento.accessibilita.flagAudioguidePercorsi == true || evento.accessibilita.flagMaterialeSottotitolato == true || evento.accessibilita.flagRiproduzioneTattili == true || evento.accessibilita.flagComputer == true || evento.accessibilita.flagLis == true}">
						<div class="row">
							<div class="col-md-12">
								<b>Servizi accessibili:</b> <span class="removecomma"> <c:if test="${evento.accessibilita.flagInfopoint == true}">
								Punto informazioni/accoglienza accessibile,
							</c:if> <c:if test="${evento.accessibilita.flagServiziIgienici == true}">
								 Servizi igienici accessibili,
							</c:if> <c:if test="${evento.accessibilita.flagParcheggioDisabili == true}">
								 Parcheggio riservato alle persone con disabilità,
							</c:if> <c:if test="${evento.accessibilita.flagIngressi == true}">
								 Ingresso principale o secondario accessibile (pedana, ascensore, altro),
							</c:if> <c:if test="${evento.accessibilita.flagPercorsiTattili == true}">
								 Percorsi tattili,
							</c:if> <c:if test="${evento.accessibilita.flagGuideBraille == true}">
								 Guide cartacee in braille,
							</c:if> <c:if test="${evento.accessibilita.flagSegnaleticaBraille == true}">
								Segnaletica in braille,
							</c:if> <c:if test="${evento.accessibilita.flagSegnaleticaLeggibile == true}">
								 Segnaletica ad altro contrasto/alta leggibilità,
							</c:if> <c:if test="${evento.accessibilita.flagMaterialeLeggibile == true}">
								 Materiale informativo ad altro contrasto/alta leggibilità,
							</c:if> <c:if test="${evento.accessibilita.flagPostazioniAudio == true}">
								 Postazioni audio-descrittive della struttura e del percorso,
							</c:if> <c:if test="${evento.accessibilita.flagAudioguide == true}">
								 Audioguide per persone ipovedenti o non vedenti,
							</c:if> <c:if test="${evento.accessibilita.flagAudioguidePercorsi == true}">
								 Audioguide con percorsi tattili per persone ipovedenti o non vedenti,
							</c:if> <c:if test="${evento.accessibilita.flagMaterialeSottotitolato == true}">
								 Materiale audiovisivo sottotitolato,
							</c:if> <c:if test="${evento.accessibilita.flagRiproduzioneTattili == true}">
								 Riproduzione tattili,
							</c:if> <c:if test="${evento.accessibilita.flagComputer == true}">
								 Computer con descrizione vocali,
							</c:if> <c:if test="${evento.accessibilita.flagLis == true}">
								 Personale che conosce la Lingua dei Segni Italiana (LIS)
							</c:if>
								</span>
							</div>
						</div>
					</c:if>
					<c:if
						test="${evento.accessibilita.flagBiblioteca == true || evento.accessibilita.flagGiardini == true || evento.accessibilita.flagLudoteca == true || evento.accessibilita.flagNursey == true || evento.accessibilita.flagParcogiochi == true }">
						<div class="row">
							<div class="col-md-12">
								<b> Servizi famiglie con bambini:</b> <span class="removecomma"><c:if test="${evento.accessibilita.flagBiblioteca == true}">
								 Biblioteca per bambini,</c:if> <c:if test="${evento.accessibilita.flagGiardini == true}">
								 Spazi esterni attrezzati (giardini),</c:if> <c:if test="${evento.accessibilita.flagLudoteca == true}"> Ludoteca,
							</c:if> <c:if test="${evento.accessibilita.flagNursey == true}"> Nursey,</c:if><c:if test="${evento.accessibilita.flagParcogiochi == true}"> Parco giochi</c:if>
								</span>
							</div>
						</div>
					</c:if>
					<c:if
						test="${evento.accessibilita.flagCaniMedi == true || evento.accessibilita.flagCaniPiccoli == true || evento.accessibilita.flagCani == true}">
						<div class="row">
							<div class="col-md-12">
								<b>Animali ammessi:</b> <span class="removecomma"> <c:if test="${evento.accessibilita.flagCaniMedi == true}">Cani taglia media,</c:if> <c:if test="${evento.accessibilita.flagCaniPiccoli == true}">Cani taglia piccola,</c:if> <c:if test="${evento.accessibilita.flagCani == true}">Tutti i cani
							</c:if>
								</span>
							</div>
						</div>
					</c:if>
					
					<c:if test="${pubblicazioni.size() > 0}">
						<div class="titolomodal">PUBBLICAZIONI</div>
						<div class="row">
							<c:forEach items="${pubblicazioni}" var="pubblicazione" varStatus="loop">
								<div class="col-md-12" style="<c:if test="${!loop.last}">border-bottom: 1px solid #C4C4C4;</c:if> padding-bottom: 10px; margin-bottom: 10px;">
									<b>Redazione:</b> ${pubblicazione.nomeRedazione},
									<fmt:formatDate pattern="dd/MM/yyyy" value="${pubblicazione.dataPubblicazione}" />
									alle ore
									<fmt:formatDate pattern="HH:mm" value="${pubblicazione.dataPubblicazione}" />
								</div>
							</c:forEach>
						</div>
					</c:if>
					
					
					
					
					

<%-- 					<c:if test="${evento.relazioneSet.size() > 0}">
						<c:if test="${evento.tipo == 'EVENTO'}">
							<div class="titolomodal">ASSOCIAZIONE</div>
							<div class="row">
								<c:forEach items="${evento.relazioneSet}" var="relazione" varStatus="loop">
									<c:if test="${relazione.redazioneId eq null}">
										<c:if test="${relazione.tipoRelazione == 'CONTENUTO'}">
											<div class="col-md-12"
												style="<c:if test="${!loop.last}">border-bottom: 1px solid #C4C4C4;</c:if> padding-bottom: 10px; margin-bottom: 10px;">
												<b> ${relazione.titolo}</b>
											</div>
										</c:if>
									</c:if>
								</c:forEach>
							</div>
						</c:if>
						<c:if test="${evento.tipo == 'RASSEGNA'}">
							<div class="titolomodal">ASSOCIAZIONI</div>
							<div class="row">
								<c:forEach items="${evento.relazioneSet}" var="relazione" varStatus="loop">
									<c:if test="${relazione.redazioneId eq null}">
										<c:if test="${relazione.tipoRelazione == 'CONTIENE'}">
											<div class="col-md-12"
												style="<c:if test="${!loop.last}">border-bottom: 1px solid #C4C4C4;</c:if> padding-bottom: 10px; margin-bottom: 10px;">
												<b> ${relazione.titolo}</b>
											</div>
										</c:if>
									</c:if>
								</c:forEach>
							</div>
						</c:if>
					</c:if> --%>
				</c:if>
				
				<c:if test="${empty statoPubblicazione}">
				
						<!-- <div class="row" style="margin-bottom: 0px;">
						<div class="col-md-2" style="text-align: center;">
							<img src="/SigeaWeb/assets/svg/redazione.svg" style="height: 120px;">
						</div> -->
						<div class="col-md-10">							
								<b>Stato scheda:</b>
								<span style="text-transform: uppercase;">In attesa di redazione</span>
								<br />
						L'evento non è ancora stato preso in carico dal redattore						
						</div>
					<!-- </div> -->			
								</c:if>				

				</div>
			</c:if>
		</div>

	</c:if>
</div>
<div style="width: 100%; text-align: center">
	<button title="Chiudi (Esc)" type="button" style="margin: 10px 10px 10px 10px;" class="btn btn-primary invia mfp-close">Chiudi</button>
</div>

<script>

function removecomma(){
	$('.removecomma').each(function( index ) {		
		var contenitore = $(this).html();		
		contenitore = contenitore.trim();
		var last = contenitore.slice(contenitore.length - 1);
		if( last == ','){
		var newstring = contenitore.substring(0, contenitore.length - 1);	
		$(this).empty().html(newstring);
		}
	});	
}

$(document).ready(function(){
	

	
	// Gestione scheda redazionale
	var vgenericMetadata = [${pubblicazioneVip.genericMetadata}];
	
	if(vgenericMetadata){		
	
	var genericMetadata = ${pubblicazioneVip.genericMetadata};	
	
	
		if(genericMetadata.tipologieMIBACT){ var prodotti = Object.values(genericMetadata.tipologieMIBACT);
		$("#bsrprodotti").show();
		for (var i = 0; i < prodotti.length; i ++ ){
			$("#srprodotti").append(prodotti[i]);
			if(i!=prodotti.length-1){$("#srprodotti").append(", ");}
		}
		}
	if(genericMetadata.mezziSet){var mezzi = Object.values(genericMetadata.mezziSet); 
	$("#bsrmezzi").show();
	$("#bsrcategorie").show();
	for (var i = 0; i < mezzi.length; i ++ ){
		$("#srmezzi").append(mezzi[i]);
		if(i!=mezzi.length-1){$("#srmezzi").append(", ");}
	}}
	if(genericMetadata.prodottiSet){var prodottiset = Object.values(genericMetadata.prodottiSet);	
	$("#bsrprodottiset").show();	
	$("#bsrcategorie").show();
	for (var i = 0; i < prodottiset.length; i ++ ){
		$("#srprodottiset").append(prodottiset[i]);
		if(i!=prodottiset.length-1){$("#srprodottiset").append(", ");}
	}}
	if(genericMetadata.tipologieAttivitaSet){var tipologiaprodotti = Object.values(genericMetadata.tipologieAttivitaSet);
	$("#bsrtipologiaprodotti").show();
	for (var i = 0; i < tipologiaprodotti.length; i ++ ){
		$("#srtipologiaprodotti").append(tipologiaprodotti[i]);
		if(i!=tipologiaprodotti.length-1){$("#srtipologiaprodotti").append(", ");}
	}}
		
	if(genericMetadata.relazioneSetAggiunto){ 
	$("#bsrassociazioni").show(); 
	$("#tbsrassociazioni").show();
	$("#tsrassociazioni").show();	
	$("#srassociazionitext").show();	
	for (var i = 0; i < genericMetadata.relazioneSetAggiunto.length; i ++ ){
		$("#srassociazioni").append(genericMetadata.relazioneSetAggiunto[i].titolo);
		if(i!=genericMetadata.relazioneSetAggiunto.length-1){$("#srassociazioni").append(", ");}
		
	}}	
	
	var associazioni = $("#srassociazioninucleo").html();
	associazioni = associazioni.replace(/\t/g, '');
	associazioni = associazioni.trim();
		
	if(associazioni.length > 5){	
		$("#srassociazioninucleo").show();
		$("#bsrassociazioninucleo").show();
		$("#srassociazioninucleotext").show();			
		$("#tsrassociazioni").show();			
	}
	
	
	
	
	
	if(genericMetadata.tipoScheda == 'evento'){
		var tipoScheda = 'Evento';
	}
			if(genericMetadata.tipoScheda == 'attivita'){	
				var tipoScheda = 'Attività';
		}	
	
	if(genericMetadata.tipoScheda){									$("#srtipologia").html(tipoScheda);	$("#bsrtipologia").show(); }
	if(genericMetadata.titoloMulti['IT'] !== undefined){			$("#srtitolo").html(genericMetadata.titoloMulti['IT']); $("#bsrtitolo").show(); }
	if(genericMetadata.abstractDescrMulti['IT']  !== undefined){	$("#srabstract").html(genericMetadata.abstractDescrMulti['IT']); $("#bsrabstract").show();}
	if(genericMetadata.snippetMulti['IT'] !== undefined){			$("#srsnippet").html(genericMetadata.snippetMulti['IT']); $("#bsrsnippet").show();}
	if(genericMetadata.descrizioneMulti['IT'] !== undefined){		$("#srdescrizione").html(genericMetadata.descrizioneMulti['IT']); $("#bsrdescrizione").show();}
	
	//console.log(genericMetadata);
	
	if(genericMetadata.ranking){	
		if(genericMetadata.ranking == '1'){			
			var ranking = '1. Non fruibile';
		}else if(genericMetadata.ranking == '2'){
			var ranking = '2. Non rilevante';
		}else if(genericMetadata.ranking == '3'){
			var ranking = '3. Poco interessante';
		}else if(genericMetadata.ranking == '4'){
			var ranking = '4. Possibile attrattività';
		}else if(genericMetadata.ranking == '5'){
			var ranking = '5. Apprezzabile';
		}else if(genericMetadata.ranking == '6'){
			var ranking = '6. Degno di nota';
		}else if(genericMetadata.ranking == '7'){
			var ranking = '7. Consigliato';
		}else if(genericMetadata.ranking == '8'){
			var ranking = '8. Da visitare';
		}else if(genericMetadata.ranking == '9'){
			var ranking = '9. Da non perdere';
		}else if(genericMetadata.ranking == '10'){
			var ranking = '10. SuperUser';
		}
	}
	
		if(genericMetadata.tipoScheda == 'evento'){
	$("#srranking").html(ranking); $("#bsrranking").show();  $("#bsrline1").show();	
		}
		if(genericMetadata.tipoScheda == 'attivita'){	
	$("#srranking2").html(genericMetadata.valore); $("#bsrranking2").show();
	}	
	
	$("#tbsrattribuzioni").show(); 	
	
	if(genericMetadata.attrattoriSet){	$("#bsrattrattore").show();  $("#tbsrattribuzioni").show();
		for (var i = 0; i < genericMetadata.attrattoriSet.length; i ++ ){		
			var value = genericMetadata.attrattoriSet[i].etichetta;
			$("#srattrattore").append(value);
			if(i!=genericMetadata.attrattoriSet.length-1){$("#srattrattore").append(", ");}
		}	
	}
	
	if(genericMetadata.arteC== true){ $("#srturista").append("Scoprire l'arte, la storia e la cultura, "); }	
	if(genericMetadata.ass_spettacoli== true){ $("#srturista").append("Assistere a spettacoli, festival ed eventi, ");}
	if(genericMetadata.autentico== true){ $("#srturista").append("Scoprire l'autenticità di luoghi e persone, ");}			
	if(genericMetadata.avventura== true){ $("#srturista").append("Desiderio di avventura tra sport e natura, ");}			
	if(genericMetadata.backpackers== true){ $("#srturista").append("Viaggi low budget con zaino in spalla, ");}			
	if(genericMetadata.benessere== true){ $("#srturista").append("Concedersi relax e benessere, ");}
	if(genericMetadata.celebrazioni== true){ $("#srturista").append("Celebrare un evento importante, ");}			
	if(genericMetadata.congressi== true){ $("#srturista").append("Partecipare a congressi ed eventi, ");}			
	if(genericMetadata.lgbt== true){ $("#srturista").append("Vivere una vacanza friendly LGBT, ");}			
	if(genericMetadata.lusso== true){ $("#srturista").append("Trovare esclusività e lusso, ");}			
	if(genericMetadata.relax== true){ $("#srturista").append("Trovare tranquillità e contatto con la natura, ");}			
	if(genericMetadata.salute== true){ $("#srturista").append("Aver cura della salute, ");}			
	if(genericMetadata.shopping== true){ $("#srturista").append("fare shopping, ");}			
	if(genericMetadata.short_break== true){ $("#srturista").append("Transito e short break, ");}
	if(genericMetadata.sportC== true){ $("#srturista").append("Praticare sport, ");}			
	if(genericMetadata.staying== true){ $("#srturista").append("Long-staying, ");}			
	if(genericMetadata.svago== true){ $("#srturista").append("Svago e divertimento, ");}			
	if(genericMetadata.vac_anziani== true){ $("#srturista").append("Vivere una vacanza adatta a persone anziane, ");}
	if(genericMetadata.vac_bambini== true){ $("#srturista").append("Vivere una vacanza con i bambini, ");}			
	if(genericMetadata.viag_affari== true){ $("#srturista").append("Viaggi d'affari, ");}			
	if(genericMetadata.viag_all_incl== true){ $("#srturista").append("Viaggi organizzati all-inclusive, ");}			
	if(genericMetadata.viag_amici== true){ $("#srturista").append("Trascorrere una vacanza con gli amici, ");}			
	if(genericMetadata.viag_disabili== true){ $("#srturista").append("Vivere una vacanza accessibile ai disabili, ");}
	if(genericMetadata.viag_incentive== true){ $("#srturista").append("Viaggi incentive, ");}			
	if(genericMetadata.viag_natura== true){ $("#srturista").append("Vivere una vacanza ecosostenibile, ");}			
	if(genericMetadata.viag_nozze== true){ $("#srturista").append("Viaggi di nozze, ");}
	if(genericMetadata.devozione== true){ $("#srturista").append("Vivere momenti di spiritualità e devozione, ");}
	var text = $("#srturista").html(); 
	text = text.slice(0, -2);
	$("#srturista").html(text); 
	if($("#srturista").html().length > 5){	
			$("#bsrturista").show();
			$("#srturista").show();
			$("#tbsrturista").show();			
	}
		
	if(genericMetadata.big== true){ $("#sropzioni").append(" big,");  $("#bsropzioni").show();  $("#tbsrattribuzioni").show();}
	if(genericMetadata.slideshow== true){ $("#sropzioni").append(" slideshow,"); $("#bsropzioni").show(); $("#tbsrattribuzioni").show();}
		
		if(genericMetadata.immagineSetAggiunto){
		for (var i = 0; i < genericMetadata.immagineSetAggiunto.length; i ++ ){	
			if(genericMetadata.immagineSetAggiunto[i].banner == true){
				var banner ="Si";
			}else{
				var banner ="No";
			}
			
			if(genericMetadata.immagineSetAggiunto[i].daPubblicare == true){
				var daPubblicare ="Si";
			}else{
				var daPubblicare ="No";
			}	
			
			var didascalia = '';
			
			if(genericMetadata.immagineSetAggiunto[i].didascaliaMulti){
				didascalia = genericMetadata.immagineSetAggiunto[i].didascaliaMulti.IT; 
			}	
			
			var credits = '';
			if(genericMetadata.immagineSetAggiunto[i].credits!== null && genericMetadata.immagineSetAggiunto[i].credits!== undefined && genericMetadata.immagineSetAggiunto[i].credits!== 'undefined')
				credits = genericMetadata.immagineSetAggiunto[i].credits;
			
			var img ="<div class=\"row\" style=\"padding:0px 0px 10px 0px; margin-bottom: 10px;\">"+
			"<div class=\"col-md-12\"><b>Immagine: </b>"+genericMetadata.immagineSetAggiunto[i].nomeOriginale+"</div>"+
			"<div class=\"col-md-12\"><b>Didascalia: </b>"+didascalia+"</div>"+
			"<div class=\"col-md-12\"><b>Credits: </b>"+credits+"</div>"+
			"<div class=\"col-md-6\"><b>Ordine numerico:</b> "+genericMetadata.immagineSetAggiunto[i].ordine+"</div>"+
			"<div class=\"col-md-6\"><b>Banner: </b> "+banner+"</div>"+
			"<div class=\"col-md-12\"><b>Da pubblicare: </b> "+daPubblicare+"</div></div><hr/>";
			$("#srimmagini").append(img);
			 $("#tsrimmagini").show();
			 $("#srimmagini").show();
			 $("#srimmaginitext").show();
		}}
		if(genericMetadata.documentoSetAggiunto){
		for (var i = 0; i < genericMetadata.documentoSetAggiunto.length; i ++ ){			
			var all ="<div class=\"row\" style=\"padding:0px 0px 10px 0px; margin-bottom: 10px;\">"+
			"<div class=\"col-md-12\"><b>Allegato: </b>"+genericMetadata.documentoSetAggiunto[i].nomeOriginale+"</div>"+
			"<div class=\"col-md-12\"><b>Titolo: </b>"+genericMetadata.documentoSetAggiunto[i].titoloMulti.IT+"</div>"+
			"</div><hr/>";
			$("#srallegati").append(all);
			 $("#tbsrallegati").show();
			 $("#bsrallegati").show();
			 $("#srallegatitext").show();
		}}
		
		if(genericMetadata.linkSetAggiunto){
		for (var i = 0; i < genericMetadata.linkSetAggiunto.length; i ++ ){		
			var link ="<div class=\"row\" style=\"padding:0px 0px 10px 0px; margin-bottom: 10px;\">"+
			"<div class=\"col-md-12\"><b>Link: </b> "+genericMetadata.linkSetAggiunto[i].link+"</div>"+
			"<div class=\"col-md-12\"><b>Titolo: </b>"+genericMetadata.linkSetAggiunto[i].titoloMulti.IT+"</div>"+	
			"</div><hr/>";
			$("#srlink").append(link);		
			$("#tsrlink").show();
			$("#srlink").show();
			$("#srlinktext").show();
		}}
		
		if(genericMetadata.immagineSet){
			for (var i = 0; i < genericMetadata.immagineSet.length; i ++ ){	
				if(genericMetadata.immagineSet[i].banner == true){
					var banner ="Si";
				}else{
					var banner ="No";
				}
				
				if(genericMetadata.immagineSet[i].daPubblicare == true){
					var daPubblicare ="Si";
				}else{
					var daPubblicare ="No";
				}		
				
				var didascalia = '';
				
				if(genericMetadata.immagineSet[i].didascaliaMulti){
					didascalia = genericMetadata.immagineSet[i].didascaliaMulti.IT; 
				}
				
				var credits = '';
				if(genericMetadata.immagineSet[i].credits!== null && genericMetadata.immagineSet[i].credits!== undefined && genericMetadata.immagineSet[i].credits!== 'undefined')
					credits = genericMetadata.immagineSet[i].credits;
				
				var img ="<div class=\"row\" style=\"padding:0px 0px 10px 0px; margin-bottom: 10px;\">"+
				"<div class=\"col-md-12\"><b>Immagine: </b>"+genericMetadata.immagineSet[i].nomeOriginale+"</div>"+
				"<div class=\"col-md-12\"><b>Didascalia: </b>"+didascalia+"</div>"+
				"<div class=\"col-md-12\"><b>Credits: </b>"+credits+"</div>"+
				"<div class=\"col-md-6\"><b>Ordine numerico:</b> "+genericMetadata.immagineSet[i].ordine+"</div>"+
				"<div class=\"col-md-6\"><b>Banner: </b> "+banner+"</div>"+
				"<div class=\"col-md-12\"><b>Da pubblicare: </b> "+daPubblicare+"</div></div><hr/>";
				$("#srimmagininucleo").append(img);
				 $("#tsrimmagininucleo").show();
				 $("#srimmagininucleo").show();
				 $("#srimmagininucleotext").show();
			}}
			if(genericMetadata.documentoSet){
			for (var i = 0; i < genericMetadata.documentoSet.length; i ++ ){			
				var all ="<div class=\"row\" style=\"padding:0px 0px 10px 0px; margin-bottom: 10px;\">"+
				"<div class=\"col-md-12\"><b>Allegato: </b>"+genericMetadata.documentoSet[i].nomeOriginale+"</div>"+
				"<div class=\"col-md-12\"><b>Titolo: </b>"+genericMetadata.documentoSet[i].titoloMulti.IT+"</div>"+
				"</div><hr/>";
				$("#srallegatinucleo").append(all);
				 $("#tbsrallegatinucleo").show();
				 $("#srallegatinucleo").show();
				 $("#srallegatinucleotext").show();
			}}
			if(genericMetadata.linkSet){
			for (var i = 0; i < genericMetadata.linkSet.length; i ++ ){		
				var link ="<div class=\"row\" style=\"padding:0px 0px 10px 0px; margin-bottom: 10px;\">"+
				"<div class=\"col-md-12\"><b>Link: </b> "+genericMetadata.linkSet[i].link+"</div>"+
				"<div class=\"col-md-12\"><b>Titolo: </b>"+genericMetadata.linkSet[i].titoloMulti.IT+"</div>"+	
				"</div><hr/>";
				$("#srlinknucleo").append(link);		
				$("#tsrlinknucleo").show();
				$("#srlinknucleo").show();
				$("#srlinknucleotext").show();
			}}	
		
	}
	
	
	$('.removecomma').each(function( index ) {		
		var contenitore = $(this).html();		
		contenitore = contenitore.trim();
		var last = contenitore.slice(contenitore.length - 1);
		if( last == ','){
		var newstring = contenitore.substring(0, contenitore.length - 1);	
		$(this).empty().html(newstring);
		}
	});	
});
</script>
