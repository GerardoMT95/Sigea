INSERT INTO sigec.t_sigea_ruoli
(ruolo_id, permessi)
VALUES('CITTADINO', '{}');

INSERT INTO sigec.t_sigea_ruoli
(ruolo_id, permessi)
VALUES('PROMOTORE', '{
  "creaEvento": true,
  "filtroPromuovi": true,
  "hideTabRevisioni": true
}');

INSERT INTO sigec.t_sigea_ruoli
(ruolo_id, permessi)
VALUES('VALIDATORE', '{
  "creaEvento": true,
  "filtroGestisci": true,
  "filtroEventoSeeAll": true,
  "filtroSegnalazioneSeeAll": true,
  "writableNucleoLAVORAZIONE": true,
  "cambioStatoMODIFICA": true,
  "cambioStatoRESPINTO": true,
  "cambioStatoLAVORAZIONE": true,
  "cambioStatoVALIDATO": true
}');

INSERT INTO sigec.t_sigea_ruoli
(ruolo_id, permessi)
VALUES('REDATTORE', '{
  "creaEvento": true,
  "filtroGestisci": true,
  "filtroEventoSeeAll": true,
  "filtroSegnalazioneSeeAll": true,
  "filtroRedattore": true
}');

INSERT INTO sigec.t_sigea_ruoli
(ruolo_id, permessi)
VALUES('CAPOREDATTORE', '{
  "creaEvento": true,
  "filtroGestisci": true,
  "filtroEventoSeeAll": true,
  "filtroSegnalazioneSeeAll": true,
  "filtroRedattore": true,
  "pubblicazioneVIP": true
}');

INSERT INTO sigec.t_sigea_tipologie_utenti
(tipologia_id, descrizione, is_redattore)
VALUES('SEGNALA_EVENTO', 'Cittadino', false);

INSERT INTO sigec.t_sigea_tipologie_utenti
(tipologia_id, descrizione, is_redattore)
VALUES('PROMUOVI_EVENTO', 'Promotore', false);

INSERT INTO sigec.t_sigea_tipologie_utenti
(tipologia_id, descrizione, is_redattore)
VALUES('RED-PP', 'Redattore di Viaggiare In Puglia', true);

INSERT INTO sigec.t_sigea_tipologie_utenti
(tipologia_id, descrizione, is_redattore)
VALUES('REDVAL-PP', 'Validatore', false);

INSERT INTO sigec.t_sigea_tipologie_utenti
(tipologia_id, descrizione, is_redattore)
VALUES('REDCAP-PP', 'Caporedattore di Viaggiare In Puglia', true);

INSERT INTO sigec.t_sigea_tipologie_utenti
(tipologia_id, descrizione, is_redattore)
VALUES('REDVALCAP-PP', 'Caporedattore e Validatore di Viaggiare In Puglia', true);

INSERT INTO sigec.t_sigea_tipologia_ruolo
(tipologia_id, ruolo_id)
VALUES('SEGNALA_EVENTO', 'CITTADINO');

INSERT INTO sigec.t_sigea_tipologia_ruolo
(tipologia_id, ruolo_id)
VALUES('PROMUOVI_EVENTO', 'PROMOTORE');

INSERT INTO sigec.t_sigea_tipologia_ruolo
(tipologia_id, ruolo_id)
VALUES('RED-PP', 'REDATTORE');

INSERT INTO sigec.t_sigea_tipologia_ruolo
(tipologia_id, ruolo_id)
VALUES('REDVAL-PP', 'VALIDATORE');

INSERT INTO sigec.t_sigea_tipologia_ruolo
(tipologia_id, ruolo_id)
VALUES('REDCAP-PP', 'CAPOREDATTORE');

INSERT INTO sigec.t_sigea_tipologia_ruolo
(tipologia_id, ruolo_id)
VALUES('REDVALCAP-PP', 'VALIDATORE');

INSERT INTO sigec.t_sigea_tipologia_ruolo
(tipologia_id, ruolo_id)
VALUES('REDVALCAP-PP', 'CAPOREDATTORE');

INSERT INTO sigec.t_sigea_stati
(stato_id, descrizione, stato_inizio, stato_fine, accesso_condizionato, nome, configurazione)
VALUES('BOZZA', 'Bozza', true, false, false, 'CREA BOZZA', '{
  "isEditableByOwner":true,
  "isWorkerExclusive":false,
  "statiRaggiungibili": [
    {
      "statoId": "VALUTAZIONE",
      "pubblicazione" : false,
      "revocaPubblicazione": false,
      "emailOwner" : "",
      "emailOperatori" : "",
      "addNote" : false,
      "statiSovrascritti" : [],
      "permessi": {
        "mustBeOwner": true
      }
    },
    {
      "statoId": "VALIDATO",
      "pubblicazione" : true,
      "revocaPubblicazione": false,
      "emailOwner" : "",
      "emailOperatori" : "",
      "addNote" : false,
      "statiSovrascritti" : ["VALUTAZIONE"],
      "permessi": {
        "autorizzato": true,
        "mustBeOwner": true
      }
    }
  ]
}');



INSERT INTO sigec.t_sigea_stati
(stato_id, descrizione, stato_inizio, stato_fine, accesso_condizionato, nome, configurazione)
VALUES('MODIFICA', 'In modifica', false, false, true, 'RICHIEDI MODIFICA', '{
  "isEditableByOwner":true,
  "isWorkerExclusive":false,
  "statiRaggiungibili": [
    {
      "statoId": "RIVALUTAZIONE",
      "pubblicazione" : false,
      "revocaPubblicazione": false,
      "emailOwner" : "",
      "emailOperatori" : "",
      "addNote" : false,
      "statiSovrascritti" : [],
      "permessi": {
        "mustBeOwner": true
      }
    },
    {
      "statoId": "VALIDATO",
      "pubblicazione" : true,
      "revocaPubblicazione": false,
      "emailOwner" : "",
      "emailOperatori" : "Rivalidato",
      "addNote" : false,
      "statiSovrascritti" : ["RIVALUTAZIONE"],
      "permessi": {
        "autorizzato": true,
        "mustBeOwner": true
      }
    }
  ]
}');

INSERT INTO sigec.t_sigea_stati
(stato_id, descrizione, stato_inizio, stato_fine, accesso_condizionato, nome, configurazione)
VALUES('VALUTAZIONE', 'In valutazione', false, false, true, 'RICHIEDI VALUTAZIONE', '{
  "isEditableByOwner":false,
  "isWorkerExclusive":false,
  "statiRaggiungibili": [
    {
      "statoId": "LAVORAZIONE",
      "pubblicazione" : false,
      "revocaPubblicazione": false,
      "emailOwner" : "",
      "emailOperatori" : "",
      "addNote" : false,
      "statiSovrascritti" : [],
      "permessi": {
        "autorizzato": true
      }
    }
  ]
}');

INSERT INTO sigec.t_sigea_stati
(stato_id, descrizione, stato_inizio, stato_fine, accesso_condizionato, nome, configurazione)
VALUES('RIVALUTAZIONE', 'In nuova valutazione', false, false, true, 'RICHIEDI VALUTAZIONE', '{
  "isEditableByOwner":false,
  "isWorkerExclusive":false,
  "statiRaggiungibili": [
    {
      "statoId": "LAVORAZIONE",
      "pubblicazione" : false,
      "revocaPubblicazione": false,
      "emailOwner" : "",
      "emailOperatori" : "",
      "addNote" : false,
      "statiSovrascritti" : [],
      "permessi": {
        "autorizzato": true
      }
    }
  ]
}');

INSERT INTO sigec.t_sigea_stati
(stato_id, descrizione, stato_inizio, stato_fine, accesso_condizionato, nome, configurazione)
VALUES('LAVORAZIONE', 'In lavorazione', false, false, true, 'LAVORAZIONE', '{
  "isEditableByOwner":false,
  "isWorkerExclusive":true,
  "statiRaggiungibili": [
    {
      "statoId": "MODIFICA",
      "pubblicazione" : false,
      "revocaPubblicazione": false,
      "emailOwner" : "InModifica",
      "emailOperatori" : "",
      "addNote" : true,
      "statiSovrascritti" : [],
      "permessi": {
        "autorizzato": true,
        "mustBeWorker": true
      }
    },
    {
      "statoId": "RESPINTO",
      "pubblicazione" : false,
      "revocaPubblicazione": false,
      "emailOwner" : "Respinto",
      "emailOperatori" : "",
      "addNote" : true,
      "statiSovrascritti" : [],
      "permessi": {
        "autorizzato": true,
        "mustNotBeOwner": true,
        "mustBeWorker": true
      }
    },
    {
      "statoId": "VALIDATO",
      "pubblicazione" : true,
      "revocaPubblicazione": false,
      "emailOwner" : "",
      "emailOperatori" : "Rivalidato",
      "addNote" : false,
      "statiSovrascritti" : [],
      "permessi": {
        "autorizzato": true,
        "mustBeWorker": true
      }
    }
  ]
}');

INSERT INTO sigec.t_sigea_stati
(stato_id, descrizione, stato_inizio, stato_fine, accesso_condizionato, nome, configurazione)
VALUES('RESPINTO', 'Respinto', false, false, true, 'RESPINGI', '{
  "isEditableByOwner":false,
  "isWorkerExclusive":false,
  "statiRaggiungibili": [
    {
      "statoId": "RIVALUTAZIONE",
      "pubblicazione" : false,
      "revocaPubblicazione": false,
      "emailOwner" : "",
      "emailOperatori" : "",
      "addNote" : false,
      "statiSovrascritti" : [],
      "permessi": {
        "autorizzato": true,
        "mustNotBeOwner": true
      }
    },
    {
      "statoId": "MODIFICA",
      "pubblicazione" : false,
      "revocaPubblicazione": false,
      "emailOwner" : "",
      "emailOperatori" : "",
      "addNote" : true,
      "statiSovrascritti" : [],
      "permessi": {
        "autorizzato": true,
        "mustNotBeOwner": true
      }
    }
  ]
}');

INSERT INTO sigec.t_sigea_stati
(stato_id, descrizione, stato_inizio, stato_fine, accesso_condizionato, nome, configurazione)
VALUES('VALIDATO', 'Validato', false, true, true, 'VALIDA', '{
  "isEditableByOwner":false,
  "isWorkerExclusive":false,
  "statiRaggiungibili": [
    {
      "statoId": "MODIFICA",
      "pubblicazione" : false,
      "revocaPubblicazione": true,
      "emailOwner" : "InModifica",
      "emailOperatori" : "Revocato",
      "addNote" : true,
      "statiSovrascritti" : [],
      "permessi": {
        "autorizzato": true,
        "mustNotBeOwner": true
      }
    },
    {
      "statoId": "MODIFICA",
      "pubblicazione" : false,
      "revocaPubblicazione": true,
      "emailOwner" : "",
      "emailOperatori" : "Revocato",
      "addNote" : true,
      "statiSovrascritti" : ["LAVORAZIONE"],
      "permessi": {
        "mustBeOwner": true
      }
    },
    {
      "statoId": "LAVORAZIONE",
      "pubblicazione" : false,
      "revocaPubblicazione": true,
      "emailOwner" : "",
      "emailOperatori" : "Revocato",
      "addNote" : false,
      "statiSovrascritti" : [],
      "permessi": {
        "autorizzato": true
      }
    },
    {
      "statoId": "ANNULLATO",
      "pubblicazione" : false,
      "revocaPubblicazione": true,
      "emailOwner" : "",
      "emailOperatori" : "Annullato",
      "addNote" : true,
      "statiSovrascritti" : [],
      "permessi": {
        "mustBeOwner": true
      }
    }
  ]
}');

INSERT INTO sigec.t_sigea_stati
(stato_id, descrizione, stato_inizio, stato_fine, accesso_condizionato, nome, configurazione)
VALUES('ANNULLATO', 'Annullato', false, false, true, 'ANNULLA', '{
  "isEditableByOwner":false,
  "isWorkerExclusive":false,
  "statiRaggiungibili": [
    {
      "statoId": "MODIFICA",
      "pubblicazione" : false,
      "revocaPubblicazione": false,
      "emailOwner" : "",
      "emailOperatori" : "",
      "addNote" : false,
      "statiSovrascritti" : [],
      "permessi": {
        "mustBeOwner": true
      }
    }
  ]
}');

INSERT INTO sigec.t_sigea_redazioni
(redazione_id, nome_redazione, link_redazione, nome_jsp, link_scheda, auto_spubblicazione, attivazione_jms)
VALUES('GENERAL', null, null, null, null, false, false);

INSERT INTO sigec.t_sigea_redazioni
(redazione_id, nome_redazione, link_redazione, nome_jsp, link_scheda, auto_spubblicazione, attivazione_jms)
VALUES('VIP', 'Viaggiare in Puglia', 'www.viaggiareinpuglia.it', 'vipScheda', null, true, true);

INSERT INTO sigec.t_sigea_tipologia_redazione
(tipologia_id, redazione_id)
VALUES('RED-PP', 'VIP');

INSERT INTO sigec.t_sigea_tipologia_redazione
(tipologia_id, redazione_id)
VALUES('REDCAP-PP', 'VIP');

INSERT INTO sigec.t_sigea_tipologia_redazione
(tipologia_id, redazione_id)
VALUES('REDVALCAP-PP', 'VIP');

INSERT INTO sigec.t_sigea_tipologie_mibact
(tipologia_id, descrizione)
VALUES('1.1.1', 'Festival');
INSERT INTO sigec.t_sigea_tipologie_mibact
(tipologia_id, descrizione)
VALUES('1.1.2', 'Mostra');
INSERT INTO sigec.t_sigea_tipologie_mibact
(tipologia_id, descrizione)
VALUES('1.1.3', 'Spettacolo teatrale');
INSERT INTO sigec.t_sigea_tipologie_mibact
(tipologia_id, descrizione)
VALUES('1.1.4', 'Spettacolo di danza');
INSERT INTO sigec.t_sigea_tipologie_mibact
(tipologia_id, descrizione)
VALUES('1.1.5', 'Manifestazione musicale');
INSERT INTO sigec.t_sigea_tipologie_mibact
(tipologia_id, descrizione)
VALUES('1.1.6', 'Visita guidata');
INSERT INTO sigec.t_sigea_tipologie_mibact
(tipologia_id, descrizione)
VALUES('1.1.7', 'Lettura (pubblica)');
INSERT INTO sigec.t_sigea_tipologie_mibact
(tipologia_id, descrizione)
VALUES('1.1.8', 'Proiezione cinematografica');
INSERT INTO sigec.t_sigea_tipologie_mibact
(tipologia_id, descrizione)
VALUES('1.1.9', 'Visita libera');
INSERT INTO sigec.t_sigea_tipologie_mibact
(tipologia_id, descrizione)
VALUES('1.2.2', 'Webinar');
INSERT INTO sigec.t_sigea_tipologie_mibact
(tipologia_id, descrizione)
VALUES('1.2.3', 'Seminario');
INSERT INTO sigec.t_sigea_tipologie_mibact
(tipologia_id, descrizione)
VALUES('1.2.4', 'Laboratorio');
INSERT INTO sigec.t_sigea_tipologie_mibact
(tipologia_id, descrizione)
VALUES('1.2.5', 'Presentazione libro');
INSERT INTO sigec.t_sigea_tipologie_mibact
(tipologia_id, descrizione)
VALUES('1.2.6', 'Corso');
INSERT INTO sigec.t_sigea_tipologie_mibact
(tipologia_id, descrizione)
VALUES('1.3.1', 'Convegno/Conferenza');
INSERT INTO sigec.t_sigea_tipologie_mibact
(tipologia_id, descrizione)
VALUES('2.4.1', 'Sfilata');
INSERT INTO sigec.t_sigea_tipologie_mibact
(tipologia_id, descrizione)
VALUES('2.4.2', 'Sagra');
INSERT INTO sigec.t_sigea_tipologie_mibact
(tipologia_id, descrizione)
VALUES('2.4.4', 'Festa patronale/Festa dei santi');
INSERT INTO sigec.t_sigea_tipologie_mibact
(tipologia_id, descrizione)
VALUES('4.1.1', 'Fiera/Salone');
INSERT INTO sigec.t_sigea_tipologie_mibact
(tipologia_id, descrizione)
VALUES('4.1.2', 'Esposizione/Esposizione globale');
INSERT INTO sigec.t_sigea_tipologie_mibact
(tipologia_id, descrizione)
VALUES('5.1.2', 'Gara/Torneo/Competizione');
INSERT INTO sigec.t_sigea_tipologie_mibact
(tipologia_id, descrizione)
VALUES('5.1.3', 'Escursione');
INSERT INTO sigec.t_sigea_tipologie_mibact
(tipologia_id, descrizione)
VALUES('mibact_1', 'Nuova apertura');
INSERT INTO sigec.t_sigea_tipologie_mibact
(tipologia_id, descrizione)
VALUES('mibact_2', 'Inaugurazione');
INSERT INTO sigec.t_sigea_tipologie_mibact
(tipologia_id, descrizione)
VALUES('mibact_3', 'Apertura straordinaria');
INSERT INTO sigec.t_sigea_tipologie_mibact
(tipologia_id, descrizione)
VALUES('mibact_4', 'Incontro/Presentazione');


INSERT INTO sigec.t_sigea_tipologie_attivita
(tipologia_id, descrizione)
VALUES('ATT_FORMAZIONE', 'Attività didattiche (Attività di formazione)');
INSERT INTO sigec.t_sigea_tipologie_attivita
(tipologia_id, descrizione)
VALUES('ATT_SPORTIVA', 'Attività sportiva');
INSERT INTO sigec.t_sigea_tipologie_attivita
(tipologia_id, descrizione)
VALUES('ESCURSIONE', 'Escursione');
INSERT INTO sigec.t_sigea_tipologie_attivita
(tipologia_id, descrizione)
VALUES('ITINERARIO', 'Itinerario');
INSERT INTO sigec.t_sigea_tipologie_attivita
(tipologia_id, descrizione)
VALUES('VISITA_GUIDATA', 'Visita guidata');


INSERT INTO sigec.t_sigea_mezzi
(mezzo_id, descrizione)
VALUES('1', 'Auto');
INSERT INTO sigec.t_sigea_mezzi
(mezzo_id, descrizione)
VALUES('2', 'Bici');
INSERT INTO sigec.t_sigea_mezzi
(mezzo_id, descrizione)
VALUES('3', 'Moto');
INSERT INTO sigec.t_sigea_mezzi
(mezzo_id, descrizione)
VALUES('4', 'A piedi');
INSERT INTO sigec.t_sigea_mezzi
(mezzo_id, descrizione)
VALUES('5', 'Per mare');
INSERT INTO sigec.t_sigea_mezzi
(mezzo_id, descrizione)
VALUES('6', 'Treno');


INSERT INTO sigec.t_sigea_prodotti
(prodotto_id, descrizione)
VALUES('1', 'Arte e Cultura');
INSERT INTO sigec.t_sigea_prodotti
(prodotto_id, descrizione)
VALUES('2', 'Tradizioni e Spiritualità (Riti e Tradizioni)');
INSERT INTO sigec.t_sigea_prodotti
(prodotto_id, descrizione)
VALUES('3', 'Natura, Sport e Benessere (Sport e Ambiente)');
INSERT INTO sigec.t_sigea_prodotti
(prodotto_id, descrizione)
VALUES('4', 'Enogastronomia');
INSERT INTO sigec.t_sigea_prodotti
(prodotto_id, descrizione)
VALUES('5', 'Mare');
INSERT INTO sigec.t_sigea_prodotti
(prodotto_id, descrizione)
VALUES('6', 'Meeting Incentive Congressi ed Eventi (Business e Fiere)');
INSERT INTO sigec.t_sigea_prodotti
(prodotto_id, descrizione)
VALUES('7', 'Teatro e Danza');
INSERT INTO sigec.t_sigea_prodotti
(prodotto_id, descrizione)
VALUES('8', 'Intrattenimento');
INSERT INTO sigec.t_sigea_prodotti
(prodotto_id, descrizione)
VALUES('9', 'Cinema');
INSERT INTO sigec.t_sigea_prodotti
(prodotto_id, descrizione)
VALUES('10', 'Musica');


INSERT INTO sigec.t_sigea_valore_attrattivita_turistica
(valore_id, descrizione)
VALUES('1', 'Non fruibile');
INSERT INTO sigec.t_sigea_valore_attrattivita_turistica
(valore_id, descrizione)
VALUES('2', 'Non rilevante');
INSERT INTO sigec.t_sigea_valore_attrattivita_turistica
(valore_id, descrizione)
VALUES('3', 'Poco interessante');
INSERT INTO sigec.t_sigea_valore_attrattivita_turistica
(valore_id, descrizione)
VALUES('4', 'Possibile attrattività');
INSERT INTO sigec.t_sigea_valore_attrattivita_turistica
(valore_id, descrizione)
VALUES('5', 'Apprezzabile');
INSERT INTO sigec.t_sigea_valore_attrattivita_turistica
(valore_id, descrizione)
VALUES('6', 'Degno di nota');
INSERT INTO sigec.t_sigea_valore_attrattivita_turistica
(valore_id, descrizione)
VALUES('7', 'Consigliato');
INSERT INTO sigec.t_sigea_valore_attrattivita_turistica
(valore_id, descrizione)
VALUES('8', 'Da visitare');
INSERT INTO sigec.t_sigea_valore_attrattivita_turistica
(valore_id, descrizione)
VALUES('9', 'Da non perdere');
INSERT INTO sigec.t_sigea_valore_attrattivita_turistica
(valore_id, descrizione)
VALUES('10', 'SuperUser');